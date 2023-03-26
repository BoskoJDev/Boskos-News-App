package com.example.boskovnewsapp

import com.example.boskovnewsapp.modeli.Vest

data class NewsApiOdgovor(val status: String, val totalResults: Int, val articles: List<Vest>)