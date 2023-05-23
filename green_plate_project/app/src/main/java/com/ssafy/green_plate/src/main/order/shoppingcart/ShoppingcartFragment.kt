package com.ssafy.green_plate.src.main.order.shoppingcart

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssafy.green_plate.R
import com.ssafy.green_plate.config.BaseFragment
import com.ssafy.green_plate.databinding.FragmentShoppingcartBinding
import com.ssafy.green_plate.src.main.MainActivity

private const val TAG = "ShoppingcartFragment_싸피"
class ShoppingcartFragment : BaseFragment<FragmentShoppingcartBinding>(
    FragmentShoppingcartBinding::bind,
    R.layout.fragment_shoppingcart
) {

    private lateinit var mainActivity: MainActivity
    private lateinit var shoppingCartAdapter: ShoppingCartAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity.hideBottomNav(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d(TAG, "onViewCreated: ")
        var items = listOf("item1", "item2", "item3")

        shoppingCartAdapter = ShoppingCartAdapter(mainActivity, items)

        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.shoppingCartRv.apply {
            layoutManager = linearLayoutManager
            adapter = shoppingCartAdapter
        }

        binding.shoppingCartOrderBtn.setOnClickListener {
            showToast("상품이 장바구니에 담겼습니다.")
        }

    }

    override fun onResume() {
        super.onResume()

    }

    override fun onDestroy() {
        super.onDestroy()
        mainActivity.hideBottomNav(false)
    }

}