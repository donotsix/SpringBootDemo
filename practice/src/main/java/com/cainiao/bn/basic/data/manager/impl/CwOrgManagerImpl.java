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
import com.cainiao.bn.basic.common.query.CwOrgQuery;
import com.cainiao.bn.basic.common.dto.CwOrgDTO;
import com.cainiao.bn.basic.common.dataobject.CwOrgDO;
import com.cainiao.bn.basic.data.dao.CwOrgDAO;
import com.cainiao.bn.basic.data.manager.CwOrgManager;

@Component
public class CwOrgManagerImpl implements CwOrgManager {

        private Logger logger = LoggerFactory.getLogger(this.getClass());

        @Autowired
        CwOrgDAO cwOrgDAO;

        @Override
        public Pagination<CwOrgDTO> queryPage(CwOrgQuery query){
            Pagination<CwOrgDTO> pagination=new Pagination<>();
            List<CwOrgDTO> list=cwOrgDAO.queryPage(query).stream().map(p->{
            CwOrgDTO dto = new CwOrgDTO();
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
        public Long queryCount(CwOrgQuery obj){
            Long totalCount=cwOrgDAO.queryCount(obj);
            return totalCount;
        }

        @Override
        public CwOrgDTO queryFirst(CwOrgQuery query, Boolean ... ignoreMultiple){
            List<CwOrgDO> dos=cwOrgDAO.queryPage(query);
            if(!CollectionUtils.isEmpty(dos)){
                if(dos.size()>1){
                //总条数大于1条，默认是返回错误
                    if(ignoreMultiple.length==1&&ignoreMultiple[0]==true){
                        List<CwOrgDTO> list=dos.stream().map(p->{
                            CwOrgDTO dto = new CwOrgDTO();
                            BeanUtils.copyProperties(p, dto);
                            return dto;
                        }).collect(Collectors.toList());
                        return list.get(0);
                    }else{
                        logger.error("Multiple record find in BnLeadsManagerImpl.queryFirst,CwOrgQuery:{},ignoreMultiple:{}",JSON.toJSONString(query),ignoreMultiple);
                        throw new RuntimeException("存在多条记录");
                    }
                }else{
                    CwOrgDTO dto = new CwOrgDTO();
                    BeanUtils.copyProperties(dos.get(0), dto);
                    return dto;
                }
            }
            logger.error("No record find in BnLeadsManagerImpl.queryFirst,CwOrgQuery:{},ignoreMultiple:{}",JSON.toJSONString(query),ignoreMultiple);
            throw new RuntimeException("未查询到记录");
        }

        @Override
        public Long save(CwOrgDTO obj){
            check(obj);
            CwOrgDO entry =new CwOrgDO();
            BeanUtils.copyProperties(obj, entry);
            try {
                entry.setIsDeleted((byte) 0);
                cwOrgDAO.insert(entry);
                //插入成功则返回插入的id
                return entry.getId();
            } catch (Exception e) {
                logger.error("Error caused in CwOrgManagerImpl.save,param:{}",JSON.toJSONString(entry));
                logger.error(e.getMessage());
                throw new RuntimeException("插入失败");
            }
        }

        public void check(CwOrgDTO obj){
            if(StringUtils.isEmpty(obj.getCreator())){
                logger.error("Creator can't be Null or Empty");
                throw new RuntimeException("创建人花名不能为空");
            }
            if(StringUtils.isEmpty(obj.getModifier())){
                logger.error("Modifier can't be Null or Empty");
                throw new RuntimeException("修改人花名不能为空");
            }
            if(StringUtils.isEmpty(obj.getOrgName())){
                logger.error("OrgName can't be Null or Empty");
                throw new RuntimeException("组织名称不能为空");
            }
            if(StringUtils.isEmpty(obj.getOrgDesc())){
                logger.error("OrgDesc can't be Null or Empty");
                throw new RuntimeException("组织描述不能为空");
            }
            if(null==obj.getIsDeleted()){
                logger.error("IsDeleted can't be Null or Empty");
                throw new RuntimeException("删除标志 , 0未删除 1已删除不能为空");
            }
        }

        @Override
        public Long update(CwOrgQuery entryQuery,CwOrgDTO obj, Boolean ... ignoreMultiple){
            List<CwOrgDO> list=cwOrgDAO.queryPage(entryQuery);
            CwOrgDO entryDO =new CwOrgDO();
            BeanUtils.copyProperties(obj, entryDO);
            try {
                if(!CollectionUtils.isEmpty(list)){
                    if(list.size()!=1){
                        if(ignoreMultiple.length==1&&ignoreMultiple[0]==true){
                            //更新条件自行xml拓展，默认根据id查询更新，也就是说默认只更新一条！！！安全第一。
                            cwOrgDAO.update(entryDO);
                            return list.get(0).getId();
                    }else{
                        throw new RuntimeException("存在多条数据");
                    }
                }else{
                        entryDO.setId(list.get(0).getId());
                        cwOrgDAO.update(entryDO);
                        return list.get(0).getId();
                }
                }else{
                    throw new RuntimeException("未查询到数据");
                }
            } catch (Exception e) {
                logger.error("Error caused in CwOrgManagerImpl.update,param:{}",JSON.toJSONString(entryDO));
                logger.error(e.getMessage());
                throw new RuntimeException("操作失败");
            }
        }

        @Override
        public Long saveOrUpdate(CwOrgQuery entryQuery,CwOrgDTO obj){
            List<CwOrgDO> list=cwOrgDAO.queryPage(entryQuery);
            CwOrgDO entryDO =new CwOrgDO();
            BeanUtils.copyProperties(obj, entryDO);
            try {
                if(!CollectionUtils.isEmpty(list)){
                    if(list.size()!=1){
                        throw new RuntimeException("存在多条数据");
                    }else{
                        entryDO.setId(list.get(0).getId());
                        cwOrgDAO.update(entryDO);
                        return list.get(0).getId();
                    }
                }else{
                    return save(obj);
                }
            } catch (Exception e) {
                logger.error("Error caused in CwOrgManagerImpl.update,param:{}",JSON.toJSONString(entryDO));
                logger.error(e.getMessage());
                throw new RuntimeException("操作失败");
            }
        }

        @Override
        public Long delete(CwOrgDTO obj){
            CwOrgDO entry =new CwOrgDO();
            BeanUtils.copyProperties(obj, entry);
            entry.setIsDeleted((byte) 1);
            try {
                cwOrgDAO.update(entry);
                return entry.getId();
            } catch (Exception e) {
                logger.error("Error caused in CwOrgManagerImpl.delete,param:{}",JSON.toJSONString(entry));
                throw new RuntimeException("操作失败");
            }
        }

}