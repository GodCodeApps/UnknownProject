package com.lib_common.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Peng YanMing on 2018\8\8 0008
 */
public class ContentGson {

    /**
     * abstract : 8月2日，莱芜市健康扶贫工作现场推进会在钢城区颜庄中心卫生院召开。会议指出，2018年，全市卫生计生系统认真贯彻落实各级党委、政府关于脱贫攻坚的部署要求，凝心聚力，狠抓落实，扎实推进健康扶贫工作，取得了一定成效。
     * action_list : [{"action":1,"desc":"","extra":{}},{"action":3,"desc":"","extra":{}},{"action":7,"desc":"","extra":{}},{"action":9,"desc":"","extra":{}}]
     * aggr_type : 1
     * allow_download : false
     * article_sub_type : 0
     * article_type : 0
     * article_url : http://www.sdwsnews.com.cn/a/laiwu/zhengwu/2018/0806/20897.html
     * ban_comment : 0
     * behot_time : 1533714155
     * bury_count : 0
     * cell_flag : 43
     * cell_layout_style : 1
     * cell_type : 0
     * comment_count : 0
     * cursor : 1533714155000
     * digg_count : 0
     * display_url : http://toutiao.com/group/6586441406095557128/
     * filter_words : [{"id":"8:0","is_selected":false,"name":"看过了"},{"id":"9:1","is_selected":false,"name":"内容太水"},{"id":"5:9320753","is_selected":false,"name":"拉黑作者:山东卫生新闻网"},{"id":"2:162207727","is_selected":false,"name":"不想看:医界新闻"},{"id":"6:23247","is_selected":false,"name":"不想看:莱芜"}]
     * gallary_image_count : 2
     * group_id : 6586441406095557128
     * has_image : true
     * has_m3u8_video : 0
     * has_mp4_video : 0
     * has_video : false
     * hot : 0
     * ignore_web_transform : 1
     * is_subject : false
     * item_id : 6586441406095557128
     * item_version : 0
     * keywords : 医疗机构,钢城区,莱芜市,张洪君,颜庄中心
     * level : 0
     * log_pb : {"impr_id":"20180808154335010010023197923CA6"}
     * media_info : {"avatar_url":"http://p3-xg.pstatp.com/large/6cb000300f66b7f3986","media_id":6597366781,"name":"山东卫生新闻网","subcribed":0,"subscribed":0,"user_verified":false}
     * media_name : 山东卫生新闻网
     * middle_image : {"height":281,"uri":"list/pgc-image/1533525391295982d072f15","url":"http://p3-xg.pstatp.com/list/190x124/pgc-image/1533525391295982d072f15.webp","url_list":[{"url":"http://p3-xg.pstatp.com/list/190x124/pgc-image/1533525391295982d072f15.webp"},{"url":"http://p9-xg.pstatp.com/list/190x124/pgc-image/1533525391295982d072f15.webp"},{"url":"http://p9-xg.pstatp.com/list/190x124/pgc-image/1533525391295982d072f15.webp"}],"width":500}
     * need_client_impr_recycle : 1
     * preload_web : 1
     * publish_time : 1533525392
     * read_count : 3
     * rid : 20180808154335010010023197923CA6
     * share_count : 0
     * share_url : https://m.365yg.com/group/6586441406095557128/?iid=0&app=video_article&timestamp=1533714215
     * show_portrait : false
     * show_portrait_article : false
     * source : 山东卫生新闻网
     * source_icon_style : 1
     * source_open_url : sslocal://media_account?media_id=6597366781
     * tag : news_politics
     * tag_id : 6586441406095557128
     * tip : 0
     * title : 莱芜市健康扶贫工作现场推进会召开
     * url : http://www.sdwsnews.com.cn/a/laiwu/zhengwu/2018/0806/20897.html
     * user_info : {"avatar_url":"http://p3-xg.pstatp.com/thumb/6cb000300f66b7f3986","description":"服务山东卫生事业，传播健康理念，促进健康山东、健康中国建设。","name":"山东卫生新闻网","quality_author_info":{"description":"4765粉丝·4视频","is_quality_author":true},"user_id":6597366781,"user_verified":false}
     * user_repin : 0
     * user_verified : 0
     * verified_content :
     * video_style : 0
     */

