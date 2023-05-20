package com.ssafy.green_plate

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ssafy.green_plate.databinding.FragmentThirdBannerBinding

class ThirdBannerFragment : Fragment() {
    private var _binding : FragmentThirdBannerBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentThirdBannerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.thirdRecommandMenuTv.text = "단호박두부 샐러드"
        binding.thirdRecommandDiscripTv.text = "단백하고 고소한 두부와 달콤한 단호박, 아삭한 당근라페가 어우러진 비건 탄단지 메뉴"
        binding.thirdRecommandImg.setImageResource(R.drawable.salad04)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}