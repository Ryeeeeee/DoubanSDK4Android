/*
 * The MIT License (MIT)
 * 
 * Copyright (c) 2015 Ryeeeeee
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
package com.ryeeeeee.doubansdk4android;

import android.content.Context;

import com.ryeeeeee.doubansdk4android.auth.IAuthListener;
import com.ryeeeeee.doubansdk4android.auth.oauth.OAuth;
import com.ryeeeeee.doubansdk4android.util.LogUtil;

/**
 * @author Ryeeeeee
 * @since 2015-01-23
 */
public class Douban {

    private final static String TAG = "Douban";

    /** 应用的 API Key */
    private static String sApiKey;
    /** 应用的 Secret */
    private static String sSecret;
    /** 应用的回调接口 */
    private static String sRedirectURI;
    /** 应用的访问权限 */
    private static String sScope;
    /** 应用的上下文环境 */
    private static Context sContext;
    /** Douban SDK 是否初始化 */
    private static boolean sIsInited = false;

    /**
     * Douban SDK 初始化接口，在调用其他接口之前，必须确保初始化成功
     *
     * @param context
     * @param apiKey
     * @param secret
     * @param redirectURI
     * @return
     */
    public static boolean init(Context context, String apiKey, String secret, String redirectURI) {
        return init(context, apiKey, secret, redirectURI, "");
    }

    /**
     *  Douban SDK 初始化接口，在调用其他接口之前，必须确保初始化成功
     * @param apiKey
     * @param secret
     * @param redirectURI
     * @return
     */
    public static boolean init(Context context, String apiKey, String secret, String redirectURI,
                               String scope) {
        if (isIsInited()) {
            LogUtil.w(TAG, "Douban has already inited");
            return false;
        }

        sContext = context;
        sApiKey = apiKey;
        sSecret = secret;
        sRedirectURI = redirectURI;
        sScope = scope;

        sIsInited = true;
        return true;
    }

    public static void auth(IAuthListener listener){
        OAuth.auth(sContext, listener);
    }

    /**
     *
     * @return
     */
    public static boolean isIsInited() {
        return sIsInited;
    }

    /**
     *
     * @return
     */
    public static String getApiKey() {
        return sApiKey;
    }

    /**
     *
     * @return
     */
    public static String getSecret() {
        return sSecret;
    }

    /**
     *
     * @return
     */
    public static String getRedirectURI() {
        return sRedirectURI;
    }
}
