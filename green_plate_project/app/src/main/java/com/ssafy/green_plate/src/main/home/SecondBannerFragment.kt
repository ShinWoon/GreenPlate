package com.ssafy.green_plate.src.main.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.ssafy.green_plate.src.main.MainActivityViewModel
import com.ssafy.green_plate.config.ApplicationClass
import com.ssafy.green_plate.databinding.FragmentSecondBannerBinding
import com.ssafy.green_plate.dto.Product

class SecondBannerFragment : Fragment() {
    private var _binding : FragmentSecondBannerBinding? = null
    private val binding
        get() = _binding!!
    private val activityViewModel: MainActivityViewModel by activityViewModels()
//    private var recommendList = mutableListOf<Product>()
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
        topThreeMenuObserver()
    }
    private fun topThreeMenuObserver() {
        activityViewModel.topThreeMenuInfo.observe(viewLifecycleOwner) {
            var recommendList = it as MutableList<Product>
            binding.secondRecommandMenuTv.text = recommendList.get(1).name
            binding.secondRecommandDiscripTv.text = recommendList.get(1).discription.split("(").get(0)
            view?.let { it1 ->
                Glide.with(it1)
                    .load("${ApplicationClass.MENU_IMGS_URL}${recommendList.get(1).img}")
                    .into(binding.secondRecommandImg)
            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}