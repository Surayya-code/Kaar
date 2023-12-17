package com.example.kaar.presentation.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.example.kaar.common.utils.ArticleDiffUtilCallBack
import com.example.kaar.databinding.ItemArticleBinding
import com.example.kaar.model.Article


class NewsHomeAdapter: RecyclerView.Adapter<NewsHomeAdapter.NewsHolder>() {
    inner class NewsHolder(val binding: ItemArticleBinding)
        :RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Article) {
            with(binding) {
                article = item

            }
        }
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        val view = ItemArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return NewsHolder((view))
    }
    val differ = AsyncListDiffer(this, ArticleDiffUtilCallBack)
    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        val item = differ.currentList[position]
        holder.bind(item)
    }
}