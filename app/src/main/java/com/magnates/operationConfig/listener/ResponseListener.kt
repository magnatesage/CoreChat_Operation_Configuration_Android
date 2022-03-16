package com.magnates.operationConfig.listener

import org.json.JSONObject

/**
 * This interface is used to notify API response
 */
interface ResponseListener {
    fun onSuccessResponse(
        dataModel: JSONObject?
    )

    fun onErrorResponse(
        message: Any?
    )
}