package com.example.corechatoperationconfigapp.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import androidx.core.content.FileProvider
import com.example.corechatoperationconfigapp.BuildConfig
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.nio.channels.FileChannel

/**
 * This class helps in copy image picked from gallery to a temporary file
 * created in external storeage directory & get URI from temporary file & display in ImageView.
 */
object ImageCopyHelperClass {

    /**
     * This method return content URI of a file
     */
    fun getUriOfFile(context: Context, file: File): Uri {
        return FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".provider", file)
    }

    /**
     * This method will copy file from source to destination.
     * @param sourceFile : file object which should be copied from
     * @param destFile   : file object should be copied to
     */
    @Throws(IOException::class)
    fun copyFile(sourceFile: File, destFile: File) {
        if (!sourceFile.exists()) {
            return
        }

        val source: FileChannel? = FileInputStream(sourceFile).channel
        val destination: FileChannel? = FileOutputStream(destFile).channel
        if (destination != null && source != null) {
            source.transferTo(0,source.size(),destination)
            destination.transferFrom(source, 0, source.size())
        }
        source?.close()
        destination?.close()
    }

    /**
     * This method will copy file from source to destination.
     * @param src : file object which should be copied from
     * @param dst : file object should be copied to
     */
    @Throws(IOException::class)
    fun copy(src: File?, dst: File?) {
        FileInputStream(src).use { `in` ->
            FileOutputStream(dst).use { out ->
                // Transfer bytes from in to out
                val buf = ByteArray(1024)
                var len: Int
                while (`in`.read(buf).also { len = it } > 0) {
                    out.write(buf, 0, len)
                }
            }
        }
    }

    /**
     * This method will return file from intent data & uri
     */
    fun getBitmapFile(context: Context, data: Intent): File {
        var selectedImagePath = ""
        val selectedImage = data.data
        if (selectedImage != null) {
            val cursor = context.contentResolver.query(
                selectedImage, arrayOf(MediaStore.Images.ImageColumns.DATA),
                null,
                null,
                null
            )
            if (cursor != null) {
                cursor.moveToFirst()
                val idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
                selectedImagePath = cursor.getString(idx)
                cursor.close()
            }
        }
        return File(selectedImagePath)
    }
}
