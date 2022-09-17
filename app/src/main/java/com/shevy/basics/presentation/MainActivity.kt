package com.shevy.basics.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.shevy.basics.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    private val vm: MainViewModel by viewModels()

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d("AAA", "Activity created")

        val dataTextView = binding.dataTextView
        val dataEditView = binding.dataEditText
        val saveButton = binding.saveButton
        val receiveButton = binding.receiveButton

        vm.resultLive.observe(this, Observer { text ->
            dataTextView.text = text
        })

        saveButton.setOnClickListener {
            val text = dataEditView.text.toString()
            vm.save(text)
        }

        receiveButton.setOnClickListener {
            vm.load()
        }
    }
}