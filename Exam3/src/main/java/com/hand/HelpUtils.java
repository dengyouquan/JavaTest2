package com.hand;

import java.io.*;

public class HelpUtils {
    public static boolean writeToFile(String content,String fileName){
        PrintWriter bw = null;
        try {
            File directory = new File("tmp/");
            if(!directory.exists()) {
                directory.mkdir();
            }
            File file = new File(directory.getAbsolutePath() + File.separatorChar+ fileName);
            bw = new PrintWriter(file,"UTF-8");
            bw.write(content);
            return true;
        }catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(bw!=null)
                bw.close();
        }
        return false;
    }
}
