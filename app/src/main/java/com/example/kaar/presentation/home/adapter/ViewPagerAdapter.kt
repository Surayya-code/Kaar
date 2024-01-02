package com.example.kaar.presentation.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.example.kaar.common.utils.ArticleDiffUtilCallBack
import com.example.kaar.databinding.ItemViewPagingBinding
import com.example.kaar.model.Article

class ViewPagerAdapter:RecyclerView.Adapter<ViewPagerAdapter.TrendNewsViewHolder>() {
    inner class TrendNewsViewHolder(val binding: ItemViewPagingBinding):
            RecyclerView.ViewHolder(binding.root){
                fun bind(item:Article){
                 with(binding){
                     binding.article = item
                     binding.executePendingBindings()
                     viewPager.setOnClickListener {

                     }
                 }
                }
            }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewPagerAdapter.TrendNewsViewHolder {
        val view = ItemViewPagingBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return  TrendNewsViewHolder(view)
    }
    override fun getItemCount(): Int {
        return differ.currentList.size
    }
    @SuppressLint("SuspiciousIndentation")
    override fun onBindViewHolder(holder: ViewPagerAdapter.TrendNewsViewHolder, position: Int) {
     val trendNews=differ.currentList[position]
        holder.bind(trendNews)
    }
     val differ = AsyncListDiffer(this,ArticleDiffUtilCallBack)

}