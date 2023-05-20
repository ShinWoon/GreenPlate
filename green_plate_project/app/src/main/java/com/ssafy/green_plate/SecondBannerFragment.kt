package com.ssafy.green_plate

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ssafy.green_plate.databinding.FragmentSecondBannerBinding

class SecondBannerFragment : Fragment() {
    private var _binding : FragmentSecondBannerBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSecondBannerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.secondRecommandMenuTv.text = "로스트닭다리살 샐러드"
        binding.secondRecommandDiscripTv.text = "담백한 닭다리살, 달콤한 단호박, 상큼한 토마토, 매콤한 할라피뇨의 다채로운 맛"
        binding.secondRecommandImg.setImageResource(R.drawable.salad01)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}