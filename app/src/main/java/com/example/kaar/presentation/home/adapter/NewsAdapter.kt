package com.example.kaar.presentation.home.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.example.kaar.common.utils.DiffUtilCallBack
import com.example.kaar.databinding.ItemNewsBinding
import com.example.kaar.model.ProductResponseModelItem

class NewsAdapter: RecyclerView.Adapter<NewsAdapter.NewsViewHolder>(){
    inner class NewsViewHolder(private val binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ProductResponseModelItem) {
            with(binding){
                productNews=item
                    executePendingBindings()
                    newsCard.setOnClickListener {
                        //Toast.makeText(context,"We have not Products in Stock",Toast.LENGTH_LONG).show()
                        //Navigation.findNavController(it).navigate(DetailFragmentDirections.toyoutubeplayer(item.key))
                    }

            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.bind(item)
//        val item = differ.currentList[position]
//        holder.bind(item, holder.itemView.context)
    }

    val differ = AsyncListDiffer(this,DiffUtilCallBack)



}