package com.ssafy.green_plate.src.main.mypage

import android.content.Context
import android.os.Bundle
import android.view.View
import com.ssafy.green_plate.R
import com.ssafy.green_plate.src.main.SubDressingAdapter
import com.ssafy.green_plate.src.main.SubToppingAdapter
import com.ssafy.green_plate.config.BaseFragment
import com.ssafy.green_plate.databinding.FragmentOrderHistoryDetailBinding
import com.ssafy.green_plate.src.main.MainActivity

class OrderHistoryDetailFragment : BaseFragment<FragmentOrderHistoryDetailBinding>(
    FragmentOrderHistoryDetailBinding::bind,
    R.layout.fragment_order_history_detail
){

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