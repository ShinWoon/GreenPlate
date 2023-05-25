package com.ssafy.green_plate.src.main.mypage

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.ssafy.green_plate.src.main.MainActivityViewModel
import com.ssafy.green_plate.R
import com.ssafy.green_plate.config.ApplicationClass
import com.ssafy.green_plate.databinding.FragmentMypageBinding
import com.ssafy.green_plate.dto.Coupon
import com.ssafy.green_plate.src.main.MainActivity

private const val TAG = "MypageFragment_싸피"

class MypageFragment : Fragment() {
    private var _binding: FragmentMypageBinding? = null
    private val binding
        get() = _binding!!
    private lateinit var mainActivity: MainActivity
    private val mypageViewModel : MypageViewModel by viewModels()

    private val activityViewModel: MainActivityViewModel by activityViewModels()

    lateinit var stampAdapter: StampAdapter
    private val stampList = mutableListOf<Boolean>()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMypageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = getUserData()
        activityViewModel.getUserInfo(id)
        activityViewModel.gradeInfo.observe(viewLifecycleOwner) {
            Log.d(TAG, "onViewCreated: ${it.img}")
            binding.mypageGreetingTv.text = it.greetings

            Glide.with(view)
                .load("${ApplicationClass.GRADE_URL}${it.img}")
                .into(binding.levelImageView)
        }
        initListener(view)
        initObserver(id)
        binding.mypageWithdrawal.setOnClickListener {
            initDialog(id)
        }



    }

    private fun initDialog(id : String) {
        val builder = MaterialAlertDialogBuilder(mainActivity)
        builder.setTitle("회원 탈퇴")
        builder.setMessage("정말 탈퇴하시겠습니까?")

        builder.setPositiveButton("확인") { dialog, which ->
            mypageViewModel.deleteUser(id)
        }

        builder.setNegativeButton("취소") { dialog, which ->
            dialog.dismiss()
        }

        // MaterialAlertDialog 생성
        val dialog = builder.create()

        dialog.show()
    }

    private fun initObserver(id: String) {
        activityViewModel.userInfo.observe(viewLifecycleOwner) {
            var currentStamps = it.stamps
            // 스탬프가 10의 배수 이상으로 증가했는지 확인
            if (currentStamps / 10 > activityViewModel.previousStamps / 10) {
                var coupon = Coupon(0, id, "스탬프 적립", 5000)
                activityViewModel.addCoupon(coupon)
            }
            // 새로운 스탬프 수를 저장
            activityViewModel.previousStamps = currentStamps
            stampList.clear()
            setStampList(currentStamps % 10)
            stampAdapter = StampAdapter(requireContext(), stampList)
            binding.mypageRecyclerView.apply {
                Log.d(TAG, "onViewCreated: $stampList")
                adapter = stampAdapter
                stampAdapter.notifyDataSetChanged()
                layoutManager = GridLayoutManager(requireContext(), 5)

            }

            binding.levelProgressBar.progress = currentStamps
            val trackColor = ContextCompat.getColor(mainActivity, R.color.green_plate_progress_gray)
            binding.levelProgressBar.trackColor = trackColor
            var indicatorColor =
                ContextCompat.getColor(mainActivity, R.color.green_plate_progess_yellow)
            if (currentStamps < 5) {
                // indicatorColor 변경
                binding.levelProgressBar.setIndicatorColor(indicatorColor)

            } else if (currentStamps < 10) {
                indicatorColor = ContextCompat.getColor(mainActivity, R.color.green_plate_orange)
                binding.levelProgressBar.setIndicatorColor(indicatorColor)
            } else {
                indicatorColor =
                    ContextCompat.getColor(mainActivity, R.color.green_plate_progess_red)
                binding.levelProgressBar.setIndicatorColor(indicatorColor)
            }

        }
    }

    private fun getUserData(): String {
        val user = ApplicationClass.sharedPreferencesUtil.getUser()
        binding.mypageNameTv.text = user.name

        return user.id
    }


    private fun initListener(view: View) {
        binding.linearLayoutCoupon.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_mypageFragment_to_couponFragment)
        }

        binding.linearLayoutOrderHistory.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_mypageFragment_to_orderHistoryFragment)
        }

        binding.linearLayoutStoreInfo.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_mypageFragment_to_storeFragment)
        }

        binding.mypageLogout.setOnClickListener {
            mainActivity.logout()
        }

        binding.mypageWithdrawal.setOnClickListener {

        }
    }

    private fun setStampList(stamps: Int) {
        for (flag in 0..10) {
            stampList.add(false)
        }

        for (i in 0 until stamps) {
            stampList[i] = true
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}