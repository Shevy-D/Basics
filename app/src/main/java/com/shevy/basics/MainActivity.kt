package com.shevy.basics

import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
/*        val linLayout = LinearLayout(this)
        linLayout.orientation = LinearLayout.VERTICAL
        val linLayoutParam =
            ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            );
        setContentView(linLayout, linLayoutParam)

        val lpView = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        val tv = TextView(this)
        tv.text = "TextView"
        linLayout.addView(tv, lpView)

        val btn = Button(this)
        btn.text = "Button"
        linLayout.addView(btn, lpView)

        val leftMarginParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        leftMarginParams.leftMargin = 50

        val btn1 = Button(this)
        btn1.text = "Button1"
        linLayout.addView(btn1, leftMarginParams)

        val rightGravityParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        rightGravityParams.gravity = Gravity.RIGHT

        val btn2 = Button(this)
        btn2.text = "Button2"
        linLayout.addView(btn2, rightGravityParams)*/


    }
}