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
package com.ryeeeeee.doubansdk4android.api;

/**
 * @author Ryeeeeee
 * @since 2015-02-24
 */
public class StatusCode {
    /** 请求成功 */
    public static final int OK = 200;
    /** 创建成功 */
    public static final int CREATED = 201;
    /** 更新成功 */
    public static final int ACCEPTED = 202;
    /** 请求的地址不存在或者包含不支持的参数 */
    public static final int BAD_REQUEST = 400;
    /** 未授权 */
    public static final int UNAUTHORIZED = 401;
    /** 被禁止访问 */
    public static final int FORBIDDEN = 403;
    /** 请求的资源不存在 */
    public static final int NOT_FOUND = 404;
    /** 内部错误 */
    public static final int INTERNAL_SERVER_ERROR = 500;
}
