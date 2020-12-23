package com.example.inventoryapp.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.example.inventoryapp.HomeActivity
import com.example.inventoryapp.R
import com.example.inventoryapp.databinding.FragmentInstructionsBinding
import com.example.inventoryapp.databinding.FragmentItemListingBinding
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


        return binding.root
    }
}