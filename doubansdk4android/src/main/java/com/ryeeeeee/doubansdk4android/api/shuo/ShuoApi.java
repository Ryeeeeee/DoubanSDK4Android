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
package com.ryeeeeee.doubansdk4android.api.shuo;

import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.ryeeeeee.doubansdk4android.Douban;
import com.ryeeeeee.doubansdk4android.api.auth.oauth.OAuth;
import com.ryeeeeee.doubansdk4android.net.HttpHelper;
import com.ryeeeeee.doubansdk4android.util.JsonUtil;
import com.ryeeeeee.doubansdk4android.util.LogUtil;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

/**
 * @author Ryeeeeee
 * @since 2015-02-20
 */
public class ShuoApi {

    private final static String TAG = "ShuoApi";

    private final static String SHUO_API_BASE_URL = "https://api.douban.com/shuo/v2/statuses/";

    private final static String SINCE_ID_KEY = "since_id";
    private final static String UNTIL_ID_KEY = "until_id";
    private final static String COUNT_KEY = "count";
    private final static String START_KEY = "start";

    /**
     *
     * @param sinceId 若指定此参数，则只返回ID比since_id大的广播消息（即比since_id发表时间晚的广播消息）
     * @param untilId 若指定此参数，则返回ID小于或等于until_id的广播消息
     * @param count 默认20，最大200
     * @param start 默认0
     */
    public static void getTimeline(long sinceId, long untilId, int count, int start, final ShuoListener listener) {
        String url = SHUO_API_BASE_URL + "home_timeline";

        RequestParams params = new RequestParams();
        if (sinceId != -1) {
            params.put(SINCE_ID_KEY, sinceId);
        }
        if (untilId != -1) {
            params.put(UNTIL_ID_KEY, untilId);
        }
        if (count != -1) {
            params.put(COUNT_KEY, count);
        }
        if (start != -1) {
            params.put(START_KEY, start);
        }

        Header[] headers = new Header[] {
                OAuth.getTokenHeader()
        };

        HttpHelper.get(Douban.getContext(), url, headers, params, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                List<Shuo> shuoList = JsonUtil.fromJson(response.toString(),
                        new TypeToken<List<Shuo>>() {}.getType());
                listener.onSuccess(shuoList);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                LogUtil.d(TAG, "error:" + errorResponse.toString());
            }

        });

    }
}
