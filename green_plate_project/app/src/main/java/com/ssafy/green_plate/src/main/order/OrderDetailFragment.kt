package com.ssafy.green_plate.src.main.order

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.ssafy.green_plate.src.main.MainActivityViewModel
import com.ssafy.green_plate.config.ApplicationClass
import com.ssafy.green_plate.databinding.FragmentOrderDetailBinding
import com.ssafy.green_plate.dto.Product
import com.ssafy.green_plate.src.main.MainActivity

class OrderDetailFragment : Fragment() {
    private lateinit var mainActivity: MainActivity
    private var _binding: FragmentOrderDetailBinding? = null
    private val binding
        get() = _binding!!

    private val dressingList = mutableListOf<Product>()
    private val toppingList = mutableListOf<Product>()

    private val activityViewModel: MainActivityViewModel by activityViewModels()

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
                orderDetailMenuEngNameTv.text = it.engName
                orderDetailMenuPrice.text = it.price.toString()
                Glide.with(view)
                    .load("${ApplicationClass.MENU_IMGS_URL}${it.img}")
                    .into(orderDetailMenuIv)
                if (it.type.equals("yogurt")) dressingListLayout.visibility = View.GONE
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

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        mainActivity.hideBottomNav(false)
    }


    companion object {
    }
}