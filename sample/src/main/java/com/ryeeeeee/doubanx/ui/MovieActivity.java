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

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ryeeeeee.doubansdk4android.api.movie.SimpleSubject;
import com.ryeeeeee.doubansdk4android.exception.RequestException;
import com.ryeeeeee.doubansdk4android.api.movie.MovieApi;
import com.ryeeeeee.doubansdk4android.api.movie.MovieListener;
import com.ryeeeeee.doubansdk4android.api.movie.Subject;
import com.ryeeeeee.doubansdk4android.api.movie.SubjectList;
import com.ryeeeeee.doubansdk4android.api.movie.UsBox;
import com.ryeeeeee.doubansdk4android.util.LogUtil;
import com.ryeeeeee.doubanx.R;

import java.util.List;

/**
 * @author Ryeeeeee
 * @since 2015-02-16
 */
public class MovieActivity extends BaseActivity {

    private final static String TAG = "MovieActivity";

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("豆瓣电影");

        FrameLayout frameLayout = (FrameLayout) this.findViewById(R.id.content_frame);
        RelativeLayout relativeLayout = (RelativeLayout) getLayoutInflater().inflate(R.layout.activity_movie,
                frameLayout, false);
        frameLayout.addView(relativeLayout);

        mRecyclerView = (RecyclerView) this.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        MovieApi.getTop250(0, 20, new MovieListener<SubjectList>() {
            @Override
            public void onSuccess(SubjectList subjectList) {
                LogUtil.d(TAG, subjectList.toString());
                mAdapter = new RecyclerAdapter(subjectList.getSubjects());
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(RequestException exception) {
                LogUtil.d(TAG, exception.toString());
            }
        });

    }

    private void unitTest() {
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

    private class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
        private List<SimpleSubject> mSimpleSubjects;

        private RecyclerAdapter(List<SimpleSubject> simpleSubjects) {
            mSimpleSubjects = simpleSubjects;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            private CardView mCardView;
            private TextView mNameView;
            private ImageView mPictureView;
            private TextView mRatingView;
            private TextView mYearView;
            private TextView mSubtypeView;

            private ViewHolder(View itemView) {
                super(itemView);
                mCardView = (CardView) itemView.findViewById(R.id.card_view);
                mNameView = (TextView) itemView.findViewById(R.id.card_name);
                mPictureView = (ImageView) itemView.findViewById(R.id.card_picture);
                mRatingView = (TextView) itemView.findViewById(R.id.card_rating);
                mYearView = (TextView) itemView.findViewById(R.id.card_year);
                mSubtypeView = (TextView) itemView.findViewById(R.id.card_subtype);
            }
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_card_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            final SimpleSubject subject = mSimpleSubjects.get(position);

            Glide.with(MovieActivity.this).load(subject.getImages().getLarge()).into(holder.mPictureView);
            holder.mNameView.setText(subject.getTitle());
            holder.mRatingView.setText(subject.getRating().getValue() + "");
            holder.mYearView.setText(subject.getYear());
            holder.mSubtypeView.setText(subject.getSubtype());

            holder.mCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(MovieActivity.this, MovieDetailActivity.class);
                    intent.putExtra(MovieDetailActivity.EXTRA_MOVIE_PICTURE, subject.getImages().getLarge());
                    intent.putExtra(MovieDetailActivity.EXTRA_MOVIE_NAME, subject.getTitle());
                    intent.putExtra(MovieDetailActivity.EXTRA_MOVIE_RATING, subject.getRating().getValue());
                    intent.putExtra(MovieDetailActivity.EXTRA_MOVIE_YEAR, subject.getYear());
                    intent.putExtra(MovieDetailActivity.EXTRA_MOVIE_SUBTYPE, subject.getSubtype());
                    intent.putExtra(MovieDetailActivity.EXTRA_MOVIE_ID, subject.getId());

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MovieActivity.this,
                                holder.mCardView, getResources().getString(R.string.movie_card_transition_name));
                        MovieActivity.this.startActivity(intent, options.toBundle());

                    } else {

                        MovieActivity.this.startActivity(intent);

                    }

                }
            });

        }

        @Override
        public int getItemCount() {
            return mSimpleSubjects.size();
        }
    }
}
