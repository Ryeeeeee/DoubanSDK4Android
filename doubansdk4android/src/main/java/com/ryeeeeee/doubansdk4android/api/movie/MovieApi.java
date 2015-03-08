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
package com.ryeeeeee.doubansdk4android.api.movie;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.ryeeeeee.doubansdk4android.Douban;
import com.ryeeeeee.doubansdk4android.api.ErrorResponse;
import com.ryeeeeee.doubansdk4android.exception.RequestException;
import com.ryeeeeee.doubansdk4android.api.auth.oauth.OAuth;
import com.ryeeeeee.doubansdk4android.net.HttpHelper;
import com.ryeeeeee.doubansdk4android.util.JsonUtil;
import com.ryeeeeee.doubansdk4android.util.LogUtil;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Ryeeeeee
 * @since 2015-02-24
 */
public class MovieApi {

    private final static String TAG = "MovieApi";

    private final static String MOVIE_API_BASE_URL = "https://api.douban.com/v2/movie/";

    /**
     * 获得电影条目信息
     * @param id 电影条目ID
     */
    public static void getMovieSubject(int id, final MovieListener<Subject> listener) {
        getMovieSubject(String.valueOf(id), listener);
    }

    /**
     * 获得电影条目信息
     * @param id 电影条目ID
     */
    public static void getMovieSubject(String id, final MovieListener<Subject> listener) {
        String url = MOVIE_API_BASE_URL + "subject/" + id;

        Header[] headers = new Header[] {
                OAuth.getTokenHeader()
        };

        HttpHelper.get(Douban.getContext(), url, headers, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                LogUtil.d(TAG, "getMovieSubject: " + response.toString());
                Subject subject = JsonUtil.fromJson(response.toString(), Subject.class);
                listener.onSuccess(subject);
            }

            @Override
            protected Object parseResponse(byte[] responseBody) throws JSONException {
                LogUtil.d(TAG, "getMovieSubject: " + new String(responseBody));
                return super.parseResponse(responseBody);
            }
        });
    }

    /**
     * 获得影人条目信息
     * @param id
     */
    public static void getCelebrity(int id) {
        String url = MOVIE_API_BASE_URL + "celebrity/" + id;

        Header[] headers = new Header[] {
                OAuth.getTokenHeader()
        };

        HttpHelper.get(Douban.getContext(), url, headers, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                LogUtil.d(TAG, "getCelebrity: " + response.toString());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                LogUtil.d(TAG, "onfailure1:" + " " + statusCode + " " + throwable.toString() + " " + errorResponse.toString() );

            }

            @Override
            protected Object parseResponse(byte[] responseBody) throws JSONException {
                LogUtil.d(TAG, "getCelebrity: " + new String(responseBody));
                return super.parseResponse(responseBody);
            }
        });
    }

    /**
     * 获得电影条目剧照
     * @param id
     */
    public static void getSubjectPhotos(int id) {
        // TODO 检查权限 advance

        String url = MOVIE_API_BASE_URL + "subject/" + "/photos";

        HttpHelper.get(url, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                LogUtil.d(TAG, "getSubjectPhotos: " + response.toString());
            }
        });
    }

    /**
     * 获得电影条目影评列表
     * @param id
     */
    public static void getSubjectReviews(int id) {
        // TODO 检查权限 advance

        String url = MOVIE_API_BASE_URL + "subject/" + id + "/reviews";

        HttpHelper.get(url, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                LogUtil.d(TAG, "getSubjectReviews: " + response.toString());
            }
        });
    }

    /**
     * 电影条目短评列表
     * @param id
     */
    public static void getSubjectComments(int id) {
        // TODO 检查权限 advance

        String url = MOVIE_API_BASE_URL + "subject/" + id + "/comments";

        HttpHelper.get(url, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                LogUtil.d(TAG, "getSubjectComments: " + response.toString());
            }
        });
    }

    /**
     * 影人作品
     * @param id
     */
    public static void getCelebrityWorks(int id) {
        // advance

        String url = MOVIE_API_BASE_URL + "celebrity/" + id + "/works";

        HttpHelper.get(url, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                LogUtil.d(TAG, "getCelebrityWorks: " + response.toString());
            }
        });
    }

    /**
     * 影人剧照
     * @param id
     */
    public static void getCelebrityPhotos(int id) {
        // advance
        String url = MOVIE_API_BASE_URL + "celebrity/" + id + "/photos";

        HttpHelper.get(url, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                LogUtil.d(TAG, "getCelebrityPhotos: " + response.toString());
            }
        });
    }

    /**
     * 电影条目搜索
     * @param content
     * @param tag
     * @param start
     * @param count
     */
    public static void search(String content, String tag, int start, int count,
                              final MovieListener<MovieList> listener) {
        String url = MOVIE_API_BASE_URL + "search";

        RequestParams param = new RequestParams();
        param.put("q", content);
        param.put("tag", tag);
        param.put("start", start);
        param.put("count", count);

        HttpHelper.get(url, param, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                LogUtil.d(TAG, "search: " + response.toString());
                MovieList list = JsonUtil.fromJson(response.toString(), MovieList.class);
                listener.onSuccess(list);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                ErrorResponse response = JsonUtil.fromJson(errorResponse.toString(), ErrorResponse.class);
                listener.onFailure(new RequestException(throwable, response));
            }
        });
    }

    /**
     * 豆瓣 top250
     * @param start
     * @param count
     */
    public static void getTop250(int start, int count, final MovieListener<SubjectList> listener) {
        String url = MOVIE_API_BASE_URL + "top250";

        RequestParams param = new RequestParams();
        param.put("start", start);
        param.put("count", count);

        HttpHelper.get(url, param, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                LogUtil.d(TAG, "getTop250: " + response.toString());
                SubjectList subjectList = JsonUtil.fromJson(response.toString(), SubjectList.class);
                listener.onSuccess(subjectList);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                ErrorResponse response = JsonUtil.fromJson(errorResponse.toString(), ErrorResponse.class);
                listener.onFailure(new RequestException(throwable, response));
            }
        });
    }

    /**
     * 北美票房榜
     */
    public static void getUsBox(final MovieListener<UsBox> listener) {
        String url = MOVIE_API_BASE_URL + "us_box";

        HttpHelper.get(url, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                LogUtil.d(TAG, "getUsBox: " + response.toString());
                UsBox usBox = JsonUtil.fromJson(response.toString(), UsBox.class);
                listener.onSuccess(usBox);
            }
        });
    }

    /**
     * 口碑榜
     */
    public static void getWeekly() {
        // advance
        String url = MOVIE_API_BASE_URL + "weekly";

        HttpHelper.get(url, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                LogUtil.d(TAG, "getWeekly: " + response.toString());
            }
        });
    }

    /**
     * 新片榜
     */
    public static void getNewMovies() {
        // advance
        String url = MOVIE_API_BASE_URL + "new_movies";

        HttpHelper.get(url, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                LogUtil.d(TAG, "getNewMovies: " + response.toString());
            }
        });
    }

    /**
     * 正在上映
     */
    public static void getNowPlaying() {
        // premium
        String url = MOVIE_API_BASE_URL + "nowplaying";

        HttpHelper.get(url, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                LogUtil.d(TAG, "getNowPlaying: " + response.toString());
            }
        });
    }

    /**
     * 即将上映
     */
    public static void getComing() {
        // premium
        String url = MOVIE_API_BASE_URL + "coming";

        HttpHelper.get(url, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                LogUtil.d(TAG, "getComing: " + response.toString());
            }
        });
    }

}
