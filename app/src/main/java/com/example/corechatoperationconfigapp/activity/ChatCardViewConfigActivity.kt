package com.example.corechatoperationconfigapp.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.LinearLayout
import androidx.recyclerview.widget.GridLayoutManager
import com.example.corechatoperationconfigapp.R
import com.example.corechatoperationconfigapp.adapter.ChatCardViewShapesAdapter
import com.example.corechatoperationconfigapp.customviews.colorpicker.AmbilWarnaDialog
import com.example.corechatoperationconfigapp.databinding.ActivityChatCardViewConfigBinding
import com.example.corechatoperationconfigapp.utils.AppConstants
import com.example.corechatoperationconfigapp.utils.Extensions.showToast
import com.example.corechatoperationconfigapp.utils.Utils

class ChatCardViewConfigActivity : BaseActivity(), AdapterView.OnItemSelectedListener {
    private lateinit var binding: ActivityChatCardViewConfigBinding
    private lateinit var chatCardViewShapesAdapter: ChatCardViewShapesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatCardViewConfigBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    /**
     * This method is used to initialization process of activity
     */
    override fun init() {
        binding.headerLayout.tvHeader.text = getString(R.string.chat_cardview_configuration)
        binding.spinnerBorderSize.onItemSelectedListener = this
        binding.spinnerHeaderTextSize.onItemSelectedListener = this
        binding.spinnerFooterTextSize.onItemSelectedListener = this

        binding.rvCardviewShapes.apply {
            layoutManager = GridLayoutManager(context, 2)
        }

        chatCardViewShapesAdapter = ChatCardViewShapesAdapter(this)
        binding.rvCardviewShapes.adapter = chatCardViewShapesAdapter

        binding.swDropShadow.setOnCheckedChangeListener { _, isChecked ->
            Utils.dynamicUIModel?.chat?.cardView?.cardViewBgDropShadow = isChecked
        }

        setValuesToViews()
    }

    /**
     * This method is used to set the values to views
     */
    private fun setValuesToViews() {
        if (!Utils.dynamicUIModel?.chat?.cardView?.cardViewBorderSize.isNullOrBlank()) {
            val selectedPosition = Utils.getIndex(
                Utils.getStringArrayFromXML(this, R.array.border_size_array),
                Utils.dynamicUIModel?.chat?.cardView?.cardViewBorderSize!!
            )
            binding.spinnerBorderSize.post {
                binding.spinnerBorderSize.setSelection(selectedPosition)
            }
        }

        if (!Utils.dynamicUIModel?.chat?.cardView?.cardViewHeaderTextSize.isNullOrBlank()) {
            val selectedPosition = Utils.getIndex(
                Utils.getStringArrayFromXML(this, R.array.dropdown_font_size_array),
                Utils.dynamicUIModel?.chat?.cardView?.cardViewHeaderTextSize!!
            )
            binding.spinnerHeaderTextSize.post {
                binding.spinnerHeaderTextSize.setSelection(selectedPosition)
            }
        }

        if (!Utils.dynamicUIModel?.chat?.cardView?.cardViewFooterButtonTextSize.isNullOrBlank()) {
            val selectedPosition =
                Utils.getIndex(
                    Utils.getStringArrayFromXML(this, R.array.dropdown_font_size_array),
                    Utils.dynamicUIModel?.chat?.cardView?.cardViewFooterButtonTextSize!!
                )
            binding.spinnerFooterTextSize.post {
                binding.spinnerFooterTextSize.setSelection(selectedPosition)
            }
        }

        if (!Utils.dynamicUIModel?.chat?.cardView?.cardViewBorderColor.isNullOrBlank()) {
            Utils.changeBg(
                binding.borderColorLayout.displayColorView,
                Utils.dynamicUIModel?.chat?.cardView?.cardViewBorderColor!!
            )
        }

        if (!Utils.dynamicUIModel?.chat?.cardView?.cardViewHeaderBgColor.isNullOrBlank()) {
            Utils.changeBg(
                binding.headerBgColorLayout.displayColorView,
                Utils.dynamicUIModel?.chat?.cardView?.cardViewHeaderBgColor!!
            )
        }

        if (!Utils.dynamicUIModel?.chat?.cardView?.cardViewHeaderTextColor.isNullOrBlank()) {
            Utils.changeBg(
                binding.headerTextColorLayout.displayColorView,
                Utils.dynamicUIModel?.chat?.cardView?.cardViewHeaderTextColor!!
            )
        }

        if (!Utils.dynamicUIModel?.chat?.cardView?.cardViewFooterButtonBgColor.isNullOrBlank()) {
            Utils.changeBg(
                binding.footerBgColorLayout.displayColorView,
                Utils.dynamicUIModel?.chat?.cardView?.cardViewFooterButtonBgColor!!
            )
        }

        if (!Utils.dynamicUIModel?.chat?.cardView?.cardViewFooterButtonTextColor.isNullOrBlank()) {
            Utils.changeBg(
                binding.footerTextColorLayout.displayColorView,
                Utils.dynamicUIModel?.chat?.cardView?.cardViewFooterButtonTextColor!!
            )
        }

        if (Utils.dynamicUIModel?.chat?.cardView?.cardViewBgDropShadow != null) {
            binding.swDropShadow.isChecked =
                Utils.dynamicUIModel?.chat?.cardView?.cardViewBgDropShadow!!
        }

        if (Utils.dynamicUIModel?.chat?.cardView?.cardViewShapeId != null &&
            Utils.dynamicUIModel?.chat?.cardView?.cardViewShapeId != 0
        ) {
            binding.rvCardviewShapes.smoothScrollToPosition(
                chatCardViewShapesAdapter.setAndGetSelectedChatCardView(Utils.dynamicUIModel?.chat?.cardView?.cardViewShapeId!!)
            )
        }
    }

