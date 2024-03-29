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

import android.Manifest.permission.*
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.LinearLayout
import androidx.core.content.res.ResourcesCompat
import com.magnates.operationConfig.R
import com.magnates.operationConfig.adapter.FontArrayAdapter
import com.magnates.operationConfig.customviews.CustomTextView
import com.magnates.operationConfig.customviews.colorpicker.AmbilWarnaDialog
import com.magnates.operationConfig.databinding.ActivitySplashConfigBinding
import com.magnates.operationConfig.utils.AppConstants
import com.magnates.operationConfig.utils.AppConstants.BOLD
import com.magnates.operationConfig.utils.AppConstants.BOLD_ITALIC
import com.magnates.operationConfig.utils.AppConstants.COLOR
import com.magnates.operationConfig.utils.AppConstants.IMAGE
import com.magnates.operationConfig.utils.AppConstants.ITALIC
import com.magnates.operationConfig.utils.AppConstants.SPLASH_SCREEN_BG_IMAGE
import com.magnates.operationConfig.utils.AppConstants.SPLASH_SCREEN_BG_IMAGE_PATH
import com.magnates.operationConfig.utils.AppConstants.SPLASH_SCREEN_LOGO_IMAGE
import com.magnates.operationConfig.utils.AppConstants.SPLASH_SCREEN_LOGO_IMAGE_PATH
import com.magnates.operationConfig.utils.AppPref
import com.magnates.operationConfig.utils.Extensions.gone
import com.magnates.operationConfig.utils.Extensions.showToast
import com.magnates.operationConfig.utils.Extensions.visible
import com.magnates.operationConfig.utils.ImageCopyHelperClass
import com.magnates.operationConfig.utils.Utils
import com.magnates.operationConfig.utils.Utils.getDesiredColorFromXML
import com.magnates.operationConfig.utils.Utils.getDesiredDrawableFromXML
import com.soundcloud.android.crop.Crop
import java.io.File

