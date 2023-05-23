package com.ssafy.green_plate

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssafy.green_plate.config.ApplicationClass
import com.ssafy.green_plate.databinding.FragmentMypageBinding
import com.ssafy.green_plate.src.main.MainActivity
import com.ssafy.green_plate.util.CommonUtils
import com.ssafy.green_plate.util.RetrofitUtil
import kotlinx.coroutines.launch


private const val TAG = "MypageFragment_싸피"
class MypageFragment : Fragment() {
    private var _binding : FragmentMypageBinding? = null
    private val binding
      get() = _binding!!
    private lateinit var mainActivity: MainActivity

    private val activityViewModel : MainActivityViewModel by activityViewModels()

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
        setList()
        binding.mypageRecyclerView.apply {
            adapter = StampAdapter(requireContext(), stampList)
            layoutManager = GridLayoutManager(requireContext(), 5)
        }

        initListener(view)

        val id = getUserData()
//        initData(id)
        activityViewModel.getUserInfo(id)
    }

    

    private fun getUserData():String{
        val user = ApplicationClass.sharedPreferencesUtil.getUser()
        binding.mypageNameTv.text = user.name + " 님"



        return user.id
    }


    private fun initListener(view: View) {
        binding.linearLayoutCoupon.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_mypageFragment_to_couponFragment)
        }

        binding.linearLayoutOrderHistory.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_mypageFragment_to_orderHistoryFragment)
        }

        binding.linearLayoutStoreInfo.setOnClickListener {

        }

        binding.mypageLogout.setOnClickListener {
            mainActivity.logout()
        }

        binding.mypageWithdrawal.setOnClickListener {

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setList() {
        for(i in 1..5) {
            stampList.add(true)
        }
        for(i in 1..5) {
            stampList.add(false)
        }
    }
}