    /**
     * This method is called when user clicks on view
     */
    override fun onClick(view: View) {
        when (view) {
            binding.borderColorLayout.llSelectColor -> {
                callColorPickerDialog(
                    Utils.getColorFromView(binding.borderColorLayout.displayColorView),
                    binding.borderColorLayout.llSelectColor
                )
            }

            binding.headerBgColorLayout.llSelectColor -> {
                callColorPickerDialog(
                    Utils.getColorFromView(binding.headerBgColorLayout.displayColorView),
                    binding.headerBgColorLayout.llSelectColor
                )
            }

            binding.headerTextColorLayout.llSelectColor -> {
                callColorPickerDialog(
                    Utils.getColorFromView(binding.headerTextColorLayout.displayColorView),
                    binding.headerTextColorLayout.llSelectColor
                )
            }

            binding.footerBgColorLayout.llSelectColor -> {
                callColorPickerDialog(
                    Utils.getColorFromView(binding.footerBgColorLayout.displayColorView),
                    binding.footerBgColorLayout.llSelectColor
                )
            }

            binding.footerTextColorLayout.llSelectColor -> {
                callColorPickerDialog(
                    Utils.getColorFromView(binding.footerTextColorLayout.displayColorView),
                    binding.footerTextColorLayout.llSelectColor
                )
            }

            binding.btnBack -> onBackPressed()

            binding.btnPreview -> {
                if (Utils.dynamicUIModel?.chat?.cardView?.cardViewShapeId == null ||
                    Utils.dynamicUIModel?.chat?.cardView?.cardViewShapeId == 0
                ) {
                    showToast(resources.getString(R.string.error_message_select_cardview_background_shape))
                } else if (Utils.dynamicUIModel?.chat?.cardView?.cardViewBorderSize.isNullOrBlank()) {
                    showToast(resources.getString(R.string.error_message_select_cardview_border_size))
                } else if (Utils.dynamicUIModel?.chat?.cardView?.cardViewHeaderTextSize.isNullOrBlank()) {
                    showToast(resources.getString(R.string.error_message_select_cardview_header_text_size))
                } else if (Utils.dynamicUIModel?.chat?.cardView?.cardViewFooterButtonTextSize.isNullOrBlank()) {
                    showToast(resources.getString(R.string.error_message_select_cardview_footer_button_text_size))
                } else {
                    if (Utils.dynamicUIModel?.chat?.cardView?.cardViewBorderColor.isNullOrBlank()) {
                        Utils.dynamicUIModel?.chat?.cardView?.cardViewBorderColor =
                            String.format(
                                "#%08x",
                                Utils.getColorFromView(binding.borderColorLayout.displayColorView)
                            )
                    }

                    if (Utils.dynamicUIModel?.chat?.cardView?.cardViewHeaderBgColor.isNullOrBlank()) {
                        Utils.dynamicUIModel?.chat?.cardView?.cardViewHeaderBgColor =
                            String.format(
                                "#%08x",
                                Utils.getColorFromView(binding.headerBgColorLayout.displayColorView)
                            )
                    }

                    if (Utils.dynamicUIModel?.chat?.cardView?.cardViewHeaderTextColor.isNullOrBlank()) {
                        Utils.dynamicUIModel?.chat?.cardView?.cardViewHeaderTextColor =
                            String.format(
                                "#%08x",
                                Utils.getColorFromView(binding.headerTextColorLayout.displayColorView)
                            )
                    }

                    if (Utils.dynamicUIModel?.chat?.cardView?.cardViewFooterButtonBgColor.isNullOrBlank()) {
                        Utils.dynamicUIModel?.chat?.cardView?.cardViewFooterButtonBgColor =
                            String.format(
                                "#%08x",
                                Utils.getColorFromView(binding.footerBgColorLayout.displayColorView)
                            )
                    }

                    if (Utils.dynamicUIModel?.chat?.cardView?.cardViewFooterButtonTextColor.isNullOrBlank()) {
                        Utils.dynamicUIModel?.chat?.cardView?.cardViewFooterButtonTextColor =
                            String.format(
                                "#%08x",
                                Utils.getColorFromView(binding.footerTextColorLayout.displayColorView)
                            )
                    }

                    val intent = Intent(this, ChatActivity::class.java)
                    intent.putExtra(AppConstants.FROM_CHAT_CARDVIEW_CONFIG, true)
                    startActivity(intent)
                }
            }
        }
    }

