package com.jadonming.TimeTracker;

import java.io.Serializable;

// Class specification
/**********
 * The 'Task' class is designed for recording single task
 * It stores the begin time and end time of the task and the content
 *  of the task
 * Four methods is used for adding end time (which is initialized to 0
 *  when the task is not terminated) and getting information of the task
 **********/

@SuppressWarnings("serial")
public class Task implements Serializable {
    private long beginTime;
    private long endTime;
    String TaskContent;

    public Task(long bTime, long eTime, String TaskInput) {
        beginTime = bTime;
        endTime = eTime;
        TaskContent = TaskInput;
    }

    public long returnBtime() {
        return beginTime;
    }

    public long returnEtime() {
        return endTime;
    }

    public String returnTaskContent() {
        return TaskContent;
    }

    public void addEtime(long Etime) {
        endTime = Etime;
    }

}
