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
package com.ryeeeeee.doubansdk4android.util;

import android.util.Log;

/**
 * @author Ryeeeeee
 * @since 2015-01-23
 */
public class LogUtil {
    /** 是否开启调试 Log 输出 */
    private static boolean sLogEnabled = false;

    /**
     * 开关调试 Log 输出
     * @param enabled
     */
    public static void setLogEnabled(boolean enabled) {
        sLogEnabled = enabled;
    }

    public static void d(String tag, String message) {
        if (sLogEnabled) {
            Log.d(tag, message);
        }
    }

    public static void i(String tag, String message) {
        if (sLogEnabled) {
            Log.i(tag, message);
        }
    }

    public static void e(String tag, String message) {
        if (sLogEnabled) {
            Log.e(tag, message);
        }
    }

    public static void w(String tag, String message) {
        if (sLogEnabled) {
            Log.w(tag, message);
        }
    }

    public static void v(String tag, String message) {
        if (sLogEnabled) {
            Log.v(tag, message);
        }
    }

}
