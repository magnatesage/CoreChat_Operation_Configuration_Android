package com.example.corechatoperationconfigapp.activity

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import com.example.corechatoperationconfigapp.R
import com.example.corechatoperationconfigapp.adapter.LiveChatListAdapter
import com.example.corechatoperationconfigapp.databinding.ActivityLiveChatBinding
import com.example.corechatoperationconfigapp.utils.Utils

class LiveChatActivity : BaseActivity(), View.OnClickListener {

    private lateinit var binding: ActivityLiveChatBinding
    private lateinit var liveChatListAdapter: LiveChatListAdapter
    private lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLiveChatBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    /**
     * This method is called when device change orientation
     */
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        setContentView(binding.root)
    }

    /**
     * This method is used to initialization process of activity
     */
    override fun init() {
        context = this@LiveChatActivity
        binding.dynamicUIModel = Utils.dynamicUIModel
        binding.toolbarLayout.toolbarTitle.text = getString(R.string.live_chat)
        liveChatListAdapter = LiveChatListAdapter(
            context as LiveChatActivity,
            arrayOf(
                "User 1",
                "User 2",
                "User 3",
                "User 4",
                "User 5",
                "User 6",
                "User 7",
                "User 8",
                "User 9",
                "User 10"
            ).toList()
        )
        binding.rvLiveChatList.apply {
            layoutManager = Utils.getVerticalLayoutManager(context)
            adapter = liveChatListAdapter
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
                startActivity(Intent(context, ChatBubbleConfigActivity::class.java))
            }
        }
    }
}