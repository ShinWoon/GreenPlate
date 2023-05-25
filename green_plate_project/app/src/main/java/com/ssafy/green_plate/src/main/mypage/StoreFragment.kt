package com.ssafy.green_plate.src.main.mypage

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssafy.green_plate.R
import com.ssafy.green_plate.config.BaseFragment
import com.ssafy.green_plate.databinding.FragmentStoreBinding
import com.ssafy.green_plate.src.main.MainActivity

class StoreFragment : BaseFragment<FragmentStoreBinding>(
    FragmentStoreBinding::bind,
    R.layout.fragment_store
) {

    private val storeViewModel: StoreViewModel by activityViewModels()
    lateinit var mainActivity: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onResume() {
        super.onResume()
        mainActivity.hideBottomNav(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        storeViewModel.getStoreInfo()
        storeViewModel.storeInfo.observe(viewLifecycleOwner) {
            binding.storePageRv.apply {
                adapter = StoreAdapter(requireContext(), it)
                layoutManager = LinearLayoutManager(mainActivity, LinearLayoutManager.VERTICAL, false)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mainActivity.hideBottomNav(false)
    }

}