package com.ssafy.green_plate

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.ssafy.green_plate.databinding.FragmentFirstBannerBinding

class FirstBannerFragment : Fragment() {

    private var _binding : FragmentFirstBannerBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBannerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.firstRecommandMenuTv.text = "연어 샐러드"
        binding.firstRecommandDiscripTv.text = "훈제 연어, 양파, 스윗포테이토의 달콤고소한 조합"
        binding.firstRecommandImg.setImageResource(R.drawable.salad02)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
    }
}