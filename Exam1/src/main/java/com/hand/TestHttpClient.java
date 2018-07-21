package com.hand;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.*;

public class TestHttpClient {

    public static void main(String[] args) {
        doHttpClientGet();
    }

    public static void doHttpClientGet(){
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet("http://192.168.11.205:18080/trainning/SampleChapter1.pdf");
        OutputStream fos = null;
        try (CloseableHttpResponse httpResponse = (CloseableHttpResponse) httpClient.execute(httpGet);){
            HttpEntity entity = httpResponse.getEntity();
            File directory = new File("Exam1/tmp/");
            if(!directory.exists()) {
                directory.mkdir();
            }
            File file = new File(directory.getAbsolutePath() + File.separatorChar+ "SampleChapter1.pdf");
            fos = new FileOutputStream(file);
            entity.writeTo(fos);
            System.out.println("download successful");
        } catch (IOException e) {
            System.out.println("download fail");
            e.printStackTrace();
        }finally {
            try {
                if(fos!=null)
                    fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
