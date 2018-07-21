package com.hand;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class TestGet {
    public static void main(String[] args) {
        doGet();
    }
    public static void doGet() {
        URL url = null;
        URLConnection connection = null;
        InputStream is = null;
        OutputStream os = null;
        try {
            System.out.println("download start");
            url = new URL("http://192.168.11.205:18080/trainning/SampleChapter1.pdf");
            connection = url.openConnection();
            is = connection.getInputStream();
            File directory = new File("tmp/");
            if(!directory.exists()) {
                directory.mkdir();
            }
            File file = new File(directory.getAbsolutePath() + File.separatorChar+ "SampleChapter1.pdf");
            os = new FileOutputStream(file);
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = is.read(buffer))!=-1){
                os.write(buffer,0,len);
                os.flush();
            }
            System.out.println("download successful");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("download fail");
            e.printStackTrace();
        }finally {
                try {
                    if(os!=null)
                        os.close();
                    if(is!=null)
                        is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
