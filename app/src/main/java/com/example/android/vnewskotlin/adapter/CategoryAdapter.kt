package com.example.android.vnewskotlin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.vnewskotlin.R
import com.example.android.vnewskotlin.model.CategoryModel
import com.squareup.picasso.Picasso

class CategoryAdapter(private val context : Context, private val categoryList : List<CategoryModel>,
                      private val categoryClickInterface : CategoryClickInterface)
    : RecyclerView.Adapter<CategoryAdapter.CategoryViewModel>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewModel {
        return CategoryViewModel(LayoutInflater.from(context).inflate(R.layout.category_rv_item,parent,false))
    }

    override fun onBindViewHolder(holder: CategoryViewModel, position: Int) {
        val categoryModel = categoryList[position]
        holder.categoryTxt.text = categoryModel.category
        Picasso.get().load(categoryModel.categoryImageUrl).into(holder.categoryImg)

        // passing the particular category to interface method by users click
        holder.itemView.setOnClickListener {
            categoryClickInterface.onCategoryClick(holder.adapterPosition)
        }


    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    // interface for getting particular category
    interface CategoryClickInterface {
        fun onCategoryClick(position: Int)
    }


    inner class CategoryViewModel(itemView : View) : RecyclerView.ViewHolder(itemView) {
        var categoryImg : ImageView = itemView.findViewById(R.id.categoryImg)
        var categoryTxt : TextView = itemView.findViewById(R.id.categoryTxt)
    }

}
