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
import com.magnates.operationConfig.databinding.ActivitySideMenuConfigBinding
import com.magnates.operationConfig.utils.AppConstants
import com.magnates.operationConfig.utils.Extensions.showToast
import com.magnates.operationConfig.utils.Utils

class SideMenuConfigActivity : BaseActivity(), AdapterView.OnItemSelectedListener {
    private lateinit var binding: ActivitySideMenuConfigBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySideMenuConfigBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    /**
     * This method is used to initialization process of activity
     */
    override fun init() {
        binding.headerLayout.tvHeader.text = getString(R.string.side_menu_configuration)
        binding.spinnerAccount.onItemSelectedListener = this
        binding.spinnerSettings.onItemSelectedListener = this
        binding.spinnerAdminSection.onItemSelectedListener = this
        binding.spinnerPrivacy.onItemSelectedListener = this
        binding.spinnerTAndC.onItemSelectedListener = this

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
                    binding.etAccount.text.toString().isEmpty() -> {
                        showToast(resources.getString(R.string.error_message_add_account_menu_name))
                    }
                    Utils.dynamicUIModel?.sideMenu?.get(0)?.iconValue.isNullOrEmpty() -> {
                        showToast(resources.getString(R.string.error_message_select_account_icon))
                    }
                    binding.etSettings.text.toString().isEmpty() -> {
                        showToast(resources.getString(R.string.error_message_add_setting_menu_name))
                    }
                    Utils.dynamicUIModel?.sideMenu?.get(1)?.iconValue.isNullOrEmpty() -> {
                        showToast(resources.getString(R.string.error_message_select_settings_icon))
                    }
                    binding.etAdminSection.text.toString().isEmpty() -> {
                        showToast(resources.getString(R.string.error_message_add_select_admin_section_menu_name))
                    }
                    Utils.dynamicUIModel?.sideMenu?.get(2)?.iconValue.isNullOrEmpty() -> {
                        showToast(resources.getString(R.string.error_message_select_admin_section_icon))
                    }
                    binding.etPrivacy.text.toString().isEmpty() -> {
                        showToast(resources.getString(R.string.error_message_add_privacy_menu_name))
                    }
                    Utils.dynamicUIModel?.sideMenu?.get(3)?.iconValue.isNullOrEmpty() -> {
                        showToast(resources.getString(R.string.error_message_select_privacy_icon))
                    }
                    binding.etTAndC.text.toString().isEmpty() -> {
                        showToast(resources.getString(R.string.error_message_add_t_and_c_menu_name))
                    }
                    Utils.dynamicUIModel?.sideMenu?.get(4)?.iconValue.isNullOrEmpty() -> {
                        showToast(resources.getString(R.string.error_message_select_t_and_c_icon))
                    }
                    else -> {
                        Utils.dynamicUIModel?.sideMenu?.get(0)?.textValue =
                            binding.etAccount.text.toString()
                        Utils.dynamicUIModel?.sideMenu?.get(1)?.textValue =
                            binding.etSettings.text.toString()
                        Utils.dynamicUIModel?.sideMenu?.get(2)?.textValue =
                            binding.etAdminSection.text.toString()
                        Utils.dynamicUIModel?.sideMenu?.get(3)?.textValue =
                            binding.etPrivacy.text.toString()
                        Utils.dynamicUIModel?.sideMenu?.get(4)?.textValue =
                            binding.etTAndC.text.toString()

                        if (binding.swBold.isChecked && binding.swItalic.isChecked) {
                            for (i in 0 until Utils.dynamicUIModel?.sideMenu?.size!!) {
                                Utils.dynamicUIModel?.sideMenu?.get(i)?.fontType =
                                    "bold-italic"
                            }
                        } else if (binding.swBold.isChecked) {
                            for (i in 0 until Utils.dynamicUIModel?.sideMenu?.size!!) {
                                Utils.dynamicUIModel?.sideMenu?.get(i)?.fontType = "bold"
                            }
                        } else if (binding.swItalic.isChecked) {
                            for (i in 0 until Utils.dynamicUIModel?.sideMenu?.size!!) {
                                Utils.dynamicUIModel?.sideMenu?.get(i)?.fontType = "italic"
                            }
                        }
                        startActivity(Intent(this, DashboardTabConfigActivity::class.java))
                    }
                }
            }
        }
    }

    /**
     * This method is used to set the values to views
     */
    private fun setValuesToViews() {
        if (Utils.dynamicUIModel?.sideMenu?.size == 5) {
            for (i in 0 until Utils.dynamicUIModel?.sideMenu!!.size) {
                when (i) {
                    0 -> {
                        if (Utils.dynamicUIModel?.sideMenu?.get(i)?.fontType?.contains(
                                AppConstants.BOLD.lowercase()
                            ) == true
                        ) {
                            binding.swBold.isChecked = true
                        }

                        if (Utils.dynamicUIModel?.sideMenu?.get(i)?.fontType?.contains(
                                AppConstants.ITALIC.lowercase()
                            ) == true
                        ) {
                            binding.swItalic.isChecked = true
                        }

                        binding.etAccount.setText(Utils.dynamicUIModel?.sideMenu?.get(i)?.textValue)

                        Utils.dynamicUIModel?.sideMenu?.get(i)?.iconValue?.let {
                            Utils.getIndex(
                                Utils.getStringArrayFromXML(
                                    this,
                                    R.array.dropdown_account_icon_array
                                ),
                                it
                            )
                        }?.let { binding.spinnerAccount.setSelection(it) }
                    }
                    1 -> {
                        binding.etSettings.setText(Utils.dynamicUIModel?.sideMenu?.get(i)?.textValue)

                        Utils.dynamicUIModel?.sideMenu?.get(i)?.iconValue?.let {
                            Utils.getIndex(
                                Utils.getStringArrayFromXML(
                                    this,
                                    R.array.dropdown_settings_icon_array
                                ),
                                it
                            )
                        }?.let { binding.spinnerSettings.setSelection(it) }
                    }
                    2 -> {
                        binding.etAdminSection.setText(Utils.dynamicUIModel?.sideMenu?.get(i)?.textValue)

                        Utils.dynamicUIModel?.sideMenu?.get(i)?.iconValue?.let {
                            Utils.getIndex(
                                Utils.getStringArrayFromXML(
                                    this,
                                    R.array.dropdown_admin_section_icon_array
                                ),
                                it
                            )
                        }?.let { binding.spinnerAdminSection.setSelection(it) }
                    }
                    3 -> {
                        binding.etPrivacy.setText(Utils.dynamicUIModel?.sideMenu?.get(i)?.textValue)

                        Utils.dynamicUIModel?.sideMenu?.get(i)?.iconValue?.let {
                            Utils.getIndex(
                                Utils.getStringArrayFromXML(
                                    this,
                                    R.array.dropdown_privacy_icon_array
                                ),
                                it
                            )
                        }?.let { binding.spinnerPrivacy.setSelection(it) }
                    }
                    4 -> {
                        binding.etTAndC.setText(Utils.dynamicUIModel?.sideMenu?.get(i)?.textValue)

                        Utils.dynamicUIModel?.sideMenu?.get(i)?.iconValue?.let {
                            Utils.getIndex(
                                Utils.getStringArrayFromXML(
                                    this,
                                    R.array.dropdown_t_and_c_icon_array
                                ),
                                it
                            )
                        }?.let { binding.spinnerTAndC.setSelection(it) }
                    }
                }
            }
        }
    }

    /**
     * This method is used when user select item in spinner
     */
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when (parent) {
            binding.spinnerAccount -> {
                if (position != 0) {
                    Utils.dynamicUIModel?.sideMenu?.get(0)?.iconValue =
                        Utils.getOriginalIconValue(parent.getItemAtPosition(position).toString())
                } else {
                    Utils.dynamicUIModel?.sideMenu?.get(0)?.iconValue = ""
                }
            }

            binding.spinnerSettings -> {
                if (position != 0) {
                    Utils.dynamicUIModel?.sideMenu?.get(1)?.iconValue =
                        Utils.getOriginalIconValue(parent.getItemAtPosition(position).toString())
                } else {
                    Utils.dynamicUIModel?.sideMenu?.get(1)?.iconValue = ""
                }
            }

            binding.spinnerAdminSection -> {
                if (position != 0) {
                    Utils.dynamicUIModel?.sideMenu?.get(2)?.iconValue =
                        Utils.getOriginalIconValue(parent.getItemAtPosition(position).toString())
                } else {
                    Utils.dynamicUIModel?.sideMenu?.get(2)?.iconValue = ""
                }
            }

            binding.spinnerPrivacy -> {
                if (position != 0) {
                    Utils.dynamicUIModel?.sideMenu?.get(3)?.iconValue =
                        Utils.getOriginalIconValue(parent.getItemAtPosition(position).toString())
                } else {
                    Utils.dynamicUIModel?.sideMenu?.get(3)?.iconValue = ""
                }
            }

            binding.spinnerTAndC -> {
                if (position != 0) {
                    Utils.dynamicUIModel?.sideMenu?.get(4)?.iconValue =
                        Utils.getOriginalIconValue(parent.getItemAtPosition(position).toString())
                } else {
                    Utils.dynamicUIModel?.sideMenu?.get(4)?.iconValue = ""
                }
            }
        }
    }

    /**
     * This method is called when user nothing select anything in spinner
     */
    override fun onNothingSelected(p0: AdapterView<*>?) {}
}