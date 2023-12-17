//package com.example.kaar.presentation.explore.adapter
//
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.AsyncListDiffer
//import androidx.recyclerview.widget.RecyclerView
//
//class NewsAdapter:RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
//    inner class NewsViewHolder(private  val binding:)
//        :RecyclerView.ViewHolder(binding.root) {
//        fun bind(item: Article) {
//            with(binding){
//                news=item
//                executePendingBindings()
//                newsCard.setOnClickListener {
//                    //Toast.makeText(context,"We have not Products in Stock",Toast.LENGTH_LONG).show()
//                    //Navigation.findNavController(it).navigate(DetailFragmentDirections.toyoutubeplayer(item.key))
//                }
//
//            }
//        }
//
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
//        val view = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return NewsViewHolder(view)
//    }
//
//    override fun getItemCount(): Int {
//        return differ.currentList.size
//    }
//
//    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
//        val item = differ.currentList[position]
//        holder.bind(item)
//    }
//    val differ = AsyncListDiffer(this, NewsResponseDiffUtilCallBack)
//}