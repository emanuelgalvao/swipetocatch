package com.emanuelgalvao.swipetocatch.gameobjects

import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Rect
import com.emanuelgalvao.swipetocatch.utils.BitmapUtils
import com.emanuelgalvao.swipetocatch.R
import com.emanuelgalvao.swipetocatch.gameobjects.interfaces.AnimatedObject
import com.emanuelgalvao.swipetocatch.gameobjects.interfaces.CollisionObject
import com.emanuelgalvao.swipetocatch.gameobjects.interfaces.GameObject
import com.emanuelgalvao.swipetocatch.utils.BITMAP_FRAME_SIZE

data class Coin(
    override val startX: Int,
    override val endX: Int,
    override val startY: Int,
    override val endY: Int,
    var isCollected: Boolean = false,
    override val maxFrame: Int = 15
): AnimatedObject(), CollisionObject {

    init {
        changeTime = 200f
    }

    override fun hasCollision(x: Float, y: Float): Boolean {
        return x > startX && x < endX && y > startY && y < endY
    }

    override fun draw(canvas: Canvas, resources: Resources) {
        if (!isCollected) {
            canvas.drawBitmap(
                BitmapUtils.getBitmap(resources, R.drawable.coin),
                Rect(
                    currentFrame * BITMAP_FRAME_SIZE,
                    0,
                    (currentFrame * BITMAP_FRAME_SIZE) + BITMAP_FRAME_SIZE,
                    32
                ),
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

}