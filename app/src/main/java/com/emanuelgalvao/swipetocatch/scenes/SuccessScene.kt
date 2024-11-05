package com.emanuelgalvao.swipetocatch.scenes

import android.graphics.Canvas
import android.graphics.Color
import android.view.MotionEvent
import com.emanuelgalvao.swipetocatch.gameobjects.ActionButton
import com.emanuelgalvao.swipetocatch.gameobjects.interfaces.isClicked
import com.emanuelgalvao.swipetocatch.utils.GameLevels
import com.emanuelgalvao.swipetocatch.utils.GameObjectFactory
import com.emanuelgalvao.swipetocatch.utils.Screen
import com.emanuelgalvao.swipetocatch.utils.SoundType
import com.emanuelgalvao.swipetocatch.utils.getPositionSize

class SuccessScene(private val screen: Screen): Scene {

    private var actionButton: ActionButton? = null

    init {
        screen.playSound(SoundType.SUCCESS)
    }

    override fun update(et: Float) {
        if (actionButton == null) {
            val buttonText = if (GameLevels.isLastLevel(screen)) {
                "Início"
            } else {
                "Próximo"
            }
            actionButton = GameObjectFactory.createActionButton(
                screen = screen,
                text = buttonText,
                textSize = 120f,
                context = screen.context
            )
        }
        actionButton?.update(et)
    }

    override fun render(canvas: Canvas) {
        canvas.drawColor(Color.GRAY)

        val message = if (GameLevels.isLastLevel(screen)) {
            "Terminou!"
        } else {
           "Passou!"
        }

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
            text = message,
            size = 200f,
            x = screen.width/2f,
            y = (screen.getPositionSize() * 6f),
            screen.context,
            canvas
        )
        actionButton?.draw(canvas, screen.resources)
    }

    override fun onTouch(e: MotionEvent): Boolean {
        return when (e.action) {
            MotionEvent.ACTION_DOWN -> {
                if (actionButton?.isClicked(e) ?: false) {
                    screen.playSound(SoundType.TOUCH)
                    if (GameLevels.isLastLevel(screen)) {
                        GameLevels.resetGame()
                        screen.scene = StartScene(screen)
                    } else {
                        GameLevels.incrementLevel()
                        screen.scene = GameScene(screen)
                    }
                }
                false
            }
            else -> false
        }
    }
}