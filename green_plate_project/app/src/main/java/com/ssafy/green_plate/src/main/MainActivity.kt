package com.ssafy.green_plate.src.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.ssafy.green_plate.*
import com.ssafy.green_plate.R
import com.ssafy.green_plate.config.ApplicationClass
import com.ssafy.green_plate.config.BaseActivity
import com.ssafy.green_plate.databinding.ActivityMainBinding
import com.ssafy.green_plate.dto.Product
import com.ssafy.green_plate.util.RetrofitUtil
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus

private const val TAG = "싸피"
class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    private val activityViewModel : MainActivityViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setBottomNavigation()
        setRecommendMenu()
        setProductList()
        activityViewModel.putRecentOrderedMenu()
    }

    private fun setBottomNavigation() {

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

    private fun setRecommendMenu() {
        lifecycleScope.launch {
            RetrofitUtil.productService.getTopThreeMenuList().let {
                activityViewModel.setTopThreeMenuInfo(it as MutableList<Product>)
            }
        }

    }
    fun logout() {
        //preference 지우기
        ApplicationClass.sharedPreferencesUtil.deleteUser()

        //화면이동
        val intent = Intent(this, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        startActivity(intent)
    }

    private fun setProductList() {
        lifecycleScope.launch {
            RetrofitUtil.productService.getProductList().let {
                activityViewModel.setProductList(it as MutableList<Product>)
            }
            getFiveRecommendMenu()
            activityViewModel.setSaladMenuList()
            activityViewModel.setYogurtMenuList()
            activityViewModel.setAllMenuList()
        }
    }

    private fun getFiveRecommendMenu() {
        lifecycleScope.launch {
            var recommendList : MutableList<Product> = mutableListOf()
            val pList = activityViewModel.productList
            val numbers = (0..11).shuffled().take(5)
            numbers.forEach {
                Log.d(TAG, "getFiveRecommendMenu: ${it}")
                recommendList.add(pList.value!!.get(it))
            }
            activityViewModel.setRecomemndMenuInfo(recommendList)
        }
    }
}