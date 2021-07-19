package com.example.corechatoperationconfigapp.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.corechatoperationconfigapp.R
import com.example.corechatoperationconfigapp.customviews.CustomMaterialButton
import com.example.corechatoperationconfigapp.databinding.ItemChatButtonBinding
import com.example.corechatoperationconfigapp.utils.AppConstants
import com.example.corechatoperationconfigapp.utils.Utils
import com.example.corechatoperationconfigapp.utils.Utils.changeBg
import com.example.corechatoperationconfigapp.utils.Utils.changeTextColor
import com.example.corechatoperationconfigapp.utils.Utils.getParsedColorValue
import com.example.corechatoperationconfigapp.utils.Utils.getStringFromXML
import com.example.corechatoperationconfigapp.utils.Utils.setElevationShadow
import com.example.corechatoperationconfigapp.utils.Utils.setStrokeColorAndWidth
import com.google.android.material.shape.CornerFamily

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