package com.mattrosanio.aitest

import android.graphics.PointF


object Interface {
    private var x = -1f
    private var y = -1f

    fun handleClick(x: Float, y: Float) {
        this.x = x
        this.y = y
    }

    fun removeClick() {
        x = -1f
        y = -1f
    }

    fun getClickPoisition(): PointF {
        return PointF(x, y)
    }
}