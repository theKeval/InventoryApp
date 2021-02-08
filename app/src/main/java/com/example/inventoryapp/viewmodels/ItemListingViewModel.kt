package com.example.inventoryapp.viewmodels

import androidx.lifecycle.*
import com.example.inventoryapp.database.ProductDao
import com.example.inventoryapp.database.ProductTable
import kotlinx.coroutines.launch

class ItemListingViewModel(private val dao: ProductDao): ViewModel() {

    val products = dao.getProducts()

    // region commented efforts

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


//    private var _products = MutableLiveData<List<ProductTable>>()
//    val products: LiveData<List<ProductTable>>
//        get() = _products
//
//    init {
//        viewModelScope.launch {
//            _products = MutableLiveData(getProducts())
//        }
//    }
//
//    private suspend fun getProducts(): List<ProductTable> {
//        return dao.getProducts()
//    }

    // endregion

}