    @SerializedName("abstract")
    private String abstractX;
    private int aggr_type;
    private boolean allow_download;
    private int article_sub_type;
    private int article_type;
    private String article_url;
    private int ban_comment;
    private int behot_time;
    private int bury_count;
    private int cell_flag;
    private int cell_layout_style;
    private int cell_type;
    private int comment_count;
    private long cursor;
    private int digg_count;
    private String display_url;
    private int gallary_image_count;
    private long group_id;
    private boolean has_image;
    private int has_m3u8_video;
    private int has_mp4_video;
    private boolean has_video;
    private int hot;
    private int ignore_web_transform;
    private boolean is_subject;
    private long item_id;
    private int item_version;
    private String keywords;
    private int level;
    private LogPbBean log_pb;
    private MediaInfoBean media_info;
    private String media_name;
    private MiddleImageBean middle_image;
    private int need_client_impr_recycle;
    private int preload_web;
    private int publish_time;
    private int read_count;
    private String rid;
    private int share_count;
    private String share_url;
    private boolean show_portrait;
    private boolean show_portrait_article;
    private String source;
    private int source_icon_style;
    private String source_open_url;
    private String tag;
    private long tag_id;
    private int tip;
    private String title;
    private String url;
    private UserInfoBean user_info;
    private int user_repin;
    private int user_verified;
    private String verified_content;
    private int video_style;
    private List<ActionListBean> action_list;
    private List<FilterWordsBean> filter_words;
    public String getAbstractX() {
        return abstractX;
    }

    public void setAbstractX(String abstractX) {
        this.abstractX = abstractX;
    }

    public int getAggr_type() {
        return aggr_type;
    }

    public void setAggr_type(int aggr_type) {
        this.aggr_type = aggr_type;
    }

    public boolean isAllow_download() {
        return allow_download;
    }

    public void setAllow_download(boolean allow_download) {
        this.allow_download = allow_download;
    }

    public int getArticle_sub_type() {
        return article_sub_type;
    }

    public void setArticle_sub_type(int article_sub_type) {
        this.article_sub_type = article_sub_type;
    }

    public int getArticle_type() {
        return article_type;
    }

    public void setArticle_type(int article_type) {
        this.article_type = article_type;
    }

    public String getArticle_url() {
        return article_url;
    }

    public void setArticle_url(String article_url) {
        this.article_url = article_url;
    }

    public int getBan_comment() {
        return ban_comment;
    }

    public void setBan_comment(int ban_comment) {
        this.ban_comment = ban_comment;
    }

    public int getBehot_time() {
        return behot_time;
    }

    public void setBehot_time(int behot_time) {
        this.behot_time = behot_time;
    }

    public int getBury_count() {
        return bury_count;
    }

    public void setBury_count(int bury_count) {
        this.bury_count = bury_count;
    }

    public int getCell_flag() {
        return cell_flag;
    }

    public void setCell_flag(int cell_flag) {
        this.cell_flag = cell_flag;
    }

    public int getCell_layout_style() {
        return cell_layout_style;
    }

    public void setCell_layout_style(int cell_layout_style) {
        this.cell_layout_style = cell_layout_style;
    }

    public int getCell_type() {
        return cell_type;
    }

    public void setCell_type(int cell_type) {
        this.cell_type = cell_type;
    }

    public int getComment_count() {
        return comment_count;
    }

    public void setComment_count(int comment_count) {
        this.comment_count = comment_count;
    }

    public long getCursor() {
        return cursor;
    }

    public void setCursor(long cursor) {
        this.cursor = cursor;
    }

    public int getDigg_count() {
        return digg_count;
    }

    public void setDigg_count(int digg_count) {
        this.digg_count = digg_count;
    }

    public String getDisplay_url() {
        return display_url;
    }

    public void setDisplay_url(String display_url) {
        this.display_url = display_url;
    }

    public int getGallary_image_count() {
        return gallary_image_count;
    }

    public void setGallary_image_count(int gallary_image_count) {
        this.gallary_image_count = gallary_image_count;
    }

    public long getGroup_id() {
        return group_id;
    }

    public void setGroup_id(long group_id) {
        this.group_id = group_id;
    }

    public boolean isHas_image() {
        return has_image;
    }

    public void setHas_image(boolean has_image) {
        this.has_image = has_image;
    }

    public int getHas_m3u8_video() {
        return has_m3u8_video;
    }

    public void setHas_m3u8_video(int has_m3u8_video) {
        this.has_m3u8_video = has_m3u8_video;
    }

    public int getHas_mp4_video() {
        return has_mp4_video;
    }

    public void setHas_mp4_video(int has_mp4_video) {
        this.has_mp4_video = has_mp4_video;
    }

    public boolean isHas_video() {
        return has_video;
    }

    public void setHas_video(boolean has_video) {
        this.has_video = has_video;
    }

    public int getHot() {
        return hot;
    }

