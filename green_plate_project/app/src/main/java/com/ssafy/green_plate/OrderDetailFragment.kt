package com.ssafy.green_plate

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssafy.green_plate.databinding.FragmentOrderDetailBinding
import com.ssafy.green_plate.dto.Product

class OrderDetailFragment : Fragment() {
    private var _binding : FragmentOrderDetailBinding? = null
    private val binding
        get() = _binding!!

    private val dressingList = mutableListOf<Product>()
    private val toppingList = mutableListOf<Product>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentOrderDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dressingList.add(Product(1,"","","","",1000,""))
        dressingList.add(Product(1,"","","","",1000,""))
        dressingList.add(Product(1,"","","","",1000,""))

        binding.orderDressingRv.apply {
            adapter = OrderDetailDressingAdapter(requireContext(), dressingList)
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }

        toppingList.add(Product(1,"","","","",1000,""))
        toppingList.add(Product(1,"","","","",1000,""))
        toppingList.add(Product(1,"","","","",1000,""))

        binding.orderToppingRv.apply {
            adapter = OrderDetailToppingAdapter(requireContext(), toppingList)
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
    }

    companion object {
    }
}