package com.ssafy.green_plate.src.main.order.shoppingcart

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.green_plate.R
import com.ssafy.green_plate.config.BaseFragment
import com.ssafy.green_plate.databinding.FragmentShoppingcartBinding
import com.ssafy.green_plate.dto.ShoppingCart
import com.ssafy.green_plate.src.main.MainActivity
import com.ssafy.green_plate.src.main.MainActivityViewModel

private const val TAG = "ShoppingcartFragment_싸피"
class ShoppingcartFragment : BaseFragment<FragmentShoppingcartBinding>(
    FragmentShoppingcartBinding::bind,
    R.layout.fragment_shoppingcart
), ShoppingCartAdapter.OnItemDeleteClickListener{

    private lateinit var mainActivity: MainActivity
    private val activityViewModel: MainActivityViewModel by activityViewModels()
    private lateinit var shoppingCartAdapter: ShoppingCartAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity.hideBottomNav(true)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        shoppingCartAdapter = ShoppingCartAdapter(mainActivity, mutableListOf(), this)

        activityViewModel.shoppingList.observe(viewLifecycleOwner) {
            shoppingCartAdapter = ShoppingCartAdapter(mainActivity, it, this)
            setRecyclerView()
            setObserver()
        }



        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.shoppingCartRv.apply {
            layoutManager = linearLayoutManager
            adapter = shoppingCartAdapter
        }

        binding.shoppingCartOrderBtn.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_shoppingcartFragment_to_payingFragment)
        }

    }

    private fun setRecyclerView() {
        binding.shoppingCartRv.apply {
            val linearLayoutManager = LinearLayoutManager(context)
            linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
            layoutManager = linearLayoutManager
            adapter = shoppingCartAdapter
        }
    }
    private fun setObserver() {
        activityViewModel.shoppingList.observe(viewLifecycleOwner) {
            shoppingCartAdapter.notifyDataSetChanged()
            setShoppingListCnt(it)
        }
    }
    private fun setShoppingListCnt(shoppingList: MutableList<ShoppingCart>) {
        var totalCnt = 0
        var totalPrice = 0
        for (shoppingCart in shoppingList) {
            totalCnt += shoppingCart.menuCnt
            totalPrice += shoppingCart.productPrice

            for (stuff in shoppingCart.addedStuff) {
                totalPrice += stuff.price
            }
        }
        binding.ShoppingCartTotalCntTv.text = "총 " + totalCnt.toString() + "개"
        binding.ShoppingCartTotalPriceTv.text = String.format("%,d", totalPrice) + "원"
    }

    override fun onDestroy() {
        super.onDestroy()
        mainActivity.hideBottomNav(false)
    }

    override fun onItemDeleteClick(position: Int) {
        Log.d(TAG, "onItemDeleteClick: $position")
        activityViewModel.deleteShoppingList(position)
    }

}