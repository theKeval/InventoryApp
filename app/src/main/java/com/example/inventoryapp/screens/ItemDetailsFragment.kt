package com.example.inventoryapp.screens

import android.annotation.SuppressLint
import android.opengl.Visibility
import android.os.Bundle
import android.text.Editable
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.inventoryapp.R
import com.example.inventoryapp.databinding.FragmentItemDetailsBinding
import com.example.inventoryapp.models.ProductModel
import com.example.inventoryapp.viewmodelfactory.ItemDetailViewModelFactory
import com.example.inventoryapp.viewmodels.HomeViewModel
import com.example.inventoryapp.viewmodels.ItemDetailsViewModel

class ItemDetailsFragment : Fragment() {

    private lateinit var binding: FragmentItemDetailsBinding
    private lateinit var viewModel: ItemDetailsViewModel
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var selectedProduct: ProductModel

    @SuppressLint("ShowToast")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_item_details, container, false)

        selectedProduct = ItemDetailsFragmentArgs.fromBundle(arguments ?: Bundle()).productModel

        val factory = ItemDetailViewModelFactory(selectedProduct)
        viewModel = ViewModelProvider(this, factory).get(ItemDetailsViewModel::class.java)
        homeViewModel = activityViewModels<HomeViewModel>().value

        binding.itemDetailViewModel = viewModel

        binding.btnProductDetailCancel.setOnClickListener {
            NavigationUI.navigateUp(
                it.findNavController(),
                AppBarConfiguration(navGraph = it.findNavController().graph)
            )
        }
        binding.btnProductDetailSave.setOnClickListener {
            if (viewModel.saveProduct(
                    homeViewModel,
                    ProductModel(
                        binding.fieldProductDetailName.text.toString(),
                        binding.fieldProductDetailCompany.text.toString(),
                        binding.fieldProductDetailCategory.text.toString(),
                        binding.fieldProductDetailDescription.text.toString()
                    )
                )
            ) {
                NavigationUI.navigateUp(
                    it.findNavController(),
                    AppBarConfiguration(navGraph = it.findNavController().graph)
                )
            } else {
                Toast.makeText(context, getString(R.string.error_saving), Toast.LENGTH_LONG)
            }
        }
        binding.btnProductDetailDelete.setOnClickListener {
            if (viewModel.deleteProduct(homeViewModel)) {
                NavigationUI.navigateUp(
                    it.findNavController(),
                    AppBarConfiguration(navGraph = it.findNavController().graph)
                )
            } else {
                Toast.makeText(context, getString(R.string.error_deleting), Toast.LENGTH_LONG)
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
//                viewModel.saveProduct(homeViewModel)
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
        if (selectedProduct.itemName.isEmpty()) {
            binding.btnProductDetailSave.text = getString(R.string.btn_text_save)
            binding.btnProductDetailDelete.visibility = View.GONE
        } else {
            binding.btnProductDetailSave.text = getString(R.string.btn_text_update)
            binding.btnProductDetailDelete.visibility = View.VISIBLE
        }
    }

}