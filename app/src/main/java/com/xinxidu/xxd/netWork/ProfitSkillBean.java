package com.xinxidu.xxd.netWork;

/**
 * Created by Administrator on 2016/10/1.
 */
public class ProfitSkillBean {

    /*
   *
   *(int )    id;//编号
   (int )    typeid;//类型编号
   (String)  typeid2;//无用
   (int )    sortrank;//排序
   (int )    click;//点击量
   (String)  title;//标题
   (String)  shorttitle;//短标题
   (String)  litpic;//图片路径
   (String)  pubdate;//出版时间
   (String)  senddate;//发布时间
   (String)  keywords;//关键字
   (String)  description;//详情描述
   (String)  body;//详细正文
   flag状态码:1表示——成功，2表示——参数有误，3表示——系统出错
   indexPage:返回的是当前的页数
   sumPage:返回的是总页数
   * */
    public String Title;
    public String Senddate;
    public String Keywords;
    public String Id;

    public String getTitle() { return Title;}
    public void setTitle(String title){ Title = title;}

    public String getSenddate() { return Senddate;}
    public void setSenddate(String senddate) { Senddate = senddate; }

    public String getKeywords() { return Keywords;}
    public void setKeywords(String keywords) { Keywords = keywords;}

    public String getId() { return Id; }
    public void setId(String id) { Id = id; }

}
