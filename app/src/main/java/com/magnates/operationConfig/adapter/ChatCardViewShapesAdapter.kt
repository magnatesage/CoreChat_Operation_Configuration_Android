package com.magnates.operationConfig.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewTreeObserver
import androidx.recyclerview.widget.RecyclerView
import com.magnates.operationConfig.R
import com.magnates.operationConfig.databinding.ItemChatCardviewShapesAdapterBinding
import com.magnates.operationConfig.utils.AppConstants
import com.magnates.operationConfig.utils.Extensions.invisible
import com.magnates.operationConfig.utils.Extensions.visible
import com.magnates.operationConfig.utils.Utils
import com.magnates.operationConfig.utils.Utils.getDesiredDrawableFromXML
import com.magnates.operationConfig.utils.Utils.getStringFromXML
import com.google.android.material.shape.CornerFamily

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

        """${getStringFromXML(context, R.string.style)} ${holder.adapterPosition + 1}""".also { holder.binding.tvContent.text = it }

        holder.binding.cvParent.viewTreeObserver.addOnPreDrawListener(object :
            ViewTreeObserver.OnPreDrawListener {
            override fun onPreDraw(): Boolean {
                holder.binding.cvParent.viewTreeObserver.removeOnPreDrawListener(this)
                val btnHeaderHeight = holder.binding.btnHeader.height.toFloat()
                val btnFooterHeight = holder.binding.btnFooter.height.toFloat()
                when (chatCardViewShapeList[holder.adapterPosition]) {
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

        if (selectedPosition != -1 && selectedPosition == holder.adapterPosition) {
            holder.binding.rlCardviewParent.background =
                getDesiredDrawableFromXML(context, R.drawable.shadow_img)
            holder.binding.checkImg.visible()
            Utils.dynamicUIModel?.chat?.cardView?.cardViewShapeId = chatCardViewShapeList[holder.adapterPosition]
        } else {
            holder.binding.rlCardviewParent.background = null
            holder.binding.checkImg.invisible()
        }

        holder.binding.rlCardviewParent.setOnClickListener {
            selectedPosition = holder.adapterPosition
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