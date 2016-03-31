package com.huangjian.jframe.utils.log;

import android.support.annotation.NonNull;

import com.huangjian.jframe.utils.log.util.ObjParser;
import com.huangjian.jframe.utils.log.util.XmlJsonParser;

import timber.log.Timber;

/**
 * Author: pierce
 * Date: 2016/3/31
 */
public class JLog {

    private static LogPrinter printer;

    // @formatter:off
    @Deprecated protected JLog() {}
    // @formatter:on

    public static void initialize(Settings settings) {
        printer = new LogPrinter(settings);
        Timber.plant(printer);
    }

    public static Settings getSettings() {
        return printer.getSettings();
    }

    public static Timber.Tree tag(String tag) {
        return Timber.tag(tag);
    }

    public static void v(String message, Object... args) {
        message = handleNullMsg(message);
        Timber.v(message, args);
    }

    public static void d(String message, Object... args) {
        message = handleNullMsg(message);
        Timber.d(message, args);
    }

    public static void i(String message, Object... args) {
        message = handleNullMsg(message);
        Timber.i(message, args);
    }

    public static void w(String message, Object... args) {
        message = handleNullMsg(message);
        Timber.w(message, args);
    }

    public static void w(Throwable throwable, String message, Object... args) {
        message = handleNullMsg(message);
        Timber.w(throwable, message, args);
    }

    public static void e(String message, Object... args) {
        message = handleNullMsg(message);
        Timber.e(message, args);
    }

    public static void e(Throwable throwable, String message, Object... args) {
        message = handleNullMsg(message);
        Timber.e(throwable, message, args);
    }

    public static void wtf(String message, Object... args) {
        message = handleNullMsg(message);
        Timber.wtf(message, args);
    }

    public static void wtf(Throwable throwable, String message, Object... args) {
        message = handleNullMsg(message);
        Timber.wtf(throwable, message, args);
    }

    /**
     * Formats the json content and print it
     *
     * @param json the json content
     */
    public static void json(String json) {
        Timber.d(XmlJsonParser.json(json));
    }

    /**
     * Formats the json content and print it
     *
     * @param xml the xml content
     */
    public static void xml(String xml) {
        Timber.d(XmlJsonParser.xml(xml));
    }

    /**
     * Formats the json content and print it
     *
     * @param object Bean,Array,Collection,Map,Pojo and so on
     */
    public static void object(Object object) {
        Timber.d(ObjParser.parseObj(object));
    }

    public static void plant(Timber.Tree tree) {
        Timber.plant(tree);
    }

    public static void uprootAll() {
        Timber.uprootAll();
    }

    /**
     * Timber will swallow message if it's null and there's no throwable.
     */
    @NonNull
    private static String handleNullMsg(String message) {
        if (message == null) {
            message = "null";
        }
        return message;
    }

}
