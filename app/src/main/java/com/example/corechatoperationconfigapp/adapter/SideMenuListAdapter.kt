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

package com.example.corechatoperationconfigapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.corechatoperationconfigapp.databinding.ItemSideMenuBinding
import com.example.corechatoperationconfigapp.utils.Utils

class SideMenuListAdapter() : RecyclerView.Adapter<SideMenuListAdapter.SideMenuListViewHolder>() {

    private val sideMenuList = Utils.dynamicUIModel?.sideMenu

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SideMenuListViewHolder {
        val binding = ItemSideMenuBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return SideMenuListViewHolder(binding)
    }

    override fun getItemCount(): Int = sideMenuList!!.size

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onBindViewHolder(holder: SideMenuListViewHolder, position: Int) {
        holder.binding.dynamicUIModel = Utils.dynamicUIModel
        val menuItem = sideMenuList!![position]
        holder.binding.tvMenuItem.setCustomFont("${Utils.getBoldItalicFontType(menuItem.fontType)}.ttf")
        holder.binding.tvMenuItem.text = menuItem.textValue
        "&#x${menuItem.iconValue}".also { holder.binding.tvMenuItemIcon.text = it }
    }

    class SideMenuListViewHolder (val binding: ItemSideMenuBinding) :
        RecyclerView.ViewHolder(binding.root)
}