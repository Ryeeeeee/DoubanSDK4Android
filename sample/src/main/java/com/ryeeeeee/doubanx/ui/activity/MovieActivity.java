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

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RatingBar;
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

    private final static String TAG = MovieActivity.class.getSimpleName();

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    /** toolbar */
    private Toolbar mNormalToolbar;
    private Toolbar mSearchToolbar;
    private ImageView mSearchBackView;
    private EditText mSearchEditText;
    private ImageView mSearchEndingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initToolBar();

        FrameLayout frameLayout = (FrameLayout) this.findViewById(R.id.content_frame);
        RelativeLayout relativeLayout = (RelativeLayout) getLayoutInflater().inflate(R.layout.activity_movie,
                frameLayout, false);
        frameLayout.addView(relativeLayout);

        mRecyclerView = (RecyclerView) this.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // 选中左侧的导航选项
        setNavigationItemViewSelected(NAVIGATION_ITEM_MOVIE);

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
            private RatingBar mRatingBar;
            private TextView mYearView;
            private TextView mSubtypeView;

            private ViewHolder(View itemView) {
                super(itemView);
                mCardView = (CardView) itemView.findViewById(R.id.card_view);
                mNameView = (TextView) itemView.findViewById(R.id.card_name);
                mPictureView = (ImageView) itemView.findViewById(R.id.card_picture);
                mRatingView = (TextView) itemView.findViewById(R.id.card_rating);
                mRatingBar = (RatingBar) itemView.findViewById(R.id.card_rating_bar);
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
            holder.mRatingView.setText(subject.getRating().getAverage() + "");
            holder.mRatingBar.setRating(subject.getRating().getAverage() / 2);
            holder.mYearView.setText(subject.getYear());
            holder.mSubtypeView.setText(subject.getSubtype());

            /** 点击电影时，跳转到电影详细界面 */
            holder.mCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(MovieActivity.this, MovieDetailActivity.class);
                    intent.putExtra(MovieDetailActivity.EXTRA_MOVIE_PICTURE, subject.getImages().getLarge());
                    intent.putExtra(MovieDetailActivity.EXTRA_MOVIE_NAME, subject.getTitle());
                    intent.putExtra(MovieDetailActivity.EXTRA_MOVIE_RATING, subject.getRating().getAverage());
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_movie, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_search) {
            // 点击搜索栏时，toolbar 切换为搜索界面
            mNormalToolbar.setVisibility(View.GONE);
            mSearchToolbar.setVisibility(View.VISIBLE);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     *
     */
    private void initToolBar() {
        getSupportActionBar().setTitle("豆瓣电影");
        mNormalToolbar = (Toolbar) findViewById(R.id.toolbar_normal);
        mSearchToolbar = (Toolbar) findViewById(R.id.toolbar_search);
        mSearchBackView = (ImageView) findViewById(R.id.toolbar_search_back_button);
        mSearchEditText = (EditText) findViewById(R.id.toolbar_search_query_text);
        mSearchEndingView = (ImageView) findViewById(R.id.toolbar_search_ending_button);
        mSearchBackView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mNormalToolbar.setVisibility(View.VISIBLE);
                mSearchToolbar.setVisibility(View.GONE);
            }
        });
    }
}
