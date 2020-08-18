package com.example.mybackgroundprocess

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.UnsupportedOperationException

class MyService : Service() {

    companion object {
        internal var TAG = MyService::class.java.simpleName
    }

    override fun onBind(intent: Intent): IBinder {
        throw UnsupportedOperationException("Not yet implemented")
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        Log.d(TAG, "Starting Service... ")
        GlobalScope.launch {
            delay(3000)
            stopSelf()
            Log.d(TAG, "Service Stopped")
        }
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "Destroying Service...")
    }
}
