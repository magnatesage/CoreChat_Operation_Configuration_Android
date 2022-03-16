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
import android.content.SharedPreferences

/**
 * This class is used for Application Shared Preferences
 */
object AppPref {
    private var sharedPreferences: SharedPreferences? = null

    /**
     * This method is used to initialize shared preferences
     * @param context the context is used to initialize shared preferences
     */
    private fun openPref(context: Context) {
        sharedPreferences = context.getSharedPreferences(
            "AppPref",
            Context.MODE_PRIVATE
        )
    }

    /**
     * This method is used to get string preference value
     * @param context the context is used to initialize shared Preference
     * @param key The name of the preference to retrieve
     * @param defaultValue Value to return if this preference does not exist
     * @return Returns the string preference value if it exists, or defaultValue
     */
    fun getValue(
        context: Context, key: String?,
        defaultValue: String?
    ): String? {
        openPref(context)
        val result = sharedPreferences!!.getString(key, defaultValue)
        sharedPreferences = null
        return result
    }

    /**
     * This method is used to set string preference value
     * @param context the context is used to initialize shared Preference
     * @param key The name of the preference to modify
     * @param value The new value for the preference.  Passing {@code null}
     *    for this argument is equivalent to calling {@link #remove(String)} with
     *    this key.
     */
    fun setValue(context: Context, key: String?, value: String?) {
        openPref(context)
        val prefsPrivateEditor = sharedPreferences!!.edit()
        prefsPrivateEditor.putString(key, value)
        prefsPrivateEditor.apply()
        sharedPreferences = null
    }

    /**
     * This method is used to get integer preference value
     * @param context the context is used to initialize shared Preference
     * @param key The name of the preference to retrieve
     * @param defaultValue Value to return if this preference does not exist
     * @return Returns the integer preference value if it exists, or defaultValue
     */
    fun getValue(context: Context, key: String?, defaultValue: Int): Int {
        openPref(context)
        val result = sharedPreferences!!.getInt(key, defaultValue)
        sharedPreferences = null
        return result
    }

    /**
     * This method is used to set integer preference value
     * @param context the context is used to initialize shared Preference
     * @param key The name of the preference to modify
     * @param value The new value for the preference.  Passing {@code null}
     *    for this argument is equivalent to calling {@link #remove(String)} with
     *    this key.
     */
    fun setValue(context: Context, key: String?, value: Int) {
        openPref(context)
        val prefsPrivateEditor = sharedPreferences!!.edit()
        prefsPrivateEditor.putInt(key, value)
        prefsPrivateEditor.apply()
        sharedPreferences = null
    }

    /**
     * This method is used to delete all preference value
     *
     * @param context the context is used to initialize shared Preference
     */
    fun deleteAllSharedPrefData(context: Context) {
        openPref(context)
        sharedPreferences?.edit()?.clear()?.apply()
    }

}
