package com.example.corechatoperationconfigapp.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.corechatoperationconfigapp.databinding.LayoutHomePublicSupportGroupsListAdapterBinding
import com.example.corechatoperationconfigapp.utils.Utils

class HomePublicSupportGroupsListAdapter(
    val context: Context,
    private val groupsList: ArrayList<String>
) : RecyclerView.Adapter<HomePublicSupportGroupsListAdapter.HomePublicSupportGroupsListViewHolder>() {

    interface OnItemClickListener{
        fun OnItemClick(list: ArrayList<String>, position: Int)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomePublicSupportGroupsListViewHolder {
        val binding =
            LayoutHomePublicSupportGroupsListAdapterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return HomePublicSupportGroupsListViewHolder(binding)
    }

    override fun getItemCount(): Int = 5 //groupsList.size

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getItemViewType(position: Int): Int = position

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onBindViewHolder(holder: HomePublicSupportGroupsListViewHolder, position: Int) {
        holder.binding.dynamicUIModel = Utils.dynamicUIModel
        val iconList = Utils.dynamicUIModel?.icons
        holder.binding.tvGroupName.text = groupsList[position]

        when (position) {
            0 -> {
                holder.binding.iconRoleProfile.setTextColor(Color.parseColor(iconList!!.agent.iconColor))
                "&#x${iconList.agent.iconValue}".also { holder.binding.iconRoleProfile.text = it }
                "&#x${iconList.allTime.iconValue}".also { holder.binding.iconGroupSchedule.text = it }
            }
            1 -> {
                holder.binding.iconRoleProfile.setTextColor(Color.parseColor(iconList!!.manager.iconColor))
                "&#x${iconList.manager.iconValue}".also { holder.binding.iconRoleProfile.text = it }
                "&#x${iconList.schedule.iconValue}".also { holder.binding.iconGroupSchedule.text = it }
            }
            2 -> {
                "&#x${iconList!!.supervisor.iconValue}".also { holder.binding.iconRoleProfile.text = it }
                holder.binding.iconRoleProfile.setTextColor(Color.parseColor(iconList.supervisor.iconColor))
                "&#x${iconList.allTime.iconValue}".also { holder.binding.iconGroupSchedule.text = it }
            }
            3 -> {
                "&#x${iconList!!.agent.iconValue}".also { holder.binding.iconRoleProfile.text = it }
                holder.binding.iconRoleProfile.setTextColor(Color.parseColor(iconList.agent.iconColor))
                "&#x${iconList.allTime.iconValue}".also { holder.binding.iconGroupSchedule.text = it }
            }
            4 -> {
                "&#x${iconList!!.supervisor.iconValue}".also { holder.binding.iconRoleProfile.text = it }
                holder.binding.iconRoleProfile.setTextColor(Color.parseColor(iconList.supervisor.iconColor))
                "&#x${iconList.allTime.iconValue}".also { holder.binding.iconGroupSchedule.text = it }
            }
        }
    }

    class HomePublicSupportGroupsListViewHolder(val binding: LayoutHomePublicSupportGroupsListAdapterBinding) :
        RecyclerView.ViewHolder(binding.root)
}