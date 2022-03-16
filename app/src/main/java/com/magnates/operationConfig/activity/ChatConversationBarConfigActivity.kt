package com.magnates.operationConfig.activity

import android.Manifest.permission.*
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.core.content.res.ResourcesCompat
import com.bumptech.glide.Glide
import com.magnates.operationConfig.R
import com.magnates.operationConfig.databinding.ActivityChatConversationBarConfigBinding
import com.magnates.operationConfig.utils.AppConstants
import com.magnates.operationConfig.utils.AppConstants.CIRCLE
import com.magnates.operationConfig.utils.AppConstants.FLOATING_ICON_IMAGE
import com.magnates.operationConfig.utils.AppConstants.OVAL
import com.magnates.operationConfig.utils.AppConstants.ROUNDED
import com.magnates.operationConfig.utils.AppConstants.SEMI_ROUNDED
import com.magnates.operationConfig.utils.AppConstants.SQUARE
import com.magnates.operationConfig.utils.AppPref
import com.magnates.operationConfig.utils.Extensions.invisible
import com.magnates.operationConfig.utils.Extensions.showToast
import com.magnates.operationConfig.utils.Extensions.visible
import com.magnates.operationConfig.utils.ImageCopyHelperClass
import com.magnates.operationConfig.utils.Utils
import com.magnates.operationConfig.utils.Utils.getDesiredDrawableFromXML
import com.magnates.operationConfig.utils.Utils.getSizeInSDP
import com.google.android.material.card.MaterialCardView
import com.soundcloud.android.crop.Crop
import java.io.File

class ChatConversationBarConfigActivity : BaseActivity() {
    private lateinit var binding: ActivityChatConversationBarConfigBinding
    private var mFileName = ""
    private var imageFile: File? = null
    private lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatConversationBarConfigBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    /**
     * This method is used to initialization process of activity
     */
    override fun init() {
        context = this@ChatConversationBarConfigActivity
        binding.headerLayout.tvHeader.text = getString(R.string.chat_conversation_bar_configuration)
        requiredPermissions(arrayOf(READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE, CAMERA))
        getViewPermissionInterfaceInstance(this)

        binding.root.post {
            setValuesToViews()
        }
    }

    /**
     * This method is used to set the values to views
     */
    private fun setValuesToViews() {
        if (!Utils.dynamicUIModel?.chat?.conversationBar?.conversationBarShape.isNullOrBlank()) {
            setSelectedView(Utils.dynamicUIModel?.chat?.conversationBar?.conversationBarShape!!)
        }

        val imageFilePath = AppPref.getValue(
            this,
            AppConstants.FLOATING_ICON_IMAGE_PATH, ""
        )
        if (imageFilePath?.isNotBlank() == true) {
            val bitmap = BitmapFactory.decodeFile(imageFilePath)
            binding.floatingIconLayout.uploadImage.setImageBitmap(bitmap)
        } else if (!Utils.dynamicUIModel?.chat?.conversationBar?.floatingIconUrl.isNullOrEmpty()) {
            Glide.with(this)
                .load(Utils.dynamicUIModel?.chat?.conversationBar?.floatingIconUrl)
                .placeholder(R.drawable.upload)
                .into(binding.floatingIconLayout.uploadImage)
        }
    }

    /**
     * This method is called when user clicks on view
     */
    override fun onClick(view: View) {
        when (view) {
            binding.rlRounded -> setSelectedView(ROUNDED)

            binding.rlSquare -> setSelectedView(SQUARE)

            binding.rlSemiRounded -> setSelectedView(SEMI_ROUNDED)

            binding.rlOval -> setSelectedView(OVAL)

            binding.rlCircle -> setSelectedView(CIRCLE)

            binding.floatingIconLayout.uploadImage -> {
                mFileName = FLOATING_ICON_IMAGE
                getPermissions()
            }

            binding.btnBack -> onBackPressed()

            binding.btnPreview -> {
                when {
                    Utils.dynamicUIModel?.chat?.conversationBar?.conversationBarShape.isNullOrBlank() -> {
                        showToast(resources.getString(R.string.error_message_select_conversation_bar_style))
                    }

                    binding.floatingIconLayout.uploadImage.drawable.constantState == ResourcesCompat.getDrawable(
                        resources,
                        R.drawable.upload,
                        null
                    )?.constantState -> {
                        showToast(resources.getString(R.string.error_message_upload_image_for_floating_icon_button))
                    }

                    else -> {
                        Utils.dynamicUIModel?.chat?.conversationBar?.floatingIconUrl =
                            AppPref.getValue(context,AppConstants.FLOATING_ICON_IMAGE_PATH,"").toString()

                        val intent = Intent(this, ChatActivity::class.java)
                        intent.putExtra(AppConstants.FROM_CHAT_CONVERSATION_BAR_CONFIG, true)
                        startActivity(intent)
                    }
                }

            }
        }
    }

