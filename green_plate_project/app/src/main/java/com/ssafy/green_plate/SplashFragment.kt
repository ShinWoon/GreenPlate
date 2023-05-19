package com.ssafy.green_plate

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.ssafy.green_plate.config.BaseFragment
import com.ssafy.green_plate.databinding.FragmentHomeBinding
import com.ssafy.green_plate.databinding.FragmentSplashBinding


class SplashFragment : BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::bind, R.layout.fragment_splash) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    // 1초 후 homeFlagment로 전환
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Handler(Looper.getMainLooper()).postDelayed({
            Navigation.findNavController(view).navigate(R.id.action_splashFragment_to_homeFragment)
        }, 1000)
        
    }

}