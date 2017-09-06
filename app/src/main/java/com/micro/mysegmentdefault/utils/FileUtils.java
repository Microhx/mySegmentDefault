package com.micro.mysegmentdefault.utils;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;

import com.micro.mysegmentdefault.base.SegmentApplication;
import com.micro.mysegmentdefault.entity.TagDataEntity;
import com.micro.mysegmentdefault.entity.TitleEntity;
import com.micro.mysegmentdefault.logic.UserLogic;
import com.micro.mysegmentdefault.network.Api;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/6 - 16:49 <p>
 * interface :
 */

public class FileUtils {

    public static final String FILE_SUFFIX = "micro_";

    /**
     * 获取头条中中顶部数据，从assert文件夹中获取
     *
     * @return
     */
    public static List<TitleEntity> readHomeTitleEntity(Context ctx) {
        List<TitleEntity> entityList = new ArrayList<>();
        try {
            String info = IOUtils.getStringFromInputStream(ctx.getAssets().open("home_list.json"));
            getTitleEntityInfos(entityList, info, false);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return entityList;
    }


    private static void getTitleEntityInfos(List<TitleEntity> entityList, String msg, boolean onlyOpen) throws JSONException {
        JSONArray array = new JSONArray(msg);
        int length = array.length();

        TitleEntity entity;
        for (int i = 0; i < length; i++) {
            entity = new TitleEntity();
            JSONObject obj = array.getJSONObject(i);
            entity.name = obj.getString("name");
            entity.channel = obj.getString("channel");
            entity.open = obj.getBoolean("open");
            if (onlyOpen) {
                if (entity.open) {
                    entityList.add(entity);
                }
            } else {
                entityList.add(entity);
            }
        }
    }


    public static List<TagDataEntity.Item> getArticlesTitleEntityList(int index) {
        List<TagDataEntity.Item> mItemList = new ArrayList<>();

        TagDataEntity.Item item1 = new TagDataEntity.Item();
        item1.id = index == 0 ? "recommendable" : "newest";
        item1.name = index == 0 ? "推荐的" : "最新的";

        TagDataEntity.Item item2 = new TagDataEntity.Item();
        item2.id = index == 0 ? "hottest" : "hottest";
        item2.name = index == 0 ? "热门的" : "热门的";

        TagDataEntity.Item item3 = new TagDataEntity.Item();
        item3.id = index == 0 ? "newest" : "unanswered";
        item3.name = index == 0 ? "全部的" : "未回答的";

        mItemList.add(item1);
        mItemList.add(item2);
        mItemList.add(item3);

        //get the chased tags ;
        if (UserLogic.checkUserLogin()) {
            File saveFilePath = new File(SegmentApplication.getApplication().
                    getExternalCacheDir().getAbsoluteFile() ,FILE_SUFFIX +  UserLogic.getUserId());

            if (saveFilePath.exists()) {
                String content = IOUtils.getStringFromFile(saveFilePath);
                addData(content, mItemList);
            }
        }

        return mItemList;
    }


    public static TagDataEntity getUserTagDataEntity() {
        if (!UserLogic.checkUserLogin()) return null;

        File saveFilePath = new File(SegmentApplication.getApplication().
                getExternalCacheDir().getAbsoluteFile() ,FILE_SUFFIX + UserLogic.getUserId());

        if (!saveFilePath.exists()) return null;

        String content = IOUtils.getStringFromFile(saveFilePath);
        TagDataEntity dataEntity = new TagDataEntity();

        dataEntity.data = new TagDataEntity.Data();
        dataEntity.data.rows = new ArrayList<>();
        addData(content, dataEntity.data.rows);

        return dataEntity;
    }

    private static void addData(String content, List<TagDataEntity.Item> list) {
        try {
            JSONArray array = new JSONArray(content);
            int length = array.length();

            TagDataEntity.Item item;
            for (int i = 0; i < length; i++) {
                JSONObject obj = array.getJSONObject(i);

                item = new TagDataEntity.Item();
                item.name = obj.getString("name");
                item.id = obj.getString("id");
                item.isFollowed = true;

                list.add(item);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param tagItems
     */
    public static void saveTagDataEntity(Context ctx, List<TagDataEntity.Item> tagItems) {
        if (!UserLogic.checkUserLogin()) return;

        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject;
        try {
            for (int i = 0, len = tagItems.size(); i < len; i++) {
                TagDataEntity.Item entity = tagItems.get(i);

                jsonObject = new JSONObject();
                jsonObject.put("id", entity.id);
                jsonObject.put("name", entity.name);
                jsonArray.put(jsonObject);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        IOUtils.saveString2File(jsonArray.toString(),
                                ctx.getExternalCacheDir().getAbsolutePath() +
                                File.separator + FILE_SUFFIX + UserLogic.getUserId());
    }

    /**
     * 获取目标targetFile
     * <p>
     * 将asset中的文件复制到
     *
     * @param ctx
     * @return
     */
    public static File getDirData(Context ctx, String assetName, String targetFileName) {
        File newFile = new File(ctx.getExternalCacheDir(), targetFileName);
        //if (!newFile.exists()) {
            try {
                String info = IOUtils.getStringFromInputStream(ctx.getAssets().open(assetName));
                IOUtils.writeContent2File(newFile, info);
            } catch (IOException e) {
                e.printStackTrace();
            }
        //}
        return newFile;

    }

    public static String replaceAllImagePath(String htmlContent) {
        Document document = Jsoup.parse(htmlContent);

        Iterator localIterator = document.select(".fmt img").iterator();
        while (localIterator.hasNext()) {
            Element localElement = (Element)localIterator.next();
            String str1 = localElement.attr("src");
            if (!str1.contains("/img/remote"))
                continue;

            String[] arrayOfString1 = str1.split("\\?");
            if (arrayOfString1.length < 2)
                continue;
            String[] arrayOfString2 = arrayOfString1[1].split("&");
            int i = arrayOfString2.length;

            for(int j = 0 ; j < i ; j++) {
                String str2 = arrayOfString2[j];
                if(str2.contains("w=")) {
                    localElement.attr("width", str2.split("=")[1]);
                }

                if(str2.contains("h=")) {
                    localElement.attr("height", str2.split("=")[1]);
                }
            }

            localElement.attr("data-original", str1);
            localElement.attr("src", Api.BASE_URL + str1);
        }

        return document.toString() ;
    }

    public static File createTempFile() {
        String str1 = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.CHINA).format(new Date());
        String str2 = "PNG_" + str1 + "_";
        File localFile1 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        try {
            File localFile2 = File.createTempFile(str2, ".png", localFile1);
            return localFile2;
        } catch (IOException e) {

        }
        return null;
    }


    public static void deleteTargetFile(File file) {
        if(null != file && file.exists()) {
            file.delete();
        }
    }
}
