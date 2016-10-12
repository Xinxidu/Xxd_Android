package com.xinxidu.xxd.models;

public class XiduInfoData {

    private int flag;
    private String msg;

    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String gongsi;

        public String getGongsi() {
            return gongsi;
        }

        public void setGongsi(String jiaoyi) {
            this.gongsi = jiaoyi;
        }
    }
}