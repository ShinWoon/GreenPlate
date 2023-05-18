package com.ssafy.green_plate

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ssafy.green_plate.config.BaseFragment
import com.ssafy.green_plate.databinding.FragmentHomeBinding
import com.ssafy.green_plate.src.main.MainActivity

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::bind, R.layout.fragment_home) {

    private lateinit var mainActivity: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}