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

class StartScene(private val screen: Screen): Scene {

    private var startButton: ActionButton? = null

    override fun update(et: Float) {
        if (startButton == null) {
            startButton = GameObjectFactory.createActionButton(
                screen = screen,
                text = "Iniciar",
                context = screen.context
            )
        }
        startButton?.update(et)
    }

    override fun render(canvas: Canvas) {
        canvas.drawColor(Color.BLACK)
        GameObjectFactory.createText(
            text = "Swipe",
            size = 240f,
            x = screen.width/2f,
            y = screen.getPositionSize() * 5f,
            screen.context,
            canvas
        )
        GameObjectFactory.createText(
            text = "To",
            size = 240f,
            x = screen.width/2f,
            y = (screen.getPositionSize() * 6f),
            screen.context,
            canvas
        )
        GameObjectFactory.createText(
            text = "Catch",
            size = 240f,
            x = screen.width/2f,
            y = screen.getPositionSize() * 7f,
            screen.context,
            canvas
        )
        startButton?.draw(canvas, screen.resources)
        GameObjectFactory.createText(
            text = "Criado por Emanuel Galvão",
            size = 60f,
            x = screen.width/2f,
            y = screen.height - (screen.getPositionSize() * 2f),
            screen.context,
            canvas
        )
    }

    override fun onTouch(e: MotionEvent): Boolean {
        return when (e.action) {
            MotionEvent.ACTION_DOWN -> {
                if (startButton?.isClicked(e) ?: false) {
                    screen.playSound(SoundType.TOUCH)
                    screen.scene = SelectSkinScene(screen)
                }
                false
            }
            else -> false
        }
    }
}