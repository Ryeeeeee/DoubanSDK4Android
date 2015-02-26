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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Ryeeeeee
 * @since 2015-02-25
 */
public class Scope {

    /** 通用 */
    public final static String DOUBAN_BASIC_COMMON = "douban_basic_common";

    /** 电影权限 */
    public final static String MOVIE_BASIC_R = "movie_basic_r";
    public final static String MOVIE_BASIC_W = "movie_basic_w";
    public final static String MOVIE_BASIC = "movie_basic";
    public final static String MOVIE_ADVANCE_R = "movie_advance_r";
    public final static String MOVIE_ADVANCE_W = "movie_advance_w";
    public final static String MOVIE_PREMIUM_R = "movie_premium_r";
    public final static String MOVIE_PREMIUM_W = "movie_premium_w";

    /** 图书 */
    public final static String BOOK_BASIC_R = "book_basic_r";
    public final static String BOOK_BASIC_W = "book_basic_w";

    /** 音乐 */
    public final static String MUSIC_BASIC_R = "music_basic_r";

    /** 同城 */
    public final static String EVENT_BASIC_R = "event_basic_r";
    public final static String EVENT_BASIC_W = "event_basic_w";

    /** 日记 */
    public final static String COMMUNITY_BASIC_NOTE = "community_basic_note";

    /** 线上活动 */
    public final static String COMMUNITY_BASIC_ONLINE = "community_basic_online";
    public final static String COMMUNITY_ADVANCED_ONLINE = "community_advanced_online";

    /** 论坛 */
    public final static String DOUBAN_COMMON_BASIC = "douban_common_basic";

    /** 我去 */
    public final static String TRAVEL_BASIC_R = "travel_basic_r";

    /**
     * 获得所有权限拼接成的字符串
     * @return
     */
    public static String getAllScopeByString() {
        StringBuilder builder = new StringBuilder(DOUBAN_BASIC_COMMON);
        builder.append(",").append(MOVIE_BASIC_R);
        builder.append(",").append(MOVIE_BASIC_W);
        builder.append(",").append(MOVIE_BASIC);
        builder.append(",").append(MOVIE_ADVANCE_R);
        builder.append(",").append(MOVIE_ADVANCE_W);
        builder.append(",").append(MOVIE_PREMIUM_R);
        builder.append(",").append(MOVIE_PREMIUM_W);
        builder.append(",").append(BOOK_BASIC_R);
        builder.append(",").append(BOOK_BASIC_W);
        builder.append(",").append(EVENT_BASIC_R);
        builder.append(",").append(EVENT_BASIC_W);
        builder.append(",").append(MOVIE_BASIC_R);
        builder.append(",").append(COMMUNITY_BASIC_NOTE);
        builder.append(",").append(COMMUNITY_BASIC_ONLINE);
        builder.append(",").append(COMMUNITY_ADVANCED_ONLINE);
        builder.append(",").append(DOUBAN_COMMON_BASIC);
        builder.append(",").append(TRAVEL_BASIC_R);
        builder.append(",").append(MUSIC_BASIC_R);
        return builder.toString();
    }

    /**
     * 获得所有权限的列表
     * @return
     */
    public static List<String> getAllScopeByList() {
        List<String> list = new ArrayList<String>();
        list.add(DOUBAN_BASIC_COMMON);
        list.add(MOVIE_BASIC_R);
        list.add(MOVIE_BASIC);
        list.add(MOVIE_ADVANCE_W);
        list.add(MOVIE_BASIC_W);
        list.add(MOVIE_PREMIUM_R);
        list.add(MOVIE_ADVANCE_R);
        list.add(MOVIE_PREMIUM_W);
        list.add(BOOK_BASIC_R);
        list.add(BOOK_BASIC_W);
        list.add(EVENT_BASIC_R);
        list.add(EVENT_BASIC_W);
        list.add(MOVIE_BASIC_R);
        list.add(COMMUNITY_BASIC_NOTE);
        list.add(COMMUNITY_BASIC_ONLINE);
        list.add(COMMUNITY_ADVANCED_ONLINE);
        list.add(DOUBAN_COMMON_BASIC);
        list.add(TRAVEL_BASIC_R);
        list.add(MUSIC_BASIC_R);
        return list;
    }

    /**
     * 将权限列表转换成权限字符串
     * @param scopeList
     * @return
     */
    public static String convertScopeList2String(List<String> scopeList) {
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < scopeList.size(); i++) {
            if (i == 0) {
                builder.append(scopeList.get(i));
            } else {
                builder.append(",").append(scopeList.get(i));
            }
        }
        return builder.toString();
    }

    /**
     * 将权限字符串转换成权限列表
     * @param scope
     * @return
     */
    public static List<String> convertScopeString2List(String scope) {

        if (scope != null && !scope.trim().equals("")) {
            String[] scopes = scope.split(",");
            List<String> list = Arrays.asList(scopes);
            return list;
        }
        return null;
    }
}
