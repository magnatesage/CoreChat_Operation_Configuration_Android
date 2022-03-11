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

package com.example.corechatoperationconfigapp.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.corechatoperationconfigapp.R
import com.example.corechatoperationconfigapp.databinding.ActivitySettingConfigBinding
import com.example.corechatoperationconfigapp.utils.AppPref
import com.example.corechatoperationconfigapp.utils.Extensions.showToast
import com.example.corechatoperationconfigapp.utils.Utils

class SettingConfigActivity : BaseActivity() {
    private lateinit var binding: ActivitySettingConfigBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingConfigBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    /**
     * This method is used to initialization process of activity
     */
    override fun init() {
        AppPref.deleteAllSharedPrefData(this)
        Utils.getDefaultUIModel(this)
        binding.headerLayout.tvHeader.text = getString(R.string.setting)
        setValuesToViews()
    }

    /**
     * This method is used to set the values to views
     */
    private fun setValuesToViews() {
        binding.etBackgroundAppTimeout.setText(Utils.dynamicUIModel?.setting?.backgroundAppTimeoutNotUsedMinutes.toString())
        binding.etForegroundAppTimeout.setText(Utils.dynamicUIModel?.setting?.foregroundAppTimeoutNotUsedMinutes.toString())
    }

    /**
     * This method is called when user clicks on view
     */
    override fun onClick(view: View) {
        when(view) {
            binding.btnNext -> {
                val backgroundAppTimeout = binding.etBackgroundAppTimeout.text?.trim().toString()
                val foregroundAppTimeout = binding.etForegroundAppTimeout.text?.trim().toString()
                when {
                    backgroundAppTimeout.isBlank() -> {
                        showToast(resources.getString(R.string.error_message_add_background_app_timeout))
                    }
                    foregroundAppTimeout.isBlank() -> {
                        showToast(resources.getString(R.string.error_message_add_foreground_app_timeout))
                    }
                    else -> {
                        Utils.dynamicUIModel?.setting?.backgroundAppTimeoutNotUsedMinutes = backgroundAppTimeout.toInt()
                        Utils.dynamicUIModel?.setting?.foregroundAppTimeoutNotUsedMinutes = foregroundAppTimeout.toInt()

                        startActivity(Intent(this, SplashConfigActivity::class.java))
                    }
                }
            }
        }
    }
}