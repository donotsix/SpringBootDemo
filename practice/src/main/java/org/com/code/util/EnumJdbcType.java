package org.com.code.util;

public enum EnumJdbcType {
    STRING("String", "varchar","VARCHAR"), 
    INTEGER("Integer", "int","INTEGER"),
    DOUBLE("Double", "double","DOUBLE"),
    BOOLEAN("Boolean", "boolean","BOOLEAN"),
    TINYINT("Byte", "tinyint","TINYINT"),
    DATE("Date", "datetime","TIMESTAMP"),
    LONG("Long", "bigint","BIGINT");
    
    // 成员变量  
    private String javaType;  
    private String columnType;  
    private String jdbcType;  
    
    // 构造方法  
    private EnumJdbcType(String javaType, String columnType,String jdbcType) {  
        this.javaType = javaType;  
        this.columnType = columnType;  
        this.jdbcType = jdbcType;  
    }  
    
    // 获取JavaType方法  
    public static String getJavaType(String columnType) {  
        for (EnumJdbcType c : EnumJdbcType.values()) {  
            if (c.getColumnType().equals(columnType)) {  
                return c.javaType;  
            }  
        }  
        return null;  
    }  
    
    // 获取JabcType方法  
    public static String getJdbcType(String columnType) {  
        for (EnumJdbcType c : EnumJdbcType.values()) {  
            if (c.getColumnType().equals(columnType)) {  
                return c.jdbcType;  
            }  
        }  
        return null;  
    } 
    // get set 方法  
    public String getJavaType() {
        return javaType;
    }
    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public String getJdbcType() {
        return jdbcType;
    }

    public void setJdbcType(String jdbcType) {
        this.jdbcType = jdbcType;
    }

   
}
