package com.example.simplegame

import android.view.SurfaceHolder
import android.graphics.*

/*
Ke Zhao
CIS 195 Homework
 */

class GameThread (private val surfaceHolder: SurfaceHolder, private val gameView: GameView) :Thread(){
    private var running: Boolean = false
    private var targetFPS = 0.toDouble()

    fun setRunning(isRunning: Boolean){
        this.running=isRunning
    }
    override fun run(){
        var startTime: Long
        var timeMillis = (1000/MAX_FPS).toLong()
        var waitTime: Long
        var totalTime: Long = 0
        var frameCount = 0
        var targetTime = (1000/MAX_FPS).toLong()

        while(running) {
            startTime = System.nanoTime()
            canvas = null

            try {
                canvas = this.surfaceHolder.lockCanvas()
                synchronized(surfaceHolder) {
                    this.gameView.update()
                    this.gameView.draw(canvas!!)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                if (canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }

            timeMillis = (System.nanoTime() - startTime) / 1000000
            waitTime = targetTime - timeMillis

            try {
                if (waitTime > 0)
                    sleep(waitTime)
            } catch (e: Exception) {
                e.printStackTrace()
            }

            totalTime += System.nanoTime() - startTime
            frameCount++
            if (frameCount == MAX_FPS) {
                targetFPS = (1000 / (totalTime / frameCount / 1000000)).toDouble()
                frameCount = 0
                totalTime = 0
            }
        }
    }
    companion object{
        var canvas: Canvas? = null
        val MAX_FPS = 30
    }
}