package com.example.inventoryapp.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.inventoryapp.database.ProductDao
import com.example.inventoryapp.models.ProductModel
import com.example.inventoryapp.viewmodels.ItemDetailsViewModel
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class ItemDetailViewModelFactory(val dao: ProductDao, val productId: Long): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ItemDetailsViewModel::class.java)) {
            return ItemDetailsViewModel(dao, productId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }

}