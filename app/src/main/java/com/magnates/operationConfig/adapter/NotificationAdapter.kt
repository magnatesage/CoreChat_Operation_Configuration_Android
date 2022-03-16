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