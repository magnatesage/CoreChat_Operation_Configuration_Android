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
import android.widget.AdapterView
import android.widget.LinearLayout
import android.widget.RadioGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.corechatoperationconfigapp.R
import com.example.corechatoperationconfigapp.adapter.ChatButtonShapesAdapter
import com.example.corechatoperationconfigapp.customviews.colorpicker.AmbilWarnaDialog
import com.example.corechatoperationconfigapp.databinding.ActivityChatButtonConfigBinding
import com.example.corechatoperationconfigapp.utils.AppConstants
import com.example.corechatoperationconfigapp.utils.AppConstants.HORIZONTAL
import com.example.corechatoperationconfigapp.utils.AppConstants.VERTICAL
import com.example.corechatoperationconfigapp.utils.Extensions.showToast
import com.example.corechatoperationconfigapp.utils.Utils

class ChatButtonConfigActivity : BaseActivity(), AdapterView.OnItemSelectedListener {
    private lateinit var binding: ActivityChatButtonConfigBinding
    private lateinit var chatButtonShapesAdapter: ChatButtonShapesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatButtonConfigBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    /**
     * This method is used to initialization process of activity
     */
    override fun init() {
        binding.headerLayout.tvHeader.text = getString(R.string.chat_button_configuration)

        binding.spinnerNormalBorderSize.onItemSelectedListener = this
        binding.spinnerSelectedBorderSize.onItemSelectedListener = this

        binding.rvButtonShapes.apply {
            layoutManager = GridLayoutManager(context, 3)
        }

        chatButtonShapesAdapter = ChatButtonShapesAdapter(this)
        binding.rvButtonShapes.adapter = chatButtonShapesAdapter

        binding.swDropShadow.setOnCheckedChangeListener { _, isChecked ->
            Utils.dynamicUIModel?.chat?.button?.buttonBgDropShadow = isChecked
        }

        binding.rgHorizontalVerticalScroll.setOnCheckedChangeListener { radioGroup: RadioGroup, checkedId: Int ->
            when (checkedId) {
                binding.rbHorizontalScroll.id -> {
                    Utils.dynamicUIModel?.chat?.button?.buttonPlacementStyle = HORIZONTAL
                }

                binding.rbVerticalScroll.id -> {
                    Utils.dynamicUIModel?.chat?.button?.buttonPlacementStyle = VERTICAL
                }
            }
        }

        binding.root.post {
            setValuesToViews()
        }
    }

    /**
     * This method is used to set the values to views
     */
    private fun setValuesToViews() {
        if (!Utils.dynamicUIModel?.chat?.button?.normalBorderSize.isNullOrBlank()) {
            Utils.dynamicUIModel?.chat?.button?.normalBorderSize?.let {
                Utils.getIndex(
                    Utils.getStringArrayFromXML(this, R.array.border_size_array),
                    it
                )
            }?.let { binding.spinnerNormalBorderSize.setSelection(it) }
        }

        if (!Utils.dynamicUIModel?.chat?.button?.clickedBorderSize.isNullOrBlank()) {
            Utils.dynamicUIModel?.chat?.button?.clickedBorderSize?.let {
                Utils.getIndex(
                    Utils.getStringArrayFromXML(this, R.array.border_size_array),
                    it
                )
            }?.let { binding.spinnerSelectedBorderSize.setSelection(it) }
        }

        if (!Utils.dynamicUIModel?.chat?.button?.normalButtonColor.isNullOrBlank()) {
            Utils.changeBg(
                binding.normalButtonColorLayout.displayColorView,
                Utils.dynamicUIModel?.chat?.button?.normalButtonColor!!
            )
        }

        if (!Utils.dynamicUIModel?.chat?.button?.normalTextColor.isNullOrBlank()) {
            Utils.changeBg(
                binding.normalTextColorLayout.displayColorView,
                Utils.dynamicUIModel?.chat?.button?.normalTextColor!!
            )
        }

        if (!Utils.dynamicUIModel?.chat?.button?.normalBorderColor.isNullOrBlank()) {
            Utils.changeBg(
                binding.normalBorderColorLayout.displayColorView,
                Utils.dynamicUIModel?.chat?.button?.normalBorderColor!!
            )
        }

        if (!Utils.dynamicUIModel?.chat?.button?.clickedButtonColor.isNullOrBlank()) {
            Utils.changeBg(
                binding.selectedButtonColorLayout.displayColorView,
                Utils.dynamicUIModel?.chat?.button?.clickedButtonColor!!
            )
        }

        if (!Utils.dynamicUIModel?.chat?.button?.clickedTextColor.isNullOrBlank()) {
            Utils.changeBg(
                binding.selectedTextColorLayout.displayColorView,
                Utils.dynamicUIModel?.chat?.button?.clickedTextColor!!
            )
        }

        if (!Utils.dynamicUIModel?.chat?.button?.clickedBorderColor.isNullOrBlank()) {
            Utils.changeBg(
                binding.selectedBorderColorLayout.displayColorView,
                Utils.dynamicUIModel?.chat?.button?.clickedBorderColor!!
            )
        }

        if (Utils.dynamicUIModel?.chat?.button?.buttonBgDropShadow != null) {
            binding.swDropShadow.isChecked =
                Utils.dynamicUIModel?.chat?.button?.buttonBgDropShadow!!
        }

        if (Utils.dynamicUIModel?.chat?.button?.buttonShapeId != null &&
            Utils.dynamicUIModel?.chat?.button?.buttonShapeId != 0
        ) {
            binding.rvButtonShapes.smoothScrollToPosition(
                chatButtonShapesAdapter.setAndGetSelectedChatButton(Utils.dynamicUIModel?.chat?.button?.buttonShapeId!!)
            )
        }

        if (HORIZONTAL == Utils.dynamicUIModel?.chat?.button?.buttonPlacementStyle) {
            binding.rbHorizontalScroll.isChecked = true
        } else if (VERTICAL == Utils.dynamicUIModel?.chat?.button?.buttonPlacementStyle) {
            binding.rbVerticalScroll.isChecked = true
        }
    }

