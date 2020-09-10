package com.example.simplegame

import android.content.Context
import android.content.Intent
import android.graphics.*
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView

/*
Ke Zhao
CIS 195 Homework
 */

class GameView (context: Context): SurfaceView(context), SurfaceHolder.Callback{
    private var thread: GameThread
    private val player: Player
    private var playerPoint: Point?=null
    private var spriteList: SpriteList?=null
    private var gameOver = false

    init{
        holder.addCallback(this)
        thread = GameThread(holder,this)
        player = Player(Rect(100,100,200,200))
        playerPoint = Point(Constants.SCREEN_WIDTH/2, 3*Constants.SCREEN_HEIGHT/4)
        player.update(playerPoint!!)
        spriteList = SpriteList(300, 350, 150)
        isFocusable = true

    }
    override fun surfaceDestroyed(holder: SurfaceHolder){
        var retry = true
        while(retry){
            try{
                thread.setRunning(false)
                thread.interrupt()
            } catch(e:Exception){
                e.printStackTrace()
            }
            retry=false
        }
    }
    override fun surfaceCreated(holder: SurfaceHolder) {
        Constants.INIT_TIME = System.currentTimeMillis()

        if(!thread.isAlive) {
            thread = GameThread(holder,this)
            thread.setRunning(true)
            thread.start()
        }

    }

    override fun surfaceChanged(p0: SurfaceHolder, p1: Int, p2: Int, p3: Int) {
    }
    fun update(){
        if(!gameOver) {
            player.update(playerPoint!!)
            spriteList!!.update()
            if(spriteList!!.collide(player)){
                gameOver = true
                end()
            }
        }
    }
    override fun draw (canvas: Canvas){
        super.draw(canvas)
        canvas.drawColor(Color.BLUE)
        player.draw(canvas)
        spriteList!!.draw(canvas)
        if(gameOver){
            val paint = Paint()
            canvas.drawColor(Color.WHITE)
            paint.color=Color.BLUE
            paint.textSize=100f
            canvas.drawText("Game Over",
                (Constants.SCREEN_WIDTH/2).toFloat()-300, (Constants.SCREEN_HEIGHT/2).toFloat(), paint)
            canvas.drawText("Your Score is "+ SpriteList.score,
                Constants.SCREEN_WIDTH/4.toFloat(),(Constants.SCREEN_HEIGHT/2).toFloat()+100,paint)
        }
    }
    override fun onTouchEvent(event: MotionEvent): Boolean{
        when (event.action){
            MotionEvent.ACTION_MOVE ->
                playerPoint!!.set(event.x.toInt(),event.y.toInt())
        }
        return true
    }

    private fun end(){
       val intent = Intent(context, SubmitActivity::class.java)
       context.startActivity(intent)
    }
}