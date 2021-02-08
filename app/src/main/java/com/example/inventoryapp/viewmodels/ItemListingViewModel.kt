package com.example.inventoryapp.viewmodels

import androidx.lifecycle.*
import com.example.inventoryapp.HomeActivity
import com.example.inventoryapp.database.ProductDao
import com.example.inventoryapp.database.ProductTable

class ItemListingViewModel(dao: ProductDao): ViewModel() {

//    private var _products = MutableLiveData<List<ProductTable>>()
//    val products: LiveData<List<ProductTable>>
//        get() = _products
//
//    init {
//        val abc = dao.getProducts().value
//        if(abc != null) {
//            _products = MutableLiveData(requireNotNull(dao.getProducts().value))
//        }
//    }

    private val _products = dao.getProducts()
    val products = Transformations.map(_products) {
        it
    }

}