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

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.ryeeeeee.doubansdk4android.Douban;
import com.ryeeeeee.doubansdk4android.auth.IAuthListener;
import com.ryeeeeee.doubansdk4android.constant.HttpParam;
import com.ryeeeeee.doubansdk4android.exception.DoubanException;
import com.ryeeeeee.doubansdk4android.util.JsonUtil;
import com.ryeeeeee.doubansdk4android.util.LogUtil;

/**
 * @author Ryeeeeee
 * @since 2015-01-24
 */
@SuppressLint("ViewConstructor")
public class AuthWebView extends RelativeLayout {
    // TODO 将 ProgressBar 替换成自定义进度条

    private final static String TAG = "AuthWebView";
    /** */
    private Context mContext;
    /** */
    private ProgressBar mProgressBar;
    /** */
    private WebView mWebView;
    /** */
    private IAuthListener mAuthListener;

    public AuthWebView(Context context, IAuthListener listener) {
        super(context);

        init(context, listener);
    }

    public void loadUrl(String url) {
        mWebView.loadUrl(url);
    }

    @SuppressLint({"SetJavaScriptEnabled", "AddJavascriptInterface"})
    private void init(Context context, IAuthListener listener) {

        this.mContext = context;
        this.mAuthListener = listener;

        // 为了能最终做成 jar 的形式，尽量使用 java 代码而不是使用 layout 实现界面
        mProgressBar = new ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal);
        LayoutParams progressBarParam = new LayoutParams(LayoutParams.MATCH_PARENT, 40);
        progressBarParam.addRule(RelativeLayout.ALIGN_PARENT_TOP);

        mWebView = new WebView(context);
        mWebView.setWebChromeClient(new AuthWebChromeClient());
        mWebView.setWebViewClient(new AuthWebViewClient());
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.addJavascriptInterface(new JavascriptHandler(), "handler");
        LayoutParams webViewParam = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        webViewParam.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);

        this.addView(mWebView, webViewParam);
        this.addView(mProgressBar, progressBarParam);
    }

    /**
     * 用于处理 WebView 中界面相关操作的回调
     */
    private class AuthWebChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            mProgressBar.setProgress(newProgress);
            if (newProgress >= mProgressBar.getMax()) {
                mProgressBar.setVisibility(View.GONE);
            }
        }
    }

    /**
     * 用于操作 WebView 加载逻辑相关的操作
     */
    private class AuthWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            // 拦截跳转值回调接口的 URL
            if (url.startsWith(Douban.getRedirectURI())) {

                String httpParams = url.split("\\?")[1];
                String[] bundles = httpParams.split("=");

                if (bundles[0].equals(HttpParam.CODE)) {
                    // 成功认证，获取 authorization code
                    String code = bundles[1];
                    LogUtil.i(TAG, "authorization code:" + code);
                    OAuth.exchangeAccessToken(code, mAuthListener);

                } else if (bundles[0].equals(HttpParam.ERROR)) {
                    // 用户拒绝认证
                    String error = bundles[1];
                    LogUtil.i(TAG, "authorization error:" + error);
                    mAuthListener.onCancel();
                }
                mWebView.stopLoading();
            }

            // return false means the current WebView handles the url.
            // return true means the host application handles the url.
            return false;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            LogUtil.d(TAG, "onPageFinished : " + url);
            // 拦截认证 URL 出错，返回的 JSON 数据
            view.loadUrl("javascript:window.handler.getContent(document.getElementsByTagName('pre')[0].innerHTML);");
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            LogUtil.d(TAG, "onReceivedError :" + "errorCode:" + errorCode + "description:" + description + "failingUrl" + failingUrl);
            mAuthListener.onError(new DoubanException(description));
        }
    }

    /**
     * 用于获取 WebView 中加在的页面内容
     */
    private final class JavascriptHandler {

        /**
         * javascript 调用，返回页面的内容
         * @param htmlContent
         */
        @JavascriptInterface
        public void getContent(String htmlContent){
            LogUtil.i(TAG, "html content:" + htmlContent);

            if (!htmlContent.equals("Undefined")) {
                ErrorResponse errorResponse = JsonUtil.fromJson(htmlContent, ErrorResponse.class);
                mAuthListener.onAuthFailure(errorResponse);
            }
        }

    }

}
