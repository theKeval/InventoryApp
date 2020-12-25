package com.example.inventoryapp.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.inventoryapp.models.ProductModel
import com.example.inventoryapp.viewmodels.ItemDetailsViewModel
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class ItemDetailViewModelFactory(val product: ProductModel): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ItemDetailsViewModel::class.java)) {
            return ItemDetailsViewModel(product) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }

}