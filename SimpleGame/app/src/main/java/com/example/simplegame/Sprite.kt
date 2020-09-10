package com.example.simplegame

import android.graphics.*

/*
Ke Zhao
CIS 195 Homework
 */

class Sprite(rectHeight: Int, x:Int, y:Int, gap: Int): GameObject{
    val rectangle:Rect
    private val rectangle2: Rect
    init{
        rectangle = Rect(0,y,x,y + rectHeight)
        rectangle2 = Rect(x+ gap,y,Constants.SCREEN_WIDTH,y+rectHeight)
    }

    fun collide(player: Player): Boolean{
        return Rect.intersects(rectangle, player.rectangle)|| Rect.intersects(rectangle2, player.rectangle)
    }
    override fun update(){

    }
    override fun draw(canvas: Canvas){
        val paint = Paint()
        paint.color = Color.BLACK
        canvas.drawRect(rectangle,paint)
        canvas.drawRect(rectangle2,paint)
    }

    fun increment(y: Int){
        rectangle.top += y.toInt()
        rectangle.bottom += y.toInt()
        rectangle2.top += y.toInt()
        rectangle2.bottom += y.toInt()
    }

}