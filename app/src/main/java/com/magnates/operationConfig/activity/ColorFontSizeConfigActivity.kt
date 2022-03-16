package com.magnates.operationConfig.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.LinearLayout
import com.magnates.operationConfig.R
import com.magnates.operationConfig.customviews.colorpicker.AmbilWarnaDialog
import com.magnates.operationConfig.databinding.ActivityColorFontSizeConfigBinding
import com.magnates.operationConfig.utils.Extensions.showToast
import com.magnates.operationConfig.utils.Utils
import com.magnates.operationConfig.utils.Utils.changeBg
import com.magnates.operationConfig.utils.Utils.getColorFromView
import com.magnates.operationConfig.utils.Utils.getIndex
import com.magnates.operationConfig.utils.Utils.getStringArrayFromXML

class ColorFontSizeConfigActivity : BaseActivity(), AdapterView.OnItemSelectedListener {
    private lateinit var binding: ActivityColorFontSizeConfigBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityColorFontSizeConfigBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    /**
     * This method is used to initialization process of activity
     */
    override fun init() {
        binding.headerLayout.tvHeader.text = getString(R.string.color_font_size_configuration)
        binding.spinnerFontType.onItemSelectedListener = this
        binding.spinnerTextFieldFontSize.onItemSelectedListener = this
        binding.spinnerButtonFontSize.onItemSelectedListener = this
        binding.spinnerTitleHeaderFontSize.onItemSelectedListener = this
        binding.spinnerHeaderFontSize.onItemSelectedListener = this
        binding.spinnerSubHeaderFontSize.onItemSelectedListener = this
        binding.spinnerSideMenuFontSize.onItemSelectedListener = this
        binding.spinnerBottomMenuFontSize.onItemSelectedListener = this
        binding.spinnerTabFontSize.onItemSelectedListener = this
        binding.spinnerCommonFontSize.onItemSelectedListener = this

        binding.root.post {
            setValuesToViews()
        }
    }

    /**
     * This method is used to set the values to views
     */
    private fun setValuesToViews() {
        Utils.dynamicUIModel?.themeColor?.primaryColor?.let {
            changeBg(
                binding.primaryColorLayout.displayColorView,
                it
            )
        }

        Utils.dynamicUIModel?.themeColor?.secondaryColor?.let {
            changeBg(
                binding.secondaryColorLayout.displayColorView,
                it
            )
        }

        Utils.dynamicUIModel?.themeColor?.commonFontColor?.let {
            changeBg(
                binding.commonFontColorLayout.displayColorView,
                it
            )
        }

        Utils.dynamicUIModel?.fontFamily?.let {
            getIndex(
                getStringArrayFromXML(this, R.array.dropdown_font_list_array),
                it
            )
        }?.let { binding.spinnerFontType.setSelection(it) }

        Utils.dynamicUIModel?.fontSize?.textField?.let {
            getIndex(
                getStringArrayFromXML(this, R.array.dropdown_font_size_array),
                it
            )
        }?.let { binding.spinnerTextFieldFontSize.setSelection(it) }

        Utils.dynamicUIModel?.fontSize?.button?.let {
            getIndex(
                getStringArrayFromXML(this, R.array.dropdown_font_size_array),
                it
            )
        }?.let { binding.spinnerButtonFontSize.setSelection(it) }

        Utils.dynamicUIModel?.fontSize?.titleHeader?.let {
            getIndex(
                getStringArrayFromXML(this, R.array.dropdown_font_size_array),
                it
            )
        }?.let { binding.spinnerTitleHeaderFontSize.setSelection(it) }

        Utils.dynamicUIModel?.fontSize?.header?.let {
            getIndex(
                getStringArrayFromXML(this, R.array.dropdown_title_header_font_size_array),
                it
            )
        }?.let { binding.spinnerHeaderFontSize.setSelection(it) }

        Utils.dynamicUIModel?.fontSize?.subHeader?.let {
            getIndex(
                getStringArrayFromXML(this, R.array.dropdown_font_size_array),
                it
            )
        }?.let { binding.spinnerSubHeaderFontSize.setSelection(it) }

        Utils.dynamicUIModel?.fontSize?.sideMenu?.let {
            getIndex(
                getStringArrayFromXML(this, R.array.dropdown_font_size_array),
                it
            )
        }?.let { binding.spinnerSideMenuFontSize.setSelection(it) }

        Utils.dynamicUIModel?.fontSize?.bottomMenu?.let {
            getIndex(
                getStringArrayFromXML(this, R.array.dropdown_bottom_menu_font_size_array),
                it
            )
        }?.let { binding.spinnerBottomMenuFontSize.setSelection(it) }

        Utils.dynamicUIModel?.fontSize?.tab?.let {
            getIndex(
                getStringArrayFromXML(this, R.array.dropdown_font_size_array),
                it
            )
        }?.let { binding.spinnerTabFontSize.setSelection(it) }

        Utils.dynamicUIModel?.fontSize?.common?.let {
            getIndex(
                getStringArrayFromXML(this, R.array.dropdown_font_size_array),
                it
            )
        }?.let { binding.spinnerCommonFontSize.setSelection(it) }
    }

