package com.emanuelgalvao.swipetocatch.scenes

import android.graphics.Canvas
import android.graphics.Color
import android.view.MotionEvent
import com.emanuelgalvao.swipetocatch.utils.GameLevels
import com.emanuelgalvao.swipetocatch.utils.GameObjectFactory
import com.emanuelgalvao.swipetocatch.utils.MINIMUM_SWIPE_DISTANCE
import com.emanuelgalvao.swipetocatch.utils.MovingDirection
import com.emanuelgalvao.swipetocatch.gameobjects.Field
import com.emanuelgalvao.swipetocatch.gameobjects.FieldBorder
import com.emanuelgalvao.swipetocatch.gameobjects.HomeButton
import com.emanuelgalvao.swipetocatch.gameobjects.Player
import com.emanuelgalvao.swipetocatch.gameobjects.RestartButton
import com.emanuelgalvao.swipetocatch.gameobjects.collidedWith
import com.emanuelgalvao.swipetocatch.gameobjects.interfaces.isClicked
import com.emanuelgalvao.swipetocatch.gameobjects.isWithinField
import com.emanuelgalvao.swipetocatch.utils.getFieldEndX
import com.emanuelgalvao.swipetocatch.utils.getFieldEndY
import com.emanuelgalvao.swipetocatch.utils.getFieldStartX
import com.emanuelgalvao.swipetocatch.utils.getFieldStartY
import com.emanuelgalvao.swipetocatch.utils.getPositionSize
import com.emanuelgalvao.swipetocatch.utils.Screen
import com.emanuelgalvao.swipetocatch.utils.SoundType
import com.emanuelgalvao.swipetocatch.utils.getFieldBorderEndY
import kotlinx.coroutines.runBlocking


class GameScene(private val screen: Screen): Scene {

    private val currentLevel = GameLevels.getCurrentLevel(screen)

    // Moving
    private var clickStartPositionX = 0f
    private var clickStartPositionY = 0f
    private var movingDirection: MovingDirection? = null
    private var lastMovingDirection: MovingDirection? = MovingDirection.LEFT

    private val vel = 5000f

    // Score
    private var remainingMoves = currentLevel.availableMoves

    private val homeButton: HomeButton = GameObjectFactory.createHomeButton(
        screen = screen
    )

    private val restartButton: RestartButton = GameObjectFactory.createRestartButton(
        screen = screen
    )

    private val fieldBorder: FieldBorder = GameObjectFactory.createFieldBorder(
        screen = screen
    )

    private val field: Field = GameObjectFactory.createField(
        screen = screen
    )

    private var player: Player = GameObjectFactory.createPlayer(
        screen = screen
    )

    private val coins = currentLevel.coins

    private val obstacles = currentLevel.obstacles

    private fun changePlayerPosition(et: Float) =
        when (movingDirection) {
            MovingDirection.LEFT -> {
                val x = player.startX - (vel * et / 1000)
                player = player.copy(
                    startX = x.toInt(),
                    endX = (x + screen.getPositionSize()).toInt()
                )
            }
            MovingDirection.RIGHT -> {
                val x = player.startX + (vel * et / 1000)
                player = player.copy(
                    startX = x.toInt(),
                    endX = (x + screen.getPositionSize()).toInt()
                )
            }
            MovingDirection.UP -> {
                val y = player.startY - (vel * et / 1000)
                player = player.copy(
                    startY = y.toInt(),
                    endY = (y + screen.getPositionSize()).toInt()
                )
            }
            MovingDirection.DOWN -> {
                val y = player.startY + (vel * et / 1000)
                player = player.copy(
                    startY = y.toInt(),
                    endY = (y + screen.getPositionSize()).toInt()
                )
            }
            null -> { }
        }

    private fun validatePlayerIsInsideField() {
        if (!player.isWithinField(field)) {
            when (movingDirection) {
                MovingDirection.LEFT -> {
                    player = player.copy(
                        startX = screen.getFieldStartX(),
                        endX = screen.getFieldStartX() + screen.getPositionSize()
                    )
                }
                MovingDirection.RIGHT -> {
                    player = player.copy(
                        startX = screen.getFieldEndX() - screen.getPositionSize(),
                        endX = screen.getFieldEndX()
                    )
                }
                MovingDirection.UP -> {
                    player = player.copy(
                        startY = screen.getFieldStartY(),
                        endY = screen.getFieldStartY() + screen.getPositionSize()
                    )
                }
                MovingDirection.DOWN -> {
                    player = player.copy(
                        startY = screen.getFieldEndY() - screen.getPositionSize(),
                        endY = screen.getFieldEndY()
                    )
                }
                null -> { }
            }
            if (movingDirection != null) {
                lastMovingDirection = movingDirection
                movingDirection = null
                remainingMoves--
            }
        }
    }

    private fun validatePlayerCollidedWithObstacle() {
        if (movingDirection != null) {
        obstacles.forEach { obstacle ->
            if (player.collidedWith(obstacle)) {
                when (movingDirection) {
                    MovingDirection.LEFT -> {
                        player = player.copy(
                            startX = obstacle.endX,
                            endX = obstacle.endX + screen.getPositionSize()
                        )
                    }
                    MovingDirection.RIGHT -> {
                        player = player.copy(
                            startX = obstacle.startX - screen.getPositionSize(),
                            endX = obstacle.startX
                        )
                    }
                    MovingDirection.UP -> {
                        player = player.copy(
                            startY = obstacle.endY,
                            endY = obstacle.endY + screen.getPositionSize()
                        )
                    }
                    MovingDirection.DOWN -> {
                        player = player.copy(
                            startY = obstacle.startY - screen.getPositionSize(),
                            endY = obstacle.startY
                        )
                    }
                    null -> { }
                }
                if (movingDirection != null) {
                    lastMovingDirection = movingDirection
                    movingDirection = null
                    remainingMoves--
                }
            }
        }}
    }

