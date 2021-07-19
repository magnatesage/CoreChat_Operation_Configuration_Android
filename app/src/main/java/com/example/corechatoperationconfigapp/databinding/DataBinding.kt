package com.example.corechatoperationconfigapp.databinding

import android.content.res.ColorStateList
import android.graphics.BitmapFactory
import android.graphics.Color
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatSpinner
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.corechatoperationconfigapp.R
import com.example.corechatoperationconfigapp.adapter.CustomArrayAdapter
import com.example.corechatoperationconfigapp.adapter.FontArrayAdapter
import com.example.corechatoperationconfigapp.adapter.IconArrayAdapter
import com.example.corechatoperationconfigapp.customviews.CustomButton
import com.example.corechatoperationconfigapp.customviews.CustomEditText
import com.example.corechatoperationconfigapp.customviews.CustomMaterialCardView
import com.example.corechatoperationconfigapp.customviews.CustomTextView
import com.example.corechatoperationconfigapp.utils.AppConstants
import com.example.corechatoperationconfigapp.utils.AppPref
import com.example.corechatoperationconfigapp.utils.Utils
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout


@BindingAdapter("fontSpinner")
fun setFontSpinnerDropdown(view: AppCompatSpinner, stringArrayId: Int) {
    val fontArrayAdapter = FontArrayAdapter(
        view.context, 0,
        Utils.getStringArrayFromXML(view.context, stringArrayId).toList()
    )
    view.adapter = fontArrayAdapter
}

@BindingAdapter("customSpinner")
fun setCustomSpinnerDropdown(view: AppCompatSpinner, stringArrayId: Int) {
    val customArrayAdapter = CustomArrayAdapter(
        view.context, 0,
        Utils.getStringArrayFromXML(view.context, stringArrayId).toList()
    )
    view.adapter = customArrayAdapter
}

@BindingAdapter("customIconSpinner")
fun setCustomIconSpinnerDropdown(view: AppCompatSpinner, stringArrayId: Int) {
    val iconArrayAdapter = IconArrayAdapter(
        view.context, 0,
        Utils.getStringArrayFromXML(view.context, stringArrayId).toList()
    )
    view.adapter = iconArrayAdapter
}

@BindingAdapter("shape", "shadow")
fun setCustomShapeToCardView(
    customMaterialCardView: CustomMaterialCardView,
    shapeId: String,
    dropShadow: Boolean
) {
    when (shapeId) {
        AppConstants.SHAPE_ROUNDED_CORNER -> {
            customMaterialCardView.setAllCorners(
                Utils.getSizeInSDP(
                    customMaterialCardView.context,
                    R.dimen._16sdp
                ).toFloat()
            )
        }
        AppConstants.SHAPE_CIRCLE -> {
            customMaterialCardView.setAllCornersInPercent(0.5F)
        }
    }

    if (dropShadow) {
        customMaterialCardView.cardElevation =
            Utils.getSizeInSDP(customMaterialCardView.context, R.dimen._10sdp).toFloat()
    }
}

@BindingAdapter("textCustomColor")
fun setCustomColorToTextView(customTextView: CustomTextView, fontColor: String) {
    customTextView.setTextColor(Color.parseColor(fontColor))
}

@BindingAdapter("textCustomFont")
fun setCustomFontToTextView(customTextView: CustomTextView, fontType: String) {
    customTextView.setCustomFont("$fontType.ttf")
}

@BindingAdapter("textCustomSize")
fun setCustomSizeToTextView(customTextView: CustomTextView, fontSize: String) {
    Utils.setTextSizeInSSP(customTextView, Utils.getFontSizeInSSP(fontSize))
}

@BindingAdapter("textCustomColor")
fun setCustomColorToEditText(customEditText: CustomEditText, fontColor: String) {
    customEditText.setTextColor(Color.parseColor(fontColor))
}

@BindingAdapter("textCustomFont")
fun setCustomFontToEditText(customEditText: CustomEditText, fontType: String) {
    customEditText.setCustomFont("$fontType.ttf")
}

@BindingAdapter("textCustomSize")
fun setCustomSizeToEditText(customEditText: CustomEditText, fontSize: String) {
    Utils.setTextSizeInSSP(customEditText, Utils.getFontSizeInSSP(fontSize))
}

@BindingAdapter("textCustomSize")
fun setCustomSizeToTextInputEditText(textInputEditText: TextInputEditText, fontSize: String) {
    Utils.setTextSizeInSSP(textInputEditText, Utils.getFontSizeInSSP(fontSize))
}

@BindingAdapter("textCustomColor")
fun setCustomColorToButton(customButton: CustomButton, fontColor: String) {
    customButton.setTextColor(Color.parseColor(fontColor))
}

@BindingAdapter("textCustomFont")
fun setCustomFontToButton(customButton: CustomButton, fontType: String) {
    customButton.setCustomFont("$fontType.ttf")
}

@BindingAdapter("textCustomSize")
fun setCustomSizeToButton(customButton: CustomButton, fontSize: String) {
    Utils.setTextSizeInSSP(customButton, Utils.getFontSizeInSSP(fontSize))
}

@BindingAdapter("customBackgroundColor")
fun setCustomBackgroundColorToButton(customButton: CustomButton, fontColor: String) {
    customButton.setBackgroundColor(Color.parseColor(fontColor))
}

@BindingAdapter("app:endIconTint")
fun setIconColorToTextInputLayout(textInputLayout: TextInputLayout, iconColor: String) {
    textInputLayout.setEndIconTintList(ColorStateList.valueOf(Color.parseColor(iconColor)))
}


@BindingAdapter("loadImage")
fun loadImageToImageView(imageView: ImageView, imageUrl: String) {
    if (imageUrl.isNotBlank()) {
        Glide.with(imageView.context)
            .load(imageUrl)
            .placeholder(R.drawable.upload)
            .error(R.drawable.upload)
            .into(imageView)
    } else {
        val imageFilePath = AppPref.getValue(
            imageView.context,
            AppConstants.SPLASH_SCREEN_LOGO_IMAGE_PATH, ""
        )
        if (imageFilePath?.isNotBlank() == true) {
            val bitmap = BitmapFactory.decodeFile(imageFilePath)
            imageView.setImageBitmap(bitmap)
        }
    }
}

