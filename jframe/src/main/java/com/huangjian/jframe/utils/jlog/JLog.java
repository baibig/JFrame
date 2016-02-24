package com.huangjian.jframe.utils.jlog;

import com.huangjian.jframe.BuildConfig;

/**
 * Description: JLog is a wrapper of {@link android.util.Log},but more pretty, simple and powerful
 * 即可以格式化显示,也可以格式化message
 * Usage: JLog.t(tag).d("abc%s %3d", "wert", 5);
 * Or JLog.d("abc%s %3d", "wert", 5);
 * Author: huangjian
 * Date: 16/2/24
 */
public final class JLog {

    public static final String DEFAULT_TAG = "JLog";
    private static boolean DEBUG;
    private static JLogPrinter printer;

    //no instance
    private JLog() {
        printer = JLogFactory.getPrinter(DEFAULT_TAG, DEBUG);
    }

    private static void createInstance(){
        if (printer == null){
            new JLog();
        }
    }

    public static void init(boolean debug) {
        DEBUG = debug;
    }
    public static void clear() {
        createInstance();
        printer.clear();
    }

    public static Settings getSettings() {
        createInstance();
        return printer.getSettings();
    }

    /**
     * 设置log的tag
     * @param tag
     * @return
     */
    public static Printer t(String tag) {
        createInstance();
        return printer.t(tag, printer.getSettings().getMethodCount());
    }

    /**
     * 设置方法调用栈深度,默认是3
     * @param methodCount
     * @return
     */
    public static Printer t(int methodCount) {
        createInstance();
        return printer.t(null, methodCount);
    }

    public static Printer t(String tag, int methodCount) {
        createInstance();
        return printer.t(tag, methodCount);
    }

    /**
     * Log.d 输出debug信息
     * @param message 要输出到终端的信息,可以格式化显示,如"abc%5d%s"
     * @param args 格式化参数
     */
    public static void d(String message, Object... args) {
        createInstance();
        printer.d(message, args);
    }

    /**
     * 同上
     * see{@link #d(String, Object...)}
     * @param throwable 抛出的异常信息
     */
    public static void e(Throwable throwable) {
        createInstance();
        printer.e(throwable);
    }

    public static void e(String message, Object... args) {
        createInstance();
        printer.e(null, message, args);
    }

    public static void e(Throwable throwable, String message, Object... args) {
        createInstance();
        printer.e(throwable, message, args);
    }

    public static void i(String message, Object... args) {
        createInstance();
        printer.i(message, args);
    }

    public static void v(String message, Object... args) {
        createInstance();
        printer.v(message, args);
    }

    public static void w(String message, Object... args) {
        createInstance();
        printer.w(message, args);
    }

    public static void wtf(String message, Object... args) {
        createInstance();
        printer.wtf(message, args);
    }

    /**
     * 格式化输出json数据
     *
     * @param json the json content
     */
    public static void json(String json) {
        createInstance();
        printer.json(json);
    }

    /**
     * 格式化输出xml数据
     *
     * @param xml the xml content
     */
    public static void xml(String xml) {
        createInstance();
        printer.xml(xml);
    }

}
