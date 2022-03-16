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
import android.view.ViewTreeObserver
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.magnates.operationConfig.R
import com.magnates.operationConfig.databinding.LayoutWaitingUsersListAdapterBinding
import com.magnates.operationConfig.utils.Utils

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
