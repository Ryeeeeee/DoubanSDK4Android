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
package com.ryeeeeee.doubansdk4android.api.shuo;

import java.util.List;

/**
 * @author Ryeeeeee
 * @since 2015-02-20
 */
public class Shuo {

    private String category;
    private int reshared_count;
    private int layout;
    private boolean from_subscription;
    private String title;
    private String text;
    private String created_at;
    private int can_reply;
    private boolean liked;
    private List<Attachment> attachments;
    private Entity entities;
    private int like_count;
    private int comments_count;
    private Source source;
    private boolean is_follow;
    private boolean has_photo;
    private int type;
    private int id;
    private User user;

    public Shuo(String category, int reshared_count, int layout, boolean from_subscription,
                String title, String text, String created_at, int can_reply, boolean liked,
                List<Attachment> attachments, Entity entities, int like_count, int comments_count,
                Source source, boolean is_follow, boolean has_photo, int type, int id, User user) {
        this.category = category;
        this.reshared_count = reshared_count;
        this.layout = layout;
        this.from_subscription = from_subscription;
        this.title = title;
        this.text = text;
        this.created_at = created_at;
        this.can_reply = can_reply;
        this.liked = liked;
        this.attachments = attachments;
        this.entities = entities;
        this.like_count = like_count;
        this.comments_count = comments_count;
        this.source = source;
        this.is_follow = is_follow;
        this.has_photo = has_photo;
        this.type = type;
        this.id = id;
        this.user = user;
    }

    public static class User {
        private String uid;
        private String description;
        private String large_avatar;
        private String original_site_url;
        private String small_avatar;
        private int original_site_id;
        private String type;
        private String id;
        private String screen_name;

