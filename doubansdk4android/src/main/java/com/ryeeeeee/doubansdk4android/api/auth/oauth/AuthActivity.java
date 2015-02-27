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
package com.ryeeeeee.doubansdk4android.api.auth.oauth;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;

import com.ryeeeeee.doubansdk4android.api.auth.AuthListener;
import com.ryeeeeee.doubansdk4android.exception.DoubanException;
import com.ryeeeeee.doubansdk4android.exception.RequestException;

/**
 * @author Ryeeeeee
 * @since 2015-01-24
 */
public class AuthActivity extends Activity implements AuthListener {

    private final static String TAG = "AuthActivity";

    public final static String OAUTH_URL = "extra_oauth_url";

    private AuthWebView mAuthWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuthWebView = new AuthWebView(this, this);
        this.setContentView(mAuthWebView);

        mAuthWebView.loadUrl(getIntent().getStringExtra(OAUTH_URL));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 当用户点击返回按键时，关闭授权页面，并回调取消认证
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            OAuth.getAuthListener().onCancel();
            this.finish();
        }
        return true;
    }

    @Override
    public void onAuthSuccess(String userId, String userName) {
        OAuth.getAuthListener().onAuthSuccess(userId, userName);
        onFinish();
    }

    @Override
    public void onAuthFailure(RequestException exception) {
        OAuth.getAuthListener().onAuthFailure(exception);
        onFinish();
    }

    @Override
    public void onError(DoubanException exception) {
        OAuth.getAuthListener().onError(exception);
        onFinish();
    }

    @Override
    public void onCancel() {
        OAuth.getAuthListener().onCancel();
        onFinish();
    }

    @Override
    public void onFinish() {
        this.finish();
        OAuth.getAuthListener().onFinish();
    }
}
