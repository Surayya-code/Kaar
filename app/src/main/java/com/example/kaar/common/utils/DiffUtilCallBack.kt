package com.example.kaar.common.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.kaar.model.ProductResponseModelItem

object DiffUtilCallBack : DiffUtil.ItemCallback<ProductResponseModelItem>() {
    override fun areItemsTheSame(
        oldItem: ProductResponseModelItem,
        newItem: ProductResponseModelItem,
    ): Boolean {
        return oldItem.id==newItem.id
    }

    override fun areContentsTheSame(
        oldItem: ProductResponseModelItem,
        newItem: ProductResponseModelItem,
    ): Boolean {
        return oldItem.id == newItem.id
                && oldItem.title == newItem.title
                && oldItem.description == newItem.description
    }
}