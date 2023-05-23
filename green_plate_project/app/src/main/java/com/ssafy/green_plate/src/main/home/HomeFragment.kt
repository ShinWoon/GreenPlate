package com.ssafy.green_plate.src.main.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.ScrollView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.ssafy.green_plate.src.main.MainActivityViewModel
import com.ssafy.green_plate.R
import com.ssafy.green_plate.config.ApplicationClass
import com.ssafy.green_plate.config.BaseFragment
import com.ssafy.green_plate.databinding.FragmentHomeBinding
import com.ssafy.green_plate.dto.Product
import com.ssafy.green_plate.src.main.MainActivity
import kotlinx.coroutines.launch
import java.util.*

private const val TAG = "HomeFragment_싸피"
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

        initScreen()

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
            recommendAdapter.setItemClickListener(object : HomeRecommendAdapter.ItemClickListener{
                override fun onClick(view: View, position: Int, menu : Product) {
                    lifecycleScope.launch {
                        Log.d(TAG, "onClick: ${menu}")
                        activityViewModel.setPageType("recommend")
                        activityViewModel.addSelectedMenu(menu)
                        Navigation.findNavController(view)
                            .navigate(R.id.action_homeFragment_to_orderDetailFragment)
                    }
                }

            })
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

    private fun initScreen() {
        val user = ApplicationClass.sharedPreferencesUtil.getUser()
        binding.greetingTv.text = "안녕하세요 ${user.name}님. "

        binding.timeRecommendMenuTv.text = "${user.name}님을 위한 아침 추천 메뉴"
    }



    override fun onDestroyView() {
        super.onDestroyView()
        timer.cancel()
    }

}