package com.ssafy.green_plate.src.main.mypage

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.green_plate.util.RetrofitUtil
import kotlinx.coroutines.launch

private const val TAG = "MypageViewModel_싸피"
class MypageViewModel : ViewModel() {
    fun deleteUser(id: String) {
        viewModelScope.launch {
            try {
                RetrofitUtil.userService.delete(id)
            } catch (e : Exception) {
                Log.d(TAG, "deleteUser: $e")
            }
        }
    }
}