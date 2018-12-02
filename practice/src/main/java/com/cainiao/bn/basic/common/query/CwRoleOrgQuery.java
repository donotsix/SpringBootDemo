package com.cainiao.bn.basic.common.query;

import java.util.Date;


public class CwRoleOrgQuery  extends BaseQuery{

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
     *  创建人花名
     */
    private String creator;

    /**
     *  修改人花名
     */
    private String modifier;

    /**
     *  组织id
     */
    private Long orgId;

    /**
     *  角色名称
     */
    private String roleName;

    /**
     *  删除标志 , 0未删除 , 1已删除
     */
    private Byte isDeleted;

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

    public void setCreator(String creator) {
        this.creator = creator;
     }

    public String getCreator() {
        return this.creator;
     }

    public void setModifier(String modifier) {
        this.modifier = modifier;
     }

    public String getModifier() {
        return this.modifier;
     }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
     }

    public Long getOrgId() {
        return this.orgId;
     }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
     }

    public String getRoleName() {
        return this.roleName;
     }

    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
     }

    public Byte getIsDeleted() {
        return this.isDeleted;
     }
}