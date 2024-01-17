package com.example.eni_shop.ui.articleview

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.eni_shop.R
import com.example.eni_shop.bo.Article
import com.example.eni_shop.databinding.FragmentDetailArticleBinding
import kotlin.math.log

private const val TAG = "DetailArticleFragment"

class DetailArticleFragment : Fragment() {

    val args: DetailArticleFragmentArgs by navArgs()
    lateinit var binding: FragmentDetailArticleBinding
    val vm: DetailArticleViewModel by viewModels { DetailArticleViewModel.Factory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_detail_article, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val article: Article = args.article

        vm.initCurrentArticle(article)

        binding.article = article
        binding.vm = vm
        binding.lifecycleOwner = this

        binding.textViewTitle.setOnClickListener {
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.google.com/search?q=eni-shop+" + article.titre)
            ).also {
                startActivity(it)
            }
        }
        binding.checkBoxFav.setOnClickListener {
            if (binding.checkBoxFav.isChecked) {
                Log.i(TAG, "ajout favori")
                vm.addArticleToFav(article).observe(viewLifecycleOwner) {
                    Log.i(TAG, "ajout favori = " + it)
                    Toast.makeText(
                        view.context,
                        "L'article a été ajouté à vos favoris !",
                        Toast.LENGTH_LONG
                    ).show()
                }
            } else {
                Log.i(TAG, "suppression favori")
                vm.deleteArticleFav(article)
                Toast.makeText(
                    view.context,
                    "L'article a été supprimé de vos favoris !",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}