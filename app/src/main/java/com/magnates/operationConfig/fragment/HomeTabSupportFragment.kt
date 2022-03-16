package com.magnates.operationConfig.fragment

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.magnates.operationConfig.adapter.HomePublicSupportGroupsListAdapter
import com.magnates.operationConfig.databinding.FragmentHomeTabSupportBinding
import com.magnates.operationConfig.utils.Utils

/**
 * A simple [Fragment] subclass.
 * Use the [HomeTabSupportFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeTabSupportFragment : BaseFragment(), View.OnClickListener {

    private var _binding: FragmentHomeTabSupportBinding? = null
    private val binding get() = _binding!!
    private lateinit var homePublicSupportGroupsListAdapter: HomePublicSupportGroupsListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeTabSupportBinding.inflate(inflater, container, false)
        bindView(_binding)
        return binding.root
    }

    /**
     * This method is used to initialization process of activity
     */
    override fun init() {
        binding.dynamicUIModel = Utils.dynamicUIModel
        onConfigurationChanged(resources.configuration)
    }

    /**
     * This method is called when device change orientation
     */
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        homePublicSupportGroupsListAdapter =
            HomePublicSupportGroupsListAdapter(requireContext(),
                arrayListOf("Help 4 Farmers", "Friends","Family Forever","Ex's Service Core","Fear No Fear"))
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){
            binding.rvHomeSupportGroupsList.apply {
                layoutManager = Utils.getGridLayoutManager(2,requireContext())
                adapter = homePublicSupportGroupsListAdapter
            }
        }else{
            binding.rvHomeSupportGroupsList.apply {
                layoutManager = Utils.getVerticalLayoutManager(requireContext())
                adapter = homePublicSupportGroupsListAdapter
            }
        }
    }

    /**
     * This method is called when user clicks on view
     */
    override fun onClick(view: View) { }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment HomeTabSupportFragment.
         */
        @JvmStatic
        fun newInstance() = HomeTabSupportFragment()
    }
}