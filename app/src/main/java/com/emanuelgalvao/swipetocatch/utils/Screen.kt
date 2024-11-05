package com.emanuelgalvao.swipetocatch.utils

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.view.MotionEvent
import android.view.View
import com.emanuelgalvao.swipetocatch.scenes.Scene
import com.emanuelgalvao.swipetocatch.scenes.StartScene

class Screen(private val context: Context, private val playSoundCallback: (SoundType) -> Unit): View(context), View.OnTouchListener {

    private var startTime = System.nanoTime()
    var scene: Scene = StartScene(this)

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val elapsedTime = (System.nanoTime() - startTime) / 1000000f
        startTime = System.nanoTime()

        canvas.drawColor(Color.GRAY)
        this.update(elapsedTime)
        this.render(canvas)

        invalidate()
    }

    private fun update(et: Float) {
        scene.update(et)
    }

    private fun render(canvas: Canvas) {
        scene.render(canvas)
    }

    override fun onTouch(view: View?, motionEvent: MotionEvent?): Boolean {
        val e = motionEvent ?: return false

        return scene.onTouch(e)
    }

    fun playSound(soundType: SoundType) {
        playSoundCallback(soundType)
    }
}

fun Screen.getPositionSize() = (this.width - (4 * FIELD_BORDER)) / POSITION_QUANTITY

fun Screen.getFieldStartX() = FIELD_BORDER * 2

fun Screen.getFieldEndX() = getFieldBorderEndX() - FIELD_BORDER

fun Screen.getFieldStartY() = getFieldBorderStartY() + FIELD_BORDER

fun Screen.getFieldEndY() = getFieldBorderEndY() - FIELD_BORDER

fun Screen.getFieldBorderStartX() = FIELD_BORDER

fun Screen.getFieldBorderEndX() = this.width - FIELD_BORDER

fun Screen.getFieldBorderStartY() = (this.height / 2) - (this.width /2) - (FIELD_BORDER * 2)

fun Screen.getFieldBorderEndY() = (this.height / 2) - (this.width /2) + (this.getPositionSize() * POSITION_QUANTITY)
