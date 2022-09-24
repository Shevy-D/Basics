package com.shevy.basics.clicklistener

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shevy.basics.R
import com.shevy.basics.databinding.FoodItemLayoutBinding

class TestAdapter(private val onTestClick: (test: Test) -> Unit) :
    RecyclerView.Adapter<TestAdapter.TestViewHolder>() {

    private val test = mutableListOf<Test>()

    @SuppressLint("NotifyDataSetChanged")
    fun setTest(test: List<Test>) {
        this.test.apply {
            clear()
            addAll(test)
        }
        notifyDataSetChanged()
    }

    inner class TestViewHolder(item: View, private val onTestClick: (test: Test) -> Unit) :
        RecyclerView.ViewHolder(item) {

        private val binding = FoodItemLayoutBinding.bind(item)

        val foodName = binding.foodItemNameTV
        val priceName = binding.foodItemPriceTV

        init {
            itemView.setOnClickListener {
                onTestClick(test[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.food_item_layout, parent, false)
        return TestViewHolder(view, onTestClick)
    }

    override fun onBindViewHolder(holder: TestViewHolder, position: Int) {
        val itemTest = test[position]
        holder.foodName.text = itemTest.test1.toString()
        holder.priceName.text = itemTest.test2

    }

    override fun getItemCount(): Int = test.size


}