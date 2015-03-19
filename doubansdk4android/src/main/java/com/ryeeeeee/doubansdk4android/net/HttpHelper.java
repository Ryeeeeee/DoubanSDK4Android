/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015. Ryeeeeee
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.ryeeeeee.doubansdk4android.net;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.ResponseHandler;

/**
 * @author Ryeeeeee
 * @since 2015-01-24
 */
public class HttpHelper {

    private static AsyncHttpClient sAsyncHttpClient = new AsyncHttpClient();

    private static final int DEFAULT_TIME_OUT = 1000 * 10;

    static {
        sAsyncHttpClient.setTimeout(DEFAULT_TIME_OUT);
    }

    public static void get(String url, AsyncHttpResponseHandler responseHandler) {
        sAsyncHttpClient.get(url, responseHandler);
    }

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        sAsyncHttpClient.get(url, params, responseHandler);
    }

    public static void get(Context context, String url, AsyncHttpResponseHandler responseHandler) {
        sAsyncHttpClient.get(context, url, responseHandler);
    }

    public static void get(Context context, String url, RequestParams params,
                           AsyncHttpResponseHandler responseHandler) {
        sAsyncHttpClient.get(context, url, params, responseHandler);
    }

    public static void get(Context context, String url, Header[] headers,
                           RequestParams params, AsyncHttpResponseHandler responseHandler) {
        sAsyncHttpClient.get(context, url, headers, params, responseHandler);
    }

    public static void post(String url, RequestParams params,
                            AsyncHttpResponseHandler responseHandler) {
        sAsyncHttpClient.post(url, params, responseHandler);
    }

    public static void post(Context context, String url, RequestParams params,
                            AsyncHttpResponseHandler responseHandler) {
        sAsyncHttpClient.post(context, url, params, responseHandler);
    }

    public static void post(Context context, String url, HttpEntity entity, String contentType,
                            AsyncHttpResponseHandler responseHandler) {
        sAsyncHttpClient.post(context, url, entity, contentType, responseHandler);
    }

    public static void post(Context context, String url, Header[] headers, HttpEntity entity,
                            String contentType, AsyncHttpResponseHandler responseHandler) {
        sAsyncHttpClient.post(context, url, headers, entity, contentType, responseHandler);
    }

    public static void post(Context context, String url, Header[] headers, RequestParams params,
                            String contentType, AsyncHttpResponseHandler responseHandler) {
        sAsyncHttpClient.post(context, url, headers, params, contentType, responseHandler);
    }

    public static void delete(String url, AsyncHttpResponseHandler responseHandler) {
        sAsyncHttpClient.delete(url, responseHandler);
    }

    public static void delete(Context context, String url, AsyncHttpResponseHandler responseHandler) {
        sAsyncHttpClient.delete(context, url, responseHandler);
    }

    public static void delete(Context context, String url, Header[] headers, AsyncHttpResponseHandler responseHandler) {
        sAsyncHttpClient.delete(context, url, headers, responseHandler);
    }

    public static void delete(Context context, String url, Header[] headers, RequestParams params,
                              AsyncHttpResponseHandler responseHandler) {
        sAsyncHttpClient.delete(context, url, headers, params, responseHandler);
    }
}
