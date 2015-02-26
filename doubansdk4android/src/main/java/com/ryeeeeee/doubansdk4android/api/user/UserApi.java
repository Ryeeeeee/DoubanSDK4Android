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
package com.ryeeeeee.doubansdk4android.api.user;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.ryeeeeee.doubansdk4android.Douban;
import com.ryeeeeee.doubansdk4android.api.auth.oauth.OAuth;
import com.ryeeeeee.doubansdk4android.net.HttpHelper;
import com.ryeeeeee.doubansdk4android.util.JsonUtil;
import com.ryeeeeee.doubansdk4android.util.LogUtil;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Ryeeeeee
 * @since 2015-02-13
 */
public class UserApi {

    private final static String TAG = "UserApi";

    /** 用户 API base url */
    private final static String USER_API_BASE_URL = "https://api.douban.com/v2/user/";

    private final static String SEARCH_KEY = "q";
    private final static String START_KEY = "start";
    private final static String COUNT_KEY = "count";

    /**
     * 获得当前授权用户信息
     * @param listener
     */
    public static void getCurrentUserInfo(final IUserListener<UserInfo> listener) {

        String url = USER_API_BASE_URL + "~me";

        Header[] headers = new Header[] {
            OAuth.getTokenHeader()
        };

        HttpHelper.get(Douban.getContext(), url, headers, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                UserInfo userInfo = JsonUtil.fromJson(response.toString(), UserInfo.class);
                listener.onSuccess(userInfo);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }

            @Override
            protected Object parseResponse(byte[] responseBody) throws JSONException {
                LogUtil.d(TAG, new String(responseBody));
                return super.parseResponse(responseBody);
            }
        });

    }

    /**
     * 获得用户信息
     * @param userId 为 用户uid 或者 数字id
     * @param listener
     */
    public static void getUserInfo(String userId, final IUserListener<UserInfo> listener) {

        String url = USER_API_BASE_URL + userId;

        Header[] headers = new Header[] {
            OAuth.getTokenHeader()
        };

        HttpHelper.get(Douban.getContext(), url, headers, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                UserInfo userInfo = JsonUtil.fromJson(response.toString(), UserInfo.class);
                listener.onSuccess(userInfo);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });

    }

    /**
     * 搜索用户
     * @param content 全文检索的关键词
     * @param start 起始元素
     * @param count 返回结果的数量
     * @param listener
     */
    public static void searchUser(String content, int start , int count, final IUserListener<UserList> listener) {
        String url = USER_API_BASE_URL;

        RequestParams params = new RequestParams();
        params.put(SEARCH_KEY, content);
        params.put(START_KEY, start);
        params.put(COUNT_KEY, count);

        HttpHelper.get(url, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                UserList userList = JsonUtil.fromJson(response.toString(), UserList.class);
                listener.onSuccess(userList);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });

    }

}
