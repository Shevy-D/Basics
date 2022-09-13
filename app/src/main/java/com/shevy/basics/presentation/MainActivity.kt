package com.shevy.basics.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.shevy.basics.databinding.ActivityMainBinding
import com.shevy.data.repository.UserRepositoryImpl
import com.shevy.data.storage.sharedprefs.SharedPrefUserStorage
import com.shevy.domain.models.SaveUserNameParam
import com.shevy.domain.models.UserName
import com.shevy.domain.usecase.GetUserNameUseCase
import com.shevy.domain.usecase.SaveUserNameUseCase

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var vm: MainViewModel

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d("AAA", "Activity created")

        vm = ViewModelProvider(this, MainViewModelFactory(this))[MainViewModel::class.java]

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