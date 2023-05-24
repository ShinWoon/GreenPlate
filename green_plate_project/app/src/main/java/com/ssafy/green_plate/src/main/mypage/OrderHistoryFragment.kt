package com.ssafy.green_plate.src.main.mypage

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssafy.green_plate.src.main.MainActivityViewModel
import com.ssafy.green_plate.R
import com.ssafy.green_plate.config.ApplicationClass
import com.ssafy.green_plate.config.BaseFragment
import com.ssafy.green_plate.databinding.FragmentOrderHistoryBinding
import com.ssafy.green_plate.models.OrderDetailResponse
import com.ssafy.green_plate.src.main.MainActivity

private const val TAG = "OrderHistoryFragment_싸피"
class OrderHistoryFragment : BaseFragment<FragmentOrderHistoryBinding>(
    FragmentOrderHistoryBinding::bind,
    R.layout.fragment_order_history
) {

    private lateinit var mainActivity: MainActivity
    private val activityViewModel: MainActivityViewModel by activityViewModels()
    private lateinit var orderHistoryAdapter: OrderHistoryAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val user = ApplicationClass.sharedPreferencesUtil.getUser()
        activityViewModel.setUserOrderedMenu(user.id)


        activityViewModel.userOrderedMenu.observe(viewLifecycleOwner) {
            Log.d(TAG, "onViewCreated: ${it[0]}, ${it.size}")
            orderHistoryAdapter = OrderHistoryAdapter(mainActivity, it)
            orderHistoryAdapter.notifyDataSetChanged()
            orderHistoryAdapter.setOnItemClickListener { position ->
                Log.d(TAG, "onViewCreated: $position")
                val clickedItem = it[position]
                activityViewModel.setClickedItem(clickedItem as MutableList<OrderDetailResponse>)
                Navigation.findNavController(view)
                    .navigate(R.id.action_orderHistoryFragment_to_orderHistoryDetailFragment)
            }
            binding.orderHistoryRv.apply {
                adapter = orderHistoryAdapter
                layoutManager = LinearLayoutManager(mainActivity)
            }
        }


    }

}