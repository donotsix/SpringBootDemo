package com.cainiao.bn.basic.data.dao;
import java.util.List;
import com.cainiao.bn.basic.common.dataobject.CwUserOrgDO;
import com.cainiao.bn.basic.common.query.CwUserOrgQuery;
import com.cainiao.bn.basic.common.dto.CwUserOrgDTO;

public interface CwUserOrgDAO {

        /**
        * @author system
        * 查询列表(可设置是否分页，默认分页)
        * @param CwUserOrgDO obj
        * @return List
        **/
        List<CwUserOrgDO> queryPage(CwUserOrgQuery query);

        /**
        * @author system
        * 查询列表总记录
        * @param CwUserOrgQuery obj
        * @return Long
        **/
        Long queryCount(CwUserOrgQuery obj);

        /**
        * @author system
        * 插入单条记录
        * @param CwUserOrgDO obj
        * @return Long
        **/
        Long insert(CwUserOrgDO obj);

        /**
        * @author system
        * 更新
        * @param CwUserOrgDO obj
        * @return Long
        **/
        Long update(CwUserOrgDO obj);

}