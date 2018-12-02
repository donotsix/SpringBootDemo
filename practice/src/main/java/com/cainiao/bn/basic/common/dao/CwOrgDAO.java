package com.cainiao.bn.basic.data.dao;
import java.util.List;
import com.cainiao.bn.basic.common.dataobject.CwOrgDO;
import com.cainiao.bn.basic.common.query.CwOrgQuery;
import com.cainiao.bn.basic.common.dto.CwOrgDTO;

public interface CwOrgDAO {

        /**
        * @author system
        * 查询列表(可设置是否分页，默认分页)
        * @param CwOrgDO obj
        * @return List
        **/
        List<CwOrgDO> queryPage(CwOrgQuery query);

        /**
        * @author system
        * 查询列表总记录
        * @param CwOrgQuery obj
        * @return Long
        **/
        Long queryCount(CwOrgQuery obj);

        /**
        * @author system
        * 插入单条记录
        * @param CwOrgDO obj
        * @return Long
        **/
        Long insert(CwOrgDO obj);

        /**
        * @author system
        * 更新
        * @param CwOrgDO obj
        * @return Long
        **/
        Long update(CwOrgDO obj);

}