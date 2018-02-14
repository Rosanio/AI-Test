package com.acceptto.aitest

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
        val deltaX = Joystick.getDeltaX()
        val deltaY = Joystick.getDeltaY()
        mattXPosition += deltaX.toFloat()
        mattYPosition += deltaY.toFloat()
    }

    fun draw(canvas: Canvas) {
        val paint = Paint()
        canvas.drawBitmap(bitmapMatt, mattXPosition, mattYPosition, paint)
    }
}