package com.huangjian.jframe.utils.http;

import java.util.concurrent.ConcurrentHashMap;

import okhttp3.Call;
import com.huangjian.jframe.utils.StringUtils;

/**
 * Desction:
 * Author:pengjianbo
 * Date:16/1/3 下午1:27
 */
public class OkHttpCallManager {

    private ConcurrentHashMap<String, Call> callMap;
    private static OkHttpCallManager manager;

    private OkHttpCallManager() {
        callMap = new ConcurrentHashMap<>();
    }

    public static OkHttpCallManager getInstance() {
        if (manager == null) {
            manager = new OkHttpCallManager();
        }
        return manager;
    }

    public void addCall(String url, Call call) {
        if (call != null && StringUtils.isEmpty(url)) {
            callMap.put(url, call);
        }
    }

    public Call getCall(String url) {
        if ( StringUtils.isEmpty(url) ) {
            return callMap.get(url);
        }

        return null;
    }

    public void removeCall(String url) {
        if ( StringUtils.isEmpty(url) ) {
            callMap.remove(url);
        }
    }

}
