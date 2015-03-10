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

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;
import android.widget.Toast;

import com.ryeeeeee.doubansdk4android.Douban;
import com.ryeeeeee.doubansdk4android.api.auth.AuthListener;
import com.ryeeeeee.doubansdk4android.api.movie.MovieApi;
import com.ryeeeeee.doubansdk4android.api.movie.MovieList;
import com.ryeeeeee.doubansdk4android.api.movie.MovieListener;
import com.ryeeeeee.doubansdk4android.api.movie.Subject;
import com.ryeeeeee.doubansdk4android.api.movie.SubjectList;
import com.ryeeeeee.doubansdk4android.api.movie.UsBox;
import com.ryeeeeee.doubansdk4android.api.shuo.ShuoApi;
import com.ryeeeeee.doubansdk4android.api.user.UserList;
import com.ryeeeeee.doubansdk4android.exception.RequestException;
import com.ryeeeeee.doubansdk4android.api.user.UserApi;
import com.ryeeeeee.doubansdk4android.api.user.UserInfo;
import com.ryeeeeee.doubansdk4android.api.user.UserListener;
import com.ryeeeeee.doubansdk4android.api.auth.oauth.Scope;
import com.ryeeeeee.doubansdk4android.exception.DoubanException;
import com.ryeeeeee.doubansdk4android.util.LogUtil;
import com.ryeeeeee.doubanx.R;

import static android.app.ActivityOptions.makeSceneTransitionAnimation;


public class MainActivity extends ActionBarActivity {
    private final static String TAG = "DoubanX";

    private Button mAnimationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        LogUtil.setLogEnabled(true);

        unitTest();

        Douban.init(this, "0abda2e1d3262fea2038e8a579728fbe", "9196f7a84f90c966",
                "http://ryeeeeee.com");

        findViewById(R.id.button_auth).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.i(TAG, "认证 ...");
                Douban.authorize(Scope.getAllScopeByString(), new AuthListener() {
                    @Override
                    public void onAuthSuccess(String userId, String userName) {
                        Toast.makeText(MainActivity.this, "userId:" + userId + " userName:" + userName,
                                Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this, HomeActivity.class));
                    }

                    @Override
                    public void onAuthFailure(RequestException exception) {
                        Toast.makeText(MainActivity.this, exception.toString(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(DoubanException exception) {
                        Toast.makeText(MainActivity.this, exception.toString(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(MainActivity.this, "取消授权", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFinish() {
                        LogUtil.i(TAG, "onFinish");
                    }
                });
            }
        });

        this.findViewById(R.id.button_current_user).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserApi.getCurrentUserInfo(new UserListener<UserInfo>() {
                    @Override
                    public void onSuccess(UserInfo userInfo) {
                        Toast.makeText(MainActivity.this, "获取认证用户成功！", Toast.LENGTH_SHORT).show();
                        LogUtil.d(TAG, userInfo.toString());
                    }

                    @Override
                    public void onFailure(RequestException exception) {
                        Toast.makeText(MainActivity.this, "获取认证用户失败！", Toast.LENGTH_SHORT).show();
                    }

                });
            }
        });

