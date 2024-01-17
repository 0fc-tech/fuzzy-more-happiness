package com.example.eni_shop.dao

import com.example.eni_shop.dao.memory.ArticleDAOMemoryImpl
import com.example.eni_shop.dao.network.ArticleDAONetworkImpl

class DAOFactory {

    companion object {

        fun createArticleDAO(type: DAOType): ArticleDAO{

            val dao: ArticleDAO

            when(type){
                DAOType.MEMORY -> dao = ArticleDAOMemoryImpl()
                else -> dao = ArticleDAONetworkImpl()
            }

            return dao
        }

    }
}