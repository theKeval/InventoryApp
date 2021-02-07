package com.example.inventoryapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inventoryapp.database.ProductDao
import com.example.inventoryapp.database.ProductTable
import com.example.inventoryapp.models.ProductModel
import kotlinx.coroutines.launch
import java.lang.Exception

class ItemDetailsViewModel(private val dao: ProductDao, val productId: Long) : ViewModel() {

    lateinit var product: ProductTable

    init {
        viewModelScope.launch {
            product = getSelectedProduct(productId)
        }
    }

    suspend fun getSelectedProduct(id: Long): ProductTable {
        return dao.getProduct(id)
    }

    fun saveProduct(product: ProductTable): Boolean {
        var success: Boolean = true
        viewModelScope.launch {
            success = save(product)
        }

        return success
    }

    private suspend fun save(product: ProductTable): Boolean {
        return try {
            dao.insert(product)
            true
        } catch (ex: Exception) {
            false
        }
    }

    fun updateProduct(product: ProductTable): Boolean {
        var success = true
        viewModelScope.launch {
            success = update(product)
        }

        return success
    }

    private suspend fun update(product: ProductTable): Boolean {
        return try {
            dao.update(product)
            true
        }
        catch (ex: Exception) {
            false
        }
    }

    fun deleteProduct(productId: Long): Boolean {
        var success = true
        viewModelScope.launch {
            success = delete(productId)
        }

        return success
    }

    suspend fun delete(productId: Long): Boolean {
        return try {
            // dao.deleteProduct(productId)
            true
        }
        catch (ex: Exception) {
            false
        }
    }



    fun saveOrUpdateProduct(homeVm: HomeViewModel, product: ProductModel): Boolean {
        val item = homeVm.products.value?.find { it.itemName.equals(product.itemName) }
        if (item != null) {
            homeVm.removeProduct(item)
        }

        homeVm.addProduct(product)

        return true
    }

    fun deleteProduct(homeVm: HomeViewModel): Boolean {
        // homeVm.removeProduct(product)

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