package com.ssafy.green_plate.src.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ssafy.green_plate.config.ApplicationClass
import com.ssafy.green_plate.dto.*
import com.ssafy.green_plate.models.MenuDetailWithProductInfo
import com.ssafy.green_plate.models.OrderDetailResponse
import com.ssafy.green_plate.util.RetrofitUtil
import kotlinx.coroutines.launch

private const val TAG = "MainActivityViewModel_싸피"
class MainActivityViewModel : ViewModel(){
    private var _productList = MutableLiveData<MutableList<Product>>()
    val productList : LiveData<MutableList<Product>>
        get() = _productList

    private val _productInfo = MutableLiveData<Int>()

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

    private var _saladToppingList = mutableListOf<Product>()
    val saladToppingList : List<Product>
        get() = _saladToppingList

    fun setSaladToppingList() {
        for (i in 12..21) _saladToppingList.add(_productList.value!!.get(i))
    }

    private var _yogurtToppingList = mutableListOf<Product>()
    val yogurtToppingList : List<Product>
        get() = _yogurtToppingList

    fun setYogurtToppingList() {
        for (i in 22..29) _yogurtToppingList.add(_productList.value!!.get(i))
    }


    private var _userOrderedMenu = MutableLiveData<MutableList<List<OrderDetailResponse>>>()
    val userOrderedMenu : LiveData<MutableList<List<OrderDetailResponse>>>
        get() = _userOrderedMenu

    fun setUserOrderedMenu(userId: String) {
        var orderInfo : MutableList<MenuDetailWithProductInfo>
        var info : MutableList<List<OrderDetailResponse>> = mutableListOf()
        viewModelScope.launch {
            try {
                orderInfo = RetrofitUtil.orderService.getMonthOrder(userId) as MutableList<MenuDetailWithProductInfo>
                orderInfo.forEach {
                    info.add(RetrofitUtil.orderService.getOrderDetail(it.orderId))
                }
            } catch (e : Exception) {
                info = arrayListOf()
            }
            _userOrderedMenu.value = info
        }
    }


    private var _userInfo = MutableLiveData<User>()
    val userInfo: LiveData<User>
        get() = _userInfo

    private var _gradeInfo = MutableLiveData<Grade>()
    val gradeInfo: LiveData<Grade>
        get() = _gradeInfo
    fun getUserInfo(userId: String) {
        Log.d(TAG, "getUserInfo: $userId")
        viewModelScope.launch {
            try {
                val userAllInfo = RetrofitUtil.userService.getInfo(userId)
                Log.d(TAG, "getUserInfo: ${userAllInfo}")
                _gradeInfo.value = userAllInfo.grade
                Log.d(TAG, "getUserInfo: ${gradeInfo.value}")
                Log.d(TAG, "getUserInfo: ${userAllInfo.user}")
                _userInfo.value = userAllInfo.user
            } catch (e: Exception) {
                e.printStackTrace()
                Log.d(TAG, "getUserInfo: $e")
            }
        }
    }

    private var _shoppingList = MutableLiveData<MutableList<ShoppingCart>>(mutableListOf())
    val shoppingList: LiveData<MutableList<ShoppingCart>>
        get() = _shoppingList

    fun addShoppingList(shoppingCart: ShoppingCart) {
        Log.d(TAG, "addShoppingList: $shoppingCart")
        _shoppingList.value?.add(shoppingCart)
        _shoppingList.value = _shoppingList.value // MutableLiveData가 변경되었음을 알리기 위해 다시 할당합니다.
    }

    fun deleteShoppingList(index: Int) {
        _shoppingList.value?.let { list ->
            if (list.size > index) {
                list.removeAt(index)
                _shoppingList.value = list
            }
        }
    }

    fun clearShoppingList() {
        _shoppingList.value = mutableListOf()
    }

    private var _selectedMenu = MutableLiveData<Product>()
    val selectedMenu: LiveData<Product>
        get() = _selectedMenu

    fun addSelectedMenu(menu : Product) {
        Log.d(TAG, "addSelectedMenu: ${menu.name}")
        _selectedMenu.value = menu
//        _selectedMenu.value = selectedMenu.value // MutableLiveData가 변경되었음을 알리기 위해 다시 할당합니다.
    }

    private var _pageType = MutableLiveData<String>()
    val pageType : LiveData<String>
        get() = _pageType

    fun setPageType(type : String) {
        _pageType.value = type
    }

    private var _clickedOrderHistoryItem = MutableLiveData<MutableList<OrderDetailResponse>>()
    val clickedOrderHistoryItem : LiveData<MutableList<OrderDetailResponse>>
        get() = _clickedOrderHistoryItem

    fun setClickedItem(clickedItem: MutableList<OrderDetailResponse>) {
        _clickedOrderHistoryItem.value = clickedItem
    }

    // liveData가 업데이트 되게 전 스탬프 값을 저장하기 위한 변수
    var previousStamps = 0
    private var _couponList = MutableLiveData<List<Coupon>>(mutableListOf())
    val couponList : LiveData<List<Coupon>>
        get() = _couponList

    fun getCouponList() {
        val user = ApplicationClass.sharedPreferencesUtil.getUser()
        viewModelScope.launch {
            try {
                _couponList.value = RetrofitUtil.couponService.getCoupon(user.id)
                Log.d(TAG, "getCouponList: ${couponList.value}")
            } catch (e : Exception) {

            }
        }
    }

    fun addCoupon(coupon : Coupon) {
        viewModelScope.launch {
            try {
                RetrofitUtil.couponService.addCoupon(coupon)
            }catch (e: Exception) {

            }
        }
    }

}