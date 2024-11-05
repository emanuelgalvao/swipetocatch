package com.emanuelgalvao.swipetocatch.gameobjects

import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Rect
import com.emanuelgalvao.swipetocatch.utils.BitmapUtils
import com.emanuelgalvao.swipetocatch.R
import com.emanuelgalvao.swipetocatch.gameobjects.interfaces.CollisionObject
import com.emanuelgalvao.swipetocatch.gameobjects.interfaces.GameObject

data class Obstacle(
    override val startX: Int,
    override val endX: Int,
    override val startY: Int,
    override val endY: Int
): GameObject, CollisionObject {

    override fun hasCollision(x: Float, y: Float): Boolean {
        return x > startX && x < endX && y > startY && y < endY
    }

    override fun draw(canvas: Canvas, resources: Resources) {
        canvas.drawBitmap(
            BitmapUtils.getBitmap(resources, R.drawable.obstacle),
            null,
            Rect(
                startX,
                startY,
                endX,
                endY
            ),
            null
        )
    }
}