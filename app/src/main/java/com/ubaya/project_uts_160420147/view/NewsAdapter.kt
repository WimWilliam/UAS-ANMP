package com.ubaya.project_uts_160420147.view

import android.provider.ContactsContract.RawContacts.Data
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.ubaya.project_uts_160420147.R
import com.ubaya.project_uts_160420147.databinding.ItemNewsBinding
import com.ubaya.project_uts_160420147.model.News

class NewsAdapter(private var newsList: ArrayList<News>) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>()
{

    class NewsViewHolder(val view:ItemNewsBinding) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemNewsBinding>(inflater,
            R.layout.item_news,parent,false)
//        val binding = ItemNewsBinding.inflate(inflater, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
//        val news = newsList[position]
        holder.view.news = newsList[position]

//        with(holder.binding) {
//            textViewJudul.text = news.title
//            textViewPengarang.text = news.author
//            textViewCategory.text = news.category
//            textViewDeskripsiSingkat.text = news.deskrip
//        }
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    fun updateNewsList(newNewsList: List<News>) {
        newsList.clear() // Membersihkan list newsList
        newsList.addAll(newNewsList) // Menambahkan semua item dari newNewsList ke newsList
        notifyDataSetChanged() // Memberitahu RecyclerView bahwa dataset telah berubah
    }
}


