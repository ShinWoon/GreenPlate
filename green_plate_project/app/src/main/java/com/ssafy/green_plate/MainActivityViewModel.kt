package com.ssafy.green_plate

import android.util.Log
import android.view.Menu
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ssafy.green_plate.dto.Product
import com.ssafy.green_plate.models.MenuDetailWithProductInfo
import com.ssafy.green_plate.util.RetrofitUtil
import kotlinx.coroutines.launch

private const val TAG = "싸피"
class MainActivityViewModel : ViewModel(){
    private var _productList = MutableLiveData<MutableList<Product>>()
    val productList : LiveData<MutableList<Product>>
        get() = _productList

    fun setProductList(result : MutableList<Product>) {
        _productList.value = result
    }

    private var _topThreeMenuInfo = MutableLiveData<MutableList<Product>>()
    val topThreeMenuInfo : LiveData<MutableList<Product>>
        get() = _topThreeMenuInfo

    fun setTopThreeMenuInfo(result : MutableList<Product>) {
        _topThreeMenuInfo.value = result
    }

    private var _recommendMenu = MutableLiveData<MutableList<Product>>()
    val recommendMenu : LiveData<MutableList<Product>>
        get() = _recommendMenu

    fun setRecomemndMenuInfo(result : MutableList<Product>) {
        _recommendMenu.value = result
    }

    private var _recentOrderedMenu = MutableLiveData<MutableList<MenuDetailWithProductInfo>>()
    val recentOrderMenu : LiveData<MutableList<MenuDetailWithProductInfo>>
        get() = _recentOrderedMenu

    fun putRecentOrderedMenu() {
        var info : MutableList<MenuDetailWithProductInfo>
        viewModelScope.launch {
            try {
                info = RetrofitUtil.orderService.getLatestOrder("id 01") as MutableList<MenuDetailWithProductInfo>
            } catch (e : Exception) {
                info = arrayListOf()
            }
            _recentOrderedMenu.value = info
        }
    }

    private var _saladMenuList = MutableLiveData<MutableList<Product>>()
    val saladMenuList : LiveData<MutableList<Product>>
        get() = _saladMenuList

    fun setSaladMenuList() {
        var info : MutableList<Product> = mutableListOf()
        _productList.value?.forEach {
            if(it.type.equals("salad")) info.add(it)
        }
        _saladMenuList.value = info
    }
    private var _yogurtMenuList = MutableLiveData<MutableList<Product>>()
    val yogurtMenuList : LiveData<MutableList<Product>>
        get() = _yogurtMenuList

    fun setYogurtMenuList() {
        var info : MutableList<Product> = mutableListOf()
        _productList.value?.forEach {
            if(it.type.equals("yogurt")) info.add(it)
        }
        _yogurtMenuList.value = info
    }

    private var _allMenuList = MutableLiveData<MutableList<Product>>()
    val allMenuList : LiveData<MutableList<Product>>
        get() = _allMenuList

    fun setAllMenuList() {
        var info : MutableList<Product> = mutableListOf()
        _productList.value?.forEach {
            if(it.type.equals("yogurt") || it.type.equals("salad")) info.add(it)
        }
        _allMenuList.value = info
    }

    private var _menuDetailInfo = MutableLiveData<Product>()
    val menuDetailInfo : LiveData<Product>
        get() = _menuDetailInfo

    fun setMenuDetailInfo(data : Product) {
        _menuDetailInfo.value = data
    }

    private var _userOrderedMenu = MutableLiveData<MutableList<MenuDetailWithProductInfo>>()
    val userOrderedMenu : LiveData<MutableList<MenuDetailWithProductInfo>>
        get() = _userOrderedMenu

    fun setUserOrderedMenu(userId: String) {
        var info : MutableList<MenuDetailWithProductInfo>
        viewModelScope.launch {
            try {
                info = RetrofitUtil.orderService.getLatestOrder(userId) as MutableList<MenuDetailWithProductInfo>
            } catch (e : Exception) {
                info = arrayListOf()
            }
            _userOrderedMenu.value = info
        }
    }

    fun getUserInfo(userId: String) {
        Log.d(TAG, "getUserInfo: $userId")
        viewModelScope.launch {
            try {
                val userInfo = RetrofitUtil.userService.getInfo(userId)
                Log.d(TAG, "getUserInfo: ${userInfo}")
//                _gradeInfo.value = Gson().fromJson<Grade>(
//                    userInfo["grade"].toString(),
//                    object : TypeToken<Grade>() {}.type
//                )
            } catch (e: Exception) {
                Log.d(TAG, "getUserInfo: $e")
            }
        }
    }
}