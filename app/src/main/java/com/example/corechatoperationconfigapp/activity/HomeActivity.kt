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

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.corechatoperationconfigapp.R
import com.example.corechatoperationconfigapp.adapter.SideMenuListAdapter
import com.example.corechatoperationconfigapp.customviews.CustomTextView
import com.example.corechatoperationconfigapp.databinding.ActivityHomeBinding
import com.example.corechatoperationconfigapp.fragment.*
import com.example.corechatoperationconfigapp.utils.AppConstants
import com.example.corechatoperationconfigapp.utils.Extensions.color
import com.example.corechatoperationconfigapp.utils.Utils

class HomeActivity : BaseActivity(), View.OnClickListener {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    /**
     * This method is used to initialization process of activity
     */
    override fun init() {
        context = this@HomeActivity
        binding.dynamicUIModel = Utils.dynamicUIModel
        setFragment(DashboardFragment.newInstance(), AppConstants.DASHBOARD)
        setNavigationDrawerLayout()
    }

    /**
     * This method is called when user clicks on view
     */
    override fun onClick(view: View) {
        when(view){
            binding.btnBack ->{
                onBackPressed()
            }
            binding.btnNext -> {
                startActivity(Intent(context, WaitingActivity::class.java))
            }
        }
    }

    /**
     * This method is used for set the fragments in view
     */
    fun setFragment(fragment: Fragment, name: String) {
        setBottomMenuIcon()
        val currentFragment = Utils.getCurrentFragment(this)
        if (currentFragment?.tag != fragment.javaClass.toString()) {
            setActiveTab(fragment)
            val fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(binding.fragmentContainer.id, fragment,fragment.javaClass.toString())
            fragmentTransaction.commit()
        }
    }

    /**
     * This method is used for add the fragments in view
     */
    fun addFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(binding.fragmentContainer.id, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    /**
     * This method is used for define active tab
     */
    private fun setActiveTab(fragment: Fragment) {
        setBottomViewSelection(binding.iconDashboard,binding.tvDashboard,false)
        setBottomViewSelection(binding.iconWaiting,binding.tvWaiting,false)
        setBottomViewSelection(binding.iconHistory,binding.tvHistory,false)
        setBottomViewSelection(binding.iconNotification,binding.tvNotification,false)
        setBottomViewSelection(binding.iconLiveChat,binding.tvLiveChat,false)

        when (fragment) {
            is DashboardFragment -> {
                setBottomViewSelection(binding.iconDashboard,binding.tvDashboard,true)
            }
        }
    }

    /**
     * This method is used to set bottom view tab ui based on user selection
     *
     * @param iconView
     * @param textView
     * @param isSelected
     */
    private fun setBottomViewSelection(
        iconView: CustomTextView,
        textView: CustomTextView,
        isSelected: Boolean
    ){
        val disableColor = color(R.color.gray)
        val activeColor = Color.parseColor(Utils.dynamicUIModel?.themeColor?.secondaryColor)
        binding.iconDashboard.setTextColor(disableColor)
        binding.tvDashboard.setTextColor(disableColor)
        binding.iconWaiting.setTextColor(disableColor)
        binding.tvWaiting.setTextColor(disableColor)
        binding.iconHistory.setTextColor(disableColor)
        binding.tvHistory.setTextColor(disableColor)
        binding.iconNotification.setTextColor(disableColor)
        binding.tvNotification.setTextColor(disableColor)
        binding.iconLiveChat.setTextColor(disableColor)
        binding.tvLiveChat.setTextColor(disableColor)

        if (isSelected){
            iconView.setTextColor(activeColor)
            textView.setTextColor(activeColor)
        }else{
            iconView.setTextColor(disableColor)
            textView.setTextColor(disableColor)
        }
    }

    /**
     * This method is used to set bottom tab icons
     */
    private fun setBottomMenuIcon() {
        val bottomBarValue = binding.dynamicUIModel!!.bottomTabBar

        "&#x${bottomBarValue[0].iconValue}".also { binding.iconDashboard.text = it }
        binding.tvDashboard.setCustomFont("${Utils.getBoldItalicFontType(bottomBarValue[0].fontType)}.ttf")

        "&#x${bottomBarValue[1].iconValue}".also { binding.iconWaiting.text = it }
        binding.tvWaiting.setCustomFont("${Utils.getBoldItalicFontType(bottomBarValue[1].fontType)}.ttf")

        "&#x${bottomBarValue[3].iconValue}".also { binding.iconHistory.text = it }
        binding.tvHistory.setCustomFont("${Utils.getBoldItalicFontType(bottomBarValue[3].fontType)}.ttf")

        "&#x${bottomBarValue[4].iconValue}".also { binding.iconNotification.text = it }
        binding.tvNotification.setCustomFont("${Utils.getBoldItalicFontType(bottomBarValue[4].fontType)}.ttf")

        "&#x${bottomBarValue[2].iconValue}".also { binding.iconLiveChat.text = it }
        binding.tvLiveChat.setCustomFont("${Utils.getBoldItalicFontType(bottomBarValue[2].fontType)}.ttf")
    }

    /**
     * This method is used for set navigation drawer layout
     */
    @SuppressLint("UseCompatLoadingForDrawables")
    private fun setNavigationDrawerLayout() {
        binding.navLayout.tvLogout.setCustomFont("${Utils.getBoldItalicFontType(Utils.dynamicUIModel?.sideMenu?.get(0)?.fontType)}.ttf")

        binding.navLayout.rvSideMenu.apply {
            layoutManager = LinearLayoutManager(this@HomeActivity)
        }

        binding.navLayout.ivProfileLogo.setImageDrawable(getDrawable(R.drawable.ic_profile_user))
        binding.navLayout.tvOrgTitle.text = getString(R.string.lbl_group_hug)

        val sideMenuListAdapter = SideMenuListAdapter()
        binding.navLayout.rvSideMenu.adapter = sideMenuListAdapter

        val drawerToggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            R.string.drawer_open,
            R.string.drawer_close
        )
        binding.drawerLayout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()
        binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
    }

    /**
     * This method is called when user click on menu icon to open navigation drawer
     */
    fun openSideDrawer() {
        binding.drawerLayout.openDrawer(GravityCompat.START)
    }
}