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
package com.ryeeeeee.doubansdk4android.api.auth.oauth;

import android.content.Context;
import android.content.Intent;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.ryeeeeee.doubansdk4android.Douban;
import com.ryeeeeee.doubansdk4android.api.ErrorResponse;
import com.ryeeeeee.doubansdk4android.api.auth.AuthListener;
import com.ryeeeeee.doubansdk4android.exception.RequestException;
import com.ryeeeeee.doubansdk4android.net.HttpHelper;
import com.ryeeeeee.doubansdk4android.util.JsonUtil;
import com.ryeeeeee.doubansdk4android.util.LogUtil;
import com.ryeeeeee.doubansdk4android.util.PreferenceUtil;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.ParseException;
import org.json.JSONObject;

import java.util.List;

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
    public final static String USER_NAME_KEY = "user_name";
    public final static String SCOPE_KEY = "scope";

    private final static String sOAuthUrl = "https://www.douban.com/service/auth2/auth";
    private final static String sAccessTokenUrl = "https://www.douban.com/service/auth2/token";
    private static AuthListener sAuthListener;

    /**
     * 认证
     * @param context
     * @param listener
     */
    public static void authorize(Context context, AuthListener listener) {
        authorize(context, null, listener);
    }

    /**
     * 认证，首先先判断本地是否有缓存的认证信息
     * 如果存在并且没有过期，则复用之前的 access token
     * 如果存在但是过期了，则获取 fresh token 换取 access token
     * 如果不存在缓存的信息，则重新进行 OAuth 认证
     * @param context
     * @param scope
     * @param listener
     */
    public static void authorize(Context context, String scope, AuthListener listener) {
        sAuthListener = listener;

        // 检查本地 access token 是否有包含此权限
        String localScope = PreferenceUtil.getString(Douban.getContext(), OAuth.SCOPE_KEY);
        List<String> localScopeList = Scope.convertScopeString2List(localScope);
        List<String> scopeList = Scope.convertScopeString2List(scope);

        if (localScopeList != null && localScopeList.containsAll(scopeList)) {

            // 检查 access token 是否在有效期内
            long expires_time = PreferenceUtil.getLong(Douban.getContext(), OAuth.EXPIRES_TIME_KEY);
            if (expires_time != -1) {

                if (expires_time > System.currentTimeMillis()) {

                    String userId = PreferenceUtil.getString(Douban.getContext(), OAuth.USER_ID_KEY);
                    String userName = PreferenceUtil.getString(Douban.getContext(), OAuth.USER_NAME_KEY);
                    listener.onAuthSuccess(userId, userName);

                } else {

                    String refreshToken = PreferenceUtil.getString(Douban.getContext(), OAuth.REFRESH_TOKEN_KEY);
                    OAuth.refreshAccessToken(refreshToken, listener);

                }

                return;
            }
        }

        PreferenceUtil.setString(Douban.getContext(), SCOPE_KEY, scope);

        StringBuilder urlStringBuilder = new StringBuilder();
        urlStringBuilder.append(sOAuthUrl).append("?");
        urlStringBuilder.append(HttpParam.CLIENT_ID_KEY).append("=").append(Douban.getApiKey());
        urlStringBuilder.append("&").append(HttpParam.REDIRECT_URI_KEY).append("=").append(Douban.getRedirectURI());
        urlStringBuilder.append("&").append(HttpParam.RESPONSE_TYPE_KEY).append("=").append("code");
        if(scope != null && !scope.trim().equals("")) {
            urlStringBuilder.append("&").append(HttpParam.SCOPE_KEY).append("=").append(scope);
        }

        Intent intent = new Intent(context, AuthActivity.class);
        intent.putExtra(AuthActivity.OAUTH_URL, urlStringBuilder.toString());

        context.startActivity(intent);
    }


    /**
     * 使用 authorization code 换取 access token
     * @param authCode
     * @param listener
     */
    public static void exchangeAccessToken(String authCode, final AuthListener listener) {

        LogUtil.d(TAG, "auth code:" + authCode);

        RequestParams params = new RequestParams();
        params.put(HttpParam.CLIENT_ID_KEY, Douban.getApiKey());
        params.put(HttpParam.REDIRECT_URI_KEY, Douban.getRedirectURI());
        params.put(HttpParam.CLIENT_SECRET_KEY, Douban.getSecret());
        params.put(HttpParam.GRANT_TYPE_KEY, HttpParam.GRANT_TYPE_VALUE_CODE);
        params.put(HttpParam.CODE, authCode);

        HttpHelper.post(sAccessTokenUrl, params, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject jsonResponse) {
                LogUtil.d(TAG, jsonResponse.toString());
                AccessTokenResponse response = JsonUtil.fromJson(jsonResponse.toString(), AccessTokenResponse.class);
                updateLocalAccessTokenInfo(response);
                listener.onAuthSuccess(response.getDouban_user_id(), response.getDouban_user_name());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                LogUtil.d(TAG + "onFailure", "status Code: " + statusCode);
                ErrorResponse response = JsonUtil.fromJson(errorResponse.toString(), ErrorResponse.class);
                PreferenceUtil.clearAllPreferences(Douban.getContext());
                listener.onAuthFailure(new RequestException(response));
            }

        });
    }

    /**
     * 使用 refresh access token 换取 access token
     * @param refreshToken 同 access token 一并获取到的 refresh token
     * @param listener 认证回调
     */
    public static void refreshAccessToken(final String refreshToken, final AuthListener listener) {

        RequestParams params = new RequestParams();
        params.put(HttpParam.CLIENT_ID_KEY, Douban.getApiKey());
        params.put(HttpParam.CLIENT_SECRET_KEY, Douban.getSecret());
        params.put(HttpParam.REDIRECT_URI_KEY, Douban.getRedirectURI());
        params.put(HttpParam.GRANT_TYPE_KEY, HttpParam.GRANT_TYPE_VALUE_REFRESH_TOKEN);
        params.put(HttpParam.REFRESH_TOKEN_KEY, refreshToken);

        HttpHelper.post(sAccessTokenUrl, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject jsonResponse) {
                AccessTokenResponse response = JsonUtil.fromJson(jsonResponse.toString(), AccessTokenResponse.class);
                updateLocalAccessTokenInfo(response);
                listener.onAuthSuccess(response.getDouban_user_id(), response.getDouban_user_name());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                LogUtil.d(OAuth.TAG, errorResponse.toString());
                ErrorResponse response = JsonUtil.fromJson(errorResponse.toString(), ErrorResponse.class);
                PreferenceUtil.clearAllPreferences(Douban.getContext());
                listener.onAuthFailure(new RequestException(response));
            }

        });
    }

    /**
     * 获得验证信息的请求头
     * @return
     */
    public static Header getTokenHeader() {
        return new Header() {
            @Override
            public String getName() {
                return "Authorization";
            }

            @Override
            public String getValue() {
                return "Bearer " + PreferenceUtil.getString(Douban.getContext(), OAuth.ACCESS_TOKEN_KEY);
            }

            @Override
            public HeaderElement[] getElements() throws ParseException {
                return new HeaderElement[0];
            }
        };
    }

    public static AuthListener getAuthListener() {
        return sAuthListener;
    }

    public static void setAuthListener(AuthListener authListener) {
        sAuthListener = authListener;
    }

    /**
     * 更新本地的缓存的 access token 信息
     * @param response 认证成功后返回的 access token 信息
     */
    private static void updateLocalAccessTokenInfo(AccessTokenResponse response) {
        // TODO 添加scope的认证
        PreferenceUtil.setString(Douban.getContext(), OAuth.ACCESS_TOKEN_KEY, response.getAccess_token());
        PreferenceUtil.setString(Douban.getContext(), OAuth.REFRESH_TOKEN_KEY, response.getRefresh_token());
        PreferenceUtil.setString(Douban.getContext(), OAuth.USER_ID_KEY, response.getDouban_user_id());
        PreferenceUtil.setString(Douban.getContext(), OAuth.USER_NAME_KEY, response.getDouban_user_name());
        PreferenceUtil.setLong(Douban.getContext(), OAuth.EXPIRES_TIME_KEY,
                response.getExpires_in() * 1000 + System.currentTimeMillis());
    }

    /**
     * 清除本地缓存的 access token 信息
     */
    private static void clearLocalAccessTokenInfo() {

    }

}
