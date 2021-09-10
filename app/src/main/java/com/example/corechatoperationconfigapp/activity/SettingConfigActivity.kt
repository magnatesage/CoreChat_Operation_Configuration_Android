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