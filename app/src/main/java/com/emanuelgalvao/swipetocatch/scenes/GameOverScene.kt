package com.emanuelgalvao.swipetocatch.scenes

import android.graphics.Canvas
import android.graphics.Color
import android.view.MotionEvent
import com.emanuelgalvao.swipetocatch.gameobjects.ActionButton
import com.emanuelgalvao.swipetocatch.gameobjects.interfaces.isClicked
import com.emanuelgalvao.swipetocatch.utils.GameObjectFactory
import com.emanuelgalvao.swipetocatch.utils.Screen
import com.emanuelgalvao.swipetocatch.utils.SoundType
import com.emanuelgalvao.swipetocatch.utils.getPositionSize

class GameOverScene(private val screen: Screen): Scene {

    private var restartButton: ActionButton? = null

    init {
        screen.playSound(SoundType.GAME_OVER)
    }

    override fun update(et: Float) {
        if (restartButton == null) {
            restartButton = GameObjectFactory.createActionButton(
                screen = screen,
                text = "Recomeçar",
                textSize = 120f,
                context = screen.context
            )
        }
        restartButton?.update(et)
    }

    override fun render(canvas: Canvas) {
        canvas.drawColor(Color.BLACK)
        GameObjectFactory.createText(
            text = "Você",
            size = 200f,
            x = screen.width/2f,
            y = screen.getPositionSize() * 5f,
            screen.context,
            canvas
        )
        GameObjectFactory.createText(
            text = "Perdeu!",
            size = 200f,
            x = screen.width/2f,
            y = (screen.getPositionSize() * 6f),
            screen.context,
            canvas
        )
        restartButton?.draw(canvas, screen.resources)
    }

    override fun onTouch(e: MotionEvent): Boolean {
        return when (e.action) {
            MotionEvent.ACTION_DOWN -> {
                if (restartButton?.isClicked(e) ?: false) {
                    screen.playSound(SoundType.TOUCH)
                    screen.scene = GameScene(screen)
                    true
                }
                false
            }
            else -> false
        }
    }
}