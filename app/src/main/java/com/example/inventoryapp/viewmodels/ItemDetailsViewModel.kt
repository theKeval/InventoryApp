package com.example.inventoryapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.inventoryapp.models.ProductModel

class ItemDetailsViewModel(val product: ProductModel) : ViewModel() {

    fun saveProduct(homeVm: HomeViewModel, product: ProductModel): Boolean {
        homeVm.products.value?.add(product)

        return true
    }

    fun deleteProduct(homeVm: HomeViewModel): Boolean {
        homeVm.products.value?.remove(product)

        return true
    }

    override fun onCleared() {
        super.onCleared()
    }


    // region commented code for future references

//    private val _productName = MutableLiveData<String>()
//    val productName: LiveData<String>
//        get() = _productName
//
//    private val _productCompany = MutableLiveData<String>()
//    val productCompany: LiveData<String>
//        get() = _productCompany
//
//    private val _productCategory = MutableLiveData<String>()
//    val productCategory: LiveData<String>
//        get() = _productCategory
//
//    private val _productDescription = MutableLiveData<String>()
//    val productDescription: LiveData<String>
//        get() = _productDescription
//
//    private val _savingProduct = MutableLiveData<Boolean>()
//    val savingProduct: LiveData<Boolean>
//        get() = _savingProduct
//
//    private val _deletingProduct = MutableLiveData<Boolean>()
//    val deletingProduct: LiveData<Boolean>
//        get() = _deletingProduct
//
//    init {
//        _productName.value = product.itemName
//        _productCompany.value = product.itemCompany
//        _productCategory.value = product.itemCategory
//        _productDescription.value = product.itemDescription
//    }
//
//    fun savingProductComplete() {
//        _savingProduct.value = false
//    }
//
//    fun deletingProductComplete() {
//        _deletingProduct.value = false
//    }

    // endregion
}