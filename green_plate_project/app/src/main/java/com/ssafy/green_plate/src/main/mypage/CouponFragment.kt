package com.ssafy.green_plate.src.main.mypage

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssafy.green_plate.config.ApplicationClass
import com.ssafy.green_plate.databinding.FragmentCouponBinding
import com.ssafy.green_plate.dto.Coupon
import com.ssafy.green_plate.src.main.MainActivity
import com.ssafy.green_plate.src.main.MainActivityViewModel
import com.ssafy.smartstore_jetpack.src.main.login.JoinFragmentViewModel

private const val TAG = "CouponFragment_싸피"
class CouponFragment :  Fragment() {
    private lateinit var mainActivity: MainActivity
//    private val viewModel : CouponViewModel by viewModels()
    private val activityViewModel : MainActivityViewModel by activityViewModels()

    private var _binding: FragmentCouponBinding? = null
    private val binding
        get() = _binding!!

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
        _binding = FragmentCouponBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        activityViewModel.getCouponList()
        Log.d(TAG, "onViewCreated: ${activityViewModel.couponList}")
        activityViewModel.couponList.observe(viewLifecycleOwner) {
            binding.couponRecyclerView.apply {
                adapter = CouponAdapter(requireContext(), it)
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

}