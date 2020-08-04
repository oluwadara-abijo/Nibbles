package com.dara.nibbles

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Nibble(
    val name: String,
    val image: Int,
    val description: String,
    val flavour: String,
    val amount: String,
    val category: Category
) : Parcelable