package com.ssafy.green_plate.src.main.order

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.ssafy.green_plate.R
import com.ssafy.green_plate.databinding.FragmentOrderBinding
import com.ssafy.green_plate.src.main.order.tab_fragment.Tab1Fragment
import com.ssafy.green_plate.src.main.order.tab_fragment.Tab2Fragment
import com.ssafy.green_plate.src.main.order.tab_fragment.Tab3Fragment

class OrderFragment : Fragment() {
    private var _binding: FragmentOrderBinding? = null
    private val binding
        get() = _binding!!

    private val tabTitle = arrayOf("전체메뉴", "샐러드", "그릭요거트")


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOrderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.orderViewPager2.adapter = ViewPagerAdapter(this)
        TabLayoutMediator(binding.orderTabLayout, binding.orderViewPager2) { tab, position ->
            tab.text = tabTitle[position]
        }.attach()

        binding.shoppingCartBtn.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_orderFragment_to_shoppingCartFragment)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    // ViewPagerAdapter 내부 클래스 생성
    inner class ViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
        override fun getItemCount(): Int = 3 // 전체 TabItem 개수

        override fun createFragment(position: Int): Fragment {
            // 각 TabItem에 대응하는 Fragment 생성
            return when (position) {
                0 -> Tab1Fragment() // 첫 번째 TabItem에 대한 Fragment
                1 -> Tab2Fragment() // 두 번째 TabItem에 대한 Fragment
                2 -> Tab3Fragment() // 세 번째 TabItem에 대한 Fragment
                else -> Tab1Fragment()
            }
        }
    }

}