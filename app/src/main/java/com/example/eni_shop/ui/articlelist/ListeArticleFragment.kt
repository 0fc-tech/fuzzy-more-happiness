package com.example.eni_shop.ui.articlelist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.eni_shop.R
import com.example.eni_shop.bo.Article
import com.example.eni_shop.databinding.FragmentListeArticleBinding
import com.example.eni_shop.repository.ArticleRepository

class ListeArticleFragment : Fragment() {

    lateinit var binding: FragmentListeArticleBinding
    val vm: ListeArticleViewModel by viewModels { ListeArticleViewModel.Factory  }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_liste_article, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vm.getArticlesList().observe(viewLifecycleOwner) {
            displayArticles(it)
        }

        binding.imageButtonFav.setOnClickListener {
            vm.getArticlesFav().observe(viewLifecycleOwner){
                displayArticles(it)
            }
        }
    }

    private fun displayArticles(articles : List<Article>){
        binding.recyclerViewArticle.also {
            it.layoutManager = LinearLayoutManager(binding.root.context)
            it.adapter = ArticleAdapter(articles){articleClicked->
                val action =
                    ListeArticleFragmentDirections.actionListeArticleFragmentToDetailArticleFragment(
                        articleClicked
                    )
                Navigation.findNavController(binding.root).navigate(action)
            }
        }

    }
}














