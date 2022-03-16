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

package com.magnates.operationConfig.activity

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.magnates.operationConfig.databinding.ActivitySplashPreviewBinding
import com.magnates.operationConfig.utils.AppConstants
import com.magnates.operationConfig.utils.AppPref
import com.magnates.operationConfig.utils.Utils

class SplashPreviewActivity : BaseActivity() {
    private lateinit var binding: ActivitySplashPreviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashPreviewBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    /**
     * This method is used to initialization process of activity
     */
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

    /**
     * This method is called when user clicks on view
     */
    override fun onClick(view: View) {
        when (view) {
            binding.btnBack -> onBackPressed()

            binding.btnNext -> startActivity(Intent(this, ColorFontSizeConfigActivity::class.java))
        }
    }

}