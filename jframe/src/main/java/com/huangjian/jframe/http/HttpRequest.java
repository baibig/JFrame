/*
 * Copyright (C) 2015 pengjianbo(pengjianbosoft@gmail.com), Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.huangjian.jframe.http;

import java.io.File;

import okhttp3.Call;
import com.huangjian.jframe.utils.StringUtils;

/**
 * Desction:http请求类
 * Author:pengjianbo
 * Date:15/9/22 下午10:17
 */
public class HttpRequest {

    /**
     * Get请求
     * @param url
     */
    public static void get(String url) {
        get(url, null, null);
    }

    public static void get(String url, RequestParams params) {
        get(url, params, null);
    }

    public static void get(String url, HttpRequestCallback callback) {
        get(url, null, callback);
    }

    public static void get(String url, RequestParams params, HttpRequestCallback callback) {
        executeRequest(Method.GET, url, params, callback);
    }

    /**
     * Post请求
     * @param url
     */
    public static void post(String url) {
        post(url, null, null);
    }

    public static void post(String url, RequestParams params) {
        post(url, params, null);
    }

    public static void post(String url, HttpRequestCallback callback) {
        post(url, null, callback);
    }

    public static void post(String url, RequestParams params, HttpRequestCallback callback) {
        executeRequest(Method.POST, url, params, callback);
    }

    /**
     * Put请求
     * @param url
     */
    public static void put(String url) {
        put(url, null, null);
    }

    public static void put(String url, RequestParams params) {
        put(url, params, null);
    }

    public static void put(String url, HttpRequestCallback callback) {
        put(url, null, callback);
    }

    public static void put(String url, RequestParams params, HttpRequestCallback callback) {
        executeRequest(Method.PUT, url, params, callback);
    }

    /**
     * delete请求
     * @param url
     */
    public static void delete(String url) {
        delete(url, null, null);
    }

    public static void delete(String url, RequestParams params) {
        delete(url, params, null);
    }

    public static void delete(String url, HttpRequestCallback callback) {
        delete(url, null, callback);
    }

    public static void delete(String url, RequestParams params, HttpRequestCallback callback) {
        executeRequest(Method.DELETE, url, params, callback);
    }

    /**
     * head请求
     * @param url
     */
    public static void head(String url) {
        head(url, null, null);
    }

    public static void head(String url, RequestParams params) {
        head(url, params, null);
    }

    public static void head(String url, HttpRequestCallback callback) {
        head(url, null, callback);
    }

    public static void head(String url, RequestParams params, HttpRequestCallback callback) {
        executeRequest(Method.HEAD, url, params, callback);
    }
    /**
     * patch请求
     * @param url
     */
    public static void patch(String url) {
        patch(url, null, null);
    }

    public static void patch(String url, RequestParams params) {
        patch(url, params, null);
    }

    public static void patch(String url, HttpRequestCallback callback) {
        patch(url, null, callback);
    }

    public static void patch(String url, RequestParams params, HttpRequestCallback callback) {
        executeRequest(Method.PATCH, url, params, callback);
    }

    /**
     * 下载文件
     * @param url
     * @param target 保存的文件
     */
    public static void download(String url, File target) {
        download(url, target, null);
    }

    public static void download(String url, File target, FileDownloadCallback callback) {
        if (!StringUtils.isEmpty(url) && target != null) {
            FileDownloadTask task = new FileDownloadTask(url, target, callback);
            task.execute();
        }
    }

    private static void executeRequest(Method method, String url, RequestParams params, HttpRequestCallback callback) {
        if (!StringUtils.isEmpty(url)) {
            HttpTask task = new HttpTask(method, url, params, callback);
            task.execute();
        }
    }

    /**
     * 取消请求
     * @param url
     */
    public static void cancel(String url) {
        if ( !StringUtils.isEmpty(url) ) {
            Call call = OkHttpCallManager.getInstance().getCall(url);
            if ( call != null ) {
                call.cancel();
            }

            OkHttpCallManager.getInstance().removeCall(url);
        }
    }

}
