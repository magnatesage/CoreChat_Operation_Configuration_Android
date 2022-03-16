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
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

/**
 * This class is having all kotlin extensions
 */
object Extensions {

    /**
     * This method is used to visible view
     */
    fun View.visible() {
        visibility = View.VISIBLE
    }

    /**
     * This method is used to gone view
     */
    fun View.gone() {
        visibility = View.GONE
    }

    /**
     * This method is used to invisible view
     */
    fun View.invisible() {
        visibility = View.INVISIBLE
    }

    /**
     * This method is used to show toast in fragment
     */
    fun Fragment.showToast(msg: String) {
        context?.showToastMsg(msg)
    }

    /**
     * This method is used to show toast in activity
     */
    fun Activity.showToast(msg: String) {
        showToastMsg(msg)
    }

    /**
     * This method is used to show toast message
     */
    private fun Context.showToastMsg(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    /**
     * This method is used to set color in fragment
     */
    fun Fragment.color(colorId: Int): Int {
        return activity?.let { ContextCompat.getColor(it, colorId) }!!
    }

    /**
     * This method is used to set color in activity
     */
    fun Activity.color(colorId: Int): Int {
        return ContextCompat.getColor(this, colorId)
    }

    /**
     * This method is used to hide keyboard in fragment
     */
    fun Fragment.hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }

    /**
     * This method is used to hide keyboard in activity
     */
    fun Activity.hideKeyboard() {
        hideKeyboard(currentFocus ?: View(this))
    }

    /**
     * This method is used to hide keyboard
     */
    private fun Context.hideKeyboard(view: View) {
        val inputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}