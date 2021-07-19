package com.example.corechatoperationconfigapp.utils

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Environment
import android.util.TypedValue
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.corechatoperationconfigapp.R
import com.example.corechatoperationconfigapp.customviews.CustomButton
import com.example.corechatoperationconfigapp.customviews.CustomEditText
import com.example.corechatoperationconfigapp.customviews.CustomMaterialButton
import com.example.corechatoperationconfigapp.customviews.CustomTextView
import com.example.corechatoperationconfigapp.model.DynamicUIModel
import com.example.corechatoperationconfigapp.utils.AppConstants.BOLD
import com.example.corechatoperationconfigapp.utils.AppConstants.BOLD_ITALIC
import com.example.corechatoperationconfigapp.utils.AppConstants.ITALIC
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import com.google.android.material.textfield.TextInputEditText
import com.google.gson.GsonBuilder
import java.io.File
import java.io.IOException
import java.nio.charset.StandardCharsets

/**
 * This class is used for global functions used in entire application
 */
object Utils {

    var dynamicUIModel: DynamicUIModel? = null
    private var dialog: Dialog? = null

    fun getDefaultUIModel(activity: Activity) {
        var json = ""
        try {
            val inputStream = activity.assets.open("default.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, StandardCharsets.UTF_8)
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
        val builder = GsonBuilder()
        val gson = builder.create()
        dynamicUIModel = gson.fromJson<Any>(json, DynamicUIModel::class.java) as DynamicUIModel
    }

    /**
     * This method is used to show Progressbar
     * @param context the context is used to create dialog
     */
    fun showProgressBar(context: Context) {
        if (dialog != null && dialog!!.isShowing) {
            dialog!!.dismiss()
            dialog = null
        }
        dialog = Dialog(context)
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog!!.setContentView(R.layout.custom_progress_dialog)
        dialog!!.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog!!.setCancelable(false)
        dialog!!.show()
    }

    /**
     * This method is used to dismiss Progressbar
     */
    fun dismissProgressBar() {
        if (dialog != null && dialog!!.isShowing) {
            dialog!!.dismiss()
            dialog = null
        }
    }

    /**
     * This method is used to hide keyboard
     * @param context the context to hide keyboard
     * @param view the view is used to get window token
     */
    fun hideKeyboard(context: Context, view: View) {
        val inputMethodManager =
            context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    /**
     * This method is used to load Image URL to ImageView using Glide Library
     * @param imageUrl the string image url
     * @param imageView the view to load image url
     * @param placeholder the id of the resource to use as a placeholder and error holder
     */
    fun loadImageURL(context: Activity, imageUrl: String, imageView: ImageView, placeholder: Int) {
        showProgressBar(context)
        Glide.with(context)
            .load(imageUrl)
            .placeholder(placeholder)
            .error(placeholder)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?, model: Any?,
                    target: Target<Drawable>?, isFirstResource: Boolean
                ): Boolean {
                    dismissProgressBar()
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?, model: Any?,
                    target: Target<Drawable>?, dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    dismissProgressBar()
                    return false
                }

            })
            .into(imageView)
    }

    /**
     * This method is used to check internet connection
     * @param context
     * @param informToUser Boolean - to inform user via alert dialog or not
     * @return Boolean - Whether there is an internet connection
     */
    fun isNetworkAvailable(context: Context, informToUser: Boolean): Boolean {
        var isConnected = false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkCapabilities = connectivityManager.activeNetwork ?: return false
        val activeNetwork =
            connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
        isConnected = when {
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }

        if (informToUser && !isConnected) {
            AlertDialogView.showAlert(
                context,
                context.getString(R.string.app_name),
                context.getString(R.string.error_internet_connection),
                context.getString(R.string.ok)
            )?.show()
        }

        return isConnected
    }

    /**
     * This method will change TextColor to the required color for the particular view passed.
     * @param view: View
     * @param newColor: Int
     */
    fun changeTextColor(view: View, color: Int) {
        (view as TextView).setTextColor(color)
    }

