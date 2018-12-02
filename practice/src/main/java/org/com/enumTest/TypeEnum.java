package org.com.enumTest;

public enum TypeEnum {
    Type("a");
    
    TypeEnum(String aString) {
        this.aString = aString;
    }

    private String aString;

    public String getaString() {
        return aString;
    }

    public void setaString(String aString) {
        this.aString = aString;
    }

}
