package com.emanuelgalvao.swipetocatch.gameobjects

import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import com.emanuelgalvao.swipetocatch.gameobjects.interfaces.GameObject

data class FieldBorder(
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
            ),
            Paint()
        )
        canvas.drawRect(
            Rect(
                startX+4,
                startY+4,
                endX-4,
                endY-4
            ),
            Paint().apply {
                color = Color.WHITE
            }
        )
        canvas.drawRect(
            Rect(
                startX+16,
                startY+16,
                endX-16,
                endY-16
            ),
            Paint()
        )
    }
}
