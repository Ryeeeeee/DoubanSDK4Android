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
package com.ryeeeeee.doubanx.ui;

import android.os.Bundle;

import com.ryeeeeee.doubansdk4android.api.RequestException;
import com.ryeeeeee.doubansdk4android.api.movie.MovieApi;
import com.ryeeeeee.doubansdk4android.api.movie.MovieListener;
import com.ryeeeeee.doubansdk4android.api.movie.Subject;
import com.ryeeeeee.doubansdk4android.api.movie.SubjectList;
import com.ryeeeeee.doubansdk4android.api.movie.UsBox;
import com.ryeeeeee.doubansdk4android.util.LogUtil;

/**
 * @author Ryeeeeee
 * @since 2015-02-16
 */
public class MovieActivity extends BaseActivity {

    private final static String TAG = "MovieActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("豆瓣电影");

        MovieApi.getMovieSubject(1764796, new MovieListener<Subject>() {
            @Override
            public void onSuccess(Subject subject) {
                LogUtil.d(TAG, subject.toString());
            }

            @Override
            public void onFailure(RequestException exception) {

            }
        });

        MovieApi.getCelebrity(1054395);

        MovieApi.getTop250(0, 20, new MovieListener<SubjectList>() {
            @Override
            public void onSuccess(SubjectList subjectList) {

            }

            @Override
            public void onFailure(RequestException exception) {

            }
        });

        MovieApi.getUsBox(new MovieListener<UsBox>() {
            @Override
            public void onSuccess(UsBox usBox) {
                LogUtil.d(TAG, usBox.toString());
            }

            @Override
            public void onFailure(RequestException exception) {

            }
        });

    }
}
