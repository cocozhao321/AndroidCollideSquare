package com.example.simplegame

import android.graphics.Canvas

/*
Ke Zhao
CIS 195 Homework
 */

interface GameObject {
    fun draw(canvas: Canvas)
    fun update()
}