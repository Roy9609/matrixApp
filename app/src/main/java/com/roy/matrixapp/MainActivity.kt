package com.roy.matrixapp

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity

/**
 *    desc   :
 *    date   : 2025/9/1 19:38
 *    author : Roy
 *    version: 1.0
 */
class MainActivity:AppCompatActivity() {

    val TAG: String = "MatrixLog"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }



    fun testThreadAnr(view: View) {
        try {
            var number = 0
            while (number++ < 5) {
                Log.e(
                    TAG,
                    "主线程睡眠导致的ANR:次数$number/5"
                )
                try {
                    Thread.sleep(5000L)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                    Log.e( TAG, "异常信息为:" + e.message)
                }
            }
        } catch (e: Throwable) {
            e.printStackTrace()
            Log.e( TAG, "异常信息为:" + e.message)
        }
    }
}