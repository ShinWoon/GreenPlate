package com.ssafy.green_plate.src.main.order

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Build.VERSION_CODES.P
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
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
    private val DRESSING_DEFAULT_ID = 30
    private var pageType = ""
    lateinit var orderDetailToppingAdapter: OrderDetailToppingAdapter
    var selectedMenu = Product()
    var selectedDressing = false

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
        Log.d(TAG, "onAttach: 메인")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
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

        Log.d(TAG, "onViewCreated: 화면 생성")

        activityViewModel.pageType.observe(viewLifecycleOwner) {
            pageType = it
            if (it.equals("recommend")) {
                activityViewModel.selectedMenu.observe(viewLifecycleOwner) {
                    Log.d(TAG, "onViewCreated: ${it}")
                    selectedMenu = it
                    shoppingCart.productPrice = it.price
                    shoppingCart.productName = it.name
                    shoppingCart.type = it.type
                    shoppingCart.productId = it.id
                    shoppingCart.productImg = it.img
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
                            orderDetailToppingAdapter = OrderDetailToppingAdapter(
                                mainActivity,
                                activityViewModel.saladToppingList
                            )
                            adapter = orderDetailToppingAdapter

//                            orderDetailToppingAdapter.notifyDataSetChanged()
                        } else {
                            orderDetailToppingAdapter = OrderDetailToppingAdapter(
                                mainActivity,
                                activityViewModel.yogurtToppingList
                            )
                            adapter = orderDetailToppingAdapter

                        }
                        layoutManager =
                            LinearLayoutManager(
                                requireContext(),
                                LinearLayoutManager.VERTICAL,
                                false
                            )

                    }
                }
            } else if (it.equals("menuPage")) {
                activityViewModel.menuDetailInfo.observe(viewLifecycleOwner) {
                    selectedMenu = it
                    shoppingCart.productPrice = it.price
                    shoppingCart.productName = it.name
                    shoppingCart.type = it.type
                    shoppingCart.productId = it.id
                    shoppingCart.productImg = it.img
                    binding.apply {
                        Log.d(TAG, "onViewCreated: menu 화면에서 이동 ${it}")
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
                            orderDetailToppingAdapter = OrderDetailToppingAdapter(
                                mainActivity,
                                activityViewModel.saladToppingList
                            )
                            adapter = orderDetailToppingAdapter

//                            orderDetailToppingAdapter.notifyDataSetChanged()
                        } else {
                            orderDetailToppingAdapter = OrderDetailToppingAdapter(
                                mainActivity,
                                activityViewModel.yogurtToppingList
                            )
                            adapter = orderDetailToppingAdapter

                        }
                        layoutManager =
                            LinearLayoutManager(
                                requireContext(),
                                LinearLayoutManager.VERTICAL,
                                false
                            )

                    }

                }

            }
        }
        setListner(view)
    }

    private fun setListner(view: View) {

        binding.dressingRadioGroup.setOnCheckedChangeListener { group, checkedId ->
            // 선택된 RadioButton에 따라 처리할 로직을 작성합니다.
            when (checkedId) {
                R.id.dressing_rbtn1 -> { // 시저 드레싱
                    shoppingCart.dressingId = DRESSING_DEFAULT_ID + 1
                    shoppingCart.dressingName = "시저 드레싱"
                    selectedDressing = true
                }

                R.id.dressing_rbtn2 -> { // 오리엔탈 드레싱
                    shoppingCart.dressingId = DRESSING_DEFAULT_ID + 2
                    shoppingCart.dressingName = "오리엔탈 드레싱"
                    selectedDressing = true
                }

                R.id.dressing_rbtn3 -> { // 발사믹 드레싱
                    shoppingCart.dressingId = DRESSING_DEFAULT_ID + 3
                    shoppingCart.dressingName = "발사믹 드레싱"
                    selectedDressing = true
                }

                R.id.dressing_rbtn4 -> { // 레몬 드레싱
                    shoppingCart.dressingId = DRESSING_DEFAULT_ID + 4
                    shoppingCart.dressingName = "레몬 드레싱"
                    selectedDressing = true
                }

                R.id.dressing_rbtn5 -> { // 머스타드 드레싱
                    shoppingCart.dressingId = DRESSING_DEFAULT_ID + 5
                    shoppingCart.dressingName = "머스타드 드레싱"
                    selectedDressing = true
                }

                R.id.dressing_rbtn6 -> { // 칠리 드레싱
                    shoppingCart.dressingId = DRESSING_DEFAULT_ID + 6
                    shoppingCart.dressingName = "칠리 드레싱"
                    selectedDressing = true
                }

                R.id.dressing_rbtn7 -> { // 드레싱X
                    shoppingCart.dressingId = DRESSING_DEFAULT_ID + 7
                    shoppingCart.dressingName = "드레싱 X"
                    selectedDressing = true
                }

            }
        }

        binding.addShoppingcartBtn.setOnClickListener {
            Log.d(TAG, "setListner: button clicked")
            if (selectedDressing || selectedMenu.type == "yogurt") {
                val checkedItems = orderDetailToppingAdapter.getCheckedItems()
                Log.d(TAG, "onViewCreated: $checkedItems")
                shoppingCart.addedStuff = checkedItems
                activityViewModel.addShoppingList(shoppingCart)
                Toast.makeText(mainActivity, "상품이 장바구니에 담겼습니다.", Toast.LENGTH_SHORT).show()
                if(pageType.equals("recommend")) Navigation.findNavController(view)
                    .navigate(R.id.action_orderDetailFragment_to_orderFragment1)
                else if (pageType.equals("menuPage")) Navigation.findNavController(view)
                    .navigate(R.id.action_orderDetailFragment_to_orderFragment)
            } else {
                Toast.makeText(mainActivity, "드레싱을 선택하세요.", Toast.LENGTH_SHORT).show()
            }

        }

        binding.orderDetailMenuIv.setOnClickListener {
            val dialog = Dialog(mainActivity)
            dialog.setContentView(R.layout.custom_dialog_layout)

            val imageView: ImageView = dialog.findViewById(R.id.dialog_image)
            Glide.with(view)
                .load("${ApplicationClass.MENU_IMGS_URL}${selectedMenu.img}")
                .into(imageView)
            dialog.window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            dialog.show()
        }

    }

    private val menuDetailInfoObserver = Observer<Product> {
        binding.apply {
            Log.d(TAG, "onViewCreated: menu 화면에서 이동 ${it}")
            orderDetailMenuNameTv.text = it.name
            orderDetailMenuEngNameTv.text = it.englishName
            orderDetailMenuPrice.text = CommonUtils.makeComma(it.price)
            Glide.with(requireView())
                .load("${ApplicationClass.MENU_IMGS_URL}${it.img}")
                .into(orderDetailMenuIv)
            if (it.type == "yogurt") dressingListLayout.visibility = View.GONE
        }

        binding.orderToppingRv.apply {
            if (it.type.equals("salad")) {
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
    private val selectedMenuObserver = Observer<Product> {
        Log.d(TAG, "onViewCreated: ${it}")
        binding.apply {
            orderDetailMenuNameTv.text = it.name
            orderDetailMenuEngNameTv.text = it.englishName
            orderDetailMenuPrice.text = CommonUtils.makeComma(it.price)
            Glide.with(requireView())
                .load("${ApplicationClass.MENU_IMGS_URL}${it.img}")
                .into(orderDetailMenuIv)
            if (it.type == "yogurt") dressingListLayout.visibility = View.GONE
        }
        binding.orderToppingRv.apply {
            if (it.type.equals("salad")) {
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

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mainActivity.hideBottomNav(false)
        activityViewModel.selectedMenu.removeObserver(selectedMenuObserver)
        activityViewModel.menuDetailInfo.removeObserver(menuDetailInfoObserver)
    }
}