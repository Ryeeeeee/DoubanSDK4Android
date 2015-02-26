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
package com.ryeeeeee.doubansdk4android.api.auth.oauth;

/**
 * @author Ryeeeeee
 * @since 2015-01-23
 */
public class AccessTokenResponse {

    /** 访问令牌 */
    private String access_token;
    /** 访问令牌有效期 */
    private int expires_in;
    /** 刷新令牌 */
    private String refresh_token;
    /** 令牌对应的用户ID */
    private String douban_user_id;
    /** 令牌对应的用户名 */
    private String douban_user_name;

    public AccessTokenResponse() {
    }

    public AccessTokenResponse(String accessToken, int expiresIn, String refreshToken, String userID,
                               String userName){
        this.access_token = accessToken;
        this.expires_in = expiresIn;
        this.refresh_token = refreshToken;
        this.douban_user_id = userID;
        this.douban_user_name = userName;
    }

    public String getAccess_token() {
        return access_token;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public String getDouban_user_id() {
        return douban_user_id;
    }

    public String getDouban_user_name() {
        return douban_user_name;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public void setDouban_user_id(String douban_user_id) {
        this.douban_user_id = douban_user_id;
    }

    public void setDouban_user_name(String douban_user_name) {
        this.douban_user_name = douban_user_name;
    }

    @Override
    public String toString() {
        return super.toString() + " access_token:" + access_token + " refresh_token: " + refresh_token
                + " douban_user_id: " + douban_user_id + " douban_user_name: " + douban_user_name
                + " expires_in: " + expires_in;
    }
}
