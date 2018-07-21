package com.hand;

import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Hello world!
 *
 */
public class AppHttp
{

    public static String doHttpClientGet(String url){
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet(url);
        try (CloseableHttpResponse httpResponse = (CloseableHttpResponse) httpClient.execute(httpGet);){
            HttpEntity entity = httpResponse.getEntity();
            if(entity!=null){
                String  entityStr= EntityUtils.toString(entity,"utf-8");
                return entityStr;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void main( String[] args )
    {
        //String code = args[0];
        String code = "sh601006";
        String str = doHttpClientGet("http://hq.sinajs.cn/list="+code);
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
            new Thread(new XmlResolver(arr)).start();
        }
    }
}
