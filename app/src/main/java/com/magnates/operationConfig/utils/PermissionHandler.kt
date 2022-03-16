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

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.magnates.operationConfig.BuildConfig
import java.util.*

/**
 * Class made for Runtime Permissions
 */
object PermissionHandler {
    private val remainingPermissionList: ArrayList<String> = ArrayList()
    var isPermissionAskedFirstTime = true

    /**
     * This method will be called first whenever there will be requirement of asking Runtime permissions.
     */
    fun initCheckPermissions(mActivity: Activity, permissionsArray: Array<String>, requestCode: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            for (permission in permissionsArray) {
                if (!isPermissionGranted(mActivity, permission)) {
                    remainingPermissionList.add(permission)
                }
            }

            if (remainingPermissionList.isNotEmpty()) {
                val remainingPermissionArray = remainingPermissionList.toTypedArray()
                ActivityCompat.requestPermissions(mActivity, remainingPermissionArray, requestCode)
            }
        }
    }

    /**
     * This method will check is any permission granted or not and will return Boolean value
     */
    private fun isPermissionGranted(mActivity: Activity, permission: String): Boolean {
        return ContextCompat.checkSelfPermission(
            mActivity.applicationContext, permission
        ) == PackageManager.PERMISSION_GRANTED
    }

    /**
     * This method will check permission array contains any permissions or not
     */
    fun hasPermissions(mActivity: Activity, permissions: Array<String>): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val tempList = ArrayList<String>()
            for (permission in permissions) {
                val permission1 = if (ContextCompat.checkSelfPermission(
                        mActivity,
                        permission
                    ) == PackageManager.PERMISSION_GRANTED
                ) null else permission
                if (permission1 != null) {
                    tempList.add(permission)
                }
            }
            return tempList.size == 0
        } else {
            return true
        }
    }

    /**
     * This method will check permissions are rationale or denied by user & add in respective List
     */
    fun checkPermissionAgain(mActivity: Activity, permissions: Array<String>, requestCode: Int) {
        val requirePermissionsToCheck = ArrayList<String>()
        val deniedPermissions = ArrayList<String>()

        val set = HashSet(listOf(*permissions))

        if (set.size > 0) {
            for (permission in set.toTypedArray()) {
                if (ContextCompat.checkSelfPermission(mActivity, permission) != PackageManager.PERMISSION_GRANTED) {
                    val isRationale = ActivityCompat.shouldShowRequestPermissionRationale(mActivity, permission)
                    if (isRationale) {
                        requirePermissionsToCheck.add(permission)
                    } else {
                        deniedPermissions.add(permission)
                    }
                }
            }
        }

        when {
            deniedPermissions.size > 0 -> showDialogForPermission(mActivity)
            requirePermissionsToCheck.size > 0 -> {
                isPermissionAskedFirstTime = false
                initCheckPermissions(mActivity, requirePermissionsToCheck.toTypedArray(), requestCode)
            }
            else -> onPermissionAllowed()
        }
    }

    /**
     * This method will be called when permissions will be allowed
     */
    private fun onPermissionAllowed() {
        ViewPermission.isPermissionAllowed()
    }

    /**
     * This method will open a dialog to user & ask for required permissions
     */
    private fun showDialogForPermission(mActivity: Activity) {
        val title = "Permissions Required"
        val message =
            "These Permissions are required for this app to work properly.\nPlease provide Permissions in Settings\nClick Ok to allow Permissions"

        android.app.AlertDialog.Builder(mActivity)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("OK") { dialog, which ->
                dialog.dismiss()
                mActivity.startActivity(
                    Intent(
                        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                        Uri.parse("package:" + BuildConfig.APPLICATION_ID)
                    )
                )
            }
            .create()
            .show()
    }
}
