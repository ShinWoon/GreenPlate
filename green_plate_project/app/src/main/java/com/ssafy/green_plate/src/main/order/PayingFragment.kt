package com.ssafy.green_plate.src.main.order

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ssafy.green_plate.R
import com.ssafy.green_plate.config.BaseFragment
import com.ssafy.green_plate.databinding.FragmentPayingBinding
import com.ssafy.green_plate.databinding.FragmentShoppingcartBinding
import com.ssafy.green_plate.dto.OrderDetail
import com.ssafy.green_plate.dto.ShoppingCart
import com.ssafy.green_plate.src.main.MainActivity
import com.ssafy.green_plate.src.main.MainActivityViewModel
import com.ssafy.green_plate.src.main.order.shoppingcart.ShoppingCartAdapter

private const val TAG = "PayingFragment_싸피"
class PayingFragment : BaseFragment<FragmentPayingBinding>(
    FragmentPayingBinding::bind,
    R.layout.fragment_paying
) {
    private lateinit var mainActivity: MainActivity
    private val activityViewModel: MainActivityViewModel by activityViewModels()
    private val payingOrderViewModel : PayingOrderViewModel by viewModels()
    private lateinit var payingOrderAdapter: PayingOrderAdapter

    private var selectedCard = ""

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        payingOrderAdapter = PayingOrderAdapter(mainActivity, activityViewModel.shoppingList.value as List<ShoppingCart>)
        setRecyclerView()

        // 할인 쿠폰 적용 구현 예정
        var coupons = listOf("5000원 할인 쿠폰", "3000원 할인 쿠폰")
        val adapter = ArrayAdapter(mainActivity, android.R.layout.simple_dropdown_item_1line, coupons)
        binding.payingCouponTv.setAdapter(adapter)

        // 카드 선택
        binding.payingCardTv.setOnItemClickListener { parent, view, position, id ->
            selectedCard = parent.getItemAtPosition(position).toString()
            Log.d(TAG, "onViewCreated: $selectedCard")
        }

        binding.payingBtn.setOnClickListener {
            setOrder(view)
            mainActivity.findViewById<BottomNavigationView>(R.id.bottom_navigation).visibility = View.VISIBLE
        }

    }

    private fun setOrder(view: View) {
        var orderDetails: MutableList<OrderDetail> = mutableListOf()
        for (detail in activityViewModel.shoppingList.value!!) {
            orderDetails.add(OrderDetail(detail.productId, detail.dressingId))
        }
        payingOrderViewModel.makeOrder(orderDetails, selectedCard)
        showToast("주문이 완료되었습니다.")
        // 장바구니 비워주기
        activityViewModel.clearShoppingList()
        Navigation.findNavController(view).navigate(R.id.action_payingFragment_to_homeFragment)
    }
    private fun setRecyclerView() {
        binding.payOrderRecyclerView.apply {
            val linearLayoutManager = LinearLayoutManager(context)
            linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
            layoutManager = linearLayoutManager
            adapter = payingOrderAdapter
        }
    }
}