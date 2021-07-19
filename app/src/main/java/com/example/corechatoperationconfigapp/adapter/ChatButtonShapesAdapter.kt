package com.example.corechatoperationconfigapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.corechatoperationconfigapp.R
import com.example.corechatoperationconfigapp.activity.ChatButtonConfigActivity
import com.example.corechatoperationconfigapp.databinding.ItemChatButtonShapesAdapterBinding
import com.example.corechatoperationconfigapp.utils.AppConstants
import com.example.corechatoperationconfigapp.utils.Extensions.invisible
import com.example.corechatoperationconfigapp.utils.Extensions.visible
import com.example.corechatoperationconfigapp.utils.Utils
import com.example.corechatoperationconfigapp.utils.Utils.getDesiredDrawableFromXML
import com.example.corechatoperationconfigapp.utils.Utils.getStringFromXML
import com.google.android.material.shape.CornerFamily

class ChatButtonShapesAdapter(val context: Context) :
    RecyclerView.Adapter<ChatButtonShapesViewHolder>() {
    private val buttonShapesList = arrayOf(
        AppConstants.BUTTON_SQUARE,
        AppConstants.BUTTON_CORNER_RADIUS,
        AppConstants.BUTTON_ROUND,
        AppConstants.BUTTON_TOP_LEFT_CUT,
        AppConstants.BUTTON_BOTTOM_LEFT_CUT,
        AppConstants.BUTTON_TOP_RIGHT_BOTTOM_LEFT_RADIUS,
        AppConstants.BUTTON_ARROW_RIGHT_INSIDE,
        AppConstants.BUTTON_ARROW_BOTH,
        AppConstants.BUTTON_ARROW_LEFT_INSIDE,
        AppConstants.BUTTON_LEFT_ROUND,
        AppConstants.BUTTON_RIGHT_ROUND

    )
    private var selectedPosition = -1

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ChatButtonShapesViewHolder {
        val binding = ItemChatButtonShapesAdapterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ChatButtonShapesViewHolder(binding)
    }

    override fun getItemCount(): Int = buttonShapesList.size
    override fun getItemId(position: Int): Long = position.toLong()
    override fun getItemViewType(position: Int): Int = position

    override fun onBindViewHolder(holder: ChatButtonShapesViewHolder, position: Int) {
        when (buttonShapesList[position]) {
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

        (getStringFromXML(
            context as ChatButtonConfigActivity,
            R.string.button
        ) + " " + (position + 1))
            .also { holder.binding.customMaterialButton.text = it }

        if (selectedPosition != -1 && selectedPosition == position) {
            holder.binding.rlButtonParent.background =
                getDesiredDrawableFromXML(
                    context,
                    R.drawable.shadow_img
                )
            holder.binding.checkImg.visible()
            Utils.dynamicUIModel?.chat?.button?.buttonShapeId = buttonShapesList[position]
        } else {
            holder.binding.rlButtonParent.background = null
            holder.binding.checkImg.invisible()
        }

        holder.binding.customMaterialButton.setOnClickListener {
            selectedPosition = position
            notifyDataSetChanged()
        }
    }

    fun setAndGetSelectedChatButton(selectedChatButtonShape: Int): Int {
        for (i in buttonShapesList.indices){
            if (buttonShapesList[i] == selectedChatButtonShape){
                selectedPosition = i
                break
            }
        }
        notifyDataSetChanged()
        return selectedPosition
    }
}

class ChatButtonShapesViewHolder(val binding: ItemChatButtonShapesAdapterBinding) :
    RecyclerView.ViewHolder(binding.root)