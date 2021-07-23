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