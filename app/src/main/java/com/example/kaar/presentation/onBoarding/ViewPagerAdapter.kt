package com.example.kaar.presentation.onBoarding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kaar.databinding.ItemSliderPagesBinding


class PagerAdapter(private val pageList:List<pages>)
    : RecyclerView.Adapter<PagerAdapter.pagerViewHolder>() {

    inner class pagerViewHolder(private val itemPagesBinding: ItemSliderPagesBinding)
        : RecyclerView.ViewHolder(itemPagesBinding.root){
        fun bindItem(pages: pages){
            itemPagesBinding.imageView.setImageResource(pages.image)


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): pagerViewHolder {
        return pagerViewHolder(
            ItemSliderPagesBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun getItemCount(): Int {
        return pageList.size
    }

    override fun onBindViewHolder(holder: pagerViewHolder, position: Int) {
        holder.bindItem(pageList[position])
    }
}