package com.example.inventoryapp.screens

import android.opengl.Visibility
import android.os.Bundle
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.children
import androidx.core.view.get
import androidx.core.widget.TextViewCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.inventoryapp.HomeActivity
import com.example.inventoryapp.R
import com.example.inventoryapp.databinding.FragmentInstructionsBinding
import com.example.inventoryapp.databinding.FragmentItemListingBinding
import com.example.inventoryapp.models.ProductModel
import com.example.inventoryapp.viewmodels.HomeViewModel
import com.example.inventoryapp.viewmodels.ItemListingViewModel

class ItemListingFragment: Fragment() {

    private lateinit var binding: FragmentItemListingBinding
    private lateinit var viewModel: ItemListingViewModel
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_item_listing, container, false)
        viewModel = ViewModelProvider(this).get(ItemListingViewModel::class.java)
        homeViewModel = activityViewModels<HomeViewModel>().value

        homeViewModel.products.observe(viewLifecycleOwner, Observer {
            // updateVisibilityUI(it.isEmpty())
        })

        addProduct(ProductModel("My Product", "", "", ""))
        addProduct(ProductModel("my second product","", "", ""))
        addProduct(ProductModel("my third product","", "", ""))
        addProduct(ProductModel("my fourth product","", "", ""))

        return binding.root
    }

    fun addProduct(product: ProductModel) {
        val linearLayout = LinearLayout(ContextThemeWrapper(context, R.style.linearLayout_products_style))
        linearLayout.orientation = LinearLayout.VERTICAL

        val textView = TextView(context)
        textView.text = "Product Name: ${product.itemName.value}"
        TextViewCompat.setTextAppearance(textView, R.style.label_style_productListing)

        linearLayout.addView(textView)
        binding.itemsLayout.addView(linearLayout)

//        val template_view: ViewGroup? = activity?.findViewById(R.id.product_container)
//        if(template_view != null){
//            val productName: View? = template_view.children.find { it.id == R.id.value_product_name }
//            (productName as TextView)?.text = product.itemName.value
//
//            binding.itemsLayout.addView(template_view)
//        }
    }

    private fun updateVisibilityUI(noProducts: Boolean) {
        if(noProducts) {
            binding.labelNoItem.visibility = View.VISIBLE
            binding.itemsScrollview.visibility = View.GONE
        }
        else {
            binding.labelNoItem.visibility = View.GONE
            binding.itemsScrollview.visibility = View.VISIBLE
        }
    }
}