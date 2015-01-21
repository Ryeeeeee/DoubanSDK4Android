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
package com.ryeeeeee.doubansdk4android.codestyle;

/**
 * 类的大体描述
 *
 * @author Ryeeeeee
 * @since 2015-01-22
 */
public class SampleCode {

    /** public 常量注释 */
    public static final String TAG = "Ryeeeeee";

    /** private 常量注释 */
    private static final int ERROR_CODE = 100;

    /** protected 成员变量注释 */
    protected Object mProtectedObject;

    /** private 的成员变量 */
    private Object mPrivatedObject;

    /**
     * 多行
     * 注释
     */
    private Object mMultiLinesComment;

    /**
     * public 方法描述
     * @param intParam      参数1描述
     * @param floatParam    参数2描述
     * @param stringParam   参数XX描述
     * @return              返回值描述
     */
    public String doPublicFunction(int intParam, float floatParam, String stringParam) {
        // statement
        return null;
    }

    /**
     * 保护方法描述
     */
    protected void doProtectedFunction() {
        // statement
    }

    /**
     * 私有方法描述
     *
     * @param intParam    参数1描述
     * @param floatParam    参数2描述
     */
    private void doPrivateFunction(int intParam, float floatParam) {
        // statement
    }
}
