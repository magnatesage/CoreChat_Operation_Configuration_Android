package com.example.corechatoperationconfigapp.customviews

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.util.TypedValue
import com.example.corechatoperationconfigapp.R
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.shape.AbsoluteCornerSize
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.CornerSize
import com.google.android.material.shape.RelativeCornerSize

class CustomShapeableImageView : ShapeableImageView {

    private var cornerFamily: Int = CornerFamily.ROUNDED
    private var allCornerSize: CornerSize = AbsoluteCornerSize(0F)
    private var topLeftCornerSize: CornerSize = allCornerSize
    private var topRightCornerSize: CornerSize = allCornerSize
    private var bottomRightCornerSize: CornerSize = allCornerSize
    private var bottomLeftCornerSize: CornerSize = allCornerSize

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs){
        customAttr(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ){
        customAttr(context, attrs)
    }

    private fun customAttr(context: Context, attrs: AttributeSet) {
        val a = context.obtainStyledAttributes(attrs, R.styleable.CustomShapeableImageView)
        cornerFamily =
            a.getInteger(R.styleable.CustomShapeableImageView_shapeableAllCornerFamily, CornerFamily.ROUNDED)

        allCornerSize = getCornerSize(
            a,
            R.styleable.CustomShapeableImageView_shapeableAllCornerSize,
            AbsoluteCornerSize(0F)
        )

        topLeftCornerSize = getCornerSize(
            a,
            R.styleable.CustomShapeableImageView_shapeableTopLeftCornerSize,
            allCornerSize
        )
        topRightCornerSize = getCornerSize(
            a,
            R.styleable.CustomShapeableImageView_shapeableTopRightCornerSize,
            allCornerSize
        )
        bottomLeftCornerSize = getCornerSize(
            a,
            R.styleable.CustomShapeableImageView_shapeableBottomLeftCornerSize,
            allCornerSize
        )
        bottomRightCornerSize = getCornerSize(
            a,
            R.styleable.CustomShapeableImageView_shapeableBottomRightCornerSize,
            allCornerSize
        )
        updateShapeableImageView()
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
        this.cornerFamily = cornerFamily
        updateShapeableImageView()
    }

    fun setAllCorners(allCornerSize: Float) {
        this.allCornerSize = AbsoluteCornerSize(allCornerSize)
        this.topLeftCornerSize = AbsoluteCornerSize(allCornerSize)
        this.topRightCornerSize = AbsoluteCornerSize(allCornerSize)
        this.bottomLeftCornerSize = AbsoluteCornerSize(allCornerSize)
        this.bottomRightCornerSize = AbsoluteCornerSize(allCornerSize)
        updateShapeableImageView()
    }

    fun setAllCornersInPercent(allCornerSize: Float) {
        this.allCornerSize = RelativeCornerSize(allCornerSize)
        this.topLeftCornerSize = RelativeCornerSize(allCornerSize)
        this.topRightCornerSize = RelativeCornerSize(allCornerSize)
        this.bottomLeftCornerSize = RelativeCornerSize(allCornerSize)
        this.bottomRightCornerSize = RelativeCornerSize(allCornerSize)
        updateShapeableImageView()
    }

    fun setTopLeftCorner(topLeftCornerSize: Float) {
        this.topLeftCornerSize = AbsoluteCornerSize(topLeftCornerSize)
        updateShapeableImageView()
    }

    fun setTopLeftCornerInPercent(topLeftCornerSize: Float) {
        this.topLeftCornerSize = RelativeCornerSize(topLeftCornerSize)
        updateShapeableImageView()
    }

    fun setTopRightCorner(topRightCornerSize: Float) {
        this.topRightCornerSize = AbsoluteCornerSize(topRightCornerSize)
        updateShapeableImageView()
    }

    fun setTopRightCornerInPercent(topRightCornerSize: Float) {
        this.topRightCornerSize = RelativeCornerSize(topRightCornerSize)
        updateShapeableImageView()
    }

    fun setBottomLeftCorner(bottomLeftCornerSize: Float) {
        this.bottomLeftCornerSize = AbsoluteCornerSize(bottomLeftCornerSize)
        updateShapeableImageView()
    }

    fun setBottomLeftCornerInPercent(bottomLeftCornerSize: Float) {
        this.bottomLeftCornerSize = RelativeCornerSize(bottomLeftCornerSize)
        updateShapeableImageView()
    }

    fun setBottomRightCorner(bottomRightCornerSize: Float) {
        this.bottomRightCornerSize = AbsoluteCornerSize(bottomRightCornerSize)
        updateShapeableImageView()
    }

    fun setBottomRightCornerInPercent(bottomRightCornerSize: Float) {
        this.bottomRightCornerSize = RelativeCornerSize(bottomRightCornerSize)
        updateShapeableImageView()
    }

    private fun updateShapeableImageView() {
        shapeAppearanceModel = shapeAppearanceModel.toBuilder()
            .setTopLeftCorner(cornerFamily, topLeftCornerSize)
            .setTopRightCorner(cornerFamily, topRightCornerSize)
            .setBottomLeftCorner(cornerFamily, bottomLeftCornerSize)
            .setBottomRightCorner(cornerFamily, bottomRightCornerSize)
            .build()
    }
}