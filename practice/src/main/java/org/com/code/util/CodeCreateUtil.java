package org.com.code.util;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.CollectionUtils;

import com.cainiao.bn.lender.data.dao.BaseTableDAO;
import com.google.common.collect.Lists;

public class CodeCreateUtil {

    public void createBeans() {
        ApplicationContext factory = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");
        BaseTableDAO baseTableDAO = (BaseTableDAO) factory.getBean("baseTableDAO");
        List<TableBean> tableFieldList = baseTableDAO.getTableBeans(CodeUtilConfig.TABLENAME);
        List<TableBean> fieldList=Lists.newArrayList();
        if(CollectionUtils.isEmpty(tableFieldList)){
            throw new RuntimeException("该表无字段，请检查");
        }
        // mysql字段名称转换成Java字段名称
        fieldList = CodeTools.tableFieldToJavaField(tableFieldList);
        System.out.println(System.getProperty("user.dir"));
        // 创建DO
        CodeTools.CreateBeanDO(fieldList,CodeUtilConfig.TABLENAME);
        // 创建DTO
        CodeTools.CreateBeanDTO(fieldList,CodeUtilConfig.TABLENAME);
        // 创建Query
        CodeTools.CreateBeanQuery(fieldList,CodeUtilConfig.TABLENAME);
        // 创建DAO
        CodeTools.createMybatisDao(CodeUtilConfig.TABLENAME);
       // 创建XML
        CodeTools.createMybatisXml(fieldList,CodeUtilConfig.TABLENAME);
        // 创建manager
        CodeTools.createDTOManager(CodeUtilConfig.TABLENAME);
        // 创建managerimpl
        CodeTools.createDTOManagerImpl(fieldList,CodeUtilConfig.TABLENAME);
        // 创建tinyint枚举
        CodeTools.createEnum(fieldList,CodeUtilConfig.TABLENAME);
        // 创建hsfService
        // CodeTools.createHSFService(CodeUtilConfig.TABLENAME);
        // 创建hsfServiceImpl
        // .createHSFServiceImpl(fieldList,CodeUtilConfig.TABLENAME);
    }

    //该工具类为代码生成器,根据数据库表自动生成xml,dto,do,query,manager,managerimpl,dao,减少开发工作量，
    //***********运行完成以后一定要刷新当前应用，不然无法显示当前生成的文件************
    public static void main(String[] args) {
        CodeCreateUtil tableInfoUtil = new CodeCreateUtil();
        tableInfoUtil.createBeans();
    }
}
