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
