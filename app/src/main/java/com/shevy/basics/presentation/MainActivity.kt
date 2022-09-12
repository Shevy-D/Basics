package com.shevy.basics.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.shevy.basics.data.repository.UserRepositoryImpl
import com.shevy.basics.data.storage.sharedprefs.SharedPrefUserStorage
import com.shevy.basics.databinding.ActivityMainBinding
import com.shevy.basics.domain.models.SaveUserNameParam
import com.shevy.basics.domain.models.UserName
import com.shevy.basics.domain.usecase.GetUserNameUseCase
import com.shevy.basics.domain.usecase.SaveUserNameUseCase

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    private val userRepository by lazy (LazyThreadSafetyMode.NONE) {UserRepositoryImpl(SharedPrefUserStorage(applicationContext))}
    private val getUserNameUseCase by lazy (LazyThreadSafetyMode.NONE) { GetUserNameUseCase(userRepository) }
    private val saveUserNameUseCase by lazy (LazyThreadSafetyMode.NONE) { SaveUserNameUseCase(userRepository) }

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dataTextView = binding.dataTextView
        val dataEditView = binding.dataEditText
        val saveButton = binding.saveButton
        val receiveButton = binding.receiveButton

        saveButton.setOnClickListener {
            val text = dataEditView.text.toString()
            val params = SaveUserNameParam(text)
            val result: Boolean = saveUserNameUseCase.execute(params)
            dataTextView.text = "Save result = $result"
        }

        receiveButton.setOnClickListener {
            val userName: UserName = getUserNameUseCase.execute()
            dataTextView.text = "${userName.firstName} ${userName.lastName}"
        }
    }
}