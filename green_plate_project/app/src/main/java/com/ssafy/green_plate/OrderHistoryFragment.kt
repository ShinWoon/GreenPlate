package com.ssafy.green_plate

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssafy.green_plate.config.BaseFragment
import com.ssafy.green_plate.databinding.FragmentOrderHistoryBinding
import com.ssafy.green_plate.src.main.MainActivity

class OrderHistoryFragment : BaseFragment<FragmentOrderHistoryBinding>(FragmentOrderHistoryBinding::bind, R.layout.fragment_order_history) {

    private lateinit var mainActivity: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var items = listOf("item1", "item2", "item3", "item4", "item5")

        binding.orderHistoryRv.apply {
            adapter = OrderHistoryAdapter(mainActivity, items)
            layoutManager = LinearLayoutManager(mainActivity)
        }
    }

}