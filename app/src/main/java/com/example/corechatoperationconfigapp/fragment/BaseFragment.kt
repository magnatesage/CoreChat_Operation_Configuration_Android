package com.example.corechatoperationconfigapp.fragment

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