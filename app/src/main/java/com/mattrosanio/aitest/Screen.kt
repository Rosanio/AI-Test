package com.mattrosanio.aitest

import android.graphics.Point


object Screen {

    fun set(point: Point) {
        Width.set(point.x.toFloat())
        Height.set(point.y.toFloat())
    }

    object Width {
        private var width = 0f

        fun set(width: Float) {
            this.width = width
        }

        fun full(): Float {
            return width
        }

        fun of2(numerator: Int): Float {
            return (numerator* width)/2
        }

        fun of4(numerator: Int): Float {
            return (numerator* width)/4
        }

        fun of8(numerator: Int): Float {
            return (numerator* width)/8
        }

        fun of16(numerator: Int): Float {
            return (numerator* width)/16
        }

        fun of32(numerator: Int): Float {
            return (numerator* width)/32
        }
    }

    object Height {
        private var height = 0f

        fun set(height: Float) {
            this.height = height
        }

        fun full(): Float {
            return height
        }

        fun of2(numerator: Int): Float {
            return (numerator* height)/2
        }

        fun of4(numerator: Int): Float {
            return (numerator* height)/4
        }

        fun of8(numerator: Int): Float {
            return (numerator* height)/8
        }

        fun of16(numerator: Int): Float {
            return (numerator* height)/16
        }

        fun of32(numerator: Int): Float {
            return (numerator* height)/32
        }
    }


}