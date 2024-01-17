package com.example.eni_shop.ui.articlelist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.eni_shop.bo.Article
import com.example.eni_shop.dao.ArticleDAO
import com.example.eni_shop.db.AppDatabase
import com.example.eni_shop.repository.ArticleRepository
import com.example.eni_shop.ui.articleview.DetailArticleViewModel
import kotlinx.coroutines.launch

class ListeArticleViewModel(private val articleDAO: ArticleDAO) : ViewModel() {


    var articles = MutableLiveData<List<Article>>()
    private val articleRepository = ArticleRepository()

    fun getArticlesList() {
        articleRepository.getAllArticle().observeForever{
            articles.value = it
        }
    }

    fun getArticlesFav(): MutableLiveData<List<Article>> {
        val articlesFav = MutableLiveData<List<Article>>()
        viewModelScope.launch {
            articlesFav.value = articleDAO.selectAll().value
        }
        return articlesFav
    }

    fun getRandomArticle() : Article{
        return articles.value?.random()!!
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

                return ListeArticleViewModel(
                    AppDatabase.getInstance(application.applicationContext).articleDAO()
                ) as T
            }
        }
    }

}