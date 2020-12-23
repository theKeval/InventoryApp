package com.example.inventoryapp.models

import android.os.Parcel
import android.os.Parcelable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.android.parcel.Parcelize

@Parcelize
class ProductModel(
    val name: String,
    val company: String,
    val category: String,
    val description: String
) :
    Parcelable {

    private var _itemName: String = name
    val itemName: String
        get() = _itemName

    private var _itemCompany: String = company
    val itemCompany: String
        get() = _itemCompany

    private var _itemCategory: String = category
    val itemCategory: String
        get() = _itemCategory

    private var _itemDescription: String = description
    val itemDescription: String
        get() = _itemDescription


    // region commented code

//    private val _itemName = MutableLiveData<String>()
//    val itemName: LiveData<String>
//        get() = _itemName
//
//    private val _itemCompany = MutableLiveData<String>()
//    val itemCompany: LiveData<String>
//        get() = _itemCompany
//
//    private val _itemCategory = MutableLiveData<String>()
//    val itemCategory: LiveData<String>
//        get() = _itemCategory
//
//    private val _itemDescription = MutableLiveData<String>()
//    val itemDescription: LiveData<String>
//        get() = _itemDescription

//    constructor(parcel: Parcel) : this(
//        TODO("name"),
//        TODO("company"),
//        TODO("category"),
//        TODO("description")
//    ) {
//    }
//
//    override fun writeToParcel(parcel: Parcel, flags: Int) {
//        parcel.writeString(itemName)
//        parcel.writeString(itemCompany)
//        parcel.writeString(itemCategory)
//        parcel.writeString(itemDescription)
//    }
//
//    override fun describeContents(): Int {
//        return 0
//    }
//
//    companion object CREATOR : Parcelable.Creator<ProductModel> {
//        override fun createFromParcel(parcel: Parcel): ProductModel {
//            return ProductModel(parcel)
//        }
//
//        override fun newArray(size: Int): Array<ProductModel?> {
//            return arrayOfNulls(size)
//        }
//    }

    //endregion
}