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

package com.example.corechatoperationconfigapp.adapter

import android.app.Activity
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.corechatoperationconfigapp.R
import com.example.corechatoperationconfigapp.customviews.CustomMaterialCardView
import com.example.corechatoperationconfigapp.customviews.CustomTextView
import com.example.corechatoperationconfigapp.databinding.ItemChatBubbleStylesAdapterBinding
import com.example.corechatoperationconfigapp.utils.AppConstants
import com.example.corechatoperationconfigapp.utils.Extensions.invisible
import com.example.corechatoperationconfigapp.utils.Extensions.visible
import com.example.corechatoperationconfigapp.utils.Utils
import com.example.corechatoperationconfigapp.utils.Utils.getLayoutFromInflater
import com.example.corechatoperationconfigapp.utils.Utils.getSizeInSDP
import com.google.android.material.shape.CornerFamily

class ChatBubbleStylesAdapter(
    val context: Activity
) :
    RecyclerView.Adapter<ChatBubbleStylesViewHolder>() {
    private var selectedPosition = -1
    private val chatBubbleShapeList = arrayOf(
        AppConstants.CHAT_BUBBLE_SQUARE,
        AppConstants.CHAT_BUBBLE_CORNER_RADIUS,
        AppConstants.CHAT_BUBBLE_CORNER_CUT_UPPER,
        AppConstants.CHAT_BUBBLE_CORNER_CUT_LOWER,
        AppConstants.CHAT_BUBBLE_ARROW_OUTSIDE,
        AppConstants.CHAT_BUBBLE_ARROW_INSIDE,
        AppConstants.CHAT_BUBBLE_ARROW_BOTH,
        AppConstants.CHAT_BUBBLE_TWO_CORNER_RADIUS,
        AppConstants.CHAT_BUBBLE_ROUND,
        AppConstants.CHAT_BUBBLE_LEFT_OR_RIGHT_ROUND,
        AppConstants.CHAT_BUBBLE_SLOPE,
        AppConstants.CHAT_BUBBLE_TALKIE_TOP,
        AppConstants.CHAT_BUBBLE_TALKIE_BOTTOM,
        AppConstants.CHAT_BUBBLE_WITH_DOTS
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatBubbleStylesViewHolder {
        val binding = ItemChatBubbleStylesAdapterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ChatBubbleStylesViewHolder(binding)
    }

    override fun getItemCount(): Int = chatBubbleShapeList.size
    override fun getItemId(position: Int): Long = position.toLong()
    override fun getItemViewType(position: Int): Int = position

    override fun onBindViewHolder(holder: ChatBubbleStylesViewHolder, position: Int) {
        holder.binding.llParent.removeAllViews()

        lateinit var senderLayout: RelativeLayout
        lateinit var receiverLayout: RelativeLayout

        val chatBubbleSenderShape = when (chatBubbleShapeList[position]) {
            AppConstants.CHAT_BUBBLE_SLOPE -> R.layout.item_chat_bubble_image
            AppConstants.CHAT_BUBBLE_TALKIE_TOP -> R.layout.item_chat_bubble_image
            AppConstants.CHAT_BUBBLE_TALKIE_BOTTOM -> R.layout.item_chat_bubble_image
            AppConstants.CHAT_BUBBLE_WITH_DOTS -> R.layout.sender_chatbubble_shape_circle_tail
            else -> R.layout.item_chat_bubble_cardview
        }

        val chatBubbleReceiverShape = when (chatBubbleShapeList[position]) {
            AppConstants.CHAT_BUBBLE_SLOPE -> R.layout.item_chat_bubble_image
            AppConstants.CHAT_BUBBLE_TALKIE_TOP -> R.layout.item_chat_bubble_image
            AppConstants.CHAT_BUBBLE_TALKIE_BOTTOM -> R.layout.item_chat_bubble_image
            AppConstants.CHAT_BUBBLE_WITH_DOTS -> R.layout.receiver_chatbubble_shape_circle_tail
            else -> R.layout.item_chat_bubble_cardview
        }

        receiverLayout =
            getLayoutFromInflater(
                context, chatBubbleReceiverShape,
                holder.binding.llParent, RelativeLayout::class.java
            )!!

        senderLayout =
            getLayoutFromInflater(
                context, chatBubbleSenderShape,
                holder.binding.llParent, RelativeLayout::class.java
            )!!

        val cvSenderChatBubble =
            senderLayout.findViewById<CustomMaterialCardView>(R.id.cv_chat_bubble)
        val tvSenderChatBubble = senderLayout.findViewById<CustomTextView>(R.id.tv_chat_bubble)

        val cvReceiverChatBubble =
            receiverLayout.findViewById<CustomMaterialCardView>(R.id.cv_chat_bubble)
        val tvReceiverChatBubble = receiverLayout.findViewById<CustomTextView>(R.id.tv_chat_bubble)

        var llSenderChatBubble: LinearLayout? = null
        var llReceiverChatBubble: LinearLayout? = null
        if (chatBubbleSenderShape == R.layout.item_chat_bubble_image &&
            chatBubbleReceiverShape == R.layout.item_chat_bubble_image
        ) {
            llSenderChatBubble = senderLayout.findViewById(R.id.ll_chat_bubble)
            llReceiverChatBubble = receiverLayout.findViewById(R.id.ll_chat_bubble)
        }

        tvSenderChatBubble.setTextColor(context.getColor(R.color.white))
        tvReceiverChatBubble.setTextColor(context.getColor(R.color.colorPrimary))

        val smallCornerSize = getSizeInSDP(context, R.dimen._8sdp).toFloat()

        Utils.setTextSizeInSSP(tvSenderChatBubble, R.dimen._6ssp)
        Utils.setTextSizeInSSP(tvReceiverChatBubble, R.dimen._6ssp)

        val startEndPadding = getSizeInSDP(context, R.dimen._20sdp)
        val topBottomPadding = getSizeInSDP(context, R.dimen._10sdp)

        tvSenderChatBubble.setPadding(startEndPadding, topBottomPadding, startEndPadding, topBottomPadding)
        tvReceiverChatBubble.setPadding(startEndPadding, topBottomPadding, startEndPadding, topBottomPadding)

        if (chatBubbleSenderShape == R.layout.item_chat_bubble_image) {
            when (chatBubbleShapeList[position]) {
                AppConstants.CHAT_BUBBLE_SLOPE -> {
                    llSenderChatBubble?.setBackgroundResource(R.drawable.chat_bubble_shape_slant)
                    llReceiverChatBubble?.setBackgroundResource(R.drawable.chat_bubble_shape_slant)
                }
                AppConstants.CHAT_BUBBLE_TALKIE_TOP -> {
                    llSenderChatBubble?.setBackgroundResource(R.drawable.chat_bubble_shape_sender_top_tail)
                    llReceiverChatBubble?.setBackgroundResource(R.drawable.chat_bubble_shape_receiver_top_tail)
                }
                AppConstants.CHAT_BUBBLE_TALKIE_BOTTOM -> {
                    llSenderChatBubble?.setBackgroundResource(R.drawable.chat_bubble_shape_sender_bottom_tail)
                    llReceiverChatBubble?.setBackgroundResource(R.drawable.chat_bubble_shape_receiver_bottom_tail)
                }
            }
        } else {
            when (chatBubbleShapeList[position]) {
                AppConstants.CHAT_BUBBLE_CORNER_CUT_UPPER -> {
                    cvSenderChatBubble.setCornerFamily(CornerFamily.CUT)
                    cvSenderChatBubble.setTopLeftCorner(smallCornerSize)
                    cvReceiverChatBubble.setCornerFamily(CornerFamily.CUT)
                    cvReceiverChatBubble.setTopRightCorner(smallCornerSize)
                }
                AppConstants.CHAT_BUBBLE_CORNER_CUT_LOWER -> {
                    cvSenderChatBubble.setCornerFamily(CornerFamily.CUT)
                    cvSenderChatBubble.setBottomLeftCorner(smallCornerSize)
                    cvReceiverChatBubble.setCornerFamily(CornerFamily.CUT)
                    cvReceiverChatBubble.setBottomRightCorner(smallCornerSize)
                }
                AppConstants.CHAT_BUBBLE_CORNER_RADIUS -> {
                    cvSenderChatBubble.setCornerFamily(CornerFamily.ROUNDED)
                    cvSenderChatBubble.setAllCorners(smallCornerSize)
                    cvReceiverChatBubble.setCornerFamily(CornerFamily.ROUNDED)
                    cvReceiverChatBubble.setAllCorners(smallCornerSize)
                }
                AppConstants.CHAT_BUBBLE_TWO_CORNER_RADIUS -> {
                    cvSenderChatBubble.setCornerFamily(CornerFamily.ROUNDED)
                    cvSenderChatBubble.setTopLeftCorner(smallCornerSize)
                    cvSenderChatBubble.setBottomRightCorner(smallCornerSize)
                    cvReceiverChatBubble.setCornerFamily(CornerFamily.ROUNDED)
                    cvReceiverChatBubble.setTopLeftCorner(smallCornerSize)
                    cvReceiverChatBubble.setBottomRightCorner(smallCornerSize)
                }
                AppConstants.CHAT_BUBBLE_ROUND -> {
                    cvSenderChatBubble.setCornerFamily(CornerFamily.ROUNDED)
                    cvSenderChatBubble.setAllCornersInPercent(0.5F)
                    cvReceiverChatBubble.setCornerFamily(CornerFamily.ROUNDED)
                    cvReceiverChatBubble.setAllCornersInPercent(0.5F)
                }
                AppConstants.CHAT_BUBBLE_ARROW_INSIDE -> {
                    cvSenderChatBubble.setCornerFamily(CornerFamily.CUT)
                    cvSenderChatBubble.setTopRightCornerInPercent(0.5F)
                    cvSenderChatBubble.setBottomRightCornerInPercent(0.5F)
                    cvReceiverChatBubble.setCornerFamily(CornerFamily.CUT)
                    cvReceiverChatBubble.setTopLeftCornerInPercent(0.5F)
                    cvReceiverChatBubble.setBottomLeftCornerInPercent(0.5F)
                }
                AppConstants.CHAT_BUBBLE_ARROW_OUTSIDE -> {
                    cvSenderChatBubble.setCornerFamily(CornerFamily.CUT)
                    cvSenderChatBubble.setTopLeftCornerInPercent(0.5F)
                    cvSenderChatBubble.setBottomLeftCornerInPercent(0.5F)
                    cvReceiverChatBubble.setCornerFamily(CornerFamily.CUT)
                    cvReceiverChatBubble.setTopRightCornerInPercent(0.5F)
                    cvReceiverChatBubble.setBottomRightCornerInPercent(0.5F)
                }
                AppConstants.CHAT_BUBBLE_ARROW_BOTH -> {
                    cvSenderChatBubble.setCornerFamily(CornerFamily.CUT)
                    cvSenderChatBubble.setAllCornersInPercent(0.5F)
                    cvReceiverChatBubble.setCornerFamily(CornerFamily.CUT)
                    cvReceiverChatBubble.setAllCornersInPercent(0.5F)
                }
                AppConstants.CHAT_BUBBLE_LEFT_OR_RIGHT_ROUND -> {
                    cvSenderChatBubble.setCornerFamily(CornerFamily.ROUNDED)
                    cvSenderChatBubble.setTopLeftCornerInPercent(0.5F)
                    cvSenderChatBubble.setBottomLeftCornerInPercent(0.5F)
                    cvReceiverChatBubble.setCornerFamily(CornerFamily.ROUNDED)
                    cvReceiverChatBubble.setTopRightCornerInPercent(0.5F)
                    cvReceiverChatBubble.setBottomRightCornerInPercent(0.5F)
                }
            }
        }

        if (chatBubbleSenderShape == R.layout.item_chat_bubble_image &&
            chatBubbleReceiverShape == R.layout.item_chat_bubble_image
        ) {
            llSenderChatBubble?.background?.setTint(context.getColor(R.color.colorPrimary))
            llReceiverChatBubble?.background?.setTint(context.getColor(R.color.colorBackground))
        } else {
            cvSenderChatBubble.setCardBackgroundColor(context.getColor(R.color.colorPrimary))
            cvReceiverChatBubble.setCardBackgroundColor(context.getColor(R.color.colorBackground))
        }

        senderLayout.gravity = Gravity.END

        holder.binding.llParent.addView(receiverLayout)
        holder.binding.llParent.addView(senderLayout)

        if (selectedPosition != -1 && selectedPosition == position) {
            holder.binding.cvMain.setAllCorners(getSizeInSDP(context, R.dimen._8sdp).toFloat())
            holder.binding.cvMain.strokeColor = context.getColor(R.color.lightGray)
            holder.binding.cvMain.strokeWidth = getSizeInSDP(context, R.dimen._1sdp)
            holder.binding.cvChatBubbleStyle.cardElevation =
                getSizeInSDP(context, R.dimen._5sdp).toFloat()
            holder.binding.checkImg.visible()
            Utils.dynamicUIModel?.chat?.chatBubble?.chatBubbleStyle = chatBubbleShapeList[position]
        } else {
            holder.binding.cvChatBubbleStyle.cardElevation = 0F
            holder.binding.cvMain.setAllCorners(0F)
            holder.binding.cvMain.strokeWidth = 0
            holder.binding.checkImg.invisible()
        }

        holder.binding.cvChatBubbleStyle.setOnClickListener {
            selectedPosition = position
            notifyDataSetChanged()
        }
    }

    fun setAndGetSelectedChatBubble(selectedChatBubbleStyle: Int): Int {
        for (i in chatBubbleShapeList.indices){
            if (chatBubbleShapeList[i] == selectedChatBubbleStyle){
                selectedPosition = i
                break
            }
        }
        notifyDataSetChanged()
        return selectedPosition
    }
}

class ChatBubbleStylesViewHolder(val binding: ItemChatBubbleStylesAdapterBinding) :
    RecyclerView.ViewHolder(binding.root)