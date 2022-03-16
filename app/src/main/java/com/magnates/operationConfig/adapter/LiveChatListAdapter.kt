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
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.magnates.operationConfig.R
import com.magnates.operationConfig.databinding.LayoutLiveChatBinding
import com.magnates.operationConfig.utils.Utils

class LiveChatListAdapter(val context: Activity, private val liveChatList: List<String>) :
    RecyclerView.Adapter<LiveChatListAdapter.LiveChatListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LiveChatListViewHolder {
        val binding =
            LayoutLiveChatBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return LiveChatListViewHolder(binding)
    }

    override fun getItemCount(): Int = 10

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: LiveChatListViewHolder, position: Int) {
        holder.binding.dynamicUIModel = Utils.dynamicUIModel
        val iconList = Utils.dynamicUIModel?.icons
        holder.binding.tvUserName.text = liveChatList[position]
        holder.binding.tvGroupName.text = context.getString(R.string.lbl_help_4_farmers)
        holder.binding.tvSubjectLine.text = context.getString(R.string.lbl_subject_line_chat)
        holder.binding.tvLastMsg.text = context.getString(R.string.hr_doc_upload)
        holder.binding.ivUser.setImageDrawable(context.getDrawable(R.drawable.group_hug))
        holder.binding.tvNotes.text = context.getString(R.string.lbl_notes)
        holder.binding.tvRemove.text = context.getString(R.string.lbl_remove)

        "&#x${iconList!!.greenFlag.iconValue}".also { holder.binding.tvFlag.text = it }
        holder.binding.tvFlag.setTextColor(Color.parseColor(iconList.greenFlag.iconColor))
        "&#x${iconList.notes.iconValue}".also { holder.binding.iconNotes.text = it }
        "&#x${iconList.remove.iconValue}".also { holder.binding.iconRemove.text = it }

        holder.binding.llNotes.setOnClickListener {
            Toast.makeText(context,"Add Notes: $position",Toast.LENGTH_SHORT).show()
        }

        holder.binding.llRemove.setOnClickListener {
            Toast.makeText(context,"Chat Removed: $position",Toast.LENGTH_SHORT).show()
        }

        holder.binding.rlMain.setOnClickListener {
            Toast.makeText(context,"Chat Clicked: $position",Toast.LENGTH_SHORT).show()
        }
    }

    class LiveChatListViewHolder(val binding: LayoutLiveChatBinding) :
        RecyclerView.ViewHolder(binding.root)
}
