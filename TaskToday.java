// 6515826 zy15826 Zequn YU
package unnc.cs.zy15826;

import java.util.ArrayList;

// Class specification
/**********
 * The 'TaskToday' class is designed for option 2
 * Basically the two methods in this class provides function to go through
 *  the DataBase and display today's date (for example 2016-01-01)
 *
 * Notice three items:
 * ->Current task and all previous tasks are separately discussed due to
 *  the framework of 'DataBase' (see more in 'DataBase' class specification)
 *  and the need to print "now" properly
 * ->One special circumstance is that the task began before today, so because
 *  the class displays today's task the displaying begin time is 00:00 and the
 *  length of time is calculated from the beginning of today
 * ->Notice that for current task, not null is checked for security
 **********/


public class TaskToday extends SharedMethod{
    DataBase db;
    public TaskToday(DataBase d) {
        db = d;
    }

    public void printTodayTask() {
        ArrayList<Task> AllTask = db.getAllTask();

        String sc = returnReadableDate(System.currentTimeMillis());
        System.out.println("Today's tasks ("+ sc +")");
        for(Task temp : AllTask) {
            String sb = returnReadableDate(temp.returnBtime());
            String se = returnReadableDate(temp.returnEtime());

            if(sc.equals(sb)&&sc.equals(se)) {
                System.out.print(returnReadableTime(temp.returnBtime())+"-");
                System.out.print(returnReadableTime(temp.returnEtime())+"\t");
                System.out.print(returnRunTime(temp.returnBtime(), temp.returnEtime())+"\t");
                System.out.print(temp.returnTaskContent());

            }
            if(!sc.equals(sb)&&sc.equals(se)) {
                System.out.print("00:00-");
                System.out.print(returnReadableTime(temp.returnEtime())+"\t");
                System.out.print(returnRunTime(convertTimeToDayStart(sc), temp.returnEtime())+"\t");
                System.out.print(temp.returnTaskContent());
            }
        }
        printCurrentTask(sc);
        System.out.println();
    }

    private void printCurrentTask(String sc) {
        Task CurrentTask = db.getCurrentTask();
        if(CurrentTask == null) {return;}
        String s = returnReadableDate(CurrentTask.returnBtime());
        if(!sc.equals(s)) {
            System.out.print("00:00-");
            System.out.print("now\t");
            System.out.print(returnRunTime(convertTimeToDayStart(sc),
                    CurrentTask.returnEtime())+"\t");
        }
        else {
            System.out.print(returnReadableTime(CurrentTask.returnBtime())+"-");
            System.out.print("now\t");
            System.out.print(returnRunTime(CurrentTask.returnBtime(),
                    CurrentTask.returnEtime())+"\t");
        }

        System.out.print(CurrentTask.returnTaskContent());
    }

}
