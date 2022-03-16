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
import com.magnates.operationConfig.R
import com.magnates.operationConfig.databinding.ActivityDashboardTabConfigBinding
import com.magnates.operationConfig.utils.AppConstants
import com.magnates.operationConfig.utils.Extensions.showToast
import com.magnates.operationConfig.utils.Utils

class DashboardTabConfigActivity : BaseActivity() {
    private lateinit var binding: ActivityDashboardTabConfigBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardTabConfigBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    /**
     * This method is used to initialization process of activity
     */
    override fun init() {
        binding.headerLayout.tvHeader.text = getString(R.string.dashboard_tab_configuration)

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

            binding.btnPreview -> {
                when {
                    binding.etHome.text.toString().isEmpty() -> {
                        showToast(resources.getString(R.string.error_message_add_first_tab_name))
                    }
                    binding.etGroup.text.toString().isEmpty() -> {
                        showToast(resources.getString(R.string.error_message_add_second_tab_name))
                    }
                    binding.etFirstMainTabSubTab1.text.toString().isEmpty() -> {
                        showToast(resources.getString(R.string.error_message_add_first_main_first_sub_tab_name))
                    }
                    binding.etFirstMainTabSubTab2.text.toString().isEmpty() -> {
                        showToast(resources.getString(R.string.error_message_add_first_main_second_sub_tab_name))
                    }
                    binding.etSecondMainTabSubTab1.text.toString().isEmpty() -> {
                        showToast(resources.getString(R.string.error_message_add_second_main_first_sub_tab_name))
                    }
                    binding.etSecondMainTabSubTab2.text.toString().isEmpty() -> {
                        showToast(resources.getString(R.string.error_message_add_second_main_second_sub_tab_name))
                    }
                    else -> {
                        Utils.dynamicUIModel?.dashboard?.mainTab1?.textValue =
                            binding.etHome.text.toString()
                        Utils.dynamicUIModel?.dashboard?.mainTab2?.textValue =
                            binding.etGroup.text.toString()
                        Utils.dynamicUIModel?.dashboard?.mainTab1?.subTab1?.textValue =
                            binding.etFirstMainTabSubTab1.text.toString()
                        Utils.dynamicUIModel?.dashboard?.mainTab1?.subTab2?.textValue =
                            binding.etFirstMainTabSubTab2.text.toString()
                        Utils.dynamicUIModel?.dashboard?.mainTab2?.subGroup1?.textValue =
                            binding.etSecondMainTabSubTab1.text.toString()
                        Utils.dynamicUIModel?.dashboard?.mainTab2?.subGroup2?.textValue =
                            binding.etSecondMainTabSubTab2.text.toString()

                        if (binding.swBold.isChecked && binding.swItalic.isChecked) {
                            Utils.dynamicUIModel?.dashboard?.mainTab1?.fontType =
                                "bold-italic"
                            Utils.dynamicUIModel?.dashboard?.mainTab2?.fontType =
                                "bold-italic"
                            Utils.dynamicUIModel?.dashboard?.mainTab1?.subTab1?.fontType =
                                "bold-italic"
                            Utils.dynamicUIModel?.dashboard?.mainTab1?.subTab2?.fontType =
                                "bold-italic"
                            Utils.dynamicUIModel?.dashboard?.mainTab2?.subGroup1?.fontType =
                                "bold-italic"
                            Utils.dynamicUIModel?.dashboard?.mainTab2?.subGroup2?.fontType =
                                "bold-italic"
                        } else if (binding.swBold.isChecked) {
                            Utils.dynamicUIModel?.dashboard?.mainTab1?.fontType =
                                "bold"
                            Utils.dynamicUIModel?.dashboard?.mainTab2?.fontType =
                                "bold"
                            Utils.dynamicUIModel?.dashboard?.mainTab1?.subTab1?.fontType =
                                "bold"
                            Utils.dynamicUIModel?.dashboard?.mainTab1?.subTab2?.fontType =
                                "bold"
                            Utils.dynamicUIModel?.dashboard?.mainTab2?.subGroup1?.fontType =
                                "bold"
                            Utils.dynamicUIModel?.dashboard?.mainTab2?.subGroup2?.fontType =
                                "bold"
                        } else if (binding.swItalic.isChecked) {
                            Utils.dynamicUIModel?.dashboard?.mainTab1?.fontType =
                                "italic"
                            Utils.dynamicUIModel?.dashboard?.mainTab2?.fontType =
                                "italic"
                            Utils.dynamicUIModel?.dashboard?.mainTab1?.subTab1?.fontType =
                                "italic"
                            Utils.dynamicUIModel?.dashboard?.mainTab1?.subTab2?.fontType =
                                "italic"
                            Utils.dynamicUIModel?.dashboard?.mainTab2?.subGroup1?.fontType =
                                "italic"
                            Utils.dynamicUIModel?.dashboard?.mainTab2?.subGroup2?.fontType =
                                "italic"
                        }
                        startActivity(Intent(this, LoginActivity::class.java))
                    }
                }
            }
        }
    }

    /**
     * This method is used to set the values to views
     */
    private fun setValuesToViews() {
        if (Utils.dynamicUIModel?.dashboard?.mainTab1?.fontType?.contains(
                AppConstants.BOLD.lowercase()
            ) == true
        ) {
            binding.swBold.isChecked = true
        }

        if (Utils.dynamicUIModel?.dashboard?.mainTab1?.fontType?.contains(
                AppConstants.ITALIC.lowercase()
            ) == true
        ) {
            binding.swItalic.isChecked = true
        }

        binding.etHome.setText(Utils.dynamicUIModel?.dashboard?.mainTab1?.textValue)
        binding.etGroup.setText(Utils.dynamicUIModel?.dashboard?.mainTab2?.textValue)
        binding.etFirstMainTabSubTab1.setText(Utils.dynamicUIModel?.dashboard?.mainTab1?.subTab1?.textValue)
        binding.etFirstMainTabSubTab2.setText(Utils.dynamicUIModel?.dashboard?.mainTab1?.subTab2?.textValue)
        binding.etSecondMainTabSubTab1.setText(Utils.dynamicUIModel?.dashboard?.mainTab2?.subGroup1?.textValue)
        binding.etSecondMainTabSubTab2.setText(Utils.dynamicUIModel?.dashboard?.mainTab2?.subGroup2?.textValue)
    }
}