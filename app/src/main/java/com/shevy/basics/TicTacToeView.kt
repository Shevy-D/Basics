package com.shevy.basics

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat

class TicTacToeView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private val paint = Paint()

    private fun init() {
        paint.color = ContextCompat.getColor(context, R.color.purple_200)
        paint.isAntiAlias = true
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = resources.displayMetrics.density * 5
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        init()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        drawVerticalLines(canvas)
        drawHorizontalLines(canvas)
    }

    private fun drawVerticalLines(canvas: Canvas) {
        canvas.drawLine(width * 1 / 3F, 0F, width * 1 / 3F, height.toFloat(), paint)
        canvas.drawLine(width * (2 / 3F), 0F, width * (2 / 3F), height.toFloat(), paint)
    }

    private fun drawHorizontalLines(canvas: Canvas) {
        canvas.drawLine(0f, height * 1/3F, width.toFloat(), height * 1/3F, paint)
        canvas.drawLine(0f, height * (2 * 1/3F), width.toFloat(), height * (2 * 1/3F), paint)
    }
}