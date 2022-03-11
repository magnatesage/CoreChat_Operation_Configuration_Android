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

import android.annotation.SuppressLint
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
import com.example.corechatoperationconfigapp.utils.Utils
import com.example.corechatoperationconfigapp.utils.Utils.changeTextColor
import com.example.corechatoperationconfigapp.utils.Utils.getDesiredColorFromXML


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