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
package com.ryeeeeee.doubansdk4android.auth;

import com.ryeeeeee.doubansdk4android.auth.oauth.AccessTokenResponse;
import com.ryeeeeee.doubansdk4android.exception.DoubanException;

/**
 * 认证回调接口
 *
 * @author Ryeeeeee
 * @since 2015-01-24
 */
public interface IAuthListener {
    /**
     * 认证成功回调
     */
    public void onComplete(AccessTokenResponse bundle);

    /**
     * 认证出错回调
     */
    public void onError(DoubanException exception);

    /**
     * 取消认证回调
     */
    public void onCancel();

    /**
     * 无论认证成功或者失败都会回调
     */
    public void onFinish();
}
