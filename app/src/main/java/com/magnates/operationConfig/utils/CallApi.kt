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

package com.magnates.operationConfig.utils

import android.content.Context
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.magnates.operationConfig.R
import com.magnates.operationConfig.listener.ResponseListener
import org.json.JSONObject
import java.util.*


/**
 * This class is used to call API
 */
object CallApi {
    private val TAG = CallApi::class.java.simpleName

    /**
     * This method is used to call post API
     * @param context the context is used to show progressbar and alert dialog
     * @param url the url string of API endpoint
     * @param params the params for post API call
     * @param showProgressBar the boolean to show progressbar or not
     * @param responseListener the response listener to notify API response
     */
    fun callPostApi(
        context: Context,
        url: String,
        params: HashMap<String, String>,
        showProgressBar: Boolean,
        responseListener: ResponseListener
    ) {
        if (showProgressBar) {
            Utils.showProgressBar(context)
        }

        AndroidNetworking.post(Endpoints.BASE_URL + url)
            .addBodyParameter(params)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener{
                override fun onResponse(response: JSONObject?) {
                    if (showProgressBar) {
                        Utils.dismissProgressBar()
                    }
                    if (response?.optString("status") == "success"
                        && response.optInt("status_code") == 200) {
                        responseListener.onSuccessResponse(response)
                    } else {
                        AlertDialogView.showAlert(
                            context,
                            context.getString(R.string.app_name),
                            context.getString(R.string.error_api_msg)
                            , context.getString(R.string.ok)
                        )?.show()
                        responseListener.onErrorResponse(context.getString(R.string.error_api_msg))
                    }
                }

                override fun onError(anError: ANError?) {
                    if (showProgressBar) {
                        Utils.dismissProgressBar()
                    }
                    var message: String = context.getString(R.string.error_api_msg)
                    if (anError?.errorCode == 0) {
                        message = anError.errorDetail
                    } else {
                        val jsonObject = JSONObject(anError!!.errorBody)
                        val errorObj = jsonObject.optJSONObject("error")
                        if (errorObj != null) {
                            val stringArray = errorObj.optJSONArray("application_id")
                            if (stringArray != null && stringArray.length() > 0) {
                                message = stringArray[0].toString()
                            }
                        }
                    }
                    AlertDialogView.showAlert(
                        context,
                        context.getString(R.string.app_name),
                        message,
                        context.getString(R.string.ok)
                    )?.show()
                    responseListener.onErrorResponse(anError)
                }
            })
    }
}
