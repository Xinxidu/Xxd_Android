package com.xinxidu.xxd.netWork;

/**
 * Created by limingquan on 2016/10/18.
 */
public class HotTradeBean {

    /**
     * commodity : AgJC
     * name : 白银基差1000
     * openPrice : 965.0
     * latestPrice : 968.0
     * highestPrice : 975.0
     * lowestPrice : 960.0
     * averagePrice : 966.1
     * balancePrice : 966.1
     * yesterdayClose : 977.0
     * yesterdayBalance : 981.0
     * volume : 132806
     * obv : 0.0
     * openInterest : 0
     * buy1 : 967.0
     * sale1 : 968.0
     * upsAndDowns : -0.92%
     * valueOfUpOrDown : -9.0
     */

    private String commodity;
    private String name;
    private String openPrice;
    private String latestPrice;
    private String highestPrice;
    private String lowestPrice;
    private String averagePrice;
    private String balancePrice;
    private String yesterdayClose;
    private String yesterdayBalance;
    private String volume;
    private String obv;
    private String openInterest;
    private String buy1;
    private String sale1;
    private String upsAndDowns;
    private String valueOfUpOrDown;

    public String getCommodity() {
        return commodity;
    }

    public void setCommodity(String commodity) {
        this.commodity = commodity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(String openPrice) {
        this.openPrice = openPrice;
    }

    public String getLatestPrice() {
        return latestPrice;
    }

    public void setLatestPrice(String latestPrice) {
        this.latestPrice = latestPrice;
    }

    public String getHighestPrice() {
        return highestPrice;
    }

    public void setHighestPrice(String highestPrice) {
        this.highestPrice = highestPrice;
    }

    public String getLowestPrice() {
        return lowestPrice;
    }

    public void setLowestPrice(String lowestPrice) {
        this.lowestPrice = lowestPrice;
    }

    public String getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(String averagePrice) {
        this.averagePrice = averagePrice;
    }

    public String getBalancePrice() {
        return balancePrice;
    }

    public void setBalancePrice(String balancePrice) {
        this.balancePrice = balancePrice;
    }

    public String getYesterdayClose() {
        return yesterdayClose;
    }

    public void setYesterdayClose(String yesterdayClose) {
        this.yesterdayClose = yesterdayClose;
    }

    public String getYesterdayBalance() {
        return yesterdayBalance;
    }

    public void setYesterdayBalance(String yesterdayBalance) {
        this.yesterdayBalance = yesterdayBalance;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getObv() {
        return obv;
    }

    public void setObv(String obv) {
        this.obv = obv;
    }

    public String getOpenInterest() {
        return openInterest;
    }

    public void setOpenInterest(String openInterest) {
        this.openInterest = openInterest;
    }

    public String getBuy1() {
        return buy1;
    }

    public void setBuy1(String buy1) {
        this.buy1 = buy1;
    }

    public String getSale1() {
        return sale1;
    }

    public void setSale1(String sale1) {
        this.sale1 = sale1;
    }

    public String getUpsAndDowns() {
        return upsAndDowns;
    }

    public void setUpsAndDowns(String upsAndDowns) {
        this.upsAndDowns = upsAndDowns;
    }

    public String getValueOfUpOrDown() {
        return valueOfUpOrDown;
    }

    public void setValueOfUpOrDown(String valueOfUpOrDown) {
        this.valueOfUpOrDown = valueOfUpOrDown;
    }

    @Override
    public String toString() {
        return "HotTradeBean{" +
                "commodity='" + commodity + '\'' +
                ", name='" + name + '\'' +
                ", openPrice='" + openPrice + '\'' +
                ", latestPrice='" + latestPrice + '\'' +
                ", highestPrice='" + highestPrice + '\'' +
                ", lowestPrice='" + lowestPrice + '\'' +
                ", averagePrice='" + averagePrice + '\'' +
                ", balancePrice='" + balancePrice + '\'' +
                ", yesterdayClose='" + yesterdayClose + '\'' +
                ", yesterdayBalance='" + yesterdayBalance + '\'' +
                ", volume='" + volume + '\'' +
                ", obv='" + obv + '\'' +
                ", openInterest='" + openInterest + '\'' +
                ", buy1='" + buy1 + '\'' +
                ", sale1='" + sale1 + '\'' +
                ", upsAndDowns='" + upsAndDowns + '\'' +
                ", valueOfUpOrDown='" + valueOfUpOrDown + '\'' +
                '}';
    }
}
