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

import android.util.Log
import com.magnates.operationConfig.BuildConfig

/**
 * This Log class is used to print logs in debug build only
 */
object AppLog {
    private val TAG = AppLog::class.java.simpleName
    private val DO_LOG: Boolean = BuildConfig.DEBUG

    /**
     * This method is used to print message
     * @param msg the text to print
     */
    fun print(msg: String?) {
        if (DO_LOG) {
            println(msg)
        }
    }

    /**
     * This method is used to print title and message
     * @param title the title to print
     * @param msg the message to print
     */
    fun print(title: String, msg: String) {
        if (DO_LOG) {
            println("$title :: $msg")
        }
    }

    /**
     * This method is used to print tag, title and message
     * @param tag the name of class to print
     * @param title the title to print
     * @param msg the message to print
     */
    fun print(tag: String, title: String, msg: String) {
        if (DO_LOG) {
            println("$tag :: $title :: $msg")
        }
    }

    /**
     * This method is used to print verbose log
     * @param title the title to print
     * @param msg the message to print
     */
    fun v(title: String?, msg: String) {
        if (DO_LOG) {
            Log.v(title, msg)
        }
    }

    /**
     * This method is used to print verbose log
     * @param title the title to print
     * @param msg the message to print
     * @param throwable Throwable to print
     */
    fun v(
        title: String?,
        msg: String?,
        throwable: Throwable?
    ) {
        if (DO_LOG) {
            Log.v(title, msg, throwable)
        }
    }

    /**
     * This method is used to print debug log
     * @param title the title to print
     * @param msg the message to print
     */
    fun d(title: String?, msg: String) {
        if (DO_LOG) {
            Log.d(title, msg)
        }
    }

    /**
     * This method is used to print debug log
     * @param title the title to print
     * @param msg the message to print
     * @param throwable Throwable to print
     */
    fun d(
        title: String?,
        msg: String?,
        throwable: Throwable?
    ) {
        if (DO_LOG) {
            Log.d(title, msg, throwable)
        }
    }

    /**
     * This method is used to print warn log
     * @param title the title to print
     * @param msg the message to print
     */
    fun w(title: String?, msg: String) {
        if (DO_LOG) {
            Log.w(title, msg)
        }
    }

    /**
     * This method is used to print warn log
     * @param title the title to print
     * @param throwable Throwable to print
     */
    fun w(title: String?, throwable: Throwable?) {
        if (DO_LOG) {
            Log.w(title, throwable)
        }
    }

    /**
     * This method is used to print warn log
     * @param title the title to print
     * @param msg the message to print
     * @param throwable Throwable to print
     */
    fun w(
        title: String?,
        msg: String?,
        throwable: Throwable?
    ) {
        if (DO_LOG) {
            Log.w(title, msg, throwable)
        }
    }

    /**
     * This method is used to print info log
     * @param title the title to print
     * @param msg the message to print
     * @param throwable Throwable to print
     */
    fun i(
        title: String?,
        msg: String?,
        throwable: Throwable?
    ) {
        if (DO_LOG) {
            Log.i(title, msg, throwable)
        }
    }

    /**
     * This method is used to print info log
     * @param title the title to print
     * @param msg the message to print
     */
    fun i(title: String?, msg: String) {
        if (DO_LOG) {
            Log.i(title, msg)
        }
    }

    /**
     * This method is used to print error log
     * @param title the title to print
     * @param msg the message to print
     */
    fun e(title: String?, msg: String) {
        if (DO_LOG) {
            Log.e(title, msg)
        }
    }

    /**
     * This method is used to print error log
     * @param title the title to print
     * @param e Exception to print
     */
    fun e(title: String?, e: Exception) {
        if (DO_LOG) {
            Log.e(title, "Exception :: $e")
        }
    }

    /**
     * This method is used to print error log
     * @param title the title to print
     * @param t Throwable to print
     */
    fun e(title: String?, t: Throwable) {
        if (DO_LOG) {
            Log.e(title, "Throwable :: $t")
        }
    }

    /**
     * This method is used to print error log
     * @param title the title to print
     * @param msg the message to print
     * @param e Exception to print
     */
    fun e(title: String?, msg: String?, e: Exception?) {
        if (DO_LOG) {
            Log.e(title, msg, e)
        }
    }

    /**
     * This method is used to print error log
     * @param title the title to print
     * @param msg the message to print
     * @param t Throwable to print
     */
    fun e(title: String?, msg: String?, t: Throwable?) {
        if (DO_LOG) {
            Log.e(title, msg, t)
        }
    }
}
