package com.example.simplegame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.WindowManager

/*
Ke Zhao
CIS 195 Homework
 */

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        val dm = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(dm)
        Constants.SCREEN_WIDTH = dm.widthPixels
        Constants.SCREEN_HEIGHT = dm.heightPixels
        setContentView(GameView(this))
    }

}