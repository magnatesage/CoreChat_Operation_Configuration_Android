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

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.google.gson.GsonBuilder
import com.magnates.operationConfig.R
import com.magnates.operationConfig.adapter.ChatButtonListAdapter
import com.magnates.operationConfig.adapter.ChatListAdapter
import com.magnates.operationConfig.databinding.ActivityChatBinding
import com.magnates.operationConfig.model.MessageModel
import com.magnates.operationConfig.utils.AppConstants
import com.magnates.operationConfig.utils.AppConstants.CHAT_BOT_BG_IMAGE_PATH
import com.magnates.operationConfig.utils.AppConstants.FLOATING_ICON_IMAGE_PATH
import com.magnates.operationConfig.utils.AppConstants.HORIZONTAL
import com.magnates.operationConfig.utils.AppConstants.SPLASH_SCREEN_BG_IMAGE_PATH
import com.magnates.operationConfig.utils.AppConstants.SPLASH_SCREEN_LOGO_IMAGE_PATH
import com.magnates.operationConfig.utils.AppPref
import com.magnates.operationConfig.utils.Extensions.visible
import com.magnates.operationConfig.utils.ImageCopyHelperClass
import com.magnates.operationConfig.utils.Utils
import com.magnates.operationConfig.utils.Utils.dynamicUIModel
import com.magnates.operationConfig.utils.Utils.getParsedColorValue
import com.magnates.operationConfig.utils.Utils.getSizeInSDP
import java.io.File