    /**
     * This method is called to open color picker dialog
     */
    private fun callColorPickerDialog(viewColor: Int, linearLayout: LinearLayout) {
        val colorPickerDialog =
            AmbilWarnaDialog(
                this,
                viewColor,
                true,
                object : AmbilWarnaDialog.OnAmbilWarnaListener {
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
            binding.borderColorLayout.llSelectColor -> {
                Utils.changeBg(
                    binding.borderColorLayout.displayColorView,
                    selectedColor
                )
                Utils.dynamicUIModel?.chat?.cardView?.cardViewBorderColor = selectedColor
            }

            binding.headerBgColorLayout.llSelectColor -> {
                Utils.changeBg(
                    binding.headerBgColorLayout.displayColorView,
                    selectedColor
                )
                Utils.dynamicUIModel?.chat?.cardView?.cardViewHeaderBgColor = selectedColor
            }

            binding.headerTextColorLayout.llSelectColor -> {
                Utils.changeBg(
                    binding.headerTextColorLayout.displayColorView,
                    selectedColor
                )
                Utils.dynamicUIModel?.chat?.cardView?.cardViewHeaderTextColor = selectedColor
            }

            binding.footerBgColorLayout.llSelectColor -> {
                Utils.changeBg(
                    binding.footerBgColorLayout.displayColorView,
                    selectedColor
                )
                Utils.dynamicUIModel?.chat?.cardView?.cardViewFooterButtonBgColor =
                    selectedColor
            }

            binding.footerTextColorLayout.llSelectColor -> {
                Utils.changeBg(
                    binding.footerTextColorLayout.displayColorView,
                    selectedColor
                )
                Utils.dynamicUIModel?.chat?.cardView?.cardViewFooterButtonTextColor =
                    selectedColor
            }
        }
    }

    /**
     * This method is used when user select item in spinner
     */
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when (parent) {
            binding.spinnerBorderSize -> {
                if (position != 0) {
                    Utils.dynamicUIModel?.chat?.cardView?.cardViewBorderSize =
                        parent.getItemAtPosition(position).toString()
                } else {
                    Utils.dynamicUIModel?.chat?.cardView?.cardViewBorderSize = ""
                }
            }

            binding.spinnerHeaderTextSize -> {
                if (position != 0) {
                    Utils.dynamicUIModel?.chat?.cardView?.cardViewHeaderTextSize =
                        parent.getItemAtPosition(position).toString()
                } else {
                    Utils.dynamicUIModel?.chat?.cardView?.cardViewHeaderTextSize = ""
                }
            }

            binding.spinnerFooterTextSize -> {
                if (position != 0) {
                    Utils.dynamicUIModel?.chat?.cardView?.cardViewFooterButtonTextSize =
                        parent.getItemAtPosition(position).toString()
                } else {
                    Utils.dynamicUIModel?.chat?.cardView?.cardViewFooterButtonTextSize = ""
                }
            }
        }
    }

    /**
     * This method is called when user nothing select anything in spinner
     */
    override fun onNothingSelected(p0: AdapterView<*>?) {}
}