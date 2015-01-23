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

/**
 * @author Ryeeeeee
 * @since 2015-01-23
 */
public class Douban {

    /** */
    private static String sApiKey = "";
    /** */
    private static String sSecret = "";
    /** */
    private static String sRedirectURI = "";
    /** */
    private static String sScope = "";
    /** */
    private static Context sContext = null;
    /** */
    private static boolean sIsInited = false;

    /**
     *
     * @param context
     * @param apiKey
     * @param secret
     * @param redirectURI
     * @return
     */
    public static boolean init(Context context, String apiKey, String secret, String redirectURI) {
        return init(context, apiKey, secret, "");
    }

    /**
     *
     * @param apiKey
     * @param secret
     * @param redirectURI
     * @return
     */
    public static boolean init(Context context, String apiKey, String secret, String redirectURI,
                               String scope) {
        sContext = context;
        sApiKey = apiKey;
        sSecret = secret;
        sRedirectURI = redirectURI;
        sScope = scope;

        sIsInited = true;
        return true;
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
