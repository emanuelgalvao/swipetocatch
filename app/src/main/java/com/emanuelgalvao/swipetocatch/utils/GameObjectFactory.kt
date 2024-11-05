package com.emanuelgalvao.swipetocatch.utils

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import androidx.core.content.res.ResourcesCompat
import com.emanuelgalvao.swipetocatch.R
import com.emanuelgalvao.swipetocatch.gameobjects.ActionButton
import com.emanuelgalvao.swipetocatch.gameobjects.Coin
import com.emanuelgalvao.swipetocatch.gameobjects.Field
import com.emanuelgalvao.swipetocatch.gameobjects.FieldBorder
import com.emanuelgalvao.swipetocatch.gameobjects.HomeButton
import com.emanuelgalvao.swipetocatch.gameobjects.Obstacle
import com.emanuelgalvao.swipetocatch.gameobjects.Player
import com.emanuelgalvao.swipetocatch.gameobjects.RestartButton

object GameObjectFactory {

    fun createFieldBorder(screen: Screen) =
        FieldBorder(
            startX = screen.getFieldBorderStartX(),
            endX = screen.getFieldBorderEndX(),
            startY = screen.getFieldBorderStartY(),
            endY = screen.getFieldBorderEndY()
        )

    fun createField(screen: Screen) =
        Field(
            startX = screen.getFieldStartX(),
            endX = screen.getFieldEndX(),
            startY = screen.getFieldStartY(),
            endY = screen.getFieldEndY()
        )

    fun createObstacle(
        xPosition: FieldPosition,
        yPosition: FieldPosition,
        screen: Screen
    ) = Obstacle(
        startX = xPosition.calculateStartX(screen),
        endX = xPosition.calculateEndX(screen),
        startY = yPosition.calculateStartY(screen),
        endY = yPosition.calculateEndY(screen)
    )

    fun createPlayer(
        screen: Screen
    ) = Player(
        startX = screen.getFieldStartX(),
        endX = screen.getFieldStartX() + screen.getPositionSize(),
        startY = screen.getFieldStartY(),
        endY = screen.getFieldStartY() + screen.getPositionSize(),
        skin = Skin.getSelectedSkin(),
        maxFrame = 1
    )

    fun createSelectPlayer(
        screen: Screen,
        skin: PlayerSkin
    ): Player {
        val xStart = when(skin) {
            PlayerSkin.SKIN1 -> (screen.width / 2) - (screen.getPositionSize() * 3.5)
            PlayerSkin.SKIN2 -> (screen.width / 2) - (screen.getPositionSize() * 1.5)
            PlayerSkin.SKIN3 -> (screen.width / 2) + (screen.getPositionSize() * 0.5)
            PlayerSkin.SKIN4 -> (screen.width / 2) + (screen.getPositionSize() * 2.5)
        }
        return Player(
            startX = xStart.toInt(),
            endX = xStart.toInt() + screen.getPositionSize(),
            startY = screen.height / 3,
            endY = (screen.height / 3) + screen.getPositionSize(),
            skin = skin,
            isSelected = skin == PlayerSkin.SKIN1
        )
    }

    fun createCoin(
        xPosition: FieldPosition,
        yPosition: FieldPosition,
        screen: Screen
    ) = Coin(
        startX = xPosition.calculateStartX(screen),
        endX = xPosition.calculateEndX(screen),
        startY = yPosition.calculateStartY(screen),
        endY = yPosition.calculateEndY(screen)
    )

    fun createRestartButton(
        screen: Screen
    ) = RestartButton(
        startX = screen.getFieldBorderEndX() - screen.getPositionSize(),
        endX = screen.getFieldBorderEndX(),
        startY = screen.getPositionSize(),
        endY = screen.getPositionSize() * 2
    )

    fun createHomeButton(
        screen: Screen
    ) = HomeButton(
        startX = screen.getFieldBorderEndX() - (screen.getPositionSize() * 2) - FIELD_BORDER,
        endX = screen.getFieldBorderEndX() - screen.getPositionSize() - FIELD_BORDER,
        startY = screen.getPositionSize(),
        endY = screen.getPositionSize() * 2
    )

    fun createActionButton(
        screen: Screen,
        text: String,
        textSize: Float = 160f,
        context: Context
    ) = ActionButton(
        startX = screen.getPositionSize(),
        endX = screen.width - screen.getPositionSize(),
        startY = (screen.height / 2),
        endY = (screen.height / 2) - (screen.getPositionSize() / 2) + (screen.getPositionSize() * 4),
        text = text,
        textSize = textSize,
        textX = screen.width / 2,
        textY = (screen.height / 2) - (screen.getPositionSize() / 2) + (screen.getPositionSize() * 2.5).toInt(),
        context = context
    )

    fun createText(
        text: String,
        size: Float,
        x: Float,
        y: Float,
        context: Context,
        canvas: Canvas
    ) {
        canvas.drawText(
            text,
            x,
            y,
            Paint().apply {
                color = Color.WHITE
                textSize = size
                textAlign = Paint.Align.CENTER
                typeface = ResourcesCompat.getFont(context, R.font.unlearne)
            }
        )
    }
}