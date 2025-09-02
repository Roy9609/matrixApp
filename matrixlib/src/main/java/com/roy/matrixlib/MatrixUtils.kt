package com.roy.matrixlib

import android.app.Application
import com.tencent.matrix.BuildConfig
import com.tencent.matrix.Matrix
import com.tencent.matrix.iocanary.IOCanaryPlugin
import com.tencent.matrix.iocanary.config.IOConfig
import com.tencent.matrix.memory.canary.MemoryCanaryPlugin
import com.tencent.matrix.trace.TracePlugin
import com.tencent.matrix.trace.config.TraceConfig

class MatrixUtils private constructor() {

    fun initPlugin(application: Application, splashActivity: String?) {
        val builder = Matrix.Builder(application) // build matrix
        builder.pluginListener(MatrixPluginListener(application)) // add general pluginListener
        val matrixDynamicConfig = MatrixDynamicConfigImpl() // dynamic config
        val fpsEnable = matrixDynamicConfig.isFPSEnable
        val traceEnable = matrixDynamicConfig.isTraceEnable
        //Trace plugin
        val traceConfig = TraceConfig.Builder()
            .dynamicConfig(matrixDynamicConfig)
            .enableFPS(fpsEnable) //帧率
            .enableEvilMethodTrace(traceEnable) //慢方法
            .enableAnrTrace(traceEnable) //anr
            .enableStartup(traceEnable) //启动速度
            .splashActivities(splashActivity) //启动页    可以不设置
            //debug模式
            .isDebug(BuildConfig.DEBUG) //dev环境
            .isDevEnv(BuildConfig.DEBUG)
            .build()

        val tracePlugin = TracePlugin(traceConfig)
        builder.plugin(tracePlugin)

        val memoryCanaryPlugin = MemoryCanaryPlugin()
        builder.plugin(memoryCanaryPlugin)

        // io plugin
        val ioCanaryPlugin = IOCanaryPlugin(
            IOConfig.Builder()
                .dynamicConfig(matrixDynamicConfig)
                .build()
        )
        builder.plugin(ioCanaryPlugin)

        //init matrix
        Matrix.init(builder.build())
        tracePlugin.start()
    }

    companion object {
        val instance: MatrixUtils by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            MatrixUtils()
        }

    }
}
