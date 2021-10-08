package com.example.corechatoperationconfigapp.activity

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
import com.example.corechatoperationconfigapp.R
import com.example.corechatoperationconfigapp.adapter.ChatButtonListAdapter
import com.example.corechatoperationconfigapp.adapter.ChatListAdapter
import com.example.corechatoperationconfigapp.databinding.ActivityChatBinding
import com.example.corechatoperationconfigapp.model.MessageModel
import com.example.corechatoperationconfigapp.utils.*
import com.example.corechatoperationconfigapp.utils.AppConstants.CHAT_BOT_BG_IMAGE_PATH
import com.example.corechatoperationconfigapp.utils.AppConstants.FLOATING_ICON_IMAGE_PATH
import com.example.corechatoperationconfigapp.utils.AppConstants.HORIZONTAL
import com.example.corechatoperationconfigapp.utils.AppConstants.SPLASH_SCREEN_BG_IMAGE_PATH
import com.example.corechatoperationconfigapp.utils.AppConstants.SPLASH_SCREEN_LOGO_IMAGE_PATH
import com.example.corechatoperationconfigapp.utils.Extensions.visible
import com.example.corechatoperationconfigapp.utils.Utils.dynamicUIModel
import com.example.corechatoperationconfigapp.utils.Utils.getParsedColorValue
import com.example.corechatoperationconfigapp.utils.Utils.getSizeInSDP
import com.google.gson.GsonBuilder
import org.json.JSONObject
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