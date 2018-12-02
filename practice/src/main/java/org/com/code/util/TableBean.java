package org.com.code.util;

public class TableBean {
    //字段名称
    private String columnName;
    //字段名称
    private String beanColumnName;
    //字段描述
    private String columnDesc;
    //字段类型
    private String columnType;
    //可空
    private Boolean isNullable;
    //是否主键
    private String columnKey;
    
    public String getBeanColumnName() {
        return beanColumnName;
    }
    public void setBeanColumnName(String beanColumnName) {
        this.beanColumnName = beanColumnName;
    }
    public String getColumnName() {
        return columnName;
    }
    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }
    public String getColumnDesc() {
        return columnDesc;
    }
    public void setColumnDesc(String columnDesc) {
        this.columnDesc = columnDesc;
    }
    public String getColumnType() {
        return columnType;
    }
    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }
    public Boolean getIsNullable() {
        return isNullable;
    }
    public void setIsNullable(Boolean isNullable) {
        this.isNullable = isNullable;
    }
    public String getColumnKey() {
        return columnKey;
    }
    public void setColumnKey(String columnKey) {
        this.columnKey = columnKey;
    }
    @Override
    public String toString() {
        return "TableBean [columnName=" + columnName + ", columnDesc=" + columnDesc + ", columnType=" + columnType
                + ", isNullable=" + isNullable + ", columnKey=" + columnKey + "]";
    }

    
}
