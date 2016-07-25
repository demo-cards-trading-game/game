package utils;

import java.util.Calendar;

/**
 * Created by luisc on 24/7/2016.
 */
public class Utils {
    public static Boolean flag;

    public static void waitSeconds(int seconds){
        seconds = Calendar.getInstance().get(Calendar.SECOND)+seconds;
        if(seconds>59){
            seconds = seconds-59;
        }
        while (Calendar.getInstance().get(Calendar.SECOND)!=seconds){
            // wait
        }
    }

    public static void waitSecondsWithFlag(int seconds){
        flag = false;
        seconds = Calendar.getInstance().get(Calendar.SECOND)+seconds;
        if(seconds>59){
            seconds = seconds-59;
        }
        while (Calendar.getInstance().get(Calendar.SECOND)!=seconds && !flag){
            // wait
        }
        flag = true;
    }

    public static void waitWithFlag(){
        flag = false;
        int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)+1;

        while (Calendar.getInstance().get(Calendar.DAY_OF_MONTH)!=day && !flag){
            // wait
        }
        flag = true;
    }
}
