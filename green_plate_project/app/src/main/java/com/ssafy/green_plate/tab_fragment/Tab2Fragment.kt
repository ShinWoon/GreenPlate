package com.ssafy.green_plate.tab_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssafy.green_plate.R
import com.ssafy.green_plate.databinding.FragmentTab2Binding
import com.ssafy.green_plate.dto.Product

class Tab2Fragment : Fragment() {
    private var _binding : FragmentTab2Binding? = null
    private val binding
        get() = _binding!!

    private val menuList = mutableListOf<Product>()
    private lateinit var tabAdapter : TabAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTab2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        menuList.add(Product(0, "연어 샐러드", "Salmon Salad", "Salad", "", 10000, "salad01.png"))
        menuList.add(Product(1, "연어 샐러드", "Salmon Salad", "Salad", "", 10000, "salad01.png"))
        menuList.add(Product(2, "연어 샐러드", "Salmon Salad", "Salad", "", 10000, "salad01.png"))
        menuList.add(Product(3, "연어 샐러드", "Salmon Salad", "Salad", "", 10000, "salad01.png"))
        menuList.add(Product(4, "연어 샐러드", "Salmon Salad", "Salad", "", 10000, "salad01.png"))
        menuList.add(Product(5, "연어 샐러드", "Salmon Salad", "Salad", "", 10000, "salad01.png"))

        tabAdapter = TabAdapter(requireContext(), menuList)
        tabAdapter.setItemClickListener(object : TabAdapter.ItemClickListener{
            override fun onClick(view: View, position: Int) {
                Navigation.findNavController(view).navigate(R.id.action_orderFragment_to_orderDetailFragment)
            }
        })

        binding.saladMenuRv.apply {
            adapter = tabAdapter
            layoutManager = LinearLayoutManager(requireContext(),
                LinearLayoutManager.VERTICAL, false)
        }
    }

    companion object {
    }
}