    /**
     * This method is called to set style in selected views
     */
    private fun setSelectedView(selectedStyle: String) {
        Utils.dynamicUIModel?.chat?.conversationBar?.conversationBarShape = selectedStyle

        binding.rlRounded.background = null
        binding.rlSquare.background = null
        binding.rlSemiRounded.background = null
        binding.rlOval.background = null
        binding.rlCircle.background = null

        binding.tvCheckImgRounded.invisible()
        binding.tvCheckImgSquare.invisible()
        binding.tvCheckImgSemiRounded.invisible()
        binding.tvCheckImgOval.invisible()
        binding.tvCheckImgCircle.invisible()

        binding.cvRounded.strokeWidth = 0
        binding.cvSquare.strokeWidth = 0
        binding.cvSemiRounded.strokeWidth = 0
        binding.cvOval.strokeWidth = 0
        binding.cvCircle.strokeWidth = 0

        val floatingIconDrawable = getDesiredDrawableFromXML(this, R.drawable.flash_small)
        binding.ivRoundedFlash.setImageDrawable(floatingIconDrawable)
        binding.ivSquareFlash.setImageDrawable(floatingIconDrawable)
        binding.ivSemiRoundedFlash.setImageDrawable(floatingIconDrawable)
        binding.ivOvalFlash.setImageDrawable(floatingIconDrawable)
        binding.ivCircleFlash.setImageDrawable(floatingIconDrawable)

        val shadowImage = getDesiredDrawableFromXML(this, R.drawable.shadow_img)

        when (selectedStyle) {
            ROUNDED -> {
                binding.rlRounded.background = shadowImage
                binding.tvCheckImgRounded.visible()
                setBorderToCardView(binding.cvRounded)
                loadFloatingIconUrl(binding.ivRoundedFlash)
            }
            SQUARE -> {
                binding.rlSquare.background = shadowImage
                binding.tvCheckImgSquare.visible()
                setBorderToCardView(binding.cvSquare)
                loadFloatingIconUrl(binding.ivSquareFlash)
            }
            SEMI_ROUNDED -> {
                binding.rlSemiRounded.background = shadowImage
                binding.tvCheckImgSemiRounded.visible()
                setBorderToCardView(binding.cvSemiRounded)
                loadFloatingIconUrl(binding.ivSemiRoundedFlash)
            }
            OVAL -> {
                binding.rlOval.background = shadowImage
                binding.tvCheckImgOval.visible()
                setBorderToCardView(binding.cvOval)
                loadFloatingIconUrl(binding.ivOvalFlash)
            }
            CIRCLE -> {
                binding.rlCircle.background = shadowImage
                binding.tvCheckImgCircle.visible()
                setBorderToCardView(binding.cvCircle)
                loadFloatingIconUrl(binding.ivCircleFlash)
            }
        }
    }

    /**
     * This method will set Border of 1dp to Material CardView
     */
    private fun setBorderToCardView(materialCardView: MaterialCardView) {
        materialCardView.strokeColor = getColor(R.color.lightGray)
        materialCardView.strokeWidth = getSizeInSDP(this, R.dimen._1sdp)
    }

    /**
     * This method is used to load the image in floating icon
     */
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
     * This method is called after permissions are granted
     * So after permission granted code to be executed will be written here.
     */
    override fun permissionGranted() {
        if (!this.isFinishing) {
            openCameraGallery(this, mFileName)
        }
    }

    /**
     * This method is called to get the result
     */
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
                        binding.floatingIconLayout.uploadImage.setImageBitmap(bitmap)
                        AppPref.setValue(
                            this,
                            AppConstants.FLOATING_ICON_IMAGE_PATH,
                            imageFile?.absolutePath!!
                        )
                        if (!Utils.dynamicUIModel?.chat?.conversationBar?.conversationBarShape.isNullOrBlank()) {
                            setSelectedView(Utils.dynamicUIModel?.chat?.conversationBar?.conversationBarShape!!)
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}