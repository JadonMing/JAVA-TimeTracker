// 6515826 zy15826 Zequn YU
package unnc.cs.zy15826;

import java.io.IOException;

// Program Brief Introduction
/**********
 * This project contains 10 classes in total essentially:
 *
 * ->The 'TimeTracker' class includes main method to run the program
 * ->The 'MainMenu' class use loop to show the main menu and ask input option
 * ->The 'ChangeTask', 'TaskToday', 'WeekSummary' and 'SearchTask' class is designed for
 *  function 1, 2, 3 and 4 respectively as required
 * ->The 'DataBase' is used to record all tasks and the 'Task' class is basically to record
 *  all data that is needed in a single task
 * ->The 'FilePrompt' class provides a stream to write and read the 'Database'(Stores 'Task')
 *  to and from file so that information is recorded and reused even if the program is
 *  terminated and reopened again
 * ->The 'SharedMethod' class basically provides several methods that can be used by
 *  several other classes to achieve same small part of aim, so it is superclass
 *
 * More details can be found in each class's Class specification
 * And hopefully the reader would like this kind of comment style :)
 *********/

// Class specification
/**********
 * This class includes main method to call each class and run the
 *  whole program
 * First the program creates 'FilePrompt' and ask for 'DataBase'
 * Then continue each option
 **********/

public class TimeTracker {


    public static void main(String[] args) {

        FilePrompt fp = new FilePrompt();
        DataBase db = null;
        try {
            db = fp.fileInput();
        } catch (IOException exc) {
            exc.printStackTrace();
        } catch (ClassNotFoundException exc) {
            exc.printStackTrace();
        }

        MainMenu a = new MainMenu(db);
        ChangeTask b = new ChangeTask(db);
        TaskToday c = new TaskToday(db);
        WeekSummary d = new WeekSummary(db);
        SearchTask e = new SearchTask(db);

        int choice ;
        try {
            do {
                choice = a.printMainMenu();
                switch (choice) {
                    case 0:
                        b.changeTask();
                        fp.fileOutput(db);
                        continue;
                    case 1:
                        c.printTodayTask();
                        continue;
                    case 2:
                        d.printWeekSummary();
                        continue;
                    case 3:
                        e.printSearchResult();
                        continue;
                    case 4:
                        break;
                }
            } while (choice != 4);
        } catch (java.io.IOException exc) {
            System.out.println("I/O exception occurred!");
        }
    }
}