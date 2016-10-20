package com.xinxidu.xxd.utils.calendar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class CalenderDataUtils {

    /**
     * time: 2016/10/19 10:50
     * @param startTime   开始日期
     * @param currentTime 当前日期
     * @param endTime     结束日期 天数差值不计算当天
     *
     */
    public static List<CalenderBean> getCalenderData(String startTime , String currentTime , String endTime){

        int diffrenceDay = getDiffrenceDay(startTime, endTime);
        List<CalenderBean> beanList = new ArrayList<>();
        Calendar instance = Calendar.getInstance();
        instance.setTime(getData(startTime));
        int day = instance.get(Calendar.DAY_OF_MONTH);
        for (int i =0 ;i<=diffrenceDay ; i++){
            CalenderBean bean = new CalenderBean();
            if (i>0) {
                instance.add(Calendar.DATE, 1);
            }
            int currentDay = instance.get(Calendar.DAY_OF_MONTH);
            int currentWeek = instance.get(Calendar.DAY_OF_WEEK);
            bean.setNunber(currentDay+"");
            switch (currentWeek){
                case 1:
                    bean.setWeek("周日");
                    break;
                case 2:
                    bean.setWeek("周一");
                    break;
                case 3:
                    bean.setWeek("周二");
                    break;
                case 4:
                    bean.setWeek("周三");
                    break;
                case 5:
                    bean.setWeek("周四");
                    break;
                case 6:
                    bean.setWeek("周五");
                    break;
                case 7:
                    bean.setWeek("周六");
                    break;
            }
            if (instance.getTimeInMillis() == getData(currentTime).getTime() ){
                bean.setCurrentItem(true);
            }else{
                bean.setCurrentItem(false);
            }
            beanList.add(bean);
        }

        return beanList;
    }


    /**
     * time: 2016/10/19 10:52
     * description:  获取两个日期之间差的天数
     */
    public static int getDiffrenceDay(String startTime, String endTime) {
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
        int day = 0;
        try {
            Date date = myFormatter.parse(startTime);
            Date mydate = myFormatter.parse(endTime);
            day = Math.abs((int) ((date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000)));
        } catch (Exception e) {
            return 0;
        }
        return day ;
    }

    /**
     * time: 2016/10/19 11:42
     * description:
     */
    public static Date getData(String time){
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date parse = myFormatter.parse(time);
            return parse;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
