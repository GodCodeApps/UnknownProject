package com.lib_common.utils;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.Locale;
import java.util.TimeZone;

/**
 * DateFormatUtil时间日期格式
 *
 * @author LiJiaJian
 * @date 2014-9-13 下午6：47：31
 * @Description：
 */
public class DateFormatUtils {

    private DateFormatUtils() {
        throw new AssertionError();
    }

    /**
     * 格式：yyyy-MM-dd
     */
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    /**
     * 格式：yyyy
     */
    public static final String YYYY = "yyyy";
    /**
     * 格式：yyyyMMdd
     */
    public static final String YYYYMMDD = "yyyyMMdd";
    /**
     * 格式：yyyy/MM/dd
     */
    public static final String YYYYMMDD2 = "yyyy/MM/dd";
    /**
     * 格式：yyyyMM
     */
    public static final String YYYYMM = "yyyyMM";
    /**
     * 格式：yyyy-MM
     */
    public static final String YYYY_MM = "yyyy-MM";
    /**
     * 格式：yyyy-MM-dd HH：mm：ss
     */
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    /**
     * 格式：yyyy-MM-dd EEEE HH：mm：ss
     */
    public static final String YYYY_MM_DD_EEEE_HH_MM_SS = "yyyy-MM-dd EEEE hh:mm:ss";
    /**
     * 格式：yyyy-MM-dd HH：mm
     */
    public static final String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
    /**
     * 格式：HH：mm：ss
     */
    public static final String HH_mm_ss = "HH：mm：ss";
    /**
     * 格式：MM-dd
     */
    public static final String MM_DD = "MM-dd";
    /**
     * 格式：MM月dd日
     */
    public static final String MM_DD2 = "MM月dd日";
    /**
     * 格式：MM月dd日HH：mm
     */
    public static final String MM_DD2_HH_MM = "MM月dd日HH：mm";

    /**
     * 格式:MM-dd HH:mm
     */
    public static final String MM_DD_HH_MM = "MM-dd HH:mm";
    /**
     * 格式：yyyy年MM月dd日
     */
    public static final String YYYY_MM_DD2 = "yyyy年MM月dd日";
    /**
     * 格式：yyyy年MM月
     */
    public static final String YYYY_MM_DD3 = "yyyy年MM月";
    /**
     * 格式：yyyy年MM月dd日HH：mm
     */
    public static final String YYYY_MM_DD2_HH_MM = "yyyy年MM月dd日HH：mm";
    /**
     * 格式：yyyy.MM.dd HH：mm
     */
    public static final String YYYY_MM_DD2_HH_MM2 = "yyyy.MM.dd HH:mm";
    /**
     * 格式：yyyy年MM月dd日 HH：mm：ss
     */
    public static final String YYYY_MM_DD2_HH_MM_SS = "yyyy年MM月dd日 HH：mm：ss";
    /**
     * 格式：KK：mm
     */
    public static final String HH_MM_12 = "KK:mm";
    /**
     * 格式：HH：mm
     */
    public static final String HH_MM_24 = "HH:mm";

    /**
     * 格式：HH
     */
    public static final String HH = "HH";
    /**
     * 格式：mm：ss
     */
    public static final String MM_SS = "mm:ss";

    /**
     * 格式：dd
     */
    public static final String DD = "dd";
    /**
     * 格式：MM
     */
    public static final String MM = "MM";
    /**
     * 格式：mm
     */
    public static final String MIN = "mm";

    /**
     * 12小时制/24小时制枚举
     */
    public enum HoursFormat {
        /**
         * 12小时制
         */
        H12,
        /**
         * 24小时制
         */
        H24
    }

    /**
     * 时间状态名称
     */
    public enum TimeName {
        /**
         * 今天
         */
        TODAY,
        /**
         * 昨天
         */
        YESTERDAY,
        /**
         * 前天
         */
        THE_DAY_BEFORE_YESTERDAY,
        /**
         * 大前天
         */
        THE_DAY_BEFORE_YESTERDAY_MORE,
        /**
         * 明天
         */
        TOMORROW,
        /**
         * 后天
         */
        THE_DAY_AFTER_TOMORROW,
        /**
         * 大后天
         */
        THE_DAY_AFTER_TOMORROW_MORE,
        /**
         * 上个月
         */
        LAST_MONTH,
        /**
         * 上上个月
         */
        LAST_MONTH_MORE,
        /**
         * 下个月
         */
        NEXT_MONTH,
        /**
         * 下下个月
         */
        NEXT_MONTH_MORE,
        /**
         * 去年
         */
        LAST_YEAR,
        /**
         * 前年
         */
        LAST_YEAR_MORE,
        /**
         * 明年
         */
        NEXT_YEAR,
        /**
         * 后年
         */
        NEXT_YEAR_MORE
    }

