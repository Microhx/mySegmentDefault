package com.micro.mysegmentdefault.utils;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * author : micro_hx <p>
 * desc : 对HTML的处理类<p>
 * email: javainstalling@163.com <p>
 * date : 2017/6/7 - 16:54 <p>
 * interface :
 */

public class HTMLMedia {

    private StringBuilder mContentDisplayStringBuilder = new StringBuilder();
    private List<HTMLMediaItem> mItems;

    public HTMLMedia(String msg) {
        if(!msg.startsWith("<body>")) {
            msg = String.format("<body>%s</body>" , new Object[]{msg});
        }
            msg = msg.replaceAll("&","&amp;");

        mItems = new ArrayList<>();

        try{
            parse(msg);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private void parse(String paramsString) throws ParserConfigurationException,IOException,SAXException {
        Element localElement = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(paramsString))).getDocumentElement();
        localElement.normalize();
        parseElement(localElement);
    }

    private void parseElement(Node paramNode) {
        HTMLMediaItem item = null ;
        if(paramNode.getNodeType() == 3) {
            this.mContentDisplayStringBuilder.append(paramNode.getNodeValue());
            return;
        }

        if(paramNode instanceof  Element) {
            Element localElement = (Element) paramNode;
            NodeList nodeList =  localElement.getChildNodes();
            for(int i = 0, len = nodeList.getLength() ; i < len ; i++) {

                Node node =  nodeList.item(i);
                //如果不是Element Node 直接获取内部数据即可
                if(node.getNodeType() != Node.ELEMENT_NODE) {
                    String textValue = node.getTextContent();
                    this.mContentDisplayStringBuilder.append(textValue);
                    continue;
                }

                Element element = (Element) nodeList.item(i);
                if(null == element) continue;

                if(element.getTagName().equals("a")) {
                    String title = element.getTextContent();
                    String href = element.getAttribute("href");

                    item = new HTMLMediaItem();
                    item.mDisplayString = title;
                    item.mLinkString = href;
                    item.start = mContentDisplayStringBuilder.length();
                    item.end = item.start + title.length();
                    mContentDisplayStringBuilder.append(title);
                    mItems.add(item);
                    continue;
                }

                if(element.getAttribute("class").contains("hidden")) continue;

                if(element.getTagName().equals("span")) continue;

                String textValue = element.getTextContent();
                this.mContentDisplayStringBuilder.append(textValue);
            }

        }else {
            //
            String textValue = paramNode.getTextContent();
            this.mContentDisplayStringBuilder.append(textValue);
        }

    }

    public List<HTMLMediaItem> getItems(){
        return mItems;
    }

    public String getDisplayString() {
        return this.mContentDisplayStringBuilder.toString();
    }


    public static class HTMLMediaItem {
        public int start , end ;
        public String mDisplayString , mLinkString;
    }


    /**
     *
     if(element.hasChildNodes()) {
     for(int _i = 0 , _len = element.getChildNodes().getLength(); _i<_len ; _i++) {
     parseElement(element.getChildNodes().item(_i));
     }
     }
     *
     */
}
