package com.example.corechatoperationconfigapp.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import com.example.corechatoperationconfigapp.R
import com.example.corechatoperationconfigapp.databinding.ActivityBottomMenuConfigBinding
import com.example.corechatoperationconfigapp.utils.AppConstants
import com.example.corechatoperationconfigapp.utils.Extensions.showToast
import com.example.corechatoperationconfigapp.utils.Utils

class BottomMenuConfigActivity : BaseActivity(), AdapterView.OnItemSelectedListener {
    private lateinit var binding: ActivityBottomMenuConfigBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBottomMenuConfigBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun init() {
        binding.headerLayout.tvHeader.text = getString(R.string.bottom_menu_configuration)
        binding.spinnerDashboard.onItemSelectedListener = this
        binding.spinnerHistory.onItemSelectedListener = this
        binding.spinnerLiveChat.onItemSelectedListener = this

        binding.root.post {
            setValuesToViews()
        }
    }

    override fun onClick(view: View) {
        when (view) {
            binding.btnBack -> onBackPressed()

            binding.btnNext -> {
                when {
                    binding.etDashboard.text.toString().isEmpty() -> {
                        showToast(resources.getString(R.string.error_message_add_dashboard_menu_name))
                    }
                    Utils.dynamicUIModel?.bottomTabBar?.get(0)?.iconValue.isNullOrEmpty() -> {
                        showToast(resources.getString(R.string.error_message_select_dashboard_icon))
                    }
                    binding.etHistory.text.toString().isEmpty() -> {
                        showToast(resources.getString(R.string.error_message_add_history_menu_name))
                    }
                    Utils.dynamicUIModel?.bottomTabBar?.get(3)?.iconValue.isNullOrEmpty() -> {
                        showToast(resources.getString(R.string.error_message_select_history_icon))
                    }
                    binding.etLiveChat.text.toString().isEmpty() -> {
                        showToast(resources.getString(R.string.error_message_add_live_chat_menu_name))
                    }
                    Utils.dynamicUIModel?.bottomTabBar?.get(2)?.iconValue.isNullOrEmpty() -> {
                        showToast(resources.getString(R.string.error_message_select_live_chat_icon))
                    }
                    binding.etWaiting.text.toString().isEmpty() -> {
                        showToast(resources.getString(R.string.error_message_add_waiting_menu_name))
                    }
                    Utils.dynamicUIModel?.bottomTabBar?.get(1)?.iconValue.isNullOrEmpty() -> {
                        showToast(resources.getString(R.string.error_message_select_waiting_icon))
                    }
                    binding.etNotification.text.toString().isEmpty() -> {
                        showToast(resources.getString(R.string.error_message_add_notification_menu_name))
                    }
                    Utils.dynamicUIModel?.bottomTabBar?.get(4)?.iconValue.isNullOrEmpty() -> {
                        showToast(resources.getString(R.string.error_message_select_notification_icon))
                    }
                    else -> {
                        Utils.dynamicUIModel?.bottomTabBar?.get(0)?.textValue =
                            binding.etDashboard.text.toString()
                        Utils.dynamicUIModel?.bottomTabBar?.get(1)?.textValue =
                            binding.etHistory.text.toString()
                        Utils.dynamicUIModel?.bottomTabBar?.get(2)?.textValue =
                            binding.etLiveChat.text.toString()
                        Utils.dynamicUIModel?.bottomTabBar?.get(3)?.textValue =
                            binding.etLiveChat.text.toString()
                        Utils.dynamicUIModel?.bottomTabBar?.get(4)?.textValue =
                            binding.etLiveChat.text.toString()

                        if (binding.swBold.isChecked && binding.swItalic.isChecked) {
                            for (i in 0 until Utils.dynamicUIModel?.bottomTabBar?.size!!) {
                                Utils.dynamicUIModel?.bottomTabBar?.get(i)?.fontType =
                                    "bold-italic"
                            }
                        } else if (binding.swBold.isChecked) {
                            for (i in 0 until Utils.dynamicUIModel?.bottomTabBar?.size!!) {
                                Utils.dynamicUIModel?.bottomTabBar?.get(i)?.fontType = "bold"
                            }
                        } else if (binding.swItalic.isChecked) {
                            for (i in 0 until Utils.dynamicUIModel?.bottomTabBar?.size!!) {
                                Utils.dynamicUIModel?.bottomTabBar?.get(i)?.fontType = "italic"
                            }
                        }
                        startActivity(Intent(this, SideMenuConfigActivity::class.java))
                    }
                }
            }
        }
    }