class ChatActivity : BaseActivity() {
    private lateinit var binding: ActivityChatBinding
    private lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    /**
     * This method is used to initialization process of activity
     */
    override fun init() {
        context = this@ChatActivity
        binding.headerLayout.tvHeader.text = getString(R.string.chat_screen)
        binding.headerLayout.tvHeader.setCustomFont("${dynamicUIModel?.fontFamily}.ttf")
        Utils.setTextSizeInSSP(
            binding.headerLayout.tvHeader,
            Utils.getFontSizeInSSP(dynamicUIModel?.fontSize?.titleHeader!!)
        )
        binding.headerLayout.tvHeader.setTextColor(Color.parseColor(dynamicUIModel?.themeColor?.primaryColor))
        binding.headerLayout.toolbar.setBackgroundColor(Color.parseColor(dynamicUIModel?.themeColor?.secondaryColor))

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
                if (dynamicUIModel?.chat?.button?.buttonPlacementStyle == HORIZONTAL) {
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
                binding.btnNext.text = getString(R.string.save)
                binding.headerLayout.ivExport.visibility = View.VISIBLE
                when (dynamicUIModel?.chat?.conversationBar?.conversationBarShape) {
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

    /**
     * This method is used to load floating icon url
     */
    private fun loadFloatingIconUrl(imageView: ImageView) {
        val imageFilePath = AppPref.getValue(
            this,
            FLOATING_ICON_IMAGE_PATH,""
        )
        if (imageFilePath?.isNotBlank() == true) {
            val bitmap = BitmapFactory.decodeFile(imageFilePath)
            imageView.setImageBitmap(bitmap)
        } else if (!dynamicUIModel?.chat?.conversationBar?.floatingIconUrl.isNullOrEmpty()) {
            Glide.with(this)
                .load(dynamicUIModel?.chat?.conversationBar?.floatingIconUrl)
                .placeholder(R.drawable.flash_small)
                .into(imageView)
        }
    }

    /**
     * This method is used to set background Color or Image to Recyclerview of Chat list.
     */
    @SuppressLint("CheckResult")
    private fun setBackgroundOfRecyclerView() {
        if (dynamicUIModel?.chat?.chatBubble?.chatScreenBgType == AppConstants.COLOR) {
            binding.rvChatList.setBackgroundColor(getParsedColorValue(dynamicUIModel?.chat?.chatBubble?.chatScreenBgColor!!))
        } else {
            if (!dynamicUIModel?.chat?.chatBubble?.chatScreenBgImageUrl.isNullOrEmpty()) {
                Glide.with(this)
                    .load(dynamicUIModel?.chat?.chatBubble?.chatScreenBgImageUrl)
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
                    CHAT_BOT_BG_IMAGE_PATH,""
                )
                if (imageFilePath?.isNotBlank() == true) {
                    val drawable = BitmapDrawable(resources, imageFilePath)
                    binding.rvChatList.background = drawable
                }
            }
        }
    }

    /**
     * This method is used when user clicks on view.
     */
    override fun onClick(view: View) {
        when (view) {
            binding.btnBack -> onBackPressed()

            binding.btnNext -> {
                if (intent.extras != null) {
                    if (intent.getBooleanExtra(AppConstants.FROM_CHAT_BUTTON_CONFIG, false)) {
                        startActivity(Intent(this, ChatCardViewConfigActivity::class.java))
                    } else if (intent.getBooleanExtra(
                            AppConstants.FROM_CHAT_CARDVIEW_CONFIG,
                            false
                        )
                    ) {
                        startActivity(Intent(this, ChatConversationBarConfigActivity::class.java))
                    } else if (intent.getBooleanExtra(
                            AppConstants.FROM_CHAT_CONVERSATION_BAR_CONFIG,
                            false
                        )) { }
                } else {
                    startActivity(Intent(this, ChatButtonConfigActivity::class.java))
                }
            }
            binding.headerLayout.ivExport -> {
                exportJsonAndImageData()
            }
        }
    }

    /**
     * This method is used to export the save JSON data and images in text file format.
     */
    @SuppressLint("ObsoleteSdkInt")
    private fun exportJsonAndImageData() {
        val fileArrayList : ArrayList<Uri> = ArrayList()

        //Convert Java object into Json
        val builder = GsonBuilder()
        val json = builder.setPrettyPrinting().create()
        val response = json.toJson(dynamicUIModel)

        val storageDir = context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)
        val mFileName = "$storageDir/jsonData.txt"

        val jsonDataFile = File(mFileName)
        jsonDataFile.writeText(response)

        val splashScreenLogoImage = AppPref.getValue(context,SPLASH_SCREEN_LOGO_IMAGE_PATH,"")
        val splashScreenBgImage = AppPref.getValue(context,SPLASH_SCREEN_BG_IMAGE_PATH,"")
        val floatingIconImage = AppPref.getValue(context,FLOATING_ICON_IMAGE_PATH,"")
        val chatBotBgImage = AppPref.getValue(context,CHAT_BOT_BG_IMAGE_PATH,"")

        if(splashScreenLogoImage != ""){
            val splashScreenLogoImageFile = File(splashScreenLogoImage)
            fileArrayList.add(
                ImageCopyHelperClass.getUriOfFile(context,splashScreenLogoImageFile)
            )
        }

        if(splashScreenBgImage != ""){
            val splashScreenBgImageFile = File(splashScreenBgImage)
            fileArrayList.add(
                ImageCopyHelperClass.getUriOfFile(context,splashScreenBgImageFile)
            )
        }

        if(floatingIconImage != ""){
            val floatingIconImageFile = File(floatingIconImage)
            fileArrayList.add(
                ImageCopyHelperClass.getUriOfFile(context,floatingIconImageFile)
            )
        }

        if(chatBotBgImage != ""){
            val chatBotBgImageFile = File(chatBotBgImage)
            fileArrayList.add(
                ImageCopyHelperClass.getUriOfFile(context,chatBotBgImageFile)
            )
        }

        fileArrayList.add(ImageCopyHelperClass.getUriOfFile(context,jsonDataFile))

        val shareIntent = Intent(Intent.ACTION_SEND_MULTIPLE)
        shareIntent.type = "*/*"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }
        shareIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, fileArrayList)
        startActivity(Intent.createChooser(shareIntent, getString(R.string.share_using)))
    }
}