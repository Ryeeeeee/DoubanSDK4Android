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
public class AuthActivity extends Activity implements IAuthListener{

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
    public void onAuthSuccess(AccessTokenResponse response) {
        OAuth.getIAuthListener().onAuthSuccess(response);
        onFinish();
    }

    @Override
    public void onAuthFailure(ErrorResponse response) {
        OAuth.getIAuthListener().onAuthFailure(response);
        onFinish();
    }

    @Override
    public void onError(DoubanException exception) {
        OAuth.getIAuthListener().onError(exception);
        onFinish();
    }

    @Override
    public void onCancel() {
        OAuth.getIAuthListener().onCancel();
        onFinish();
    }

    @Override
    public void onFinish() {
        this.finish();
        OAuth.getIAuthListener().onFinish();
    }
}
