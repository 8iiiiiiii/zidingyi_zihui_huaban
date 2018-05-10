package com.example.com.rk0510_demo;

import java.util.List;

/**
 * Created by 老赵的拯救者 on 2018/5/10.
 */

public class UserBean {
    /**
     * code : 200
     * msg : success
     * newslist : [{"ctime":"2017-10-28 08:11","title":"瑞士情侣印度惨遭群殴 路人不援助还拍照！","description":"搜狐国际","picUrl":"","url":"http://www.sohu.com/a/200747326_267106"},{"ctime":"2017-10-28 08:11","title":"美中情局雇凶？ 肯尼迪档案解密引爆\u201c新阴谋\u201d","description":"搜狐国际","picUrl":"","url":"http://www.sohu.com/a/200748913_162522"},{"ctime":"2017-10-28 08:12","title":"歪曲事实！韩教授称慰安妇是卖淫女被判有罪","description":"搜狐国际","picUrl":"","url":"http://www.sohu.com/a/200748596_162522"},{"ctime":"2017-10-28 08:12","title":"震撼！巨型座头鲸跃出水面与船只咫尺之隔(图)","description":"搜狐国际","picUrl":"","url":"http://www.sohu.com/a/200748928_162522"},{"ctime":"2017-10-29 09:44","title":"希腊年内发生多起邮件炸弹事件 一名男子涉嫌被捕","description":"搜狐国际","picUrl":"","url":"http://www.sohu.com/a/200940777_428290"},{"ctime":"2017-10-29 09:45","title":"英男子用白巧克力钓到31公斤英国史上最大鲤鱼","description":"搜狐国际","picUrl":"","url":"http://www.sohu.com/a/200930051_162522"},{"ctime":"2017-10-29 09:45","title":"索马里首都遭汽车炸弹袭击 至少23人死亡30人伤","description":"搜狐国际","picUrl":"","url":"http://www.sohu.com/a/200934613_123753"},{"ctime":"2017-10-29 09:46","title":"俄米-8直升机在挪威海域失联 俄派遣人员搜救","description":"搜狐国际","picUrl":"","url":"http://www.sohu.com/a/200938257_123753"},{"ctime":"2017-10-29 09:47","title":"日媒：安倍有意让全部阁僚及自民党高层继续任职","description":"搜狐国际","picUrl":"","url":"http://www.sohu.com/a/200935343_123753"},{"ctime":"2017-10-30 07:14","title":"埃塞俄比亚245人涉制造骚乱、煽动民族仇恨被捕","description":"搜狐国际","picUrl":"","url":"http://www.sohu.com/a/201076921_119562"}]
     */

    private int code;
    private String msg;
    private List<NewslistBean> newslist;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<NewslistBean> getNewslist() {
        return newslist;
    }

    public void setNewslist(List<NewslistBean> newslist) {
        this.newslist = newslist;
    }

    public static class NewslistBean {
        /**
         * ctime : 2017-10-28 08:11
         * title : 瑞士情侣印度惨遭群殴 路人不援助还拍照！
         * description : 搜狐国际
         * picUrl :
         * url : http://www.sohu.com/a/200747326_267106
         */

        private String ctime;
        private String title;
        private String description;
        private String picUrl;
        private String url;

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
