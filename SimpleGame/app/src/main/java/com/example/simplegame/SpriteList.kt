package com.example.simplegame

import android.graphics.*

/*
Ke Zhao
CIS 195 Homework
 */

class SpriteList(private val gap: Int, private val spriteGap: Int, private val h: Int ){
    private val sprites: ArrayList<Sprite>
    private var startTime: Long = 0
    private var initTime: Long = 0
    companion object {
        var score = 0
    }
    init{
        sprites = ArrayList()
        moreSprites()
    }

    fun collide(player: Player): Boolean{
        for (sprite in sprites){
            if(sprite.collide(player)){
                return true
            }
        }
        return false
    }
    fun moreSprites(){
        var y_pos = -5*Constants.SCREEN_HEIGHT / 4
        while(y_pos < 0){
            val x_pos = (Math.random() * (Constants.SCREEN_WIDTH - gap)).toInt()
            sprites.add(Sprite(h,x_pos,y_pos,gap))
            y_pos += h + spriteGap
        }
    }
    fun update(){
        for(sprite in sprites){
            sprite.increment(10)
        }
        if(sprites[sprites.size-1].rectangle.top >= Constants.SCREEN_HEIGHT){
            val x = (Math.random() * (Constants.SCREEN_WIDTH - gap)).toInt()
            sprites.add(0,Sprite(h, x, sprites[0].rectangle.top-h-spriteGap, gap)) //add new sprite
            sprites.removeAt(sprites.size-1)//remove last sprite
            score+=5
        }
    }
    fun draw(canvas:Canvas){
        for (sprite in sprites){
            sprite.draw(canvas)
        }
        val paint=Paint()
        paint.color = Color.WHITE
        paint.textSize=100f
        canvas.drawText("Score: "+score, 50f, 50 + paint.descent()-paint.ascent(),paint)
    }
}