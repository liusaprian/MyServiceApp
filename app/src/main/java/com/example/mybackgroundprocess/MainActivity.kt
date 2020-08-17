package com.example.mybackgroundprocess

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import java.lang.Exception
import java.lang.ref.WeakReference

class MainActivity : AppCompatActivity() {

    companion object {
        private val LOG_ASYNC = "DemoAsync"
        private const val INPUT_STRING = "Halo Ini Demo AsyncTask!!"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        status.setText(R.string.status_pre)
        desc.text = INPUT_STRING

        GlobalScope.launch(Dispatchers.Default) {
            var output: String? = null
            Log.d(LOG_ASYNC, "status: doInBackground")

            try {
                output = "$INPUT_STRING selamat belajar!!"

                delay(5000)

                withContext(Dispatchers.Main) {
                    status.setText(R.string.status_post)
                    desc.text = output
                }
            } catch (e: Exception) {
                Log.d(LOG_ASYNC, e.message.toString())
            }
        }
    }
}

//class MainActivity : AppCompatActivity(), MyAsyncCallback {
//
//    companion object {
//        private const val INPUT_STRING = "Halo Ini Demo AsyncTask!!"
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        val demoAsync = DemoAsync(this)
//        demoAsync.execute(INPUT_STRING)
//    }
//
//
//    override fun onPreExecute() {
//        status.setText(R.string.status_pre)
//        desc.text = INPUT_STRING
//    }
//
//    override fun onPostExecute(result: String) {
//        status.setText(R.string.status_post)
//        desc.text = result
//    }
//
//    private class DemoAsync(listener: MyAsyncCallback) : AsyncTask<String, Void, String>() {
//
//        private val myListener: WeakReference<MyAsyncCallback>
//        init {
//            this.myListener = WeakReference(listener)
//        }
//
//        companion object {
//            private val LOG_ASYNC = "DemoAsync"
//        }
//
//        override fun onPreExecute() {
//            super.onPreExecute()
//            Log.d(LOG_ASYNC, "status : onPreExecute")
//
//            myListener.get()?.onPreExecute()
//        }
//
//        override fun doInBackground(vararg params: String?): String {
//            Log.d(LOG_ASYNC, "status : doInBackground")
//
//            var output: String? = null
//
//            try {
//                val input = params[0]
//                output = "$input Selamat Belajar!!"
//                Thread.sleep(2000)
//
//            } catch (e: Exception) {
//                Log.d(LOG_ASYNC, e.message.toString())
//            }
//
//            return output.toString()
//        }
//
//        override fun onPostExecute(result: String) {
//            super.onPostExecute(result)
//            Log.d(LOG_ASYNC, "status : onPostExecute")
//
//            myListener.get()?.onPostExecute(result)
//        }
//    }
//}
//
//internal interface MyAsyncCallback {
//    fun onPreExecute()
//    fun onPostExecute(text: String)
//}