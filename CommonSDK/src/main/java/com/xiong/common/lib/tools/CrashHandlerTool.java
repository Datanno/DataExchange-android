package com.xiong.common.lib.tools;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
/**
 * Created by xionglh on 2017/6/14
 */
public class CrashHandlerTool implements Thread.UncaughtExceptionHandler {
    private static final String TAG = "CrashHandlerTool---->";
    private Thread.UncaughtExceptionHandler mDefaultUEH;

    public CrashHandlerTool() {
        this.mDefaultUEH = Thread.getDefaultUncaughtExceptionHandler();
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        final Writer result = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(result);
        StackTraceElement[] trace = ex.getStackTrace();
        StackTraceElement[] trace2 = new StackTraceElement[trace.length + 3];
        System.arraycopy(trace, 0, trace2, 0, trace.length);
        trace2[trace.length + 0] = new StackTraceElement("Android", "MODEL", android.os.Build.MODEL, -1);
        trace2[trace.length + 1] = new StackTraceElement("Android", "VERSION", android.os.Build.VERSION.RELEASE, -1);
        trace2[trace.length + 2] = new StackTraceElement("Android", "FINGERPRINT", android.os.Build.FINGERPRINT, -1);
        ex.setStackTrace(trace2);
        ex.printStackTrace(printWriter);
        String stacktrace = result.toString();
        printWriter.close();
        SystemLog.E(TAG, stacktrace);
        mDefaultUEH.uncaughtException(thread, ex);
    }

}