package com.ssafy.green_plate.src.main.mypage

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.green_plate.dto.Store
import com.ssafy.green_plate.util.RetrofitUtil
import kotlinx.coroutines.launch

private const val TAG = "StoreViewModel"
class StoreViewModel : ViewModel() {
    private var _storeInfo = MutableLiveData<List<Store>>()
    val storeInfo : LiveData<List<Store>>
        get() = _storeInfo

    fun getStoreInfo() {
        viewModelScope.launch {
            try {
                _storeInfo.value = RetrofitUtil.storeService.getStore()
            } catch (e : Exception) {
                Log.d(TAG, "getStoreInfo: $e")
            }
        }
    }
}