    /**
     * This method is called when user clicks on view
     */
    override fun onClick(view: View) {
        when (view) {
            binding.normalButtonColorLayout.llSelectColor -> {
                callColorPickerDialog(
                    Utils.getColorFromView(binding.normalButtonColorLayout.displayColorView),
                    binding.normalButtonColorLayout.llSelectColor
                )
            }

            binding.normalTextColorLayout.llSelectColor -> {
                callColorPickerDialog(
                    Utils.getColorFromView(binding.normalTextColorLayout.displayColorView),
                    binding.normalTextColorLayout.llSelectColor
                )
            }

            binding.normalBorderColorLayout.llSelectColor -> {
                callColorPickerDialog(
                    Utils.getColorFromView(binding.normalBorderColorLayout.displayColorView),
                    binding.normalBorderColorLayout.llSelectColor
                )
            }

            binding.selectedButtonColorLayout.llSelectColor -> {
                callColorPickerDialog(
                    Utils.getColorFromView(binding.selectedButtonColorLayout.displayColorView),
                    binding.selectedButtonColorLayout.llSelectColor
                )
            }

            binding.selectedTextColorLayout.llSelectColor -> {
                callColorPickerDialog(
                    Utils.getColorFromView(binding.selectedTextColorLayout.displayColorView),
                    binding.selectedTextColorLayout.llSelectColor
                )
            }

            binding.selectedBorderColorLayout.llSelectColor -> {
                callColorPickerDialog(
                    Utils.getColorFromView(binding.selectedBorderColorLayout.displayColorView),
                    binding.selectedBorderColorLayout.llSelectColor
                )
            }

            binding.btnBack -> onBackPressed()

            binding.btnPreview -> {
                if (Utils.dynamicUIModel?.chat?.button?.buttonShapeId == null ||
                    Utils.dynamicUIModel?.chat?.button?.buttonShapeId == 0
                ) {
                    showToast(resources.getString(R.string.error_message_select_button_background_shape))
                } else if (Utils.dynamicUIModel?.chat?.button?.normalBorderSize.isNullOrBlank()) {
                    showToast(resources.getString(R.string.error_message_select_normal_border_size))
                } else if (Utils.dynamicUIModel?.chat?.button?.clickedBorderSize.isNullOrBlank()) {
                    showToast(resources.getString(R.string.error_message_select_selected_border_size))
                } else if (Utils.dynamicUIModel?.chat?.button?.buttonPlacementStyle.isNullOrBlank()) {
                    showToast(resources.getString(R.string.error_message_select_button_placement_style))
                } else {
                    if (Utils.dynamicUIModel?.chat?.button?.normalButtonColor.isNullOrBlank()) {
                        Utils.dynamicUIModel?.chat?.button?.normalButtonColor = String.format(
                            "#%08x",
                            Utils.getColorFromView(binding.normalButtonColorLayout.displayColorView)
                        )
                    }

                    if (Utils.dynamicUIModel?.chat?.button?.normalTextColor.isNullOrBlank()) {
                        Utils.dynamicUIModel?.chat?.button?.normalTextColor = String.format(
                            "#%08x",
                            Utils.getColorFromView(binding.normalTextColorLayout.displayColorView)
                        )
                    }

                    if (Utils.dynamicUIModel?.chat?.button?.normalBorderColor.isNullOrBlank()) {
                        Utils.dynamicUIModel?.chat?.button?.normalBorderColor = String.format(
                            "#%08x",
                            Utils.getColorFromView(binding.normalBorderColorLayout.displayColorView)
                        )
                    }

                    if (Utils.dynamicUIModel?.chat?.button?.clickedButtonColor.isNullOrBlank()) {
                        Utils.dynamicUIModel?.chat?.button?.clickedButtonColor = String.format(
                            "#%08x",
                            Utils.getColorFromView(binding.selectedButtonColorLayout.displayColorView)
                        )
                    }

                    if (Utils.dynamicUIModel?.chat?.button?.clickedTextColor.isNullOrBlank()) {
                        Utils.dynamicUIModel?.chat?.button?.clickedTextColor = String.format(
                            "#%08x",
                            Utils.getColorFromView(binding.selectedTextColorLayout.displayColorView)
                        )
                    }

                    if (Utils.dynamicUIModel?.chat?.button?.clickedBorderColor.isNullOrBlank()) {
                        Utils.dynamicUIModel?.chat?.button?.clickedBorderColor = String.format(
                            "#%08x",
                            Utils.getColorFromView(binding.selectedBorderColorLayout.displayColorView)
                        )
                    }

                    val intent = Intent(this, ChatActivity::class.java)
                    intent.putExtra(AppConstants.FROM_CHAT_BUTTON_CONFIG, true)
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
            binding.normalButtonColorLayout.llSelectColor -> {
                Utils.changeBg(
                    binding.normalButtonColorLayout.displayColorView,
                    selectedColor
                )
                Utils.dynamicUIModel?.chat?.button?.normalButtonColor = selectedColor
            }

            binding.normalTextColorLayout.llSelectColor -> {
                Utils.changeBg(
                    binding.normalTextColorLayout.displayColorView,
                    selectedColor
                )
                Utils.dynamicUIModel?.chat?.button?.normalTextColor = selectedColor
            }

            binding.normalBorderColorLayout.llSelectColor -> {
                Utils.changeBg(
                    binding.normalBorderColorLayout.displayColorView,
                    selectedColor
                )
                Utils.dynamicUIModel?.chat?.button?.normalBorderColor = selectedColor
            }

            binding.selectedButtonColorLayout.llSelectColor -> {
                Utils.changeBg(
                    binding.selectedButtonColorLayout.displayColorView,
                    selectedColor
                )
                Utils.dynamicUIModel?.chat?.button?.clickedButtonColor = selectedColor
            }

            binding.selectedTextColorLayout.llSelectColor -> {
                Utils.changeBg(
                    binding.selectedTextColorLayout.displayColorView,
                    selectedColor
                )
                Utils.dynamicUIModel?.chat?.button?.clickedTextColor = selectedColor
            }

            binding.selectedBorderColorLayout.llSelectColor -> {
                Utils.changeBg(
                    binding.selectedBorderColorLayout.displayColorView,
                    selectedColor
                )
                Utils.dynamicUIModel?.chat?.button?.clickedBorderColor = selectedColor
            }
        }
    }

    /**
     * This method is used when user select item in spinner
     */
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when (parent) {
            binding.spinnerNormalBorderSize -> {
                if (position != 0) {
                    Utils.dynamicUIModel?.chat?.button?.normalBorderSize =
                        parent.getItemAtPosition(position).toString()
                } else {
                    Utils.dynamicUIModel?.chat?.button?.normalBorderSize = ""
                }
            }

            binding.spinnerSelectedBorderSize -> {
                if (position != 0) {
                    Utils.dynamicUIModel?.chat?.button?.clickedBorderSize =
                        parent.getItemAtPosition(position).toString()
                } else {
                    Utils.dynamicUIModel?.chat?.button?.clickedBorderSize = ""
                }
            }
        }
    }

    /**
     * This method is called when user nothing select anything in spinner
     */
    override fun onNothingSelected(p0: AdapterView<*>?) {}
}