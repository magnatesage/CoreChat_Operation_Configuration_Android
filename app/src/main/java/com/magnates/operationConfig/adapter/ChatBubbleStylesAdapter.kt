package com.magnates.operationConfig.adapter

import android.app.Activity
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.recyclerview.widget.RecyclerView
import com.magnates.operationConfig.R
import com.magnates.operationConfig.customviews.CustomMaterialCardView
import com.magnates.operationConfig.customviews.CustomTextView
import com.magnates.operationConfig.databinding.ItemChatBubbleStylesAdapterBinding
import com.magnates.operationConfig.utils.AppConstants
import com.magnates.operationConfig.utils.Extensions.invisible
import com.magnates.operationConfig.utils.Extensions.visible
import com.magnates.operationConfig.utils.Utils
import com.magnates.operationConfig.utils.Utils.getLayoutFromInflater
import com.magnates.operationConfig.utils.Utils.getSizeInSDP
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

        val chatBubbleSenderShape = when (chatBubbleShapeList[holder.adapterPosition]) {
            AppConstants.CHAT_BUBBLE_SLOPE -> R.layout.item_chat_bubble_image
            AppConstants.CHAT_BUBBLE_TALKIE_TOP -> R.layout.item_chat_bubble_image
            AppConstants.CHAT_BUBBLE_TALKIE_BOTTOM -> R.layout.item_chat_bubble_image
            AppConstants.CHAT_BUBBLE_WITH_DOTS -> R.layout.sender_chatbubble_shape_circle_tail
            else -> R.layout.item_chat_bubble_cardview
        }

        val chatBubbleReceiverShape = when (chatBubbleShapeList[holder.adapterPosition]) {
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
            when (chatBubbleShapeList[holder.adapterPosition]) {
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
            when (chatBubbleShapeList[holder.adapterPosition]) {
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

        if (selectedPosition != -1 && selectedPosition == holder.adapterPosition) {
            holder.binding.cvMain.setAllCorners(getSizeInSDP(context, R.dimen._8sdp).toFloat())
            holder.binding.cvMain.strokeColor = context.getColor(R.color.lightGray)
            holder.binding.cvMain.strokeWidth = getSizeInSDP(context, R.dimen._1sdp)
            holder.binding.cvChatBubbleStyle.cardElevation =
                getSizeInSDP(context, R.dimen._5sdp).toFloat()
            holder.binding.checkImg.visible()
            Utils.dynamicUIModel?.chat?.chatBubble?.chatBubbleStyle = chatBubbleShapeList[holder.adapterPosition]
        } else {
            holder.binding.cvChatBubbleStyle.cardElevation = 0F
            holder.binding.cvMain.setAllCorners(0F)
            holder.binding.cvMain.strokeWidth = 0
            holder.binding.checkImg.invisible()
        }

        holder.binding.cvChatBubbleStyle.setOnClickListener {
            selectedPosition = holder.adapterPosition
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