    /**
     * 时段
     */
    public enum TimeQuantum {
        /**
         * 凌晨：24点-6点
         **/
        WEE_HOURS,
        /**
         * 上午：6点-12点
         **/
        FORENOON,
        /**
         * 中午：12点-14点
         **/
        NOONING,
        /**
         * 下午：14点-18点
         **/
        AFTERNOON,
        /**
         * 晚上：18点-24点
         **/
        NIGHT
    }

    /**
     * 对比时间
     *
     * @param milliseconds 需要对比的时间戳（ms）
     * @return {@link TimeName}
     */
    public static TimeName compareTime(long milliseconds) {
        return compareTime(milliseconds, System.currentTimeMillis());
    }

    /**
     * 对比时间
     *
     * @param milliseconds 需要对比的时间戳（ms）
     * @param current      当前时间时间戳（ms）
     * @return {@link TimeName}
     */
    public static TimeName compareTime(long milliseconds, long current) {
        Calendar calendarCurrent = Calendar.getInstance();
        calendarCurrent.setTimeInMillis(current);

        Calendar calendarUser = Calendar.getInstance();
        calendarUser.setTimeInMillis(milliseconds);

        // 年份差值
        int y = calendarUser.get(Calendar.YEAR) - calendarCurrent.get(Calendar.YEAR);

        if (y == 0) { //同年
            return compareMonth(calendarUser, calendarCurrent);
        } else if (y == -1) {
            // 跨年份，去年
            int m = calendarUser.get(Calendar.MONTH) - (calendarCurrent.get(Calendar.MONTH) + 12);
            if (m == -1) {
                // 上个月是12月时
                int d = calendarUser.get(Calendar.DAY_OF_MONTH) - (calendarCurrent.get(Calendar.DAY_OF_MONTH) + 31);
                if (d >= -3) {
                    // 前两天，则算名称按昨天前天
                    return compareDay(d);
                } else {
                    return TimeName.LAST_MONTH; //上个月
                }
            } else {
                return TimeName.LAST_YEAR; //去年
            }
        } else if (y <= -2) {
            return TimeName.LAST_YEAR_MORE; //前年
        } else if (y == 1) {
            // 跨年份, 明年
            int m = calendarUser.get(Calendar.MONTH) + 12 - calendarCurrent.get(Calendar.MONTH);
            if (m == 1) {
                // 下个月是1月
                int d = calendarUser.get(Calendar.DAY_OF_MONTH) + 31 - calendarCurrent.get(Calendar.DAY_OF_MONTH);
                if (d <= 3) {
                    // 前两天，则算名称按昨天前天
                    return compareDay(d);
                } else {
                    return TimeName.NEXT_MONTH; //下个月
                }
            } else {
                return TimeName.NEXT_YEAR; //下年
            }
        } else {
            return TimeName.NEXT_YEAR_MORE; //后年
        }
    }

    /**
     * 对比月份
     *
     * @param calendarUser    需要对比的时间
     * @param calendarCurrent 当前时间
     * @return {@link TimeName}
     */
    private static TimeName compareMonth(Calendar calendarUser, Calendar calendarCurrent) {
        int m = calendarUser.get(Calendar.MONTH) - calendarCurrent.get(Calendar.MONTH);
        int d = calendarUser.get(Calendar.DAY_OF_YEAR) - calendarCurrent.get(Calendar.DAY_OF_YEAR);
        if (m == 0) {
            // 当月
            return compareDay(d);
        } else if (m == -1) {
            // 上个月
            if (d >= -3) {
                // 前两天，则算名称按昨天前天
                return compareDay(d);
            } else {
                return TimeName.LAST_MONTH;
            }
        } else if (m <= -2) {
            return TimeName.LAST_MONTH_MORE;
        } else if (m == 1) {
            // 下个月
            if (d <= 3) {
                // 后两天，则算名称按明天后天
                return compareDay(d);
            } else {
                return TimeName.NEXT_MONTH;
            }
        } else {
            // 下下个月
            return TimeName.NEXT_MONTH_MORE;
        }
    }

