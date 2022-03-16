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
import com.magnates.operationConfig.R
import com.magnates.operationConfig.utils.*
import com.magnates.operationConfig.utils.Utils.createTempImageFile
import com.magnates.operationConfig.utils.Utils.getStringFromXML
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
                    com.magnates.operationConfig.BuildConfig.APPLICATION_ID + ".provider",
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