    private fun validatePlayerCollidedWithCoin() {
        coins.forEach { coin ->
            if (player.collidedWith(coin) && !coin.isCollected) {
                coin.isCollected = true
                screen.playSound(SoundType.COIN)
            }
        }
    }

    override fun update(et: Float) = runBlocking {
        if (movingDirection == lastMovingDirection) {
            return@runBlocking
        }
        changePlayerPosition(et)
        validatePlayerIsInsideField()
        validatePlayerCollidedWithObstacle()
        validatePlayerCollidedWithCoin()
        coins.forEach { it.update(et) }
        player.update(et)
    }

    override fun render(canvas: Canvas) {
        canvas.drawColor(Color.BLACK)
        validateEndGame()
        drawField(canvas)
        drawPlayer(canvas)
        drawObstacles(canvas)
        drawCoins(canvas)
        drawRemainingMoves(canvas)
        homeButton.draw(canvas, screen.resources)
        restartButton.draw(canvas, screen.resources)
    }

    private fun validateEndGame() {
        if (coins.all { it.isCollected }) {
            screen.scene = SuccessScene(screen)
        } else if (remainingMoves == 0) {
            screen.scene = GameOverScene(screen)
        }
    }

    private fun drawField(canvas: Canvas) {
        fieldBorder.draw(canvas, screen.resources)
        field.draw(canvas, screen.resources)
    }

    private fun drawPlayer(canvas: Canvas) {
        player.draw(canvas, screen.resources)
    }

    private fun drawCoins(canvas: Canvas) {
        for (coin in coins) {
            coin.draw(canvas, screen.resources)
        }
    }

    private fun drawObstacles(canvas: Canvas) {
        for (obstacle in obstacles) {
            obstacle.draw(canvas, screen.resources)
        }
    }

    private fun drawRemainingMoves(canvas: Canvas) {
        GameObjectFactory.createText(
            text = "movimentos",
            size = 120f,
            x = screen.width/2f,
            y = screen.getFieldBorderEndY() + screen.getPositionSize().toFloat(),
            context = screen.context,
            canvas
        )
        GameObjectFactory.createText(
            text = "restantes",
            size = 120f,
            x = screen.width/2f,
            y = screen.getFieldBorderEndY() + (screen.getPositionSize().toFloat() * 1.7f),
            context = screen.context,
            canvas
        )
        GameObjectFactory.createText(
            text = "$remainingMoves",
            size = 280f,
            x = screen.width/2f,
            y = screen.getFieldBorderEndY() + (screen.getPositionSize().toFloat() * 3.2f),
            context = screen.context,
            canvas
        )
    }

    override fun onTouch(e: MotionEvent): Boolean {
        when (e.action) {
            MotionEvent.ACTION_DOWN -> {
                onActionDown(e)
            }
            MotionEvent.ACTION_UP -> {
                if (clickStartPositionX == 0f && clickStartPositionY == 0f) return false
                onActionUp(e)
            }
        }
        return true
    }

    private fun onActionDown(e: MotionEvent) {
        validateHomeClick(e)
        validateRestartClick(e)
        clickStartPositionX = e.x
        clickStartPositionY = e.y
    }

    private fun validateRestartClick(e: MotionEvent) {
        if (restartButton.isClicked(e)) {
            screen.playSound(SoundType.TOUCH)
            screen.scene = GameScene(screen)
        }
    }

    private fun validateHomeClick(e: MotionEvent) {
        if (homeButton.isClicked(e)) {
            screen.playSound(SoundType.TOUCH)
            GameLevels.resetGame()
            screen.scene = StartScene(screen)
        }
    }

    private fun onActionUp(e: MotionEvent) {
        if (isValidSwipe(e)) {
            movingDirection = getMovingDirection(e)
            screen.playSound(SoundType.SWIPE)
        }
    }

    private fun isValidSwipe(e: MotionEvent): Boolean {
        return getXDistance(e) > MINIMUM_SWIPE_DISTANCE ||
            getYDistance(e) > MINIMUM_SWIPE_DISTANCE
    }

    private fun getMovingDirection(e: MotionEvent): MovingDirection {
        return if (getXDistance(e) > getYDistance(e)) {
            if (e.x > clickStartPositionX) {
                MovingDirection.RIGHT
            } else {
                MovingDirection.LEFT
            }
        } else {
            if (e.y > clickStartPositionY) {
                MovingDirection.DOWN
            } else {
                MovingDirection.UP
            }
        }
    }

    private fun getXDistance(e: MotionEvent): Float {
        return if (e.x > clickStartPositionX) {
            e.x - clickStartPositionX
        } else {
            clickStartPositionX - e.x
        }
    }

    private fun getYDistance(e: MotionEvent): Float {
        return if (e.y > clickStartPositionY) {
            e.y - clickStartPositionY
        } else {
            clickStartPositionY - e.y
        }
    }
}