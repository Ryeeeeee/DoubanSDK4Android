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

import android.content.Context;
import android.content.Intent;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.ryeeeeee.doubansdk4android.Douban;
import com.ryeeeeee.doubansdk4android.auth.IAuthListener;
import com.ryeeeeee.doubansdk4android.constant.HttpParam;
import com.ryeeeeee.doubansdk4android.net.HttpHelper;
import com.ryeeeeee.doubansdk4android.util.JsonUtil;
import com.ryeeeeee.doubansdk4android.util.LogUtil;
import com.ryeeeeee.doubansdk4android.util.PreferenceUtil;

import org.apache.http.Header;

/**
 * @author Ryeeeeee
 * @since 2015-01-23
 */
public class OAuth {

    private final static String TAG = "OAuth";

    public final static String ACCESS_TOKEN_KEY = "access_token";
    public final static String EXPIRES_TIME_KEY = "expires_time";
    public final static String REFRESH_TOKEN_KEY = "refresh_token";
    public final static String USER_ID_KEY = "user_id";

    private final static String sOAuthUrl = "https://www.douban.com/service/auth2/auth";
    private final static String sAccessTokenUrl = "https://www.douban.com/service/auth2/token";
    private static IAuthListener sIAuthListener;

    /**
     * 认证
     * @param context
     * @param listener
     */
    public static void authorize(Context context, IAuthListener listener) {

        sIAuthListener = listener;

        StringBuilder urlStringBuilder = new StringBuilder();
        urlStringBuilder.append(sOAuthUrl).append("?");
        urlStringBuilder.append(HttpParam.CLIENT_ID_KEY).append("=").append(Douban.getApiKey());
        urlStringBuilder.append("&").append(HttpParam.REDIRECT_URI_KEY).append("=").append(Douban.getRedirectURI());
        urlStringBuilder.append("&").append(HttpParam.RESPONSE_TYPE_KEY).append("=").append("code");

        Intent intent = new Intent(context, AuthActivity.class);
        intent.putExtra(AuthActivity.OAUTH_URL, urlStringBuilder.toString());

        context.startActivity(intent);

    }

    /**
     * 使用 authorization code 换取 access token
     * @param authCode
     * @param listener
     */
    public static void exchangeAccessToken(String authCode, final IAuthListener listener) {

        RequestParams params = new RequestParams();
        params.put(HttpParam.CLIENT_ID_KEY, Douban.getApiKey());
        params.put(HttpParam.REDIRECT_URI_KEY, Douban.getRedirectURI());
        params.put(HttpParam.CLIENT_SECRET_KEY, Douban.getSecret());
        params.put(HttpParam.GRANT_TYPE_KEY, HttpParam.GRANT_TYPE_VALUE_CODE);
        params.put(HttpParam.CODE, authCode);

        HttpHelper.post(sAccessTokenUrl, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {

                AccessTokenResponse response = JsonUtil.fromJson(responseString, AccessTokenResponse.class);
                updateLocalAccessTokenInfo(response);

                listener.onAuthSuccess(response.getUserID());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                // TODO 回调错误
            }
        });
    }

    /**
     * 使用 refresh access token 换取 access token
     * @param refreshToken 同 access token 一并获取到的 refresh token
     * @param listener 认证回调
     */
    public static void refreshAccessToken(String refreshToken, final IAuthListener listener) {

        RequestParams params = new RequestParams();
        params.put(HttpParam.CLIENT_ID_KEY, Douban.getApiKey());
        params.put(HttpParam.CLIENT_SECRET_KEY, Douban.getSecret());
        params.put(HttpParam.REDIRECT_URI_KEY, Douban.getRedirectURI());
        params.put(HttpParam.GRANT_TYPE_KEY, HttpParam.GRANT_TYPE_VALUE_REFRESH_TOKEN);
        params.put(HttpParam.REFRESH_TOKEN_KEY, refreshToken);

        HttpHelper.post(sAccessTokenUrl, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                LogUtil.d(OAuth.TAG, responseString);

                AccessTokenResponse response = JsonUtil.fromJson(responseString, AccessTokenResponse.class);
                updateLocalAccessTokenInfo(response);

                listener.onAuthSuccess(response.getUserID());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                LogUtil.d(OAuth.TAG, responseString);
                // TODO 回调错误
            }
        });
    }

    public static IAuthListener getIAuthListener() {
        return sIAuthListener;
    }

    public static void setIAuthListener(IAuthListener IAuthListener) {
        sIAuthListener = IAuthListener;
    }

    /**
     * 更新本地的缓存的 access token 信息
     * @param response 认证成功后返回的 access token 信息
     */
    private static void updateLocalAccessTokenInfo(AccessTokenResponse response) {
        PreferenceUtil.setString(Douban.getContext(), OAuth.ACCESS_TOKEN_KEY, response.getAccessToken());
        PreferenceUtil.setString(Douban.getContext(), OAuth.REFRESH_TOKEN_KEY, response.getRefreshToken());
        PreferenceUtil.setString(Douban.getContext(), OAuth.USER_ID_KEY, response.getUserID());
        PreferenceUtil.setLong(Douban.getContext(), OAuth.EXPIRES_TIME_KEY,
                response.getExpiresIn() * 1000 + System.currentTimeMillis());
    }
}
