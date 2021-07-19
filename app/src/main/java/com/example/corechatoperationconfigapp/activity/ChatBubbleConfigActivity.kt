package com.example.corechatoperationconfigapp.activity

import android.Manifest
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.LinearLayout
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.example.corechatoperationconfigapp.R
import com.example.corechatoperationconfigapp.adapter.ChatBubbleStylesAdapter
import com.example.corechatoperationconfigapp.customviews.colorpicker.AmbilWarnaDialog
import com.example.corechatoperationconfigapp.databinding.ActivityChatBubbleConfigBinding
import com.example.corechatoperationconfigapp.utils.AppConstants
import com.example.corechatoperationconfigapp.utils.AppPref
import com.example.corechatoperationconfigapp.utils.Extensions.gone
import com.example.corechatoperationconfigapp.utils.Extensions.showToast
import com.example.corechatoperationconfigapp.utils.Extensions.visible
import com.example.corechatoperationconfigapp.utils.ImageCopyHelperClass
import com.example.corechatoperationconfigapp.utils.Utils
import com.soundcloud.android.crop.Crop
import java.io.File

class ChatBubbleConfigActivity : BaseActivity(), AdapterView.OnItemSelectedListener {
    private lateinit var binding: ActivityChatBubbleConfigBinding
    private lateinit var chatBubbleStylesAdapter: ChatBubbleStylesAdapter
    private var mFileName = ""
    private var imageFile: File? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBubbleConfigBinding.inflate(layoutInflater)
        setContentView(binding.root)
        requiredPermissions(
            arrayOf(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA
            )
        )
    }

    override fun init() {
        getViewPermissionInterfaceInstance(this)

        binding.headerLayout.tvHeader.text = getString(R.string.chat_bubble_configuration)
        binding.spinnerBgType.onItemSelectedListener = this
        binding.selectBgColorLayout.cvSelectColor.gone()

        binding.rvChatBubble.apply {
            layoutManager = GridLayoutManager(context, 2)
        }

        chatBubbleStylesAdapter = ChatBubbleStylesAdapter(this)
        binding.rvChatBubble.adapter = chatBubbleStylesAdapter

        binding.swDropShadow.setOnCheckedChangeListener { _, isChecked ->
            Utils.dynamicUIModel?.chat?.chatBubble?.cardBgDropShadow = isChecked
        }

        binding.root.post {
            setValuesToViews()
        }
    }

    private fun setValuesToViews() {
        if (!Utils.dynamicUIModel?.chat?.chatBubble?.chatScreenBgType.isNullOrBlank()) {
            Utils.dynamicUIModel?.chat?.chatBubble?.chatScreenBgType?.let {
                Utils.getIndex(
                    Utils.getStringArrayFromXML(this, R.array.dropdown_bg_array),
                    it
                )
            }?.let { binding.spinnerBgType.setSelection(it) }
        }

        if (!Utils.dynamicUIModel?.chat?.chatBubble?.chatScreenBgImageUrl.isNullOrBlank()) {
            Utils.loadImageURL(
                this,
                Utils.dynamicUIModel?.chat?.chatBubble?.chatScreenBgImageUrl!!,
                binding.selectBgImageLayout.uploadImage,
                R.drawable.upload
            )
        }

        if (Utils.dynamicUIModel?.chat?.chatBubble?.cardBgDropShadow != null) {
            binding.swDropShadow.isChecked =
                Utils.dynamicUIModel?.chat?.chatBubble?.cardBgDropShadow!!
        }

        if (!Utils.dynamicUIModel?.chat?.chatBubble?.chatScreenBgColor.isNullOrBlank()) {
            Utils.changeBg(
                binding.selectBgColorLayout.displayColorView,
                Utils.dynamicUIModel?.chat?.chatBubble?.chatScreenBgColor!!
            )
        }

        if (!Utils.dynamicUIModel?.chat?.chatBubble?.senderChatBubbleColor.isNullOrBlank()) {
            Utils.changeBg(
                binding.senderChatBubbleColorLayout.displayColorView,
                Utils.dynamicUIModel?.chat?.chatBubble?.senderChatBubbleColor!!
            )
        }

        if (!Utils.dynamicUIModel?.chat?.chatBubble?.senderTextColor.isNullOrBlank()) {
            Utils.changeBg(
                binding.senderTextColorLayout.displayColorView,
                Utils.dynamicUIModel?.chat?.chatBubble?.senderTextColor!!
            )
        }

        if (!Utils.dynamicUIModel?.chat?.chatBubble?.receiverChatBubbleColor.isNullOrBlank()) {
            Utils.changeBg(
                binding.receiverChatBubbleColorLayout.displayColorView,
                Utils.dynamicUIModel?.chat?.chatBubble?.receiverChatBubbleColor!!
            )
        }

        if (!Utils.dynamicUIModel?.chat?.chatBubble?.receiverTextColor.isNullOrBlank()) {
            Utils.changeBg(
                binding.receiverTextColorLayout.displayColorView,
                Utils.dynamicUIModel?.chat?.chatBubble?.receiverTextColor!!
            )
        }

        if (Utils.dynamicUIModel?.chat?.chatBubble?.chatBubbleStyle != null &&
            Utils.dynamicUIModel?.chat?.chatBubble?.chatBubbleStyle != 0
        ) {
            binding.rvChatBubble.smoothScrollToPosition(
                chatBubbleStylesAdapter.setAndGetSelectedChatBubble(Utils.dynamicUIModel?.chat?.chatBubble?.chatBubbleStyle!!)
            )
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when (parent) {
            binding.spinnerBgType -> {
                when (position) {
                    0 -> {
                        Utils.dynamicUIModel?.chat?.chatBubble?.chatScreenBgType = ""
                        binding.selectBgColorLayout.cvSelectColor.gone()
                        binding.llUploadImage.gone()
                    }

                    1 -> {
                        Utils.dynamicUIModel?.chat?.chatBubble?.chatScreenBgType =
                            parent.getItemAtPosition(position).toString()
                        binding.selectBgColorLayout.cvSelectColor.visible()
                        binding.llUploadImage.gone()
                    }

                    2 -> {
                        Utils.dynamicUIModel?.chat?.chatBubble?.chatScreenBgType =
                            parent.getItemAtPosition(position).toString()
                        binding.selectBgColorLayout.cvSelectColor.gone()
                        binding.llUploadImage.visible()
                    }
                }
            }
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }

    override fun onClick(view: View) {
        when (view) {
            binding.selectBgColorLayout.llSelectColor -> {
                callColorPickerDialog(
                    Utils.getColorFromView(binding.selectBgColorLayout.displayColorView),
                    binding.selectBgColorLayout.llSelectColor
                )
            }

            binding.senderChatBubbleColorLayout.llSelectColor -> {
                callColorPickerDialog(
                    Utils.getColorFromView(binding.senderChatBubbleColorLayout.displayColorView),
                    binding.senderChatBubbleColorLayout.llSelectColor
                )
            }

            binding.senderTextColorLayout.llSelectColor -> {
                callColorPickerDialog(
                    Utils.getColorFromView(binding.senderTextColorLayout.displayColorView),
                    binding.senderTextColorLayout.llSelectColor
                )
            }

            binding.receiverChatBubbleColorLayout.llSelectColor -> {
                callColorPickerDialog(
                    Utils.getColorFromView(binding.receiverChatBubbleColorLayout.displayColorView),
                    binding.receiverChatBubbleColorLayout.llSelectColor
                )
            }

            binding.receiverTextColorLayout.llSelectColor -> {
                callColorPickerDialog(
                    Utils.getColorFromView(binding.receiverTextColorLayout.displayColorView),
                    binding.receiverTextColorLayout.llSelectColor
                )
            }

            binding.selectBgImageLayout.uploadImage -> {
                mFileName = AppConstants.CHAT_BOT_BG_IMAGE
                getPermissions()
            }

            binding.btnBack -> onBackPressed()

            binding.btnNext -> {
                if (Utils.dynamicUIModel?.chat?.chatBubble?.chatBubbleStyle == null ||
                    Utils.dynamicUIModel?.chat?.chatBubble?.chatBubbleStyle == 0
                ) {
                    showToast(resources.getString(R.string.error_message_select_chat_bubble_background))
                } else if (Utils.dynamicUIModel?.chat?.chatBubble?.chatScreenBgType.isNullOrBlank()) {
                    showToast(resources.getString(R.string.error_message_select_chat_bot_background_type))
                } else if (Utils.dynamicUIModel?.chat?.chatBubble?.chatScreenBgType == AppConstants.IMAGE &&
                    binding.selectBgImageLayout.uploadImage.drawable.constantState == ResourcesCompat.getDrawable(
                        resources,
                        R.drawable.upload,
                        null
                    )?.constantState
                ) {
                    showToast(resources.getString(R.string.error_message_select_chat_bot_background_image))
                } else {
                    if (Utils.dynamicUIModel?.chat?.chatBubble?.chatScreenBgColor.isNullOrBlank()) {
                        Utils.dynamicUIModel?.chat?.chatBubble?.chatScreenBgColor = String.format(
                            "#%08x",
                            Utils.getColorFromView(binding.selectBgColorLayout.displayColorView)
                        )
                    }

                    if (Utils.dynamicUIModel?.chat?.chatBubble?.chatScreenBgType == AppConstants.IMAGE) {
                        Utils.dynamicUIModel?.chat?.chatBubble?.chatScreenBgColor = ""
                    }

                    if (Utils.dynamicUIModel?.chat?.chatBubble?.senderChatBubbleColor.isNullOrBlank()) {
                        Utils.dynamicUIModel?.chat?.chatBubble?.senderChatBubbleColor =
                            String.format(
                                "#%08x",
                                Utils.getColorFromView(binding.senderChatBubbleColorLayout.displayColorView)
                            )
                    }

                    if (Utils.dynamicUIModel?.chat?.chatBubble?.senderTextColor.isNullOrBlank()) {
                        Utils.dynamicUIModel?.chat?.chatBubble?.senderTextColor = String.format(
                            "#%08x",
                            Utils.getColorFromView(binding.senderTextColorLayout.displayColorView)
                        )
                    }

                    if (Utils.dynamicUIModel?.chat?.chatBubble?.receiverChatBubbleColor.isNullOrBlank()) {
                        Utils.dynamicUIModel?.chat?.chatBubble?.receiverChatBubbleColor =
                            String.format(
                                "#%08x",
                                Utils.getColorFromView(binding.receiverChatBubbleColorLayout.displayColorView)
                            )
                    }

                    if (Utils.dynamicUIModel?.chat?.chatBubble?.receiverTextColor.isNullOrBlank()) {
                        Utils.dynamicUIModel?.chat?.chatBubble?.receiverTextColor = String.format(
                            "#%08x",
                            Utils.getColorFromView(binding.receiverTextColorLayout.displayColorView)
                        )
                    }
                    startActivity(Intent(this, ChatActivity::class.java))
                }
            }
        }
    }

    /**
     * This method is called to open color picker dialog
     */
    private fun callColorPickerDialog(viewColor: Int, linearLayout: LinearLayout) {
        val colorPickerDialog =
            AmbilWarnaDialog(this, viewColor, true, object : AmbilWarnaDialog.OnAmbilWarnaListener {
                override fun onCancel(dialog: AmbilWarnaDialog?) {
                }

                override fun onOk(dialog: AmbilWarnaDialog?, mColor: Int) {
                    setSelectedColor(mColor, linearLayout)
                }
            })
        colorPickerDialog.show()
    }

    private fun setSelectedColor(color: Int, linearLayout: LinearLayout) {
        val selectedColor = String.format("#%08x", color)

        when (linearLayout) {
            binding.selectBgColorLayout.llSelectColor -> {
                Utils.changeBg(
                    binding.selectBgColorLayout.displayColorView,
                    selectedColor
                )
                Utils.dynamicUIModel?.chat?.chatBubble?.chatScreenBgColor = selectedColor
            }
            binding.senderChatBubbleColorLayout.llSelectColor -> {
                Utils.changeBg(
                    binding.senderChatBubbleColorLayout.displayColorView,
                    selectedColor
                )
                Utils.dynamicUIModel?.chat?.chatBubble?.senderChatBubbleColor = selectedColor
            }
            binding.senderTextColorLayout.llSelectColor -> {
                Utils.changeBg(
                    binding.senderTextColorLayout.displayColorView,
                    selectedColor
                )
                Utils.dynamicUIModel?.chat?.chatBubble?.senderTextColor = selectedColor
            }
            binding.receiverChatBubbleColorLayout.llSelectColor -> {
                Utils.changeBg(
                    binding.receiverChatBubbleColorLayout.displayColorView,
                    selectedColor
                )
                Utils.dynamicUIModel?.chat?.chatBubble?.receiverChatBubbleColor = selectedColor
            }
            binding.receiverTextColorLayout.llSelectColor -> {
                Utils.changeBg(
                    binding.receiverTextColorLayout.displayColorView,
                    selectedColor
                )
                Utils.dynamicUIModel?.chat?.chatBubble?.receiverTextColor = selectedColor
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == RESULT_OK) {
            when (requestCode) {
                REQUEST_TAKE_PHOTO -> {
                    try {
                        mPhotoUri = Uri.parse(AppPref.getValue(this, "mPhotoUri", ""))
                        mPhotoFile = File(AppPref.getValue(this, "mPhotoFile", "")!!)
                        imageUri = mPhotoUri
                        imageFile = mPhotoFile!!
                        Crop.of(imageUri!!, imageUri!!).asSquare().start(this)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }

                REQUEST_GALLERY_PHOTO -> {
                    try {
                        val newFile = ImageCopyHelperClass.getBitmapFile(this, data!!)
                        ImageCopyHelperClass.copyFile(newFile, mFile!!)
                        imageUri = ImageCopyHelperClass.getUriOfFile(this, mFile!!)
                        imageFile = mFile!!
                        Crop.of(imageUri!!, imageUri!!).asSquare().start(this)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }

                Crop.REQUEST_CROP -> {
                    try {
                        val bitmap = BitmapFactory.decodeFile(imageFile?.absolutePath)
                        binding.selectBgImageLayout.uploadImage.setImageBitmap(bitmap)
                        AppPref.setValue(
                            this,
                            AppConstants.CHAT_BOT_BG_IMAGE_PATH,
                            imageFile?.absolutePath!!
                        )
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    /**
     * This method is called after permissions are granted
     * So after permission granted code to be executed will be written here.
     */
    override fun permissionGranted() {
        if (!this.isFinishing) {
            openCameraGallery(this, mFileName)
        }
    }
}