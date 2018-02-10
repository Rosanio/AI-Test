package com.acceptto.aitest

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Point


object Joystick {
    fun update() {

    }

    fun draw(canvas: Canvas, size: Point) {
        val paint = Paint()
        paint.color = Color.argb(255, 255, 255, 255)
        canvas.drawCircle(size.x.toFloat()-25f, size.y.toFloat()/2f, 50f, paint)
    }
}