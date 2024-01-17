package com.example.eni_shop.repository

import com.example.eni_shop.bo.Article
import com.example.eni_shop.dao.ArticleDAO
import com.example.eni_shop.dao.DAOFactory
import com.example.eni_shop.dao.DAOType

class ArticleRepository {

    val articleDAO: ArticleDAO = DAOFactory.createArticleDAO(DAOType.MEMORY)

    fun getArticle(id: Long): Article? {
        return articleDAO.selectById(id)
    }

    fun addArticle(article: Article): Long? {
        return articleDAO.addNewOne(article)
    }

    fun getAllArticle(): List<Article>{
        return articleDAO.selectAll()
    }

}