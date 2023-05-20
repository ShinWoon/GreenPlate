package com.ssafy.green_plate

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.ssafy.green_plate.config.BaseFragment
import com.ssafy.green_plate.databinding.FragmentHomeBinding
import com.ssafy.green_plate.dto.Product
import com.ssafy.green_plate.src.main.MainActivity
import java.util.*

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::bind, R.layout.fragment_home) {

    private lateinit var mainActivity: MainActivity
    private lateinit var bannerAdapter: BannerViewPagerAdapter
    private lateinit var timer: Timer
    private var recommendList = mutableListOf<Product>()
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
        timer.schedule(autoScrollTask, 3000,3000)

        setRecommendList()
        binding.mainRecommendRV.apply {
            adapter = HomeRecommendAdapter(requireContext(), recommendList)
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
        }
    }
    private inner class AutoScrollTask : TimerTask() {
        override fun run() {
            requireActivity().runOnUiThread {
                val currentItem = binding.mainViewPager.currentItem
                val nextItem = if (currentItem == bannerAdapter.itemCount - 1) 0 else currentItem + 1
                binding.mainViewPager.setCurrentItem(nextItem, true)
            }
        }
    }

    private fun setRecommendList(){
        for(i in 1..5) {
            recommendList.add(Product(i,"연어 샐러드","salad","",0,"salad01.png"))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        timer.cancel()
    }

}