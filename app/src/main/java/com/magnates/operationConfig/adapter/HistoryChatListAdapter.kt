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
