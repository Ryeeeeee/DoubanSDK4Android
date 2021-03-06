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

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.transition.ChangeBounds;
import android.transition.ChangeTransform;
import android.transition.Explode;
import android.transition.Fade;
import android.util.SparseIntArray;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.ryeeeeee.doubansdk4android.api.user.UserListener;
import com.ryeeeeee.doubansdk4android.exception.RequestException;
import com.ryeeeeee.doubansdk4android.api.user.UserApi;
import com.ryeeeeee.doubansdk4android.api.user.UserInfo;
import com.ryeeeeee.doubanx.R;
import com.ryeeeeee.doubanx.manager.AppManager;
import com.ryeeeeee.doubanx.util.UIUtil;

import java.util.ArrayList;

import static com.ryeeeeee.doubanx.R.id.title_text;

/**
 * @author Ryeeeeee
 * @since 2015-02-14
 */
public class BaseActivity extends ActionBarActivity {
    /**  */
    private DrawerLayout mDrawerLayout;
    /** */
    private ViewGroup mNavigationItemContainer;
    /** */
    private View[] mNavigationItemViews;
    /** */
    private ArrayList<Integer> mNavigationItems = new ArrayList<>();

    private ImageView mAvatarView;
    private TextView mNameView;
    private TextView mAccountView;
    /** navigation item 是否点击 */
    private boolean mOnNavigationClicked = false;

    /** 定义导航栏选项 */
    protected final static int NAVIGATION_ITEM_MOVIE = 0;
    protected final static int NAVIGATION_ITEM_FM = 1;
    protected final static int NAVIGAtiON_ITEM_SETTING = 2;
    protected final static int NAVIGATION_ITEM_HOME = 3;
    protected final static int NAVIGATION_ITEM_INVALID = -1;
    protected final static int NAVIGATION_ITEM_SEPARATOR = -2;

    /** 导航栏选项标题资源 ID */
    private static final SparseIntArray NAVIGATION_TITLE_RES_ID = new SparseIntArray() {
        {
            put(NAVIGATION_ITEM_MOVIE, R.string.navigation_item_movie);
            put(NAVIGATION_ITEM_FM, R.string.navigation_item_FM);
            put(NAVIGAtiON_ITEM_SETTING, R.string.navigation_item_setting);
            put(NAVIGATION_ITEM_HOME, R.string.navigation_item_home);
        }
    };

