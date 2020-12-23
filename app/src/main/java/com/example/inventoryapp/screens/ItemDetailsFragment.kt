package com.example.inventoryapp.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.example.inventoryapp.R
import com.example.inventoryapp.databinding.FragmentItemDetailsBinding
import com.example.inventoryapp.viewmodels.HomeViewModel
import com.example.inventoryapp.viewmodels.ItemDetailsViewModel

class ItemDetailsFragment: Fragment() {
    
    private lateinit var binding: FragmentItemDetailsBinding
    private lateinit var viewModel: ItemDetailsViewModel
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var args: ItemDetailsFragmentArgs
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_item_details, container, false)
        viewModel = ViewModelProvider(this).get(ItemDetailsViewModel::class.java)
        homeViewModel = activityViewModels<HomeViewModel>().value

        args = ItemDetailsFragmentArgs.fromBundle(requireArguments())
        // args.productModel

        binding.itemDetailViewModel = viewModel


        return binding.root
    }
}