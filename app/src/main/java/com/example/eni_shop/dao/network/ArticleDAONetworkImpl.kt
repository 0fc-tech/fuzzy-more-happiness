package com.example.eni_shop.dao.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.eni_shop.bo.Article
import com.example.eni_shop.dao.ArticleDAO
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import java.util.Date

private const val TAG = "ArticleDAONetworkImpl"
class ArticleDAONetworkImpl : ArticleDAO {
    private val clientHttp = OkHttpClient()

    override fun selectById(id: Long): Article? {
        TODO("Not yet implemented")
    }

    override fun countById(id: Long): Int {
        TODO("Not yet implemented")
    }


    override fun selectAll(): LiveData<List<Article>> {
        //Je télécharge mes listes d'article et je les renvoie au repositroy
        val listArticlesLD = MutableLiveData<List<Article>>()
        clientHttp
            .newCall(Request.Builder().url("https://fakestoreapi.com/products").build())
            .enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    Log.e(TAG, "onFailure: ${e.message}")
                }

                override fun onResponse(call: Call, response: Response) {
                    response.body?.let { responseBody->
                        listArticlesLD.postValue(deserializeResponse(responseBody.string()))
                    }
                }
            })
        return listArticlesLD
    }

    override fun addNewOne(article: Article): Long {
        TODO("Not yet implemented")
    }

    override fun delete(article: Article) {
        TODO("Not yet implemented")
    }

    private fun deserializeResponse(responseBody: String) : List<Article>{
        val arrayListArticles = arrayListOf<Article>()
        val arrayArticlesJSON = JSONArray(responseBody)
        for(articleJSONIndex in 0 until arrayArticlesJSON.length()){
            val articleJSON = arrayArticlesJSON.getJSONObject(articleJSONIndex)
            arrayListArticles.add(
                Article(
                    articleJSON.getInt("id").toLong(),
                    articleJSON.getString("title"),
                    articleJSON.getString("description"),
                    articleJSON.getDouble("price"),
                    articleJSON.getString("image"),
                    Date()
                ))
        }
        return arrayListArticles
    }
}