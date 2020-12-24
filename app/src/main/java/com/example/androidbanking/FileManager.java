package com.example.androidbanking;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class FileManager {
    /**
     * Helper method to write text to files
     * @param mcoContext
     * @param name
     * @param content
     */
    public static void writeFile(Context mcoContext, String name, String content) {
            File dir = new File(mcoContext.getFilesDir(), "bankFiles");
            if(!dir.exists()){
                dir.mkdir();
            }
            try {
                File gpxfile = new File(dir, name);
                FileWriter writer = new FileWriter(gpxfile);
                // write content
                writer.write(content);
                writer.write("\n");
                writer.flush();
                writer.close();
            } catch (Exception e){
                e.printStackTrace();
            }
    }

    /**
     * Helper method to read text from files
     * @param mcoContext
     * @param name
     * @return
     */
    public static String readFile(Context mcoContext, String name) {
        // get directory
        File dir = new File(mcoContext.getFilesDir(), "bankFiles");
        if(!dir.exists()){
            dir.mkdir();
        }
        try {
            // get file
            File file = new File(dir, name);
            StringBuilder text = new StringBuilder();
            // used buffered reader
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                text.append(line);
                text.append('\n');
            }
            br.close();
            // convert to string
            return text.toString();
        } catch (Exception e){

            return "";
        }

    }
}
