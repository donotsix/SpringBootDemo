package com.futurecode.springbootlog1.dao;

import com.futurecode.springbootlog1.bean.RoncooUser;
import com.futurecode.springbootlog1.dao.base.Page;

public interface RoncooUserDao {

    int insert(RoncooUser roncooUser);

    int deleteById(int id);

    int updateById(RoncooUser roncooUser);

    RoncooUser selectById(int id);

    Page<RoncooUser> queryForPage(int pageCurrent, int pageSize, String name);
}
