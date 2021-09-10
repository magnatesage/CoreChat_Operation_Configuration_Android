package com.example.corechatoperationconfigapp.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import com.example.corechatoperationconfigapp.R
import com.example.corechatoperationconfigapp.databinding.ActivityBottomMenuConfigBinding
import com.example.corechatoperationconfigapp.databinding.ActivityDashboardTabConfigBinding
import com.example.corechatoperationconfigapp.utils.AppConstants
import com.example.corechatoperationconfigapp.utils.Extensions.showToast
import com.example.corechatoperationconfigapp.utils.Utils

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