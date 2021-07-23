package com.example.corechatoperationconfigapp.fragment

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.corechatoperationconfigapp.R
import com.example.corechatoperationconfigapp.adapter.LiveChatListAdapter
import com.example.corechatoperationconfigapp.databinding.FragmentLiveChatBinding
import com.example.corechatoperationconfigapp.utils.Utils

/**
 * A simple [Fragment] subclass.
 * Use the [LiveChatFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LiveChatFragment : BaseFragment(), View.OnClickListener {

    private var _binding: FragmentLiveChatBinding? = null
    private lateinit var liveChatListAdapter: LiveChatListAdapter
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentLiveChatBinding.inflate(inflater, container, false)
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
        binding.toolbarLayout.toolbarTitle.text = getString(R.string.live_chat)
        liveChatListAdapter = LiveChatListAdapter(
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
        binding.rvLiveChatList.apply {
            layoutManager = Utils.getVerticalLayoutManager(requireContext())
            adapter = liveChatListAdapter
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
         * @return A new instance of fragment LiveChatFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() = LiveChatFragment()
    }
}