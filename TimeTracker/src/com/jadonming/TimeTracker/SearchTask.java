package com.jadonming.TimeTracker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

// Class specification
/**********
 * The 'TaskToday' class is designed for option 4
 * If the search input matches task, then it displays, if not,
 *  continue to go through the 'DatBase'
 *
 * Notice four items:
 * ->Current task and all previous tasks are separately discussed due to
 *  the framework of 'DataBase' (see more in 'DataBase' class specification)
 *  and the need to print "now" properly (the same in 'TaskToday')
 * ->Boolean MatchA(All) and MatchC(Current) are used to determine whether
 *  the search input is found or not
 * ->Notice that for current task, not null is checked for security
 * ->If the user types nothing but just '\n', no task will matches, the not
 *  empty condition is added for this special circumstance, due to the reason
 *  that .contains("") is always true
 **********/

public class SearchTask extends SharedMethod {
    DataBase db;
    public SearchTask(DataBase d) {
        db = d;
    }

    public void printSearchResult()
        throws IOException{
        ArrayList<Task> AllTask = db.getAllTask();
        boolean matchA = false;
        boolean matchC;
        System.out.print("Enter search term: ");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String SearchInput = br.readLine();

        for(Task temp : AllTask) {
            if(!SearchInput.equals("") && temp.returnTaskContent().contains(SearchInput)) {
                System.out.print(returnReadableDate(temp.returnBtime())+"\t");
                System.out.print(returnReadableTime(temp.returnBtime())+"-");
                System.out.print(returnReadableTime(temp.returnEtime())+"\t");
                System.out.print(returnRunTime(temp.returnBtime(), temp.returnEtime())+"\t");
                System.out.print(temp.returnTaskContent());
                matchA = true;
            }
        }
        matchC = printCurrentTask(SearchInput);
        if(!(matchA||matchC)) {
            System.out.println("(no matches)");
        }
        System.out.println();
    }

    private boolean printCurrentTask(String si) {
        boolean match = false;

        Task CurrentTask = db.getCurrentTask();
        if(CurrentTask == null) {return match;}
        if(!si.equals("") && CurrentTask.returnTaskContent().contains(si)) {
            System.out.print(returnReadableDate
                    (CurrentTask.returnBtime())+"\t");
            System.out.print(returnReadableTime
                    (CurrentTask.returnBtime())+"-");
            System.out.print("now\t");
            System.out.print(returnRunTime(CurrentTask.returnBtime(),
                    CurrentTask.returnEtime())+"\t");
            System.out.print(CurrentTask.returnTaskContent());
            match = true;
        }
        return match;
    }

}
