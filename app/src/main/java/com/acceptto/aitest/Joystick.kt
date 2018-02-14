package com.acceptto.aitest

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint


//todo: make it so wherever user clicks on screen is starting point for joystick, disappears on lift finger
object Joystick {
    private val boundaryRadius = 100f;
    private var startingX = -1f
    private var startingY = -1f
    private var x = startingX
    private var y = startingY
    private var isJoystickActice = false

    fun update() {
        val clickPosition = Interface.getClickPoisition()
        if(clickPosition.x >= 0) {
            if(isJoystickActice) {
                val deltaX: Double = clickPosition.x.toDouble() - startingX
                val deltaY: Double = clickPosition.y.toDouble() - startingY
                val distance = Math.sqrt(Math.pow(deltaX, 2.0)+(deltaY*deltaY))
                val movementAngle = Math.atan2(deltaY, deltaX)
                if(distance >= boundaryRadius) {
                    x = startingX + boundaryRadius*Math.cos(movementAngle).toFloat()
                    y = startingY + boundaryRadius*Math.sin(movementAngle).toFloat()
                } else {
                    x = clickPosition.x
                    y = clickPosition.y
                }
            } else {
                isJoystickActice = true
                startingX = clickPosition.x
                startingY = clickPosition.y
                x = startingX
                y = startingY
            }
        } else {
            isJoystickActice = false
        }
    }

    fun draw(canvas: Canvas) {
        if(isJoystickActice) {
            val paint = Paint()
            paint.color = Color.argb(255, 255, 255, 255)
            canvas.drawCircle(x, y, 50f, paint)
            paint.color = Color.argb(150, 255, 255, 255)
            canvas.drawCircle(startingX, startingY, boundaryRadius, paint)
        }
    }
}