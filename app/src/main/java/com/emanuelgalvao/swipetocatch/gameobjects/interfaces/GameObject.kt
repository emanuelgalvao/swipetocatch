package com.emanuelgalvao.swipetocatch.gameobjects.interfaces

import android.content.res.Resources
import android.graphics.Canvas

interface GameObject {

    val startX: Int
    val endX: Int
    val startY: Int
    val endY: Int

    fun draw(canvas: Canvas, resources: Resources)
}