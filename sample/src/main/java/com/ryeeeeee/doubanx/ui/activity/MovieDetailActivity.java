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

package com.ryeeeeee.doubanx.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.ryeeeeee.doubansdk4android.api.movie.MovieApi;
import com.ryeeeeee.doubansdk4android.api.movie.MovieListener;
import com.ryeeeeee.doubansdk4android.api.movie.Subject;
import com.ryeeeeee.doubansdk4android.exception.RequestException;
import com.ryeeeeee.doubansdk4android.util.LogUtil;
import com.ryeeeeee.doubanx.R;
import com.ryeeeeee.doubanx.ui.component.ExpandableTextView;

/**
 * @author Ryeeeeee
 * @since 2015-03-07
 */
public class MovieDetailActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    private final static String TAG = "MovieDetailActivity";

    public final static String EXTRA_MOVIE_PICTURE = "extra_movie_picture";
    public final static String EXTRA_MOVIE_NAME = "extra_movie_name";
    public final static String EXTRA_MOVIE_RATING = "extra_movie_rating";
    public final static String EXTRA_MOVIE_YEAR = "extra_movie_year";
    public final static String EXTRA_MOVIE_SUBTYPE = "extra_movie_subtype";
    public final static String EXTRA_MOVIE_ID = "extra_movie_id";

    public final static int INVALID_MOVIE_RATING = -1;

    /**  */
    private CardView mCardView;
    private TextView mNameView;
    private ImageView mPictureView;
    private TextView mRatingView;
    private RatingBar mRatingBar;
    private TextView mYearView;
    private TextView mSubtypeView;

    private SwipeRefreshLayout mSwipeRefreshLayout;
    /** 简介 */
    private ExpandableTextView mExpandableTextView;
    private TextView mDescriptionTextView;
    private ImageButton mExpandableImageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setTitle("豆瓣电影");

        FrameLayout frameLayout = (FrameLayout) this.findViewById(R.id.content_frame);
        View rootView = getLayoutInflater().inflate(R.layout.activity_movie_detail,
                frameLayout, false);
        frameLayout.addView(rootView);

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        mExpandableTextView = (ExpandableTextView) findViewById(R.id.expand_text_view);
        mDescriptionTextView = (TextView) findViewById(R.id.expandable_text);
        mExpandableImageButton = (ImageButton) findViewById(R.id.expand_collapse);

        initCardView();

        updateViewWithMovieId(getIntent().getStringExtra(EXTRA_MOVIE_ID));
    }

    private void initCardView() {

        mCardView = (CardView) findViewById(R.id.card_view);
        mNameView = (TextView) findViewById(R.id.card_name);
        mPictureView = (ImageView) findViewById(R.id.card_picture);
        mRatingView = (TextView) findViewById(R.id.card_rating);
        mRatingBar = (RatingBar) findViewById(R.id.card_rating_bar);
        mYearView = (TextView) findViewById(R.id.card_year);
        mSubtypeView = (TextView) findViewById(R.id.card_subtype);

        Intent intent = getIntent();
        mNameView.setText(intent.getStringExtra(EXTRA_MOVIE_NAME));
        Glide.with(MovieDetailActivity.this).load(intent.getStringExtra(EXTRA_MOVIE_PICTURE)).into(mPictureView);
        mRatingView.setText(intent.getFloatExtra(EXTRA_MOVIE_RATING, INVALID_MOVIE_RATING) + "");
        mRatingBar.setRating(intent.getFloatExtra(EXTRA_MOVIE_RATING, INVALID_MOVIE_RATING) / 2);
        mYearView.setText(intent.getStringExtra(EXTRA_MOVIE_YEAR));
        mSubtypeView.setText(intent.getStringExtra(EXTRA_MOVIE_SUBTYPE));
    }

    @Override
    public void onRefresh() {
        updateViewWithMovieId(getIntent().getStringExtra(EXTRA_MOVIE_ID));
    }

    /**
     * 更新界面
     * @param id
     */
    private void updateViewWithMovieId(String id) {

        if (id == null || id.trim().equals("")) {
            Toast.makeText(MovieDetailActivity.this, R.string.failed_to_get_information, Toast.LENGTH_SHORT).show();
            mSwipeRefreshLayout.setRefreshing(false);
            return;
        }

        mSwipeRefreshLayout.setRefreshing(true);

        MovieApi.getMovieSubject(id, new MovieListener<Subject>() {
            @Override
            public void onSuccess(Subject subject) {
                updateViewWithMovieSubject(subject);
                mSwipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(RequestException exception) {
                Toast.makeText(MovieDetailActivity.this, R.string.failed_to_get_information, Toast.LENGTH_SHORT).show();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    /**
     * 更新界面
     * @param subject
     */
    private void updateViewWithMovieSubject(Subject subject) {
        LogUtil.d(TAG, subject.toString());
        mExpandableTextView.setText(subject.getSummary());
    }
}
