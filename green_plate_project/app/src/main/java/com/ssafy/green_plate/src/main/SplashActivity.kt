package com.ssafy.green_plate.src.main

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.ssafy.green_plate.R
import com.ssafy.green_plate.config.ApplicationClass

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        // 1초 후에 다른 Activity로 이동
        Handler(Looper.getMainLooper()).postDelayed({
            //로그인 된 상태인지 확인
            var user = ApplicationClass.sharedPreferencesUtil.getUser()

            //로그인 상태 확인. id가 있다면 로그인 된 상태
            if (user.id != ""){
                intent = Intent(this, MainActivity::class.java)
            } else {
                intent = Intent(this, LoginActivity::class.java)
            }
            startActivity(intent)
            finish()
        }, 1000)
    }
}