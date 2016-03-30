// 6515826 zy15826 Zequn YU
package unnc.cs.zy15826;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

// Class specification
/**********
 * The class is designed for printing week summary (previous 6 days
 *  and today)
 * The aim can be divided into three parts: find all tags (begins with '#'
 *  and ends with whitespace or '#'), calculate time in seven days and
 *  print the summary table
 *
 * The findTag method uses two for loop (one inside another) to find every
 *  tag, and the reason is that considering the efficiency, no repeated
 *  search is needed
 * The class uses hash map structure to store the length of time in each
 *  day, since the search is based on tag, and by using hasp map, no repeated
 *  search is needed either
 *
 * The addRunTime calculates the most exciting part of the whole program :)
 * This paragraph introduce the way to calculate length of time:
 * The defineSevenDay method asks for current date and stores 8 dates, which
 *  consists 7 time intervals (starts from 6 days ago 00:00 ends to today 24:00,
 *  divided by day) like that:
 * |1|2|3|4|5|6|today|
 * ('|' stands for begin and end time of day, B and E stands for begin and end time
 *  of task)
 * For each day there are six circumstances:
 * (1) BE||  (2) ||BE  (3) B|E|  (4) |B|E  (5) B||E  (6) |BE|
 * So, if the first two has been considered (which is obviously 0 for this day),
 *  the rule for the other four circumstances is: the length of time in this day
 *  is always the interval between the inner two times (for instance (5) is || and
 *  (6) is BE)
 * The if condition and calculation is based on this
 *
 *
 * For printTable method, alignment is only assured of tag whose length is less than
 *  7 (includes '#')
 * And notice that returnRunTime method overload from returnRunTime inherited
 *  from SharedMethod
 **********/

public class WeekSummary extends SharedMethod {
    DataBase db;
    HashMap<String, long[]> hm = new HashMap<>();
    long[] startTime = new long[8];

    public WeekSummary(DataBase d) {
        db = d;
    }

    private void defineSevenDay() throws IOException {
        long today = System.currentTimeMillis();

        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
        long dayInMs = 1000 * 60 * 60 * 24;
        Date SevenDaysAgo = new Date(today - (6 * dayInMs));
        long dayStartTime = convertTimeToDayStart(sdfDate.format(SevenDaysAgo));

        for(int i = 0; i< 8; i++) {
            startTime[i] = dayStartTime;
            dayStartTime = dayStartTime + dayInMs;
        }

    }

    public void printWeekSummary()
        throws IOException {

        defineSevenDay();
        ArrayList<Task> AllTask = db.getAllTask();
        for(Task temp : AllTask) {
            findTag(temp);
        }
        Task CurrentTask = db.getCurrentTask();
        if(CurrentTask != null) {
            findTag(CurrentTask);
        }
        printTable();
    }

    private void findTag(Task T)
            throws IOException {

        String TaskContent = T.returnTaskContent();
        StringBuilder sbr = new StringBuilder();

        for(int i=0; i<TaskContent.length(); i++ ) {
            char c = TaskContent.charAt(i);
            if( c == '#') {
                sbr.append(c);
                for(;;) {
                    i++;
                    if (i>=TaskContent.length()){i--;break;}
                    c = TaskContent.charAt(i);
                    if(Character.isWhitespace(c) || c == '#'){i--;break;}
                    sbr.append(c);
                }

                if(!sbr.toString().equals("#")) {
                    if (!hm.containsKey(sbr.toString())) {
                        hm.put(sbr.toString(), new long[7]);
                    }

                    addRunTime(sbr.toString(), T);
                }
                sbr.delete(0, sbr.length());
            }
        }
    }

    private void addRunTime(String Tag, Task T)
            throws IOException{

        long bTime = T.returnBtime();
        long eTime;


        if(T.returnEtime() == 0) {
            eTime = System.currentTimeMillis();
        }
        else {
            eTime = T.returnEtime();
        }

        for(int i=0; i<7; i++) {

            if(!(bTime<=startTime[i]&&eTime<=startTime[i])&&
                    !(bTime>startTime[i+1]&&eTime>startTime[i+1])) {

                long tempBTime = bTime;
                long tempETime = eTime;

                if (bTime < startTime[i]) {tempBTime = startTime[i];}
                if (eTime >= startTime[i + 1]) {tempETime = startTime[i + 1];}

                (hm.get(Tag))[i] += (tempETime - tempBTime);
            }
        }
    }

    private void printTable() {

        System.out.print("Tags\t");

        for(int i=0; i<7; i++) {
            System.out.print(returnReadableDate(startTime[i])+"\t");
        }
        System.out.println();

        Iterator Itor = hm.entrySet().iterator();
        while (Itor.hasNext()) {

            Map.Entry pair = (Map.Entry)Itor.next();
            String temp = (String) pair.getKey();
            System.out.printf("%-7s", temp+"\t");

            for(int i=0; i<7; i++) {
                if((hm.get(temp))[i] != 0) {
                    System.out.printf("%-10s", returnRunTime((hm.get(temp))[i]));
                }
                else {
                    System.out.print("          ");
                }
                System.out.print("\t");
            }

            Itor.remove(); // using to avoid ConcurrentModificationException
            System.out.println();
        }
        System.out.println();
    }

    private String returnRunTime(long runTime){

        long minutesInMillis = 60000;
        long hoursMillis = minutesInMillis * 60;
        long rHours = runTime / hoursMillis;
        runTime = runTime % hoursMillis;
        long rMinutes = runTime / minutesInMillis;
        runTime = runTime % minutesInMillis;

        StringBuilder str = new StringBuilder();

        if (rHours > 0) {
            int rH = (int) rHours;
            str.append(rH+"h ");
        }
        int rM = (int) rMinutes;
        if(runTime > 0) {
            rM += 1;
        }
        str.append(rM+"m");
        return str.toString();
    }

}
