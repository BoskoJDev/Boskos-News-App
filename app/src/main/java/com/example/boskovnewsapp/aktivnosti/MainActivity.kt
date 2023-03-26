package com.example.boskovnewsapp.aktivnosti

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.boskovnewsapp.R
import com.example.boskovnewsapp.RetrofitMenadzer
import com.example.boskovnewsapp.adapter.AdapterVesti
import com.example.boskovnewsapp.modeli.Vest
import com.example.boskovnewsapp.osluskivac.OsluskivacPodataka

class MainActivity : AppCompatActivity()
{
    private lateinit var pogledPretrage: SearchView

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var dijalogProgresa = ProgressDialog(this)
        dijalogProgresa.setTitle("Izvlacenje vesti...")
        dijalogProgresa.show()

        this.pogledPretrage = findViewById(R.id.PretragaVesti)
        this.pogledPretrage.setOnQueryTextListener(object : SearchView.OnQueryTextListener
        {
            override fun onQueryTextSubmit(p0: String?): Boolean
            {
                dijalogProgresa = ProgressDialog(this@MainActivity)
                dijalogProgresa.setTitle("Izvlacenje vesti iz datog upita...")
                dijalogProgresa.show()

                this@MainActivity.retrofit(dijalogProgresa, "general", p0!!)
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean = false
        })

        this.podesiDugmice()
        this.retrofit(dijalogProgresa)
    }

    private fun prikaziVesti(vesti: List<Vest>)
    {
        val adapter = AdapterVesti(this)
        adapter.setVesti(vesti)

        val lista = findViewById<RecyclerView>(R.id.ListaVesti)
        lista.layoutManager = LinearLayoutManager(this)
        lista.adapter = adapter
    }

    private fun podesiDugmice()
    {
        val osluskivac = View.OnClickListener {
            val kategorija = (it as Button).text.toString()
            val dijalogProgresa = ProgressDialog(this)
            dijalogProgresa.setTitle("Izvlacenje vesti iz '$kategorija' kategorije...")
            dijalogProgresa.show()
            retrofit(dijalogProgresa, kategorija)
        }
        
        findViewById<Button>(R.id.DugmeBiznisa).setOnClickListener(osluskivac)
        findViewById<Button>(R.id.DugmeZabave).setOnClickListener(osluskivac)
        findViewById<Button>(R.id.OpsteDugme).setOnClickListener(osluskivac)
        findViewById<Button>(R.id.DugmeZdravlja).setOnClickListener(osluskivac)
        findViewById<Button>(R.id.DugmeNauke).setOnClickListener(osluskivac)
        findViewById<Button>(R.id.DugmeSporta).setOnClickListener(osluskivac)
        findViewById<Button>(R.id.DugmeTehnologije).setOnClickListener(osluskivac)
    }

    private fun retrofit(dp: ProgressDialog, kategorija: String = "general", upit: String = "")
    {
        RetrofitMenadzer.izvuciVesti(this@MainActivity, object : OsluskivacPodataka {
            override fun primanjePodataka(vesti: List<Vest>, poruka: String) {
                prikaziVesti(vesti)
                dp.dismiss()
            }

            override fun metodaGreske(poruka: String) {}
        }, kategorija, upit)
    }
}