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
import com.cainiao.bn.basic.common.query.CwUserOrgQuery;
import com.cainiao.bn.basic.common.dto.CwUserOrgDTO;
import com.cainiao.bn.basic.common.dataobject.CwUserOrgDO;
import com.cainiao.bn.basic.data.dao.CwUserOrgDAO;
import com.cainiao.bn.basic.data.manager.CwUserOrgManager;

@Component
public class CwUserOrgManagerImpl implements CwUserOrgManager {

        private Logger logger = LoggerFactory.getLogger(this.getClass());

        @Autowired
        CwUserOrgDAO cwUserOrgDAO;

        @Override
        public Pagination<CwUserOrgDTO> queryPage(CwUserOrgQuery query){
            Pagination<CwUserOrgDTO> pagination=new Pagination<>();
            List<CwUserOrgDTO> list=cwUserOrgDAO.queryPage(query).stream().map(p->{
            CwUserOrgDTO dto = new CwUserOrgDTO();
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
        public Long queryCount(CwUserOrgQuery obj){
            Long totalCount=cwUserOrgDAO.queryCount(obj);
            return totalCount;
        }

        @Override
        public CwUserOrgDTO queryFirst(CwUserOrgQuery query, Boolean ... ignoreMultiple){
            List<CwUserOrgDO> dos=cwUserOrgDAO.queryPage(query);
            if(!CollectionUtils.isEmpty(dos)){
                if(dos.size()>1){
                //总条数大于1条，默认是返回错误
                    if(ignoreMultiple.length==1&&ignoreMultiple[0]==true){
                        List<CwUserOrgDTO> list=dos.stream().map(p->{
                            CwUserOrgDTO dto = new CwUserOrgDTO();
                            BeanUtils.copyProperties(p, dto);
                            return dto;
                        }).collect(Collectors.toList());
                        return list.get(0);
                    }else{
                        logger.error("Multiple record find in BnLeadsManagerImpl.queryFirst,CwUserOrgQuery:{},ignoreMultiple:{}",JSON.toJSONString(query),ignoreMultiple);
                        throw new RuntimeException("存在多条记录");
                    }
                }else{
                    CwUserOrgDTO dto = new CwUserOrgDTO();
                    BeanUtils.copyProperties(dos.get(0), dto);
                    return dto;
                }
            }
            logger.error("No record find in BnLeadsManagerImpl.queryFirst,CwUserOrgQuery:{},ignoreMultiple:{}",JSON.toJSONString(query),ignoreMultiple);
            throw new RuntimeException("未查询到记录");
        }

        @Override
        public Long save(CwUserOrgDTO obj){
            check(obj);
            CwUserOrgDO entry =new CwUserOrgDO();
            BeanUtils.copyProperties(obj, entry);
            try {
                entry.setIsDeleted((byte) 0);
                cwUserOrgDAO.insert(entry);
                //插入成功则返回插入的id
                return entry.getId();
            } catch (Exception e) {
                logger.error("Error caused in CwUserOrgManagerImpl.save,param:{}",JSON.toJSONString(entry));
                logger.error(e.getMessage());
                throw new RuntimeException("插入失败");
            }
        }

        public void check(CwUserOrgDTO obj){
            if(StringUtils.isEmpty(obj.getCreator())){
                logger.error("Creator can't be Null or Empty");
                throw new RuntimeException("创建人花名不能为空");
            }
            if(StringUtils.isEmpty(obj.getModifier())){
                logger.error("Modifier can't be Null or Empty");
                throw new RuntimeException("修改人花名不能为空");
            }
            if(StringUtils.isEmpty(obj.getAccount())){
                logger.error("Account can't be Null or Empty");
                throw new RuntimeException("人员账户花名不能为空");
            }
            if(StringUtils.isEmpty(obj.getAccountType())){
                logger.error("AccountType can't be Null or Empty");
                throw new RuntimeException("人员账户类型不能为空");
            }
            if(null==obj.getOrgId()){
                logger.error("OrgId can't be Null or Empty");
                throw new RuntimeException("所属组织id不能为空");
            }
            if(null==obj.getIsDeleted()){
                logger.error("IsDeleted can't be Null or Empty");
                throw new RuntimeException("删除标志 , 0未删除 , 1已删除不能为空");
            }
        }

        @Override
        public Long update(CwUserOrgQuery entryQuery,CwUserOrgDTO obj, Boolean ... ignoreMultiple){
            List<CwUserOrgDO> list=cwUserOrgDAO.queryPage(entryQuery);
            CwUserOrgDO entryDO =new CwUserOrgDO();
            BeanUtils.copyProperties(obj, entryDO);
            try {
                if(!CollectionUtils.isEmpty(list)){
                    if(list.size()!=1){
                        if(ignoreMultiple.length==1&&ignoreMultiple[0]==true){
                            //更新条件自行xml拓展，默认根据id查询更新，也就是说默认只更新一条！！！安全第一。
                            cwUserOrgDAO.update(entryDO);
                            return list.get(0).getId();
                    }else{
                        throw new RuntimeException("存在多条数据");
                    }
                }else{
                        entryDO.setId(list.get(0).getId());
                        cwUserOrgDAO.update(entryDO);
                        return list.get(0).getId();
                }
                }else{
                    throw new RuntimeException("未查询到数据");
                }
            } catch (Exception e) {
                logger.error("Error caused in CwUserOrgManagerImpl.update,param:{}",JSON.toJSONString(entryDO));
                logger.error(e.getMessage());
                throw new RuntimeException("操作失败");
            }
        }

        @Override
        public Long saveOrUpdate(CwUserOrgQuery entryQuery,CwUserOrgDTO obj){
            List<CwUserOrgDO> list=cwUserOrgDAO.queryPage(entryQuery);
            CwUserOrgDO entryDO =new CwUserOrgDO();
            BeanUtils.copyProperties(obj, entryDO);
            try {
                if(!CollectionUtils.isEmpty(list)){
                    if(list.size()!=1){
                        throw new RuntimeException("存在多条数据");
                    }else{
                        entryDO.setId(list.get(0).getId());
                        cwUserOrgDAO.update(entryDO);
                        return list.get(0).getId();
                    }
                }else{
                    return save(obj);
                }
            } catch (Exception e) {
                logger.error("Error caused in CwUserOrgManagerImpl.update,param:{}",JSON.toJSONString(entryDO));
                logger.error(e.getMessage());
                throw new RuntimeException("操作失败");
            }
        }

        @Override
        public Long delete(CwUserOrgDTO obj){
            CwUserOrgDO entry =new CwUserOrgDO();
            BeanUtils.copyProperties(obj, entry);
            entry.setIsDeleted((byte) 1);
            try {
                cwUserOrgDAO.update(entry);
                return entry.getId();
            } catch (Exception e) {
                logger.error("Error caused in CwUserOrgManagerImpl.delete,param:{}",JSON.toJSONString(entry));
                throw new RuntimeException("操作失败");
            }
        }

}