package com.magnates.operationConfig.activity

import android.content.Context
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.View
import com.magnates.operationConfig.databinding.ActivityLoginBinding
import com.magnates.operationConfig.utils.AppConstants
import com.magnates.operationConfig.utils.AppPref
import com.magnates.operationConfig.utils.Utils


class LoginActivity : BaseActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    /**
     * This method is used to initialization process of activity
     */
    override fun init() {
        context = this@LoginActivity
        binding.dynamicModel = Utils.dynamicUIModel
        val splashScreenLogo = AppPref.getValue(context,AppConstants.SPLASH_SCREEN_LOGO_IMAGE_PATH,"")
        if (splashScreenLogo?.isNotBlank() == true){
            val drawable = BitmapDrawable(resources,splashScreenLogo)
            binding.ivLogo.background = drawable
        }
    }

    /**
     * This method is called when user clicks on view
     */
    override fun onClick(view: View) {
        when (view) {
            binding.btnBack -> {
                onBackPressed()
            }

            binding.btnNext -> {
                startActivity(Intent(context,HomeActivity::class.java))
            }
        }
    }
}