    /**
     * This method is used to change text color to the particular view
     * @param view the view to set text color
     * @param newColor the string text color
     */
    fun changeTextColor(view: View, newColor: String) {
        (view as TextView).setTextColor(getParsedColorValue(newColor))
    }

    /**
     * This method will return predefined Integer value of Color from Colors.xml
     * @param R.color.white (id of color) :Int
     */
    fun getDesiredColorFromXML(context: Context, id: Int): Int {
        return ContextCompat.getColor(context, id)
    }

    /**
     * This method will return predefined Array value from Strings.xml
     */
    fun getStringArrayFromXML(context: Context, stringArrayId: Int): Array<String> {
        return context.resources.getStringArray(stringArrayId)
    }

    /**
     * This method will return only font name regardless of its bold or italic type
     * @param stringValue: String
     */
    fun getOriginalFontValue(stringValue: String): String {
        var newStringvalue = stringValue
        if (newStringvalue.contains(BOLD) || newStringvalue.contains(ITALIC)
            || stringValue.contains(BOLD_ITALIC)
        ) {
            newStringvalue =
                newStringvalue.removeRange(newStringvalue.indexOf("-"), newStringvalue.length)
        }
        return newStringvalue
    }

    /**
     * This method will return index of particular value of an array
     */
    fun getIndex(array: Array<String>, getIndexOfValue: String): Int {
        return array.toList().indexOf(getIndexOfValue)
    }

    /**
     * This method will change Background Color to the required color for the particular view passed.
     * @param view: View
     * @param newColor: String
     */
    fun changeBg(view: View, newColor: String) {
        view.setBackgroundColor(Color.parseColor(newColor))
    }

    /**
     * This method is used to change Background Color to the particular view
     * @param view the view to set background color
     * @param newColor the integer background color
     */
    fun changeBg(view: View, newColor: Int) {
        view.setBackgroundColor(newColor)
    }

    /**
     * This method will return color of a particulat view passed
     * @param view: View
     */
    fun getColorFromView(view: View): Int {
        return (view.background as ColorDrawable).color
    }

    /**
     * This method will return predefined Integer value of Drawable from Drawable folder
     * @param R.drawable.shape (id of drawable) :Int
     */
    fun getDesiredDrawableFromXML(context: Activity, id: Int): Drawable? {
        return ContextCompat.getDrawable(context, id)
    }

    /**
     * This method will return predefined string value from Strings.xml
     * @param R.string.abc (stringValue as id): Int
     */
    fun getStringFromXML(context: Activity, stringValue: Int): String {
        return context.resources.getString(stringValue)
    }

    /**
     * This method will create a temporary file for Image, store image in that file & return that file
     */
    @Throws(IOException::class)
    fun createTempImageFile(context: Context, fileName: String): File {
        // Create an image file name
        val storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val mFileName = "$storageDir/$fileName"
        return File(mFileName)
    }

    /**
     * This method is used to convert size in sdp
     * @param context the context is used to get resources
     * @param dimenSize the size from dimen class
     * @return size in sdp
     */
    fun getSizeInSDP(context: Context, dimenSize: Int): Int {
        return context.resources.getDimensionPixelSize(dimenSize)
    }

    /**
     * This method will set Text size in SSP
     * @param dimenSize: Int
     */
    fun setTextSizeInSSP(view: View, dimenSize: Int) {
        val fontSize = getSizeInSDP(view.context, dimenSize).toFloat()
        when (view) {
            is CustomTextView -> {
                view.setTextSize(
                    TypedValue.COMPLEX_UNIT_PX,
                    fontSize
                )
            }
            is CustomEditText -> {
                view.setTextSize(
                    TypedValue.COMPLEX_UNIT_PX,
                    fontSize
                )
            }
            is CustomButton -> {
                view.setTextSize(
                    TypedValue.COMPLEX_UNIT_PX,
                    fontSize
                )
            }
            is TextInputEditText -> {
                view.setTextSize(
                    TypedValue.COMPLEX_UNIT_PX,
                    fontSize
                )
            }
            is CustomMaterialButton -> {
                view.setTextSize(
                    TypedValue.COMPLEX_UNIT_PX,
                    fontSize
                )
            }
        }
    }

