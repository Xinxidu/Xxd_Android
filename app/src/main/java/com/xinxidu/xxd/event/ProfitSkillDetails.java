package com.xinxidu.xxd.event;

import java.util.List;

/**
 * Created by limingquan on 2016/9/20.
 * 每日投资策略详情
 */
public class ProfitSkillDetails {

    private int flag;
    private List<DataBean> data;
    private String msg;
    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }
    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        public String getBody() {
            return Body;
        }

        public void setBody(String body) {
            Body = body;
        }

        public String Body;
    }

}