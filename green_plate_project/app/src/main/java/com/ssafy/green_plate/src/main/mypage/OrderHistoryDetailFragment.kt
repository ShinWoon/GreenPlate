package com.ssafy.green_plate.src.main.mypage

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssafy.green_plate.R
import com.ssafy.green_plate.src.main.SubDressingAdapter
import com.ssafy.green_plate.src.main.SubToppingAdapter
import com.ssafy.green_plate.config.BaseFragment
import com.ssafy.green_plate.databinding.FragmentOrderHistoryDetailBinding
import com.ssafy.green_plate.src.main.MainActivity
import com.ssafy.green_plate.src.main.MainActivityViewModel
import com.ssafy.green_plate.util.CommonUtils

private const val TAG = "OrderHistoryDetailFragm_싸피"
class OrderHistoryDetailFragment : BaseFragment<FragmentOrderHistoryDetailBinding>(
    FragmentOrderHistoryDetailBinding::bind,
    R.layout.fragment_order_history_detail
){

    private lateinit var mainActivity : MainActivity
    private val activityViewModel: MainActivityViewModel by activityViewModels()
    private lateinit var orderHistoryDetailAdapter: OrderHistoryDetailAdapter
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

        Log.d(TAG, "onViewCreated: ${activityViewModel.clickedOrderHistoryItem}")
        activityViewModel.clickedOrderHistoryItem.observe(viewLifecycleOwner) {
            orderHistoryDetailAdapter = OrderHistoryDetailAdapter(mainActivity, it)
            orderHistoryDetailAdapter.notifyDataSetChanged()
            binding.orderDetailInfoRv.apply {
                adapter = orderHistoryDetailAdapter
                layoutManager = LinearLayoutManager(mainActivity)
            }
            var priceSum = 0
            for(i in 0 until it.size) priceSum += it.get(i).totalPrice
            binding.apply {
                orderHistoryStoreNameTv.text = it[0].storeName
                orderHistoryDateTv.text = CommonUtils.getFormattedString(it[0].orderDate)
                orderHistoryNumberTv.text = "41278471892371982${it[0].orderId.toString()}"
                orderHistoryTotalPriceTv.text = CommonUtils.makeComma(priceSum)
                orderHistoryDiscountTv.text = CommonUtils.makeComma(it[0].discountPrice)
                orderHistoryFinalPriceTv.text = CommonUtils.makeComma(priceSum-it[0].discountPrice)
                orderHistoryPayMethod.text = it[0].payType
            }

        }
//
//        binding.orderHistorySubToppingRv.apply {
//            adapter = SubToppingAdapter(mainActivity, items)
//        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mainActivity.hideBottomNav(false)
    }
}