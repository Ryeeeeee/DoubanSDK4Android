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
 * @since 2015-02-24
 */
public class Subject {
    /** 评分 */
    private Rating rating;
    /** 影评数量 */
    private int reviews_count;
    /** 想看人数 */
    private int wish_count;
    /** 看过人数 */
    private int collect_count;
    /** 豆瓣小站 */
    private String douban_site;
    /** 年代 */
    private String year;
    /** 电影海报图 */
    private Image images;
    /** 条目页URL */
    private String alt;
    /** 条目 id */
    private String id;
    /** 移动版条目页URL */
    private String mobile_url;
    /** 中文名 */
    private String title;
    /** 在看人数，如果是电视剧，默认值为0，如果是电影值为 null */
    private String do_count;
    /** 总季数 (tv only) */
    private String seasons_count;
    /** 影讯页URL (movie only) */
    private String schedule_url;
    /** 当前季的集数 (tv only) */
    private String episodes_count;
    /** 影片类型，最多提供3个 */
    private List<String> genres;
    /** 制片国家/地区 */
    private List<String> countries;
    /** 主演 */
    private List<SimpleCelebrity> casts;
    /** 当前季数 (tv only) */
    private String current_season;
    /** 原名 */
    private String original_title;
    /** 简介 */
    private String summary;
    /** 条目分类, movie 或者 tv */
    private String subtype;
    /** 导演 */
    private List<SimpleCelebrity> directors;
    /** 短评数量 */
    private int comments_count;
    /** 评分人数 */
    private int ratings_count;
    /** 又名 */
    private List<String> aka;

    public Subject(Rating rating, int reviews_count, int wish_count, int collect_count,
                   String douban_site, String year, Image images, String alt, String id,
                   String mobile_url, String title, String do_count, String seasons_count,
                   String schedule_url, String episodes_count, List<String> genres,
                   List<String> countries, List<SimpleCelebrity> casts, String current_season,
                   String original_title, String summary, String subtype,
                   List<SimpleCelebrity> directors, int comments_count, int ratings_count,
                   List<String> aka) {
        this.rating = rating;
        this.reviews_count = reviews_count;
        this.wish_count = wish_count;
        this.collect_count = collect_count;
        this.douban_site = douban_site;
        this.year = year;
        this.images = images;
        this.alt = alt;
        this.id = id;
        this.mobile_url = mobile_url;
        this.title = title;
        this.do_count = do_count;
        this.seasons_count = seasons_count;
        this.schedule_url = schedule_url;
        this.episodes_count = episodes_count;
        this.genres = genres;
        this.countries = countries;
        this.casts = casts;
        this.current_season = current_season;
        this.original_title = original_title;
        this.summary = summary;
        this.subtype = subtype;
        this.directors = directors;
        this.comments_count = comments_count;
        this.ratings_count = ratings_count;
        this.aka = aka;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public int getReviews_count() {
        return reviews_count;
    }

    public void setReviews_count(int reviews_count) {
        this.reviews_count = reviews_count;
    }

    public int getWish_count() {
        return wish_count;
    }

    public void setWish_count(int wish_count) {
        this.wish_count = wish_count;
    }

    public int getCollect_count() {
        return collect_count;
    }

    public void setCollect_count(int collect_count) {
        this.collect_count = collect_count;
    }

    public String getDouban_site() {
        return douban_site;
    }

    public void setDouban_site(String douban_site) {
        this.douban_site = douban_site;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Image getImages() {
        return images;
    }

    public void setImages(Image images) {
        this.images = images;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMobile_url() {
        return mobile_url;
    }

    public void setMobile_url(String mobile_url) {
        this.mobile_url = mobile_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDo_count() {
        return do_count;
    }

    public void setDo_count(String do_count) {
        this.do_count = do_count;
    }

    public String getSeasons_count() {
        return seasons_count;
    }

    public void setSeasons_count(String seasons_count) {
        this.seasons_count = seasons_count;
    }

    public String getSchedule_url() {
        return schedule_url;
    }

    public void setSchedule_url(String schedule_url) {
        this.schedule_url = schedule_url;
    }

    public String getEpisodes_count() {
        return episodes_count;
    }

    public void setEpisodes_count(String episodes_count) {
        this.episodes_count = episodes_count;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public List<String> getCountries() {
        return countries;
    }

    public void setCountries(List<String> countries) {
        this.countries = countries;
    }

    public List<SimpleCelebrity> getCasts() {
        return casts;
    }

    public void setCasts(List<SimpleCelebrity> casts) {
        this.casts = casts;
    }

    public String getCurrent_season() {
        return current_season;
    }

    public void setCurrent_season(String current_season) {
        this.current_season = current_season;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public List<SimpleCelebrity> getDirectors() {
        return directors;
    }

    public void setDirectors(List<SimpleCelebrity> directors) {
        this.directors = directors;
    }

    public int getComments_count() {
        return comments_count;
    }

    public void setComments_count(int comments_count) {
        this.comments_count = comments_count;
    }

    public int getRatings_count() {
        return ratings_count;
    }

    public void setRatings_count(int ratings_count) {
        this.ratings_count = ratings_count;
    }

    public List<String> getAka() {
        return aka;
    }

    public void setAka(List<String> aka) {
        this.aka = aka;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "rating=" + rating +
                ", reviews_count=" + reviews_count +
                ", wish_count=" + wish_count +
                ", collect_count=" + collect_count +
                ", douban_site='" + douban_site + '\'' +
                ", year='" + year + '\'' +
                ", images=" + images +
                ", alt='" + alt + '\'' +
                ", id='" + id + '\'' +
                ", mobile_url='" + mobile_url + '\'' +
                ", title='" + title + '\'' +
                ", do_count='" + do_count + '\'' +
                ", seasons_count='" + seasons_count + '\'' +
                ", schedule_url='" + schedule_url + '\'' +
                ", episodes_count='" + episodes_count + '\'' +
                ", genres=" + genres +
                ", countries=" + countries +
                ", casts=" + casts +
                ", current_season='" + current_season + '\'' +
                ", original_title='" + original_title + '\'' +
                ", summary='" + summary + '\'' +
                ", subtype='" + subtype + '\'' +
                ", directors=" + directors +
                ", comments_count=" + comments_count +
                ", ratings_count=" + ratings_count +
                ", aka=" + aka +
                '}';
    }
}
