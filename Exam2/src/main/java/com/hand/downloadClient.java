package com.hand;

import java.io.*;
import java.net.Socket;

public class downloadClient {
    private static final String ADDRESS = "127.0.0.1";
    private static final int PORT = 28888;
    public static void main(String[] args) {
        OutputStream os = null;
        try(Socket socket = new Socket(ADDRESS,PORT);) {
            System.out.println("start download...");
            InputStream is = socket.getInputStream();
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
            System.out.println("download success");
            is.close();
        } catch (IOException e) {
            System.out.println("download fail");
            e.printStackTrace();
        }finally {
            try {
                if(os!=null)
                    os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
