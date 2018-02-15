package com.mattrosanio.aitest

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF


//todo: update getXDiff and getDeltaY to be limited by boundary radius
object Joystick {
    private val boundaryRadius = 100.0
    private var startingX = -1f
    private var startingY = -1f
    private var x = startingX
    private var y = startingY
    private var isJoystickActice = false
    private var deltaX = 0.0
    private var deltaY = 0.0

    fun update() {
        val clickPosition = Interface.getClickPoisition()
        if(clickPosition.x >= 0) {
            if(isJoystickActice) {
                updateJoystickPosition(clickPosition)
            } else {
                initializeJoystick(clickPosition)
            }
        } else {
            deactivateJoystick()
        }
    }

    private fun updateJoystickPosition(clickPosition: PointF) {
        deltaX = clickPosition.x.toDouble() - startingX
        deltaY = clickPosition.y.toDouble() - startingY
        x = startingX + getJoystickXComponent().toFloat()
        y = startingY + getJoystickYComponent().toFloat()
    }

    private fun initializeJoystick(clickPosition: PointF) {
        isJoystickActice = true
        startingX = clickPosition.x
        startingY = clickPosition.y
        x = startingX
        y = startingY
    }

    private fun deactivateJoystick() {
        isJoystickActice = false
        deltaX = 0.0
        deltaY = 0.0
    }

    fun getJoystickXComponent(): Double {
        return getDistance()*Math.cos(getTheta())
    }

    fun getJoystickYComponent(): Double {
        return getDistance()*Math.sin(getTheta())
    }

    private fun getDistance(): Double {
        val rawDistance = Math.sqrt((deltaX * deltaX) + (deltaY * deltaY))
        return if(rawDistance >= boundaryRadius) boundaryRadius else rawDistance
    }

    private fun getTheta(): Double {
        return Math.atan2(deltaY, deltaX)
    }

    fun draw(canvas: Canvas) {
        if(isJoystickActice) {
            val paint = Paint()
            drawInnerCircle(paint, canvas)
            drawOuterCircle(paint, canvas)
        }
    }

    private fun drawInnerCircle(paint: Paint, canvas: Canvas) {
        paint.color = Color.argb(255, 255, 255, 255)
        canvas.drawCircle(x, y, 50f, paint)
    }

    private fun drawOuterCircle(paint: Paint, canvas: Canvas) {
        paint.color = Color.argb(150, 255, 255, 255)
        canvas.drawCircle(startingX, startingY, boundaryRadius.toFloat(), paint)
    }
}