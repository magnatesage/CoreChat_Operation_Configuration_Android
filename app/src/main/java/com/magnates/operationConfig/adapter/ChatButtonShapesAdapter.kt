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

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.shape.CornerFamily
import com.magnates.operationConfig.R
import com.magnates.operationConfig.activity.ChatButtonConfigActivity
import com.magnates.operationConfig.databinding.ItemChatButtonShapesAdapterBinding
import com.magnates.operationConfig.utils.AppConstants
import com.magnates.operationConfig.utils.Extensions.invisible
import com.magnates.operationConfig.utils.Extensions.visible
import com.magnates.operationConfig.utils.Utils
import com.magnates.operationConfig.utils.Utils.getDesiredDrawableFromXML
import com.magnates.operationConfig.utils.Utils.getStringFromXML

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