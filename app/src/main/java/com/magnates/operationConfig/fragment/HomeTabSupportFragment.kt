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