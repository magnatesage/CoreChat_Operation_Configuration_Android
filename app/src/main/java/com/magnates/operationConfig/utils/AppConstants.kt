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

/**
 * This class contains all the Constants of the app
 */
object AppConstants {

    /**
     * This Constants are used to parse API call response
     */
    const val API_TAG = "_API_TAG"
    const val STATUS_CODE = "status_code"
    const val STATUS = "status"
    const val MESSAGE = "message"

    /**
     * This are common Constants
     */
    const val BOLD = "Bold"
    const val ITALIC = "Italic"
    const val BOLD_ITALIC = "BoldItalic"
    const val IMAGE = "Image"
    const val COLOR = "Color"
    const val HORIZONTAL = "Horizontal"
    const val VERTICAL = "Vertical"
    const val SHAPE_NONE = "0"
    const val SHAPE_SQUARE = "1"
    const val SHAPE_ROUNDED_CORNER = "2"
    const val SHAPE_CIRCLE = "3"
    const val SPLASH_SCREEN_LOGO_IMAGE_PATH = "splashScreenLogoImagePath"
    const val SPLASH_SCREEN_BG_IMAGE_PATH = "splashScreenBgImagePath"
    const val FLOATING_ICON_IMAGE_PATH ="floatingIconImagePath"
    const val CHAT_BOT_BG_IMAGE_PATH ="chatBotBgImagePath"
    const val SPLASH_SCREEN_LOGO_IMAGE = "splash_screen_logo_image.jpeg"
    const val SPLASH_SCREEN_BG_IMAGE = "splash_screen_bg_image.jpeg"
    const val FLOATING_ICON_IMAGE ="floating_icon_image.jpeg"
    const val CHAT_BOT_BG_IMAGE ="chat_bot_bg_image.jpeg"
    const val FROM_CHAT_BUTTON_CONFIG ="from_chat_button_config"
    const val FROM_CHAT_CARDVIEW_CONFIG ="from_chat_cardview_config"
    const val FROM_CHAT_CONVERSATION_BAR_CONFIG ="from_chat_conversation_bar_config"

    /**
     * This Constants are used for Chat Bubble shape
     */
    const val CHAT_BUBBLE_SQUARE = 1
    const val CHAT_BUBBLE_CORNER_CUT_UPPER = 2
    const val CHAT_BUBBLE_CORNER_CUT_LOWER = 3
    const val CHAT_BUBBLE_CORNER_RADIUS = 4
    const val CHAT_BUBBLE_TWO_CORNER_RADIUS = 5
    const val CHAT_BUBBLE_ROUND = 6
    const val CHAT_BUBBLE_ARROW_OUTSIDE = 7
    const val CHAT_BUBBLE_ARROW_INSIDE = 8
    const val CHAT_BUBBLE_ARROW_BOTH = 9
    const val CHAT_BUBBLE_SLOPE = 10
    const val CHAT_BUBBLE_LEFT_OR_RIGHT_ROUND = 11
    const val CHAT_BUBBLE_TALKIE_TOP = 12
    const val CHAT_BUBBLE_TALKIE_BOTTOM = 13
    const val CHAT_BUBBLE_WITH_DOTS = 14

    /**
     * This Constants are used for Button shape
     */
    const val BUTTON_ROUND = 1
    const val BUTTON_SQUARE = 2
    const val BUTTON_TOP_LEFT_CUT = 3
    const val BUTTON_BOTTOM_LEFT_CUT = 5
    const val BUTTON_TOP_RIGHT_BOTTOM_LEFT_RADIUS = 9
    const val BUTTON_LEFT_ROUND = 13
    const val BUTTON_RIGHT_ROUND = 14
    const val BUTTON_CORNER_RADIUS = 18
    const val BUTTON_ARROW_LEFT_INSIDE = 21
    const val BUTTON_ARROW_BOTH = 22
    const val BUTTON_ARROW_RIGHT_INSIDE = 23

    /**
     * This Constants are used for CardView shape
     */
    const val CARDVIEW_SQUARE = 2
    const val CARDVIEW_CORNER_RADIUS_SMALL = 24
    const val CARDVIEW_TOP_LEFT_BOTTOM_RIGHT_RADIUS_SMALL = 12
    const val CARDVIEW_TOP_RIGHT_BOTTOM_LEFT_RADIUS_SMALL = 10
    const val CARDVIEW_FOUR_CORNER_CUT_SMALL = 25
    const val CARDVIEW_TOP_LEFT_BOTTOM_RIGHT_CUT_SMALL = 27
    const val CARDVIEW_TOP_RIGHT_BOTTOM_LEFT_CUT_SMALL = 26
    const val CARDVIEW_FOUR_CORNER_CUT_BIG = 8
    const val CARDVIEW_TOP_LEFT_BOTTOM_RIGHT_CUT_BIG = 6
    const val CARDVIEW_TOP_RIGHT_BOTTOM_LEFT_CUT_BIG = 7
    const val CARDVIEW_CORNER_RADIUS_BIG = 18
    const val CARDVIEW_TOP_LEFT_BOTTOM_RIGHT_RADIUS_BIG = 11
    const val CARDVIEW_TOP_RIGHT_BOTTOM_LEFT_RADIUS_BIG = 9

    /**
     * This Constants are used for Conversation bar style
     */
    const val ROUNDED = "style1"
    const val SQUARE = "style2"
    const val SEMI_ROUNDED = "style3"
    const val OVAL = "style4"
    const val CIRCLE = "style5"


    const val DASHBOARD = "dashboard"
    const val WAITING = "waiting"
    const val HISTORY = "history"
    const val LIVE_CHAT = "live_chat"
    const val NOTIFICATION = "notification"
}