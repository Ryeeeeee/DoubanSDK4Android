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
package com.ryeeeeee.doubansdk4android.constant;

/**
 * @author Ryeeeeee
 * @since 2015-01-23
 */
public class ErrorCode {

    /** 错误的请求协议 */
    public static final String INVALID_REQUEST_SCHEME = "100";
    /** 错误的请求方法 */
    public static final String INVALID_REQUEST_METHOD = "101";
    /** 未找到 access token */
    public static final String ACCESS_TOKEN_IS_MISSING = "102";
    /** access token 不存在或已被用户删除，或者用户修改了密码 */
    public static final String INVALID_ACCESS_TOKEN = "103";
    /** api key 不存在或已删除 */
    public static final String INVALID_APIKEY = "104";
    /** api key 已被禁用 */
    public static final String APIKEY_IS_BLOCKED = "105";
    /** access token 已过期 */
    public static final String ACCESS_TOKEN_HAS_EXPIRED = "106";
    /** 请求地址未注册 */
    public static final String INVALID_REQUEST_URI = "107";
    /** 用户未授权访问此数据 */
    public static final String INVALID_CREDENCIAL1 = "108";
    /** apikey未申请此权限 */
    public static final String INVALID_CREDENCIAL2 = "109";
    /** 未注册的测试用户 */
    public static final String NOT_TRIAL_USER = "110";
    /** 用户访问速度限制 */
    public static final String RATE_LIMIT_EXCEEDED1 = "111";
    /** IP访问速度限制 */
    public static final String RATE_LIMIT_EXCEEDED2 = "112";
    /**  缺少参数 */
    public static final String REQUIRED_PARAMETER_IS_MISSING = "113";
    /** 错误的 grant type */
    public static final String UNSUPPORTED_GRANT_TYPE = "114";
    /** 错误的 response type */
    public static final String UNSUPPORTED_RESPONSE_TYPE = "115";
    /** client secret 不匹配 */
    public static final String CLIENT_SECRET_MISMATCH = "116";
    /** redirect uri 不匹配 */
    public static final String REDIRECT_URI_MISMATCH = "117";
    /** authorization code 不存在或已过期 */
    public static final String INVALID_AUTHORIZATION_CODE = "118";
    /** refresh token不存在或已过期 */
    public static final String INVALID_REFRESH_TOKEN = "119";
    /** 用户名密码不匹配 */
    public static final String USERNAME_PASSWORD_MISMATCH = "120";
    /** 用户不存在或已删除 */
    public static final String INVALID_USER = "121";
    /** 用户已被屏蔽 */
    public static final String USER_HAS_BLOCKED = "122";
    /** 因用户修改密码而导致 access token 过期 */
    public static final String ACCESS_TOKEN_HAS_EXPIRED_SINCE_PASSWORD_CHANGED = "123";
    /** access token 未过期 */
    public static final String ACCESS_TOKEN_HAS_NOT_EXPIRED = "124";
    /** 访问的 scope 不合法，开发者不用太关注，一般不会出现该错误 */
    public static final String INVALID_REQUEST_SCOPE = "125";
    /** 未知错误 */
    public static final String UNKNOWN = "999";
}


