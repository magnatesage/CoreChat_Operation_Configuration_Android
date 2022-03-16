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