    /**
     * 对比天
     *
     * @param d 跟当前天数的差值
     * @return {@link TimeName}
     */
    private static TimeName compareDay(int d) {
        if (d == 0) {
            return TimeName.TODAY;
        } else if (d == -1) {
            return TimeName.YESTERDAY;
        } else if (d == -2) {
            return TimeName.THE_DAY_BEFORE_YESTERDAY;
        } else if (d < -2) {
            return TimeName.THE_DAY_BEFORE_YESTERDAY_MORE;
        } else if (d == 1) {
            return TimeName.TOMORROW;
        } else if (d == 2) {
            return TimeName.THE_DAY_AFTER_TOMORROW;
        } else {
            return TimeName.THE_DAY_AFTER_TOMORROW_MORE;
        }
    }

    /**
     * 获取指定时间戳的时间段
     *
     * @param milliseconds 需要获取时间段的时间戳（ms）
     * @return {@link TimeQuantum}
     */
    public static TimeQuantum getTimeQuantum(long milliseconds) {
        Calendar calendarUser = Calendar.getInstance();
        calendarUser.setTimeInMillis(milliseconds);
        int h = calendarUser.get(Calendar.HOUR_OF_DAY);
        if (h >= 0 && h < 6) {// 凌晨：24点-6点
            return TimeQuantum.WEE_HOURS;
        } else if (h >= 6 && h < 12) {// 上午：6点-12点
            return TimeQuantum.FORENOON;
        } else if (h >= 12 && h < 14) {// 中午：12点-14点
            return TimeQuantum.NOONING;
        } else if (h >= 14 && h < 18) {// 下午：14点-18点
            return TimeQuantum.AFTERNOON;
        } else {// 晚上：18点-24点
            return TimeQuantum.NIGHT;
        }
    }

