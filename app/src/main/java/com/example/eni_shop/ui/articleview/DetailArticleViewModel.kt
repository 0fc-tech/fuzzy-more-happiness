package com.example.eni_shop.ui.articleview

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.eni_shop.bo.Article
import com.example.eni_shop.dao.ArticleDAO
import com.example.eni_shop.db.AppDatabase
import kotlinx.coroutines.launch

private const val TAG = "DetailArticleViewModel"
class DetailArticleViewModel(private val articleDAO: ArticleDAO) : ViewModel() {

    var currentArticle  = MutableLiveData<Article>()
    var fav = MutableLiveData<Boolean>(false)

    fun initCurrentArticle(article : Article){
        currentArticle.value = article
        viewModelScope.launch {
            val count = articleDAO.countById(article.id)
            if(count > 0){
                fav.value = true
            }
        }
    }

    fun addArticleToFav(article: Article): MutableLiveData<Long> {
        val id = MutableLiveData<Long>()
        viewModelScope.launch {
            id.value = articleDAO.addNewOne(article)
        }
        return id
    }

    fun deleteArticleFav(article: Article) {
        viewModelScope.launch {
            articleDAO.delete(article)
        }
    }

    companion object {

        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {

            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                val application =
                    checkNotNull(extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY])

                return DetailArticleViewModel(
                    AppDatabase.getInstance(application.applicationContext).articleDAO()
                ) as T
            }
        }
    }
}