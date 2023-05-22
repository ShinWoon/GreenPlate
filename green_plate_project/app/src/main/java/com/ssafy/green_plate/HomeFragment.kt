package com.ssafy.green_plate

import android.content.Context
import android.os.Bundle
import android.util.Log
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

private const val TAG = "싸피"
class HomeFragment :
    BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::bind, R.layout.fragment_home) {

    private lateinit var mainActivity: MainActivity
    private lateinit var bannerAdapter: BannerViewPagerAdapter
    private lateinit var timer: Timer
    private var recommendList = mutableListOf<Product>()
    private var recentMenuList = mutableListOf<String>()
    private lateinit var recommendAdapter : HomeRecommendAdapter
    private lateinit var recentMenuAdapter: RecentMenuAdapter

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

        activityViewModel.recommendMenu.observe(viewLifecycleOwner) {
            recommendAdapter = HomeRecommendAdapter(requireContext(), it)
            recommendAdapter.notifyDataSetChanged()
            binding.mainRecommendRV.apply {
                adapter = recommendAdapter
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            }
        }

        activityViewModel.recentOrderMenu.observe(viewLifecycleOwner) {
            recentMenuAdapter = RecentMenuAdapter(requireContext(), it)
            recentMenuAdapter.notifyDataSetChanged()
            binding.mainLatestOrderRV.apply {
                adapter = recentMenuAdapter
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            }
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



    override fun onDestroyView() {
        super.onDestroyView()
        timer.cancel()
    }

}