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
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.magnates.operationConfig.R
import com.magnates.operationConfig.databinding.LayoutNotificationBinding
import com.magnates.operationConfig.utils.Utils

class NotificationAdapter(
    val context: Context,
    private val notificationList: List<String>
) : RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>() {

    class NotificationViewHolder(val binding: LayoutNotificationBinding):
        RecyclerView.ViewHolder(binding.root)

    interface OnItemClickListener {
        fun onItemClick(notificationList: List<String>)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val binding = LayoutNotificationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NotificationViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        holder.binding.dynamicUIModel = Utils.dynamicUIModel
        val iconList = Utils.dynamicUIModel?.icons
        holder.binding.tvName.text = notificationList[position]
        holder.binding.tvSubtext.text = context.getString(R.string.lbl_subject_line)
        holder.binding.ivUser.setImageDrawable(context.getDrawable(R.drawable.group_hug))
        "&#x${iconList!!.umberFlag.iconValue}".also { holder.binding.tvFlag.text = it }
        "&#x${iconList.userLocation.iconValue}".also { holder.binding.tvIcon.text = it }
        holder.binding.tvIcon.setTextColor(context.getColor(R.color.colorRedButton))
        holder.binding.tvFlag.setTextColor(ColorStateList.valueOf(Color.parseColor(iconList.umberFlag.iconColor)))
        if (position == 2){
            holder.binding.tvStatus.visibility = View.VISIBLE
            holder.binding.tvStatus.text = context.getString(R.string.agent)
            holder.binding.tvStatus.setTextColor(ColorStateList.valueOf(Color.parseColor(iconList.agent.iconColor)))
            holder.binding.llAcceptReject.visibility = View.GONE
            holder.binding.viewAcceptReject.visibility = View.GONE
        }else{
            holder.binding.tvStatus.visibility = View.GONE
            holder.binding.llAcceptReject.visibility = View.VISIBLE
        }
    }

    override fun getItemCount(): Int = 6
}