/*
 * The MIT License (MIT)
 * 
 * Copyright (c) 2015 Ryeeeeee
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
package com.ryeeeeee.doubansdk4android.auth.oauth;

/**
 * @author Ryeeeeee
 * @since 2015-01-23
 */
public class AccessTokenResponse {

    /** 访问令牌 */
    private String mAccessToken;
    /** 访问令牌有效期 */
    private int mExpiresIn;
    /** 刷新令牌 */
    private String mRefreshToken;
    /** 令牌对应的用户ID */
    private String mUserID;

    public AccessTokenResponse(String accessToken, int expiresIn, String refreshToken, String userID){
        this.mAccessToken = accessToken;
        this.mExpiresIn = expiresIn;
        this.mRefreshToken = refreshToken;
        this.mUserID = userID;
    }

    public String getAccessToken() {
        return mAccessToken;
    }

    public int getExpiresIn() {
        return mExpiresIn;
    }

    public String getRefreshToken() {
        return mRefreshToken;
    }

    public String getUserID() {
        return mUserID;
    }

    public void setAccessToken(String accessToken) {
        mAccessToken = accessToken;
    }

    public void setExpiresIn(int expiresIn) {
        mExpiresIn = expiresIn;
    }

    public void setRefreshToken(String refreshToken) {
        mRefreshToken = refreshToken;
    }

    public void setUserID(String userID) {
        mUserID = userID;
    }
}
