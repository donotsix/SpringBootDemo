package com.cainiao.bn.basic.data.dao;
import java.util.List;
import com.cainiao.bn.basic.common.dataobject.CwRoleOrgDO;
import com.cainiao.bn.basic.common.query.CwRoleOrgQuery;
import com.cainiao.bn.basic.common.dto.CwRoleOrgDTO;

public interface CwRoleOrgDAO {

        /**
        * @author system
        * 查询列表(可设置是否分页，默认分页)
        * @param CwRoleOrgDO obj
        * @return List
        **/
        List<CwRoleOrgDO> queryPage(CwRoleOrgQuery query);

        /**
        * @author system
        * 查询列表总记录
        * @param CwRoleOrgQuery obj
        * @return Long
        **/
        Long queryCount(CwRoleOrgQuery obj);

        /**
        * @author system
        * 插入单条记录
        * @param CwRoleOrgDO obj
        * @return Long
        **/
        Long insert(CwRoleOrgDO obj);

        /**
        * @author system
        * 更新
        * @param CwRoleOrgDO obj
        * @return Long
        **/
        Long update(CwRoleOrgDO obj);

}