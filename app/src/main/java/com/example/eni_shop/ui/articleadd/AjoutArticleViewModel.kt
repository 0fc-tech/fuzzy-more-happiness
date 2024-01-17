package com.example.eni_shop.ui.articleadd

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.example.eni_shop.bo.Article
import com.example.eni_shop.repository.ArticleRepository
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date

class AjoutArticleViewModel : ViewModel() {

    var title: String = ""
    var description: String = ""
    var price: Double = 0.0
    var date: String = ""

    var article: Article? = null

    private val articleRepository = ArticleRepository()

    fun addArticle() {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy")
        article = Article(0, this.title, this.description, this.price, null, dateFormat.parse(this.date))
        articleRepository.addArticle(article!!)
    }
}