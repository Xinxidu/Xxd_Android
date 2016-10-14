package com.xinxidu.xxd.netWork;

import java.util.List;

/**
 * Created by limingquan on 2016/10/13.
 */
public class HotActivityBean {


    /**
     * currentPage : 1
     * pageSize : 15
     * resultList : [{"activeTime":"2016-10-10 10:02","content":"","createDate":"2016-10-10 10:02","deleteFlag":0,"id":17,"picUrl":"/static/upload/appupload/bg2.jpg","sortNo":111,"status":0,"title":"??3","type":2},{"activeTime":"2016-10-11 14:45","content":"","createDate":"2016-10-11 14:45","deleteFlag":0,"id":16,"picUrl":"/static/upload/appupload/bg2.jpg","sortNo":2,"status":0,"title":"??2","type":1},{"activeTime":"2016-10-11 14:45","content":"","createDate":"2016-10-11 14:45","deleteFlag":0,"id":21,"picUrl":"/static/upload/appupload/bg2.jpg","sortNo":2,"status":0,"title":"??5","type":1},{"activeTime":"2016-10-11 14:45","content":"","createDate":"2016-10-11 14:45","deleteFlag":0,"id":23,"picUrl":"/static/upload/appupload/bg2.jpg","sortNo":2,"status":0,"title":"??7","type":1},{"activeTime":"2016-10-11 14:45","content":"","createDate":"2016-10-11 14:45","deleteFlag":0,"id":25,"picUrl":"/static/upload/appupload/bg2.jpg","sortNo":2,"status":0,"title":"??9","type":1},{"activeTime":"2016-10-11 14:45","content":"","createDate":"2016-10-11 14:45","deleteFlag":0,"id":27,"picUrl":"/static/upload/appupload/bg2.jpg","sortNo":2,"status":0,"title":"??11","type":1},{"activeTime":"2016-10-11 14:45","content":"","createDate":"2016-10-11 14:45","deleteFlag":0,"id":29,"picUrl":"/static/upload/appupload/bg2.jpg","sortNo":2,"status":0,"title":"??13","type":1},{"activeTime":"2016-10-11 14:45","content":"","createDate":"2016-10-11 14:45","deleteFlag":0,"id":31,"picUrl":"/static/upload/appupload/bg2.jpg","sortNo":2,"status":0,"title":"??15","type":1},{"activeTime":"2016-10-11 14:45","content":"","createDate":"2016-10-11 14:45","deleteFlag":0,"id":33,"picUrl":"/static/upload/appupload/bg2.jpg","sortNo":2,"status":0,"title":"??17","type":1},{"activeTime":"2016-10-11 14:45","content":"","createDate":"2016-10-11 14:45","deleteFlag":0,"id":35,"picUrl":"/static/upload/appupload/bg2.jpg","sortNo":2,"status":0,"title":"??19","type":1},{"activeTime":"2016-10-11 14:45","content":"","createDate":"2016-10-11 14:45","deleteFlag":0,"id":37,"picUrl":"/static/upload/appupload/bg2.jpg","sortNo":2,"status":0,"title":"??21","type":1},{"activeTime":"2016-10-11 14:45","content":"","createDate":"2016-10-11 14:45","deleteFlag":0,"id":39,"picUrl":"/static/upload/appupload/bg2.jpg","sortNo":2,"status":0,"title":"??23","type":1},{"activeTime":"2016-10-10 10:01","content":"","createDate":"2016-10-10 10:01","deleteFlag":0,"id":15,"picUrl":"/static/upload/appupload/bg.jpg","sortNo":1,"status":1,"title":"??1","type":1},{"activeTime":"2016-10-10 10:01","content":"","createDate":"2016-10-10 10:01","deleteFlag":0,"id":20,"picUrl":"/static/upload/appupload/bg.jpg","sortNo":1,"status":1,"title":"??4","type":1},{"activeTime":"2016-10-10 10:01","content":"","createDate":"2016-10-10 10:01","deleteFlag":0,"id":22,"picUrl":"/static/upload/appupload/bg.jpg","sortNo":1,"status":1,"title":"??6","type":1}]
     * startLimit : 0
     * status :
     * totalCount : 23
     * totalPage : 2
     */

    public int currentPage;
    public int pageSize;
    public int startLimit;
    public String status;
    public int totalCount;
    public int totalPage;
    /**
     * activeTime : 2016-10-10 10:02
     * content :
     * createDate : 2016-10-10 10:02
     * deleteFlag : 0
     * id : 17
     * picUrl : /static/upload/appupload/bg2.jpg
     * sortNo : 111
     * status : 0
     * title : ??3
     * type : 2
     */

    public List<ResultListBean> resultList;

    public static class ResultListBean {
        public String activeTime;
        public String content;
        public String createDate;
        public int deleteFlag;
        public int id;
        public String picUrl;
        public int sortNo;
        public int status;
        public String title;
        public int type;
    }

    @Override
    public String toString() {
        return "SportBean{" +
                "currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                ", startLimit=" + startLimit +
                ", status='" + status + '\'' +
                ", totalCount=" + totalCount +
                ", totalPage=" + totalPage +
                ", resultList=" + resultList +
                '}';
    }
}
