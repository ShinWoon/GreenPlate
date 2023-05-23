package com.ssafy.green_plate.src.main.order.tab_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssafy.green_plate.src.main.MainActivityViewModel
import com.ssafy.green_plate.R
import com.ssafy.green_plate.databinding.FragmentTab3Binding
import com.ssafy.green_plate.dto.Product

class Tab3Fragment : Fragment() {

    private var _binding : FragmentTab3Binding? = null
    private val binding
        get() = _binding!!

    private val menuList = mutableListOf<Product>()
    private lateinit var tabAdapter : TabAdapter
    private val activityViewModel: MainActivityViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTab3Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activityViewModel.yogurtMenuList.observe(viewLifecycleOwner) {
            tabAdapter = TabAdapter(requireContext(), it)
            tabAdapter.setItemClickListener(object : TabAdapter.ItemClickListener {
                override fun onClick(data: Product) {
                    activityViewModel.setPageType("menuPage")
                    activityViewModel.setMenuDetailInfo(data)
                    Navigation.findNavController(view).navigate(R.id.action_orderFragment_to_orderDetailFragment)
                }
            })

            binding.yogurtMenuRv.apply {
                adapter = tabAdapter
                layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL, false)
            }
        }
    }

    companion object {
    }
}