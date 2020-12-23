package com.example.inventoryapp.screens

import android.app.ActionBar
import android.os.Bundle
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.marginTop
import androidx.core.view.setMargins
import androidx.core.widget.TextViewCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.inventoryapp.R
import com.example.inventoryapp.databinding.FragmentItemListingBinding
import com.example.inventoryapp.models.ProductModel
import com.example.inventoryapp.viewmodels.HomeViewModel
import com.example.inventoryapp.viewmodels.ItemListingViewModel

class ItemListingFragment : Fragment() {

    private lateinit var binding: FragmentItemListingBinding
    private lateinit var viewModel: ItemListingViewModel
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_item_listing, container, false)
        viewModel = ViewModelProvider(this).get(ItemListingViewModel::class.java)
        homeViewModel = activityViewModels<HomeViewModel>().value

        homeViewModel.products.observe(viewLifecycleOwner, Observer { productList ->
            val isEmpty = productList.isEmpty()
            updateVisibilityUI(isEmpty)
            if (!isEmpty) {
                for (product in productList) {
                    addProductUI(product)
                }
            }
        })

        binding.fab.setOnClickListener {
            it.findNavController().navigate(ItemListingFragmentDirections.actionItemListingFragmentToItemDetailFragment(ProductModel("", "", "", "")))
        }

        // homeViewModel.createDummyData()

        return binding.root
    }

    private fun addProductUI(product: ProductModel) {

        val linearLayout =
            LinearLayout(ContextThemeWrapper(context, R.style.linearLayout_products_style))
        linearLayout.orientation = LinearLayout.VERTICAL
        val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        layoutParams.setMargins(16)
        linearLayout.setOnClickListener {
            it.findNavController().navigate(
                ItemListingFragmentDirections.actionItemListingFragmentToItemDetailFragment(product)
            )
        }

        val name_textView = TextView(context)
        name_textView.text = "Product Name: ${product.itemName}"
        TextViewCompat.setTextAppearance(name_textView, R.style.label_style_productListing)

        val company_textView = TextView(context)
        company_textView.text = "Company: ${product.itemCompany}"
        TextViewCompat.setTextAppearance(company_textView, R.style.label_style_productListing)

        val category_textView = TextView(context)
        category_textView.text = "Category: ${product.itemCategory}"
        TextViewCompat.setTextAppearance(category_textView, R.style.label_style_productListing)

        val description_textView = TextView(context)
        description_textView.text = "Description: ${product.itemDescription}"
        TextViewCompat.setTextAppearance(description_textView, R.style.label_style_productListing)

        linearLayout.addView(name_textView)
        linearLayout.addView(company_textView)
        linearLayout.addView(category_textView)
        linearLayout.addView(description_textView)

        binding.itemsLayout.addView(linearLayout, layoutParams)

    }

    private fun updateVisibilityUI(noProducts: Boolean) {
        if (noProducts) {
            binding.labelNoItem.visibility = View.VISIBLE
            binding.itemsScrollview.visibility = View.GONE
        } else {
            binding.labelNoItem.visibility = View.GONE
            binding.itemsScrollview.visibility = View.VISIBLE
        }
    }


}