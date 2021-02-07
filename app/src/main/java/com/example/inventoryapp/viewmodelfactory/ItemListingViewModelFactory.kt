package com.example.inventoryapp.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.inventoryapp.database.ProductDao
import com.example.inventoryapp.viewmodels.ItemListingViewModel
import java.lang.IllegalArgumentException

class ItemListingViewModelFactory(private val dbDao: ProductDao): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(ItemListingViewModel::class.java)) {
            return ItemListingViewModel(dbDao) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}