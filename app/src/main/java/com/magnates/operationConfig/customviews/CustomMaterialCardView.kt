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

package com.magnates.operationConfig.customviews

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.util.TypedValue
import com.google.android.material.card.MaterialCardView
import com.google.android.material.shape.AbsoluteCornerSize
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.CornerSize
import com.google.android.material.shape.RelativeCornerSize
import com.magnates.operationConfig.R

class CustomMaterialCardView : MaterialCardView {
    private var cardCornerFamily: Int = CornerFamily.ROUNDED
    private var cardAllCornerSize: CornerSize = AbsoluteCornerSize(0F)
    private var cardTopLeftCornerSize: CornerSize = cardAllCornerSize
    private var cardTopRightCornerSize: CornerSize = cardAllCornerSize
    private var cardBottomRightCornerSize: CornerSize = cardAllCornerSize
    private var cardBottomLeftCornerSize: CornerSize = cardAllCornerSize

    constructor(context: Context?) : super(context) {}

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
        val a = context.obtainStyledAttributes(attrs, R.styleable.CustomMaterialCardView)
        cardCornerFamily =
            a.getInteger(R.styleable.CustomMaterialCardView_allCornerFamily, CornerFamily.ROUNDED)

        cardAllCornerSize = getCornerSize(
            a,
            R.styleable.CustomMaterialCardView_allCornerSize,
            AbsoluteCornerSize(0F)
        )

        cardTopLeftCornerSize = getCornerSize(
            a,
            R.styleable.CustomMaterialCardView_topLeftCornerSize,
            cardAllCornerSize
        )
        cardTopRightCornerSize = getCornerSize(
            a,
            R.styleable.CustomMaterialCardView_topRightCornerSize,
            cardAllCornerSize
        )
        cardBottomLeftCornerSize = getCornerSize(
            a,
            R.styleable.CustomMaterialCardView_bottomLeftCornerSize,
            cardAllCornerSize
        )
        cardBottomRightCornerSize = getCornerSize(
            a,
            R.styleable.CustomMaterialCardView_bottomRightCornerSize,
            cardAllCornerSize
        )
        updateCardView()
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

    fun setCornerFamily(cornerFamily: Int) {
        this.cardCornerFamily = cornerFamily
        updateCardView()
    }

    fun setAllCorners(allCornerSize: Float) {
        this.cardAllCornerSize = AbsoluteCornerSize(allCornerSize)
        this.cardTopLeftCornerSize = AbsoluteCornerSize(allCornerSize)
        this.cardTopRightCornerSize = AbsoluteCornerSize(allCornerSize)
        this.cardBottomLeftCornerSize = AbsoluteCornerSize(allCornerSize)
        this.cardBottomRightCornerSize = AbsoluteCornerSize(allCornerSize)
        updateCardView()
    }

    fun setAllCornersInPercent(allCornerSize: Float) {
        this.cardAllCornerSize = RelativeCornerSize(allCornerSize)
        this.cardTopLeftCornerSize = RelativeCornerSize(allCornerSize)
        this.cardTopRightCornerSize = RelativeCornerSize(allCornerSize)
        this.cardBottomLeftCornerSize = RelativeCornerSize(allCornerSize)
        this.cardBottomRightCornerSize = RelativeCornerSize(allCornerSize)
        updateCardView()
    }

    fun setTopLeftCorner(topLeftCornerSize: Float) {
        this.cardTopLeftCornerSize = AbsoluteCornerSize(topLeftCornerSize)
        updateCardView()
    }

    fun setTopLeftCornerInPercent(topLeftCornerSize: Float) {
        this.cardTopLeftCornerSize = RelativeCornerSize(topLeftCornerSize)
        updateCardView()
    }

    fun setTopRightCorner(topRightCornerSize: Float) {
        this.cardTopRightCornerSize = AbsoluteCornerSize(topRightCornerSize)
        updateCardView()
    }

    fun setTopRightCornerInPercent(topRightCornerSize: Float) {
        this.cardTopRightCornerSize = RelativeCornerSize(topRightCornerSize)
        updateCardView()
    }

    fun setBottomLeftCorner(bottomLeftCornerSize: Float) {
        this.cardBottomLeftCornerSize = AbsoluteCornerSize(bottomLeftCornerSize)
        updateCardView()
    }

    fun setBottomLeftCornerInPercent(bottomLeftCornerSize: Float) {
        this.cardBottomLeftCornerSize = RelativeCornerSize(bottomLeftCornerSize)
        updateCardView()
    }

    fun setBottomRightCorner(bottomRightCornerSize: Float) {
        this.cardBottomRightCornerSize = AbsoluteCornerSize(bottomRightCornerSize)
        updateCardView()
    }

    fun setBottomRightCornerInPercent(bottomRightCornerSize: Float) {
        this.cardBottomRightCornerSize = RelativeCornerSize(bottomRightCornerSize)
        updateCardView()
    }

    private fun updateCardView() {
        shapeAppearanceModel = shapeAppearanceModel.toBuilder()
            .setTopLeftCorner(cardCornerFamily, cardTopLeftCornerSize)
            .setTopRightCorner(cardCornerFamily, cardTopRightCornerSize)
            .setBottomLeftCorner(cardCornerFamily, cardBottomLeftCornerSize)
            .setBottomRightCorner(cardCornerFamily, cardBottomRightCornerSize)
            .build()
    }
}