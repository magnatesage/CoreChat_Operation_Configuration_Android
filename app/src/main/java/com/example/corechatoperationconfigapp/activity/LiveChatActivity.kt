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