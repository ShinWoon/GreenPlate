package com.ssafy.green_plate.src.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.ssafy.green_plate.HomeFragment
import com.ssafy.green_plate.MypageFragment
import com.ssafy.green_plate.OrderFragment
import com.ssafy.green_plate.R
import com.ssafy.green_plate.config.BaseActivity
import com.ssafy.green_plate.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setBottomNavigation()

    }

    private fun setBottomNavigation() {
        //        supportFragmentManager.beginTransaction()
//            .replace(R.id.frame_layout_main, HomeFragment())
//            .commit()

        // 네비게이션 호스트
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        // 네비게이션 컨트롤러
        val navController = navHostFragment.navController
        // 바인딩
        NavigationUI.setupWithNavController(binding.bottomNavigation, navController)


    }

    fun hideBottomNav(state : Boolean) {
        if (state) binding.bottomNavigation.visibility = View.GONE
        else binding.bottomNavigation.visibility = View.VISIBLE
    }
}