class SplashConfigActivity : BaseActivity(), AdapterView.OnItemSelectedListener {
    private var isLogoImage = false
    private var boldItalicString: String = ""
    private lateinit var binding: ActivitySplashConfigBinding
    private var isOrgNameBold: Boolean = false
    private var isOrgNameItalic: Boolean = false
    private var mFileName = ""
    private var imageFile: File? = null
    private lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashConfigBinding.inflate(layoutInflater)
        setContentView(binding.root)
        requiredPermissions(arrayOf(READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE, CAMERA))
    }

    /**
     * This method is used to initialization process of activity
     */
    override fun init() {
        context = this@SplashConfigActivity
        getViewPermissionInterfaceInstance(this)
        binding.headerLayout.tvHeader.text = getString(R.string.splash_screen_config)
        binding.spinnerFontType.onItemSelectedListener = this
        binding.spinnerFontSize.onItemSelectedListener = this
        binding.spinnerBgType.onItemSelectedListener = this
        binding.selectBgColorLayout.cvSelectColor.gone()

        binding.swDropShadowToggleSwitch.setOnCheckedChangeListener { _, isChecked ->
            Utils.dynamicUIModel?.splash?.logoBgDropShadow = isChecked
            if (binding.ivLogo.drawable.constantState != ResourcesCompat.getDrawable(
                    resources,
                    R.drawable.upload,
                    null
                )?.constantState
            ) {
                binding.cvLogo.cardElevation = Utils.getSizeInSDP(this, R.dimen._10sdp).toFloat()
            }
        }

        binding.swBold.setOnCheckedChangeListener { _, isChecked ->
            isOrgNameBold = isChecked
            setBoldItalicFontVariable()
        }

        binding.swItalic.setOnCheckedChangeListener { _, isChecked ->
            isOrgNameItalic = isChecked
            setBoldItalicFontVariable()
        }

        binding.root.post {
            setValuesToViews()
        }
    }

    /**
     * This method is used to set the values to views
     */
    private fun setValuesToViews() {
        if (!Utils.dynamicUIModel?.splash?.logoUrl.isNullOrBlank()) {
            Utils.loadImageURL(
                this,
                Utils.dynamicUIModel?.splash?.logoUrl!!,
                binding.ivLogo,
                R.drawable.upload
            )
        }

        if (!Utils.dynamicUIModel?.splash?.splashScreenBgImageUrl.isNullOrBlank()) {
            Utils.loadImageURL(
                this,
                Utils.dynamicUIModel?.splash?.splashScreenBgImageUrl!!,
                binding.selectBgImageLayout.uploadImage,
                R.drawable.upload
            )
        }

        binding.etOrgName.setText(Utils.dynamicUIModel?.splash?.orgName)

        if (!Utils.dynamicUIModel?.splash?.orgNameFontType.isNullOrBlank()) {
            Utils.getOriginalFontValue(Utils.dynamicUIModel?.splash?.orgNameFontType!!).let {
                Utils.getIndex(
                    Utils.getStringArrayFromXML(this, R.array.dropdown_font_list_array),
                    it
                )
            }.let { binding.spinnerFontType.setSelection(it) }
        }

        if (!Utils.dynamicUIModel?.splash?.orgNameFontSize.isNullOrBlank()) {
            Utils.dynamicUIModel?.splash?.orgNameFontSize?.let {
                Utils.getIndex(
                    Utils.getStringArrayFromXML(this, R.array.dropdown_splash_font_size_array),
                    it
                )
            }?.let { binding.spinnerFontSize.setSelection(it) }
        }

        if (!Utils.dynamicUIModel?.splash?.splashScreenBgType.isNullOrBlank()) {
            Utils.dynamicUIModel?.splash?.splashScreenBgType?.let {
                Utils.getIndex(
                    Utils.getStringArrayFromXML(this, R.array.dropdown_bg_array),
                    it
                )
            }?.let { binding.spinnerBgType.setSelection(it) }
        }

        if (Utils.dynamicUIModel?.splash?.orgNameFontType!!.contains(BOLD)) {
            binding.swBold.isChecked = true
        }

        if (Utils.dynamicUIModel?.splash?.orgNameFontType!!.contains(ITALIC)) {
            binding.swItalic.isChecked = true
        }

        binding.swDropShadowToggleSwitch.isChecked =
            Utils.dynamicUIModel?.splash?.logoBgDropShadow!!

        if (!Utils.dynamicUIModel?.splash?.orgNameFontColor.isNullOrBlank()) {
            Utils.changeBg(
                binding.selectFontColorLayout.displayColorView,
                Utils.dynamicUIModel?.splash?.orgNameFontColor!!
            )
        }

        if (!Utils.dynamicUIModel?.splash?.splashScreenBgColor.isNullOrBlank()) {
            Utils.changeBg(
                binding.selectBgColorLayout.displayColorView,
                Utils.dynamicUIModel?.splash?.splashScreenBgColor!!
            )
        }

        setShapeOfLogo(Utils.dynamicUIModel?.splash?.logoBgShape!!)
    }

    /**
     * This method is used when user select item in spinner
     */
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when (parent) {
            binding.spinnerFontType -> {
                if (position != 0) {
                    Utils.dynamicUIModel?.splash?.orgNameFontType =
                        parent.getItemAtPosition(position).toString()
                } else {
                    Utils.dynamicUIModel?.splash?.orgNameFontType = ""
                }
            }
            binding.spinnerFontSize -> {
                if (position != 0) {
                    Utils.dynamicUIModel?.splash?.orgNameFontSize =
                        parent.getItemAtPosition(position).toString()
                } else {
                    Utils.dynamicUIModel?.splash?.orgNameFontSize = ""
                }
            }
            binding.spinnerBgType -> {
                when (position) {
                    0 -> {
                        Utils.dynamicUIModel?.splash?.splashScreenBgType = ""
                        binding.selectBgColorLayout.cvSelectColor.gone()
                        binding.llUploadImage.gone()
                    }

                    1 -> {
                        Utils.dynamicUIModel?.splash?.splashScreenBgType =
                            parent.getItemAtPosition(position).toString()
                        binding.selectBgColorLayout.cvSelectColor.visible()
                        binding.llUploadImage.gone()
                    }

                    2 -> {
                        Utils.dynamicUIModel?.splash?.splashScreenBgType =
                            parent.getItemAtPosition(position).toString()
                        binding.selectBgColorLayout.cvSelectColor.gone()
                        binding.llUploadImage.visible()
                    }
                }
            }
        }
    }

    /**
     * This method is called when user nothing select anything in spinner
     */
    override fun onNothingSelected(p0: AdapterView<*>?) {}

    /**
     * This method is called when user clicks on view
     */
    override fun onClick(view: View) {
        when (view) {
            binding.rlShapeNone -> {
                setShapeOfLogo(AppConstants.SHAPE_NONE)
            }

            binding.rlShapeSquare -> {
                setShapeOfLogo(AppConstants.SHAPE_SQUARE)
            }

            binding.rlShapeRoundCorner -> {
                setShapeOfLogo(AppConstants.SHAPE_ROUNDED_CORNER)
            }

            binding.rlShapeCircle -> {
                setShapeOfLogo(AppConstants.SHAPE_CIRCLE)
            }

            binding.ivLogo -> {
                isLogoImage = true
                mFileName = SPLASH_SCREEN_LOGO_IMAGE
                getPermissions()
            }

            binding.selectBgImageLayout.uploadImage -> {
                isLogoImage = false
                mFileName = SPLASH_SCREEN_BG_IMAGE
                getPermissions()
            }

            binding.selectFontColorLayout.llSelectColor -> {
                callColorPickerDialog(
                    Utils.getColorFromView(binding.selectFontColorLayout.displayColorView),
                    binding.selectFontColorLayout.llSelectColor
                )
            }

            binding.selectBgColorLayout.llSelectColor -> {
                callColorPickerDialog(
                    Utils.getColorFromView(binding.selectBgColorLayout.displayColorView),
                    binding.selectBgColorLayout.llSelectColor
                )
            }

            binding.btnBack -> onBackPressed()

            binding.btnPreview -> {
                when {
                    binding.etOrgName.text.toString().isBlank() -> {
                        showToast(resources.getString(R.string.error_message_add_organization_name))
                    }
                    binding.ivLogo.drawable.constantState == ResourcesCompat.getDrawable(
                        resources,
                        R.drawable.upload,
                        null
                    )?.constantState -> {
                        showToast(resources.getString(R.string.error_message_select_logo_for_organization))
                    }
                    Utils.dynamicUIModel?.splash?.orgNameFontType.isNullOrBlank() -> {
                        showToast(resources.getString(R.string.error_message_select_font_type))
                    }
                    Utils.dynamicUIModel?.splash?.orgNameFontSize.isNullOrBlank() -> {
                        showToast(resources.getString(R.string.error_message_select_font_size))
                    }
                    Utils.dynamicUIModel?.splash?.logoBgShape.isNullOrBlank() -> {
                        showToast(resources.getString(R.string.error_message_select_shape))
                    }
                    Utils.dynamicUIModel?.splash?.splashScreenBgType.isNullOrBlank() -> {
                        showToast(resources.getString(R.string.error_message_select_background_type))
                    }
                    Utils.dynamicUIModel?.splash?.splashScreenBgType == IMAGE &&
                            binding.selectBgImageLayout.uploadImage.drawable.constantState == ResourcesCompat.getDrawable(
                        resources,
                        R.drawable.upload,
                        null
                    )?.constantState -> {
                        showToast(resources.getString(R.string.error_message_select_background_image))
                    }
                    else -> {
                        Utils.dynamicUIModel?.splash?.orgName =
                            binding.etOrgName.text?.trim().toString()

                        Utils.dynamicUIModel?.splash?.logoUrl =
                            AppPref.getValue(context,SPLASH_SCREEN_LOGO_IMAGE_PATH,"").toString()

                        Utils.dynamicUIModel?.splash?.orgNameFontType =
                            Utils.getOriginalFontValue(Utils.dynamicUIModel?.splash?.orgNameFontType!!)
                        Utils.dynamicUIModel?.splash?.orgNameFontType += boldItalicString

                        if (Utils.dynamicUIModel?.splash?.orgNameFontColor.isNullOrBlank()) {
                            Utils.dynamicUIModel?.splash?.orgNameFontColor = String.format(
                                "#%08x",
                                Utils.getColorFromView(binding.selectFontColorLayout.displayColorView)
                            )
                        }

                        if (Utils.dynamicUIModel?.splash?.splashScreenBgType == IMAGE) {
                            Utils.dynamicUIModel?.splash?.splashScreenBgImageUrl =
                                AppPref.getValue(context, SPLASH_SCREEN_BG_IMAGE_PATH,"")
                                    .toString()
                            Utils.dynamicUIModel?.splash?.splashScreenBgColor = ""

                        }else if (Utils.dynamicUIModel?.splash?.splashScreenBgType == COLOR){

                            Utils.dynamicUIModel?.splash?.splashScreenBgColor = String.format(
                                "#%08x",
                                Utils.getColorFromView(binding.selectBgColorLayout.displayColorView))
                        }

                        startActivity(Intent(this, SplashPreviewActivity::class.java))
                    }
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

    /**
     * This method is called to set the selected color
     */
    private fun setSelectedColor(color: Int, linearLayout: LinearLayout) {
        val selectedColor = String.format("#%08x", color)

        when (linearLayout) {
            binding.selectFontColorLayout.llSelectColor -> {
                Utils.changeBg(
                    binding.selectFontColorLayout.displayColorView,
                    selectedColor
                )
                Utils.dynamicUIModel?.splash?.orgNameFontColor = selectedColor
            }

            binding.selectBgColorLayout.llSelectColor -> {
                Utils.changeBg(
                    binding.selectBgColorLayout.displayColorView,
                    selectedColor
                )
                Utils.dynamicUIModel?.splash?.splashScreenBgColor = selectedColor
            }
        }
    }

    /**
     * This method is used to select Shape of Logo & highlight that shape
     */
    private fun setShapeOfLogo(shapeOfLogo: String) {
        Utils.dynamicUIModel?.splash?.logoBgShape = shapeOfLogo
        when (shapeOfLogo) {
            AppConstants.SHAPE_NONE -> {
                changeBgAsSelectedView(binding.rlShapeNone, binding.tvCheckImgShapeNone)
                removeBg(binding.rlShapeCircle, binding.tvCheckImgShapeCircle)
                removeBg(binding.rlShapeSquare, binding.tvCheckImgShapeSquare)
                removeBg(binding.rlShapeRoundCorner, binding.tvCheckImgShapeRoundCorner)
            }

            AppConstants.SHAPE_SQUARE -> {
                changeBgAsSelectedView(binding.rlShapeSquare, binding.tvCheckImgShapeSquare)
                removeBg(binding.rlShapeCircle, binding.tvCheckImgShapeCircle)
                removeBg(binding.rlShapeNone, binding.tvCheckImgShapeNone)
                removeBg(binding.rlShapeRoundCorner, binding.tvCheckImgShapeRoundCorner)
            }

            AppConstants.SHAPE_ROUNDED_CORNER -> {
                removeBg(binding.rlShapeCircle, binding.tvCheckImgShapeCircle)
                removeBg(binding.rlShapeSquare, binding.tvCheckImgShapeSquare)
                removeBg(binding.rlShapeNone, binding.tvCheckImgShapeNone)
                changeBgAsSelectedView(
                    binding.rlShapeRoundCorner,
                    binding.tvCheckImgShapeRoundCorner
                )
            }

            AppConstants.SHAPE_CIRCLE -> {
                changeBgAsSelectedView(binding.rlShapeCircle, binding.tvCheckImgShapeCircle)
                removeBg(binding.rlShapeSquare, binding.tvCheckImgShapeSquare)
                removeBg(binding.rlShapeNone, binding.tvCheckImgShapeNone)
                removeBg(binding.rlShapeRoundCorner, binding.tvCheckImgShapeRoundCorner)
            }
        }
        setShapeToImageView(shapeOfLogo)
    }

    /**
     * This method is used to set the shape into imageview
     */
    private fun setShapeToImageView(shapeOfLogo: String) {
        if (binding.ivLogo.drawable.constantState != ResourcesCompat.getDrawable(
                resources,
                R.drawable.upload,
                null
            )?.constantState
        ) {
            when (shapeOfLogo) {
                AppConstants.SHAPE_NONE, AppConstants.SHAPE_SQUARE -> {
                    binding.cvLogo.setAllCorners(0F)
                }
                AppConstants.SHAPE_ROUNDED_CORNER -> {
                    binding.cvLogo.setAllCorners(Utils.getSizeInSDP(this, R.dimen._16sdp).toFloat())
                }
                AppConstants.SHAPE_CIRCLE -> {
                    binding.cvLogo.setAllCornersInPercent(0.5F)
                }
            }
        }
    }

    /**
     * This method changes Background from blank to a white selected highlighted image
     * Also it makes "Right CheckMark" VISIBLE
     */
    private fun changeBgAsSelectedView(view: View, checkMark: View) {
        view.background = getDesiredDrawableFromXML(this, R.drawable.shadow_img)
        checkMark.visibility = View.VISIBLE
        (checkMark as CustomTextView).setTextColor(
            getDesiredColorFromXML(
                this,
                R.color.colorCheckMark
            )
        )
    }

    /**
     * This method removes Background from view
     * Also it makes "Right CheckMark" INVISIBLE
     */
    private fun removeBg(view: View, checkMark: View) {
        view.background = null
        checkMark.visibility = View.INVISIBLE
    }

    /**
     * This method will set that user selected Bold or Italic font & store in a vairable.
     */
    private fun setBoldItalicFontVariable() {
        (binding.spinnerFontType.adapter as FontArrayAdapter).updateBoldItalic(
            isOrgNameBold,
            isOrgNameItalic
        )
        boldItalicString = if (isOrgNameBold && isOrgNameItalic) {
            "-$BOLD_ITALIC"
        } else {
            when {
                isOrgNameBold -> {
                    "-$BOLD"
                }
                isOrgNameItalic -> {
                    "-$ITALIC"
                }
                else -> {
                    ""
                }
            }
        }
    }

    /**
     * This method is used to get the result
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
                        var key = ""
                        val bitmap = BitmapFactory.decodeFile(imageFile?.absolutePath)
                        if (isLogoImage) {
                            key = SPLASH_SCREEN_LOGO_IMAGE_PATH
                            binding.ivLogo.setImageBitmap(bitmap)
                        } else {
                            key = SPLASH_SCREEN_BG_IMAGE_PATH
                            binding.selectBgImageLayout.uploadImage.setImageBitmap(bitmap)
                        }
                        AppPref.setValue(this, key, imageFile?.absolutePath!!)
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