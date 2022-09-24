package com.shevy.basics.clicklistener

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.shevy.basics.databinding.TestActivityBinding

class TestActivity : AppCompatActivity() {

    lateinit var binding: TestActivityBinding

    private val adapterTest = TestAdapter(::onTestClick)

    private fun onTestClick(test: Test) {
        Toast.makeText(this@TestActivity, "${test.test1} + ${test.test2}", Toast.LENGTH_LONG).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = TestActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapterTest

        val testList: MutableList<Test> = mutableListOf<Test>()
        testList.add(Test(1, "One"))
        testList.add(Test(2, "Two"))
        testList.add(Test(3, "Free"))
        testList.add(Test(4, "Four"))
        testList.add(Test(5, "Five"))
        testList.add(Test(6, "Six"))
        testList.add(Test(7, "Seven"))
        testList.add(Test(8, "Eight"))
        testList.add(Test(9, "Nine"))
        testList.add(Test(10, "Ten"))
        testList.add(Test(11, "Eleven"))
        testList.add(Test(12, "Twelve"))
        testList.add(Test(13, "Thirteen"))
        testList.add(Test(14, "Fourteen"))
        testList.add(Test(15, "Fifteen"))
        adapterTest.setTest(testList)
    }

}