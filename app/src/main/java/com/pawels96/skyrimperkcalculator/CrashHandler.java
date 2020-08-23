package com.pawels96.skyrimperkcalculator;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CrashHandler implements Thread.UncaughtExceptionHandler {

    private Thread.UncaughtExceptionHandler defaultUEH;
    private String localPath;

    public CrashHandler(String localPath) {
        this.localPath = localPath;
        this.defaultUEH = Thread.getDefaultUncaughtExceptionHandler();
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {

        final Writer stringBuffSync = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(stringBuffSync);
        e.printStackTrace(printWriter);
        String stacktrace = stringBuffSync.toString();
        printWriter.close();

        if (localPath != null)
            writeToFile(stacktrace);

        defaultUEH.uncaughtException(t, e);
    }

    private void writeToFile(String currentStacktrace) {
        try {

            File dir = new File(Environment.getExternalStorageDirectory(),"Crash_Reports_Skyrim_perk_calculator");
            if (!dir.exists())
                dir.mkdirs();

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
            Date date = new Date();
            String filename = dateFormat.format(date) + ".txt";

            File reportFile = new File(dir, filename);
            FileWriter fileWriter = new FileWriter(reportFile);
            fileWriter.append(currentStacktrace);
            fileWriter.flush();
            fileWriter.close();
        } catch (Exception e) {
            Log.e("ExceptionHandler", e.getMessage());
        }
    }

}