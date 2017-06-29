package com.micro.mysegmentdefault.utils;

import android.content.Context;
import android.text.TextUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * author : micro_hx <p>
 * desc : 用户搜素记录 <p>
 * version1.0  保存在cache缓存中
 * <p>
 * email: javainstalling@163.com <p>
 * date : 2017/6/29 - 18:36 <p>
 * interface :
 */

public class SearchKeyWordsUtils {

    private static final String SEARCH_HISTORY_FILE = "search.data";

    private static final String SPLIT_TAG = "7_&_&_micro_&_&_7";

    /**
     * 获取搜素记录
     *
     * @param ctx
     * @return
     */
    public static List<String> getSearchHistoryWords(Context ctx) {
        List<String> searchList = new ArrayList<>();
        File cacheFile = new File(ctx.getCacheDir(), SEARCH_HISTORY_FILE);
        if (cacheFile.exists()) {
            String target = IOUtils.getStringFromFile(cacheFile);
            if (!TextUtils.isEmpty(target)) {
                searchList.addAll(Arrays.asList(target.split(SPLIT_TAG)));
            }
        }
        return searchList;

    }

    /**
     * 保存所有的搜素记录
     *
     * @param ctx
     */
    public static void saveNewKeyWords(Context ctx, Set<String> allKeyWords) {
        if(CommonUtils.collectionIsNull(allKeyWords)) return;

        StringBuilder sb = new StringBuilder();
        for (String s : allKeyWords) {
            sb.append(s).append(SPLIT_TAG);
        }

        String info = "";
        if (sb.toString().length() > SPLIT_TAG.length()) {
            info = sb.substring(0, sb.length() - SPLIT_TAG.length());
        }

        IOUtils.saveString2File(info, new File(ctx.getCacheDir(), SEARCH_HISTORY_FILE).getAbsolutePath());

    }

    /**
     * 删除所有记录
     */
    public static void clearAllSearchHistory(Context ctx) {
        FileUtils.deleteTargetFile(new File(ctx.getCacheDir(), SEARCH_HISTORY_FILE));


    }


}