        findViewById(R.id.button_animation).setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                if(mAnimationButton.getVisibility() == View.VISIBLE) {
                    int cx = (mAnimationButton.getLeft() + mAnimationButton.getRight()) / 2;
                    int cy = (mAnimationButton.getTop() + mAnimationButton.getBottom()) / 2;

                    // get the initial radius for the clipping circle
                    int initialRadius = mAnimationButton.getWidth();

                    // create the animation (the final radius is zero)
                    Animator anim = ViewAnimationUtils.createCircularReveal(mAnimationButton, cx, cy, initialRadius, 0);
                    anim.setDuration(3000);

                    // make the view invisible when the animation is done
                    anim.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            mAnimationButton.setVisibility(View.INVISIBLE);
                        }
                    });
                    // start the animation
                    anim.start();

                } else {
                    // get the center for the clipping circle
                    int cx = (mAnimationButton.getLeft() + mAnimationButton.getRight()) / 2;
                    int cy = (mAnimationButton.getTop() + mAnimationButton.getBottom()) / 2;

                    // get the final radius for the clipping circle
                    int finalRadius = Math.max(mAnimationButton.getWidth(), mAnimationButton.getHeight());

                    // create the animator for this view (the start radius is zero)
                    Animator anim =
                            ViewAnimationUtils.createCircularReveal(mAnimationButton, cx, cy, 0, finalRadius);

                    // make the view visible and start the animation
                    mAnimationButton.setVisibility(View.VISIBLE);
                    anim.start();
                }
            }
        });

        findViewById(R.id.button_search_user).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserApi.searchUser("Ryeeeeee", 1, 20, new UserListener<UserList>() {
                    @Override
                    public void onSuccess(UserList userList) {
                        LogUtil.d(TAG, "searchUserButton:" + userList.toString());
                    }

                    @Override
                    public void onFailure(RequestException exception) {
                        LogUtil.d(TAG, "searchUserButton:" + exception.toString());
                    }
                });
            }
        });

        findViewById(R.id.button_get_user_info).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserApi.getUserInfo("Ryeeeeee", new UserListener<UserInfo>() {
                    @Override
                    public void onSuccess(UserInfo userInfo) {
                        LogUtil.d(TAG, "getUserInfoButton:" + userInfo.toString());
                    }

                    @Override
                    public void onFailure(RequestException exception) {
                        LogUtil.d(TAG, "getUserInfoButton:" + exception.toString());
                    }
                });
            }
        });

        findViewById(R.id.button_get_movie_celebrity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MovieApi.getCelebrity(1054395);
            }
        });

        findViewById(R.id.button_get_movie_subject).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MovieApi.getMovieSubject(1764796, new MovieListener<Subject>() {
                    @Override
                    public void onSuccess(Subject subject) {
                        LogUtil.d(TAG, "GET_MOVIE_SUBJECT: " + subject.toString());
                    }

                    @Override
                    public void onFailure(RequestException exception) {
                        LogUtil.d(TAG, "GET_MOVIE_SUBJECT: " + exception.toString());
                    }
                });
            }
        });

        findViewById(R.id.button_get_movie_top250).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MovieApi.getTop250(0, 20, new MovieListener<SubjectList>() {
                    @Override
                    public void onSuccess(SubjectList subjectList) {
                        LogUtil.d(TAG, "GET_MOVIE_TOP250: " + subjectList.toString());
                    }

                    @Override
                    public void onFailure(RequestException exception) {
                        LogUtil.d(TAG, "GET_MOVIE_TOP250: " + exception.toString());
                    }
                });
            }
        });

        findViewById(R.id.button_get_movie_us_box).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MovieApi.getUsBox(new MovieListener<UsBox>() {
                    @Override
                    public void onSuccess(UsBox usBox) {
                        LogUtil.d(TAG, "GET_MOVIE_US_BOX: " + usBox.toString());
                    }

                    @Override
                    public void onFailure(RequestException exception) {
                        LogUtil.d(TAG, "GET_MOVIE_US_BOX: " + exception.toString());
                    }
                });
            }
        });

        findViewById(R.id.button_search_movie).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MovieApi.search("肖申克", null, 0, 20, new MovieListener<MovieList>() {
                    @Override
                    public void onSuccess(MovieList movieList) {
                        LogUtil.d(TAG, "search_movie: " + movieList.toString());
                    }

                    @Override
                    public void onFailure(RequestException exception) {
                        LogUtil.d(TAG, "search_movie : " + exception.toString());
                    }
                });
            }
        });

        findViewById(R.id.button_post_shuo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShuoApi.postShuo("just for test", "from DoubanX",
                        MainActivity.this.getResources().getDrawable(R.drawable.ic_launcher),
                        "a", "b", "c", "d");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    private void unitTest() {
    }
}
