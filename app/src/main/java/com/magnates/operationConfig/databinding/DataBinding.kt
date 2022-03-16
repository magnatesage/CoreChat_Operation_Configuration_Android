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

package com.magnates.operationConfig.databinding

import android.content.res.ColorStateList
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.Typeface
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatSpinner
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.magnates.operationConfig.R
import com.magnates.operationConfig.adapter.CustomArrayAdapter
import com.magnates.operationConfig.adapter.FontArrayAdapter
import com.magnates.operationConfig.adapter.IconArrayAdapter
import com.magnates.operationConfig.customviews.*
import com.magnates.operationConfig.utils.AppConstants
import com.magnates.operationConfig.utils.AppPref
import com.magnates.operationConfig.utils.Utils

/**
 * This method is used to set font array in AppCompatSpinner dropdown
 * @param view: AppCompatSpinner
 * @param stringArrayId: Int
 */
@BindingAdapter("fontSpinner")
fun setFontSpinnerDropdown(view: AppCompatSpinner, stringArrayId: Int) {
    val fontArrayAdapter = FontArrayAdapter(
        view.context, 0,
        Utils.getStringArrayFromXML(view.context, stringArrayId).toList()
    )
    view.adapter = fontArrayAdapter
}

/**
 * This method is used to set custom array in AppCompatSpinner dropdown
 * @param view: AppCompatSpinner
 * @param stringArrayId: Int
 */
@BindingAdapter("customSpinner")
fun setCustomSpinnerDropdown(view: AppCompatSpinner, stringArrayId: Int) {
    val customArrayAdapter = CustomArrayAdapter(
        view.context, 0,
        Utils.getStringArrayFromXML(view.context, stringArrayId).toList()
    )
    view.adapter = customArrayAdapter
}

/**
 * This method is used to set custom icon array in AppCompatSpinner dropdown
 * @param view: AppCompatSpinner
 * @param stringArrayId: Int
 */
@BindingAdapter("customIconSpinner")
fun setCustomIconSpinnerDropdown(view: AppCompatSpinner, stringArrayId: Int) {
    val iconArrayAdapter = IconArrayAdapter(
        view.context, 0,
        Utils.getStringArrayFromXML(view.context, stringArrayId).toList()
    )
    view.adapter = iconArrayAdapter
}

/**
 * This method is used to set custom shape to CustomMaterialCardView
 * @param customMaterialCardView: CustomMaterialCardView
 * @param shapeId: String
 * @param dropShadow: Boolean
 */
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

/**
 * This method is used to set custom color to CustomTextView
 * @param customTextView: CustomTextView
 * @param fontColor: String
 */
@BindingAdapter("textCustomColor")
fun setCustomColorToTextView(customTextView: CustomTextView, fontColor: String) {
    customTextView.setTextColor(Color.parseColor(fontColor))
}

/**
 * This method is used to set custom font to CustomTextView
 * @param customTextView: CustomTextView
 * @param fontType: String
 */
@BindingAdapter("textCustomFont")
fun setCustomFontToTextView(customTextView: CustomTextView, fontType: String) {
    customTextView.setCustomFont("$fontType.ttf")
}

/**
 * This method is used to set custom size to CustomTextView
 * @param customTextView: CustomTextView
 * @param fontSize: String
 */
@BindingAdapter("textCustomSize")
fun setCustomSizeToTextView(customTextView: CustomTextView, fontSize: String) {
    Utils.setTextSizeInSSP(customTextView, Utils.getFontSizeInSSP(fontSize))
}

/**
 * This method is used to set custom color to CustomEditText
 * @param customEditText: CustomEditText
 * @param fontColor: String
 */
@BindingAdapter("textCustomColor")
fun setCustomColorToEditText(customEditText: CustomEditText, fontColor: String) {
    customEditText.setTextColor(Color.parseColor(fontColor))
}

/**
 * This method is used to set custom font to CustomEditText
 * @param customEditText: CustomEditText
 * @param fontType: String
 */
@BindingAdapter("textCustomFont")
fun setCustomFontToEditText(customEditText: CustomEditText, fontType: String) {
    customEditText.setCustomFont("$fontType.ttf")
}

/**
 * This method is used to set custom size to CustomEditText
 * @param customEditText: CustomEditText
 * @param fontSize: String
 */
@BindingAdapter("textCustomSize")
fun setCustomSizeToEditText(customEditText: CustomEditText, fontSize: String) {
    Utils.setTextSizeInSSP(customEditText, Utils.getFontSizeInSSP(fontSize))
}

