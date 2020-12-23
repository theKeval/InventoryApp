package com.example.inventoryapp.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.inventoryapp.R
import com.example.inventoryapp.databinding.FragmentOnboardingBinding

class OnboardingFragment: Fragment() {

    private lateinit var binding:FragmentOnboardingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_onboarding, container, false)
        binding.btnNext.setOnClickListener {
            it.findNavController().navigate(OnboardingFragmentDirections.actionOnboardingFragmentToInstructionsFragment(binding.fieldName.text.toString()))
        }

        return binding.root
    }
}