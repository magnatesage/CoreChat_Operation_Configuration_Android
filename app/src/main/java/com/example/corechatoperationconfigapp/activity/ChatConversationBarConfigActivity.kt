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
import com.example.corechatoperationconfigapp.R
import com.example.corechatoperationconfigapp.databinding.ActivityChatConversationBarConfigBinding
import com.example.corechatoperationconfigapp.utils.AppConstants
import com.example.corechatoperationconfigapp.utils.AppConstants.CIRCLE
import com.example.corechatoperationconfigapp.utils.AppConstants.FLOATING_ICON_IMAGE
import com.example.corechatoperationconfigapp.utils.AppConstants.OVAL
import com.example.corechatoperationconfigapp.utils.AppConstants.ROUNDED
import com.example.corechatoperationconfigapp.utils.AppConstants.SEMI_ROUNDED
import com.example.corechatoperationconfigapp.utils.AppConstants.SQUARE
import com.example.corechatoperationconfigapp.utils.AppPref
import com.example.corechatoperationconfigapp.utils.Extensions.invisible
import com.example.corechatoperationconfigapp.utils.Extensions.showToast
import com.example.corechatoperationconfigapp.utils.Extensions.visible
import com.example.corechatoperationconfigapp.utils.ImageCopyHelperClass
import com.example.corechatoperationconfigapp.utils.Utils
import com.example.corechatoperationconfigapp.utils.Utils.getDesiredDrawableFromXML
import com.example.corechatoperationconfigapp.utils.Utils.getSizeInSDP
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