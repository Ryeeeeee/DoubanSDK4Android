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
package com.ryeeeeee.doubansdk4android.api.movie;

import java.util.List;

/**
 * @author Ryeeeeee
 * @since 2015-02-28
 */
public class MovieList {
    private int start;
    private int count;
    private int total;
    private String query;
    private String tag;
    private List<SimpleSubject> subjects;

    public MovieList(int start, int count, int total, String query, String tag, List<SimpleSubject> subjects) {
        this.start = start;
        this.count = count;
        this.total = total;
        this.query = query;
        this.tag = tag;
        this.subjects = subjects;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public List<SimpleSubject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<SimpleSubject> subjects) {
        this.subjects = subjects;
    }

    @Override
    public String toString() {
        return "MovieList{" +
                "start=" + start +
                ", count=" + count +
                ", total=" + total +
                ", query='" + query + '\'' +
                ", tag='" + tag + '\'' +
                ", subjects=" + subjects +
                '}';
    }
}
