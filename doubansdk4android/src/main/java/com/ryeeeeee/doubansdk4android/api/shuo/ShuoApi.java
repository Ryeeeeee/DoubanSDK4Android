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

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

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
import org.json.JSONException;
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
    private final static String PACK_KEY = "pack";

    /**
     *
     * @param sinceId 若指定此参数，则只返回ID比since_id大的广播消息（即比since_id发表时间晚的广播消息）
     * @param untilId 若指定此参数，则返回ID小于或等于until_id的广播消息
     * @param count 默认20，最大200
     * @param start 默认0
     */
    public static void getTimeline(long sinceId, long untilId, int count, int start, final ShuoListener<List<Shuo>> listener) {
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

    /**
     * 发送一条广播
     * image参数一定是我说带的图， 无法作为推荐时带的图， 推荐附图请使用rec_image参数
     * 有image的情况下rec系列参数都会被忽略。
     * @param source appkey[必选]
     * @param text 广播文本内容[必选]
     * @param drawable 我说的图[可选]
     * @param recTitle 推荐网址的标题[可选]
     * @param recUrl 推荐网址的href[可选]
     * @param recDesc 推荐网址的描述[可选]
     * @param recImage 推荐网址的附图url[可选]
     */
    public static void postShuo(String source, String text, Drawable drawable, String recTitle,
                                String recUrl, final String recDesc, String recImage) {

        String url = SHUO_API_BASE_URL;

        RequestParams params = new RequestParams();
        params.put("source", source);
        params.put("text", text);
        params.put("image", drawable);
        params.put("rec_title", recTitle);
        params.put("rec_url", recUrl);
        params.put("rec_desc", recDesc);
        params.put("rec_image", recImage);

        Header[] headers = new Header[] {
                OAuth.getTokenHeader()
        };

        HttpHelper.post(Douban.getContext(), url, headers, params, "multipart/form-data",new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                LogUtil.d(TAG, "postShuo:" + response.toString());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                LogUtil.d(TAG, "postShuo:" + errorResponse.toString());
            }

            @Override
            protected Object parseResponse(byte[] responseBody) throws JSONException {
                LogUtil.d(TAG, "postShuo:" + new String(responseBody));
                return super.parseResponse(responseBody);
            }
        });
    }

    /**
     * 读取一条广播
     * @param id
     * @param pack
     */
    public static void getOneShuo(int id, boolean pack) {
        String url = SHUO_API_BASE_URL + id;

        RequestParams params = new RequestParams();
        params.put(PACK_KEY, pack);

        Header[] headers = new Header[] {
                OAuth.getTokenHeader()
        };

        HttpHelper.get(Douban.getContext(), url, headers, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                LogUtil.d(TAG, "getOneShuo:" + response.toString());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                LogUtil.d(TAG, "getOneShuo:" + errorResponse.toString());
            }
        });
    }

    /**
     * 删除一条广播，只能删除自己的广播
     * @param id
     * @param pack
     */
    public static void deleteOneShuo(int id, boolean pack) {
        String url = SHUO_API_BASE_URL + id;

        RequestParams params = new RequestParams();
        params.put(PACK_KEY, pack);

        Header[] headers = new Header[] {
                OAuth.getTokenHeader()
        };

        HttpHelper.delete(Douban.getContext(), url, headers, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                LogUtil.d(TAG, "deleteOneShuo:" + response.toString());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                LogUtil.d(TAG, "deleteOneShuo:" + errorResponse.toString());
            }
        });
    }

    /**
     * 获取一条广播的回复列表
     * @param id
     * @param start
     * @param count
     */
    public static void getComments(int id, int start, int count) {
        String url = SHUO_API_BASE_URL + id + "/comments";

        RequestParams params = new RequestParams();
        params.put(START_KEY, start);
        params.put(COUNT_KEY, count);

        Header[] headers = new Header[] {
                OAuth.getTokenHeader()
        };

        HttpHelper.get(Douban.getContext(), url, headers, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                LogUtil.d(TAG, "getComments:" + response.toString());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                LogUtil.d(TAG, "getComments:" + errorResponse.toString());
            }
        });
    }

    /**
     * 添加一条评论
     * @param id
     */
    public static void postComment(int id) {
        String url = SHUO_API_BASE_URL + id + "/comments";

        RequestParams params = new RequestParams();

        Header[] headers = new Header[] {
                OAuth.getTokenHeader()
        };

        HttpHelper.post(Douban.getContext(), url, headers, params, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                LogUtil.d(TAG, "postComment:" + response.toString());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                LogUtil.d(TAG, "postComment:" + errorResponse.toString());
            }
        });
    }

    /**
     * 获取单条回复的内容
     * @param id
     */
    public static void getCommentContent(int id) {
        String url = SHUO_API_BASE_URL + "comment/" + id;

        Header[] headers = new Header[] {
                OAuth.getTokenHeader()
        };

        HttpHelper.get(Douban.getContext(), url, headers, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                LogUtil.d(TAG, "getCommentContent:" + response.toString());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                LogUtil.d(TAG, "getCommentContent:" + errorResponse.toString());
            }
        });
    }

    /**
     * 删除该回复,楼主、发帖人、管理员能删除
     * @param id
     */
    public static void deleteComment(int id) {
        String url = SHUO_API_BASE_URL + "comment/" + id;

        Header[] headers = new Header[] {
                OAuth.getTokenHeader()
        };

        HttpHelper.delete(Douban.getContext(), url, headers, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                LogUtil.d(TAG, "getCommentContent:" + response.toString());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                LogUtil.d(TAG, "getCommentContent:" + errorResponse.toString());
            }
        });
    }

    /**
     * 获取最近转播的用户列表
     */
    public static void reshare(int id) {
        String url = SHUO_API_BASE_URL + id + "/reshare";

        RequestParams params = new RequestParams();

        Header[] headers = new Header[] {
                OAuth.getTokenHeader()
        };

        HttpHelper.post(Douban.getContext(), url, headers, params, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                LogUtil.d(TAG, "reshare:" + response.toString());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                LogUtil.d(TAG, "reshare:" + errorResponse.toString());
            }
        });
    }

    /**
     * 获取最近转播的用户列表
     * @param shuoId
     */
    public static void getResharedUserList(int shuoId) {
        String url = SHUO_API_BASE_URL + shuoId + "/reshare";

        Header[] headers = new Header[] {
                OAuth.getTokenHeader()
        };

        HttpHelper.get(Douban.getContext(), url, headers, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                LogUtil.d(TAG, "getResharedUserList:" + response.toString());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                LogUtil.d(TAG, "getResharedUserList:" + errorResponse.toString());
            }
        });
    }

    /**
     * 赞
     * @param shuoId
     */
    public static void postLike(int shuoId) {
        String url = SHUO_API_BASE_URL + shuoId + "/like";

        RequestParams params = new RequestParams();

        Header[] headers = new Header[] {
                OAuth.getTokenHeader()
        };

        HttpHelper.post(Douban.getContext(), url, headers, params, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                LogUtil.d(TAG, "postLike:" + response.toString());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                LogUtil.d(TAG, "postLike:" + errorResponse.toString());
            }
        });
    }

    /**
     * 取消赞
     * @param shuoId
     */
    public static void deleteLike(int shuoId) {
        String url = SHUO_API_BASE_URL + shuoId + "/like";

        Header[] headers = new Header[] {
                OAuth.getTokenHeader()
        };

        HttpHelper.delete(Douban.getContext(), url, headers, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                LogUtil.d(TAG, "deleteLike:" + response.toString());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                LogUtil.d(TAG, "deleteLike:" + errorResponse.toString());
            }
        });
    }

    /**
     * 获取最近赞的用户列表
     * @param shuoId
     */
    public static void getLikeUserList(int shuoId) {
        String url = SHUO_API_BASE_URL + shuoId + "/like";

        Header[] headers = new Header[] {
                OAuth.getTokenHeader()
        };

        HttpHelper.get(Douban.getContext(), url, headers, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                LogUtil.d(TAG, "getLikeUserList:" + response.toString());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                LogUtil.d(TAG, "getLikeUserList:" + errorResponse.toString());
            }
        });

    }

}
