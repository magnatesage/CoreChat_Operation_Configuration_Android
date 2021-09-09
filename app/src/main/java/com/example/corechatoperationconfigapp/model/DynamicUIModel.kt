package com.example.corechatoperationconfigapp.model

import com.google.gson.annotations.SerializedName

data class DynamicUIModel(

    @SerializedName("company_id")
    val companyId: String,
    @SerializedName("font_family")
    var fontFamily: String,
    @SerializedName("setting")
    var setting: Setting,
    @SerializedName("theme_color")
    val themeColor: ThemeColor,
    @SerializedName("font_size")
    val fontSize: FontSize,
    @SerializedName("splash")
    val splash: Splash,
    @SerializedName("side_menu")
    val sideMenu: ArrayList<MenuItem>,
    @SerializedName("bottom_tab_bar")
    val bottomTabBar: ArrayList<MenuItem>,
    @SerializedName("dashboard")
    val dashboard: Dashboard,
    @SerializedName("chat_list_members")
    val chatListMembers: ChatListMembers,
    @SerializedName("icons")
    val icons: Icons,
    @SerializedName("chat")
    val chat: Chat
)

data class Setting(
    @SerializedName("backgroundAppTimeoutNotUsedMinutes")
    var backgroundAppTimeoutNotUsedMinutes: Int,

    @SerializedName("foregroundAppTimeoutNotUsedMinutes")
    var foregroundAppTimeoutNotUsedMinutes: Int
)

data class ThemeColor(
    @SerializedName("primary_color")
    var primaryColor: String,
    @SerializedName("secondary_color")
    var secondaryColor: String,
    @SerializedName("common_font_color")
    var commonFontColor: String
)

data class FontSize(
    @SerializedName("text_field")
    var textField: String,
    @SerializedName("button")
    var button: String,
    @SerializedName("title_header")
    var titleHeader: String,
    @SerializedName("header")
    var header: String,
    @SerializedName("sub_header")
    var subHeader: String,
    @SerializedName("side_menu")
    var sideMenu: String,
    @SerializedName("bottom_menu")
    var bottomMenu: String,
    @SerializedName("tab")
    var tab: String,
    @SerializedName("common")
    var common: String
)

data class Splash(
    @SerializedName("logo_url")
    var logoUrl: String,
    @SerializedName("logo_bg_shape")
    var logoBgShape: String,
    @SerializedName("logo_bg_drop_shadow")
    var logoBgDropShadow: Boolean,
    @SerializedName("org_name")
    var orgName: String,
    @SerializedName("org_name_font_color")
    var orgNameFontColor: String,
    @SerializedName("org_name_font_size")
    var orgNameFontSize: String,
    @SerializedName("org_name_font_type")
    var orgNameFontType: String,
    @SerializedName("splash_screen_bg_color")
    var splashScreenBgColor: String,
    @SerializedName("splash_screen_bg_image_url")
    var splashScreenBgImageUrl: String,
    @SerializedName("splash_screen_bg_type")
    var splashScreenBgType: String
)

data class MenuItem(
    @SerializedName("text_value")
    var textValue: String,
    @SerializedName("icon_type")
    var iconType: String,
    @SerializedName("icon_value")
    var iconValue: String,
    @SerializedName("font_type")
    var fontType: String
)

data class Dashboard(
    @SerializedName("main_tab_1")
    val mainTab1: MainTab1,
    @SerializedName("main_tab_2")
    val mainTab2: MainTab2
)

data class MainTab1(
    @SerializedName("text_value")
    var textValue: String,
    @SerializedName("font_type")
    var fontType: String,
    @SerializedName("sub_tab_1")
    val subTab1: TextStyle,
    @SerializedName("sub_tab_2")
    val subTab2: TextStyle
)

data class MainTab2(
    @SerializedName("text_value")
    var textValue: String,
    @SerializedName("font_type")
    var fontType: String,
    @SerializedName("sub_group_1")
    val subGroup1: TextStyle,
    @SerializedName("sub_group_2")
    val subGroup2: TextStyle
)

data class ChatListMembers(
    @SerializedName("tab_1")
    val tab1: TextStyle,
    @SerializedName("tab_2")
    val tab2: TextStyle,
    @SerializedName("tab_3")
    val tab3: TextStyle
)

data class TextStyle(
    @SerializedName("text_value")
    var textValue: String,
    @SerializedName("font_type")
    var fontType: String,
)

