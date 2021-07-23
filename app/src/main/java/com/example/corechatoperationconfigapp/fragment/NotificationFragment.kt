package com.example.corechatoperationconfigapp.fragment

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.corechatoperationconfigapp.R
import com.example.corechatoperationconfigapp.adapter.NotificationAdapter
import com.example.corechatoperationconfigapp.databinding.FragmentNotificationBinding
import com.example.corechatoperationconfigapp.utils.Utils

/**
 * A simple [Fragment] subclass.
 * Use the [NotificationFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NotificationFragment : BaseFragment(), View.OnClickListener {

    private var _binding: FragmentNotificationBinding? = null
    private val binding get() = _binding!!
    private lateinit var notificationAdapter: NotificationAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentNotificationBinding.inflate(inflater, container, false)
        bindView(_binding)
        return binding.root
    }

    /**
     * This method is called when device change orientation
     */
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        bindView(_binding)
    }

    /**
     * This method is used to initialization process of activity
     */
    override fun init() {
        setListeners()
        binding.dynamicUIModel = Utils.dynamicUIModel
        binding.toolbarLayout.toolbarTitle.text = getString(R.string.notification)
        notificationAdapter = NotificationAdapter(requireActivity(),arrayOf(
            "Let's Talk Family",
            "User V",
            "User Z",
            "User X",
            "User N",
            "New Day",
        ).toList())
        binding.rvNotification.apply {
            layoutManager = Utils.getVerticalLayoutManager(requireContext())
            adapter = notificationAdapter
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
         * @return A new instance of fragment NotificationFragment.
         */
        @JvmStatic
        fun newInstance() = NotificationFragment()
    }
}