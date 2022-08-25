package com.shevy.basics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.shevy.basics.databinding.FragmentNewBinding

class NewFragment : BottomSheetDialogFragment() {
    lateinit var binding: FragmentNewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment
        binding.loadContent.text = arguments?.getString("nameBut")

        //(context as FragmentActivity).supportFragmentManager.beginTransaction().replace(R.id.content, NewFragment()).commit()

        return binding.root
    }
}