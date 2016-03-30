package unnc.cs.zy15826;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

// Class specification
/**********
 * This class is the superclass of 'MainMenu', 'ChangeTask', 'TaskToday'
 *  'WeekSummary' and 'SearchTask'
 * It provides several methods that can be used repetitiously by the subclasses
 * The aim of designing the class is to simplify the code so that it contains
 *  less duplicated parts that provides almost the same function
 *
 * Method returnRunTime is used in: 'MainMenu', 'TaskToday' and 'SearchTask'
 * Method returnReadableDate is used in 'TaskToday', 'WeekSummary' and 'SearchTask'
 * Method returnReadableTime is used in 'TaskToday' and 'SearchTask'
 * Method convertTimeToDayStart is used in 'TaskToday' and 'WeekSummary'
 **********/


public class SharedMethod {
    SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm");

    public String returnRunTime(long beginTime, long endTime){
        long runTime;
        if(endTime == 0) {
            runTime = System.currentTimeMillis() - beginTime;
        }
        else {
            runTime = endTime - beginTime;
        }

        long minutesInMillis = 60000;
        long hoursMillis = minutesInMillis * 60;
        long rHours = runTime / hoursMillis;
        runTime = runTime % hoursMillis;
        long rMinutes = runTime / minutesInMillis;
        runTime = runTime % minutesInMillis;



        StringBuilder str = new StringBuilder("(");


        if (rHours > 0) {
            int rH = (int) rHours;
            str.append(rH+"h ");
        }
        int rM = (int) rMinutes;
        if(runTime > 0) {
            rM += 1;
        }
        str.append(rM+"m)");
        return str.toString();
    }

    public String returnReadableDate(long Time) {
        Date DateNow = new Date(Time);
        return sdfDate.format(DateNow);
    }

    public String returnReadableTime(long Time) {
        Date TimeNow = new Date(Time);
        return sdfTime.format(TimeNow);
    }

    public long convertTimeToDayStart(String D) {

        ParsePosition pp = new ParsePosition(0);
        Date nD = sdfDate.parse(D, pp);

        return nD.getTime();
    }
}
