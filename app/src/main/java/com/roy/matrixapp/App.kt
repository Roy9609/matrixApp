package com.roy.matrixapp

import android.app.Application
import com.example.myapplication.matrix.AppMatrix

/**
 *    desc   :
 *    date   : 2025/9/1 19:37
 *    author : Roy
 *    version: 1.0
 */
class App:Application() {
    override fun onCreate() {
        super.onCreate()
        AppMatrix.initMatrix(this,"")
    }
}