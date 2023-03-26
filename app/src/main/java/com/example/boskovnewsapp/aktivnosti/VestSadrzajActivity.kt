package com.example.boskovnewsapp.aktivnosti

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.ScrollView
import android.widget.TextView
import com.example.boskovnewsapp.R
import com.example.boskovnewsapp.modeli.Vest
import com.squareup.picasso.Picasso

class VestSadrzajActivity : AppCompatActivity()
{
    private lateinit var slika: ImageView
    private lateinit var naslov: TextView
    private lateinit var sadrzaj: TextView

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vest_sadrzaj)

        val vest = this.intent.getParcelableExtra<Vest>("izabranaVest") as Vest

        this.slika = findViewById(R.id.SlikaVesti)
        Picasso.get().load(vest.urlToImage).into(this.slika)

        this.naslov = findViewById(R.id.NaslovVesti)
        this.naslov.text = vest.title

        this.sadrzaj = findViewById(R.id.SadrzajVesti)
        this.sadrzaj.text = vest.content
    }
}