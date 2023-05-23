package com.ssafy.green_plate.src.main.home

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ssafy.green_plate.config.ApplicationClass
import com.ssafy.green_plate.databinding.ListItemHomeRecommendBinding
import com.ssafy.green_plate.dto.Product

private const val TAG = "HomeRecommendAdapter_싸피"
class HomeRecommendAdapter(val context : Context, private var items : List<Product>) : RecyclerView.Adapter<HomeRecommendAdapter.HomeRecommendViewHolder>() {
    inner class HomeRecommendViewHolder(private val binding: ListItemHomeRecommendBinding)
        : RecyclerView.ViewHolder(binding.root) {
            fun bindInfo(data: Product) {
                binding.recommendMenuTv.text = data.name
                Log.d(TAG, "bindInfo: ${data.name}")
                Glide.with(itemView)
                    .load("${ApplicationClass.MENU_IMGS_URL}${data.img}")
                    .into(binding.recommendIv)
                itemView.setOnClickListener{
                    itemClickListner.onClick(it, layoutPosition, data)
                }
            }
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomeRecommendAdapter.HomeRecommendViewHolder {
        val binding =
            ListItemHomeRecommendBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeRecommendViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: HomeRecommendAdapter.HomeRecommendViewHolder,
        position: Int
    ) {
        val item = items[position]
        holder.bindInfo(item)

    }

    override fun getItemCount(): Int {
        return items.size
    }

    interface ItemClickListener {
        fun onClick(view: View, position: Int, menu: Product)
    }
    //클릭리스너 선언
    private lateinit var itemClickListner: ItemClickListener
    //클릭리스너 등록 매소드
    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListner = itemClickListener
    }
}