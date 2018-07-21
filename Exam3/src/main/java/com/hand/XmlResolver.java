package com.hand;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;

public class XmlResolver implements Runnable {
    private String[] arr;
    public XmlResolver(String[] arr){
        this.arr = arr;
    }
    @Override
    public void run() {
        String str = "<xml><stock><name>"+arr[0]+"</name><open>"+arr[1]+
                "</open><close>"+arr[2]+"</close><current>"+arr[3]+"</current><high>"+
                arr[4]+"</high><low>"+arr[5]+"</low></stock></xml>";
        try {
            Document document = DocumentHelper.parseText(str);
            boolean isSuccess = HelpUtils.writeToFile(document.asXML(),"股票编码.xml");
            if(isSuccess){
                System.out.println("xml resovler success");
            }else{
                System.out.println("xml resovler fail");
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}
