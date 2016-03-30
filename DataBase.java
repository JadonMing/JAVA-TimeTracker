// 6515826 zy15826 Zequn YU
package unnc.cs.zy15826;

import java.io.Serializable;
import java.util.ArrayList;

// Class specification
/**********
 * The 'DataBase' class is designed for storing records of tasks
 * The current task is separated from other tasks that are stored by
 *  using ArrayList
 * The class implements Serializable because it will be serialized and
 *  write into file in the future
 * The three methods are designed for adding task and getting tasks
 *
 * Notice that if Task points to null, it won't be recorded
 **********/

public class DataBase implements Serializable{

    private Task CurrentTask;
    private ArrayList<Task> ArrayListDb = new ArrayList<>();

    public void addTask(Task NewT) {
        if(CurrentTask != null) {
            ArrayListDb.add(CurrentTask);
        }
        CurrentTask = NewT;
    }

    public Task getCurrentTask() {
        return CurrentTask;
    }

    public ArrayList<Task> getAllTask() {
        return ArrayListDb;
    }

}
