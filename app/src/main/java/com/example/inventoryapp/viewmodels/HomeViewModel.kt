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

    fun removeProduct(product: ProductModel) {
        _products.value?.remove(product)
    }

    fun createDummyData() {
        val dummyData = ArrayList<ProductModel>()
        dummyData.add(ProductModel("shoes", "Nike", "footwear", "well known brand for apparel "))
        dummyData.add(ProductModel("Shirts", "Arrow", "Cloths", "nice qulality shirts"))
        dummyData.add(
            ProductModel(
                "Jeans",
                "World-O-Jeans",
                "bottom wear",
                "most popular bottom wear"
            )
        )
        dummyData.add(
            ProductModel(
                "Cap",
                "Decathlon",
                "Essentials apparel",
                "variety of caps and hats"
            )
        )
        dummyData.add(
            ProductModel(
                "Spectacles",
                "EyeWear",
                "Essentials apparel",
                "shades to protect your eye"
            )
        )
        dummyData.add(ProductModel("shoes", "Nike", "footwear", "well known brand for apparel "))
        dummyData.add(ProductModel("Shirts", "Arrow", "Cloths", "nice qulality shirts"))
        dummyData.add(
            ProductModel(
                "Jeans",
                "World-O-Jeans",
                "bottom wear",
                "most populat bottom wear"
            )
        )
        dummyData.add(
            ProductModel(
                "Cap",
                "Decathlon",
                "Essentials apparel",
                "variety of caps and hats"
            )
        )
        dummyData.add(
            ProductModel(
                "Spectacles",
                "EyeWear",
                "Essentials apparel",
                "shades to protect your eye"
            )
        )
        dummyData.add(ProductModel("shoes", "Nike", "footwear", "well known brand for apparel "))
        dummyData.add(ProductModel("Shirts", "Arrow", "Cloths", "nice qulality shirts"))
        dummyData.add(
            ProductModel(
                "Jeans",
                "World-O-Jeans",
                "bottom wear",
                "most populat bottom wear"
            )
        )
        dummyData.add(
            ProductModel(
                "Cap",
                "Decathlon",
                "Essentials apparel",
                "variety of caps and hats"
            )
        )
        dummyData.add(
            ProductModel(
                "Spectacles",
                "EyeWear",
                "Essentials apparel",
                "shades to protect your eye"
            )
        )
        dummyData.add(ProductModel("shoes", "Nike", "footwear", "well known brand for apparel "))
        dummyData.add(ProductModel("Shirts", "Arrow", "Cloths", "nice qulality shirts"))
        dummyData.add(
            ProductModel(
                "Jeans",
                "World-O-Jeans",
                "bottom wear",
                "most populat bottom wear"
            )
        )
        dummyData.add(
            ProductModel(
                "Cap",
                "Decathlon",
                "Essentials apparel",
                "variety of caps and hats"
            )
        )
        dummyData.add(
            ProductModel(
                "Spectacles",
                "EyeWear",
                "Essentials apparel",
                "shades to protect your eye "
            )
        )

        _products.value?.addAll(dummyData)
    }

    override fun onCleared() {
        super.onCleared()
    }
}