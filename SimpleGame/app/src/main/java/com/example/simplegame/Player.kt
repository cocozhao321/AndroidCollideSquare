package com.example.simplegame

import android.graphics.*

/*
Ke Zhao
CIS 195 Homework
 */

class Player (var rectangle: Rect): GameObject {
    init {
        this.rectangle = rectangle

    }
    override fun draw(canvas: Canvas){
        val paint = Paint()
        paint.setColor(Color.rgb(0,255,0))
        canvas.drawRect(rectangle,paint)
    }
    override fun update(){

    }
    fun update (point: Point){
        rectangle.set(point.x - rectangle.width()/2,
            point.y -rectangle.width()/2, point.x + rectangle.width()/2,
            point.y+rectangle.width()/2)
    }
}