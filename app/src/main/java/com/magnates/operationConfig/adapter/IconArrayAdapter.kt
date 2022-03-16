package com.magnates.operationConfig.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.widget.AppCompatImageView
import com.magnates.operationConfig.R
import com.magnates.operationConfig.customviews.CustomTextView
import com.magnates.operationConfig.utils.Extensions.gone
import com.magnates.operationConfig.utils.Extensions.visible
import com.magnates.operationConfig.utils.Utils
import com.magnates.operationConfig.utils.Utils.changeTextColor
import com.magnates.operationConfig.utils.Utils.getDesiredColorFromXML


class IconArrayAdapter(
    context: Context, resourceId: Int,
    private val objects: List<String>
) : ArrayAdapter<String>(context, resourceId, objects) {

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getCustomView(position, convertView, parent, false)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getCustomView(position, convertView, parent, true)
    }

    @SuppressLint("SetTextI18n")
    private fun getCustomView(position: Int, convertView: View?, parent: ViewGroup, isOnlyView: Boolean): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val row = inflater.inflate(R.layout.spinner_item_layout, parent, false)
        val tvItem = row.findViewById(R.id.tv_item) as CustomTextView
        val ivDropDown = row.findViewById(R.id.iv_drop_down) as AppCompatImageView

        if (position == 0){
            tvItem.text = objects[position]
            tvItem.setCustomFont(context.getString(R.string.Roboto_Italic))
            Utils.setTextSizeInSSP(tvItem, R.dimen.small_text_size)
        } else {
            tvItem.text = """&#x${objects[position]}"""
            tvItem.setCustomFont(context.getString(R.string.font_icons))
            Utils.setTextSizeInSSP(tvItem, R.dimen._18ssp)
        }

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