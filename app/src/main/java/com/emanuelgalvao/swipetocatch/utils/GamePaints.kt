package com.emanuelgalvao.swipetocatch.utils

import android.graphics.Color
import android.graphics.Paint

object GamePaints {
    val playerPaint: Paint = Paint()
    val obstaclePaint: Paint = Paint()
    val coinPaint: Paint = Paint()
    val textPaint: Paint = Paint()

    init {
        playerPaint.color = Color.WHITE
        obstaclePaint.color = Color.parseColor("#996e6f")
        coinPaint.color = Color.parseColor("#ffcc00")
        textPaint.color = Color.WHITE
        textPaint.textSize = 80f
        textPaint.textAlign = Paint.Align.CENTER

    }
}