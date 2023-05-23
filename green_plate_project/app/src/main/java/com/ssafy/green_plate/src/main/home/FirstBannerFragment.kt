package com.ssafy.green_plate.src.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.ssafy.green_plate.src.main.MainActivityViewModel
import com.ssafy.green_plate.config.ApplicationClass
import com.ssafy.green_plate.databinding.FragmentFirstBannerBinding
import com.ssafy.green_plate.dto.Product

class FirstBannerFragment : Fragment() {

    private var _binding : FragmentFirstBannerBinding? = null
    private val binding
        get() = _binding!!

    private val activityViewModel: MainActivityViewModel by activityViewModels()
    private var recommendList = mutableListOf<Product>()

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
        topThreeMenuObserver()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun topThreeMenuObserver() {
        activityViewModel.topThreeMenuInfo.observe(viewLifecycleOwner) {
            recommendList = it as MutableList<Product>
            binding.firstRecommandMenuTv.text = recommendList.get(0).name
            binding.firstRecommandDiscripTv.text = recommendList.get(0).discription.split("(").get(0)
            view?.let { it1 ->
                Glide.with(it1)
                    .load("${ApplicationClass.MENU_IMGS_URL}${recommendList.get(0).img}")
                    .into(binding.firstRecommandImg)
            }
        }
    }

    companion object {
    }
}