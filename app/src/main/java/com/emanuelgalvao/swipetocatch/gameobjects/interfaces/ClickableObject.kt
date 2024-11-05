package com.emanuelgalvao.swipetocatch.gameobjects.interfaces

import android.view.MotionEvent

interface ClickableObject: GameObject

fun ClickableObject.isClicked(e: MotionEvent): Boolean {
    return e.x > this.startX &&
            e.x < this.endX &&
            e.y > this.startY &&
            e.y < this.endY
}