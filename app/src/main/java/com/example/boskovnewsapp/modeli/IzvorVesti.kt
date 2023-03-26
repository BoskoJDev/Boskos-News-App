package com.example.boskovnewsapp.modeli

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class IzvorVesti(val id: String?, val name: String) : Parcelable