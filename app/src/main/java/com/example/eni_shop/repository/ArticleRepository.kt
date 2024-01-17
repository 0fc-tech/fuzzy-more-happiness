package com.example.eni_shop.repository

import androidx.lifecycle.LiveData
import com.example.eni_shop.bo.Article
import com.example.eni_shop.dao.ArticleDAO
import com.example.eni_shop.dao.DAOFactory
import com.example.eni_shop.dao.DAOType

class ArticleRepository {

    val articleDAOMemory: ArticleDAO = DAOFactory.createArticleDAO(DAOType.MEMORY)
    val articleDAONetwork: ArticleDAO = DAOFactory.createArticleDAO(DAOType.NETWORK)

    fun getArticle(id: Long): Article? {
        return articleDAOMemory.selectById(id)
    }

    fun addArticle(article: Article): Long? {
        return articleDAOMemory.addNewOne(article)
    }

    fun getAllArticle(): LiveData<List<Article>>{
        return articleDAONetwork.selectAll()
    }

}