    /** 导航栏选项 Icon ID */
    private static final SparseIntArray NAVIGATION_ICON_RES_ID = new SparseIntArray() {
        {
            put(NAVIGATION_ITEM_MOVIE, R.drawable.ic_movie_24dp);
            put(NAVIGATION_ITEM_FM, R.drawable.ic_radio_24dp);
            put(NAVIGAtiON_ITEM_SETTING, R.drawable.ic_settings_24dp);
            put(NAVIGATION_ITEM_HOME, R.drawable.ic_home_24dp);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        enableTransition();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        mDrawerLayout = (DrawerLayout) this.findViewById(R.id.drawer_layout);
        mDrawerLayout.setStatusBarBackground(R.color.theme_dark_primary);

        Toolbar toolbar = (Toolbar) this.findViewById(R.id.toolbar_normal);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawerLayout.openDrawer(Gravity.LEFT);
            }
        });

        getCurrentUserInfo();
        populateNavigationItems();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
    }

    /** 选中导航栏选项 */
    protected void setNavigationItemViewSelected(int navigationItem) {
        for(int i = 0; i < mNavigationItems.size(); i++) {
            if (mNavigationItems.get(i) == navigationItem) {
                mNavigationItemViews[i].setSelected(true);
//                TextView textView = (TextView) mNavigationItemViews[i].findViewById(title_text);
//                textView.;
                break;
            }
        }
    }

    /**
     * 填充导航栏选项
     */
    private void populateNavigationItems() {
        mNavigationItems.clear();

        mNavigationItems.add(NAVIGATION_ITEM_HOME);
        mNavigationItems.add(NAVIGATION_ITEM_MOVIE);
        mNavigationItems.add(NAVIGATION_ITEM_FM);
        mNavigationItems.add(NAVIGATION_ITEM_SEPARATOR);
        mNavigationItems.add(NAVIGAtiON_ITEM_SETTING);

        createNavigationItems();
    }

    /**
     * 创建导航栏选项列表
     */
    private void createNavigationItems() {
        mNavigationItemContainer = (ViewGroup) this.findViewById(R.id.navigation_items_list);
        if (mNavigationItemContainer == null) {
            return ;
        }

        mNavigationItemViews = new View[mNavigationItems.size()];
        mNavigationItemContainer.removeAllViews();

        for (int i = 0; i < mNavigationItems.size(); i++) {
            mNavigationItemViews[i] = makeNavigationDrawerItem(mNavigationItems.get(i), mNavigationItemContainer);
            mNavigationItemContainer.addView(mNavigationItemViews[i]);
        }
    }

    /**
     * 创建导航栏选项元素
     * @return
     */
    private View makeNavigationDrawerItem(final int itemId, ViewGroup container) {
        View view = null;
        if (itemId == NAVIGATION_ITEM_SEPARATOR) {

            view = this.getLayoutInflater().inflate(R.layout.navigation_separator, container, false);
            UIUtil.setAccessibilityIgnore(view);
            return view;

        } else {

            view = this.getLayoutInflater().inflate(R.layout.navigation_item, container, false);
            ImageView iconView = (ImageView) view.findViewById(R.id.icon_image);
            TextView titleView = (TextView) view.findViewById(title_text);

            int iconId = NAVIGATION_ICON_RES_ID.get(itemId);
            int titleId = NAVIGATION_TITLE_RES_ID.get(itemId);

            iconView.setVisibility(View.VISIBLE);
            titleView.setVisibility(View.VISIBLE);

            iconView.setBackgroundResource(iconId);
            titleView.setText(getString(titleId));

        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onNavigationItemClick(itemId);
            }
        });

        return view;
    }

    /**
     * 当导航栏的选项点击时调用
     * @param itemId
     */
    private void onNavigationItemClick(int itemId) {

        switch (itemId) {
            case NAVIGATION_ITEM_HOME:
                closeDrawersAndSwitchActivity(HomeActivity.class);
                break;
            case NAVIGATION_ITEM_MOVIE:
                closeDrawersAndSwitchActivity(MovieActivity.class);
                break;
            case NAVIGATION_ITEM_FM:
                closeDrawersAndSwitchActivity(FMActivity.class);
                break;
            case NAVIGAtiON_ITEM_SETTING:
                break;
            default:
                break;
        }
    }

    /**
     * 填充用户资料
     */
    private void populateProfileInfo(UserInfo userInfo) {
        mAccountView = (TextView) this.findViewById(R.id.profile_account_text);
        mNameView = (TextView) this.findViewById(R.id.profile_name_text);
        mAvatarView = (ImageView) this.findViewById(R.id.profile_image);

        mAccountView.setText(userInfo.getUid());
        mNameView.setText(userInfo.getName());
        Glide.with(this).load(userInfo.getAvatar()).into(mAvatarView);

    }

    /**
     * 获得当前授权用户的信息
     */
    private void getCurrentUserInfo() {

        UserInfo userInfo = AppManager.getInstance().getCurrentUserInfo();
        if (userInfo != null) {
            populateProfileInfo(userInfo);
            return;
        }

        UserApi.getCurrentUserInfo(new UserListener<UserInfo>() {
            @Override
            public void onSuccess(UserInfo userInfo) {
                Toast.makeText(BaseActivity.this, "获取用户信息成功", Toast.LENGTH_SHORT).show();
                AppManager.getInstance().setCurrentUserInfo(userInfo);
                populateProfileInfo(userInfo);
            }

            @Override
            public void onFailure(RequestException exception) {
                Toast.makeText(BaseActivity.this, "获取用户信息失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 在 Lollipop 上启用 transition
     */
    private void enableTransition() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            // inside your activity (if you did not enable transitions in your theme)
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);

            // To start an enter transition as soon as possible,
            // use the Window.setAllowEnterTransitionOverlap() method on the called activity.
            // This lets you have more dramatic enter transitions.
            getWindow().setAllowEnterTransitionOverlap(true);

            // set an exit transition
            getWindow().setExitTransition(new Fade());
            // set an enter transition
            getWindow().setEnterTransition(new Fade());

            // set an exit transition for shared element
            getWindow().setSharedElementExitTransition(new ChangeBounds().setDuration(200));
            // set an enter transition for shared element
            getWindow().setSharedElementEnterTransition(new ChangeBounds().setDuration(200));

        }
    }

    /**
     * 关闭侧边栏，并切换Activity
     */
    private void closeDrawersAndSwitchActivity(final Class classType) {
        mOnNavigationClicked = true;
        mDrawerLayout.closeDrawers();
        mDrawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                startActivity(new Intent(BaseActivity.this, classType));
                mDrawerLayout.setDrawerListener(null);
                BaseActivity.this.finish();
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
    }

}
