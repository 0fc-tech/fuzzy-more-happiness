package com.example.eni_shop.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.eni_shop.bo.Article
import com.example.eni_shop.dao.ArticleDAO
import com.example.eni_shop.utils.DateConverter


//classe abstraite, @Database permet de définir la base afin de s'y connecter
@Database(entities = [Article::class], version = 1)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {

    //liste de nos DAOs
    abstract fun articleDAO() : ArticleDAO

    companion object{

        private var INSTANCE : AppDatabase? = null

        fun getInstance(context : Context) : AppDatabase{

            var instance = INSTANCE

            if (instance == null){

                instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "articles"
                ).allowMainThreadQueries().fallbackToDestructiveMigration().build()

                INSTANCE = instance
            }
            return instance
        }
    }
}