    /**
     * 格式化日期时间
     *
     * @param milliseconds 需要格式化的时间戳（ms）
     * @param format       需要格式化的日期格式 例：{@value YYYY_MM_DD2_HH_MM}
     * @return 返回格式化后的日期格式
     */
    public static String format(long milliseconds, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(milliseconds));
    }

    /**
     * 格式化日期时间
     *
     * @param date   需要格式化的时间Date格式日期
     * @param format 需要格式化的日期格式 例：{@value YYYY_MM_DD2_HH_MM}
     * @return 返回格式化后的日期格式
     */
    public static String format(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * 根据指定的日期和对应的格式转换成Date对象<br/>
     * 如果在转换过程中出现异常会返回NULL
     *
     * @param date   需要转换的日期
     * @param format 对应日期的格式
     * @return 转换后的Date对象 如果出现异常会返回NULL
     */
    public static Date parseDate(String date, String format) {
        Date tempDate = null;
        try {
            SimpleDateFormat asf = new SimpleDateFormat(format);
            tempDate = asf.parse(date);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return tempDate;
    }

    /**
     * 格式化时间<br/>
     * 显示格式：<br/>
     * 今天12小时制：KK：mm<br/>
     * 今天24小时制：HH：mm<br/>
     * 昨天：昨天<br/>
     * 前天：前天<br/>
     * 大前天或上个月或上上个月： MM-dd<br/>
     * 去年或去年以前：yyyy-MM-dd<br/>
     * 未来的时间：yyyy-MM-dd HH：mm
     *
     * @param milliseconds 需要格式化的时间戳（ms）
     * @param hoursFormat  是12小时制显示还是24小时制显示{@link HoursFormat}
     * @return 按照不同的时间返回格式化过后的显示格式字符串
     */
    public static String format(long milliseconds, HoursFormat hoursFormat) {
        TimeName tName = DateFormatUtils.compareTime(milliseconds);
        String timeQuantumName = getTimeQuantumName(DateFormatUtils.getTimeQuantum(milliseconds));
        if (tName == TimeName.TODAY) {
            if (hoursFormat == HoursFormat.H12) {
                return timeQuantumName + DateFormatUtils.format(milliseconds, DateFormatUtils.HH_MM_12);
            } else {
                return DateFormatUtils.format(milliseconds, DateFormatUtils.HH_MM_24);
            }
        } else if (tName == TimeName.YESTERDAY) {
            return "昨天";
        } else if (tName == TimeName.THE_DAY_BEFORE_YESTERDAY) {
            return "前天";
        } else if (tName == TimeName.THE_DAY_BEFORE_YESTERDAY_MORE || tName == TimeName.LAST_MONTH
                || tName == TimeName.LAST_MONTH_MORE) {
            return DateFormatUtils.format(milliseconds, DateFormatUtils.MM_DD);

        } else if (tName == TimeName.LAST_YEAR || tName == TimeName.LAST_YEAR_MORE) {
            // 去年或去年以前
            return DateFormatUtils.format(milliseconds, DateFormatUtils.YYYY_MM_DD);
        } else {
            // 如果是未来的时间
            return DateFormatUtils.format(milliseconds, DateFormatUtils.YYYY_MM_DD_HH_MM);
        }
    }

    public static String format(long milliseconds) {
        TimeName tName = DateFormatUtils.compareTime(milliseconds);
        switch (tName) {
            case TODAY:
            case YESTERDAY:
            case THE_DAY_BEFORE_YESTERDAY:
            case THE_DAY_BEFORE_YESTERDAY_MORE:
            case LAST_MONTH_MORE:
            case LAST_MONTH:
                return DateFormatUtils.format(milliseconds, DateFormatUtils.MM_DD_HH_MM);
            default:
                return DateFormatUtils.format(milliseconds, DateFormatUtils.YYYY_MM_DD_HH_MM);
        }
    }

    /**
     * 获取时间段名称<br/>
     * {@literal DateFormatUtils.TimeQuantum.WEE_HOURS}：凌晨<br/>
     * {@literal DateFormatUtils.TimeQuantum.FORENOON}：上午<br/>
     * {@literal DateFormatUtils.TimeQuantum.NOONING}：中午<br/>
     * {@literal DateFormatUtils.TimeQuantum.AFTERNOON}：下午<br/>
     * other：晚上
     *
     * @param timeQuantum 时间段
     * @return 返回对应的名称
     */
    private static String getTimeQuantumName(TimeQuantum timeQuantum) {
        String name = "";
        if (timeQuantum == TimeQuantum.WEE_HOURS) {
            name = "凌晨";
        } else if (timeQuantum == TimeQuantum.FORENOON) {
            name = "上午";
        } else if (timeQuantum == TimeQuantum.NOONING) {
            name = "中午";
        } else if (timeQuantum == TimeQuantum.AFTERNOON) {
            name = "下午";
        } else {
            name = "晚上";
        }
        return name;
    }

    /**
     * 根据时区获取当前时间毫秒数
     *
     * @param timeInMillis 需要获取的时间戳（ms）
     * @param timeZone     时区 例：TimeZone.getTimeZone("GMT+8")
     * @return 返回转换过的时间戳（ms）
     */
    public static long getCurrentTimeInMillis(long timeInMillis, TimeZone timeZone) {
        Calendar c = Calendar.getInstance();
        c.setTimeZone(timeZone);
        c.setTimeInMillis(timeInMillis);
        return c.getTimeInMillis();
    }

    /**
     * 获取当前北京时间毫秒数
     *
     * @param timeInMillis 需要获取的时间戳（ms）
     * @return 返回转换过的时间戳（ms）
     */
    public static long getCurrentBeiJingTimeInMillis(long timeInMillis) {
        return getCurrentTimeInMillis(timeInMillis, TimeZone.getTimeZone("GMT+8"));
    }


    /**
     * 判断是否同一天
     *
     * @param time1 对比时间戳1（ms）
     * @param time2 对比时间戳2（ms）
     * @return true：是；false：否
     */
    public static boolean isSameDay(long time1, long time2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTimeInMillis(time1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTimeInMillis(time2);
        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
                && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH)
                && cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取一个时间所在天的起始时间(即0点)
     *
     * @param time 需要获取的时间戳（ms）
     * @return 返回起始时间点时间戳
     * @author LiJiaJian
     */
    public static long getTimesmorning(long time) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(time);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis();
    }


    /**
     * 获取一个时间所在天的结束时间(即23点)
     *
     * @param time 需要获取的时间戳（ms）
     * @return 返回起始时间点时间戳
     * @author jiangtianming
     */
    public static long getEndTimes(long time) {
        Calendar todayEnd = Calendar.getInstance();
        todayEnd.setTimeInMillis(time);
        todayEnd.set(Calendar.HOUR, 23);
        todayEnd.set(Calendar.MINUTE, 59);
        todayEnd.set(Calendar.SECOND, 59);
        todayEnd.set(Calendar.MILLISECOND, 999);
        return todayEnd.getTime().getTime();
    }

    /**
     * 获得日期是周几
     *
     * @param dt
     * @return
     */
    public static String getWeekOfDate(Date dt) {
        String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;

        return weekDays[w];
    }

    /**
     * 将日期格式转为 2016-09-08 周二 16:00
     *
     * @param dt
     * @return
     */
    public static String getWeekFormatOfDate(Date dt) {
        String weekStr = format(dt, YYYY_MM_DD) + " " + format(dt, HH_MM_24) + " " + getWeekOfDate(dt);
        return weekStr;
    }

    /**
     * 格式化日期 2016-09-08 周二 16:00
     *
     * @param date
     * @return
     */
    public static String getWeekFormatOfDate(String date) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String weekStr;
        try {
            Date weekdate = sdf.parse(date);
            weekStr = format(weekdate, "yyyy-MM-dd") + " " + format(weekdate, "HH:mm") + " " + getWeekOfDate(weekdate);
        } catch (ParseException e) {
            e.printStackTrace();
            weekStr = "";
        }
        return weekStr;
    }

    /**
     * 获取日期的毫秒数
     *
     * @param expireDate
     * @return
     * @author jiangtianming
     */
    public static long getSecondsFromDate(String expireDate,String format) {
        if (expireDate == null || expireDate.trim().equals(""))
            return 0;
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = sdf.parse(expireDate);
            return date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 日期增加
     *
     * @param times long
     * @param day   int
     * @return Date
     * @author jiangtianming
     */
    public static Date addDate(long times, int day) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(times + ((long) day) * 24 * 3600 * 1000);
        return c.getTime();
    }

    /**
     * 获取当前毫秒
     *
     * @param date
     * @return
     * @author jiangtianming
     */
    public static long getMillis(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.getTimeInMillis();
    }

    /**
     * 截取月日
     *
     * @param date
     * @return
     * @author jiangtianming
     */
    public static String getMonthAndDay(String date) {
        if (TextUtils.isEmpty(date)) {
            return "";
        }
        return date.substring(5, date.length()).replaceFirst("-", "月") + "日";
    }

    /**
     * 截取年
     *
     * @param date
     * @return
     * @author jiangtianming
     */
    public static String getYear(String date) {
        if (TextUtils.isEmpty(date)) {
            return "";
        }
        return date.substring(0, 4);
    }

    /**
     * 把毫秒转换成：1：20：30这样的形式
     *
     * @param timeMs
     * @return
     */
    public static String stringForTime(int timeMs) {
        StringBuilder mFormatBuilder = new StringBuilder();
        Formatter mFormatter = new Formatter(mFormatBuilder, Locale.getDefault());
        int totalSeconds = timeMs / 1000;
        int seconds = totalSeconds % 60;
        int minutes = (totalSeconds / 60) % 60;
        int hours = totalSeconds / 3600;
        mFormatBuilder.setLength(0);
        if (hours > 0) {
            return mFormatter.format("%d:%02d:%02d", hours, minutes, seconds).toString();
        } else {
            return mFormatter.format("%02d:%02d", minutes, seconds).toString();
        }
    }

    public static int getAge(Date birthDay) throws Exception {
        Calendar cal = Calendar.getInstance();

        if (cal.before(birthDay)) {
            throw new IllegalArgumentException(
                    "The birthDay is before Now.It's unbelievable!");
        }
        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH);
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(birthDay);

        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

        int age = yearNow - yearBirth;

        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth)
                    age--;
            } else {
                age--;
            }
        }
        return age;
    }

    public static String getNearTime(int timeDescriptor) {
        if (timeDescriptor <= 0) {
            return "刚刚";
        } else if (timeDescriptor < 60) {
            return timeDescriptor + "分钟前";
        } else if (timeDescriptor < 60 * 24) {
            return (timeDescriptor / 60) + "小时前";
        } else if (timeDescriptor < 60 * 24 * 365) {
            return (timeDescriptor / (60 * 24)) + "天前";
        } else {
            return (timeDescriptor / (60 * 24 * 365)) + "年前";
        }
    }

}
