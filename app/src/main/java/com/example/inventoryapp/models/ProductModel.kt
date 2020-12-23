package com.example.inventoryapp.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class ProductModel(name: String, company: String, category: String, description: String) {

    private val _itemName = MutableLiveData<String>()
    val itemName: LiveData<String>
        get() = _itemName

    private val _itemCompany = MutableLiveData<String>()
    val itemCompany: LiveData<String>
        get() = _itemCompany

    private val _itemCategory = MutableLiveData<String>()
    val itemCategory: LiveData<String>
        get() = _itemCategory

    private val _itemDescription = MutableLiveData<String>()
    val itemDescription: LiveData<String>
        get() = _itemDescription

    init {
        _itemName.value = name
        _itemCompany.value = company
        _itemCategory.value = category
        _itemDescription.value = description
    }

}