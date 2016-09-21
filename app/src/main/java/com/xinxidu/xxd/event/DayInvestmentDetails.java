package com.xinxidu.xxd.event;

/**
 * Created by limingquan on 2016/9/20.
 * 每日投资策略详情
 */
public class DayInvestmentDetails {
    /**
     * Pubdate : 2016-05-16  10:33:57
     * Senddate : 2016-05-16  10:37:13
     * Typeid : 18
     * Typeid2 : null
     * Sortrank : 1463366037
     * Click : 60
     * Shorttitle :
     * Litpic : /uploads//allimg/160516/1-16051610345C00-lp.jpg
     * Redirecturl : null
     * Body : 据能源情报公司Genscape数据，截至5月3日，WTI原油交
     * Keywords : 油价,摆脱,盘整,格局,横空出世,再攀,新高,
     * Id : 11811
     * Title : 油价摆脱盘整格局，横空出世再攀新高
     * Description : 西都投资： 据能源情报公司Genscape数据，截至5月3日，WTI原油交割地库欣地区库存超过7000万桶，利用率升至仅略低于80%水平，为有纪录来最高。周五，美油自上个交易日创下的半年高点回落，收跌1.1%。 行情回顾： 美国原油6月期货上周(5月9日至5月13日)收盘于
     */

    private int flag;

    private DataBean data;
    private String msg;

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        public String Pubdate;
        public String Senddate;
        public int Typeid;
        public Object Typeid2;
        public int Sortrank;
        public int Click;
        public String Shorttitle;
        public String Litpic;
        public Object Redirecturl;
        public String Body;
        public String Keywords;
        public int Id;
        public String Title;

        public String getDescription() {
            return Description;
        }

        public void setDescription(String description) {
            Description = description;
        }

        public String getPubdate() {
            return Pubdate;
        }

        public void setPubdate(String pubdate) {
            Pubdate = pubdate;
        }

        public String getSenddate() {
            return Senddate;
        }

        public void setSenddate(String senddate) {
            Senddate = senddate;
        }

        public int getTypeid() {
            return Typeid;
        }

        public void setTypeid(int typeid) {
            Typeid = typeid;
        }

        public Object getTypeid2() {
            return Typeid2;
        }

        public void setTypeid2(Object typeid2) {
            Typeid2 = typeid2;
        }

        public int getSortrank() {
            return Sortrank;
        }

        public void setSortrank(int sortrank) {
            Sortrank = sortrank;
        }

        public int getClick() {
            return Click;
        }

        public void setClick(int click) {
            Click = click;
        }

        public String getShorttitle() {
            return Shorttitle;
        }

        public void setShorttitle(String shorttitle) {
            Shorttitle = shorttitle;
        }

        public String getLitpic() {
            return Litpic;
        }

        public void setLitpic(String litpic) {
            Litpic = litpic;
        }

        public Object getRedirecturl() {
            return Redirecturl;
        }

        public void setRedirecturl(Object redirecturl) {
            Redirecturl = redirecturl;
        }

        public String getBody() {
            return Body;
        }

        public void setBody(String body) {
            Body = body;
        }

        public String getKeywords() {
            return Keywords;
        }

        public void setKeywords(String keywords) {
            Keywords = keywords;
        }

        public int getId() {
            return Id;
        }

        public void setId(int id) {
            Id = id;
        }

        public String getTitle() {
            return Title;
        }

        public void setTitle(String title) {
            Title = title;
        }

        public String Description;
    }
}