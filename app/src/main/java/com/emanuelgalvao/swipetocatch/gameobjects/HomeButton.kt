package com.emanuelgalvao.swipetocatch.gameobjects

import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Rect
import com.emanuelgalvao.swipetocatch.utils.BitmapUtils
import com.emanuelgalvao.swipetocatch.R
import com.emanuelgalvao.swipetocatch.gameobjects.interfaces.ClickableObject

data class HomeButton(
    override val startX: Int,
    override val endX: Int,
    override val startY: Int,
    override val endY: Int
): ClickableObject {

    override fun draw(canvas: Canvas, resources: Resources) {
        canvas.drawBitmap(
            BitmapUtils.getBitmap(resources, R.drawable.home),
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
