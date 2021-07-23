package com.example.corechatoperationconfigapp.fragment

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.corechatoperationconfigapp.R
import com.example.corechatoperationconfigapp.adapter.WaitingUsersListAdapter
import com.example.corechatoperationconfigapp.databinding.FragmentWaitingBinding
import com.example.corechatoperationconfigapp.utils.Utils

/**
 * A simple [Fragment] subclass.
 * Use the [WaitingFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WaitingFragment : BaseFragment(), View.OnClickListener {

    private var _binding: FragmentWaitingBinding? = null
    private val binding get() = _binding!!
    private lateinit var waitingUsersListAdapter: WaitingUsersListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentWaitingBinding.inflate(inflater, container, false)
        bindView(_binding)
        return binding.root
    }

    /**
     * This method is used to initialization process of activity
     */
    override fun init() {
        setListeners()
        binding.dynamicUIModel = Utils.dynamicUIModel
        binding.toolbarLayout.toolbarTitle.text = getString(R.string.waiting_users)
        onConfigurationChanged(resources.configuration)
    }

    /**
     * This method is called when device change orientation
     */
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        waitingUsersListAdapter = WaitingUsersListAdapter(
            requireActivity(),
            arrayOf(
                "User 1",
                "User 2",
                "User 3",
                "User 4",
                "User 5",
                "User 6",
                "User 7",
                "User 8",
                "User 9",
                "User 10"
            ).toList()
        )

        var portraitItem = 2
        var landscapeItem = 4

        if (Utils.isTablet(requireContext())) {
            if (!Utils.isLargeTablet(requireContext())) {
                portraitItem = 3
                landscapeItem = 4
            } else {
                portraitItem = 4
                landscapeItem = 6
            }
        }

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            binding.rvWaitingUsersList.apply {
                layoutManager = Utils.getGridLayoutManager(landscapeItem,requireContext())
                adapter = waitingUsersListAdapter
            }
        } else {
            binding.rvWaitingUsersList.apply {
                layoutManager = Utils.getGridLayoutManager(portraitItem,requireContext())
                adapter = waitingUsersListAdapter
            }
        }
    }

    /**
     * This method is called for set listeners
     */
    private fun setListeners() {
        binding.toolbarLayout.tvBackArrow.setOnClickListener(this)
    }

    /**
     * This method is called when user clicks on view
     */
    override fun onClick(view: View) {
        when (view) {
            binding.toolbarLayout.tvBackArrow -> {
                activity?.onBackPressed()
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment WaitingFragment.
         */
        @JvmStatic
        fun newInstance() = WaitingFragment()
    }
}