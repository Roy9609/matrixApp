package com.roy.matrixlib

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.tencent.matrix.plugin.DefaultPluginListener
import com.tencent.matrix.report.Issue
import com.tencent.matrix.util.MatrixLog
import java.lang.ref.SoftReference

class MatrixPluginListener(context: Context) : DefaultPluginListener(context) {
    var softReference: SoftReference<Context> = SoftReference(context)
    private val mHandler = Handler(Looper.getMainLooper())

    override fun onReportIssue(issue: Issue) {
        super.onReportIssue(issue)
        //todo 处理性能监控数据
        MatrixLog.e(TAG, issue.toString())
        Log.e(TAG, issue.toString())

        // IssuesMap.put(IssueFilter.getCurrentFilter(), issue);

        /*   mHandler.post(new Runnable() {// 等ui线程阻塞结束后，才执行
            @Override
            public void run() {
                Context context = softReference.get();
                String message = String.format("666 Report an issue - [%s]. context - [%s]", issue.getTag(),context);
                LogUtils.e(TAG, message);
                if (context != null) {
                    Toast.makeText(context, message, Toast.LENGTH_LONG).show();
                }
            }
        });*/
    }

    companion object {
        const val TAG: String = "MatrixPluginListener"
    }
}
