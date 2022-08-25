package com.shevy.basics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.shevy.basics.databinding.ActivityCatalogBinding
import com.shevy.basics.databinding.ActivityMainBinding

class Catalog : AppCompatActivity() {
    lateinit var binding: ActivityCatalogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCatalogBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.catalog.text = intent.getStringExtra("nameBut")
    }
}