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

package com.magnates.operationConfig.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.shape.CornerFamily
import com.magnates.operationConfig.R
import com.magnates.operationConfig.customviews.CustomMaterialButton
import com.magnates.operationConfig.databinding.ItemChatButtonBinding
import com.magnates.operationConfig.utils.AppConstants
import com.magnates.operationConfig.utils.Utils
import com.magnates.operationConfig.utils.Utils.changeBg
import com.magnates.operationConfig.utils.Utils.changeTextColor
import com.magnates.operationConfig.utils.Utils.getParsedColorValue
import com.magnates.operationConfig.utils.Utils.getStringFromXML
import com.magnates.operationConfig.utils.Utils.setElevationShadow
import com.magnates.operationConfig.utils.Utils.setStrokeColorAndWidth

class ChatButtonListAdapter(
    val context: Activity
) : RecyclerView.Adapter<ChatButtonListViewHolder>() {
    private var selectedPosition = -1

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ChatButtonListViewHolder {
        val binding = ItemChatButtonBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ChatButtonListViewHolder(binding)
    }

    override fun getItemCount(): Int = 5

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onBindViewHolder(holder: ChatButtonListViewHolder, position: Int) {

        holder.binding.customMaterialButton.text = getStringFromXML(context, R.string.button)

        if (Utils.dynamicUIModel?.chat?.button?.buttonPlacementStyle == AppConstants.HORIZONTAL) {
            val params = holder.binding.rlParent.layoutParams
            params.width = ViewGroup.LayoutParams.WRAP_CONTENT
            params.height = ViewGroup.LayoutParams.WRAP_CONTENT
            holder.binding.rlParent.layoutParams = params
        }

        holder.binding.customMaterialButton.setCustomFont("${Utils.dynamicUIModel?.fontFamily}.ttf")
        Utils.setTextSizeInSSP(holder.binding.customMaterialButton, Utils.getFontSizeInSSP(Utils.dynamicUIModel?.fontSize?.common!!))

        setElevationShadow(
            context,
            holder.binding.customMaterialButton,
            Utils.dynamicUIModel?.chat?.button?.buttonBgDropShadow!!
        )

        if (selectedPosition != -1 && selectedPosition == position){
            setButtonClicked(holder.binding.customMaterialButton)
        } else {
            setButtonNormal(holder.binding.customMaterialButton)
        }

        holder.binding.customMaterialButton.setOnClickListener {
            selectedPosition = position
            notifyDataSetChanged()
        }

        when (Utils.dynamicUIModel?.chat?.button?.buttonShapeId) {
            AppConstants.BUTTON_CORNER_RADIUS -> {
                holder.binding.customMaterialButton.setCornerFamily(CornerFamily.ROUNDED)
                holder.binding.customMaterialButton.setAllCornersInPercent(0.25F)
            }
            AppConstants.BUTTON_ROUND -> {
                holder.binding.customMaterialButton.setCornerFamily(CornerFamily.ROUNDED)
                holder.binding.customMaterialButton.setAllCornersInPercent(0.5F)
            }
            AppConstants.BUTTON_TOP_LEFT_CUT -> {
                holder.binding.customMaterialButton.setCornerFamily(CornerFamily.CUT)
                holder.binding.customMaterialButton.setTopLeftCornerInPercent(0.25F)
            }
            AppConstants.BUTTON_BOTTOM_LEFT_CUT -> {
                holder.binding.customMaterialButton.setCornerFamily(CornerFamily.CUT)
                holder.binding.customMaterialButton.setBottomLeftCornerInPercent(0.25F)
            }
            AppConstants.BUTTON_TOP_RIGHT_BOTTOM_LEFT_RADIUS -> {
                holder.binding.customMaterialButton.setCornerFamily(CornerFamily.ROUNDED)
                holder.binding.customMaterialButton.setTopRightCornerInPercent(0.25F)
                holder.binding.customMaterialButton.setBottomLeftCornerInPercent(0.25F)
            }
            AppConstants.BUTTON_ARROW_RIGHT_INSIDE -> {
                holder.binding.customMaterialButton.setCornerFamily(CornerFamily.CUT)
                holder.binding.customMaterialButton.setTopRightCornerInPercent(0.5F)
                holder.binding.customMaterialButton.setBottomRightCornerInPercent(0.5F)
            }
            AppConstants.BUTTON_ARROW_BOTH -> {
                holder.binding.customMaterialButton.setCornerFamily(CornerFamily.CUT)
                holder.binding.customMaterialButton.setAllCornersInPercent(0.5F)
            }
            AppConstants.BUTTON_ARROW_LEFT_INSIDE -> {
                holder.binding.customMaterialButton.setCornerFamily(CornerFamily.CUT)
                holder.binding.customMaterialButton.setTopLeftCornerInPercent(0.5F)
                holder.binding.customMaterialButton.setBottomLeftCornerInPercent(0.5F)
            }
            AppConstants.BUTTON_LEFT_ROUND -> {
                holder.binding.customMaterialButton.setCornerFamily(CornerFamily.ROUNDED)
                holder.binding.customMaterialButton.setTopLeftCornerInPercent(0.5F)
                holder.binding.customMaterialButton.setBottomLeftCornerInPercent(0.5F)
            }
            AppConstants.BUTTON_RIGHT_ROUND -> {
                holder.binding.customMaterialButton.setCornerFamily(CornerFamily.ROUNDED)
                holder.binding.customMaterialButton.setTopRightCornerInPercent(0.5F)
                holder.binding.customMaterialButton.setBottomRightCornerInPercent(0.5F)
            }
        }
    }

    private fun setButtonNormal(customMaterialButton: CustomMaterialButton) {
        val normalButtonColor = getParsedColorValue(Utils.dynamicUIModel?.chat?.button?.normalButtonColor!!)
        val normalTextColor = getParsedColorValue(Utils.dynamicUIModel?.chat?.button?.normalTextColor!!)
        val normalBorderColor = getParsedColorValue(Utils.dynamicUIModel?.chat?.button?.normalBorderColor!!)
        val normalBorderSize = Utils.dynamicUIModel?.chat?.button?.normalBorderSize?.toInt()
        makeChangesToButton(
            customMaterialButton, normalButtonColor, normalTextColor,
            normalBorderColor, normalBorderSize!!
        )
    }

    private fun makeChangesToButton(
        buttonShapeLayout: CustomMaterialButton, buttonColor: Int,
        textColor: Int, borderColor: Int, borderSize: Int
    ) {
        changeBg(buttonShapeLayout, buttonColor)
        changeTextColor(buttonShapeLayout, textColor)
        setStrokeColorAndWidth(buttonShapeLayout, borderColor, borderSize)
    }

    private fun setButtonClicked(
        buttonShapeLayout: CustomMaterialButton
    ) {
        val clickedButtonColor = getParsedColorValue(Utils.dynamicUIModel?.chat?.button?.clickedButtonColor!!)
        val clickedTextColor = getParsedColorValue(Utils.dynamicUIModel?.chat?.button?.clickedTextColor!!)
        val clickedBorderColor = getParsedColorValue(Utils.dynamicUIModel?.chat?.button?.clickedBorderColor!!)
        val clickedBorderSize = Utils.dynamicUIModel?.chat?.button?.clickedBorderSize?.toInt()
        makeChangesToButton(
            buttonShapeLayout, clickedButtonColor, clickedTextColor,
            clickedBorderColor, clickedBorderSize!!
        )
    }
}

class ChatButtonListViewHolder(val binding: ItemChatButtonBinding) :
    RecyclerView.ViewHolder(binding.root)