    /**
     * This method will return icon value
     * @param iconValue: String
     */
    fun getOriginalIconValue(iconValue: String): String {
        return iconValue.replace("&#x", "")
    }

    /**
     * This method used to get size in ssp
     * @param fontSize: String
     */
    fun getFontSizeInSSP(fontSize: String): Int {
        return when (fontSize) {
            "5" -> R.dimen._5ssp
            "6" -> R.dimen._6ssp
            "7" -> R.dimen._7ssp
            "8" -> R.dimen._8ssp
            "9" -> R.dimen._9ssp
            "10" -> R.dimen._10ssp
            "11" -> R.dimen._11ssp
            "12" -> R.dimen._12ssp
            "13" -> R.dimen._13ssp
            "14" -> R.dimen._14ssp
            "15" -> R.dimen._15ssp
            "16" -> R.dimen._16ssp
            "17" -> R.dimen._17ssp
            "18" -> R.dimen._18ssp
            "19" -> R.dimen._19ssp
            "20" -> R.dimen._20ssp
            "21" -> R.dimen._21ssp
            "22" -> R.dimen._22ssp
            "23" -> R.dimen._23ssp
            "24" -> R.dimen._24ssp
            "25" -> R.dimen._25ssp
            "26" -> R.dimen._26ssp
            "27" -> R.dimen._27ssp
            "28" -> R.dimen._28ssp
            "29" -> R.dimen._29ssp
            "30" -> R.dimen._30ssp
            else -> R.dimen._10ssp
        }
    }

    /**
     * This method will return a view that developer wants to inflate at runtime
     * This is generic method & can be used for any type of layout to inflate at runtime
     * @param shape the id of layout
     * @param parent the parent layout
     * @param aClass the class that will be inflated
     * @return T
     */
    fun <T> getLayoutFromInflater(
        context: Context,
        shape: Int,
        parent: ViewGroup,
        aClass: Class<T>
    ): T? {
        val inflater = LayoutInflater.from(context)
        return aClass.cast(inflater.inflate(shape, parent, false))
    }

    /**
     * This method will return Integer value of Color
     * @param color the string value of color
     * @return integer value of color
     */
    fun getParsedColorValue(color: String): Int {
        return Color.parseColor(color)
    }

    /**
     * This method is used to set shadow to particular CardView
     * @param view the cardview to set shadow
     * @param showShadow: Boolean - to show shadow or not
     */
    fun setCardElevation(context: Context, view: CardView, showShadow: Boolean) {
        if (showShadow) {
            view.cardElevation = getSizeInSDP(context, R.dimen._5sdp).toFloat()
        } else {
            view.cardElevation = 0F
        }
    }

    /**
     * This method is used to set border color and border width to particular view
     * @param view the view to set border color and border width
     * @param strokeColor the integer border color value
     * @param strokeWidth the integer border width
     */
    fun setStrokeColorAndWidth(view: View, strokeColor: Int, strokeWidth: Int) {
        if (view is MaterialButton) {
            view.strokeColor = ColorStateList.valueOf(strokeColor)
            view.strokeWidth = strokeWidth
        } else if (view is MaterialCardView) {
            view.strokeColor = strokeColor
            view.strokeWidth = strokeWidth
        }
    }

    /**
     * This method is used to set shadow to particular view
     * @param view the view to set shadow
     * @param showShadow: Boolean - to show shadow or not
     */
    fun setElevationShadow(context: Activity, view: View, showShadow: Boolean) {
        if (showShadow) {
            view.elevation = getSizeInSDP(context, R.dimen._5sdp).toFloat()
        } else {
            view.elevation = 0F
        }
    }
}