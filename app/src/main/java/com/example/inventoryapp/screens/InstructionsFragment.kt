package com.example.inventoryapp.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.inventoryapp.R
import com.example.inventoryapp.databinding.FragmentInstructionsBinding

class InstructionsFragment : Fragment() {

    private lateinit var binding: FragmentInstructionsBinding
    lateinit var args: InstructionsFragmentArgs
    lateinit var name: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_instructions, container, false)

        args = InstructionsFragmentArgs.fromBundle(requireArguments())
        name = if (args.argsName.isBlank()) "Guest" else args.argsName
        binding.lableHelloInstructions.text =
            resources.getString(R.string.hello_instructions_text).plus(" $name,")

        binding.btnInstructionsNext.setOnClickListener {
            it.findNavController()
                .navigate(InstructionsFragmentDirections.actionInstructionsFragmentToItemListingFragment())
        }

        return binding.root
    }
}