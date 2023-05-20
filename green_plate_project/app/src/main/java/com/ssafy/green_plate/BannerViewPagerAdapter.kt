package com.ssafy.green_plate

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2

class BannerViewPagerAdapter(fragment : Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> FirstBannerFragment()
            1 -> SecondBannerFragment()
            2 -> ThirdBannerFragment()
            else -> FirstBannerFragment()
        }
    }
}