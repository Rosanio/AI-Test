package com.acceptto.aitest

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.view.MotionEvent
import android.view.MotionEvent.*
import android.view.SurfaceView


@SuppressLint("ViewConstructor")
class GameView constructor(context: Context, size: Point) : SurfaceView(context), Runnable {

    private var gameThread: Thread = Thread(this)
    @Volatile private var playing = false
    private var paint = Paint()
    private var fps: Long = 0
    private var timeThisFrame: Long = 0
    private val matt: Matt = Matt(this)

    init{
        playing = true
        Screen.set(size)
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
        Joystick.update()
        matt.update()
    }

    private fun draw() {
        if(holder.surface.isValid) {
            val canvas = holder.lockCanvas()
            canvas.drawColor(Color.argb(255, 26, 128, 182))
            paint.color = Color.argb(255, 249, 129, 0)
            paint.textSize = 45f
            canvas.drawText("FPS: " + fps, 20f, 40f, paint)
            matt.draw(canvas)
            Joystick.draw(canvas)
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

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when(event.action) {
            ACTION_DOWN or ACTION_MOVE -> Interface.handleClick(event.x, event.y)
            ACTION_UP -> Interface.removeClick()
        }

        return true
    }
}