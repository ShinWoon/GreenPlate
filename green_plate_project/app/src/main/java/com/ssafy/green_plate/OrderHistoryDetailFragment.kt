package com.ssafy.green_plate

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ssafy.green_plate.config.BaseFragment
import com.ssafy.green_plate.databinding.FragmentOrderHistoryDetailBinding
import com.ssafy.green_plate.src.main.MainActivity

class OrderHistoryDetailFragment : BaseFragment<FragmentOrderHistoryDetailBinding>(FragmentOrderHistoryDetailBinding::bind, R.layout.fragment_order_history_detail){

    private lateinit var mainActivity : MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var items = listOf("item1", "item2", "item3")
        binding.orderHistorySubDressingRv.apply {
            adapter = SubDressingAdapter(mainActivity, items)
        }

        binding.orderHistorySubToppingRv.apply {
            adapter = SubToppingAdapter(mainActivity, items)
        }
    }
}