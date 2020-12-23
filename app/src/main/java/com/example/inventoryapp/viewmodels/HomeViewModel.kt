package com.example.inventoryapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.inventoryapp.models.ProductModel

class HomeViewModel : ViewModel() {

    private val _products = MutableLiveData<ArrayList<ProductModel>>()
    val products: LiveData<ArrayList<ProductModel>>
        get() = _products

    init {
        _products.value = ArrayList()
    }

    fun addProduct(product: ProductModel) {
        _products.value?.add(product)
    }

    override fun onCleared() {
        super.onCleared()
    }
}