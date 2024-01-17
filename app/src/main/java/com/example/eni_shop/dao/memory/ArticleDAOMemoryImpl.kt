package com.example.eni_shop.dao.memory

import android.util.Log
import com.example.eni_shop.bo.Article
import com.example.eni_shop.dao.ArticleDAO
import java.util.Date

private const val TAG = "ArticleDAOMemoryImpl"
class ArticleDAOMemoryImpl : ArticleDAO {
   
    companion object {

        private val articlesInMemory: MutableList<Article> = mutableListOf<Article>(

            Article(
                1,
                "Souris",
                "Souris sans fil",
                10.0,
                "https://m.media-amazon.com/images/I/61-GeOlhtlL._AC_SX679_.jpg",
               Date()
            ),
            Article(2, "Ecran", "Ecran 27\" FULL HD", 780.40, "", Date()),
            Article(3, "Barrete de mémoire vive", "RAM 2 x 16Go", 45.90, "", Date())
        )
    }

    override fun selectById(id: Long): Article? {
        var article: Article? = null

        try {
            article = articlesInMemory.first() {
                it.id == id
            }

        } catch (e: NoSuchElementException) {
            Log.i(TAG, "Article non trouvé !")
        }

        return article

    }

    override fun countById(id: Long): Int {
        TODO("Not yet implemented")
    }


    override fun selectAll(): List<Article> {
        return articlesInMemory
    }

    override fun addNewOne(article: Article): Long {
        article.id = articlesInMemory.size.toLong() + 1
        articlesInMemory.add(article)
        return article.id
    }

    override fun delete(article: Article) {
        TODO("Not yet implemented")
    }


}