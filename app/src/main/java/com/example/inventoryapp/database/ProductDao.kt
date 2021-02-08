package com.example.inventoryapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ProductDao {

    @Insert
    suspend fun insert(product: ProductTable)

    @Update
    suspend fun update(product: ProductTable)

    @Query("DELETE FROM products WHERE productId == :id")
    suspend fun deleteProduct(id: Long)

    @Query("SELECT * FROM products ORDER BY productId DESC")
    fun getProducts(): LiveData<List<ProductTable>>

    @Query("SELECT * FROM products WHERE productId == :id")
    suspend fun getProduct(id: Long): ProductTable

}