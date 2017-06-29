package com.micro.mysegmentdefault.viewparser;

import com.micro.mysegmentdefault.utils.LogUtils;

import net.asfun.jangod.template.TemplateEngine;

import java.io.IOException;
import java.util.Map;

/**
 * author : micro_hx <p>
 * desc : 使用Jangod 解析文章关键字 <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/15 - 22:41 <p>
 * interface :
 */

public class NewsDetailParser {

    private static NewsDetailParser mParser ;

    private static TemplateEngine mEngine ;

    private NewsDetailParser(String parentDirPath) {
        mEngine = new TemplateEngine();
        mEngine.getConfiguration().setWorkspace(parentDirPath);
    }

    public static NewsDetailParser getInstance(String parentDirPath) {
        if(null == mParser) {
            synchronized (NewsDetailParser.class) {
                if(null == mParser) {
                    mParser = new NewsDetailParser(parentDirPath);
                }
            }
        }
        return mParser ;
    }

    public String render(String templateFilePath , Map<String,Object> data) {
        try {
            return mEngine.process(templateFilePath,data);
        }catch (IOException e) {
            LogUtils.d("render error :" + e);
            return "" ;
        }
    }
}
