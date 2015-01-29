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
package com.ryeeeeee.doubansdk4android.auth.oauth;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.WindowManager;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.ryeeeeee.doubansdk4android.Douban;
import com.ryeeeeee.doubansdk4android.auth.IAuthListener;
import com.ryeeeeee.doubansdk4android.constant.HttpParam;
import com.ryeeeeee.doubansdk4android.net.HttpHelper;
import com.ryeeeeee.doubansdk4android.util.LogUtil;

import org.apache.http.Header;

/**
 * @author Ryeeeeee
 * @since 2015-01-23
 */
public class OAuth {

    private final static String sOAuthUrl = "https://www.douban.com/service/auth2/auth";
    private final static String sAccessTokenUrl = "https://www.douban.com/service/auth2/token";
    private static IAuthListener sIAuthListener;

    @Deprecated
    private static AuthWebView sAuthWebView;
    @Deprecated
    private static WindowManager sWindowManager;

    /**
     * 
     * @param context
     * @param listener
     */
    public static void authorize(Context context, IAuthListener listener) {

//        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
//        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
//        layoutParams.type = WindowManager.LayoutParams.TYPE_PHONE;
//        layoutParams.format = PixelFormat.RGBA_8888; // 透明
//        layoutParams.gravity = Gravity.CENTER;
//        layoutParams.x = 0;
//        layoutParams.y = 0;
//        layoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
//        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
//
//        sAuthWebView = new AuthWebView(context, listener);
//        windowManager.addView(sAuthWebView, layoutParams);
//
//        StringBuilder urlStringBuilder = new StringBuilder("https://www.douban.com/service/auth2/auth?");
//        urlStringBuilder.append(HttpParam.CLIENT_ID).append("=").append(Douban.getApiKey());
//        urlStringBuilder.append("").append(HttpParam.REDIRECT_URI).append("=").append(Douban.getRedirectURI());
//        urlStringBuilder.append("&").append(HttpParam.RESPONSE_TYPE).append("=").append("code");
//
//        sAuthWebView.loadUrl(urlStringBuilder.toString());
//        LogUtil.d("Ryeeeeee", "authrize end");

        sIAuthListener = listener;

        StringBuilder urlStringBuilder = new StringBuilder();
        urlStringBuilder.append(sOAuthUrl).append("?");
        urlStringBuilder.append(HttpParam.CLIENT_ID).append("=").append(Douban.getApiKey());
        urlStringBuilder.append("&").append(HttpParam.REDIRECT_URI).append("=").append(Douban.getRedirectURI());
        urlStringBuilder.append("&").append(HttpParam.RESPONSE_TYPE).append("=").append("code");

        Intent intent = new Intent(context, AuthActivity.class);
        intent.putExtra(AuthActivity.OAUTH_URL, urlStringBuilder.toString());

        context.startActivity(intent);

    }

    public static void exchangeAccessToken(String authCode, IAuthListener listener) {

        RequestParams params = new RequestParams();
        params.put(HttpParam.CLIENT_ID, Douban.getApiKey());
        params.put(HttpParam.REDIRECT_URI, Douban.getRedirectURI());
        params.put(HttpParam.CLIENT_SECRET, Douban.getSecret());
        // TODO 改成枚举
        params.put(HttpParam.GRANT_TYPE, "authorization_code");
        params.put(HttpParam.CODE, authCode);

        HttpHelper.post(sAccessTokenUrl, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {

            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {

            }
        });
    }

    @Deprecated
    private void removeAuthWebView() {
        if (sWindowManager != null && sAuthWebView != null) {
            sWindowManager.removeView(sAuthWebView);
        }
    }

    public static IAuthListener getIAuthListener() {
        return sIAuthListener;
    }

    public static void setIAuthListener(IAuthListener IAuthListener) {
        sIAuthListener = IAuthListener;
    }
}
