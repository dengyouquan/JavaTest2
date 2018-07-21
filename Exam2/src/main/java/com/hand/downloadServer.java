package com.hand;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class downloadServer {
    private static final int PORT = 28888;
    public static void main(String[] args) {
        InputStream fis = null;
        try(ServerSocket serverSocket = new ServerSocket(PORT);) {
            File directory = new File("../Exam1/tmp/");
            if(!directory.exists()) {
                System.out.println("file not exists");
                return;
            }
            File file = new File(directory.getAbsolutePath() + File.separatorChar+ "SampleChapter1.pdf");
            fis = new FileInputStream(file);
            System.out.println("download server start...");
            Socket socket = serverSocket.accept();
            OutputStream os = socket.getOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = fis.read(buffer))!=-1){
                os.write(buffer,0,len);
                os.flush();
            }
            System.out.println("file download success。。。");
            PrintWriter pw = new PrintWriter(socket.getOutputStream());
            pw.write("file download success!");
            pw.flush();
            os.close();
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(fis!=null)
                    fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
