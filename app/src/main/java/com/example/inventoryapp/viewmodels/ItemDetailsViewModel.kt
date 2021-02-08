package com.example.inventoryapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inventoryapp.database.ProductDao
import com.example.inventoryapp.database.ProductTable
import kotlinx.coroutines.launch

class ItemDetailsViewModel(private val dao: ProductDao, val productId: Long) : ViewModel() {

    private var _product = MutableLiveData<ProductTable>()
    val product: LiveData<ProductTable>
        get() = _product

    init {
        viewModelScope.launch {
            if(productId != 0L) {
                _product.value = getSelectedProduct(productId)
            }
        }
    }

    private suspend fun getSelectedProduct(id: Long): ProductTable {
        return dao.getProduct(id)
    }

    fun saveProduct(product: ProductTable): Boolean {
        var success = true
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
        } catch (ex: Exception) {
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

    private suspend fun delete(productId: Long): Boolean {
        return try {
            dao.deleteProduct(productId)
            true
        } catch (ex: Exception) {
            false
        }
    }

}