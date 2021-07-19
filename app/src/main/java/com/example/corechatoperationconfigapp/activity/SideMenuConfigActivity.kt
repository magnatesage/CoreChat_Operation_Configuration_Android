package com.example.corechatoperationconfigapp.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import com.example.corechatoperationconfigapp.R
import com.example.corechatoperationconfigapp.databinding.ActivitySideMenuConfigBinding
import com.example.corechatoperationconfigapp.utils.AppConstants
import com.example.corechatoperationconfigapp.utils.Extensions.showToast
import com.example.corechatoperationconfigapp.utils.Utils

class SideMenuConfigActivity : BaseActivity(), AdapterView.OnItemSelectedListener {
    private lateinit var binding: ActivitySideMenuConfigBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySideMenuConfigBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun init() {
        binding.headerLayout.tvHeader.text = getString(R.string.side_menu_configuration)
        binding.spinnerAccount.onItemSelectedListener = this
        binding.spinnerAboutUs.onItemSelectedListener = this
        binding.spinnerSelectTopics.onItemSelectedListener = this
        binding.spinnerPrivacy.onItemSelectedListener = this
        binding.spinnerTAndC.onItemSelectedListener = this
        binding.spinnerNotification.onItemSelectedListener = this
        binding.spinnerEula.onItemSelectedListener = this
        binding.spinnerDownloadData.onItemSelectedListener = this

        binding.root.post {
            setValuesToViews()
        }
    }

    override fun onClick(view: View) {
        when (view) {
            binding.btnBack -> onBackPressed()

            binding.btnNext -> {
                when {
                    binding.etAccount.text.toString().isEmpty() -> {
                        showToast(resources.getString(R.string.error_message_add_account_menu_name))
                    }
                    Utils.dynamicUIModel?.sideMenuList?.get(0)?.iconValue.isNullOrEmpty() -> {
                        showToast(resources.getString(R.string.error_message_select_account_icon))
                    }
                    binding.etAboutUs.text.toString().isEmpty() -> {
                        showToast(resources.getString(R.string.error_message_add_about_us_menu_name))
                    }
                    Utils.dynamicUIModel?.sideMenuList?.get(1)?.iconValue.isNullOrEmpty() -> {
                        showToast(resources.getString(R.string.error_message_select_about_us_icon))
                    }
                    binding.etSelectTopics.text.toString().isEmpty() -> {
                        showToast(resources.getString(R.string.error_message_add_select_topics_menu_name))
                    }
                    Utils.dynamicUIModel?.sideMenuList?.get(2)?.iconValue.isNullOrEmpty() -> {
                        showToast(resources.getString(R.string.error_message_select_topics_icon))
                    }
                    binding.etPrivacy.text.toString().isEmpty() -> {
                        showToast(resources.getString(R.string.error_message_add_privacy_menu_name))
                    }
                    Utils.dynamicUIModel?.sideMenuList?.get(3)?.iconValue.isNullOrEmpty() -> {
                        showToast(resources.getString(R.string.error_message_select_privacy_icon))
                    }
                    binding.etTAndC.text.toString().isEmpty() -> {
                        showToast(resources.getString(R.string.error_message_add_t_and_c_menu_name))
                    }
                    Utils.dynamicUIModel?.sideMenuList?.get(4)?.iconValue.isNullOrEmpty() -> {
                        showToast(resources.getString(R.string.error_message_select_t_and_c_icon))
                    }
                    binding.etNotification.text.toString().isEmpty() -> {
                        showToast(resources.getString(R.string.error_message_add_notification_menu_name))
                    }
                    Utils.dynamicUIModel?.sideMenuList?.get(5)?.iconValue.isNullOrEmpty() -> {
                        showToast(resources.getString(R.string.error_message_select_notification_icon))
                    }
                    binding.etEula.text.toString().isEmpty() -> {
                        showToast(resources.getString(R.string.error_message_add_eula_menu_name))
                    }
                    Utils.dynamicUIModel?.sideMenuList?.get(6)?.iconValue.isNullOrEmpty() -> {
                        showToast(resources.getString(R.string.error_message_select_eula_icon))
                    }
                    binding.etDownloadData.text.toString().isEmpty() -> {
                        showToast(resources.getString(R.string.error_message_add_download_data_menu_name))
                    }
                    Utils.dynamicUIModel?.sideMenuList?.get(7)?.iconValue.isNullOrEmpty() -> {
                        showToast(resources.getString(R.string.error_message_select_download_data_icon))
                    }
                    else -> {
                        Utils.dynamicUIModel?.sideMenuList?.get(0)?.textValue =
                            binding.etAccount.text.toString()
                        Utils.dynamicUIModel?.sideMenuList?.get(1)?.textValue =
                            binding.etAboutUs.text.toString()
                        Utils.dynamicUIModel?.sideMenuList?.get(2)?.textValue =
                            binding.etSelectTopics.text.toString()
                        Utils.dynamicUIModel?.sideMenuList?.get(3)?.textValue =
                            binding.etPrivacy.text.toString()
                        Utils.dynamicUIModel?.sideMenuList?.get(4)?.textValue =
                            binding.etTAndC.text.toString()
                        Utils.dynamicUIModel?.sideMenuList?.get(5)?.textValue =
                            binding.etNotification.text.toString()
                        Utils.dynamicUIModel?.sideMenuList?.get(6)?.textValue =
                            binding.etEula.text.toString()
                        Utils.dynamicUIModel?.sideMenuList?.get(7)?.textValue =
                            binding.etDownloadData.text.toString()

                        if (binding.swBold.isChecked && binding.swItalic.isChecked) {
                            for (i in 0 until Utils.dynamicUIModel?.sideMenuList?.size!!) {
                                Utils.dynamicUIModel?.sideMenuList?.get(i)?.fontType =
                                    "bold-italic"
                            }
                        } else if (binding.swBold.isChecked) {
                            for (i in 0 until Utils.dynamicUIModel?.sideMenuList?.size!!) {
                                Utils.dynamicUIModel?.sideMenuList?.get(i)?.fontType = "bold"
                            }
                        } else if (binding.swItalic.isChecked) {
                            for (i in 0 until Utils.dynamicUIModel?.sideMenuList?.size!!) {
                                Utils.dynamicUIModel?.sideMenuList?.get(i)?.fontType = "italic"
                            }
                        }

                        startActivity(Intent(this, ChatBubbleConfigActivity::class.java))
                    }
                }
            }
        }
    }

