package com.jadonming.TimeTracker;

import java.io.IOException;

// Class specification
/**********
 * The 'MainMenu' class essentially achieves three aims:
 *  display current task, print the main menu and ask input for option
 * So that the 'Database' is used to show current task
 * And the three methods are designed for each aim
 *
 * Notice that MainMenu inherits from SharedMethod since in showing current
 *  task the length of time(RunTime) is displayed using returnRunTime
 *  provided by SharedMethod
 **********/

public class MainMenu extends SharedMethod {
    DataBase db;
    public MainMenu(DataBase d) {
        db = d;
    }


    public int printMainMenu()
        throws IOException {

        for(;;) {

            System.out.print("Current Task: ");

            Task CurrentTask = db.getCurrentTask();

            if(CurrentTask == null) {
                System.out.println("(None)");
            }
            else {
                System.out.print(returnRunTime(CurrentTask.returnBtime(),
                        CurrentTask.returnEtime())+"\t");
                System.out.print(CurrentTask.returnTaskContent());
            }

            printAllOption();

            String check = "12345";
            StringBuilder str = readOption();

            if(str.length() != 1 || check.indexOf(str.charAt(0)) == -1) {
                System.out.println("Invalid input!\n");
                continue;
            }
            else {
                return check.indexOf(str.charAt(0));
            }
        }
    }


    private void printAllOption() {

        System.out.println("1) Change task");
        System.out.println("2) Show today's tasks");
        System.out.println("3) Show this week's summary");
        System.out.println("4) Search tasks");
        System.out.println("5) Quit");
        System.out.print("Option: ");
    }


    private StringBuilder readOption()
        throws IOException{
        StringBuilder str = new StringBuilder();
        char c;

        // read until type '\n', then check if valid, else input again
        c = (char)System.in.read();
        while(c != '\n') {
            str.append(c);
            c = (char)System.in.read();
        }
        return str;
    }


}
