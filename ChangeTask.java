// 6515826 zy15826 Zequn YU
package unnc.cs.zy15826;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// Class specification
/**********
 * The 'ChangeTask' class ask input from the user in the command line
 *  which is the only way that user can change tasks that are stored in
 *  'DataBase'
 * For that reason, the 'DataBase' is used in adding end time to the previous
 *  task, inputting new task content and adding begin time to the new task
 *
 * Notice two items:
 * -> When option 1 is chose, the previous task will end anyhow, so that the
 *  end time is added immediately
 * -> The user could type nothing but just a blank line to stop, in which the
 *  'Task' will point to null as the very beginning of the program
 **********/

public class ChangeTask {
    DataBase db;
    public ChangeTask(DataBase d) {
        db = d;
    }


    public void changeTask()
        throws IOException{

        if(db.getCurrentTask() != null) {
            db.getCurrentTask().addEtime(System.currentTimeMillis());
        }

        System.out.println("Enter new task, two enter to end: ");
        Task NewTask;
        String TaskInput = taskContentInput();
        if(TaskInput.equals("")){
            NewTask = null;
        }
        else {
            NewTask = new Task(System.currentTimeMillis(), 0, TaskInput);
        }
        db.addTask(NewTask);
    }

    private String taskContentInput()
        throws IOException {

        StringBuilder str = new StringBuilder();
        String temp;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        do {
            temp = br.readLine();
            str.append(temp+"\n");
        } while (!temp.equals(""));

        // the user type blank line to end so that we ignore the final '\n'
        return str.toString().substring(0, str.toString().length()-1);

    }
}
