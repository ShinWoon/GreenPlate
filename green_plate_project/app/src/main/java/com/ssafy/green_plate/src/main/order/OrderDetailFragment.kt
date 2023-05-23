package com.ssafy.green_plate.src.main.order

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.ssafy.green_plate.R
import com.ssafy.green_plate.src.main.MainActivityViewModel
import com.ssafy.green_plate.config.ApplicationClass
import com.ssafy.green_plate.databinding.FragmentOrderDetailBinding
import com.ssafy.green_plate.dto.Product
import com.ssafy.green_plate.dto.ShoppingCart
import com.ssafy.green_plate.src.main.MainActivity
import com.ssafy.green_plate.util.CommonUtils

private const val TAG = "OrderDetailFragment_싸피"
class OrderDetailFragment : Fragment() {
    private lateinit var mainActivity: MainActivity
    private var _binding: FragmentOrderDetailBinding? = null
    private val binding
        get() = _binding!!

    private val activityViewModel: MainActivityViewModel by activityViewModels()
    private val shoppingCart = ShoppingCart()
    private val DRESSING_DEFAULT_ID =  30

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity.hideBottomNav(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentOrderDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        activityViewModel.menuDetailInfo.observe(viewLifecycleOwner) {
            binding.apply {
                orderDetailMenuNameTv.text = it.name
                orderDetailMenuEngNameTv.text = it.englishName
                orderDetailMenuPrice.text = CommonUtils.makeComma(it.price)
                Glide.with(view)
                    .load("${ApplicationClass.MENU_IMGS_URL}${it.img}")
                    .into(orderDetailMenuIv)
                if (it.type == "yogurt") dressingListLayout.visibility = View.GONE
            }


            binding.orderToppingRv.apply {
                if (it.type == "salad") {
                    adapter = OrderDetailToppingAdapter(
                        requireContext(),
                        activityViewModel.saladToppingList
                    )
                } else {
                    adapter = OrderDetailToppingAdapter(
                        requireContext(),
                        activityViewModel.yogurtToppingList
                    )
                }
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            }

        }

        binding.dressingRadioGroup.setOnCheckedChangeListener { group, checkedId ->
            // 선택된 RadioButton에 따라 처리할 로직을 작성합니다.
            when (checkedId) {
                R.id.dressing_rbtn1 -> { // 시저 드레싱
                    shoppingCart.dressingId = DRESSING_DEFAULT_ID + 1
                }
                R.id.dressing_rbtn2 -> { // 오리엔탈 드레싱
                    shoppingCart.dressingId = DRESSING_DEFAULT_ID + 2
                }
                R.id.dressing_rbtn3 -> { // 발사믹 드레싱
                    shoppingCart.dressingId = DRESSING_DEFAULT_ID + 3
                }
                R.id.dressing_rbtn4 -> { // 레몬 드레싱
                    shoppingCart.dressingId = DRESSING_DEFAULT_ID + 4
                }
                R.id.dressing_rbtn5 -> { // 머스타드 드레싱
                    shoppingCart.dressingId = DRESSING_DEFAULT_ID + 5
                }
                R.id.dressing_rbtn6 -> { // 칠리 드레싱
                    shoppingCart.dressingId = DRESSING_DEFAULT_ID + 6
                }
                R.id.dressing_rbtn7 -> { // 드레싱X
                    shoppingCart.dressingId = DRESSING_DEFAULT_ID + 7
                }
            }
        }

        binding.addShoppingcartBtn.setOnClickListener {
            Log.d(TAG, "onViewCreated: ${activityViewModel.allMenuList.value}")

            Log.d(TAG, "onViewCreated: ${activityViewModel.productList.value}")
            Log.d(TAG, "onViewCreated: ${activityViewModel.saladToppingList}")
        }



    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        mainActivity.hideBottomNav(false)
    }



}