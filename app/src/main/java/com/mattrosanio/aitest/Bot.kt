package com.mattrosanio.aitest

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF


class Bot {
    private var defaultXPosition = 0f
    private var defaultYPosition = 0f

    private var width = 70f
    private var height = 70f

    private var rect: RectF? = null

    init {
        defaultXPosition = Screen.Width.of8(7)
        defaultYPosition = Screen.Height.half()
        rect = RectF(defaultXPosition, defaultYPosition, defaultXPosition+width, defaultYPosition+height)
    }

    fun update() {

    }

    fun draw(canvas: Canvas) {
        val paint = Paint()
        paint.color = Color.argb(255, 219, 6, 81)
        canvas.drawRect(rect, paint)
    }
}