package com.ssafy.green_plate.src.main.mypage

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import com.ssafy.green_plate.R
import com.ssafy.green_plate.src.main.SubDressingAdapter
import com.ssafy.green_plate.src.main.SubToppingAdapter
import com.ssafy.green_plate.config.BaseFragment
import com.ssafy.green_plate.databinding.FragmentOrderHistoryDetailBinding
import com.ssafy.green_plate.src.main.MainActivity
import com.ssafy.green_plate.src.main.MainActivityViewModel

private const val TAG = "OrderHistoryDetailFragm_싸피"
class OrderHistoryDetailFragment : BaseFragment<FragmentOrderHistoryDetailBinding>(
    FragmentOrderHistoryDetailBinding::bind,
    R.layout.fragment_order_history_detail
){

    private lateinit var mainActivity : MainActivity
    private val activityViewModel: MainActivityViewModel by activityViewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


//        Log.d(TAG, "onViewCreated: ${activityViewModel.clickedOrderHistoryItem}")

        var items = listOf("item1", "item2", "item3")
//        binding.orderHistorySubDressingRv.apply {
//            adapter = SubDressingAdapter(mainActivity, activityViewModel)
//        }
//
//        binding.orderHistorySubToppingRv.apply {
//            adapter = SubToppingAdapter(mainActivity, items)
//        }
    }
}