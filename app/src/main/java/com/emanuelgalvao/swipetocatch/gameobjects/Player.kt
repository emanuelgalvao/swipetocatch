package com.emanuelgalvao.swipetocatch.gameobjects

import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Rect
import com.emanuelgalvao.swipetocatch.utils.BitmapUtils
import com.emanuelgalvao.swipetocatch.R
import com.emanuelgalvao.swipetocatch.gameobjects.interfaces.AnimatedObject
import com.emanuelgalvao.swipetocatch.gameobjects.interfaces.ClickableObject
import com.emanuelgalvao.swipetocatch.gameobjects.interfaces.GameObject
import com.emanuelgalvao.swipetocatch.utils.BITMAP_FRAME_SIZE
import com.emanuelgalvao.swipetocatch.utils.PlayerSkin

data class Player(
    override val startX: Int,
    override val endX: Int,
    override val startY: Int,
    override val endY: Int,
    val skin: PlayerSkin = PlayerSkin.SKIN4,
    var isSelected: Boolean = false,
    override val maxFrame: Int = 1
): AnimatedObject(), ClickableObject {

    override fun draw(canvas: Canvas, resources: Resources) {
        if (isSelected) {
            canvas.drawBitmap(
                BitmapUtils.getBitmap(resources, R.drawable.indicator),
                Rect(
                    currentFrame * BITMAP_FRAME_SIZE,
                    0,
                    (currentFrame * BITMAP_FRAME_SIZE) + BITMAP_FRAME_SIZE,
                    32
                ),
                Rect(
                    startX,
                    startY - 150,
                    endX,
                    endY - 150
                )
                ,
                null
            )
        }
        canvas.drawBitmap(
            BitmapUtils.getBitmap(resources, skin.value),
            Rect(
                currentFrame * BITMAP_FRAME_SIZE,
                0,
                (currentFrame * BITMAP_FRAME_SIZE) + BITMAP_FRAME_SIZE,
                32
            ),
            Rect(
                startX ,
                startY,
                endX,
                endY
            )
            ,
            null
        )
    }
}

fun Player.collidedWith(gameObject: GameObject): Boolean {
    val xOverlap = this.startX < gameObject.endX && this.endX > gameObject.startX
    val yOverlap = this.startY < gameObject.endY && this.endY > gameObject.startY
    return xOverlap && yOverlap
}

fun Player.isWithinField(field: Field): Boolean {
    val isWithinX = this.startX >= field.startX && this.endX <= field.endX
    val isWithinY = this.startY >= field.startY && this.endY <= field.endY
    return isWithinX && isWithinY
}