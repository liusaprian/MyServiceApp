package com.example.mybackgroundprocess

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var serviceBound = false
    private lateinit var boundService: MyBoundService

    private val serviceConnection = object: ServiceConnection {
        override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
            val binder = p1 as MyBoundService.MyBinder
            boundService = binder.getService
            serviceBound = true
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
            serviceBound = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_start_service.setOnClickListener(this)
        btn_start_intent_service.setOnClickListener(this)
        btn_start_bound_service.setOnClickListener(this)
        btn_stop_bound_service.setOnClickListener(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        if(serviceBound) unbindService(serviceConnection)
    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.btn_start_service -> startService(Intent(this@MainActivity, MyService::class.java))
            R.id.btn_start_intent_service -> {
                val intentService = Intent(this, MyIntentService::class.java)
                intentService.putExtra(MyIntentService.EXTRA_DURATION, 5000L)
                startService(intentService)
            }
            R.id.btn_start_bound_service -> {
                val boundServiceIntent = Intent(this, MyBoundService::class.java)
                bindService(boundServiceIntent, serviceConnection, Context.BIND_AUTO_CREATE)
            }
            R.id.btn_stop_bound_service -> {
                unbindService(serviceConnection)
            }
        }
    }
}