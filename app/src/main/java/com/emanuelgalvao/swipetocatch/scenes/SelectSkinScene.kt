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

class SelectSkinScene(private val screen: Screen): Scene {

    private var selectButton: ActionButton? = null

    private val selectableSkins = listOf(
        GameObjectFactory.createSelectPlayer(screen, PlayerSkin.SKIN1),
        GameObjectFactory.createSelectPlayer(screen, PlayerSkin.SKIN2),
        GameObjectFactory.createSelectPlayer(screen, PlayerSkin.SKIN3),
        GameObjectFactory.createSelectPlayer(screen, PlayerSkin.SKIN4),
    )

    override fun update(et: Float) {
        if (selectButton == null) {
            selectButton = GameObjectFactory.createActionButton(
                screen = screen,
                text = "Confirmar",
                textSize = 120f,
                context = screen.context
            )
        }
        selectableSkins.forEach {
            it.update(et)
        }
        selectButton?.update(et)
    }

    override fun render(canvas: Canvas) {
        canvas.drawColor(Color.BLACK)
        GameObjectFactory.createText(
            text = "Escolha o jogador!",
            size = 100f,
            x = screen.width/2f,
            y = screen.getPositionSize() * 3f,
            screen.context,
            canvas
        )
        selectableSkins.forEach {
            it.draw(canvas, screen.resources)
        }
        selectButton?.draw(canvas, screen.resources)
    }

    override fun onTouch(e: MotionEvent): Boolean {
        return when (e.action) {
            MotionEvent.ACTION_DOWN -> {
                if (selectButton?.isClicked(e) ?: false) {
                    screen.playSound(SoundType.TOUCH)
                    screen.scene = HowToPlayScene(screen)
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