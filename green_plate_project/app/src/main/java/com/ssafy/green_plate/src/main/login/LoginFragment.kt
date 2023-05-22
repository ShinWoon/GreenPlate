package com.ssafy.green_plate.src.main.login

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.ssafy.green_plate.R
import com.ssafy.green_plate.config.ApplicationClass
import com.ssafy.green_plate.config.BaseFragment
import com.ssafy.green_plate.databinding.FragmentLoginBinding
import com.ssafy.green_plate.src.main.LoginActivity
import com.ssafy.smartstore_jetpack.src.main.login.LoginFragmentViewModel

private const val TAG = "LoginFragment_싸피"
class LoginFragment : BaseFragment<FragmentLoginBinding>(
    FragmentLoginBinding::bind,
    R.layout.fragment_login
) {
    private lateinit var loginActivity: LoginActivity

    private val viewModel: LoginFragmentViewModel by viewModels()
    override fun onAttach(context: Context) {
        super.onAttach(context)
        loginActivity = context as LoginActivity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerObserver()
        // 로그인 구현
        binding.btnLogin.setOnClickListener {
            Log.d(TAG, "onViewCreated: ")
            viewModel.login(binding.loginIdEdt.text.toString(), binding.loginPassEdt.text.toString())
            // user 객체가 바뀌면 registerObserver가 불림
        }

        binding.btnJoin.setOnClickListener {
            loginActivity.openFragment(2)
        }
    }

    fun registerObserver() {
        viewModel.user.observe(viewLifecycleOwner) {
            Log.d(TAG, "registerObserver: ${it.id}")
            if (it.id.isNotEmpty()) {
                Log.d(TAG, "registerObserver: 로그인 성공!!")
                // id가 비어있지 않으면 로그인 성공
                showToast("로그인 되었습니다.")

                // sharedpreference에 저장
                ApplicationClass.sharedPreferencesUtil.addUser(it)

                // 화면 이동
                loginActivity.openFragment(1)       // 1번 화면으로 이동

            } else {
                // 실패
                showToast("id 혹은 password를 확인해 주세요.")
            }
        }

    }
}