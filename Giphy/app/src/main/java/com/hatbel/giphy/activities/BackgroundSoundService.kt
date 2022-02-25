package com.hatbel.giphy.activities

import android.app.Service
import android.content.Intent
import android.os.IBinder

import android.media.MediaPlayer
import com.hatbel.giphy.R


class BackgroundSoundService : Service() {

    private lateinit var mediaPlayer: MediaPlayer

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        mediaPlayer = MediaPlayer.create(this, R.raw.plenka)
        mediaPlayer.isLooping = true
        mediaPlayer.setVolume(100f, 100f)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        mediaPlayer.start()
        return startId
    }

    override fun onStart(intent: Intent?, startId: Int) {}
    override fun onDestroy() {
        mediaPlayer.stop()
        mediaPlayer.release()
    }

    override fun onLowMemory() {}
}