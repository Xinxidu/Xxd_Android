package com.xinxidu.xxd.models;

public class   JsonData {

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
        private String jiaoyi;

        public String getJiaoyi() {
            return jiaoyi;
        }

        public void setJiaoyi(String jiaoyi) {
            this.jiaoyi = jiaoyi;
        }

    }


}
