package com.example.corechatoperationconfigapp.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.corechatoperationconfigapp.databinding.ActivityLoginBinding
import com.example.corechatoperationconfigapp.utils.Utils


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