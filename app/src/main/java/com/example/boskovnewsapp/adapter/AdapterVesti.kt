package com.example.boskovnewsapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.boskovnewsapp.R
import com.example.boskovnewsapp.aktivnosti.MainActivity
import com.example.boskovnewsapp.aktivnosti.VestSadrzajActivity
import com.example.boskovnewsapp.modeli.Vest
import com.squareup.picasso.Picasso

class AdapterVesti(private val kontekst: Context) : RecyclerView.Adapter<AdapterVesti.DrzacPogleda>()
{
    private lateinit var vesti: List<Vest>

    fun setVesti(vesti: List<Vest>)
    {
        this.vesti = vesti
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrzacPogleda
    {
        return DrzacPogleda(LayoutInflater.from(this.kontekst).inflate(R.layout.vest, parent, false))
    }

    override fun onBindViewHolder(holder: DrzacPogleda, position: Int)
    {
        val vest = this.vesti[position]
        holder.naslov.text = vest.title
        holder.sadrzaj.text = vest.description
        if (vest.urlToImage == "")
            return

        Picasso.get().load(vest.urlToImage).into(holder.slika)
        holder.roditelj.setOnClickListener {
            val namera = Intent(it.context, VestSadrzajActivity::class.java)
            namera.putExtra("izabranaVest", vest)
            it.context.startActivity(namera)
        }
    }

    override fun getItemCount(): Int = this.vesti.size

    class DrzacPogleda(view: View) : RecyclerView.ViewHolder(view)
    {
        val naslov = view.findViewById<TextView>(R.id.Naslov)!!
        val sadrzaj = view.findViewById<TextView>(R.id.Sadrzaj)!!
        val slika = view.findViewById<ImageView>(R.id.Slika)!!
        val roditelj = view.findViewById<CardView>(R.id.Kontejner)!!
    }
}