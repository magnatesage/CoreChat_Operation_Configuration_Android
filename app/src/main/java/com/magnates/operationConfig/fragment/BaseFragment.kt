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

package com.magnates.operationConfig.fragment

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment : Fragment() {

    private var _binding: ViewBinding? = null

    fun bindView(_binding: ViewBinding?) {
        this._binding = _binding
        if (_binding != null) {
            init()
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
     * This method is called when user switch between activities
     */
    open fun moveActivity(
        context: Context,
        c: Class<*>?,
        finish: Boolean,
        clearStack: Boolean,
        bundle: Bundle?
    ) {
        val intent = Intent(context, c)
        if (bundle != null) {
            intent.putExtras(bundle)
        }
        if (clearStack) {
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        context.startActivity(intent)
        if (finish) {
            (context as Activity).finish()
        }
    }

    /**
     * This method is called when user switch between activities
     */
    open fun moveActivity(
        context: Context,
        intent: Intent,
        finish: Boolean,
        clearStack: Boolean,
        bundle: Bundle?
    ) {
        if (bundle != null) {
            intent.putExtras(bundle)
        }
        if (finish) {
            (context as Activity).finish()
        }
        if (clearStack) {
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        context.startActivity(intent)
    }

    /**
     * This method is called when fragment is destroy
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}