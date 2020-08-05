package com.dara.nibbles

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Order(
    val nibble: Nibble,
    var quantity: Int
) : Parcelable