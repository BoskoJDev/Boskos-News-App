package com.example.boskovnewsapp

import android.content.Context
import android.widget.Toast
import com.example.boskovnewsapp.osluskivac.OsluskivacPodataka
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class RetrofitMenadzer
{
    companion object
    {
        private val retrofit = Retrofit.Builder().baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create()).build()

        fun izvuciVesti(kontekst: Context, osluskivac: OsluskivacPodataka, kategorija: String, upit: String)
        {
            val poziv = this.retrofit.create(NewsApiPoziv::class.java)
                .izvuciVesti("us", kategorija, upit, kontekst.getString(R.string.api_key))

            poziv.enqueue(object : Callback<NewsApiOdgovor> {
                override fun onResponse(call: Call<NewsApiOdgovor>, response: Response<NewsApiOdgovor>)
                {
                    if (!response.isSuccessful)
                    {
                        Toast.makeText(kontekst, response.code().toString(), Toast.LENGTH_LONG).show()
                        return
                    }

                    osluskivac.primanjePodataka(response.body()!!.articles, response.message())
                }

                override fun onFailure(call: Call<NewsApiOdgovor>, t: Throwable) {}
            })
        }
    }

    interface NewsApiPoziv
    {
        @GET("top-headlines")
        fun izvuciVesti(
            @Query("country") country: String,
            @Query("category") category: String,
            @Query("q") query: String,
            @Query("apiKey") apiKey: String
        ): Call<NewsApiOdgovor>
    }
}