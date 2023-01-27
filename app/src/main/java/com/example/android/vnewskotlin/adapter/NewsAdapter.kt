package com.example.android.vnewskotlin.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.vnewskotlin.NewsDetailsActivity
import com.example.android.vnewskotlin.R
import com.example.android.vnewskotlin.model.ArticlesModel
import com.squareup.picasso.Picasso


class NewsAdapter(private val context: Context, private val articles : List<ArticlesModel>)
    : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private var lastIndexAnimation = -1


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(LayoutInflater.from(context).inflate(R.layout.news_rv_items,parent,false))
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article = articles[position]
        holder.newsTitle.text = article.title
        holder.newsDesc.text = article.description
        Picasso.get().load(article.urlToImage).into(holder.newsImg)

        setAnimationOnView(holder.itemView,holder.adapterPosition);

        // on item click passing all the news related data to NewsDetailsActivity through intent
        holder.itemView.setOnClickListener {
            val intent = Intent(context, NewsDetailsActivity::class.java)
            intent.putExtra("title", article.title)
            intent.putExtra("content", article.content)
            intent.putExtra("desc", article.description)
            intent.putExtra("imageUrl", article.urlToImage)
            intent.putExtra("url", article.url)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return articles.size
    }


    inner class NewsViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        var newsTitle: TextView = itemView.findViewById(R.id.idNewsheading)
        var newsDesc: TextView = itemView.findViewById(R.id.idSubheading)
        var newsImg: ImageView = itemView.findViewById(R.id.idIVnews)
    }

    // setting animation on every view
    private fun setAnimationOnView(view: View, position: Int) {
        if (position > lastIndexAnimation) {
            val animation: Animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left)
            view.startAnimation(animation)
            lastIndexAnimation = position
        }
    }

}