    private fun setValuesToViews() {
        if (Utils.dynamicUIModel?.sideMenuList?.size == 8) {
            for (i in 0 until Utils.dynamicUIModel?.sideMenuList!!.size) {
                when (i) {
                    0 -> {
                        if (Utils.dynamicUIModel?.sideMenuList?.get(i)?.fontType?.contains(
                                AppConstants.BOLD.lowercase()
                            ) == true
                        ) {
                            binding.swBold.isChecked = true
                        }

                        if (Utils.dynamicUIModel?.sideMenuList?.get(i)?.fontType?.contains(
                                AppConstants.ITALIC.lowercase()
                            ) == true
                        ) {
                            binding.swItalic.isChecked = true
                        }

                        binding.etAccount.setText(Utils.dynamicUIModel?.sideMenuList?.get(i)?.textValue)

                        Utils.dynamicUIModel?.sideMenuList?.get(i)?.iconValue?.let {
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
                        binding.etAboutUs.setText(Utils.dynamicUIModel?.sideMenuList?.get(i)?.textValue)

                        Utils.dynamicUIModel?.sideMenuList?.get(i)?.iconValue?.let {
                            Utils.getIndex(
                                Utils.getStringArrayFromXML(
                                    this,
                                    R.array.dropdown_about_us_icon_array
                                ),
                                it
                            )
                        }?.let { binding.spinnerAboutUs.setSelection(it) }
                    }
                    2 -> {
                        binding.etSelectTopics.setText(Utils.dynamicUIModel?.sideMenuList?.get(i)?.textValue)

                        Utils.dynamicUIModel?.sideMenuList?.get(i)?.iconValue?.let {
                            Utils.getIndex(
                                Utils.getStringArrayFromXML(
                                    this,
                                    R.array.dropdown_select_topics_icon_array
                                ),
                                it
                            )
                        }?.let { binding.spinnerSelectTopics.setSelection(it) }
                    }
                    3 -> {
                        binding.etPrivacy.setText(Utils.dynamicUIModel?.sideMenuList?.get(i)?.textValue)

                        Utils.dynamicUIModel?.sideMenuList?.get(i)?.iconValue?.let {
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
                        binding.etTAndC.setText(Utils.dynamicUIModel?.sideMenuList?.get(i)?.textValue)

                        Utils.dynamicUIModel?.sideMenuList?.get(i)?.iconValue?.let {
                            Utils.getIndex(
                                Utils.getStringArrayFromXML(
                                    this,
                                    R.array.dropdown_t_and_c_icon_array
                                ),
                                it
                            )
                        }?.let { binding.spinnerTAndC.setSelection(it) }
                    }
                    5 -> {
                        binding.etNotification.setText(Utils.dynamicUIModel?.sideMenuList?.get(i)?.textValue)

                        Utils.dynamicUIModel?.sideMenuList?.get(i)?.iconValue?.let {
                            Utils.getIndex(
                                Utils.getStringArrayFromXML(
                                    this,
                                    R.array.dropdown_notification_icon_array
                                ),
                                it
                            )
                        }?.let { binding.spinnerNotification.setSelection(it) }
                    }
                    6 -> {
                        binding.etEula.setText(Utils.dynamicUIModel?.sideMenuList?.get(i)?.textValue)

                        Utils.dynamicUIModel?.sideMenuList?.get(i)?.iconValue?.let {
                            Utils.getIndex(
                                Utils.getStringArrayFromXML(
                                    this,
                                    R.array.dropdown_eula_icon_array
                                ),
                                it
                            )
                        }?.let { binding.spinnerEula.setSelection(it) }
                    }
                    7 -> {
                        binding.etDownloadData.setText(Utils.dynamicUIModel?.sideMenuList?.get(i)?.textValue)

                        Utils.dynamicUIModel?.sideMenuList?.get(i)?.iconValue?.let {
                            Utils.getIndex(
                                Utils.getStringArrayFromXML(
                                    this,
                                    R.array.dropdown_download_data_icon_array
                                ),
                                it
                            )
                        }?.let { binding.spinnerDownloadData.setSelection(it) }
                    }
                }
            }
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when (parent) {
            binding.spinnerAccount -> {
                if (position != 0) {
                    Utils.dynamicUIModel?.sideMenuList?.get(0)?.iconValue =
                        Utils.getOriginalIconValue(parent.getItemAtPosition(position).toString())
                } else {
                    Utils.dynamicUIModel?.sideMenuList?.get(0)?.iconValue = ""
                }
            }

            binding.spinnerAboutUs -> {
                if (position != 0) {
                    Utils.dynamicUIModel?.sideMenuList?.get(1)?.iconValue =
                        Utils.getOriginalIconValue(parent.getItemAtPosition(position).toString())
                } else {
                    Utils.dynamicUIModel?.sideMenuList?.get(1)?.iconValue = ""
                }
            }

            binding.spinnerSelectTopics -> {
                if (position != 0) {
                    Utils.dynamicUIModel?.sideMenuList?.get(2)?.iconValue =
                        Utils.getOriginalIconValue(parent.getItemAtPosition(position).toString())
                } else {
                    Utils.dynamicUIModel?.sideMenuList?.get(2)?.iconValue = ""
                }
            }

            binding.spinnerPrivacy -> {
                if (position != 0) {
                    Utils.dynamicUIModel?.sideMenuList?.get(3)?.iconValue =
                        Utils.getOriginalIconValue(parent.getItemAtPosition(position).toString())
                } else {
                    Utils.dynamicUIModel?.sideMenuList?.get(3)?.iconValue = ""
                }
            }

            binding.spinnerTAndC -> {
                if (position != 0) {
                    Utils.dynamicUIModel?.sideMenuList?.get(4)?.iconValue =
                        Utils.getOriginalIconValue(parent.getItemAtPosition(position).toString())
                } else {
                    Utils.dynamicUIModel?.sideMenuList?.get(4)?.iconValue = ""
                }
            }

            binding.spinnerNotification -> {
                if (position != 0) {
                    Utils.dynamicUIModel?.sideMenuList?.get(5)?.iconValue =
                        Utils.getOriginalIconValue(parent.getItemAtPosition(position).toString())
                } else {
                    Utils.dynamicUIModel?.sideMenuList?.get(5)?.iconValue = ""
                }
            }

            binding.spinnerEula -> {
                if (position != 0) {
                    Utils.dynamicUIModel?.sideMenuList?.get(6)?.iconValue =
                        Utils.getOriginalIconValue(parent.getItemAtPosition(position).toString())
                } else {
                    Utils.dynamicUIModel?.sideMenuList?.get(6)?.iconValue = ""
                }
            }

            binding.spinnerDownloadData -> {
                if (position != 0) {
                    Utils.dynamicUIModel?.sideMenuList?.get(7)?.iconValue =
                        Utils.getOriginalIconValue(parent.getItemAtPosition(position).toString())
                } else {
                    Utils.dynamicUIModel?.sideMenuList?.get(7)?.iconValue = ""
                }
            }
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }
}