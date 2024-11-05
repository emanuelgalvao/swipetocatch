package com.emanuelgalvao.swipetocatch.utils

import com.emanuelgalvao.swipetocatch.gameobjects.Coin
import com.emanuelgalvao.swipetocatch.gameobjects.Obstacle

object GameLevels {
    private var currentLevelIndex = 0

    fun getCurrentLevel(screen: Screen): GameLevel {
        if (currentLevelIndex > getLevels(screen).lastIndex) {
            currentLevelIndex = 0
        }
        return getLevels(screen)[currentLevelIndex]
    }

    fun incrementLevel() = currentLevelIndex++

    fun isLastLevel(screen: Screen) =
        currentLevelIndex >= getLevels(screen).lastIndex

    fun resetGame() {
        currentLevelIndex = 0
    }
}

data class GameLevel(
    val availableMoves: Int,
    val obstacles: List<Obstacle>,
    val coins: List<Coin>
)

private fun getLevels(screen: Screen) = listOf(
    GameLevel(
        availableMoves = 1,
        obstacles = listOf(),
        coins = listOf(
            GameObjectFactory.createCoin(
                xPosition = FieldPosition.ZERO,
                yPosition = FieldPosition.SEVEN,
                screen = screen
            )
        )
    ),
    GameLevel(
        availableMoves = 2,
        obstacles = listOf(),
        coins = listOf(
            GameObjectFactory.createCoin(
                xPosition = FieldPosition.ZERO,
                yPosition = FieldPosition.SEVEN,
                screen = screen
            ),
            GameObjectFactory.createCoin(
                xPosition = FieldPosition.SEVEN,
                yPosition = FieldPosition.SEVEN,
                screen = screen
            )
        )
    ),
    GameLevel(
        availableMoves = 2,
        obstacles = listOf(
            GameObjectFactory.createObstacle(
                xPosition = FieldPosition.FIVE,
                yPosition = FieldPosition.ZERO,
                screen = screen
            ),
            GameObjectFactory.createObstacle(
                xPosition = FieldPosition.ZERO,
                yPosition = FieldPosition.FOUR,
                screen = screen
            )
        ),
        coins = listOf(
            GameObjectFactory.createCoin(
                xPosition = FieldPosition.SEVEN,
                yPosition = FieldPosition.THREE,
                screen = screen
            )
        )
    ),
    GameLevel(
        availableMoves = 3,
        obstacles = listOf(
            GameObjectFactory.createObstacle(
                xPosition = FieldPosition.FIVE,
                yPosition = FieldPosition.ZERO,
                screen = screen
            ),
            GameObjectFactory.createObstacle(
                xPosition = FieldPosition.ZERO,
                yPosition = FieldPosition.FOUR,
                screen = screen
            )
        ),
        coins = listOf(
            GameObjectFactory.createCoin(
                xPosition = FieldPosition.FOUR,
                yPosition = FieldPosition.SEVEN,
                screen = screen
            ),
            GameObjectFactory.createCoin(
                xPosition = FieldPosition.ZERO,
                yPosition = FieldPosition.SEVEN,
                screen = screen
            )
        )
    ),
    GameLevel(
        availableMoves = 4,
        obstacles = listOf(
            GameObjectFactory.createObstacle(
                xPosition = FieldPosition.SIX,
                yPosition = FieldPosition.ZERO,
                screen = screen
            ),
            GameObjectFactory.createObstacle(
                xPosition = FieldPosition.ZERO,
                yPosition = FieldPosition.SIX,
                screen = screen
            )
        ),
        coins = listOf(
            GameObjectFactory.createCoin(
                xPosition = FieldPosition.ZERO,
                yPosition = FieldPosition.SEVEN,
                screen = screen
            ),
            GameObjectFactory.createCoin(
                xPosition = FieldPosition.SEVEN,
                yPosition = FieldPosition.SEVEN,
                screen = screen
            )
        )
    ),
    GameLevel(
        availableMoves = 6,
        obstacles = listOf(
            GameObjectFactory.createObstacle(
                xPosition = FieldPosition.THREE,
                yPosition = FieldPosition.ZERO,
                screen = screen
            ),
            GameObjectFactory.createObstacle(
                xPosition = FieldPosition.TWO,
                yPosition = FieldPosition.FIVE,
                screen = screen
            ),
            GameObjectFactory.createObstacle(
                xPosition = FieldPosition.ZERO,
                yPosition = FieldPosition.SIX,
                screen = screen
            )
        ),
        coins = listOf(
            GameObjectFactory.createCoin(
                xPosition = FieldPosition.SEVEN,
                yPosition = FieldPosition.SEVEN,
                screen = screen
            ),
            GameObjectFactory.createCoin(
                xPosition = FieldPosition.FOUR,
                yPosition = FieldPosition.ZERO,
                screen = screen
            )
        )
    ),
    GameLevel(
        availableMoves = 6,
        obstacles = listOf(
            GameObjectFactory.createObstacle(
                xPosition = FieldPosition.ONE,
                yPosition = FieldPosition.ZERO,
                screen = screen
            ),
            GameObjectFactory.createObstacle(
                xPosition = FieldPosition.ZERO,
                yPosition = FieldPosition.FIVE,
                screen = screen
            ),
            GameObjectFactory.createObstacle(
                xPosition = FieldPosition.FIVE,
                yPosition = FieldPosition.FOUR,
                screen = screen
            ),
            GameObjectFactory.createObstacle(
                xPosition = FieldPosition.FOUR,
                yPosition = FieldPosition.TWO,
                screen = screen
            )
        ),
        coins = listOf(
            GameObjectFactory.createCoin(
                xPosition = FieldPosition.FOUR,
                yPosition = FieldPosition.SEVEN,
                screen = screen
            ),
            GameObjectFactory.createCoin(
                xPosition = FieldPosition.FOUR,
                yPosition = FieldPosition.ZERO,
                screen = screen
            )
        )
    ),
    GameLevel(
        availableMoves = 7,
        obstacles = listOf(
            GameObjectFactory.createObstacle(
                xPosition = FieldPosition.ZERO,
                yPosition = FieldPosition.SIX,
                screen = screen
            ),
            GameObjectFactory.createObstacle(
                xPosition = FieldPosition.FIVE,
                yPosition = FieldPosition.SEVEN,
                screen = screen
            ),
            GameObjectFactory.createObstacle(
                xPosition = FieldPosition.SIX,
                yPosition = FieldPosition.ZERO,
                screen = screen
            ),
            GameObjectFactory.createObstacle(
                xPosition = FieldPosition.SEVEN,
                yPosition = FieldPosition.FOUR,
                screen = screen
            )
        ),
        coins = listOf(
            GameObjectFactory.createCoin(
                xPosition = FieldPosition.SEVEN,
                yPosition = FieldPosition.ZERO,
                screen = screen
            )
        )
    ),
    GameLevel(
        availableMoves = 8,
        obstacles = listOf(
            GameObjectFactory.createObstacle(
                xPosition = FieldPosition.ZERO,
                yPosition = FieldPosition.SIX,
                screen = screen
            ),
            GameObjectFactory.createObstacle(
                xPosition = FieldPosition.SEVEN,
                yPosition = FieldPosition.ZERO,
                screen = screen
            ),
            GameObjectFactory.createObstacle(
                xPosition = FieldPosition.FIVE,
                yPosition = FieldPosition.SEVEN,
                screen = screen
            ),
            GameObjectFactory.createObstacle(
                xPosition = FieldPosition.FIVE,
                yPosition = FieldPosition.SIX,
                screen = screen
            ),
            GameObjectFactory.createObstacle(
                xPosition = FieldPosition.FIVE,
                yPosition = FieldPosition.FIVE,
                screen = screen
            ),
            GameObjectFactory.createObstacle(
                xPosition = FieldPosition.SIX,
                yPosition = FieldPosition.SIX,
                screen = screen
            )
        ),
        coins = listOf(
            GameObjectFactory.createCoin(
                xPosition = FieldPosition.SEVEN,
                yPosition = FieldPosition.FOUR,
                screen = screen
            ),
            GameObjectFactory.createCoin(
                xPosition = FieldPosition.FOUR,
                yPosition = FieldPosition.SEVEN,
                screen = screen
            )
        )
    ),
    GameLevel(
        availableMoves = 9,
        obstacles = listOf(
            GameObjectFactory.createObstacle(
                xPosition = FieldPosition.SIX,
                yPosition = FieldPosition.ZERO,
                screen = screen
            ),
            GameObjectFactory.createObstacle(
                xPosition = FieldPosition.ZERO,
                yPosition = FieldPosition.SIX,
                screen = screen
            ),
            GameObjectFactory.createObstacle(
                xPosition = FieldPosition.SIX,
                yPosition = FieldPosition.SEVEN,
                screen = screen
            )
        ),
        coins = listOf(
            GameObjectFactory.createCoin(
                xPosition = FieldPosition.ZERO,
                yPosition = FieldPosition.SEVEN,
                screen = screen
            ),
            GameObjectFactory.createCoin(
                xPosition = FieldPosition.SEVEN,
                yPosition = FieldPosition.SEVEN,
                screen = screen
            )
        )
    )
)