/**
 * This method is used to set custom color to text of TextInputEditText
 * @param textInputEditText: TextInputEditText
 * @param fontColor: String
 */
@BindingAdapter("textCustomColor")
fun setColorToTextInputEditText(textInputEditText: TextInputEditText, fontColor: String){
    textInputEditText.setTextColor(Color.parseColor(fontColor))
}

/**
 * This method is used to set custom font to text of TextInputEditText
 * @param textInputEditText: TextInputEditText
 * @param fontType: String
 */
@BindingAdapter("textCustomFont")
fun setFontToTextInputEditText(textInputEditText: TextInputEditText, fontType: String){
    textInputEditText.typeface = Typeface.createFromAsset(textInputEditText.context.assets,"fonts/$fontType.ttf")
}

/**
 * This method is used to set custom size to text of TextInputEditText
 * @param textInputEditText: TextInputEditText
 * @param fontSize: String
 */
@BindingAdapter("textCustomSize")
fun setSizeToTextInputEditText(textInputEditText: TextInputEditText, fontSize: String){
    Utils.setTextSizeInSSP(textInputEditText, Utils.getFontSizeInSSP(fontSize))
}

/**
 * This method is used to set custom color to CustomButton
 * @param customButton: CustomButton
 * @param fontColor: String
 */
@BindingAdapter("textCustomColor")
fun setCustomColorToButton(customButton: CustomButton, fontColor: String) {
    customButton.setTextColor(Color.parseColor(fontColor))
}

/**
 * This method is used to set custom font to CustomButton
 * @param customButton: CustomButton
 * @param fontType: String
 */
@BindingAdapter("textCustomFont")
fun setCustomFontToButton(customButton: CustomButton, fontType: String) {
    customButton.setCustomFont("$fontType.ttf")
}

/**
 * This method is used to set custom size to CustomButton
 * @param customButton: CustomButton
 * @param fontSize: String
 */
@BindingAdapter("textCustomSize")
fun setCustomSizeToButton(customButton: CustomButton, fontSize: String) {
    Utils.setTextSizeInSSP(customButton, Utils.getFontSizeInSSP(fontSize))
}

/**
 * This method is used to set custom background color to CustomButton
 * @param customButton: CustomButton
 * @param backgroundColor: String
 */
@BindingAdapter("customBackgroundColor")
fun setCustomBackgroundColorToButton(customButton: CustomButton, backgroundColor: String) {
    customButton.setBackgroundColor(Color.parseColor(backgroundColor))
}

/**
 * This method is used to set custom icon color to TextInputLayout
 * @param textInputLayout: TextInputLayout
 * @param iconColor: String
 */
@BindingAdapter("app:endIconTint")
fun setIconColorToTextInputLayout(textInputLayout: TextInputLayout, iconColor: String) {
    textInputLayout.setEndIconTintList(ColorStateList.valueOf(Color.parseColor(iconColor)))
}

/**
 * This method is used to set custom color to CustomMaterialButton
 * @param customMaterialButton: CustomMaterialButton
 * @param fontColor: String
 */
@BindingAdapter("textCustomColor")
fun setColorToButton(customMaterialButton: CustomMaterialButton, fontColor: String){
    customMaterialButton.setTextColor(Color.parseColor(fontColor))
}

/**
 * This method is used to set custom font to CustomMaterialButton
 * @param customMaterialButton: CustomMaterialButton
 * @param fontType: String
 */
@BindingAdapter("textCustomFont")
fun setFontToButton(customMaterialButton: CustomMaterialButton, fontType: String){
    customMaterialButton.setCustomFont("$fontType.ttf")
}

/**
 * This method is used to set custom size to CustomMaterialButton
 * @param customMaterialButton: CustomMaterialButton
 * @param fontSize: String
 */
@BindingAdapter("textCustomSize")
fun setSizeToButton(customMaterialButton: CustomMaterialButton, fontSize: String){
    Utils.setTextSizeInSSP(customMaterialButton, Utils.getFontSizeInSSP(fontSize))
}

/**
 * This method is used to set custom background color to CustomMaterialButton
 * @param customMaterialButton: CustomMaterialButton
 * @param fontColor: String
 */
@BindingAdapter("buttonBackgroundColor")
fun setButtonBackgroundColor(customMaterialButton: CustomMaterialButton, fontColor: String){
    customMaterialButton.setBackgroundColor(Color.parseColor(fontColor))
}

/**
 * This method is used to set image to ImageView
 * @param imageView: ImageView
 * @param imageUrl: String
 */
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

