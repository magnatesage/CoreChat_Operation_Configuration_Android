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

package com.magnates.operationConfig.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import com.magnates.operationConfig.R
import com.magnates.operationConfig.databinding.ActivityBottomMenuConfigBinding
import com.magnates.operationConfig.utils.AppConstants
import com.magnates.operationConfig.utils.Extensions.showToast
import com.magnates.operationConfig.utils.Utils

class BottomMenuConfigActivity : BaseActivity(), AdapterView.OnItemSelectedListener {
    private lateinit var binding: ActivityBottomMenuConfigBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBottomMenuConfigBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    /**
     * This method is used to initialization process of activity
     */
    override fun init() {
        binding.headerLayout.tvHeader.text = getString(R.string.bottom_menu_configuration)
        binding.spinnerDashboard.onItemSelectedListener = this
        binding.spinnerHistory.onItemSelectedListener = this
        binding.spinnerLiveChat.onItemSelectedListener = this
        binding.spinnerWaiting.onItemSelectedListener = this
        binding.spinnerNotification.onItemSelectedListener = this

        binding.root.post {
            setValuesToViews()
        }
    }

    /**
     * This method is called when user clicks on view
     */
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
                            binding.etWaiting.text.toString()
                        Utils.dynamicUIModel?.bottomTabBar?.get(2)?.textValue =
                            binding.etLiveChat.text.toString()
                        Utils.dynamicUIModel?.bottomTabBar?.get(3)?.textValue =
                            binding.etHistory.text.toString()
                        Utils.dynamicUIModel?.bottomTabBar?.get(4)?.textValue =
                            binding.etNotification.text.toString()

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

    /**
     * This method is used to set the values in views
     */
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

    /**
     * This method is called when user select item in spinner
     */
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

            binding.spinnerWaiting -> {
                if (position != 0) {
                    Utils.dynamicUIModel?.bottomTabBar?.get(3)?.iconValue =
                        Utils.getOriginalIconValue(parent.getItemAtPosition(position).toString())
                } else {
                    Utils.dynamicUIModel?.bottomTabBar?.get(3)?.iconValue = ""
                }
            }

            binding.spinnerNotification -> {
                if (position != 0) {
                    Utils.dynamicUIModel?.bottomTabBar?.get(4)?.iconValue =
                        Utils.getOriginalIconValue(parent.getItemAtPosition(position).toString())
                } else {
                    Utils.dynamicUIModel?.bottomTabBar?.get(4)?.iconValue = ""
                }
            }
        }
    }

    /**
     * This method is called when user nothing select anything in spinner
     */
    override fun onNothingSelected(p0: AdapterView<*>?) { }
}