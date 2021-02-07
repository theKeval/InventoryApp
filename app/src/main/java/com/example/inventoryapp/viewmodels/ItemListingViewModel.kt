package com.example.inventoryapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.inventoryapp.HomeActivity
import com.example.inventoryapp.database.ProductDao

/**
 * Right now there is no use of ItemListingViewModel but for future enhancements
 * I'm keeping this viewModel as it is
 */
class ItemListingViewModel(dao: ProductDao): ViewModel() {

    public var products = dao.getProducts()

}