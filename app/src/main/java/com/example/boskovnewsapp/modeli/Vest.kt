package com.example.boskovnewsapp.modeli

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Vest(
    val source: IzvorVesti,
    val author: String?,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String
) : Parcelable