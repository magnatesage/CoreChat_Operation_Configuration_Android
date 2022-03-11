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

package com.example.corechatoperationconfigapp.customviews

import android.content.Context
import android.graphics.Typeface
import android.text.Html
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.text.HtmlCompat
import com.example.corechatoperationconfigapp.R

/**
 * This is Custom TextView Class extended from AppCompatTextView Class of Android
 * Using @property setCustomFont we can set Custom Fonts to text of TextView
 * This class can also be used for showing custom text from ttf files as icons.
 */
class CustomTextView : AppCompatTextView {

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        setCustomFont(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        setCustomFont(context, attrs)
    }

    private fun setCustomFont(ctx: Context, attrs: AttributeSet) {
        val a = ctx.obtainStyledAttributes(attrs, R.styleable.CustomTextView)
        val customFont = a.getString(R.styleable.CustomTextView_customFont)
        setCustomFont(customFont)
        a.recycle()
    }
    fun setCustomFont(fontName: String?) {
        var myTypeface: Typeface? = null
        try {
            myTypeface = if (!fontName.isNullOrBlank()) {
                Typeface.createFromAsset(context.assets, "fonts/$fontName")
            } else {
                Typeface.createFromAsset(context.assets, "fonts/" + context.getString(R.string.Roboto_Regular))
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        typeface = myTypeface
    }

    override fun setText(text: CharSequence, type: BufferType?) {
        super.setText(HtmlCompat.fromHtml(text.toString().replace("\n", "<br/>"), HtmlCompat.FROM_HTML_MODE_LEGACY).toString(), type)
    }
}
