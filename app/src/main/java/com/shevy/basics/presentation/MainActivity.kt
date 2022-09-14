package com.shevy.basics.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.shevy.basics.app.App
import com.shevy.basics.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    //private val vm by viewModel<MainViewModel>()

    @javax.inject.Inject
    lateinit var vmFactory: MainViewModelFactory

    private lateinit var vm: MainViewModel

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        (applicationContext as App).appComponent.inject(this)

        Log.d("AAA", "Activity created")

        vm = ViewModelProvider(this, vmFactory)[MainViewModel::class.java]

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