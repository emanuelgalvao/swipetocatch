package com.emanuelgalvao.swipetocatch.gameobjects

import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import com.emanuelgalvao.swipetocatch.gameobjects.interfaces.GameObject

data class Field(
    override val startX: Int,
    override val endX: Int,
    override val startY: Int,
    override val endY: Int
): GameObject {

    override fun draw(canvas: Canvas, resources: Resources) {
        canvas.drawRect(
            Rect(
                startX,
                startY,
                endX,
                endY
            ), Paint().apply {
                color = Color.BLACK
            }
        )
    }
}
