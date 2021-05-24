package com.dept.filesite.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @className: DateUtil
 * @description:
 * @author: 201998
 * @create: 2020-04-27 14:05
 */

public class DateUtil {

    /**
     *
     * @return yyyy-MM-dd
     */
    public static String getToday(){
        Calendar ca = Calendar.getInstance();
        int year = ca.get(Calendar.YEAR);
        int month = ca.get(Calendar.MONTH)+1;
        int day = ca.get(Calendar.DATE);
        String result = "";
        if (month < 10){
            result =  year+"-"+"0"+month;
        }else{
            result = year+"-"+month;
        }
        if (day < 10){
            result+="-"+"0"+day;
        }else{
            result+="-"+day;
        }
        return result;


    }

    /**
     *
     * @return HH:mm:ss
     */
    public static String getCurrentTime(){
        Calendar ca = Calendar.getInstance();
        int hour = ca.get(Calendar.HOUR_OF_DAY);
        int min = ca.get(Calendar.MINUTE);
        int second = ca.get(Calendar.SECOND);
        String result = "";
        if (min < 10){
            result = hour + ":" + "0" + min;
        }else{
            result = hour + ":" + min;
        }
        if (second < 10){
            result+= ":" + "0" + second;
        }else{
            result+= ":" + second;
        }
        return result;
    }

    /**
     *
     * @param am 时间点
     * @deprecated 判断当前时间是否超过某个时间点
     * @return true 超过； false 没超过
     * @throws ParseException
     */
    public static boolean isTimeRange(String am) {
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        try {
            Date now = df.parse(df.format(new Date()));
            Date begin = df.parse(am);
            //Date end = df.parse(pm");
            Calendar nowTime = Calendar.getInstance();
            nowTime.setTime(now);
            Calendar beginTime = Calendar.getInstance();
            beginTime.setTime(begin);
            //Calendar endTime = Calendar.getInstance();
            //endTime.setTime(end);
            if (nowTime.after(beginTime)) {
                return true;
            } else {
                return false;
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }


}
