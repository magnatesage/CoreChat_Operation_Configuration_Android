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
import android.view.ViewTreeObserver
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.shape.CornerFamily
import com.magnates.operationConfig.R
import com.magnates.operationConfig.databinding.ItemChatCardviewShapesAdapterBinding
import com.magnates.operationConfig.utils.AppConstants
import com.magnates.operationConfig.utils.Extensions.invisible
import com.magnates.operationConfig.utils.Extensions.visible
import com.magnates.operationConfig.utils.Utils
import com.magnates.operationConfig.utils.Utils.getDesiredDrawableFromXML
import com.magnates.operationConfig.utils.Utils.getStringFromXML

class ChatCardViewShapesAdapter(
    val context: Activity
) :
    RecyclerView.Adapter<ChatCardViewShapesViewHolder>() {
    private var selectedPosition = -1
    private val chatCardViewShapeList = arrayOf(
        AppConstants.CARDVIEW_SQUARE,
        AppConstants.CARDVIEW_CORNER_RADIUS_SMALL,
        AppConstants.CARDVIEW_TOP_LEFT_BOTTOM_RIGHT_RADIUS_SMALL,
        AppConstants.CARDVIEW_TOP_RIGHT_BOTTOM_LEFT_RADIUS_SMALL,
        AppConstants.CARDVIEW_FOUR_CORNER_CUT_SMALL,
        AppConstants.CARDVIEW_TOP_LEFT_BOTTOM_RIGHT_CUT_SMALL,
        AppConstants.CARDVIEW_TOP_RIGHT_BOTTOM_LEFT_CUT_SMALL,
        AppConstants.CARDVIEW_FOUR_CORNER_CUT_BIG,
        AppConstants.CARDVIEW_TOP_LEFT_BOTTOM_RIGHT_CUT_BIG,
        AppConstants.CARDVIEW_TOP_RIGHT_BOTTOM_LEFT_CUT_BIG,
        AppConstants.CARDVIEW_CORNER_RADIUS_BIG,
        AppConstants.CARDVIEW_TOP_LEFT_BOTTOM_RIGHT_RADIUS_BIG,
        AppConstants.CARDVIEW_TOP_RIGHT_BOTTOM_LEFT_RADIUS_BIG
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatCardViewShapesViewHolder {
        val binding = ItemChatCardviewShapesAdapterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ChatCardViewShapesViewHolder(binding)
    }

    override fun getItemCount(): Int = chatCardViewShapeList.size
    override fun getItemId(position: Int): Long = position.toLong()
    override fun getItemViewType(position: Int): Int = position

    override fun onBindViewHolder(holder: ChatCardViewShapesViewHolder, position: Int) {

        """${getStringFromXML(context, R.string.style)} ${position + 1}""".also { holder.binding.tvContent.text = it }

        holder.binding.cvParent.viewTreeObserver.addOnPreDrawListener(object :
            ViewTreeObserver.OnPreDrawListener {
            override fun onPreDraw(): Boolean {
                holder.binding.cvParent.viewTreeObserver.removeOnPreDrawListener(this)
                val btnHeaderHeight = holder.binding.btnHeader.height.toFloat()
                val btnFooterHeight = holder.binding.btnFooter.height.toFloat()
                when (chatCardViewShapeList[position]) {
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

        if (selectedPosition != -1 && selectedPosition == position) {
            holder.binding.rlCardviewParent.background =
                getDesiredDrawableFromXML(context, R.drawable.shadow_img)
            holder.binding.checkImg.visible()
            Utils.dynamicUIModel?.chat?.cardView?.cardViewShapeId = chatCardViewShapeList[position]
        } else {
            holder.binding.rlCardviewParent.background = null
            holder.binding.checkImg.invisible()
        }

        holder.binding.rlCardviewParent.setOnClickListener {
            selectedPosition = position
            notifyDataSetChanged()
        }
    }

    fun setAndGetSelectedChatCardView(selectedChatCardViewShape: Int): Int {
        for (i in chatCardViewShapeList.indices){
            if (chatCardViewShapeList[i] == selectedChatCardViewShape){
                selectedPosition = i
                break
            }
        }
        notifyDataSetChanged()
        return selectedPosition
    }
}

class ChatCardViewShapesViewHolder(val binding: ItemChatCardviewShapesAdapterBinding) :
    RecyclerView.ViewHolder(binding.root)