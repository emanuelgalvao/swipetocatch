package com.emanuelgalvao.swipetocatch.gameobjects.interfaces

abstract class AnimatedObject: GameObject {

    abstract val maxFrame: Int

    private var animationTime: Float = 0f
    protected var currentFrame: Int = 0
    protected var changeTime = 1000f

    fun update(elapsedTime: Float) {
        animationTime += elapsedTime
        if (animationTime >= changeTime) {
            animationTime = 0f
            currentFrame = if (currentFrame == maxFrame) 0 else currentFrame + 1
        }
    }

}