    /**
     * This method is used when user select item in spinner
     */
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when (parent) {
            binding.spinnerFontType -> {
                if (position != 0) {
                    Utils.dynamicUIModel?.fontFamily = parent.getItemAtPosition(position).toString()
                } else {
                    Utils.dynamicUIModel?.fontFamily = ""
                }
            }
            binding.spinnerTextFieldFontSize -> {
                if (position != 0) {
                    Utils.dynamicUIModel?.fontSize?.textField =
                        parent.getItemAtPosition(position).toString()
                } else {
                    Utils.dynamicUIModel?.fontSize?.textField = ""
                }
            }
            binding.spinnerButtonFontSize -> {
                if (position != 0) {
                    Utils.dynamicUIModel?.fontSize?.button =
                        parent.getItemAtPosition(position).toString()
                } else {
                    Utils.dynamicUIModel?.fontSize?.button = ""
                }
            }
            binding.spinnerTitleHeaderFontSize -> {
                if (position != 0) {
                    Utils.dynamicUIModel?.fontSize?.titleHeader =
                        parent.getItemAtPosition(position).toString()
                } else {
                    Utils.dynamicUIModel?.fontSize?.titleHeader = ""
                }
            }
            binding.spinnerHeaderFontSize -> {
                if (position != 0) {
                    Utils.dynamicUIModel?.fontSize?.header =
                        parent.getItemAtPosition(position).toString()
                } else {
                    Utils.dynamicUIModel?.fontSize?.header = ""
                }
            }
            binding.spinnerSubHeaderFontSize -> {
                if (position != 0) {
                    Utils.dynamicUIModel?.fontSize?.subHeader =
                        parent.getItemAtPosition(position).toString()
                } else {
                    Utils.dynamicUIModel?.fontSize?.subHeader = ""
                }
            }
            binding.spinnerSideMenuFontSize -> {
                if (position != 0) {
                    Utils.dynamicUIModel?.fontSize?.sideMenu =
                        parent.getItemAtPosition(position).toString()
                } else {
                    Utils.dynamicUIModel?.fontSize?.sideMenu = ""
                }
            }
            binding.spinnerBottomMenuFontSize -> {
                if (position != 0) {
                    Utils.dynamicUIModel?.fontSize?.bottomMenu =
                        parent.getItemAtPosition(position).toString()
                } else {
                    Utils.dynamicUIModel?.fontSize?.bottomMenu = ""
                }
            }
            binding.spinnerTabFontSize -> {
                if (position != 0) {
                    Utils.dynamicUIModel?.fontSize?.tab =
                        parent.getItemAtPosition(position).toString()
                } else {
                    Utils.dynamicUIModel?.fontSize?.tab = ""
                }
            }
            binding.spinnerCommonFontSize -> {
                if (position != 0) {
                    Utils.dynamicUIModel?.fontSize?.common =
                        parent.getItemAtPosition(position).toString()
                } else {
                    Utils.dynamicUIModel?.fontSize?.common = ""
                }
            }

        }
    }

    /**
     * This method is called when user nothing select anything in spinner
     */
    override fun onNothingSelected(p0: AdapterView<*>?) {}

    /**
     * This method is called when user clicks on view
     */
    override fun onClick(view: View) {
        when (view) {
            binding.btnNext -> {
                when {
                    Utils.dynamicUIModel?.fontFamily.isNullOrBlank() -> {
                        showToast(resources.getString(R.string.error_message_select_font_type))
                    }
                    Utils.dynamicUIModel?.fontSize?.textField.isNullOrBlank() -> {
                        showToast(resources.getString(R.string.error_message_select_text_field_font_size))
                    }
                    Utils.dynamicUIModel?.fontSize?.button.isNullOrBlank() -> {
                        showToast(resources.getString(R.string.error_message_select_button_font_size))
                    }
                    Utils.dynamicUIModel?.fontSize?.titleHeader.isNullOrBlank() -> {
                        showToast(resources.getString(R.string.error_message_select_title_header_font_size))
                    }
                    Utils.dynamicUIModel?.fontSize?.header.isNullOrBlank() -> {
                        showToast(resources.getString(R.string.error_message_select_header_font_size))
                    }
                    Utils.dynamicUIModel?.fontSize?.subHeader.isNullOrBlank() -> {
                        showToast(resources.getString(R.string.error_message_select_sub_header_font_size))
                    }
                    Utils.dynamicUIModel?.fontSize?.sideMenu.isNullOrBlank() -> {
                        showToast(resources.getString(R.string.error_message_select_side_menu_font_size))
                    }
                    Utils.dynamicUIModel?.fontSize?.bottomMenu.isNullOrBlank() -> {
                        showToast(resources.getString(R.string.error_message_select_bottom_menu_font_size))
                    }
                    Utils.dynamicUIModel?.fontSize?.tab.isNullOrBlank() -> {
                        showToast(resources.getString(R.string.error_message_select_tab_font_size))
                    }
                    Utils.dynamicUIModel?.fontSize?.common.isNullOrBlank() -> {
                        showToast(resources.getString(R.string.error_message_select_common_font_size))
                    }
                    else -> {
                        if (Utils.dynamicUIModel?.themeColor?.primaryColor.isNullOrBlank()) {
                            Utils.dynamicUIModel?.themeColor?.primaryColor = String.format(
                                "#%08x",
                                Utils.getColorFromView(binding.primaryColorLayout.displayColorView)
                            )
                        }
                        if (Utils.dynamicUIModel?.themeColor?.secondaryColor.isNullOrBlank()) {
                            Utils.dynamicUIModel?.themeColor?.secondaryColor = String.format(
                                "#%08x",
                                Utils.getColorFromView(binding.secondaryColorLayout.displayColorView)
                            )
                        }
                        if (Utils.dynamicUIModel?.themeColor?.commonFontColor.isNullOrBlank()) {
                            Utils.dynamicUIModel?.themeColor?.commonFontColor = String.format(
                                "#%08x",
                                Utils.getColorFromView(binding.commonFontColorLayout.displayColorView)
                            )
                        }

                        startActivity(Intent(this, IconsConfigActivity::class.java))
                    }
                }
            }

            binding.btnBack -> onBackPressed()

            binding.primaryColorLayout.llSelectColor -> {
                callColorPickerDialog(
                    getColorFromView(binding.primaryColorLayout.displayColorView),
                    binding.primaryColorLayout.llSelectColor
                )
            }

            binding.secondaryColorLayout.llSelectColor -> {
                callColorPickerDialog(
                    getColorFromView(binding.secondaryColorLayout.displayColorView),
                    binding.secondaryColorLayout.llSelectColor
                )
            }

            binding.commonFontColorLayout.llSelectColor -> {
                callColorPickerDialog(
                    getColorFromView(binding.commonFontColorLayout.displayColorView),
                    binding.commonFontColorLayout.llSelectColor
                )
            }
        }
    }

    /**
     * This method is called to open color picker dialog
     */
    private fun callColorPickerDialog(viewColor: Int, linearLayout: LinearLayout) {
        val colorPickerDialog =
            AmbilWarnaDialog(this, viewColor, true, object : AmbilWarnaDialog.OnAmbilWarnaListener {
                override fun onCancel(dialog: AmbilWarnaDialog?) {
                }

                override fun onOk(dialog: AmbilWarnaDialog?, mColor: Int) {
                    setSelectedColor(mColor, linearLayout)
                }
            })
        colorPickerDialog.show()
    }

    /**
     * This method is called to set the selected color
     */
    private fun setSelectedColor(color: Int, linearLayout: LinearLayout) {
        val selectedColor = String.format("#%08x", color)

        when (linearLayout) {
            binding.primaryColorLayout.llSelectColor -> {
                changeBg(
                    binding.primaryColorLayout.displayColorView,
                    selectedColor
                )
                Utils.dynamicUIModel?.themeColor?.primaryColor = selectedColor
            }

            binding.secondaryColorLayout.llSelectColor -> {
                changeBg(
                    binding.secondaryColorLayout.displayColorView,
                    selectedColor
                )
                Utils.dynamicUIModel?.themeColor?.secondaryColor = selectedColor
            }

            binding.commonFontColorLayout.llSelectColor -> {
                changeBg(
                    binding.commonFontColorLayout.displayColorView,
                    selectedColor
                )
                Utils.dynamicUIModel?.themeColor?.commonFontColor = selectedColor
            }
        }
    }
}