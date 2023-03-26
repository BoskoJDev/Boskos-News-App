package com.example.boskovnewsapp.osluskivac

import com.example.boskovnewsapp.modeli.Vest

interface OsluskivacPodataka
{
    fun primanjePodataka(vesti: List<Vest>, poruka: String)
    fun metodaGreske(poruka: String)
}