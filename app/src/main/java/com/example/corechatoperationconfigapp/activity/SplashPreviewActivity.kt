package com.example.corechatoperationconfigapp.activity

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.corechatoperationconfigapp.databinding.ActivitySplashPreviewBinding
import com.example.corechatoperationconfigapp.utils.AppConstants
import com.example.corechatoperationconfigapp.utils.AppPref
import com.example.corechatoperationconfigapp.utils.Utils

class SplashPreviewActivity : BaseActivity() {
    private lateinit var binding: ActivitySplashPreviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashPreviewBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun init() {
        binding.dynamicUIModel = Utils.dynamicUIModel

        if (Utils.dynamicUIModel?.splash?.splashScreenBgType == AppConstants.COLOR) {
            binding.rlMain.setBackgroundColor(Color.parseColor(Utils.dynamicUIModel?.splash?.splashScreenBgColor))
        } else {
            if (!Utils.dynamicUIModel?.splash?.splashScreenBgImageUrl.isNullOrEmpty()) {
                Glide.with(this)
                    .load(Utils.dynamicUIModel?.splash?.splashScreenBgImageUrl)
                    .into(object : CustomTarget<Drawable>() {
                        override fun onResourceReady(
                            resource: Drawable,
                            transition: Transition<in Drawable>?
                        ) {
                            binding.rlMain.background = resource
                        }

                        override fun onLoadCleared(placeholder: Drawable?) {
                        }
                    })
            } else {
                val imageFilePath = AppPref.getValue(
                    this,
                    AppConstants.SPLASH_SCREEN_BG_IMAGE_PATH, ""
                )
                if (imageFilePath?.isNotBlank() == true) {
                    val drawable = BitmapDrawable(resources, imageFilePath)
                    binding.rlMain.background = drawable
                }
            }
        }
    }

    override fun onClick(view: View) {
        when (view) {
            binding.btnBack -> onBackPressed()

            binding.btnNext -> startActivity(Intent(this, ColorFontSizeConfigActivity::class.java))
        }
    }

}