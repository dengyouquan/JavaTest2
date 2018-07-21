package com.hand;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;

public class XmlResolver1 implements Runnable {
    private String[] arr;
    public XmlResolver1(String[] arr){
        this.arr = arr;
    }
    @Override
    public void run() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            org.w3c.dom.Document document = builder.newDocument();
            Element root = document.createElement("xml");
            Element stock = document.createElement("stock");
            Element name = document.createElement("name");
            name.setTextContent(arr[0]);
            Element open = document.createElement("open");
            open.setTextContent(arr[1]);
            Element close = document.createElement("close");
            close.setTextContent(arr[2]);
            Element current = document.createElement("current");
            current.setTextContent(arr[3]);
            Element high = document.createElement("high");
            high.setTextContent(arr[4]);
            Element low = document.createElement("low");
            low.setTextContent(arr[5]);
            stock.appendChild(name);
            stock.appendChild(open);
            stock.appendChild(close);
            stock.appendChild(current);
            stock.appendChild(high);
            stock.appendChild(low);
            root.appendChild(stock);
            document.appendChild(root);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            StringWriter stringWriter = new StringWriter();
            transformer.transform(new DOMSource(document),new StreamResult(stringWriter));
            String str = new String(stringWriter.toString().getBytes(),"GBK");
            boolean isSuccess = HelpUtils.writeToFile(str,"股票编码.xml");
            if(isSuccess){
                System.out.println("xml resovler success");
            }else{
                System.out.println("xml resovler fail");
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


    }
}
