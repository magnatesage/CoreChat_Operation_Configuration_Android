////////////////////////////////////////////////////////////////
// ++_COPYRIGHT_START_++
//   (C) Copyright XYZ Systems 202l
//
//   C O P Y R I G H T     N O T I C E
//   --------------------------------
//   The contents of this file are protected by copyright.
//   Any unauthorised copying, duplication of its
//   contents are breach of the copyright.
//
//
//   C O N F I D E N T I A L I T Y    O F    S O F T W A R E
//   -------------------------------------------------------
//   This Software file is CONFIDENTIAL.
//   The XYZ Systems Software and all information pertaining to it,
//   to the extent not published by XYZ Systems, is Confidential.
//   Full Title to the XYZ Systems Software remains
//   at all times in XYZ Systems.
//   The following is deemed Confidential Information with or
//   without marking or written confirmation:
//   (i)   the Software and other related materials furnished
//         by XYZ Systems;
//   (ii)  the oral and visual information relating to the Software
//         and provided in the Software Developers Kanban Tasks
//         including all attachments, designs and descriptions; and
//   (iii) XYZ Systems representation methods of modelled data
//         and databases.
//   Software Developers will not disclose such information
//   to any other party and by doing so will be a violation
//   of this Confidentiality Of Software.
//   By opening this file, you are bound to this
//   Confidentiality of Software.
// ++_COPYRIGHT_END_++
////////////////////////////////////////////////////////////////

package com.magnates.operationConfig.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import androidx.core.content.FileProvider
import com.magnates.operationConfig.BuildConfig
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
