package com.mattrosanio.aitest

import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.view.SurfaceView


class Matt constructor(context: SurfaceView) {
    private var bitmapMatt = BitmapFactory.decodeResource(context.resources, R.drawable.bob)
    private var isMoving = false

    private var mattXPosition: Float = 10f
    private var mattYPosition: Float = 10f

    fun update() {
        val deltaX = Joystick.getJoystickXComponent()
        val deltaY = Joystick.getJoystickYComponent()
        mattXPosition += deltaX.toFloat()/7
        mattYPosition += deltaY.toFloat()/7
    }

    fun draw(canvas: Canvas) {
        val paint = Paint()
        canvas.drawBitmap(bitmapMatt, mattXPosition, mattYPosition, paint)
    }
}