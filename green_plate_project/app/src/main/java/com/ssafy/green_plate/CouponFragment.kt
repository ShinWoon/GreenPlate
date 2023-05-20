package com.ssafy.green_plate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssafy.green_plate.databinding.FragmentCouponBinding
import com.ssafy.green_plate.dto.Coupon

class CouponFragment :  Fragment() {
    private var _binding: FragmentCouponBinding? = null
    private val binding
        get() = _binding!!

    private val couponList = mutableListOf<Coupon>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCouponBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setList()
        binding.couponRecyclerView.apply {
            adapter = CouponAdapter(requireContext(), couponList)
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setList() {
        for (i in 1..5) {
            couponList.add(Coupon(i,"id 01", "신규 가입", 7000))
        }
    }
}