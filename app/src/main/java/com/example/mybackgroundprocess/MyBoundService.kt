package com.example.mybackgroundprocess

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.CountDownTimer
import android.os.IBinder
import android.util.Log

class MyBoundService : Service() {

    companion object {
        private val TAG = MyBoundService::class.java.simpleName
    }

    private var binder = MyBinder()
    private var startTime = System.currentTimeMillis()

    private var timer: CountDownTimer = object: CountDownTimer(10_000, 1000) {
        override fun onTick(p0: Long) {
            Log.d(TAG, "${System.currentTimeMillis() - startTime}")
        }

        override fun onFinish() {

        }
    }

    internal inner class MyBinder : Binder() {
        val getService: MyBoundService = this@MyBoundService
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate: ")
    }

    override fun onBind(intent: Intent): IBinder {
        Log.d(TAG, "onBind: ")
        timer.start()
        return binder
    }

    override fun onUnbind(intent: Intent): Boolean {
        Log.d(TAG, "onUnbind: ")
        timer.cancel()
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ")
    }

    override fun onRebind(intent: Intent) {
        super.onRebind(intent)
        Log.d(TAG, "onRebind: ")
    }
}
