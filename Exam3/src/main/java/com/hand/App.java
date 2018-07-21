package com.hand;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Hello world!
 *
 */
public class App 
{
    public static String doGet(String urlStr) {
        URL url = null;
        URLConnection connection = null;
        BufferedReader br = null;
        try {
            System.out.println("download start");
            url = new URL(urlStr);
            connection = url.openConnection();
            br = new BufferedReader(new InputStreamReader(connection.getInputStream(),"GBK"));
            StringBuilder sb = new StringBuilder("");
            String line = null;
            while ((line=br.readLine())!=null){
                sb.append(line);
            }
            System.out.println(sb.toString());
            System.out.println("download successful");
            return sb.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("download fail");
            e.printStackTrace();
        }finally {
            try {
                if(br!=null)
                    br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    public static void main( String[] args )
    {
        //String code = args[0];
        String code = "sh601006";
        String str = doGet("http://hq.sinajs.cn/list="+code);
        if (str!=null && !str.equals("")){
            System.out.println("download data success");
            processStr(str);
            System.out.println("resovler success");
        }else{
            System.out.println("download data fail");
        }
    }

    private static void processStr(String str) {
        String[] arr = str.split("\"");
        if(arr.length>2){
            str = arr[1];
            arr = str.split(",",7);
            new Thread(new JsonResolver(arr)).start();
            new Thread(new XmlResolver1(arr)).start();
        }
    }
}
