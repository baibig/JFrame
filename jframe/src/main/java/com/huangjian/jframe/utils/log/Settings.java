package com.huangjian.jframe.utils.log;

public final class Settings {

    private int methodCount = 2;
    private boolean showThreadInfo = true;
    private int methodOffset = 0;
    private LogTool logTool;

    /**
     * Determines how logs will printed
     */
    private JLogLevel logLevel = JLogLevel.FULL;

    public Settings hideThreadInfo() {
        showThreadInfo = false;
        return this;
    }

    /**
     * Use {@link #methodCount}
     */
    @Deprecated
    public Settings setMethodCount(int methodCount) {
        return methodCount(methodCount);
    }

    public Settings methodCount(int methodCount) {
        if (methodCount < 0) {
            methodCount = 0;
        }
        this.methodCount = methodCount;
        return this;
    }

    /**
     * Use {@link #logLevel}
     */
    @Deprecated
    public Settings setLogLevel(JLogLevel logLevel) {
        return logLevel(logLevel);
    }

    public Settings logLevel(JLogLevel logLevel) {
        this.logLevel = logLevel;
        return this;
    }

    /**
     * Use {@link #methodOffset}
     */
    @Deprecated
    public Settings setMethodOffset(int offset) {
        return methodOffset(offset);
    }

    public Settings methodOffset(int offset) {
        this.methodOffset = offset;
        return this;
    }

    public Settings logTool(LogTool logTool) {
        this.logTool = logTool;
        return this;
    }

    public int getMethodCount() {
        return methodCount;
    }

    public boolean isShowThreadInfo() {
        return showThreadInfo;
    }

    public JLogLevel getLogLevel() {
        return logLevel;
    }

    public int getMethodOffset() {
        return methodOffset;
    }

    public LogTool getLogTool() {
        if (logTool == null) {
            logTool = new AndroidLogTool();
        }
        return logTool;
    }
}
