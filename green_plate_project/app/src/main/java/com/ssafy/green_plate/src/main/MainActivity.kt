package com.ssafy.green_plate.src.main

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.ssafy.green_plate.R
import com.ssafy.green_plate.config.ApplicationClass
import com.ssafy.green_plate.config.BaseActivity
import com.ssafy.green_plate.databinding.ActivityMainBinding
import com.ssafy.green_plate.dto.Product
import com.ssafy.green_plate.util.CheckPermission
import com.ssafy.green_plate.util.RetrofitUtil
import kotlinx.coroutines.launch
import java.util.*


private const val TAG = "싸피"
class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    private val activityViewModel : MainActivityViewModel by viewModels()
    private val runtimePermissions = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION,
    )
    private lateinit var checkPermission: CheckPermission

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var user = ApplicationClass.sharedPreferencesUtil.getUser()
        setBottomNavigation()
        setRecommendMenu()
        setProductList()
        activityViewModel.putRecentOrderedMenu(user.id)
        setLocationPermission()


        if (intent.getBooleanExtra("openFragment", false)) {
            val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
            val navController = navHostFragment.navController
            navController.navigate(R.id.orderHistoryFragment)
        }

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
            activityViewModel.setSaladToppingList()
            activityViewModel.setYogurtToppingList()
        }
    }

    private fun getFiveRecommendMenu() {
        lifecycleScope.launch {
            var recommendList : MutableList<Product> = mutableListOf()
            val pList = activityViewModel.productList
            val calendar = Calendar.getInstance()
            val currrentHour = calendar.get(Calendar.HOUR_OF_DAY)

            val numbers = when(currrentHour) {
                in 6..10 -> (3..11).shuffled().take(5)
                in 11..17 -> (0..11).shuffled().take(5)
                else -> (0..6).shuffled().take(5)
            }
            numbers.forEach {
                Log.d(TAG, "getFiveRecommendMenu: ${it}")
                recommendList.add(pList.value!!.get(it))
            }
            activityViewModel.setRecomemndMenuInfo(recommendList)
        }
    }

    private fun setLocationPermission() {
        checkPermission = CheckPermission(this)
        if (!checkPermission.runtimeCheckPermission(this, *runtimePermissions)) {
            ActivityCompat.requestPermissions(this, runtimePermissions, PERMISSION_REQUEST_CODE)
        } else { //이미 전체 권한이 있는 경우
            activityViewModel.permissionCheck = true
            Log.d(TAG, "setLocationPermission: ${activityViewModel.permissionCheck}")
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PERMISSION_REQUEST_CODE -> if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) { // 권한을 모두 획득했다면.
                activityViewModel.permissionCheck = true
            } else {
                checkPermission.requestPermission()
            }
        }
    }

    companion object {
        private const val PERMISSION_REQUEST_CODE = 8
    }

    fun createNotification() {
        val notificationId = 1
        val channelId = "channel_id"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelName = "Channel Name"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(channelId, channelName, importance)
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra("openFragment", true)
        }
        val pendingIntent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT)
        } else {
            PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        }

        val builder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.carrot)
            .setContentTitle("그린 플레이트")
            .setContentText("주문하신 메뉴가 픽업 준비되었습니다!")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)

        with(NotificationManagerCompat.from(this@MainActivity)) {
            notify(notificationId, builder.build())
        }
    }

}