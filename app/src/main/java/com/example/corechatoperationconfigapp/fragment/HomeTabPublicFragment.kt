package com.example.corechatoperationconfigapp.fragment

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.corechatoperationconfigapp.adapter.HomePublicSupportGroupsListAdapter
import com.example.corechatoperationconfigapp.databinding.FragmentHomeTabPublicBinding
import com.example.corechatoperationconfigapp.utils.Utils

/**
 * A simple [Fragment] subclass.
 * Use the [HomeTabPublicFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeTabPublicFragment : BaseFragment() {

    private var _binding: FragmentHomeTabPublicBinding? = null
    private val binding get() = _binding!!
    private lateinit var homePublicSupportGroupsListAdapter: HomePublicSupportGroupsListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeTabPublicBinding.inflate(inflater, container, false)
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
            binding.rvHomePublicGroupsList.apply {
                layoutManager = Utils.getGridLayoutManager(2,requireContext())
                adapter = homePublicSupportGroupsListAdapter
            }
        }else{
            binding.rvHomePublicGroupsList.apply {
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
         * @return A new instance of fragment HomeTabPublicFragment.
         */
        @JvmStatic
        fun newInstance() = HomeTabPublicFragment()
    }
}