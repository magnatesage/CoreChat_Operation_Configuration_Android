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

package com.magnates.operationConfig.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.magnates.operationConfig.BuildConfig
import com.magnates.operationConfig.R
import com.magnates.operationConfig.utils.AppPref
import com.magnates.operationConfig.utils.ImageCopyHelperClass
import com.magnates.operationConfig.utils.PermissionHandler
import com.magnates.operationConfig.utils.Utils.createTempImageFile
import com.magnates.operationConfig.utils.Utils.getStringFromXML
import com.magnates.operationConfig.utils.ViewPermission
import java.io.File
import java.io.IOException

/**
 * This class is extended by all application activities
 */
abstract class BaseActivity: AppCompatActivity(), ViewPermission.PermissionInterface {
    var mPhotoFile: File? = null
    var mPhotoUri: Uri? = null
    val REQUEST_TAKE_PHOTO = 101
    val REQUEST_GALLERY_PHOTO = 102
    var imageUri: Uri? = null
    private var uriImagePath: Uri? = null
    var mFile: File? = null
    val PERMISSION_REQ_CODE = 100

    lateinit var requiredPermissions: Array<String>

    fun requiredPermissions(permissions: Array<String>) {
        requiredPermissions = permissions
    }

    fun getViewPermissionInterfaceInstance(activityReference: ViewPermission.PermissionInterface) {
        ViewPermission().setOnPermissionInterface(activityReference)
    }


    override fun setContentView(view: View?) {
        super.setContentView(view)
        try {
            init()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * This method is used to initialization process of activity
     */
    abstract fun init()

    /**
     * This method is called when user clicks on view
     */
    abstract fun onClick(view: View)

    /**
     * This method is called to ask required runtime permission to user
     */
    fun getPermissions() {
        if (PermissionHandler.hasPermissions(this, requiredPermissions)) {
            permissionGranted()
        } else {
            PermissionHandler.initCheckPermissions(this, requiredPermissions, PERMISSION_REQ_CODE)
        }
    }

    /**
     * This method is used to open popup having option of opening Camera and Gallery.
     */
    fun openCameraGallery(context: Activity, fileName: String) {
        val getImageFrom: AlertDialog.Builder = AlertDialog.Builder(context)
        getImageFrom.setTitle(getStringFromXML(this, R.string.select))
        val opsChars =
            arrayOf(
                getStringFromXML(this, R.string.camera),
                getStringFromXML(this, R.string.gallery)
            )

        getImageFrom.setItems(opsChars) { dialog, which ->
            if (which == 0) {
                callCameraIntent(fileName)
            } else if (which == 1) {
                callGalleryIntent(fileName)
            }
            dialog.dismiss()
        }
        getImageFrom.show()
    }

    /**
     * This method will be called when asked runtime permissions will be allowed.
     */
    override fun permissionAllowed() {
        permissionGranted()
    }

    /**
     * This method will be called once permissionAllowed is called.
     * This method has different different implementations as per requirement in respective Activity.
     */
    open fun permissionGranted() {}

    /**
     * This method will be used to get permission result on request.
     */
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PERMISSION_REQ_CODE -> {
                PermissionHandler.checkPermissionAgain(this, permissions, requestCode)
            }
        }
    }

    /**
     * Capture image from camera
     */
    private fun callCameraIntent(fileName: String) {
        val photoURI: Uri?
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (takePictureIntent.resolveActivity(packageManager) != null) {
            // Create the File where the photo should go
            var photoFile: File? = null
            try {
                photoFile = createTempImageFile(this, fileName)
            } catch (ex: IOException) {
                ex.printStackTrace()
            }

            if (photoFile != null) {
                photoURI = FileProvider.getUriForFile(
                    this,
                    BuildConfig.APPLICATION_ID + ".provider",
                    photoFile
                )
                mPhotoFile = photoFile
                mPhotoUri = photoURI
            }
            AppPref.setValue(this,"mPhotoUri", mPhotoUri.toString())
            AppPref.setValue(this, "mPhotoFile", mPhotoFile?.absolutePath!!)
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, mPhotoUri)
            takePictureIntent.putExtra("return-data", true)
            takePictureIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            takePictureIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
            startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO)
        }
    }

    /**
     * Select image from gallery
     */
    @SuppressLint("QueryPermissionsNeeded")
    private fun callGalleryIntent(fileName: String) {
        try {
            mFile = createTempImageFile(this,fileName)
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
        uriImagePath = ImageCopyHelperClass.getUriOfFile(this, mFile!!)

        val pickPhoto = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        pickPhoto.type = "image/*"
        pickPhoto.putExtra(MediaStore.EXTRA_OUTPUT, uriImagePath)
        pickPhoto.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        pickPhoto.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
        if (pickPhoto.resolveActivity(packageManager) != null) {
            startActivityForResult(pickPhoto, REQUEST_GALLERY_PHOTO)
        }
    }

}