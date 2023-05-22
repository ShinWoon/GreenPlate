package com.ssafy.green_plate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.ssafy.green_plate.config.ApplicationClass.Companion.sharedPreferencesUtil
import com.ssafy.green_plate.src.main.LoginActivity
import com.ssafy.green_plate.src.main.MainActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        // 1초 후에 다른 Activity로 이동
        Handler(Looper.getMainLooper()).postDelayed({
            //로그인 된 상태인지 확인
            var user = sharedPreferencesUtil.getUser()

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