package com.micro.mysegmentdefault.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/6 - 16:52 <p>
 * interface :
 */

public class IOUtils {

    public static String getStringFromInputStream(InputStream inputStream) {
        BufferedReader bStream = null ;
        try {
            StringBuilder sb = new StringBuilder();
            bStream = new BufferedReader(new InputStreamReader(inputStream));
            String line ;
            while((line = bStream.readLine()) != null) {
                sb.append(line) ;
            }
            return sb.toString() ;
        }catch (IOException e ) {
            LogUtils.d("read IOException : " + e.getMessage()) ;
        }finally {
            closeQuiet(bStream);
        }

        return null ;
    }


    public static String getStringFromFile(File file) {
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = null ;
        try{
            reader = new BufferedReader(new FileReader(file));
            String line  ;
            while((line = reader.readLine()) != null) {
                sb.append(line);
            }
        }catch (IOException e) {
            LogUtils.d("read IOException : " + e.getMessage()) ;
        }finally {
            closeQuiet(reader);
        }

        return sb.toString();
    }


    public static String getStringFromFile(String filePath) {
        return getStringFromFile(new File(filePath));
    }


    /**
     * 保存字符串到文件
     * @param content
     * @param filePath
     */
    public static void saveString2File(String content , String filePath) {
        BufferedWriter writer = null ;
        try {
            writer = new BufferedWriter(new FileWriter(filePath));
            writer.write(content);
            writer.flush();
        }catch (IOException e) {
            e.printStackTrace();
        }finally{
            closeQuiet(writer);
        }
    }


    public static void closeQuiet(Closeable close) {
        if(null != close) {
            try {
                close.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static void writeContent2File(File newFile, String info) {
        BufferedWriter bWriter = null ;
        try {
            bWriter = new BufferedWriter(new FileWriter(newFile)) ;
            bWriter.write(info);
            bWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            closeQuiet(bWriter);
        }
    }
}
