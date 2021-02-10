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
import com.example.inventoryapp.adapters.DataItem
import com.example.inventoryapp.adapters.ProductClickListener
import com.example.inventoryapp.adapters.ProductListAdapter
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

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        val adapter = ProductListAdapter(ProductClickListener { productId ->
            viewModel.onProductClicked(productId)
        })

        binding.rvProductList.adapter = adapter

        viewModel.products.observe(viewLifecycleOwner, Observer { productList ->
            val isEmpty = productList.isEmpty()
            updateVisibilityUI(isEmpty)
            if (!isEmpty) {
                adapter.submitList(productList.map {
                    DataItem.ProductItem(it)
                })
            }
        })

        viewModel.selectedProductId.observe(viewLifecycleOwner, Observer {
            it?.let {
                this.findNavController().navigate(
                    ItemListingFragmentDirections.actionItemListingFragmentToItemDetailFragment(it)
                )
                viewModel.onProductDetailNavigated()
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

    private fun updateVisibilityUI(noProducts: Boolean) {
        if (noProducts) {
            binding.labelNoItem.visibility = View.VISIBLE
            binding.rvProductList.visibility = View.GONE
        } else {
            binding.labelNoItem.visibility = View.GONE
            binding.rvProductList.visibility = View.VISIBLE
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