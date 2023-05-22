package com.ssafy.smartstore_jetpack.src.main.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.green_plate.dto.User
import com.ssafy.green_plate.util.RetrofitUtil

import kotlinx.coroutines.launch

private const val TAG = "JoinFragmentViewModel_싸피"
class JoinFragmentViewModel : ViewModel() {
    private val _idDuplication = MutableLiveData<Boolean>()
    val idDuplication : LiveData<Boolean>
        get() = _idDuplication

    fun join(joinId:String, joinName: String, joinPass: String) {
        viewModelScope.launch {
            try {
                RetrofitUtil.userService.insert(User(joinId, joinName, joinPass, 0))
                Log.d(TAG, "join: 회원가입 성공")
            } catch (e: Exception) {
                RetrofitUtil.userService.insert(User())
            }
        }
    }

    fun checkIdDuplication(joinId: String) {
        viewModelScope.launch {
            try {
                _idDuplication.value = RetrofitUtil.userService.isUsedId(joinId)
                Log.d(TAG, "checkIdDuplication: ${_idDuplication.value}")
            } catch (e: Exception) {
                _idDuplication.value = true
            }
        }
    }
}