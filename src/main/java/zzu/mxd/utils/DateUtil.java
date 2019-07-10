package zzu.mxd.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class DateUtil {
    /**
     * 获取当前时间前xxx分钟的时间
     * @return
     */
    public static LocalDateTime timeBefore(int minutes){
        LocalDateTime beforeDate = LocalDateTime.now().minus(minutes,ChronoUnit.MINUTES);
        return beforeDate;
    }

    /**
     * 获取当前时间
     * @return
     */
    public static LocalDateTime getCurrentTime(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        return LocalDateTime.parse(df.format(new Date()));
    }
}
