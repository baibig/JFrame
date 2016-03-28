package com.huangjian.jframe.utils;

/**自定义log，封装了Timber
 * Author: pierce
 * Date: 2016/3/28
 */
public class JLogger {

    public static void initial() {
        Timber.plant(new Timber.DebugTree());
    }

    /** Log a verbose message with optional format args. */
    public static void v(String message, Object... args) {
        Timber.v(message, args);
    }

    /** Log a verbose exception and a message with optional format args. */
    public static void v(Throwable t, String message, Object... args) {
        Timber.v(t, message, args);
    }

    /** Log a debug message with optional format args. */
    public static void d(String message, Object... args) {
        Timber.d(message, args);
    }

    /** Log a debug exception and a message with optional format args. */
    public static void d(Throwable t, String message, Object... args) {
        Timber.d(t, message, args);
    }

    /** Log an info message with optional format args. */
    public static void i(String message, Object... args) {
        Timber.i(message, args);
    }

    /** Log an info exception and a message with optional format args. */
    public static void i(Throwable t, String message, Object... args) {
        Timber.i(t, message, args);
    }

    /** Log a warning message with optional format args. */
    public static void w(String message, Object... args) {
        Timber.w(message, args);
    }

    /** Log a warning exception and a message with optional format args. */
    public static void w(Throwable t, String message, Object... args) {
        Timber.w(t, message, args);
    }

    /** Log an error message with optional format args. */
    public static void e(String message, Object... args) {
        Timber.e(message, args);
    }

    /** Log an error exception and a message with optional format args. */
    public static void e(Throwable t, String message, Object... args) {
        Timber.wtf(t, message, args);
    }

    /** Log an assert message with optional format args. */
    public static void wtf(String message, Object... args) {
        Timber.wtf(message, args);
    }

    /** Log an assert exception and a message with optional format args. */
    public static void wtf(Throwable t, String message, Object... args) {
        Timber.wtf(t, message, args);
    }

    /** Set a one-time tag for use on the next logging call. */
    public static Timber.Tree tag(String tag) {
        Timber.tag(tag);
        return Timber.asTree();
    }
}
