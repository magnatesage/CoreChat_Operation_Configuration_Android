package com.example.corechatoperationconfigapp.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.corechatoperationconfigapp.R
import com.example.corechatoperationconfigapp.adapter.ChatButtonListAdapter
import com.example.corechatoperationconfigapp.adapter.ChatListAdapter
import com.example.corechatoperationconfigapp.databinding.ActivityChatBinding
import com.example.corechatoperationconfigapp.model.MessageModel
import com.example.corechatoperationconfigapp.utils.AppConstants
import com.example.corechatoperationconfigapp.utils.AppConstants.HORIZONTAL
import com.example.corechatoperationconfigapp.utils.AppPref
import com.example.corechatoperationconfigapp.utils.Extensions.visible
import com.example.corechatoperationconfigapp.utils.Utils
import com.example.corechatoperationconfigapp.utils.Utils.getParsedColorValue
import com.example.corechatoperationconfigapp.utils.Utils.getSizeInSDP

class ChatActivity : BaseActivity() {
    private lateinit var binding: ActivityChatBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun init() {
        binding.headerLayout.tvHeader.text = getString(R.string.chat_screen)
        binding.headerLayout.tvHeader.setCustomFont("${Utils.dynamicUIModel?.fontFamily}.ttf")
        Utils.setTextSizeInSSP(
            binding.headerLayout.tvHeader,
            Utils.getFontSizeInSSP(Utils.dynamicUIModel?.fontSize?.titleHeader!!)
        )
        binding.headerLayout.tvHeader.setTextColor(Color.parseColor(Utils.dynamicUIModel?.themeColor?.primaryColor))
        binding.headerLayout.toolbar.setBackgroundColor(Color.parseColor(Utils.dynamicUIModel?.themeColor?.secondaryColor))

        setBackgroundOfRecyclerView()

        val dummyData = "Lorem ipsum dolor sit amet"
        val chatList = ArrayList<MessageModel>()
        chatList.add(MessageModel(dummyData, isSender = false, isCardView = false))
        chatList.add(MessageModel(dummyData, isSender = false, isCardView = false))
        chatList.add(MessageModel(dummyData, isSender = true, isCardView = false))

        binding.rvChatList.apply {
            layoutManager = LinearLayoutManager(this@ChatActivity)
        }

        val chatListAdapter = ChatListAdapter(this, chatList)
        binding.rvChatList.adapter = chatListAdapter

        if (intent.extras != null) {
            if (intent.getBooleanExtra(AppConstants.FROM_CHAT_BUTTON_CONFIG, false)) {
                binding.rlButtonList.visible()
                if (Utils.dynamicUIModel?.chat?.button?.buttonPlacementStyle == HORIZONTAL) {
                    binding.rvButtonList.apply {
                        layoutManager =
                            LinearLayoutManager(
                                this@ChatActivity,
                                LinearLayoutManager.HORIZONTAL,
                                false
                            )
                    }
                } else {
                    val layoutParams = RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.MATCH_PARENT,
                        getSizeInSDP(this, R.dimen._150sdp)
                    )

                    binding.rvButtonList.layoutParams = layoutParams
                    binding.rvButtonList.apply {
                        layoutManager = LinearLayoutManager(this@ChatActivity)
                    }
                }
                val chatButtonListAdapter = ChatButtonListAdapter(this)
                binding.rvButtonList.adapter = chatButtonListAdapter
            } else if (intent.getBooleanExtra(AppConstants.FROM_CHAT_CARDVIEW_CONFIG, false)) {
                chatList.add(MessageModel(dummyData, isSender = false, isCardView = true))
                chatListAdapter.notifyDataSetChanged()
            } else if (intent.getBooleanExtra(
                    AppConstants.FROM_CHAT_CONVERSATION_BAR_CONFIG,
                    false
                )
            ) {
                when (Utils.dynamicUIModel?.chat?.conversationBar?.conversationBarShape) {
                    AppConstants.ROUNDED -> {
                        binding.llRounded.visible()
                        loadFloatingIconUrl(binding.ivRoundedFlash)
                    }
                    AppConstants.SQUARE -> {
                        binding.llSquare.visible()
                        loadFloatingIconUrl(binding.ivSquareFlash)
                    }
                    AppConstants.SEMI_ROUNDED -> {
                        binding.llSemiRounded.visible()
                        loadFloatingIconUrl(binding.ivSemiRoundedFlash)
                    }
                    AppConstants.OVAL -> {
                        binding.llOval.visible()
                        loadFloatingIconUrl(binding.ivOvalFlash)
                    }
                    AppConstants.CIRCLE -> {
                        binding.llCircle.visible()
                        loadFloatingIconUrl(binding.ivCircleFlash)
                    }
                }
            }
        }
    }

    private fun loadFloatingIconUrl(imageView: ImageView) {
        val imageFilePath = AppPref.getValue(
            this,
            AppConstants.FLOATING_ICON_IMAGE_PATH, ""
        )
        if (imageFilePath?.isNotBlank() == true) {
            val bitmap = BitmapFactory.decodeFile(imageFilePath)
            imageView.setImageBitmap(bitmap)
        } else if (!Utils.dynamicUIModel?.chat?.conversationBar?.floatingIconUrl.isNullOrEmpty()) {
            Glide.with(this)
                .load(Utils.dynamicUIModel?.chat?.conversationBar?.floatingIconUrl)
                .placeholder(R.drawable.flash_small)
                .into(imageView)
        }
    }

    /**
     * This method is used to set background Color or Image to Recyclerview of Chat list.
     */
    @SuppressLint("CheckResult")
    private fun setBackgroundOfRecyclerView() {
        if (Utils.dynamicUIModel?.chat?.chatBubble?.chatScreenBgType == AppConstants.COLOR) {
            binding.rvChatList.setBackgroundColor(getParsedColorValue(Utils.dynamicUIModel?.chat?.chatBubble?.chatScreenBgColor!!))
        } else {
            if (!Utils.dynamicUIModel?.chat?.chatBubble?.chatScreenBgImageUrl.isNullOrEmpty()) {
                Glide.with(this)
                    .load(Utils.dynamicUIModel?.chat?.chatBubble?.chatScreenBgImageUrl)
                    .into(object : CustomTarget<Drawable>() {
                        override fun onResourceReady(
                            resource: Drawable,
                            transition: Transition<in Drawable>?
                        ) {
                            binding.rvChatList.background = resource
                        }

                        override fun onLoadCleared(placeholder: Drawable?) {
                        }
                    })
            } else {
                val imageFilePath = AppPref.getValue(
                    this,
                    AppConstants.CHAT_BOT_BG_IMAGE_PATH, ""
                )
                if (imageFilePath?.isNotBlank() == true) {
                    val drawable = BitmapDrawable(resources, imageFilePath)
                    binding.rvChatList.background = drawable
                }
            }
        }
    }

    override fun onClick(view: View) {
        when (view) {
            binding.btnBack -> onBackPressed()

            binding.btnNext -> {
                if (intent.extras != null) {
                    if (intent.getBooleanExtra(AppConstants.FROM_CHAT_BUTTON_CONFIG, false)) {
                        startActivity(Intent(this, ChatCardViewConfigActivity::class.java))
                    } else if (intent.getBooleanExtra(AppConstants.FROM_CHAT_CARDVIEW_CONFIG, false)) {
                        startActivity(Intent(this, ChatConversationBarConfigActivity::class.java))
                    } else {
                        startActivity(Intent(this, ChatButtonConfigActivity::class.java))
                    }
                } else {
                    startActivity(Intent(this, ChatButtonConfigActivity::class.java))
                }
            }
        }
    }
}