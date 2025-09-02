package com.example.myapplication.matrix

import android.app.Application
import com.roy.matrixlib.MatrixUtils


object AppMatrix {
    fun initMatrix(application: Application,splashActivity:String) {
        MatrixUtils.instance.initPlugin(application, splashActivity) //"com.example.myapplication.matrix.MatrixActivity;"
    }
}
