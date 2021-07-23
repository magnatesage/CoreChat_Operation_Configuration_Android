package com.example.corechatoperationconfigapp.adapter

import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.corechatoperationconfigapp.R
import com.example.corechatoperationconfigapp.databinding.LayoutLiveChatBinding
import com.example.corechatoperationconfigapp.utils.Utils

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

    override fun getItemCount(): Int = 10 //waitingUsersList.size

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
