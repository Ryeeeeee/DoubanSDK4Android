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
package com.ryeeeeee.doubansdk4android.auth.oauth;

import android.app.Activity;
import android.os.Bundle;

import com.ryeeeeee.doubansdk4android.Douban;
import com.ryeeeeee.doubansdk4android.auth.IAuthListener;
import com.ryeeeeee.doubansdk4android.constant.HttpParam;
import com.ryeeeeee.doubansdk4android.exception.DoubanException;
import com.ryeeeeee.doubansdk4android.util.LogUtil;

/**
 * @author Ryeeeeee
 * @since 2015-01-24
 */
public class AuthActivity extends Activity {

    private final static String TAG = "AuthActivity";

    private AuthWebView mAuthWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuthWebView = new AuthWebView(this, new IAuthListener() {
            @Override
            public void onComplete(AccessTokenResponse bundle) {
                LogUtil.i(TAG, "onComplete");
            }

            @Override
            public void onError(DoubanException exception) {
                LogUtil.i(TAG, "onError");
            }

            @Override
            public void onCancel() {
                LogUtil.i(TAG, "onCancel");
            }

            @Override
            public void onFinish() {
                LogUtil.i(TAG, "onFinish");
            }
        });

        this.setContentView(mAuthWebView);


        StringBuilder urlStringBuilder = new StringBuilder("https://www.douban.com/service/auth2/auth?");
        urlStringBuilder.append(HttpParam.CLIENT_ID).append("=").append(Douban.getApiKey());
        urlStringBuilder.append("&").append(HttpParam.REDIRECT_URI).append("=").append(Douban.getRedirectURI());
        urlStringBuilder.append("&").append(HttpParam.RESPONSE_TYPE).append("=").append("code");
        mAuthWebView.loadUrl(urlStringBuilder.toString());
        //mAuthWebView.loadUrl("http://Ryeeeeee.com");
    }
}
