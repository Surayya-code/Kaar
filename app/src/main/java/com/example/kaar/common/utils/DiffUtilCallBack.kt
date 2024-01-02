package com.example.kaar.common.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.kaar.model.Article
import com.example.kaar.model.NewsResponseModelItem

object ArticleDiffUtilCallBack : DiffUtil.ItemCallback<Article>() {

       override fun areItemsTheSame(
        oldItem: Article,
        newItem: Article
    ): Boolean {
        return oldItem.url==newItem.url
    }

    override fun areContentsTheSame(
        oldItem: Article,
        newItem: Article
    ): Boolean {
        return oldItem==newItem
    }

}

object NewsResponseDiffUtilCallBack : DiffUtil.ItemCallback<NewsResponseModelItem>() {

    override fun areItemsTheSame(
        oldItem: NewsResponseModelItem,
        newItem: NewsResponseModelItem
    ): Boolean {
        return oldItem.articles==newItem.articles
    }

    override fun areContentsTheSame(
        oldItem: NewsResponseModelItem,
        newItem: NewsResponseModelItem
    ): Boolean {
        return oldItem==newItem
    }

}