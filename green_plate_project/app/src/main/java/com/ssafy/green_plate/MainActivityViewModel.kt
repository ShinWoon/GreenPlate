package com.ssafy.green_plate

import android.util.Log
import android.view.Menu
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

}