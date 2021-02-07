package com.example.inventoryapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "Products")
data class ProductTable(

    @PrimaryKey(autoGenerate = true)
    var productId: Long = 0L,

    @ColumnInfo(name = "name")
    var name: String = "",

    @ColumnInfo(name = "company")
    var company: String = "",

    @ColumnInfo(name = "category")
    var category: String = "",

    @ColumnInfo(name = "description")
    var description: String = ""

)