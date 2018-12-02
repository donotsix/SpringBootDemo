package com.cainiao.bn.lender.common.dto;

import java.io.Serializable;
import java.util.Date;


public class BnAgentLevelDTO  implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     *  主键
     */
    private Long id;

    /**
     *  创建时间
     */
    private Date gmtCreate;

    /**
     *  修改时间
     */
    private Date gmtModified;

    /**
     *  菜鸟账户id
     */
    private Long cainiaoUserId;

    /**
     *  代理商等级    byte 1金牌 , 2银牌 默认金牌1
     */
    private Byte level;

    /**
     *  等级生效年份
     */
    private Integer validYear;

    /**
     *  等级生效季度 123月为1季度 , 依次
     */
    private Integer validQuarter;

    /**
     *  目标单量(日均)
     */
    private Long targetOrderCount;

    /**
     *  实际单量
     */
    private Long actualOrderCount;

    /**
     *  创建人账户id(小二) , 为空时为系统定时任务自动创建
     */
    private Long createrId;

    /**
     *  是否删除 0未删除 1已删除 DeletedConst
     */
    private Byte deleted;

    public void setId(Long id) {
        this.id = id;
     }

    public Long getId() {
        return this.id;
     }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
     }

    public Date getGmtCreate() {
        return this.gmtCreate;
     }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
     }

    public Date getGmtModified() {
        return this.gmtModified;
     }

    public void setCainiaoUserId(Long cainiaoUserId) {
        this.cainiaoUserId = cainiaoUserId;
     }

    public Long getCainiaoUserId() {
        return this.cainiaoUserId;
     }

    public void setLevel(Byte level) {
        this.level = level;
     }

    public Byte getLevel() {
        return this.level;
     }

    public void setValidYear(Integer validYear) {
        this.validYear = validYear;
     }

    public Integer getValidYear() {
        return this.validYear;
     }

    public void setValidQuarter(Integer validQuarter) {
        this.validQuarter = validQuarter;
     }

    public Integer getValidQuarter() {
        return this.validQuarter;
     }

    public void setTargetOrderCount(Long targetOrderCount) {
        this.targetOrderCount = targetOrderCount;
     }

    public Long getTargetOrderCount() {
        return this.targetOrderCount;
     }

    public void setActualOrderCount(Long actualOrderCount) {
        this.actualOrderCount = actualOrderCount;
     }

    public Long getActualOrderCount() {
        return this.actualOrderCount;
     }

    public void setCreaterId(Long createrId) {
        this.createrId = createrId;
     }

    public Long getCreaterId() {
        return this.createrId;
     }

    public void setDeleted(Byte deleted) {
        this.deleted = deleted;
     }

    public Byte getDeleted() {
        return this.deleted;
     }
}