package com.emanuelgalvao.swipetocatch

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.media.SoundPool
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import com.emanuelgalvao.swipetocatch.utils.Screen
import com.emanuelgalvao.swipetocatch.utils.SoundType

class MainActivity : AppCompatActivity() {

    private lateinit var soundPool: SoundPool
    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val audioAttributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_MEDIA)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build()
        soundPool = SoundPool.Builder()
            .setMaxStreams(2)
            .setAudioAttributes(audioAttributes)
            .build()

        val swipeSound = soundPool.load(this, R.raw.swipe, 1)
        val coinSound = soundPool.load(this, R.raw.coin, 1)
        val touchSound = soundPool.load(this, R.raw.touch, 1)
        val successSound = soundPool.load(this, R.raw.success, 1)
        val gameOverSound = soundPool.load(this, R.raw.gameover, 1)

        val screen = Screen(this) { soundType ->
            val sound = when (soundType) {
                SoundType.SWIPE -> swipeSound
                SoundType.COIN -> coinSound
                SoundType.TOUCH -> touchSound
                SoundType.SUCCESS -> successSound
                SoundType.GAME_OVER -> gameOverSound
            }
            soundPool.play(sound, 0.9f, 0.9f, 1, 0, 1f)
        }
        screen.setOnTouchListener(screen)
        setContentView(screen)
    }

    override fun onResume() {
        super.onResume()
        mediaPlayer = MediaPlayer.create(this, R.raw.music)
        mediaPlayer.isLooping = true
        mediaPlayer.setVolume(0.5f, 0.5f)
        mediaPlayer.start()
    }

    override fun onPause() {
        super.onPause()
        if (::mediaPlayer.isInitialized) {
            mediaPlayer.release()
        }
    }
}