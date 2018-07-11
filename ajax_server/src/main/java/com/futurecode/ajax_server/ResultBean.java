package com.futurecode.ajax_server;

/**
 * @author :lvweihao@outlook.com
 * @description:
 * @date :create in 20:05 18.5.21
 * @modify by :
 */
public class ResultBean {
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    private String data;

    public ResultBean(String data) {
        this.data = data;
    }
}
