package com.example.inventoryapp.screens

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.inventoryapp.R
import com.example.inventoryapp.database.ProductDatabase
import com.example.inventoryapp.database.ProductTable
import com.example.inventoryapp.databinding.FragmentItemDetailsBinding
import com.example.inventoryapp.models.ProductModel
import com.example.inventoryapp.viewmodelfactory.ItemDetailViewModelFactory
import com.example.inventoryapp.viewmodels.HomeViewModel
import com.example.inventoryapp.viewmodels.ItemDetailsViewModel

class ItemDetailsFragment : Fragment() {

    private lateinit var binding: FragmentItemDetailsBinding
    private lateinit var viewModel: ItemDetailsViewModel
    private var productId: Long = 0L

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_item_details, container, false)

        productId = ItemDetailsFragmentArgs.fromBundle(arguments ?: Bundle()).productId

        val application = requireNotNull(this.activity).application
        val dao = ProductDatabase.getInstance(application).productDatabaseDao

        val factory = ItemDetailViewModelFactory(dao, productId)
        viewModel = ViewModelProvider(this, factory).get(ItemDetailsViewModel::class.java)

        binding.itemDetailViewModel = viewModel
        binding.lifecycleOwner = this


        // Click listeners
        binding.btnProductDetailCancel.setOnClickListener {
            NavigationUI.navigateUp(
                it.findNavController(),
                AppBarConfiguration(navGraph = it.findNavController().graph)
            )
        }

        binding.btnProductDetailSave.setOnClickListener {
            val prod = ProductTable(
                0L,
                binding.fieldProductDetailName.text.toString(),
                binding.fieldProductDetailCompany.text.toString(),
                binding.fieldProductDetailCategory.text.toString(),
                binding.fieldProductDetailDescription.text.toString()
            )

            if (productId == 0L) {
                // save product
                if (viewModel.saveProduct(prod)) {
                    NavigationUI.navigateUp(
                        it.findNavController(),
                        AppBarConfiguration(navGraph = it.findNavController().graph)
                    )
                } else {
                    Toast.makeText(context, getString(R.string.error_deleting), Toast.LENGTH_LONG)
                        .show()
                }
            }
            // update product
            else {
                prod.productId = productId
                if (viewModel.updateProduct(prod)) {
                    NavigationUI.navigateUp(
                        it.findNavController(),
                        AppBarConfiguration(navGraph = it.findNavController().graph)
                    )
                } else {
                    Toast.makeText(context, getString(R.string.error_saving), Toast.LENGTH_LONG)
                        .show()
                }
            }

        }

        binding.btnProductDetailDelete.setOnClickListener {
            if (viewModel.deleteProduct(productId)) {
                NavigationUI.navigateUp(
                    it.findNavController(),
                    AppBarConfiguration(navGraph = it.findNavController().graph)
                )
            } else {
                Toast.makeText(context, getString(R.string.error_deleting), Toast.LENGTH_LONG)
                    .show()
            }
        }

        updateUI()

        return binding.root

        // region commented code for future references

        // viewModel = ViewModelProvider(this).get(ItemDetailsViewModel::class.java)

        // viewModel.productName.observe(viewLifecycleOwner, Observer { newName ->
        // binding.fieldProductDetailName.text = Editable.Factory().newEditable(newName.toString())
        // })

//        viewModel.savingProduct.observe(viewLifecycleOwner, Observer { saving ->
//            if (saving) {
//                viewModel.saveOrUpdateProduct(homeViewModel)
//                viewModel.savingProductComplete()
//            }
//        })
//
//        viewModel.deletingProduct.observe(viewLifecycleOwner, Observer { deleting ->
//            if (deleting) {
//                viewModel.deleteProduct(homeViewModel)
//                viewModel.deletingProductComplete()
//            }
//        })

        // endregion
    }

    private fun updateUI() {
        if (productId == 0L) {
            binding.btnProductDetailSave.text = getString(R.string.btn_text_save)
            binding.btnProductDetailDelete.visibility = View.GONE
        } else {
            binding.fieldProductDetailName.isEnabled = false
            binding.textDisabled.visibility = View.VISIBLE
            binding.btnProductDetailSave.text = getString(R.string.btn_text_update)
            binding.btnProductDetailDelete.visibility = View.VISIBLE
        }
    }

}