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