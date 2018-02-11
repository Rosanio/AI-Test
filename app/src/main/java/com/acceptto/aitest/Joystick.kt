package com.acceptto.aitest

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint


//todo: make it so wherever user clicks on screen is starting point for joystick, dissapears on lift finger
object Joystick {
    private val boundaryRadius = 100f;
    private var x = Screen.Width.of32(3)
    private var y = Screen.Height.of8(7)

    fun update() {
        val clickPosition = Interface.getClickPoisition()
        if(clickPosition.x < 0) {
            x = Screen.Width.of32(3)
            y = Screen.Height.of8(7)
        } else {
            x = clickPosition.x
            y = clickPosition.y
        }
    }

    fun draw(canvas: Canvas) {
        val paint = Paint()
        paint.color = Color.argb(255, 255, 255, 255)
        canvas.drawCircle(x, y, 50f, paint)
    }
}