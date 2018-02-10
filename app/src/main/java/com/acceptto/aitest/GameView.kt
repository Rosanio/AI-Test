package com.acceptto.aitest

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.view.MotionEvent
import android.view.MotionEvent.ACTION_DOWN
import android.view.MotionEvent.ACTION_UP
import android.view.SurfaceView


@SuppressLint("ViewConstructor")
class GameView constructor(context: Context, private var size: Point) : SurfaceView(context), Runnable {

    private var gameThread: Thread = Thread(this)
    @Volatile private var playing = false
    private var paint = Paint()
    private var fps: Long = 0
    private var timeThisFrame: Long = 0
    private var bitmapMatt = BitmapFactory.decodeResource(this.resources, R.drawable.bob)
    private var isMoving = false
    private val walkSpeedPerSecond = 150
    private var mattXPosition: Float = 10f

    init{
        playing = true
    }

    override fun run() {
        while(playing) {
            val startFrameTime = System.currentTimeMillis()
            update()
            draw()
            timeThisFrame = System.currentTimeMillis() - startFrameTime
            if(timeThisFrame > 0)
                fps = 1000 / timeThisFrame
        }
    }

    private fun update() {
        if(isMoving)
            mattXPosition+=(walkSpeedPerSecond/fps)
    }

    private fun draw() {
        if(holder.surface.isValid) {
            val canvas = holder.lockCanvas()
            canvas.drawColor(Color.argb(255, 26, 128, 182))
            paint.color = Color.argb(255, 249, 129, 0)
            paint.textSize = 45f
            canvas.drawText("FPS: " + fps, 20f, 40f, paint)
            canvas.drawBitmap(bitmapMatt, mattXPosition, 200f, paint)
            Joystick.draw(canvas, size)
            holder.unlockCanvasAndPost(canvas)
        }
    }

    fun pause() {
        playing = false
        try {
            gameThread.join()
        } catch(e: InterruptedException) {
            e.printStackTrace()
        }
    }

    fun resume() {
        playing = true
        gameThread = Thread(this)
        gameThread.start()
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when(event.action) {
            ACTION_DOWN -> isMoving = true
            ACTION_UP -> isMoving = false
        }

        return true
    }
}