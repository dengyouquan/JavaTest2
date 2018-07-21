package com.hand;

import com.google.gson.JsonObject;


public class JsonResolver implements Runnable {
     private String[] arr;
     public JsonResolver(String[] arr){
         this.arr = arr;
     }

    @Override
    public void run() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name",arr[0]);
        jsonObject.addProperty("open",arr[1]);
        jsonObject.addProperty("close",arr[2]);
        jsonObject.addProperty("current",arr[3]);
        jsonObject.addProperty("high",arr[4]);
        jsonObject.addProperty("low",arr[5]);
        boolean isSuccess = HelpUtils.writeToFile(jsonObject.toString(),"股票编码.json");
        if(isSuccess){
            System.out.println("json resovler success");
        }else{
            System.out.println("json resovler fail");
        }
    }
}
