package com.example.corechatoperationconfigapp.fragment

import android.content.res.Configuration
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.corechatoperationconfigapp.activity.BaseActivity
import com.example.corechatoperationconfigapp.adapter.HistoryChatListAdapter
import com.example.corechatoperationconfigapp.databinding.FragmentHistoryBinding
import com.example.corechatoperationconfigapp.utils.Utils

/**
 * A simple [Fragment] subclass.
 * Use the [HistoryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HistoryFragment : BaseFragment(), View.OnClickListener {

    private var _binding: FragmentHistoryBinding? = null
    private lateinit var historyChatListAdapter: HistoryChatListAdapter
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
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
        binding.searchLayout.baseActivity = context as BaseActivity?
        historyChatListAdapter = HistoryChatListAdapter(requireActivity(),
            arrayOf("User 1","User 2","User 3","User 4","User 5","User 6","User 7","User 8","User 9","User 10").toList())
        binding.rvHistoryChatList.apply {
            layoutManager = Utils.getVerticalLayoutManager(context)
            adapter = historyChatListAdapter
        }

        binding.searchLayout.svHistory.etSearch.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString().isNotEmpty()){
                    binding.searchLayout.svHistory.iconClose.visibility = View.VISIBLE
                    binding.searchLayout.svHistory.iconSearch.visibility = View.GONE
                }else{
                    binding.searchLayout.svHistory.iconClose.visibility = View.GONE
                    binding.searchLayout.svHistory.iconSearch.visibility = View.VISIBLE
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    /**
     * This method is called for set listeners
     */
    private fun setListeners(){
        binding.searchLayout.svHistory.iconClose.setOnClickListener(this)
    }

    /**
     * This method is called when user clicks on view
     */
    override fun onClick(view: View) {
        when(view) {
            binding.searchLayout.svHistory.iconClose -> {
                binding.searchLayout.svHistory.etSearch.setText("")
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment HistoryFragment.
         */
        @JvmStatic
        fun newInstance() = HistoryFragment()
    }
}