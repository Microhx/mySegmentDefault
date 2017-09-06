package com.micro.mysegmentdefault.entity;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/8 - 22:10 <p>
 * interface :
 */

public class MessageEvent {

    public TagDataEntity.Item item ;

    /**
     *
     * 已关注A       热门推荐B
     * A 取消  ----  B中取消  type为1
     *         ----  A 消失
     *
     * B 选中  ----  A 选中   type为2
     *
     * B 取消  ---- A 取消    type为3
     *
     *
     * 其他用户区分
     * -1  选中标签后重新排列
     *
     * 搜索 TAG 界面
     *          关注       type为6
     *          取消关注   type为7
     *
     *
     * 用户修改名字和头像      type为4
     *
     *
     */
    public int type ;




}
