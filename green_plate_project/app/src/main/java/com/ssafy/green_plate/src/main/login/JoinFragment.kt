package com.ssafy.green_plate.src.main.login

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ssafy.green_plate.R
import com.ssafy.green_plate.config.BaseFragment
import com.ssafy.green_plate.databinding.FragmentJoinBinding
import com.ssafy.green_plate.dto.Coupon
import com.ssafy.green_plate.src.main.LoginActivity
import com.ssafy.smartstore_jetpack.src.main.login.JoinFragmentViewModel

class JoinFragment : BaseFragment<FragmentJoinBinding>(FragmentJoinBinding::bind, R.layout.fragment_join) {
    private var checkedId = false
    private val viewModel : JoinFragmentViewModel by viewModels()

    private lateinit var loginActivity: LoginActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        loginActivity = context as LoginActivity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkIdObserver()
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // 텍스트 변경 이전에 호출됩니다.
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // 텍스트 변경 시 호출됩니다.
//                val changedText = s.toString()
                checkedId = false
                binding.joinCheckBtn.setImageResource(R.drawable.baseline_check_circle_outline_24)
            }

            override fun afterTextChanged(s: Editable?) {
                // 텍스트 변경 후에 호출됩니다.
            }
        }
        binding.joinIdEdt.addTextChangedListener(textWatcher)

        binding.joinCheckBtn.setOnClickListener {
            if(checkedId) {
                showToast("이미 아이디 중복 확인되었습니다.")

            } else {
                // 중복 확인하기
                viewModel.checkIdDuplication(binding.joinIdEdt.text.toString())
            }
        }

        binding.btnJoin.setOnClickListener{

            if(checkedId) {
                // 아이디 중복 확인을 끝낸 상태
                val joinId = binding.joinIdEdt.text.toString()
                val joinName = binding.joinNicknameEdt.text.toString()
                val joinPassword = binding.joinPassEdt.text.toString()
                val joinCheckPassword = binding.joinCheckPassEdt.text.toString()
                if(joinId.isNotEmpty() && joinPassword.isNotEmpty() && joinCheckPassword.isNotEmpty()
                    && joinName.isNotEmpty()) {
                    if (joinPassword != joinCheckPassword) {
                        showToast("password가 일치하지 않습니다.")
                    } else {
                        // 값이 모두 들어 왔고, 비밀번호와 비밀번호확인이 같으면 회원가입 가능
                        viewModel.join(joinId, joinName, joinPassword)
                        viewModel.addCoupon(Coupon(0, joinId, "신규 가입", 3000))
                        showToast("회원가입을 축하합니다!")
                        loginActivity.openFragment(3)
                    }

                } else {
                    showToast("password 혹은 name을 확인해 주세요.")
                }
            } else {
                // id 중복 확인
                showToast("아이디 중복 확인이 되지 않았습니다.")
            }
        }
    }



    fun checkIdObserver() {

        viewModel.idDuplication.observe(viewLifecycleOwner) {
            if(it) {
                // true == 이미 있음
                showToast("이미 존재하는 아이디입니다.")
            } else {
                showToast("사용 가능한 아이디입니다.")
                checkedId = true
                binding.joinCheckBtn.setImageResource(R.drawable.baseline_check_circle_24_green)
            }
        }
    }
}