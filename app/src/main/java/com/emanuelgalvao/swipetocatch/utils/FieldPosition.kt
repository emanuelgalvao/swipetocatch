package com.emanuelgalvao.swipetocatch.utils

enum class FieldPosition(val value: Int) {
    ZERO(0),
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7)
}

fun FieldPosition.calculateStartX(screen: Screen) = (screen.getPositionSize() * this.value) + (2 * FIELD_BORDER)

fun FieldPosition.calculateEndX(screen: Screen) = this.calculateStartX(screen) + screen.getPositionSize()

fun FieldPosition.calculateStartY(screen: Screen) = ((screen.height / 2) - (screen.width /2) - FIELD_BORDER) + (screen.getPositionSize() * this.value)

fun FieldPosition.calculateEndY(screen: Screen) = this.calculateStartY(screen) + screen.getPositionSize()