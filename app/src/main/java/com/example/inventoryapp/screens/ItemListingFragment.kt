package com.example.inventoryapp.screens

import android.app.ActionBar
import android.os.Bundle
import android.view.*
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
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import com.example.inventoryapp.R
import com.example.inventoryapp.database.ProductDatabase
import com.example.inventoryapp.database.ProductTable
import com.example.inventoryapp.databinding.FragmentItemListingBinding
import com.example.inventoryapp.models.ProductModel
import com.example.inventoryapp.viewmodelfactory.ItemListingViewModelFactory
import com.example.inventoryapp.viewmodels.HomeViewModel
import com.example.inventoryapp.viewmodels.ItemListingViewModel
import timber.log.Timber

class ItemListingFragment : Fragment() {

    private lateinit var binding: FragmentItemListingBinding
    private lateinit var viewModel: ItemListingViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_item_listing, container, false)

        // getting the Dao
        val application = requireNotNull(this.activity).application
        val dao = ProductDatabase.getInstance(application).productDatabaseDao

        val factory = ItemListingViewModelFactory(dao)
        viewModel = ViewModelProvider(this, factory).get(ItemListingViewModel::class.java)

        viewModel.products.observe(viewLifecycleOwner, Observer {
            val isEmpty = it.isEmpty()
            updateVisibilityUI(isEmpty)
            if(!isEmpty) {
                for (product in it){
                    addProductUI(product)
                }
            }
        })

        binding.fab.setOnClickListener {
            it.findNavController().navigate(
                ItemListingFragmentDirections.actionItemListingFragmentToItemDetailFragment(0L)
            )
        }

        setHasOptionsMenu(true)
        return binding.root

    }

    private fun addProductUI(product: ProductTable) {

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
                ItemListingFragmentDirections.actionItemListingFragmentToItemDetailFragment(product.productId)
            )
        }

        val name_textView = TextView(context)
        name_textView.text = "Product Name: ${product.name}"
        TextViewCompat.setTextAppearance(name_textView, R.style.label_style_productListing)

        val company_textView = TextView(context)
        company_textView.text = "Company: ${product.company}"
        TextViewCompat.setTextAppearance(company_textView, R.style.label_style_productListing)

        val category_textView = TextView(context)
        category_textView.text = "Category: ${product.category}"
        TextViewCompat.setTextAppearance(category_textView, R.style.label_style_productListing)

        val description_textView = TextView(context)
        description_textView.text = "Description: ${product.description}"
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_logout, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_item_logout -> logout()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun logout() {
        val done =
            findNavController().navigate(ItemListingFragmentDirections.actionItemListingFragmentToLoginFragment())

        Timber.i("logout done?: $done")
    }

}