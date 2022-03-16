package com.magnates.operationConfig.adapter

import android.app.Activity
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.magnates.operationConfig.R
import com.magnates.operationConfig.databinding.LayoutHistoryChatBinding
import com.magnates.operationConfig.utils.Utils

class HistoryChatListAdapter(val context: Activity, private val historyChatList: List<String>) :
    RecyclerView.Adapter<HistoryChatListAdapter.HistoryChatListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryChatListViewHolder {
        val binding =
            LayoutHistoryChatBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return HistoryChatListViewHolder(binding)
    }

    override fun getItemCount(): Int = 10 //historyChatList.size

    override fun onBindViewHolder(holder: HistoryChatListViewHolder, position: Int) {
        holder.binding.dynamicUIModel = Utils.dynamicUIModel
        val iconList = Utils.dynamicUIModel?.icons
        holder.binding.tvUserName.text = historyChatList[position]
        holder.binding.tvGroupName.text = context.getString(R.string.lbl_help_4_farmers)
        holder.binding.tvSenderName.text = context.getString(R.string.lbl_sender)
        holder.binding.tvLastMsg.text = context.getString(R.string.lbl_last_msg)
        holder.binding.tvMsgTime.text = context.getString(R.string.value_msg_time)
        holder.binding.tvSenderName.text = context.getString(R.string.value_sender_name)
        holder.binding.tvSenderName.setTextColor(Color.parseColor(iconList!!.agent.iconColor))
        holder.binding.ivUser.setImageDrawable(context.getDrawable(R.drawable.group_hug))

        "&#x${iconList.agent.iconValue}".also { holder.binding.tvSenderIcon.text = it }
        holder.binding.tvSenderIcon.setTextColor(Color.parseColor(iconList.agent.iconColor))
        "&#x${iconList.greenFlag.iconValue}".also { holder.binding.tvFlag.text = it }
        holder.binding.tvFlag.setTextColor(Color.parseColor(iconList.greenFlag.iconColor))
        "&#x${iconList.notes.iconValue}".also { holder.binding.iconNotes.text = it }

        holder.binding.llNotes.setOnClickListener {
            Toast.makeText(context,"Add Notes: $position",Toast.LENGTH_SHORT).show()
        }

        holder.binding.rlMain.setOnClickListener {
            Toast.makeText(context,"Chat Clicked: $position",Toast.LENGTH_SHORT).show()
        }
    }

    class HistoryChatListViewHolder(val binding: LayoutHistoryChatBinding) :
        RecyclerView.ViewHolder(binding.root)
}
