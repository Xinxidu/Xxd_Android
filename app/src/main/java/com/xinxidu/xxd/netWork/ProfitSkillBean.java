package com.xinxidu.xxd.netWork;

import java.util.List;

/**
 * Created by Administrator on 2016/10/1.
 */
public class ProfitSkillBean {

    /*
   *
   flag状态码:1表示——成功，2表示——参数有误，3表示——系统出错
   indexPage:返回的是当前的页数
   sumPage:返回的是总页数
   msg:数据接收成功
   data:
   * */
    public int flag;
    public int indexPage;
    public int sumPage;
    public String msg;

    /**
     * Click : 61
     * Description : 据知情人表示，沙特阿拉伯在上次多哈石油产量冻结会议失败之后，准备将利用本周的OPEC会议来修复与其它产油国之间的关系。 据知情人士透露，上个月接任沙特阿拉伯石油部长将向其它产油国重新保证不会令其石油产出大量涌向国际原油市场，此外，消息人士同时指
     * Id : 11911
     * Keywords : 沙特,利用,OPEC,会议,加强团结,知情人,表示,沙特阿拉
     * Pubdate : 2016-06-07  15:32:44
     * Senddate : 2016-06-07  15:35:15
     * Title : 沙特或利用OPEC会议加强团结
     * Typeid : 7
     * Typeid2 : 0
     */
    public List<ResultListBean> data;

    public static class ResultListBean {
        public String Body;
        public int Click;
        public String Description;
        public String Title;
        public String Senddate;
        public String Keywords;
        public String Id;

    }

}
