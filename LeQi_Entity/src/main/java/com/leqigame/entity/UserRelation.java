package com.leqigame.entity;

/**
 * 用户关心实体
 * Created by zhuqing on 2017/9/17.
 */
public class UserRelation extends Entity {
    /**
     * 用户id
     */
    private Long userId;
    /**\
     * 好友Id
     */
    private Long friendId;
    /**
     * 好友名称，用户自定义
     */
    private String friendName;
    /**
     * 分组Id
     */
    private Long groupId;
}
