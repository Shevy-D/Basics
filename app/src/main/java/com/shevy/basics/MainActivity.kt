package com.shevy.basics

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.shevy.basics.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    @SuppressLint("CommitTransaction")
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.butLoad.setOnClickListener {
            val parameters = Bundle()
            parameters.putString("nameBut", binding.butLoad.text.toString())

            val panel = NewFragment()
            panel.arguments = parameters
            panel.show(supportFragmentManager, "load")


/*            val intent = Intent(this, Catalog::class.java)
            intent.putExtra("nameBut", binding.butLoad.text)

            startActivity(intent)*/
            //supportFragmentManager.beginTransaction().replace(R.id.content, NewFragment()).commit()

            //(context as Fragment).supporFragmenManager
            //binding.butLoad.isEnabled = false
        }
    }
}