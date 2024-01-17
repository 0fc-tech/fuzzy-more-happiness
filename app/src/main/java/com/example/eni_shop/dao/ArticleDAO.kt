package com.example.eni_shop.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.eni_shop.bo.Article

@Dao
interface ArticleDAO {

    @Query("SELECT * FROM Article WHERE id = :id")
    fun selectById(id: Long) : Article?

    @Query("SELECT COUNT(*) FROM Article WHERE id = :id")
    fun countById(id : Long) : Int

    @Query("SELECT * FROM Article")
    fun selectAll() : List<Article>

    @Insert
    fun addNewOne(article : Article) : Long

    @Delete
    fun delete(article : Article)

}