    private fun setValuesToViews() {
        if (Utils.dynamicUIModel?.bottomTabBar?.size == 5) {
            for (i in 0 until Utils.dynamicUIModel?.bottomTabBar!!.size) {
                when (i) {
                    0 -> {
                        if (Utils.dynamicUIModel?.bottomTabBar?.get(i)?.fontType?.contains(
                                AppConstants.BOLD.lowercase()
                            ) == true
                        ) {
                            binding.swBold.isChecked = true
                        }

                        if (Utils.dynamicUIModel?.bottomTabBar?.get(i)?.fontType?.contains(
                                AppConstants.ITALIC.lowercase()
                            ) == true
                        ) {
                            binding.swItalic.isChecked = true
                        }

                        binding.etDashboard.setText(Utils.dynamicUIModel?.bottomTabBar?.get(i)?.textValue)

                        Utils.dynamicUIModel?.bottomTabBar?.get(i)?.iconValue?.let {
                            Utils.getIndex(
                                Utils.getStringArrayFromXML(
                                    this,
                                    R.array.dropdown_dashboard_icon_array
                                ),
                                it
                            )
                        }?.let { binding.spinnerDashboard.setSelection(it) }
                    }
                    1 -> {
                        binding.etWaiting.setText(Utils.dynamicUIModel?.bottomTabBar?.get(i)?.textValue)

                        Utils.dynamicUIModel?.bottomTabBar?.get(i)?.iconValue?.let {
                            Utils.getIndex(
                                Utils.getStringArrayFromXML(
                                    this,
                                    R.array.dropdown_waiting_icon_array
                                ),
                                it
                            )
                        }?.let { binding.spinnerWaiting.setSelection(it) }
                    }
                    2 -> {
                        binding.etLiveChat.setText(Utils.dynamicUIModel?.bottomTabBar?.get(i)?.textValue)

                        Utils.dynamicUIModel?.bottomTabBar?.get(i)?.iconValue?.let {
                            Utils.getIndex(
                                Utils.getStringArrayFromXML(
                                    this,
                                    R.array.dropdown_live_chat_icon_array
                                ),
                                it
                            )
                        }?.let { binding.spinnerLiveChat.setSelection(it) }
                    }
                    3 -> {
                        binding.etHistory.setText(Utils.dynamicUIModel?.bottomTabBar?.get(i)?.textValue)

                        Utils.dynamicUIModel?.bottomTabBar?.get(i)?.iconValue?.let {
                            Utils.getIndex(
                                Utils.getStringArrayFromXML(
                                    this,
                                    R.array.dropdown_history_icon_array
                                ),
                                it
                            )
                        }?.let { binding.spinnerHistory.setSelection(it) }
                    }
                    4 -> {
                        binding.etNotification.setText(Utils.dynamicUIModel?.bottomTabBar?.get(i)?.textValue)

                        Utils.dynamicUIModel?.bottomTabBar?.get(i)?.iconValue?.let {
                            Utils.getIndex(
                                Utils.getStringArrayFromXML(
                                    this,
                                    R.array.dropdown_notification_icon_array
                                ),
                                it
                            )
                        }?.let { binding.spinnerNotification.setSelection(it) }
                    }
                }
            }
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when (parent) {
            binding.spinnerDashboard -> {
                if (position != 0) {
                    Utils.dynamicUIModel?.bottomTabBar?.get(0)?.iconValue =
                        Utils.getOriginalIconValue(parent.getItemAtPosition(position).toString())
                } else {
                    Utils.dynamicUIModel?.bottomTabBar?.get(0)?.iconValue = ""
                }
            }

            binding.spinnerHistory -> {
                if (position != 0) {
                    Utils.dynamicUIModel?.bottomTabBar?.get(1)?.iconValue =
                        Utils.getOriginalIconValue(parent.getItemAtPosition(position).toString())
                } else {
                    Utils.dynamicUIModel?.bottomTabBar?.get(1)?.iconValue = ""
                }
            }

            binding.spinnerLiveChat -> {
                if (position != 0) {
                    Utils.dynamicUIModel?.bottomTabBar?.get(2)?.iconValue =
                        Utils.getOriginalIconValue(parent.getItemAtPosition(position).toString())
                } else {
                    Utils.dynamicUIModel?.bottomTabBar?.get(2)?.iconValue = ""
                }
            }
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }
}