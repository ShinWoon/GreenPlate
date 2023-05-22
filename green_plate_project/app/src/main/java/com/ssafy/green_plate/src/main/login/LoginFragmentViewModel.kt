package com.ssafy.smartstore_jetpack.src.main.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.green_plate.dto.User
import com.ssafy.green_plate.util.RetrofitUtil
import kotlinx.coroutines.launch
import java.util.*

private const val TAG = "LoginFragmentViewModel_싸피"
class LoginFragmentViewModel : ViewModel() {
    private val _user = MutableLiveData<User>()
    val user : LiveData<User>
        get() = _user

    fun login(loginId:String, loginPass: String) {
        viewModelScope.launch {
            try {
                Log.d(TAG, "login: $loginId, $loginPass")
                _user.value = RetrofitUtil.userService.login(User(loginId, loginPass))
                Log.d(TAG, "login: ${user.value?.id}")
            } catch (e: Exception) {
                _user.value = User()
//                _user.value = User(UUID.randomUUID().toString(), "")
                Log.d(TAG, "login: ${_user.value}, exception")
            }
        }
    }

}