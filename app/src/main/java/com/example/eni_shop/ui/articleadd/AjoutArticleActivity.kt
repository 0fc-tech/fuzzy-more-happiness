package com.example.eni_shop.ui.articleadd

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.eni_shop.databinding.ActivityAddArticleBinding
import com.example.eni_shop.ui.HomeActivity


class AjoutArticleActivity : AppCompatActivity() {

    lateinit var binding: ActivityAddArticleBinding
    lateinit var vm: AjoutArticleViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        vm = ViewModelProvider(this)[AjoutArticleViewModel::class.java]

        binding.vm = vm

        binding.button.setOnClickListener {

            vm.addArticle()

            Intent(this, HomeActivity::class.java).also {
                it.putExtra("title", vm.title)
                it.putExtra("price", vm.price.toString())
                startActivity(it)
            }
        }


    }
}