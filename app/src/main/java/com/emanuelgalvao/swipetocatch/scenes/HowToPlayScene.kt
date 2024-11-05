package com.emanuelgalvao.swipetocatch.scenes

import android.graphics.Canvas
import android.graphics.Color
import android.view.MotionEvent
import com.emanuelgalvao.swipetocatch.gameobjects.ActionButton
import com.emanuelgalvao.swipetocatch.gameobjects.interfaces.isClicked
import com.emanuelgalvao.swipetocatch.utils.GameObjectFactory
import com.emanuelgalvao.swipetocatch.utils.PlayerSkin
import com.emanuelgalvao.swipetocatch.utils.Screen
import com.emanuelgalvao.swipetocatch.utils.Skin
import com.emanuelgalvao.swipetocatch.utils.SoundType
import com.emanuelgalvao.swipetocatch.utils.getPositionSize

class HowToPlayScene(private val screen: Screen): Scene {

    private var startButton: ActionButton? = null

    private val selectableSkins = listOf(
        GameObjectFactory.createSelectPlayer(screen, PlayerSkin.SKIN1),
        GameObjectFactory.createSelectPlayer(screen, PlayerSkin.SKIN2),
        GameObjectFactory.createSelectPlayer(screen, PlayerSkin.SKIN3),
        GameObjectFactory.createSelectPlayer(screen, PlayerSkin.SKIN4),
    )

    override fun update(et: Float) {
        if (startButton == null) {
            startButton = GameObjectFactory.createActionButton(
                screen = screen,
                text = "Iniciar",
                textSize = 120f,
                context = screen.context
            )
        }
        selectableSkins.forEach {
            it.update(et)
        }
        startButton?.update(et)
    }

    override fun render(canvas: Canvas) {
        canvas.drawColor(Color.BLACK)
        GameObjectFactory.createText(
            text = "Como Jogar!",
            size = 120f,
            x = screen.width/2f,
            y = screen.getPositionSize() * 3f,
            screen.context,
            canvas
        )
        GameObjectFactory.createText(
            text = "Deslize o dedo pela tela",
            size = 80f,
            x = screen.width/2f,
            y = screen.getPositionSize() * 5f,
            screen.context,
            canvas
        )
        GameObjectFactory.createText(
            text = "na horizontal e vertical",
            size = 80f,
            x = screen.width/2f,
            y = screen.getPositionSize() * 5.5f,
            screen.context,
            canvas
        )
        GameObjectFactory.createText(
            text = "para se movimentar!",
            size = 80f,
            x = screen.width/2f,
            y = screen.getPositionSize() * 6f,
            screen.context,
            canvas
        )
        GameObjectFactory.createText(
            text = "Você precisa coletar",
            size = 80f,
            x = screen.width/2f,
            y = screen.getPositionSize() * 7f,
            screen.context,
            canvas
        )
        GameObjectFactory.createText(
            text = "todas as moedas antes",
            size = 80f,
            x = screen.width/2f,
            y = screen.getPositionSize() * 7.5f,
            screen.context,
            canvas
        )
        GameObjectFactory.createText(
            text = "de acabar os movimentos!",
            size = 80f,
            x = screen.width/2f,
            y = screen.getPositionSize() * 8f,
            screen.context,
            canvas
        )
        startButton?.draw(canvas, screen.resources)
    }

    override fun onTouch(e: MotionEvent): Boolean {
        return when (e.action) {
            MotionEvent.ACTION_DOWN -> {
                if (startButton?.isClicked(e) ?: false) {
                    screen.playSound(SoundType.TOUCH)
                    screen.scene = GameScene(screen)
                    true
                }
                selectableSkins.forEach {
                    if (it.isClicked(e) && !it.isSelected) {
                        screen.playSound(SoundType.TOUCH)
                        selectableSkins.firstOrNull { it.isSelected }?.let { it.isSelected = false }
                        Skin.setSelectedSkin(it.skin)
                        it.isSelected = true
                    }
                }
                false
            }
            else -> false
        }
    }
}