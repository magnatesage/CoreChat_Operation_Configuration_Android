package com.magnates.operationConfig.utils

/**
 * This class consist of interface PermissionInterface
 * This @interface is used for transmitting data from Permission handler Class to Activity/Fragment class.
 */
class ViewPermission {

    fun setOnPermissionInterface(permissionInterface: PermissionInterface?) {
        if (mPermissionInterface == null && permissionInterface != null) {
            mPermissionInterface = permissionInterface
        }
    }

    interface PermissionInterface {
        fun permissionAllowed()
    }

    companion object {

        private var mPermissionInterface: PermissionInterface? = null

        fun isPermissionAllowed() {
            if (mPermissionInterface != null) {
                mPermissionInterface!!.permissionAllowed()
            }
        }
    }
}