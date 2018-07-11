package quarter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author :lvweihao@outlook.com
 * @description:
 * @date :create in 2:01 18.5.28
 * @modify by :
 */
public class QuarterUtils {

    public static Calendar calendar = Calendar.getInstance();

    /**
       * @author : lvweihao@outlook.com
       * @description : 获取当前季度
       * @date : create in 23:03 18.6.4
       * @param
       * @modify by :
       */
    public static int getCurrentQuarter() throws RuntimeException {
        int month = calendar.get(Calendar.MONTH) + 1;

        int quarter = 0;

        switch (month) {
            case 1:
            case 2:
            case 3:
                quarter = 1;
                break;

            case 4:
            case 5:
            case 6:
                quarter = 2;
                break;

            case 7:
            case 8:
            case 9:
                quarter = 3;
                break;

            case 10:
            case 11:
            case 12:
                quarter = 4;
                break;
            default:
                break;
        }

        if (quarter == 0) {
            throw new RuntimeException("获取当前季度失败");
        }
        return quarter;
    }

    /**
       * @author : lvweihao@outlook.com
       * @description : 获取指定季度开始月份
       * @date : create in 23:10 18.6.4
       * @param quarter
       * @modify by :
       */
    public static int getQuarterFloorMonth(int quarter) throws RuntimeException {
        int floorMonth = 0;
        switch (quarter) {
            case 1 :
                floorMonth = 1;
                break;
            case 2 :
                floorMonth = 4;
                break;
            case 3 :
                floorMonth = 7;
                break;
            case 4 :
                floorMonth = 10;
                break;
            default :
                break;
        }

        if (floorMonth == 0) {
            throw new RuntimeException("获取季度开始月份失败");
        }
        return floorMonth;
    }

    /**
       * @author : lvweihao@outlook.com
       * @description : 获取指定季度结束月份
       * @date : create in 23:25 18.6.4
       * @param
       * @modify by :
       */
    public static int getQuarterCellingMonth(int quarter) throws RuntimeException {
        int cellingMonth = 0;
        switch (quarter) {
            case 1 :
                cellingMonth = 3;
                break;
            case 2 :
                cellingMonth = 6;
                break;
            case 3 :
                cellingMonth = 9;
                break;
            case 4 :
                cellingMonth = 12;
                break;
            default :
                break;
        }

        if (cellingMonth == 0) {
            throw new RuntimeException("获取季度结束月份失败");
        }
        return cellingMonth;
    }

    /**
       * @author : lvweihao@outlook.com
       * @description : 获取指定季度中间月份
       * @date : create in 23:36 18.6.4
       * @param : quarter
       * @modify by :
       */
    public static int getQuarterMiddleMonth(int quarter) {
        return (getQuarterCellingMonth(quarter) + getQuarterFloorMonth(quarter)) / 2;
    }

    /**
       * @author : lvweihao@outlook.com
       * @description : 获取当前年份
       * @date : create in 22:56 18.6.4
       * @param
       * @modify by :
       */
    public static int getCurrentYear() {
        return calendar.get(Calendar.YEAR);
    }

    /**
       * @author : lvweihao@outlook.com
       * @description : 获取代表当前季度的字符串 , 年份季度之间使用 || 隔开
       * @date : create in 22:57 18.6.4
       * @param
       * @modify by :
       */
    public static String getCurrentQuarterFormat() {
        int currentYear = getCurrentYear();
        int currentQuarter = getCurrentQuarter();
        return currentYear + "||" + currentQuarter;
    }

    /**
       * @author : lvweihao@outlook.com
       * @description : 获取代表下一个季度的字符串 , 年份和季度之间以 || 隔开
       * @date : create in 23:01 18.6.4
        * @param
       * @modify by :
       */
    public static String getNextQuarterFormat() {

        int currentYear = getCurrentYear();
        int currentQuarter = getCurrentQuarter();

        int year = currentYear;
        int quarter = currentQuarter + 1;

        if (currentQuarter == 4) {
            year = currentYear + 1;
            quarter = 1;
        }

        return year + "||" + quarter;
    }

    /**
       * @author : lvweihao@outlook.com
       * @description : 是否已过指定季度的中间点 (中间点 以中间月份的十五日为界)
       * @date : create in 23:05 18.6.4
       * @param quarter
       * @modify by :
       */
    /*public boolean passMiddleQuarter(int quarter) throws ParseException {
        boolean flag = false;
        int month = getQuarterMiddleMonth(quarter);
        int year = getCurrentYear();
        // 定义界限为16日凌晨00点00分00秒
        String timeFormat = year + "-" +
                ((month < 10) ? "0" + month : month) + "-" + "16" + " 00:00:00";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
        Date date = format.parse(timeFormat);

        return flag;
    }*/

    /**
       * @author : lvweihao@outlook.com
       * @description : 指定时间是否已过当前季度的中间点
       * @date : create in 23:28 18.6.4
       * @param :
       * @modify by :
       */
    public static boolean passMiddleCurrentQuarter(Date date) throws ParseException {
        int month = getQuarterMiddleMonth(getCurrentQuarter());
        int year = getCurrentYear();
        // 定义界限为16日凌晨00点00分00秒
        String timeFormat = year + "-" +
                ((month < 10) ? "0" + month : month) + "-" + "16" + " 00:00:00";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
        Date middleTime = format.parse(timeFormat);
        return  !date.before(middleTime);
    }

    public static void main(String[] args) throws ParseException {
        System.out.println(getCurrentQuarterFormat());
        System.out.println(getNextQuarterFormat());
        System.out.println(passMiddleCurrentQuarter(new Date()));
    }

}