data class Icons(
    @SerializedName("profile")
    val profile: IconStyle,
    @SerializedName("live")
    val live: IconStyle,
    @SerializedName("tickets")
    val tickets: IconStyle,
    @SerializedName("bot")
    val bot: IconStyle,
    @SerializedName("message")
    val message: IconStyle,
    @SerializedName("timer")
    val timer: IconStyle,
    @SerializedName("disconnect")
    val disconnect: IconStyle,
    @SerializedName("schedule")
    val schedule: IconStyle,
    @SerializedName("all_time")
    val allTime: IconStyle,
    @SerializedName("user")
    val user: IconStyle,
    @SerializedName("info")
    val info: IconStyle,
    @SerializedName("notes")
    val notes: IconStyle,
    @SerializedName("remove")
    val remove: IconStyle,
    @SerializedName("training_mode")
    val trainingMode: IconStyle,
    @SerializedName("end_session")
    val endSession: IconStyle,
    @SerializedName("whisper")
    val whisper: IconStyle,
    @SerializedName("interact")
    val interact: IconStyle,
    @SerializedName("check")
    val check: IconStyle,
    @SerializedName("bot_start")
    val botStart: IconStyle,
    @SerializedName("user_location")
    val userLocation: IconStyle,
    @SerializedName("manager")
    val manager: IconStyle,
    @SerializedName("supervisor")
    val supervisor: IconStyle,
    @SerializedName("agent")
    val agent: IconStyle,
    @SerializedName("green_flag")
    val greenFlag: IconStyle,
    @SerializedName("umber_flag")
    val umberFlag: IconStyle,
    @SerializedName("red_flag")
    val redFlag: IconStyle,
    @SerializedName("online")
    val online: IconStyle,
    @SerializedName("offline")
    val offline: IconStyle,
    @SerializedName("upcoming")
    val upcoming: IconStyle,
    @SerializedName("pause")
    val pause: IconStyle,
    @SerializedName("double_tick")
    val doubleTick: IconStyle
)

data class IconStyle(
    @SerializedName("icon_type")
    val iconType: String,
    @SerializedName("icon_value")
    var iconValue: String,
    @SerializedName("icon_color")
    var iconColor: String
)

data class Chat(
    @SerializedName("chat_bubble")
    val chatBubble: ChatBubble,
    @SerializedName("button")
    val button: Button,
    @SerializedName("card_view")
    val cardView: CardView,
    @SerializedName("conversation_bar")
    val conversationBar: ConversationBar,
    @SerializedName("top_menu")
    val topMenu: ArrayList<String>,
    @SerializedName("bottom_menu")
    val bottomMenu: ArrayList<BottomMenuItem>,
)

data class ChatBubble(
    @SerializedName("card_bg_drop_shadow")
    var cardBgDropShadow: Boolean,
    @SerializedName("chat_screen_bg_color")
    var chatScreenBgColor: String,
    @SerializedName("chat_screen_bg_image_url")
    var chatScreenBgImageUrl: String,
    @SerializedName("chat_screen_bg_type")
    var chatScreenBgType: String,
    @SerializedName("chat_bubble_style")
    var chatBubbleStyle: Int,
    @SerializedName("receiver_chat_bubble_color")
    var receiverChatBubbleColor: String,
    @SerializedName("receiver_text_color")
    var receiverTextColor: String,
    @SerializedName("sender_chat_bubble_color")
    var senderChatBubbleColor: String,
    @SerializedName("sender_text_color")
    var senderTextColor: String
)

data class Button(
    @SerializedName("button_bg_drop_shadow")
    var buttonBgDropShadow: Boolean,
    @SerializedName("button_placement_style")
    var buttonPlacementStyle: String,
    @SerializedName("button_shape_id")
    var buttonShapeId: Int,
    @SerializedName("clicked_border_color")
    var clickedBorderColor: String,
    @SerializedName("clicked_border_size")
    var clickedBorderSize: String,
    @SerializedName("clicked_button_color")
    var clickedButtonColor: String,
    @SerializedName("clicked_text_color")
    var clickedTextColor: String,
    @SerializedName("normal_border_color")
    var normalBorderColor: String,
    @SerializedName("normal_border_size")
    var normalBorderSize: String,
    @SerializedName("normal_button_color")
    var normalButtonColor: String,
    @SerializedName("normal_text_color")
    var normalTextColor: String
)

data class CardView(
    @SerializedName("cardview_bg_drop_shadow")
    var cardViewBgDropShadow: Boolean,
    @SerializedName("cardview_border_color")
    var cardViewBorderColor: String,
    @SerializedName("cardview_border_size")
    var cardViewBorderSize: String,
    @SerializedName("cardview_shape_id")
    var cardViewShapeId: Int,
    @SerializedName("cardview_footer_button_bg_color")
    var cardViewFooterButtonBgColor: String,
    @SerializedName("cardview_footer_button_text_color")
    var cardViewFooterButtonTextColor: String,
    @SerializedName("cardview_footer_button_text_size")
    var cardViewFooterButtonTextSize: String,
    @SerializedName("cardview_header_bg_color")
    var cardViewHeaderBgColor: String,
    @SerializedName("cardview_header_text_color")
    var cardViewHeaderTextColor: String,
    @SerializedName("cardview_header_text_size")
    var cardViewHeaderTextSize: String
)

data class ConversationBar(
    @SerializedName("conversation_bar_shape")
    var conversationBarShape: String,
    @SerializedName("floating_icon_url")
    var floatingIconUrl: String
)

data class BottomMenuItem(
    @SerializedName("text_value")
    val textValue: String,
    @SerializedName("icon_type")
    val iconType: String,
    @SerializedName("icon_value")
    val iconValue: String
)