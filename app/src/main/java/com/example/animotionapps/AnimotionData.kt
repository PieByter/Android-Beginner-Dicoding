package com.example.animotionapps

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Suppress("DEPRECATED_ANNOTATION")
@Parcelize
data class AnimotionData(
    val name: String,
    val description: String,
    val photo: String,
    val rating: String,
    val episodes: String
) : Parcelable
