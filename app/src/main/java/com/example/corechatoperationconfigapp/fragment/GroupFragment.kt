package com.example.corechatoperationconfigapp.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.corechatoperationconfigapp.R
import com.example.corechatoperationconfigapp.adapter.PublicGroupAdapter
import com.example.corechatoperationconfigapp.adapter.SupportGroupAdapter
import com.example.corechatoperationconfigapp.databinding.FragmentGroupBinding
import com.example.corechatoperationconfigapp.utils.Utils

/**
 * A simple [Fragment] subclass.
 * Use the [GroupFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GroupFragment : BaseFragment() {

    private var _binding: FragmentGroupBinding? = null
    private val binding get() = _binding!!
    private lateinit var publicGroupAdapter: PublicGroupAdapter
    private lateinit var supportGroupAdapter: SupportGroupAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentGroupBinding.inflate(inflater, container, false)
        bindView(_binding)
        return binding.root
    }

    /**
     * This method is used to initialization process of activity
     */
    @SuppressLint("SetTextI18n")
    override fun init() {
        binding.dynamicUIModel = Utils.dynamicUIModel
        publicGroupAdapter = PublicGroupAdapter(requireContext(),
            arrayListOf("Help 4 Farmers - YorkShire", "Friends","Family Forever","Ex's Service Core","Fear No Fear"))
        binding.rvGroupPublicGroupsList.apply {
            layoutManager = Utils.getHorizontalLayoutManager(requireContext())
            adapter = publicGroupAdapter
        }

        supportGroupAdapter = SupportGroupAdapter(requireContext(),
            arrayListOf("Help 4 Farmers - YorkShire", "Friends","Family Forever","Ex's Service Core","Fear No Fear"))
        binding.rvGroupSupportGroupsList.apply {
            layoutManager = Utils.getHorizontalLayoutManager(requireContext())
            adapter = supportGroupAdapter
        }

        binding.tvPublicGroup.text = Utils.dynamicUIModel!!.dashboard.mainTab2.subGroup1.textValue + " " + context?.getString(R.string.group)
        binding.tvSupportGroup.text = Utils.dynamicUIModel!!.dashboard.mainTab2.subGroup2.textValue + " " + context?.getString(R.string.group)
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
         * @return A new instance of fragment GroupFragment.
         */
        @JvmStatic
        fun newInstance() = GroupFragment()
    }
}