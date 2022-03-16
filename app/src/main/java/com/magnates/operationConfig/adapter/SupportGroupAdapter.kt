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
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.magnates.operationConfig.R
import com.magnates.operationConfig.databinding.LayoutGroupPublicSupportGroupsListAdapterBinding
import com.magnates.operationConfig.utils.Utils

class SupportGroupAdapter(
    val context: Context,
    private val groupsList: ArrayList<String>
) : RecyclerView.Adapter<SupportGroupAdapter.GroupPublicSupportGroupsListViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(list: ArrayList<String>, position: Int, role: String)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GroupPublicSupportGroupsListViewHolder {
        val binding =
            LayoutGroupPublicSupportGroupsListAdapterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return GroupPublicSupportGroupsListViewHolder(binding)
    }

    override fun getItemCount(): Int = 5 //groupsList.size

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getItemViewType(position: Int): Int = position

    override fun onBindViewHolder(holder: GroupPublicSupportGroupsListViewHolder, position: Int) {
        holder.binding.dynamicUIModel = Utils.dynamicUIModel
        val iconList = Utils.dynamicUIModel?.icons
        holder.binding.tvGroupName.text = groupsList[position]
        "&#x${iconList!!.user.iconValue}".also { holder.binding.iconRoleProfile.text = it }
        "&#x${iconList.info.iconValue}".also { holder.binding.iconInfo.text = it }
        holder.binding.ivGroupCoverLogo
        holder.binding.tvInfoLabel.text = context.getString(R.string.info)
        when(position){
            0 -> {
                holder.binding.tvUserRole.text = context.getString(R.string.supervisor)
                holder.binding.tvUserRole.setTextColor(Color.parseColor(iconList.supervisor.iconColor))
            }
            1 -> {
                holder.binding.tvUserRole.text = context.getString(R.string.manager)
                holder.binding.tvUserRole.setTextColor(Color.parseColor(iconList.manager.iconColor))
            }
            2 -> {
                holder.binding.tvUserRole.text = context.getString(R.string.agent)
                holder.binding.tvUserRole.setTextColor(Color.parseColor(iconList.agent.iconColor))
            }
            3 -> {
                holder.binding.tvUserRole.text = context.getString(R.string.supervisor)
                holder.binding.tvUserRole.setTextColor(Color.parseColor(iconList.supervisor.iconColor))
            }
            4 -> {
                holder.binding.tvUserRole.text = context.getString(R.string.manager)
                holder.binding.tvUserRole.setTextColor(Color.parseColor(iconList.manager.iconColor))
            }
        }
    }

    class GroupPublicSupportGroupsListViewHolder(val binding: LayoutGroupPublicSupportGroupsListAdapterBinding) :
        RecyclerView.ViewHolder(binding.root)
}