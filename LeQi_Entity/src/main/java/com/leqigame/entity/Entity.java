package com.leqigame.entity;

/**
 * Created by zhuqing on 2017/7/25.
 */
public class Entity {
    private Long id;
    /**
     * 创建日期
     */
    private Long createDate;
    /**
     * 更新日期
     */
    private Long updateDate;
    /**
     * 状态
     */
    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }

    public Long getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Long updateDate) {
        this.updateDate = updateDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
