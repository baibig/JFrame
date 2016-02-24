package com.huangjian.jframe.utils.jlog;

/**
 * Desction:日志工厂类
 * Author:huangjian
 * Date:2016/1/29 0029 17:30
 */
public class JLogFactory {

    public static JLogPrinter getPrinter(String tag, boolean debug) {
        final JLogPrinter printer = new JLogPrinter();
        printer.init(tag);
        JLogLevel level = JLogLevel.NONE;
        if (debug) {
            level = JLogLevel.FULL;
        }
        printer.getSettings().methodCount(3).logLevel(level);

        return printer;
    }

}
