package Domain;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class SystemLog {
    private static  SystemLog ourInstance = new SystemLog();
    private File logfile;

    public static SystemLog getInstance() {
        return ourInstance;
    }

    private SystemLog() {
        if (ourInstance != null){
            System.out.println("existing log");
        }
        this.makeFile();

    }

    private void makeFile() {
        logfile = new File("log/log.txt");
    }

    public  void UpdateLog(String note) {
        BufferedWriter bW = null;
        try {
            Date xdate = new Date();
            bW = new BufferedWriter(new FileWriter(logfile));
            bW.write(xdate.toString()+ " " + note);
            bW.newLine();
            bW.flush();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }

}


