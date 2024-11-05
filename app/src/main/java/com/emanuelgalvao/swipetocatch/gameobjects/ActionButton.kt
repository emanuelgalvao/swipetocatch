package com.emanuelgalvao.swipetocatch.gameobjects

import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Rect
import com.emanuelgalvao.swipetocatch.utils.BitmapUtils
import com.emanuelgalvao.swipetocatch.R
import com.emanuelgalvao.swipetocatch.gameobjects.interfaces.AnimatedObject
import com.emanuelgalvao.swipetocatch.gameobjects.interfaces.ClickableObject
import com.emanuelgalvao.swipetocatch.utils.BITMAP_FRAME_SIZE
import com.emanuelgalvao.swipetocatch.utils.GameObjectFactory

data class ActionButton(
    override val startX: Int,
    override val endX: Int,
    override val startY: Int,
    override val endY: Int,
    val text: String,
    val textSize: Float,
    val textX: Int,
    val textY: Int,
    val context: Context,
    override val maxFrame: Int = 1
): AnimatedObject(), ClickableObject {

    init {
        changeTime = 500f
    }

    override fun draw(canvas: Canvas, resources: Resources) {
        canvas.drawBitmap(
            BitmapUtils.getBitmap(resources, R.drawable.action_button),
            Rect(
                0,
                currentFrame * 128,
                194,
                (currentFrame * 128) + 128
            ),
            Rect(
                startX,
                startY,
                endX,
                endY
            ),
            null
        )
        GameObjectFactory.createText(
            text = text,
            size = if (currentFrame == 0) textSize else textSize - 10f,
            x = textX.toFloat(),
            y = textY.toFloat(),
            context = context,
            canvas = canvas
        )
    }
}
