package com.tw2416.gogolook.util;

import android.text.TextUtils;
import android.util.Log;

import com.tw2416.gogolook.BuildConfig;

/**
 * 统一打印日志的入口类
 */
public class Logger {
    public static final boolean ENABLE_LOG = BuildConfig.DEBUG;

    public static final String TAG = Logger.class.getSimpleName();

    private static final boolean DEBUG = BuildConfig.DEBUG;

    public static String customTagPrefix = "";

    public static boolean allowD = true && DEBUG;
    public static boolean allowE = true;
    public static boolean allowI = true && DEBUG;
    public static boolean allowV = true && DEBUG;
    public static boolean allowW = true && DEBUG;
    public static boolean allowWtf = true && DEBUG;


    /**
     * generate TAG.
     *
     * @param caller
     * @return
     */
    private static String generateTag(StackTraceElement caller) {
        String callerClazzName = caller.getClassName();
        callerClazzName = callerClazzName.substring(callerClazzName.lastIndexOf(".") + 1);
        callerClazzName = TextUtils.isEmpty(customTagPrefix) ? callerClazzName : customTagPrefix + ":" + callerClazzName;
        return callerClazzName;
    }

    private static String generateClassNavigationSuffix(StackTraceElement caller) {
        String suffix = " (%s.java:%d)";
        String callerClazzName = caller.getClassName();
        callerClazzName = callerClazzName.substring(callerClazzName.lastIndexOf(".") + 1);
        suffix = String.format(suffix, callerClazzName, caller.getLineNumber());
        return suffix;
    }

    public static void d(String content) {
        if (!allowD) return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        if (customLogger != null) {
            customLogger.d(tag, content + generateClassNavigationSuffix(caller));
        } else {
            Log.d(tag, content + generateClassNavigationSuffix(caller));
        }
    }

    public static void d(String content, Throwable tr) {
        if (!allowD) return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        if (customLogger != null) {
            customLogger.d(tag, content + generateClassNavigationSuffix(caller), tr);
        } else {
            Log.d(tag, content + generateClassNavigationSuffix(caller), tr);
        }
    }

    public static void e(String content) {
        if (!allowE) return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        if (customLogger != null) {
            customLogger.e(tag, content + generateClassNavigationSuffix(caller));
        } else {
            Log.e(tag, content + generateClassNavigationSuffix(caller));
        }
    }

    public static void e(String content, Throwable tr) {
        if (!allowE) return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        if (customLogger != null) {
            customLogger.e(tag,  content + generateClassNavigationSuffix(caller), tr);
        } else {
            Log.e(tag,  content + generateClassNavigationSuffix(caller), tr);
        }
    }

    public static void i(String content) {
        if (!allowI) return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        if (customLogger != null) {
            customLogger.i(tag,  content + generateClassNavigationSuffix(caller));
        } else {
            Log.i(tag,  content + generateClassNavigationSuffix(caller));
        }
    }

    public static void i(String content, Throwable tr) {
        if (!allowI) return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        if (customLogger != null) {
            customLogger.i(tag,  content + generateClassNavigationSuffix(caller), tr);
        } else {
            Log.i(tag,  content + generateClassNavigationSuffix(caller), tr);
        }
    }

    public static void v(String content) {
        if (!allowV) return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        if (customLogger != null) {
            customLogger.v(tag,  content + generateClassNavigationSuffix(caller));
        } else {
            Log.v(tag,  content + generateClassNavigationSuffix(caller));
        }
    }

    public static void v(String content, Throwable tr) {
        if (!allowV) return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        if (customLogger != null) {
            customLogger.v(tag,  content + generateClassNavigationSuffix(caller), tr);
        } else {
            Log.v(tag,  content + generateClassNavigationSuffix(caller), tr);
        }
    }

    public static void w(String content) {
        if (!allowW) return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        if (customLogger != null) {
            customLogger.w(tag,  content + generateClassNavigationSuffix(caller));
        } else {
            Log.w(tag,  content + generateClassNavigationSuffix(caller));
        }
    }

    public static void w(String content, Throwable tr) {
        if (!allowW) return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        if (customLogger != null) {
            customLogger.w(tag,  content + generateClassNavigationSuffix(caller), tr);
        } else {
            Log.w(tag,  content + generateClassNavigationSuffix(caller), tr);
        }
    }

    public static void w(Throwable tr) {
        if (!allowW) return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        if (customLogger != null) {
            customLogger.w(tag, tr);
        } else {
            Log.w(tag, tr);
        }
    }


    public static void wtf(String content) {
        if (!allowWtf) return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        if (customLogger != null) {
            customLogger.wtf(tag,  content + generateClassNavigationSuffix(caller));
        } else {
            Log.wtf(tag,  content + generateClassNavigationSuffix(caller));
        }
    }

    public static void wtf(String content, Throwable tr) {
        if (!allowWtf) return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        if (customLogger != null) {
            customLogger.wtf(tag,  content + generateClassNavigationSuffix(caller), tr);
        } else {
            Log.wtf(tag,  content + generateClassNavigationSuffix(caller), tr);
        }
    }

    public static void wtf(Throwable tr) {
        if (!allowWtf) return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        if (customLogger != null) {
            customLogger.wtf(tag, tr);
        } else {
            Log.wtf(tag, tr);
        }
    }


    public static StackTraceElement getCallerStackTraceElement() {
        return Thread.currentThread().getStackTrace()[4];
    }

    public static CustomLogger customLogger;

    // Custom Logger
    public interface CustomLogger {

        void d(String tag, String content);

        void d(String tag, String content, Throwable tr);

        void e(String tag, String content);

        void e(String tag, String content, Throwable tr);

        void i(String tag, String content);

        void i(String tag, String content, Throwable tr);

        void v(String tag, String content);

        void v(String tag, String content, Throwable tr);

        void w(String tag, String content);

        void w(String tag, String content, Throwable tr);

        void w(String tag, Throwable tr);

        void wtf(String tag, String content);

        void wtf(String tag, String content, Throwable tr);

        void wtf(String tag, Throwable tr);
    }

}
