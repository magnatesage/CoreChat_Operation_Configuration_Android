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
import android.content.res.TypedArray
import android.graphics.Typeface
import android.util.AttributeSet
import android.util.TypedValue
import com.example.corechatoperationconfigapp.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.shape.AbsoluteCornerSize
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.CornerSize
import com.google.android.material.shape.RelativeCornerSize

/**
 * This is Custom Material Button Class extended from Material Button Class of Material Designing
 * Using @property setCustomFont we can set Custom Fonts to text of Material Buttons
 * This class is mainly used for making custom shapes of buttons.
 */
class CustomMaterialButton : MaterialButton {
    private var cornerFamily: Int = CornerFamily.ROUNDED
    private var allCornerSize: CornerSize = AbsoluteCornerSize(0F)
    private var topLeftCornerSize: CornerSize = allCornerSize
    private var topRightCornerSize: CornerSize = allCornerSize
    private var bottomRightCornerSize: CornerSize = allCornerSize
    private var bottomLeftCornerSize: CornerSize = allCornerSize

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

    private fun customAttr(context: Context, attrs: AttributeSet) {
        val a = context.obtainStyledAttributes(attrs, R.styleable.CustomMaterialButton)
        val customFont = a.getString(R.styleable.CustomMaterialButton_customMaterialButtonFont)
        setCustomFont(customFont)

        cornerFamily =
            a.getInteger(R.styleable.CustomMaterialButton_buttonAllCornerFamily, CornerFamily.ROUNDED)

        allCornerSize = getCornerSize(
            a,
            R.styleable.CustomMaterialCardView_allCornerSize,
            AbsoluteCornerSize(0F)
        )

        topLeftCornerSize = getCornerSize(
            a,
            R.styleable.CustomMaterialCardView_topLeftCornerSize,
            allCornerSize
        )
        topRightCornerSize = getCornerSize(
            a,
            R.styleable.CustomMaterialCardView_topRightCornerSize,
            allCornerSize
        )
        bottomLeftCornerSize = getCornerSize(
            a,
            R.styleable.CustomMaterialCardView_bottomLeftCornerSize,
            allCornerSize
        )
        bottomRightCornerSize = getCornerSize(
            a,
            R.styleable.CustomMaterialCardView_bottomRightCornerSize,
            allCornerSize
        )
        
        updateMaterialButton()
        a.recycle()
    }

    private fun getCornerSize(
        a: TypedArray, index: Int, defaultValue: CornerSize
    ): CornerSize {
        val value = a.peekValue(index) ?: return defaultValue
        return if (value.type == TypedValue.TYPE_DIMENSION) {
            AbsoluteCornerSize(
                TypedValue.complexToDimensionPixelSize(
                    value.data,
                    a.resources.displayMetrics
                ).toFloat()
            )
        } else if (value.type == TypedValue.TYPE_FRACTION) {
            RelativeCornerSize(value.getFraction(1.0f, 1.0f))
        } else {
            defaultValue
        }
    }

    fun setCustomFont(fontName: String?) {
        var myTypeface: Typeface? = null
        try {
            if (!fontName.isNullOrBlank())
                myTypeface = Typeface.createFromAsset(context.assets, "fonts/$fontName")
        } catch (e: Exception) {
            e.printStackTrace()
        }
        typeface = myTypeface
    }

    fun setCornerFamily(cornerFamily: Int) {
        this.cornerFamily = cornerFamily
        updateMaterialButton()
    }

    fun setAllCorners(allCornerSize: Float) {
        this.allCornerSize = AbsoluteCornerSize(allCornerSize)
        this.topLeftCornerSize = AbsoluteCornerSize(allCornerSize)
        this.topRightCornerSize = AbsoluteCornerSize(allCornerSize)
        this.bottomLeftCornerSize = AbsoluteCornerSize(allCornerSize)
        this.bottomRightCornerSize = AbsoluteCornerSize(allCornerSize)
        updateMaterialButton()
    }

    fun setAllCornersInPercent(allCornerSize: Float) {
        this.allCornerSize = RelativeCornerSize(allCornerSize)
        this.topLeftCornerSize = RelativeCornerSize(allCornerSize)
        this.topRightCornerSize = RelativeCornerSize(allCornerSize)
        this.bottomLeftCornerSize = RelativeCornerSize(allCornerSize)
        this.bottomRightCornerSize = RelativeCornerSize(allCornerSize)
        updateMaterialButton()
    }

    fun setTopLeftCorner(topLeftCornerSize: Float) {
        this.topLeftCornerSize = AbsoluteCornerSize(topLeftCornerSize)
        updateMaterialButton()
    }

    fun setTopLeftCornerInPercent(topLeftCornerSize: Float) {
        this.topLeftCornerSize = RelativeCornerSize(topLeftCornerSize)
        updateMaterialButton()
    }

    fun setTopRightCorner(topRightCornerSize: Float) {
        this.topRightCornerSize = AbsoluteCornerSize(topRightCornerSize)
        updateMaterialButton()
    }

    fun setTopRightCornerInPercent(topRightCornerSize: Float) {
        this.topRightCornerSize = RelativeCornerSize(topRightCornerSize)
        updateMaterialButton()
    }

    fun setBottomLeftCorner(bottomLeftCornerSize: Float) {
        this.bottomLeftCornerSize = AbsoluteCornerSize(bottomLeftCornerSize)
        updateMaterialButton()
    }

    fun setBottomLeftCornerInPercent(bottomLeftCornerSize: Float) {
        this.bottomLeftCornerSize = RelativeCornerSize(bottomLeftCornerSize)
        updateMaterialButton()
    }

    fun setBottomRightCorner(bottomRightCornerSize: Float) {
        this.bottomRightCornerSize = AbsoluteCornerSize(bottomRightCornerSize)
        updateMaterialButton()
    }

    fun setBottomRightCornerInPercent(bottomRightCornerSize: Float) {
        this.bottomRightCornerSize = RelativeCornerSize(bottomRightCornerSize)
        updateMaterialButton()
    }

    private fun updateMaterialButton() {
        shapeAppearanceModel = shapeAppearanceModel.toBuilder()
            .setTopLeftCorner(cornerFamily, topLeftCornerSize)
            .setTopRightCorner(cornerFamily, topRightCornerSize)
            .setBottomLeftCorner(cornerFamily, bottomLeftCornerSize)
            .setBottomRightCorner(cornerFamily, bottomRightCornerSize)
            .build()
    }
}
