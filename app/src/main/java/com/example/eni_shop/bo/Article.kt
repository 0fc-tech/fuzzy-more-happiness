package com.example.eni_shop.bo

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.eni_shop.utils.DateConverter
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
@Entity
data class Article(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    var titre: String = "",
    var description: String? = null,
    var prix: Double = 0.0,
    var urlImage: String? = null,
    @TypeConverters(DateConverter::class)
    var dateSortie: Date? = null,
) : Parcelable
