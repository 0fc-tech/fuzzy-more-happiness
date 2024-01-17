package com.example.eni_shop.ui.articlelist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.eni_shop.bo.Article
import com.example.eni_shop.databinding.TemplateArticleBinding

class ArticleAdapter(val arrayList: List<Article>,
                     val onArticleClick :(articla:Article)-> Unit) : RecyclerView.Adapter<ArticleAdapter.ArticleVH>() {

    class ArticleVH(val binding: TemplateArticleBinding) : RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleVH {
        return ArticleVH(TemplateArticleBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,false
        ))
    }

    override fun getItemCount(): Int =arrayList.size

    override fun onBindViewHolder(holder: ArticleVH, position: Int) {
        holder.binding.article = arrayList[position]
        holder.itemView.setOnClickListener {
            onArticleClick(arrayList[position])
        }
    }
}