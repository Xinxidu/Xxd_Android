package com.xinxidu.xxd.netWork;

import java.util.List;

/**
 * Created by Administrator on 2016/10/1.
 */
public class XiduNewsBean {
    /**
     data:
     flag : 1
     indexPage : 1
     msg : 数据接收成功
     sumPage ： 1
     *
     */
    public int flag;
    public int indexPage;
    public String msg;
    public int sumPage;
    /*
    * Click : 152
    * Id : 11913
    * Keywords : 关于,端午节,正常,开市,通知,
    * Pubdate : 2016-06-07  15:56:14
    * Senddate : 2016-06-07  15:57:03
    * Title : 关于端午节正常开市的通知
    * Typeid : 30
    * */
    public List<ResultListBean> data;
    public static class ResultListBean {
        public String Title;
        public String Senddate;
        public String Keywords;
        public String Id;
    }
}
