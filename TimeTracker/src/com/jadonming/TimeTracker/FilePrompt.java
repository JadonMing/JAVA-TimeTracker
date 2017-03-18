package com.jadonming.TimeTracker;

import java.io.*;

// Class specification
/**********
 * The class 'FilePrompt' is designed for writing and reading 'DataBase'
 *  (includes every task) to and from the file
 * The two methods are used for writing and reading respectively
 *  using ObjectOutputStream and ObjectInputStream
 *
 * Reading from file occurs at the beginning of program running
 * Writing to file occurs at:
 * ->The first creating the file and database
 * ->Every time the 'ChangeTask' is finished, which is obviously the only
 *  way to change database (option 1)
 **********/

public class FilePrompt {


    public void fileOutput(DataBase db)
            throws IOException {
        FileOutputStream fOut = new FileOutputStream("timetracker.data");
        ObjectOutputStream oos = new ObjectOutputStream(fOut);
        oos.writeObject(db);
        oos.close();
        fOut.close();
    }

    public DataBase fileInput()
            throws IOException, ClassNotFoundException {
        DataBase db;
        File f = new File("timetracker.data");
        if(!f.exists()) {
            f.createNewFile();
            db = new DataBase();
            fileOutput(db);
        }
        else {
            FileInputStream fIn = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fIn);
            db = (DataBase) ois.readObject();
            ois.close();
            fIn.close();
        }
        return db;
    }


}
