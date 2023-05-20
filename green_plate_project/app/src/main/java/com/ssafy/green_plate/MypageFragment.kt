package com.ssafy.green_plate

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.ssafy.green_plate.databinding.FragmentMypageBinding

class MypageFragment : Fragment() {
    private var _binding : FragmentMypageBinding? = null
    private val binding
      get() = _binding!!

    private val stampList = mutableListOf<Boolean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMypageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setList()
        binding.mypageRecyclerView.apply {
            adapter = StampAdapter(requireContext(), stampList)
            layoutManager = GridLayoutManager(requireContext(), 5)
        }
        binding.linearLayoutCoupon.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_mypageFragment_to_couponFragment)
        }
        binding.linearLayout3.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_mypageFragment_to_orderHistoryFragment)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setList() {
        for(i in 1..5) {
            stampList.add(true)
        }
        for(i in 1..5) {
            stampList.add(false)
        }
    }
}