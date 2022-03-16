package com.magnates.operationConfig.customviews

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import com.magnates.operationConfig.R

/**
 * This is Custom EditText Class extended from AppCompatEditText Class of Android
 * Using @property setCustomFont we can set Custom Fonts to text of EditTexts
 */
class CustomEditText : AppCompatEditText {

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        customAttr(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        customAttr(context, attrs)
    }

    @SuppressLint("CustomViewStyleable")
    private fun customAttr(context: Context, attrs: AttributeSet) {
        val a = context.obtainStyledAttributes(attrs, R.styleable.CustomEditText)
        val customFont = a.getString(R.styleable.CustomEditText_customEditTextFont)
        setCustomFont(customFont)
        a.recycle()
    }

    fun setCustomFont(fontName: String?) {
        var myTypeface: Typeface? = null
        try {
            if (!fontName.isNullOrBlank())
                myTypeface = Typeface.createFromAsset(context.assets, "fonts/" + fontName!!)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        typeface = myTypeface
    }
}
