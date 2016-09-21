package com.xinxidu.xxd.models;

public class XiduInfoData {

    private int flag;
    private DataBeans data;
    private String msg;

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public DataBeans getData() {
        return data;
    }

    public void setData(DataBeans data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBeans {
        private  String gongsi;

        public String getGongsi(){
            return gongsi;
        }
        public void setGongsi(String gongsi) {
            this.gongsi = gongsi;
        }
    }


}
