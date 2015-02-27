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

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.SparseIntArray;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
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
            put(NAVIGATION_ITEM_MOVIE, R.drawable.ic_movie_grey600_24dp);
            put(NAVIGATION_ITEM_FM, R.drawable.ic_radio_grey600_24dp);
            put(NAVIGAtiON_ITEM_SETTING, R.drawable.ic_settings_grey600_24dp);
            put(NAVIGATION_ITEM_HOME, R.drawable.ic_home_grey600_24dp);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        mDrawerLayout = (DrawerLayout) this.findViewById(R.id.drawer_layout);

        Toolbar toolbar = (Toolbar) this.findViewById(R.id.toolbar);
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

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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
            TextView titleView = (TextView) view.findViewById(R.id.title_text);

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
        // TODO 添加回调逻辑
        switch (itemId) {
            case NAVIGATION_ITEM_HOME:
                startActivity(new Intent(this, HomeActivity.class));
                this.finish();
                break;
            case NAVIGATION_ITEM_MOVIE:
                startActivity(new Intent(this, MovieActivity.class));
                this.finish();
                break;
            case NAVIGATION_ITEM_FM:
                startActivity(new Intent(this, FMActivity.class));
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

    /** */
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
}
