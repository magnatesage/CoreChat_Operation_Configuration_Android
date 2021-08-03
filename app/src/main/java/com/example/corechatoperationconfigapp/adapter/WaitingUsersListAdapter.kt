package com.example.corechatoperationconfigapp.adapter

import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.corechatoperationconfigapp.R
import com.example.corechatoperationconfigapp.databinding.LayoutWaitingUsersListAdapterBinding
import com.example.corechatoperationconfigapp.utils.Utils

class WaitingUsersListAdapter(val context: Activity, private val waitingUsersList: List<String>) :
    RecyclerView.Adapter<WaitingUsersListAdapter.WaitingUsersListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WaitingUsersListViewHolder {
        val binding =
            LayoutWaitingUsersListAdapterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return WaitingUsersListViewHolder(binding)
    }

    override fun getItemCount(): Int = 10 //waitingUsersList.size

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: WaitingUsersListViewHolder, position: Int) {
        holder.binding.dynamicUIModel = Utils.dynamicUIModel

        holder.binding.rlMain.viewTreeObserver.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
            override fun onPreDraw(): Boolean {
                val height = Utils.getSizeInSDP(context, R.dimen._40sdp)
                val layoutParams = holder.binding.rlMain.layoutParams
                layoutParams.height = holder.binding.rlMain.width + height
                holder.binding.rlMain.layoutParams = layoutParams
                holder.binding.rlMain.viewTreeObserver.removeOnPreDrawListener(this)
                return false
            }
        })

        val iconList = Utils.dynamicUIModel?.icons
        holder.binding.tvUserName.text = waitingUsersList[position]
        holder.binding.tvGroupName.text = "Lets Talk Family"
        when(position){
            0 -> {
                "&#x${iconList!!.disconnect.iconValue}".also { holder.binding.cvUserStatus.text = it }
                holder.binding.cvUserStatus.setTextColor(context.getColor(R.color.colorRedButton))
            }
            1 -> {
                "&#x${iconList!!.trainingMode.iconValue}".also { holder.binding.cvUserStatus.text = it }
                holder.binding.cvUserStatus.setTextColor(Color.parseColor(holder.binding.dynamicUIModel!!.themeColor.primaryColor))
            }
            2 -> {
                "&#x${iconList!!.redFlag.iconValue}".also { holder.binding.cvUserStatus.text = it }
                holder.binding.cvUserStatus.setTextColor(Color.parseColor(iconList.redFlag.iconColor))
            }
            3 -> {
                "&#x${iconList!!.greenFlag.iconValue}".also { holder.binding.cvUserStatus.text = it }
                holder.binding.cvUserStatus.setTextColor(Color.parseColor(iconList.greenFlag.iconColor))
            }
            4 -> {
                "&#x${iconList!!.umberFlag.iconValue}".also { holder.binding.cvUserStatus.text = it }
                holder.binding.cvUserStatus.setTextColor(Color.parseColor(iconList.umberFlag.iconColor))
            }
            else -> {
                "&#x${iconList!!.trainingMode.iconValue}".also { holder.binding.cvUserStatus.text = it }
                holder.binding.cvUserStatus.setTextColor(Color.parseColor(holder.binding.dynamicUIModel!!.themeColor.primaryColor))
            }
        }
    }

    class WaitingUsersListViewHolder(val binding: LayoutWaitingUsersListAdapterBinding) :
        RecyclerView.ViewHolder(binding.root)
}
