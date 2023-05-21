package com.ssafy.green_plate

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.green_plate.dto.Product
import com.ssafy.green_plate.util.RetrofitUtil
import kotlinx.coroutines.launch

private const val TAG = "싸피"
class MainActivityViewModel : ViewModel(){
    private val _productInfo = MutableLiveData<Product>()
    var productInfo: LiveData<List<Product>> = MutableLiveData()
        get() {
            val livedata: MutableLiveData<List<Product>> = MutableLiveData()
            viewModelScope.launch {
                var info: List<Product>
                try {
                    info = RetrofitUtil.productService.getProductList()
                    Log.d(TAG, "${info} ")
                } catch (e: Exception) {
                    info = arrayListOf()
                    Log.d(TAG, ": 실패")
                }
                livedata.value = info
            }
            return livedata
        }
}