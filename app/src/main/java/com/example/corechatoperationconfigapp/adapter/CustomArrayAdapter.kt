package com.example.corechatoperationconfigapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.widget.AppCompatImageView
import com.example.corechatoperationconfigapp.R
import com.example.corechatoperationconfigapp.customviews.CustomTextView
import com.example.corechatoperationconfigapp.utils.Extensions.gone
import com.example.corechatoperationconfigapp.utils.Extensions.visible
import com.example.corechatoperationconfigapp.utils.Utils.changeTextColor
import com.example.corechatoperationconfigapp.utils.Utils.getDesiredColorFromXML


class CustomArrayAdapter(
    context: Context, resourceId: Int,
    private val objects: List<String>
) : ArrayAdapter<String>(context, resourceId, objects) {

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getCustomView(position, convertView, parent, false)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getCustomView(position, convertView, parent, true)
    }

    private fun getCustomView(position: Int, convertView: View?, parent: ViewGroup, isOnlyView: Boolean): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val row = inflater.inflate(R.layout.spinner_item_layout, parent, false)
        val tvItem = row.findViewById(R.id.tv_item) as CustomTextView
        val ivDropDown = row.findViewById(R.id.iv_drop_down) as AppCompatImageView
        tvItem.text = objects[position]

        if (isOnlyView) {
            ivDropDown.visible()
        } else {
            ivDropDown.gone()
        }

        if (isOnlyView && position == 0) {
            changeTextColor(tvItem, getDesiredColorFromXML(context, R.color.lightGray))
            ivDropDown.visible()
        }
        return row
    }
}