package com.acceptto.aitest

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.Log


//todo: make it so wherever user clicks on screen is starting point for joystick, disappears on lift finger
object Joystick {
    private val boundaryRadius = 100f;
    private val defaultX = Screen.Width.of32(3)
    private val defaultY = Screen.Height.of8(7)
    private var x = defaultX
    private var y = defaultY
    private var movementAngle: Double = 0.0

    fun update() {
        val clickPosition = Interface.getClickPoisition()
        if(clickPosition.x >= 0) {
            val deltaX: Double = clickPosition.x.toDouble() - defaultX
            val deltaY: Double = clickPosition.y.toDouble() - defaultY
            val distance = Math.sqrt(Math.pow(deltaX, 2.0)+(deltaY*deltaY))
            movementAngle = Math.atan2(deltaY, deltaX)
            if(distance >= boundaryRadius) {
                x = defaultX + boundaryRadius*Math.cos(movementAngle).toFloat()
                y = defaultY + boundaryRadius*Math.sin(movementAngle).toFloat()
            } else {
                x = clickPosition.x
                y = clickPosition.y
            }
        } else {
            x = defaultX
            y = defaultY
        }
    }

    fun draw(canvas: Canvas) {
        val paint = Paint()
        paint.color = Color.argb(255, 255, 255, 255)
        canvas.drawCircle(x, y, 50f, paint)
        paint.color = Color.argb(150, 255, 255, 255)
        canvas.drawCircle(defaultX, defaultY, boundaryRadius, paint)
    }
}