package com.example.corechatoperationconfigapp.fragment

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.corechatoperationconfigapp.R
import com.example.corechatoperationconfigapp.adapter.ViewPagerAdapter
import com.example.corechatoperationconfigapp.customviews.CustomTextView
import com.example.corechatoperationconfigapp.databinding.FragmentHomeBinding
import com.example.corechatoperationconfigapp.utils.Utils
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

class HomeFragment : BaseFragment(), TabLayout.OnTabSelectedListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var pagerAdapter: ViewPagerAdapter
    private lateinit var titleList: List<String>
    private lateinit var fragmentList: List<Fragment>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        bindView(_binding)
        return binding.root
    }

    /**
     * This method is used to initialization process of activity
     */
    override fun init() {
        binding.dynamicUIModel = Utils.dynamicUIModel
        binding.subTabLayout.addOnTabSelectedListener(this)
        titleList = arrayListOf(
            Utils.dynamicUIModel?.dashboard?.mainTab1?.subTab1?.textValue!!,
            Utils.dynamicUIModel?.dashboard?.mainTab1?.subTab2?.textValue!!
        )
        fragmentList =
            arrayListOf(HomeTabPublicFragment.newInstance(), HomeTabSupportFragment.newInstance())
        setUpViewPagerAdapter()
        customizeTabLayout()
    }

    /**
     * This method is called when user clicks on view
     */
    override fun onClick(view: View) {}

    /**
     * This method is called for set the adapter in viewpager
     */
    private fun setUpViewPagerAdapter() {
        if (activity != null) {
            pagerAdapter = ViewPagerAdapter(requireActivity(), fragmentList, titleList.size)
            binding.subViewPager.adapter = pagerAdapter
            TabLayoutMediator(binding.subTabLayout, binding.subViewPager) { tab, position ->
                tab.text = titleList[position]
            }.attach()
        }
    }

    /**
     * This method is used for customize tab layout
     */
    private fun customizeTabLayout() {
        binding.subTabLayout.setSelectedTabIndicatorColor(Color.parseColor(Utils.dynamicUIModel?.themeColor?.primaryColor))

        for (i in 0 until binding.subTabLayout.tabCount) {
            val customTextView = LayoutInflater.from(requireContext())
                .inflate(R.layout.custom_tab, null) as CustomTextView
            Utils.setTextSizeInSSP(
                customTextView,
                Utils.getFontSizeInSSP(Utils.dynamicUIModel?.fontSize?.tab!!)
            )
            customTextView.setCustomFont("${Utils.getBoldItalicFontType(Utils.dynamicUIModel?.dashboard?.mainTab1?.fontType)}.ttf")

            when (i) {
                0 -> {
                    customTextView.text =
                        Utils.dynamicUIModel?.dashboard?.mainTab1?.subTab1?.textValue!!
                    customTextView.setTextColor(Color.parseColor(Utils.dynamicUIModel?.themeColor?.primaryColor))
                }
                1 -> {
                    customTextView.text =
                        Utils.dynamicUIModel?.dashboard?.mainTab1?.subTab2?.textValue
                    customTextView.setTextColor(requireContext().getColor(R.color.gray))
                }
            }
            binding.subTabLayout.getTabAt(i)?.customView = customTextView
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment HomeFragment.
         */
        @JvmStatic
        fun newInstance() = HomeFragment()
    }

    /**
     * This method is called when tab selected
     */
    override fun onTabSelected(tab: TabLayout.Tab?) {
        if (tab?.customView is CustomTextView){
            (tab.customView as CustomTextView).setTextColor(Color.parseColor(Utils.dynamicUIModel?.themeColor?.primaryColor))
        }
    }

    /**
     * This method is called when tab unselected
     */
    override fun onTabUnselected(tab: TabLayout.Tab?) {
        if (tab?.customView is CustomTextView){
            (tab.customView as CustomTextView).setTextColor(requireContext().getColor(R.color.gray))
        }
    }

    /**
     * This method is called when tab reselected
     */
    override fun onTabReselected(tab: TabLayout.Tab?) { }
}