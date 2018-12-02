package com.cainiao.bn.basic.data.manager.impl;

import com.cainiao.bn.common.util.result.DataResult;
import com.cainiao.bn.common.util.result.Pagination;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import com.alibaba.fastjson.JSON;
import org.springframework.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.cainiao.bn.basic.common.query.CwRoleOrgQuery;
import com.cainiao.bn.basic.common.dto.CwRoleOrgDTO;
import com.cainiao.bn.basic.common.dataobject.CwRoleOrgDO;
import com.cainiao.bn.basic.data.dao.CwRoleOrgDAO;
import com.cainiao.bn.basic.data.manager.CwRoleOrgManager;

@Component
public class CwRoleOrgManagerImpl implements CwRoleOrgManager {

        private Logger logger = LoggerFactory.getLogger(this.getClass());

        @Autowired
        CwRoleOrgDAO cwRoleOrgDAO;

        @Override
        public Pagination<CwRoleOrgDTO> queryPage(CwRoleOrgQuery query){
            Pagination<CwRoleOrgDTO> pagination=new Pagination<>();
            List<CwRoleOrgDTO> list=cwRoleOrgDAO.queryPage(query).stream().map(p->{
            CwRoleOrgDTO dto = new CwRoleOrgDTO();
            BeanUtils.copyProperties(p, dto);
            return dto;
            }).collect(Collectors.toList());
            Long totalCount=queryCount(query);
            pagination.setDatas(list);
            pagination.setLimit(query.getQueryLimit());
            pagination.setStart(query.getQueryFrom());
            pagination.setTotalCount(totalCount);
            return pagination;
        }

        @Override
        public Long queryCount(CwRoleOrgQuery obj){
            Long totalCount=cwRoleOrgDAO.queryCount(obj);
            return totalCount;
        }

        @Override
        public CwRoleOrgDTO queryFirst(CwRoleOrgQuery query, Boolean ... ignoreMultiple){
            List<CwRoleOrgDO> dos=cwRoleOrgDAO.queryPage(query);
            if(!CollectionUtils.isEmpty(dos)){
                if(dos.size()>1){
                //总条数大于1条，默认是返回错误
                    if(ignoreMultiple.length==1&&ignoreMultiple[0]==true){
                        List<CwRoleOrgDTO> list=dos.stream().map(p->{
                            CwRoleOrgDTO dto = new CwRoleOrgDTO();
                            BeanUtils.copyProperties(p, dto);
                            return dto;
                        }).collect(Collectors.toList());
                        return list.get(0);
                    }else{
                        logger.error("Multiple record find in BnLeadsManagerImpl.queryFirst,CwRoleOrgQuery:{},ignoreMultiple:{}",JSON.toJSONString(query),ignoreMultiple);
                        throw new RuntimeException("存在多条记录");
                    }
                }else{
                    CwRoleOrgDTO dto = new CwRoleOrgDTO();
                    BeanUtils.copyProperties(dos.get(0), dto);
                    return dto;
                }
            }
            logger.error("No record find in BnLeadsManagerImpl.queryFirst,CwRoleOrgQuery:{},ignoreMultiple:{}",JSON.toJSONString(query),ignoreMultiple);
            throw new RuntimeException("未查询到记录");
        }

        @Override
        public Long save(CwRoleOrgDTO obj){
            check(obj);
            CwRoleOrgDO entry =new CwRoleOrgDO();
            BeanUtils.copyProperties(obj, entry);
            try {
                entry.setIsDeleted((byte) 0);
                cwRoleOrgDAO.insert(entry);
                //插入成功则返回插入的id
                return entry.getId();
            } catch (Exception e) {
                logger.error("Error caused in CwRoleOrgManagerImpl.save,param:{}",JSON.toJSONString(entry));
                logger.error(e.getMessage());
                throw new RuntimeException("插入失败");
            }
        }

        public void check(CwRoleOrgDTO obj){
            if(StringUtils.isEmpty(obj.getCreator())){
                logger.error("Creator can't be Null or Empty");
                throw new RuntimeException("创建人花名不能为空");
            }
            if(StringUtils.isEmpty(obj.getModifier())){
                logger.error("Modifier can't be Null or Empty");
                throw new RuntimeException("修改人花名不能为空");
            }
            if(null==obj.getOrgId()){
                logger.error("OrgId can't be Null or Empty");
                throw new RuntimeException("组织id不能为空");
            }
            if(StringUtils.isEmpty(obj.getRoleName())){
                logger.error("RoleName can't be Null or Empty");
                throw new RuntimeException("角色名称不能为空");
            }
            if(null==obj.getIsDeleted()){
                logger.error("IsDeleted can't be Null or Empty");
                throw new RuntimeException("删除标志 , 0未删除 , 1已删除不能为空");
            }
        }

        @Override
        public Long update(CwRoleOrgQuery entryQuery,CwRoleOrgDTO obj, Boolean ... ignoreMultiple){
            List<CwRoleOrgDO> list=cwRoleOrgDAO.queryPage(entryQuery);
            CwRoleOrgDO entryDO =new CwRoleOrgDO();
            BeanUtils.copyProperties(obj, entryDO);
            try {
                if(!CollectionUtils.isEmpty(list)){
                    if(list.size()!=1){
                        if(ignoreMultiple.length==1&&ignoreMultiple[0]==true){
                            //更新条件自行xml拓展，默认根据id查询更新，也就是说默认只更新一条！！！安全第一。
                            cwRoleOrgDAO.update(entryDO);
                            return list.get(0).getId();
                    }else{
                        throw new RuntimeException("存在多条数据");
                    }
                }else{
                        entryDO.setId(list.get(0).getId());
                        cwRoleOrgDAO.update(entryDO);
                        return list.get(0).getId();
                }
                }else{
                    throw new RuntimeException("未查询到数据");
                }
            } catch (Exception e) {
                logger.error("Error caused in CwRoleOrgManagerImpl.update,param:{}",JSON.toJSONString(entryDO));
                logger.error(e.getMessage());
                throw new RuntimeException("操作失败");
            }
        }

        @Override
        public Long saveOrUpdate(CwRoleOrgQuery entryQuery,CwRoleOrgDTO obj){
            List<CwRoleOrgDO> list=cwRoleOrgDAO.queryPage(entryQuery);
            CwRoleOrgDO entryDO =new CwRoleOrgDO();
            BeanUtils.copyProperties(obj, entryDO);
            try {
                if(!CollectionUtils.isEmpty(list)){
                    if(list.size()!=1){
                        throw new RuntimeException("存在多条数据");
                    }else{
                        entryDO.setId(list.get(0).getId());
                        cwRoleOrgDAO.update(entryDO);
                        return list.get(0).getId();
                    }
                }else{
                    return save(obj);
                }
            } catch (Exception e) {
                logger.error("Error caused in CwRoleOrgManagerImpl.update,param:{}",JSON.toJSONString(entryDO));
                logger.error(e.getMessage());
                throw new RuntimeException("操作失败");
            }
        }

        @Override
        public Long delete(CwRoleOrgDTO obj){
            CwRoleOrgDO entry =new CwRoleOrgDO();
            BeanUtils.copyProperties(obj, entry);
            entry.setIsDeleted((byte) 1);
            try {
                cwRoleOrgDAO.update(entry);
                return entry.getId();
            } catch (Exception e) {
                logger.error("Error caused in CwRoleOrgManagerImpl.delete,param:{}",JSON.toJSONString(entry));
                throw new RuntimeException("操作失败");
            }
        }

}