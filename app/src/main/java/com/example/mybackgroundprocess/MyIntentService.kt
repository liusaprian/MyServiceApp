package com.example.mybackgroundprocess

import android.app.IntentService
import android.content.Intent
import android.util.Log

class MyIntentService : IntentService("MyIntentService") {

    companion object {
        internal const val EXTRA_DURATION = "extra_duration"
        private val TAG = MyIntentService::class.java.simpleName
    }

    override fun onHandleIntent(intent: Intent?) {
        Log.d(TAG, "onHandleIntent: start...")
        val duration = intent?.getLongExtra(EXTRA_DURATION, 0) as Long
        try {
            Thread.sleep(duration)
            Log.d(TAG, "onHandleIntent: done...")
        } catch (e: InterruptedException) {
            e.printStackTrace()
            Thread.currentThread().interrupt()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "Destroyed")
    }
}
