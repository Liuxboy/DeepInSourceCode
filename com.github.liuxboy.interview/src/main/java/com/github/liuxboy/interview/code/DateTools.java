package com.github.liuxboy.interview.code;

import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;

/**
 * Package: com.github.liuxboy.interview.code <br>
 * Author: liuchundong <br>
 * Date: 2017/6/9 <br>
 * Time: 10:26 <br>
 * Desc:
 */
public class DateTools {
    public static void main(String[] args) {
        String[] timeStrs = new String[]{"20100708 10:00:00", "20100708 12:20:30", "20100708 14:40:00"};
        System.out.println(countPeriod(timeStrs));
    }

    /**
     * @param params
     * @return
     * @link /resources/rest/PeriodCount.png
     */
    private static String countPeriod(String[] params) {
        Date[] dates = new Date[params.length];
        try {
            for (int i = 0; i < params.length; i++) {
                dates[i] = DateUtils.parseDate(params[i], "yyyyMMdd HH:mm:ss");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long HOUR = 60 * 60 * 1000;
        long MIN = 60 * 1000;
        long SEC = 1 * 1000;

        long gapTime = dates[1].getTime() - dates[0].getTime();
        long hour = gapTime / HOUR;
        long minute = (gapTime - hour * HOUR) / MIN;
        long second = (gapTime - hour * HOUR - minute * MIN) / SEC;

        StringBuilder stringBuilder = new StringBuilder()
                .append(hour).append("hour")
                .append(minute).append("minute")
                .append(second).append("second");
        return stringBuilder.toString();
    }
}