        public User(String uid, String description, String large_avatar, String original_site_url,
                     String small_avatar, int original_site_id, String type, String id,
                     String screen_name) {
            this.uid = uid;
            this.description = description;
            this.large_avatar = large_avatar;
            this.original_site_url = original_site_url;
            this.small_avatar = small_avatar;
            this.original_site_id = original_site_id;
            this.type = type;
            this.id = id;
            this.screen_name = screen_name;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getLarge_avatar() {
            return large_avatar;
        }

        public void setLarge_avatar(String large_avatar) {
            this.large_avatar = large_avatar;
        }

        public String getOriginal_site_url() {
            return original_site_url;
        }

        public void setOriginal_site_url(String original_site_url) {
            this.original_site_url = original_site_url;
        }

        public String getSmall_avatar() {
            return small_avatar;
        }

        public void setSmall_avatar(String small_avatar) {
            this.small_avatar = small_avatar;
        }

        public int getOriginal_site_id() {
            return original_site_id;
        }

        public void setOriginal_site_id(int original_site_id) {
            this.original_site_id = original_site_id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getScreen_name() {
            return screen_name;
        }

        public void setScreen_name(String screen_name) {
            this.screen_name = screen_name;
        }
    }

    public static class Source  {
        public Source() {
        }
    }

    public static class Entity {
        private List<UserMention> user_mentions;
        private List<Topic> topics;
        private List<Url> urls;

        public Entity(List<UserMention> user_mentions, List<Topic> topics, List<Url> urls) {
            this.user_mentions = user_mentions;
            this.topics = topics;
            this.urls = urls;
        }

        public static class UserMention {
            public UserMention() {
            }
        }

        public static class Topic {
            public Topic() {
            }
        }

        public static class Url {
            public Url() {
            }
        }

        public List<UserMention> getUser_mentions() {
            return user_mentions;
        }

        public void setUser_mentions(List<UserMention> user_mentions) {
            this.user_mentions = user_mentions;
        }

        public List<Topic> getTopics() {
            return topics;
        }

        public void setTopics(List<Topic> topics) {
            this.topics = topics;
        }

        public List<Url> getUrls() {
            return urls;
        }

        public void setUrls(List<Url> urls) {
            this.urls = urls;
        }
    }

    public static class Attachment {
        private String description;
        private String title;
        private List<Media> media;
        private Property properties;
        private String caption;
        private String href;
        private String type;
        private String expaned_href;

        public Attachment(String description, String title, List<Media> media, Property properties,
                          String caption, String href, String type, String expaned_href) {
            this.description = description;
            this.title = title;
            this.media = media;
            this.properties = properties;
            this.caption = caption;
            this.href = href;
            this.type = type;
            this.expaned_href = expaned_href;
        }

        public static class Property {
            public Property() {
            }
        }

        public static class Media {
            private String src;
            private Size sizes;
            private String secret_pid;
            private String original_src;
            private String href;
            private String type;
            private boolean is_animated;

            public Media(String src, Size sizes, String secret_pid, String original_src,
                         String href, String type, boolean is_animated) {
                this.src = src;
                this.sizes = sizes;
                this.secret_pid = secret_pid;
                this.original_src = original_src;
                this.href = href;
                this.type = type;
                this.is_animated = is_animated;
            }

            public static class Size {
                private List<Integer> small;
                private List<Integer> raw;
                private List<Integer> median;
                private List<Integer> ismall;

                public Size(List<Integer> small, List<Integer> raw, List<Integer> median,
                            List<Integer> ismall) {
                    this.small = small;
                    this.raw = raw;
                    this.median = median;
                    this.ismall = ismall;
                }

                public List<Integer> getSmall() {
                    return small;
                }

                public void setSmall(List<Integer> small) {
                    this.small = small;
                }

                public List<Integer> getRaw() {
                    return raw;
                }

                public void setRaw(List<Integer> raw) {
                    this.raw = raw;
                }

                public List<Integer> getMedian() {
                    return median;
                }

                public void setMedian(List<Integer> median) {
                    this.median = median;
                }

                public List<Integer> getIsmall() {
                    return ismall;
                }

                public void setIsmall(List<Integer> ismall) {
                    this.ismall = ismall;
                }
            }

            public String getSrc() {
                return src;
            }

            public void setSrc(String src) {
                this.src = src;
            }

            public Size getSizes() {
                return sizes;
            }

            public void setSizes(Size sizes) {
                this.sizes = sizes;
            }

            public String getSecret_pid() {
                return secret_pid;
            }

            public void setSecret_pid(String secret_pid) {
                this.secret_pid = secret_pid;
            }

            public String getOriginal_src() {
                return original_src;
            }

            public void setOriginal_src(String original_src) {
                this.original_src = original_src;
            }

            public String getHref() {
                return href;
            }

            public void setHref(String href) {
                this.href = href;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public boolean isIs_animated() {
                return is_animated;
            }

            public void setIs_animated(boolean is_animated) {
                this.is_animated = is_animated;
            }
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<Media> getMedia() {
            return media;
        }

        public void setMedia(List<Media> media) {
            this.media = media;
        }

        public Property getProperties() {
            return properties;
        }

        public void setProperties(Property properties) {
            this.properties = properties;
        }

        public String getCaption() {
            return caption;
        }

        public void setCaption(String caption) {
            this.caption = caption;
        }

        public String getHref() {
            return href;
        }

        public void setHref(String href) {
            this.href = href;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getExpaned_href() {
            return expaned_href;
        }

        public void setExpaned_href(String expaned_href) {
            this.expaned_href = expaned_href;
        }
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getReshared_count() {
        return reshared_count;
    }

    public void setReshared_count(int reshared_count) {
        this.reshared_count = reshared_count;
    }

    public int getLayout() {
        return layout;
    }

    public void setLayout(int layout) {
        this.layout = layout;
    }

    public boolean isFrom_subscription() {
        return from_subscription;
    }

    public void setFrom_subscription(boolean from_subscription) {
        this.from_subscription = from_subscription;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public int getCan_reply() {
        return can_reply;
    }

    public void setCan_reply(int can_reply) {
        this.can_reply = can_reply;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    public Entity getEntities() {
        return entities;
    }

    public void setEntities(Entity entities) {
        this.entities = entities;
    }

    public int getLike_count() {
        return like_count;
    }

    public void setLike_count(int like_count) {
        this.like_count = like_count;
    }

    public int getComments_count() {
        return comments_count;
    }

    public void setComments_count(int comments_count) {
        this.comments_count = comments_count;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public boolean isIs_follow() {
        return is_follow;
    }

    public void setIs_follow(boolean is_follow) {
        this.is_follow = is_follow;
    }

    public boolean isHas_photo() {
        return has_photo;
    }

    public void setHas_photo(boolean has_photo) {
        this.has_photo = has_photo;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
