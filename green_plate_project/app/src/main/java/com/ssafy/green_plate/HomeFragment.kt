package com.ssafy.green_plate

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ScrollView
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.ssafy.green_plate.config.BaseFragment
import com.ssafy.green_plate.databinding.FragmentHomeBinding
import com.ssafy.green_plate.dto.Product
import com.ssafy.green_plate.src.main.MainActivity
import java.util.*

class HomeFragment :
    BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::bind, R.layout.fragment_home) {

    private lateinit var mainActivity: MainActivity
    private lateinit var bannerAdapter: BannerViewPagerAdapter
    private lateinit var timer: Timer
    private var recommendList = mutableListOf<Product>()
    private var recentMenuList = mutableListOf<String>()

    private val activityViewModel: MainActivityViewModel by activityViewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val scrollView = ScrollView(requireContext())
        val linearLayout = LinearLayout(requireContext())
        linearLayout.orientation = LinearLayout.VERTICAL
        scrollView.addView(linearLayout)

        bannerAdapter = BannerViewPagerAdapter(this)
        binding.mainViewPager.adapter = bannerAdapter
        binding.mainViewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        val autoScrollTask = AutoScrollTask()
        timer = Timer()
        timer.schedule(autoScrollTask, 3000, 3000)

        binding.mainRecommendRV.apply {
            adapter = HomeRecommendAdapter(requireContext(), recommendList)
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }

        binding.mainLatestOrderRV.apply {
            adapter = RecentMenuAdapter(requireContext(), recentMenuList)
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
    }

    private inner class AutoScrollTask : TimerTask() {
        override fun run() {
            requireActivity().runOnUiThread {
                val currentItem = binding.mainViewPager.currentItem
                val nextItem =
                    if (currentItem == bannerAdapter.itemCount - 1) 0 else currentItem + 1
                binding.mainViewPager.setCurrentItem(nextItem, true)
            }
        }
    }

    private fun recommendObserver() {
        activityViewModel.productInfo.observe(viewLifecycleOwner) {
            recommendList = it as MutableList<Product>
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        timer.cancel()
    }

}