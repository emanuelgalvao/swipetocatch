package com.emanuelgalvao.swipetocatch.scenes

import android.graphics.Canvas
import android.view.MotionEvent
import android.view.View

interface Scene {

    fun update(et: Float)
    fun render(canvas: Canvas)
    fun onTouch(e: MotionEvent): Boolean

}