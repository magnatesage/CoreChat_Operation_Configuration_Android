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