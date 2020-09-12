package com.henryhans.shopinglist.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName ="item_table")
data class Item (
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val itemName: String,
    val quantity: Int,
    val itemUnit: String
): Parcelable
