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
import android.graphics.Color
import android.view.*
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.shape.CornerFamily
import com.magnates.operationConfig.R
import com.magnates.operationConfig.customviews.CustomMaterialCardView
import com.magnates.operationConfig.customviews.CustomTextView
import com.magnates.operationConfig.databinding.ItemChatListBinding
import com.magnates.operationConfig.model.MessageModel
import com.magnates.operationConfig.utils.AppConstants
import com.magnates.operationConfig.utils.Utils
import com.magnates.operationConfig.utils.Utils.changeBg
import com.magnates.operationConfig.utils.Utils.changeTextColor
import com.magnates.operationConfig.utils.Utils.getLayoutFromInflater
import com.magnates.operationConfig.utils.Utils.getParsedColorValue
import com.magnates.operationConfig.utils.Utils.getSizeInSDP
import com.magnates.operationConfig.utils.Utils.getStringFromXML
import com.magnates.operationConfig.utils.Utils.setCardElevation
import com.magnates.operationConfig.utils.Utils.setElevationShadow
import com.magnates.operationConfig.utils.Utils.setStrokeColorAndWidth
import java.util.*

class ChatListAdapter(
    val context: Activity,
    private val chatList: ArrayList<MessageModel>
) : RecyclerView.Adapter<ChatListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatListViewHolder {
        val binding = ItemChatListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ChatListViewHolder(binding)
    }

    override fun getItemCount(): Int = chatList.size

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onBindViewHolder(holder: ChatListViewHolder, position: Int) {
        if (chatList[position].isCardView) {

            holder.binding.cvParent.visibility = View.VISIBLE
            val messageModel = chatList[position]

            setCardElevation(
                context,
                holder.binding.cvParent,
                Utils.dynamicUIModel?.chat?.cardView?.cardViewBgDropShadow!!
            )
            setStrokeColorAndWidth(
                holder.binding.cvParent,
                getParsedColorValue(Utils.dynamicUIModel?.chat?.cardView?.cardViewBorderColor!!),
                Utils.dynamicUIModel?.chat?.cardView?.cardViewBorderSize?.toInt()!!
            )
            changeBg(
                holder.binding.btnHeader,
                Utils.dynamicUIModel?.chat?.cardView?.cardViewHeaderBgColor!!
            )
            changeBg(
                holder.binding.btnFooter,
                Utils.dynamicUIModel?.chat?.cardView?.cardViewFooterButtonBgColor!!
            )
            changeTextColor(
                holder.binding.btnHeader,
                Utils.dynamicUIModel?.chat?.cardView?.cardViewHeaderTextColor!!
            )
            changeTextColor(
                holder.binding.btnFooter,
                Utils.dynamicUIModel?.chat?.cardView?.cardViewFooterButtonTextColor!!
            )

            holder.binding.btnHeader.text = messageModel.data
            holder.binding.btnFooter.text = getStringFromXML(context, R.string.button)
            Utils.setTextSizeInSSP(holder.binding.btnHeader, Utils.getFontSizeInSSP(Utils.dynamicUIModel?.chat?.cardView?.cardViewHeaderTextSize!!))
            Utils.setTextSizeInSSP(holder.binding.btnFooter, Utils.getFontSizeInSSP(Utils.dynamicUIModel?.chat?.cardView?.cardViewFooterButtonTextSize!!))

            holder.binding.tvContent.gravity = Gravity.CENTER
            holder.binding.btnHeader.isAllCaps = false
            holder.binding.btnFooter.isAllCaps = false

            holder.binding.tvContent.visibility = View.VISIBLE
            holder.binding.tvContent.text = getStringFromXML(context, R.string.lorem_ipsum_paragraph)
            holder.binding.tvContent.setCustomFont("${Utils.dynamicUIModel?.fontFamily}.ttf")

            changeTextColor(holder.binding.tvContent, Color.BLACK)
            changeBg(holder.binding.llContent, Color.WHITE)

            holder.binding.cvParent.viewTreeObserver.addOnPreDrawListener(object :
                ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    holder.binding.cvParent.viewTreeObserver.removeOnPreDrawListener(this)
                    val btnHeaderHeight = holder.binding.btnHeader.height.toFloat()
                    val btnFooterHeight = holder.binding.btnFooter.height.toFloat()
                    when (Utils.dynamicUIModel?.chat?.cardView?.cardViewShapeId) {
                        AppConstants.CARDVIEW_CORNER_RADIUS_SMALL -> {
                            holder.binding.cvParent.setCornerFamily(CornerFamily.ROUNDED)
                            holder.binding.cvParent.setTopLeftCorner(btnHeaderHeight / 2)
                            holder.binding.cvParent.setTopRightCorner(btnHeaderHeight / 2)
                            holder.binding.cvParent.setBottomLeftCorner(btnFooterHeight / 2)
                            holder.binding.cvParent.setBottomRightCorner(btnFooterHeight / 2)

                            holder.binding.btnHeader.setCornerFamily(CornerFamily.ROUNDED)
                            holder.binding.btnHeader.setTopLeftCorner(btnHeaderHeight / 2)
                            holder.binding.btnHeader.setTopRightCorner(btnHeaderHeight / 2)

                            holder.binding.btnFooter.setCornerFamily(CornerFamily.ROUNDED)
                            holder.binding.btnFooter.setBottomLeftCorner(btnFooterHeight / 2)
                            holder.binding.btnFooter.setBottomRightCorner(btnFooterHeight / 2)
                        }
                        AppConstants.CARDVIEW_TOP_LEFT_BOTTOM_RIGHT_RADIUS_SMALL -> {
                            holder.binding.cvParent.setCornerFamily(CornerFamily.ROUNDED)
                            holder.binding.cvParent.setTopLeftCorner(btnHeaderHeight / 2)
                            holder.binding.cvParent.setBottomRightCorner(btnFooterHeight / 2)

                            holder.binding.btnHeader.setCornerFamily(CornerFamily.ROUNDED)
                            holder.binding.btnHeader.setTopLeftCorner(btnHeaderHeight / 2)

                            holder.binding.btnFooter.setCornerFamily(CornerFamily.ROUNDED)
                            holder.binding.btnFooter.setBottomRightCorner(btnFooterHeight / 2)
                        }
                        AppConstants.CARDVIEW_TOP_RIGHT_BOTTOM_LEFT_RADIUS_SMALL -> {
                            holder.binding.cvParent.setCornerFamily(CornerFamily.ROUNDED)
                            holder.binding.cvParent.setTopRightCorner(btnHeaderHeight / 2)
                            holder.binding.cvParent.setBottomLeftCorner(btnHeaderHeight / 2)

                            holder.binding.btnHeader.setCornerFamily(CornerFamily.ROUNDED)
                            holder.binding.btnHeader.setTopRightCorner(btnHeaderHeight / 2)

                            holder.binding.btnFooter.setCornerFamily(CornerFamily.ROUNDED)
                            holder.binding.btnFooter.setBottomLeftCorner(btnFooterHeight / 2)
                        }
                        AppConstants.CARDVIEW_FOUR_CORNER_CUT_SMALL -> {
                            holder.binding.cvParent.setCornerFamily(CornerFamily.CUT)
                            holder.binding.cvParent.setTopLeftCorner(btnHeaderHeight / 2)
                            holder.binding.cvParent.setTopRightCorner(btnHeaderHeight / 2)
                            holder.binding.cvParent.setBottomLeftCorner(btnFooterHeight / 2)
                            holder.binding.cvParent.setBottomRightCorner(btnFooterHeight / 2)

                            holder.binding.btnHeader.setCornerFamily(CornerFamily.CUT)
                            holder.binding.btnHeader.setTopLeftCorner(btnHeaderHeight / 2)
                            holder.binding.btnHeader.setTopRightCorner(btnHeaderHeight / 2)

                            holder.binding.btnFooter.setCornerFamily(CornerFamily.CUT)
                            holder.binding.btnFooter.setBottomLeftCorner(btnFooterHeight / 2)
                            holder.binding.btnFooter.setBottomRightCorner(btnFooterHeight / 2)
                        }
                        AppConstants.CARDVIEW_TOP_LEFT_BOTTOM_RIGHT_CUT_SMALL -> {
                            holder.binding.cvParent.setCornerFamily(CornerFamily.CUT)
                            holder.binding.cvParent.setTopLeftCorner(btnHeaderHeight / 2)
                            holder.binding.cvParent.setBottomRightCorner(btnFooterHeight / 2)

                            holder.binding.btnHeader.setCornerFamily(CornerFamily.CUT)
                            holder.binding.btnHeader.setTopLeftCorner(btnHeaderHeight / 2)

                            holder.binding.btnFooter.setCornerFamily(CornerFamily.CUT)
                            holder.binding.btnFooter.setBottomRightCorner(btnFooterHeight / 2)
                        }
                        AppConstants.CARDVIEW_TOP_RIGHT_BOTTOM_LEFT_CUT_SMALL -> {
                            holder.binding.cvParent.setCornerFamily(CornerFamily.CUT)
                            holder.binding.cvParent.setTopRightCorner(btnHeaderHeight / 2)
                            holder.binding.cvParent.setBottomLeftCorner(btnFooterHeight / 2)

                            holder.binding.btnHeader.setCornerFamily(CornerFamily.CUT)
                            holder.binding.btnHeader.setTopRightCorner(btnHeaderHeight / 2)

                            holder.binding.btnFooter.setCornerFamily(CornerFamily.CUT)
                            holder.binding.btnFooter.setBottomLeftCorner(btnFooterHeight / 2)
                        }
                        AppConstants.CARDVIEW_FOUR_CORNER_CUT_BIG -> {
                            holder.binding.cvParent.setCornerFamily(CornerFamily.CUT)
                            holder.binding.cvParent.setTopLeftCorner(btnHeaderHeight)
                            holder.binding.cvParent.setTopRightCorner(btnHeaderHeight)
                            holder.binding.cvParent.setBottomLeftCorner(btnFooterHeight)
                            holder.binding.cvParent.setBottomRightCorner(btnFooterHeight)

                            holder.binding.btnHeader.setCornerFamily(CornerFamily.CUT)
                            holder.binding.btnHeader.setTopLeftCorner(btnHeaderHeight)
                            holder.binding.btnHeader.setTopRightCorner(btnHeaderHeight)

                            holder.binding.btnFooter.setCornerFamily(CornerFamily.CUT)
                            holder.binding.btnFooter.setBottomLeftCorner(btnFooterHeight)
                            holder.binding.btnFooter.setBottomRightCorner(btnFooterHeight)
                        }
                        AppConstants.CARDVIEW_TOP_LEFT_BOTTOM_RIGHT_CUT_BIG -> {
                            holder.binding.cvParent.setCornerFamily(CornerFamily.CUT)
                            holder.binding.cvParent.setTopLeftCorner(btnHeaderHeight)
                            holder.binding.cvParent.setBottomRightCorner(btnFooterHeight)

                            holder.binding.btnHeader.setCornerFamily(CornerFamily.CUT)
                            holder.binding.btnHeader.setTopLeftCorner(btnHeaderHeight)

                            holder.binding.btnFooter.setCornerFamily(CornerFamily.CUT)
                            holder.binding.btnFooter.setBottomRightCorner(btnFooterHeight)
                        }
                        AppConstants.CARDVIEW_TOP_RIGHT_BOTTOM_LEFT_CUT_BIG -> {
                            holder.binding.cvParent.setCornerFamily(CornerFamily.CUT)
                            holder.binding.cvParent.setTopRightCorner(btnHeaderHeight)
                            holder.binding.cvParent.setBottomLeftCorner(btnFooterHeight)

                            holder.binding.btnHeader.setCornerFamily(CornerFamily.CUT)
                            holder.binding.btnHeader.setTopRightCorner(btnHeaderHeight)

                            holder.binding.btnFooter.setCornerFamily(CornerFamily.CUT)
                            holder.binding.btnFooter.setBottomLeftCorner(btnFooterHeight)
                        }
                        AppConstants.CARDVIEW_CORNER_RADIUS_BIG -> {
                            holder.binding.cvParent.setCornerFamily(CornerFamily.ROUNDED)
                            holder.binding.cvParent.setTopLeftCorner(btnHeaderHeight)
                            holder.binding.cvParent.setTopRightCorner(btnHeaderHeight)
                            holder.binding.cvParent.setBottomLeftCorner(btnFooterHeight)
                            holder.binding.cvParent.setBottomRightCorner(btnFooterHeight)

                            holder.binding.btnHeader.setCornerFamily(CornerFamily.ROUNDED)
                            holder.binding.btnHeader.setTopLeftCorner(btnHeaderHeight)
                            holder.binding.btnHeader.setTopRightCorner(btnHeaderHeight)

                            holder.binding.btnFooter.setCornerFamily(CornerFamily.ROUNDED)
                            holder.binding.btnFooter.setBottomLeftCorner(btnFooterHeight)
                            holder.binding.btnFooter.setBottomRightCorner(btnFooterHeight)
                        }
                        AppConstants.CARDVIEW_TOP_LEFT_BOTTOM_RIGHT_RADIUS_BIG -> {
                            holder.binding.cvParent.setCornerFamily(CornerFamily.ROUNDED)
                            holder.binding.cvParent.setTopLeftCorner(btnHeaderHeight)
                            holder.binding.cvParent.setBottomRightCorner(btnFooterHeight)

                            holder.binding.btnHeader.setCornerFamily(CornerFamily.ROUNDED)
                            holder.binding.btnHeader.setTopLeftCorner(btnHeaderHeight)

                            holder.binding.btnFooter.setCornerFamily(CornerFamily.ROUNDED)
                            holder.binding.btnFooter.setBottomRightCorner(btnFooterHeight)
                        }
                        AppConstants.CARDVIEW_TOP_RIGHT_BOTTOM_LEFT_RADIUS_BIG -> {
                            holder.binding.cvParent.setCornerFamily(CornerFamily.ROUNDED)
                            holder.binding.cvParent.setTopRightCorner(btnHeaderHeight)
                            holder.binding.cvParent.setBottomLeftCorner(btnFooterHeight)

                            holder.binding.btnHeader.setCornerFamily(CornerFamily.ROUNDED)
                            holder.binding.btnHeader.setTopRightCorner(btnHeaderHeight)

                            holder.binding.btnFooter.setCornerFamily(CornerFamily.ROUNDED)
                            holder.binding.btnFooter.setBottomLeftCorner(btnFooterHeight)
                        }
                    }
                    return false
                }
            })
        } else {
            val chatListModel = chatList[position]
            holder.binding.llParent.removeAllViews()

            val chatBubbleShape = when (Utils.dynamicUIModel?.chat?.chatBubble?.chatBubbleStyle) {
                AppConstants.CHAT_BUBBLE_SLOPE -> R.layout.item_chat_bubble_image
                AppConstants.CHAT_BUBBLE_TALKIE_TOP -> R.layout.item_chat_bubble_image
                AppConstants.CHAT_BUBBLE_TALKIE_BOTTOM -> R.layout.item_chat_bubble_image
                AppConstants.CHAT_BUBBLE_WITH_DOTS -> {
                    if (chatListModel.isSender)
                        R.layout.sender_chatbubble_shape_circle_tail
                    else
                        R.layout.receiver_chatbubble_shape_circle_tail
                }
                else -> R.layout.item_chat_bubble_cardview
            }

            val chatBubbleLayout = getLayoutFromInflater(
                context, chatBubbleShape,
                holder.binding.llParent, RelativeLayout::class.java
            )!!

            val cvChatBubble =
                chatBubbleLayout.findViewById<CustomMaterialCardView>(R.id.cv_chat_bubble)
            val tvChatBubble = chatBubbleLayout.findViewById<CustomTextView>(R.id.tv_chat_bubble)
            Utils.setTextSizeInSSP(
                tvChatBubble,
                Utils.getFontSizeInSSP(Utils.dynamicUIModel?.fontSize?.titleHeader!!)
            )
            tvChatBubble.setCustomFont("${Utils.dynamicUIModel?.fontFamily}.ttf")

            val startEndPadding = getSizeInSDP(context, R.dimen._20sdp)
            val topBottomPadding = getSizeInSDP(context, R.dimen._10sdp)
            tvChatBubble.setPadding(startEndPadding, topBottomPadding, startEndPadding, topBottomPadding)

            tvChatBubble.text = chatListModel.data

            var llChatBubble: LinearLayout? = null
            if (chatBubbleShape == R.layout.item_chat_bubble_image) {
                llChatBubble = chatBubbleLayout.findViewById(R.id.ll_chat_bubble)
            }

            val smallCornerSize = getSizeInSDP(context, R.dimen._8sdp).toFloat()

            if (chatListModel.isSender) {
                tvChatBubble.setTextColor(getParsedColorValue(Utils.dynamicUIModel?.chat?.chatBubble?.senderTextColor!!))

                if (chatBubbleShape == R.layout.item_chat_bubble_image) {
                    when (Utils.dynamicUIModel?.chat?.chatBubble?.chatBubbleStyle) {
                        AppConstants.CHAT_BUBBLE_SLOPE -> {
                            llChatBubble?.setBackgroundResource(R.drawable.chat_bubble_shape_slant)
                        }
                        AppConstants.CHAT_BUBBLE_TALKIE_TOP -> {
                            llChatBubble?.setBackgroundResource(R.drawable.chat_bubble_shape_sender_top_tail)
                        }
                        AppConstants.CHAT_BUBBLE_TALKIE_BOTTOM -> {
                            llChatBubble?.setBackgroundResource(R.drawable.chat_bubble_shape_sender_bottom_tail)
                        }
                    }
                } else {
                    when (Utils.dynamicUIModel?.chat?.chatBubble?.chatBubbleStyle) {
                        AppConstants.CHAT_BUBBLE_CORNER_CUT_UPPER -> {
                            cvChatBubble.setCornerFamily(CornerFamily.CUT)
                            cvChatBubble.setTopLeftCorner(smallCornerSize)
                        }
                        AppConstants.CHAT_BUBBLE_CORNER_CUT_LOWER -> {
                            cvChatBubble.setCornerFamily(CornerFamily.CUT)
                            cvChatBubble.setBottomLeftCorner(smallCornerSize)
                        }
                        AppConstants.CHAT_BUBBLE_CORNER_RADIUS -> {
                            cvChatBubble.setCornerFamily(CornerFamily.ROUNDED)
                            cvChatBubble.setAllCorners(smallCornerSize)
                        }
                        AppConstants.CHAT_BUBBLE_TWO_CORNER_RADIUS -> {
                            cvChatBubble.setCornerFamily(CornerFamily.ROUNDED)
                            cvChatBubble.setTopLeftCorner(smallCornerSize)
                            cvChatBubble.setBottomRightCorner(smallCornerSize)
                        }
                        AppConstants.CHAT_BUBBLE_ROUND -> {
                            cvChatBubble.setCornerFamily(CornerFamily.ROUNDED)
                            cvChatBubble.setAllCornersInPercent(0.5F)
                        }
                        AppConstants.CHAT_BUBBLE_ARROW_INSIDE -> {
                            cvChatBubble.setCornerFamily(CornerFamily.CUT)
                            cvChatBubble.setTopRightCornerInPercent(0.5F)
                            cvChatBubble.setBottomRightCornerInPercent(0.5F)
                        }
                        AppConstants.CHAT_BUBBLE_ARROW_OUTSIDE -> {
                            cvChatBubble.setCornerFamily(CornerFamily.CUT)
                            cvChatBubble.setTopLeftCornerInPercent(0.5F)
                            cvChatBubble.setBottomLeftCornerInPercent(0.5F)
                        }
                        AppConstants.CHAT_BUBBLE_ARROW_BOTH -> {
                            cvChatBubble.setCornerFamily(CornerFamily.CUT)
                            cvChatBubble.setAllCornersInPercent(0.5F)
                        }
                        AppConstants.CHAT_BUBBLE_LEFT_OR_RIGHT_ROUND -> {
                            cvChatBubble.setCornerFamily(CornerFamily.ROUNDED)
                            cvChatBubble.setTopLeftCornerInPercent(0.5F)
                            cvChatBubble.setBottomLeftCornerInPercent(0.5F)
                        }
                        AppConstants.CHAT_BUBBLE_WITH_DOTS -> {
                            cvChatBubble.setCornerFamily(CornerFamily.ROUNDED)
                            cvChatBubble.setAllCornersInPercent(0.5F)
                            val tailCircle1: ShapeableImageView? =
                                chatBubbleLayout.findViewById(R.id.iv_circle_1)
                            val tailCircle2: ShapeableImageView? =
                                chatBubbleLayout.findViewById(R.id.iv_circle_3)
                            val tailCircle3: ShapeableImageView? =
                                chatBubbleLayout.findViewById(R.id.iv_circle_2)

                            val bgColor =
                                getParsedColorValue(Utils.dynamicUIModel?.chat?.chatBubble?.senderChatBubbleColor!!)
                            tailCircle1?.setColorFilter(bgColor)
                            tailCircle2?.setColorFilter(bgColor)
                            tailCircle3?.setColorFilter(bgColor)
                        }
                    }
                }

                if (chatBubbleShape == R.layout.item_chat_bubble_image) {
                    llChatBubble?.background?.setTint(
                        getParsedColorValue(
                            Utils.dynamicUIModel?.chat?.chatBubble?.senderChatBubbleColor!!
                        )
                    )
                } else {
                    cvChatBubble.setCardBackgroundColor(
                        getParsedColorValue(
                            Utils.dynamicUIModel?.chat?.chatBubble?.senderChatBubbleColor!!
                        )
                    )
                    setElevationShadow(
                        context,
                        cvChatBubble,
                        Utils.dynamicUIModel?.chat?.chatBubble?.cardBgDropShadow!!
                    )
                }

                val layoutParams = RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT
                )
                layoutParams.setMargins(
                    getSizeInSDP(context, R.dimen._50sdp), getSizeInSDP(context, R.dimen._6sdp),
                    getSizeInSDP(context, R.dimen._6sdp), getSizeInSDP(context, R.dimen._6sdp)
                )

                if (chatBubbleShape == R.layout.item_chat_bubble_image) {
                    llChatBubble?.layoutParams = layoutParams
                } else if (chatBubbleShape == R.layout.item_chat_bubble_cardview) {
                    cvChatBubble.layoutParams = layoutParams
                }

                chatBubbleLayout.gravity = Gravity.END
                holder.binding.llParent.addView(chatBubbleLayout)
            } else {
                tvChatBubble.setTextColor(getParsedColorValue(Utils.dynamicUIModel?.chat?.chatBubble?.receiverTextColor!!))

                if (chatBubbleShape == R.layout.item_chat_bubble_image) {
                    when (Utils.dynamicUIModel?.chat?.chatBubble?.chatBubbleStyle) {
                        AppConstants.CHAT_BUBBLE_SLOPE -> {
                            llChatBubble?.setBackgroundResource(R.drawable.chat_bubble_shape_slant)
                        }
                        AppConstants.CHAT_BUBBLE_TALKIE_TOP -> {
                            llChatBubble?.setBackgroundResource(R.drawable.chat_bubble_shape_receiver_top_tail)
                        }
                        AppConstants.CHAT_BUBBLE_TALKIE_BOTTOM -> {
                            llChatBubble?.setBackgroundResource(R.drawable.chat_bubble_shape_receiver_bottom_tail)
                        }
                    }
                } else {
                    when (Utils.dynamicUIModel?.chat?.chatBubble?.chatBubbleStyle) {
                        AppConstants.CHAT_BUBBLE_CORNER_CUT_UPPER -> {
                            cvChatBubble.setCornerFamily(CornerFamily.CUT)
                            cvChatBubble.setTopRightCorner(smallCornerSize)
                        }
                        AppConstants.CHAT_BUBBLE_CORNER_CUT_LOWER -> {
                            cvChatBubble.setCornerFamily(CornerFamily.CUT)
                            cvChatBubble.setBottomRightCorner(smallCornerSize)
                        }
                        AppConstants.CHAT_BUBBLE_CORNER_RADIUS -> {
                            cvChatBubble.setCornerFamily(CornerFamily.ROUNDED)
                            cvChatBubble.setAllCorners(smallCornerSize)
                        }
                        AppConstants.CHAT_BUBBLE_TWO_CORNER_RADIUS -> {
                            cvChatBubble.setCornerFamily(CornerFamily.ROUNDED)
                            cvChatBubble.setTopLeftCorner(smallCornerSize)
                            cvChatBubble.setBottomRightCorner(smallCornerSize)
                        }
                        AppConstants.CHAT_BUBBLE_ROUND -> {
                            cvChatBubble.setCornerFamily(CornerFamily.ROUNDED)
                            cvChatBubble.setAllCornersInPercent(0.5F)
                        }
                        AppConstants.CHAT_BUBBLE_ARROW_INSIDE -> {
                            cvChatBubble.setCornerFamily(CornerFamily.CUT)
                            cvChatBubble.setTopLeftCornerInPercent(0.5F)
                            cvChatBubble.setBottomLeftCornerInPercent(0.5F)
                        }
                        AppConstants.CHAT_BUBBLE_ARROW_OUTSIDE -> {
                            cvChatBubble.setCornerFamily(CornerFamily.CUT)
                            cvChatBubble.setTopRightCornerInPercent(0.5F)
                            cvChatBubble.setBottomRightCornerInPercent(0.5F)
                        }
                        AppConstants.CHAT_BUBBLE_ARROW_BOTH -> {
                            cvChatBubble.setCornerFamily(CornerFamily.CUT)
                            cvChatBubble.setAllCornersInPercent(0.5F)
                        }
                        AppConstants.CHAT_BUBBLE_LEFT_OR_RIGHT_ROUND -> {
                            cvChatBubble.setCornerFamily(CornerFamily.ROUNDED)
                            cvChatBubble.setTopRightCornerInPercent(0.5F)
                            cvChatBubble.setBottomRightCornerInPercent(0.5F)
                        }
                        AppConstants.CHAT_BUBBLE_WITH_DOTS -> {
                            cvChatBubble.setCornerFamily(CornerFamily.ROUNDED)
                            cvChatBubble.setAllCornersInPercent(0.5F)
                            val tailCircle1: ShapeableImageView? =
                                chatBubbleLayout.findViewById(R.id.iv_circle_1)
                            val tailCircle2: ShapeableImageView? =
                                chatBubbleLayout.findViewById(R.id.iv_circle_3)
                            val tailCircle3: ShapeableImageView? =
                                chatBubbleLayout.findViewById(R.id.iv_circle_2)

                            val bgColor =
                                getParsedColorValue(Utils.dynamicUIModel?.chat?.chatBubble?.receiverChatBubbleColor!!)
                            tailCircle1?.setColorFilter(bgColor)
                            tailCircle2?.setColorFilter(bgColor)
                            tailCircle3?.setColorFilter(bgColor)
                        }
                    }
                }

                if (chatBubbleShape == R.layout.item_chat_bubble_image) {
                    llChatBubble?.background?.setTint(
                        getParsedColorValue(Utils.dynamicUIModel?.chat?.chatBubble?.receiverChatBubbleColor!!)
                    )
                } else {
                    cvChatBubble.setCardBackgroundColor(
                        getParsedColorValue(Utils.dynamicUIModel?.chat?.chatBubble?.receiverChatBubbleColor!!)
                    )
                    setCardElevation(
                        context,
                        cvChatBubble,
                        Utils.dynamicUIModel?.chat?.chatBubble?.cardBgDropShadow!!
                    )
                }

                val layoutParams = RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT
                )
                layoutParams.setMargins(
                    getSizeInSDP(context, R.dimen._6sdp), getSizeInSDP(context, R.dimen._6sdp),
                    getSizeInSDP(context, R.dimen._50sdp), getSizeInSDP(context, R.dimen._6sdp)
                )

                if (chatBubbleShape == R.layout.item_chat_bubble_image) {
                    llChatBubble?.layoutParams = layoutParams
                } else if (chatBubbleShape == R.layout.item_chat_bubble_cardview) {
                    cvChatBubble.layoutParams = layoutParams
                }

                chatBubbleLayout.gravity = Gravity.START

                holder.binding.llParent.addView(chatBubbleLayout)
            }
        }
    }
}

class ChatListViewHolder(val binding: ItemChatListBinding) :
    RecyclerView.ViewHolder(binding.root)