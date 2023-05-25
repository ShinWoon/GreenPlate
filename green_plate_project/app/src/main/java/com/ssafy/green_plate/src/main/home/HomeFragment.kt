package com.ssafy.green_plate.src.main.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.LinearLayout
import android.widget.ScrollView
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
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
import com.ssafy.green_plate.models.MenuDetailWithProductInfo
import com.ssafy.green_plate.src.main.MainActivity
import com.ssafy.green_plate.src.main.mypage.MypageViewModel
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
                        Log.d(TAG, "onClick: ${menu}")
                        activityViewModel.setPageType("recommend")
                        activityViewModel.addSelectedMenu(menu)
                        Navigation.findNavController(view)
                            .navigate(R.id.action_homeFragment_to_orderDetailFragment)
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
            recentMenuAdapter.setItemClickListener(object : RecentMenuAdapter.ItemClickListener{
                override fun onClick(view: View, position: Int, menu : MenuDetailWithProductInfo) {
                    lifecycleScope.launch {
                        Log.d(TAG, "onClick: ${menu.productId}")

                        activityViewModel.setPageType("recommend")
                        val tmpMenu = Product(menu.productId, menu.productName, menu.engName, menu.productType, "", menu.price, menu.productImg)
                        activityViewModel.addSelectedMenu(tmpMenu)
                        Navigation.findNavController(view)
                            .navigate(R.id.action_homeFragment_to_orderDetailFragment)
                    }
                }

            })
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

        val calendar = Calendar.getInstance()
        val currentHour = calendar.get(Calendar.HOUR_OF_DAY)

        val timeMenu : String = when (currentHour) {
            in 6 .. 10 -> "님을 위한 아침 메뉴"
            in 11..14 -> "님을 위한 점심 메뉴"
            else -> "님을 위한 저녁 메뉴"
        }

        val timeGreeting : String = when (currentHour) {
            in 6 .. 10 -> "활기찬 아침입니다!"
            in 11..14 -> "\n오늘 점심 같이 할래요?"
            else -> "\n하루를 마무리하는 저녁입니다."
        }

        binding.apply {
            greetingTv.text = "안녕하세요 ${user.name}님. ${timeGreeting}"
            timeRecommendMenuTv.text = "${user.name}${timeMenu}"
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        timer.cancel()
    }

}