package com.example.corechatoperationconfigapp.model

import com.google.gson.annotations.SerializedName

data class DynamicUIModel(

    @SerializedName("company_id")
    var companyId: String,

    @SerializedName("font_family")
    var fontFamily: String,

    @SerializedName("theme_color")
    var themeColor: ThemeColor,

    @SerializedName("font_size")
    var fontSize: FontSize,

    @SerializedName("splash")
    var splash: Splash,

    @SerializedName("side_menu")
    var sideMenuList: ArrayList<MenuItem>,

    @SerializedName("bottom_tab_bar")
    var bottomMenuList: ArrayList<MenuItem>,

    @SerializedName("dashboard")
    var dashboard: Dashboard,

    @SerializedName("icons")
    var icons: Icons,

    @SerializedName("chat")
    var chat: Chat
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
    var fontType: String,
)

data class Dashboard(
    @SerializedName("tab_1")
    var tab1: TextModel,

    @SerializedName("tab_2")
    var tab2: TextModel
)

data class TextModel(
    @SerializedName("text_varue")
    var textValue: String,

    @SerializedName("font_type")
    var fontType: String
)

data class Icons(
    @SerializedName("profile")
    var profile: IconModel,

    @SerializedName("notification_selected")
    var notificationSelected: IconModel,

    @SerializedName("notification_unselected")
    var notificationUnselected: IconModel,

    @SerializedName("favourites_selected")
    var favouritesSelected: IconModel,

    @SerializedName("favourites_unselected")
    var favouritesUnselected: IconModel,

    @SerializedName("notes")
    var notes: IconModel,

    @SerializedName("live")
    var live: IconModel,

    @SerializedName("timer")
    var timer: IconModel,

    @SerializedName("message")
    var message: IconModel
)

data class IconModel(
    @SerializedName("icon_type")
    var iconType: String,

    @SerializedName("icon_value")
    var iconValue: String,
)

data class Chat(
    @SerializedName("chat_bubble")
    var chatBubble: ChatBubble,

    @SerializedName("button")
    var button: Button,

    @SerializedName("card_view")
    var cardView: CardView,

    @SerializedName("conversation_bar")
    var conversationBar: ConversationBar
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
    var normalTextColor: String,
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