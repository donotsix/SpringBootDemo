package com.cainiao.bn.basic.data.manager;

import com.cainiao.bn.common.util.result.DataResult;
import com.cainiao.bn.common.util.result.Pagination;
import com.cainiao.bn.basic.common.query.CwOrgQuery;
import com.cainiao.bn.basic.common.dto.CwOrgDTO;

public interface CwOrgManager {

        /**
        * @author system
        * 查询列表(可设置是否分页，默认分页)
        * isNeedPagination=false(不分页)
        * @param CwOrgQuery query
        * @return List
        **/
        Pagination<CwOrgDTO> queryPage(CwOrgQuery query);

        /**
        * @author system
        * 查询记录数量
        * @param CwOrgQuery obj
        * @return Long
        **/
        Long queryCount(CwOrgQuery obj);

        /**
        * @author system
        * 重载方法，根据条件获取最新一条记录(默认更新时间倒叙)，可选数据重复是否报错
        * @param CwOrgQuery query,Boolean ignoreMultiple)
        * @return CwOrgDTO
        **/
        CwOrgDTO queryFirst(CwOrgQuery query,Boolean ... ignoreMultiple);

        /**
        * @author system
        * 插入(无视数据是否存在都进行插入)
        * @param CwOrgDTO obj
        * @return 返回插入或者更新的id
        **/
        Long save(CwOrgDTO obj);

        /**
        * @author system
        * 根据查询条件query查询数据，ignoreMultiple可选变量，为true的时候允许批量变更，需要自行xml拓展，否则只允许变更单条
        * @param CwOrgQuery query,CwOrgDTO obj,Boolean ... ignoreMultiple
        * @return 返回更新的id
        **/
        Long update(CwOrgQuery query,CwOrgDTO obj,Boolean ... ignoreMultiple);

        /**
        * @author system
        * 根据查询条件查询，判断是否插入或者更新单条记录(有：更新，无：新增)
        * @param CwOrgQuery query,CwOrgDTO obj
        * @return 返回插入或者更新的id
        **/
        Long saveOrUpdate(CwOrgQuery query,CwOrgDTO obj);

        /**
        * @author system
        * 删除单条记录
        * @param CwOrgDTO obj
        * @return 返回删除的id
        **/
        Long delete(CwOrgDTO obj);

}