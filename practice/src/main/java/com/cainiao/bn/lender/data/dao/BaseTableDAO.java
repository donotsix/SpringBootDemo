package com.cainiao.bn.lender.data.dao;

import java.util.List;

import org.com.code.util.TableBean;

public interface BaseTableDAO {
    
    List<TableBean> getTableBeans(String tableName);
    
}