    public void setHot(int hot) {
        this.hot = hot;
    }

    public int getIgnore_web_transform() {
        return ignore_web_transform;
    }

    public void setIgnore_web_transform(int ignore_web_transform) {
        this.ignore_web_transform = ignore_web_transform;
    }

    public boolean isIs_subject() {
        return is_subject;
    }

    public void setIs_subject(boolean is_subject) {
        this.is_subject = is_subject;
    }

    public long getItem_id() {
        return item_id;
    }

    public void setItem_id(long item_id) {
        this.item_id = item_id;
    }

    public int getItem_version() {
        return item_version;
    }

    public void setItem_version(int item_version) {
        this.item_version = item_version;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public LogPbBean getLog_pb() {
        return log_pb;
    }

    public void setLog_pb(LogPbBean log_pb) {
        this.log_pb = log_pb;
    }

    public MediaInfoBean getMedia_info() {
        return media_info;
    }

    public void setMedia_info(MediaInfoBean media_info) {
        this.media_info = media_info;
    }

    public String getMedia_name() {
        return media_name;
    }

    public void setMedia_name(String media_name) {
        this.media_name = media_name;
    }

    public MiddleImageBean getMiddle_image() {
        return middle_image;
    }

    public void setMiddle_image(MiddleImageBean middle_image) {
        this.middle_image = middle_image;
    }

    public int getNeed_client_impr_recycle() {
        return need_client_impr_recycle;
    }

    public void setNeed_client_impr_recycle(int need_client_impr_recycle) {
        this.need_client_impr_recycle = need_client_impr_recycle;
    }

    public int getPreload_web() {
        return preload_web;
    }

    public void setPreload_web(int preload_web) {
        this.preload_web = preload_web;
    }

    public int getPublish_time() {
        return publish_time;
    }

    public void setPublish_time(int publish_time) {
        this.publish_time = publish_time;
    }

    public int getRead_count() {
        return read_count;
    }

    public void setRead_count(int read_count) {
        this.read_count = read_count;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public int getShare_count() {
        return share_count;
    }

    public void setShare_count(int share_count) {
        this.share_count = share_count;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public boolean isShow_portrait() {
        return show_portrait;
    }

    public void setShow_portrait(boolean show_portrait) {
        this.show_portrait = show_portrait;
    }

    public boolean isShow_portrait_article() {
        return show_portrait_article;
    }

    public void setShow_portrait_article(boolean show_portrait_article) {
        this.show_portrait_article = show_portrait_article;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int getSource_icon_style() {
        return source_icon_style;
    }

    public void setSource_icon_style(int source_icon_style) {
        this.source_icon_style = source_icon_style;
    }

    public String getSource_open_url() {
        return source_open_url;
    }

    public void setSource_open_url(String source_open_url) {
        this.source_open_url = source_open_url;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public long getTag_id() {
        return tag_id;
    }

    public void setTag_id(long tag_id) {
        this.tag_id = tag_id;
    }

    public int getTip() {
        return tip;
    }

    public void setTip(int tip) {
        this.tip = tip;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public UserInfoBean getUser_info() {
        return user_info;
    }

    public void setUser_info(UserInfoBean user_info) {
        this.user_info = user_info;
    }

    public int getUser_repin() {
        return user_repin;
    }

    public void setUser_repin(int user_repin) {
        this.user_repin = user_repin;
    }

    public int getUser_verified() {
        return user_verified;
    }

    public void setUser_verified(int user_verified) {
        this.user_verified = user_verified;
    }

    public String getVerified_content() {
        return verified_content;
    }

    public void setVerified_content(String verified_content) {
        this.verified_content = verified_content;
    }

    public int getVideo_style() {
        return video_style;
    }

    public void setVideo_style(int video_style) {
        this.video_style = video_style;
    }

    public List<ActionListBean> getAction_list() {
        return action_list;
    }

    public void setAction_list(List<ActionListBean> action_list) {
        this.action_list = action_list;
    }

    public List<FilterWordsBean> getFilter_words() {
        return filter_words;
    }

    public void setFilter_words(List<FilterWordsBean> filter_words) {
        this.filter_words = filter_words;
    }

    public static class LogPbBean {
        /**
         * impr_id : 20180808154335010010023197923CA6
         */

        private String impr_id;

        public String getImpr_id() {
            return impr_id;
        }

        public void setImpr_id(String impr_id) {
            this.impr_id = impr_id;
        }
    }

    public static class MediaInfoBean {
        /**
         * avatar_url : http://p3-xg.pstatp.com/large/6cb000300f66b7f3986
         * media_id : 6597366781
         * name : 山东卫生新闻网
         * subcribed : 0
         * subscribed : 0
         * user_verified : false
         */

        private String avatar_url;
        private long media_id;
        private String name;
        private int subcribed;
        private int subscribed;
        private boolean user_verified;

        public String getAvatar_url() {
            return avatar_url;
        }

        public void setAvatar_url(String avatar_url) {
            this.avatar_url = avatar_url;
        }

        public long getMedia_id() {
            return media_id;
        }

        public void setMedia_id(long media_id) {
            this.media_id = media_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getSubcribed() {
            return subcribed;
        }

        public void setSubcribed(int subcribed) {
            this.subcribed = subcribed;
        }

        public int getSubscribed() {
            return subscribed;
        }

        public void setSubscribed(int subscribed) {
            this.subscribed = subscribed;
        }

        public boolean isUser_verified() {
            return user_verified;
        }

        public void setUser_verified(boolean user_verified) {
            this.user_verified = user_verified;
        }
    }

    public static class MiddleImageBean {
        /**
         * height : 281
         * uri : list/pgc-image/1533525391295982d072f15
         * url : http://p3-xg.pstatp.com/list/190x124/pgc-image/1533525391295982d072f15.webp
         * url_list : [{"url":"http://p3-xg.pstatp.com/list/190x124/pgc-image/1533525391295982d072f15.webp"},{"url":"http://p9-xg.pstatp.com/list/190x124/pgc-image/1533525391295982d072f15.webp"},{"url":"http://p9-xg.pstatp.com/list/190x124/pgc-image/1533525391295982d072f15.webp"}]
         * width : 500
         */

        private int height;
        private String uri;
        private String url;
        private int width;
        private List<UrlListBean> url_list;

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public String getUri() {
            return uri;
        }

        public void setUri(String uri) {
            this.uri = uri;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public List<UrlListBean> getUrl_list() {
            return url_list;
        }

        public void setUrl_list(List<UrlListBean> url_list) {
            this.url_list = url_list;
        }

        public static class UrlListBean {
            /**
             * url : http://p3-xg.pstatp.com/list/190x124/pgc-image/1533525391295982d072f15.webp
             */

            private String url;

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }

    public static class UserInfoBean {
        /**
         * avatar_url : http://p3-xg.pstatp.com/thumb/6cb000300f66b7f3986
         * description : 服务山东卫生事业，传播健康理念，促进健康山东、健康中国建设。
         * name : 山东卫生新闻网
         * quality_author_info : {"description":"4765粉丝·4视频","is_quality_author":true}
         * user_id : 6597366781
         * user_verified : false
         */

        private String avatar_url;
        private String description;
        private String name;
        private QualityAuthorInfoBean quality_author_info;
        private long user_id;
        private boolean user_verified;

        public String getAvatar_url() {
            return avatar_url;
        }

        public void setAvatar_url(String avatar_url) {
            this.avatar_url = avatar_url;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public QualityAuthorInfoBean getQuality_author_info() {
            return quality_author_info;
        }

        public void setQuality_author_info(QualityAuthorInfoBean quality_author_info) {
            this.quality_author_info = quality_author_info;
        }

        public long getUser_id() {
            return user_id;
        }

        public void setUser_id(long user_id) {
            this.user_id = user_id;
        }

        public boolean isUser_verified() {
            return user_verified;
        }

        public void setUser_verified(boolean user_verified) {
            this.user_verified = user_verified;
        }

        public static class QualityAuthorInfoBean {
            /**
             * description : 4765粉丝·4视频
             * is_quality_author : true
             */

            private String description;
            private boolean is_quality_author;

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public boolean isIs_quality_author() {
                return is_quality_author;
            }

            public void setIs_quality_author(boolean is_quality_author) {
                this.is_quality_author = is_quality_author;
            }
        }
    }

    public static class ActionListBean {
        /**
         * action : 1
         * desc :
         * extra : {}
         */

        private int action;
        private String desc;
        private ExtraBean extra;

        public int getAction() {
            return action;
        }

        public void setAction(int action) {
            this.action = action;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public ExtraBean getExtra() {
            return extra;
        }

        public void setExtra(ExtraBean extra) {
            this.extra = extra;
        }

        public static class ExtraBean {
        }
    }

    public static class FilterWordsBean {
        /**
         * id : 8:0
         * is_selected : false
         * name : 看过了
         */

        private String id;
        private boolean is_selected;
        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public boolean isIs_selected() {
            return is_selected;
        }

        public void setIs_selected(boolean is_selected) {
            this.is_selected = is_selected;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
