package org.com.code.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.util.CollectionUtils;

import com.google.common.collect.Lists;

public class CodeTools {

    // mysql字段名称转换成Java字段名称
    public static List<TableBean> tableFieldToJavaField(List<TableBean> list) {
        list.stream().map(obj -> {
            System.out.println(obj.getColumnName());
            String columnName = obj.getColumnName();
            while (columnName.indexOf("_") != -1) {
                // 获取_字符下标
                int index = columnName.indexOf("_");
                // 替换
                columnName = columnName.replaceFirst("_", "");
                StringBuffer buffer = new StringBuffer(columnName);
                // 转换成大写
                buffer = buffer.replace(index, index + 1, buffer.substring(index, index + 1).toUpperCase());
                columnName = buffer.toString();
            }
            obj.setBeanColumnName(columnName);
            return obj;
        }).collect(Collectors.toList());
        List<TableBean> listTableBean= list.stream().filter(p->p.getBeanColumnName().equals("deleted") || p.getBeanColumnName().equals("isDeleted")).collect(Collectors.toList());
        List<TableBean> listIdTableBean= list.stream().filter(p->p.getBeanColumnName().equals("id")).collect(Collectors.toList());
        if(CollectionUtils.isEmpty(listIdTableBean)){
            throw new RuntimeException("必须有id字段");
        }
        if(!(listTableBean.size()>0)) {
        	throw new RuntimeException("没有删除标识");
        }
        if(!listTableBean.get(0).getColumnType().equals("tinyint")){
            throw new RuntimeException("删除标识deleted必须为tinyint类型");
        }
        return list;
    }

    // 根据表名获取对象名
    public static String tableFieldToJavaField(String tableName) {
        
        while (tableName.indexOf("_") != -1) {
            // 获取_字符下标
            int index = tableName.indexOf("_");
            // 替换
            tableName = tableName.replaceFirst("_", "");
            // 转换成大写
            StringBuffer buffer = new StringBuffer(tableName);
            buffer = buffer.replace(index, index + 1, buffer.substring(index, index + 1).toUpperCase());
            tableName=buffer.toString();
        }
        StringBuffer tableNameBuffer = new StringBuffer(tableName);
        tableNameBuffer = tableNameBuffer.replace(0, 1, tableNameBuffer.substring(0, 1).toUpperCase());
        return tableNameBuffer.toString();
    }
    
    // 获取字段名称列表
    public static List<String> getColumnList(List<TableBean> list) {
        List<String> listBeanColumnName=Lists.newArrayList();
        listBeanColumnName=list.stream().map(obj ->{
            return obj.getBeanColumnName();
        }).collect(Collectors.toList());
        return listBeanColumnName;
    }
    
	public static <T> void createMybatisDao(String tableName){
	    String beanName=CodeTools.tableFieldToJavaField(tableName);
        String doName=beanName+"DO";
        String doBean=CodeUtilConfig.DO_PACKAGE+"."+doName;
        String dtoName=beanName+"DTO";
        String dtoBean=CodeUtilConfig.DTO_PACKAGE+"."+dtoName;
        String queryName=beanName+"Query";
        String queryBean=CodeUtilConfig.QUERY_PACKAGE+"."+queryName;
        FileWriter fw = null;
        String daoName=beanName+"DAO";
        String daoFileName=beanName+"DAO.java";
        File file = new File(CodeUtilConfig.DAO_PATH + daoFileName);
		if (!file.exists()) {// 判断xml文件是否存在
			try {
			    // 创建文件夹
                file.getParentFile().mkdirs();
				// 创建文件
				file.createNewFile();
				fw = new FileWriter(file);
				fw.write("package "+CodeUtilConfig.DAO_PACKAGE+";");
				fw.write(""+CodeUtilConfig.NEW_LINE);
				fw.write("import java.util.List;");
				fw.write(CodeUtilConfig.NEW_LINE+"import "+doBean+";");
				fw.write(CodeUtilConfig.NEW_LINE+"import "+queryBean+";");
				fw.write(CodeUtilConfig.NEW_LINE+"import "+dtoBean+";");
				fw.write(CodeUtilConfig.NEW_LINE);
                fw.write(CodeUtilConfig.NEW_LINE+"public interface "+daoName+" {");
                fw.write(CodeUtilConfig.NEW_LINE);
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"/**");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"* @author "+CodeUtilConfig.AUTHOR);
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"* 查询列表(可设置是否分页，默认分页)");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"* @param "+doName+" obj");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"* @return List");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"**/");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"List<"+doName+"> queryPage("+queryName+" query);");
                fw.write(CodeUtilConfig.NEW_LINE);
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"/**");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"* @author "+CodeUtilConfig.AUTHOR);
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"* 查询列表总记录");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"* @param "+queryName+" obj");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"* @return Long");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"**/");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"Long queryCount("+queryName+" obj);");
                fw.write(CodeUtilConfig.NEW_LINE);
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"/**");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"* @author "+CodeUtilConfig.AUTHOR);
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"* 插入单条记录");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"* @param "+doName+" obj");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"* @return Long");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"**/");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"Long insert("+doName+" obj);");
                fw.write(CodeUtilConfig.NEW_LINE);
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"/**");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"* @author "+CodeUtilConfig.AUTHOR);
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"* 更新");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"* @param "+doName+" obj");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"* @return Long");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"**/");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"Long update("+doName+" obj);");
                fw.write(CodeUtilConfig.NEW_LINE);
				fw.write(CodeUtilConfig.NEW_LINE+"}");
				fw.close();
				System.out.println("DAO创建文件成功");
			}catch (IOException e) {
				System.out.println("DAO创建文件失败");
			}
		}else {
			System.out.println("DAO文件已存在");
		}
	}
	
    public static void CreateBeanDO(List<TableBean> list,String tableName){
        String beanName=CodeTools.tableFieldToJavaField(tableName);
        FileWriter fw = null;
        String doName=beanName+"DO";
        String doFileName=beanName+"DO.java";
        File file = new File(CodeUtilConfig.DO_PATH + doFileName);
        if (!file.exists()) {// 判断xml文件是否存在
            try {
                // 创建文件夹
                file.getParentFile().mkdirs();
                // 创建文件
                file.createNewFile();
                fw = new FileWriter(file);
                //引包
                fw.write("package "+CodeUtilConfig.DO_PACKAGE+";");
                fw.write(CodeUtilConfig.NEW_LINE);
                fw.write(CodeUtilConfig.NEW_LINE+"import java.util.Date;");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.NEW_LINE);
                fw.write(CodeUtilConfig.NEW_LINE+"public class "+doName+"{");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK4);
                for(TableBean tableBean:list){
                    fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK4+"/**");
                    fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK4+" *  "+tableBean.getColumnDesc().trim());
                    fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK4+" */");
                    fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK4+"private "+EnumJdbcType.getJavaType(tableBean.getColumnType())+" "+tableBean.getBeanColumnName()+";");
                }
                for(TableBean tableBean:list){
                    fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK4+"public void set"+getFirstUpcase(tableBean.getBeanColumnName())+"("+EnumJdbcType.getJavaType(tableBean.getColumnType())+" "+tableBean.getBeanColumnName()+") {");
                    fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"this."+tableBean.getBeanColumnName()+" = "+tableBean.getBeanColumnName()+";");
                    fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK4+"}");
                    fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK4+"public "+EnumJdbcType.getJavaType(tableBean.getColumnType())+" get"+getFirstUpcase(tableBean.getBeanColumnName())+"() {");
                    fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"return this."+tableBean.getBeanColumnName()+";");
                    fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK4+"}");
                }
                fw.write(CodeUtilConfig.NEW_LINE+"}");
                fw.close();
                System.out.println(doName+" 创建文件成功！");
            }catch (IOException e) {
                System.out.println(doName+" 创建文件失败！");
            }
        }else {
            System.out.println(doName+" 文件已存在！");
        }
    }
    public static String getFirstUpcase(String string){
        return string.substring(0,1).toUpperCase() + string.substring(1);
    }

    
    public static void CreateBeanDTO(List<TableBean> list,String tableName){
        String beanName=CodeTools.tableFieldToJavaField(tableName);
        FileWriter fw = null;
        String doName=beanName+"DTO";
        String doFileName=beanName+"DTO.java";
        File file = new File(CodeUtilConfig.DTO_PATH + doFileName);
        if (!file.exists()) {// 判断xml文件是否存在
            try {
                // 创建文件夹
                file.getParentFile().mkdirs();
                // 创建文件
                file.createNewFile();
                fw = new FileWriter(file);
                //引包
                fw.write("package "+CodeUtilConfig.DTO_PACKAGE+";");
                fw.write(CodeUtilConfig.NEW_LINE);
                fw.write(CodeUtilConfig.NEW_LINE+"import java.io.Serializable;");
                fw.write(CodeUtilConfig.NEW_LINE+"import java.util.Date;");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.NEW_LINE);
                fw.write(CodeUtilConfig.NEW_LINE+"public class "+doName+"  implements Serializable{");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK4);
                fw.write("private static final long serialVersionUID = 1L;");
                for(TableBean tableBean:list){
                    fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK4+"/**");
                    fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK4+" *  "+tableBean.getColumnDesc().trim());
                    fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK4+" */");
                    fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK4+"private "+EnumJdbcType.getJavaType(tableBean.getColumnType())+" "+tableBean.getBeanColumnName()+";");
                }
                for(TableBean tableBean:list){
                    fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK4+"public void set"+getFirstUpcase(tableBean.getBeanColumnName())+"("+EnumJdbcType.getJavaType(tableBean.getColumnType())+" "+tableBean.getBeanColumnName()+") {");
                    fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"this."+tableBean.getBeanColumnName()+" = "+tableBean.getBeanColumnName()+";");
                    fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK4+"}");
                    fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK4+"public "+EnumJdbcType.getJavaType(tableBean.getColumnType())+" get"+getFirstUpcase(tableBean.getBeanColumnName())+"() {");
                    fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"return this."+tableBean.getBeanColumnName()+";");
                    fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK4+"}");
                }
                fw.write(CodeUtilConfig.NEW_LINE+"}");
                fw.close();
                System.out.println(doName+" 创建文件成功！");
            }catch (IOException e) {
                System.out.println(doName+" 创建文件失败！");
            }
        }else {
            System.out.println(doName+" 文件已存在！");
        }
    }
    

	public static <T> void createDTOManager(String tableName){
	    String beanName=CodeTools.tableFieldToJavaField(tableName);
	    String mangerName=beanName+"Manager";
        String mangerBean=CodeUtilConfig.MANAGER_PACKAGE +"."+mangerName;
        String mangerFileName=mangerName+".java";
        String doName=beanName+"DO";
        String doBean=CodeUtilConfig.DO_PACKAGE+"."+doName;
        String dtoName=beanName+"DTO";
        String dtoBean=CodeUtilConfig.DTO_PACKAGE+"."+dtoName;
        String queryName=beanName+"Query";
        String queryBean=CodeUtilConfig.QUERY_PACKAGE+"."+queryName;
        FileWriter fw = null;
        String daoName=beanName+"DAO";
        String daoFileName=beanName+"DAO.java";
        File file = new File(CodeUtilConfig.MANGER_PATH + mangerFileName);
		if (!file.exists()) {// 判断xml文件是否存在
			try {
                // 创建文件夹
                file.getParentFile().mkdirs();
				// 创建文件
				file.createNewFile();
				fw = new FileWriter(file);
				fw.write("package "+CodeUtilConfig.MANAGER_PACKAGE +";");
				fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.NEW_LINE+"import com.cainiao.bn.common.util.result.DataResult;");
				fw.write(CodeUtilConfig.NEW_LINE+"import com.cainiao.bn.common.util.result.Pagination;");
				fw.write(CodeUtilConfig.NEW_LINE+"import "+queryBean+";");
				fw.write(CodeUtilConfig.NEW_LINE+"import "+dtoBean+";");
				fw.write(CodeUtilConfig.NEW_LINE);
                fw.write(CodeUtilConfig.NEW_LINE+"public interface "+mangerName+" {");
                fw.write(CodeUtilConfig.NEW_LINE);
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"/**");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"* @author "+CodeUtilConfig.AUTHOR);
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"* 查询列表(可设置是否分页，默认分页)");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"* isNeedPagination=false(不分页)");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"* @param "+queryName+" query");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"* @return List");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"**/");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"Pagination<"+dtoName+"> queryPage("+queryName+" query);");
                fw.write(CodeUtilConfig.NEW_LINE);
                
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"/**");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"* @author "+CodeUtilConfig.AUTHOR);
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"* 查询记录数量");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"* @param "+queryName+" obj");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"* @return Long");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"**/");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"Long queryCount("+queryName+" obj);");
                fw.write(CodeUtilConfig.NEW_LINE);

                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"/**");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"* @author "+CodeUtilConfig.AUTHOR);
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"* 重载方法，根据条件获取最新一条记录(默认更新时间倒叙)，可选数据重复是否报错");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"* @param "+queryName+" query,Boolean ignoreMultiple)");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"* @return "+dtoName+"");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"**/");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+""+dtoName+" queryFirst("+queryName+" query,Boolean ... ignoreMultiple);");
                fw.write(CodeUtilConfig.NEW_LINE);
                
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"/**");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"* @author "+CodeUtilConfig.AUTHOR);
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"* 插入(无视数据是否存在都进行插入)");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"* @param "+dtoName+" obj");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"* @return 返回插入或者更新的id");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"**/");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"Long save("+dtoName+" obj);");
                fw.write(CodeUtilConfig.NEW_LINE);
                
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"/**");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"* @author "+CodeUtilConfig.AUTHOR);

                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"* 根据查询条件query查询数据，ignoreMultiple可选变量，为true的时候允许批量变更，需要自行xml拓展，否则只允许变更单条");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"* @param "+queryName+" query,"+dtoName+" obj,Boolean ... ignoreMultiple");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"* @return 返回更新的id");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"**/");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"Long update("+queryName+" query,"+dtoName+" obj,Boolean ... ignoreMultiple);");
                fw.write(CodeUtilConfig.NEW_LINE);
                
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"/**");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"* @author "+CodeUtilConfig.AUTHOR);
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"* 根据查询条件查询，判断是否插入或者更新单条记录(有：更新，无：新增)");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"* @param "+queryName+" query,"+dtoName+" obj");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"* @return 返回插入或者更新的id");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"**/");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"Long saveOrUpdate("+queryName+" query,"+dtoName+" obj);");
                fw.write(CodeUtilConfig.NEW_LINE);
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"/**");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"* @author "+CodeUtilConfig.AUTHOR);
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"* 删除单条记录");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"* @param "+dtoName+" obj");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"* @return 返回删除的id");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"**/");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"Long delete("+dtoName+" obj);");
                fw.write(CodeUtilConfig.NEW_LINE);
				fw.write(CodeUtilConfig.NEW_LINE+"}");
				fw.close();
				System.out.println("Manger创建文件成功");
			}catch (IOException e) {
				System.out.println("Manger创建文件失败");
			}
		}else {
			System.out.println("Manger文件已存在");
		}
	}
	

	public static <T> void createDTOManagerImpl(List<TableBean> fieldList,String tableName){
	    String beanName=CodeTools.tableFieldToJavaField(tableName);
	    String mangerName=beanName+"Manager";
        String mangerBean=CodeUtilConfig.MANAGER_PACKAGE +"."+mangerName;
        String mangerFileName=mangerName+".java";
        String mangerImplName=beanName+"ManagerImpl";
        String mangerImplBean=CodeUtilConfig.MANAGERIMPL_PACKAGE +"."+mangerImplName;
        String mangerImplFileName=mangerImplName+".java";
        String doName=beanName+"DO";
        String doBean=CodeUtilConfig.DO_PACKAGE+"."+doName;
        String dtoName=beanName+"DTO";
        String dtoBean=CodeUtilConfig.DTO_PACKAGE+"."+dtoName;
        String queryName=beanName+"Query";
        String queryBean=CodeUtilConfig.QUERY_PACKAGE+"."+queryName;
        FileWriter fw = null;
        String daoName=beanName+"DAO";
        String daoBean=CodeUtilConfig.DAO_PACKAGE+"."+daoName;
        String daoBeanEx=daoName.substring(0, 1).toLowerCase()+daoName.substring(1);
        String daoFileName=beanName+"DAO.java";
        File file = new File(CodeUtilConfig.MANGERIMPL_PATH + mangerImplFileName);
		if (!file.exists()) {// 判断xml文件是否存在
			try {
                // 创建文件夹
                file.getParentFile().mkdirs();
				// 创建文件
				file.createNewFile();
				fw = new FileWriter(file);
				fw.write("package "+CodeUtilConfig.MANAGERIMPL_PACKAGE +";");
				fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.NEW_LINE+"import com.cainiao.bn.common.util.result.DataResult;");
				fw.write(CodeUtilConfig.NEW_LINE+"import com.cainiao.bn.common.util.result.Pagination;");
				fw.write(CodeUtilConfig.NEW_LINE+"import org.springframework.stereotype.Component;");
				fw.write(CodeUtilConfig.NEW_LINE+"import org.springframework.util.CollectionUtils;");
                fw.write(CodeUtilConfig.NEW_LINE+"import com.alibaba.fastjson.JSON;");
                fw.write(CodeUtilConfig.NEW_LINE+"import org.springframework.util.StringUtils;");
                fw.write(CodeUtilConfig.NEW_LINE+"import org.slf4j.Logger;");
                fw.write(CodeUtilConfig.NEW_LINE+"import org.slf4j.LoggerFactory;");
				fw.write(CodeUtilConfig.NEW_LINE+"import java.util.List;");
				fw.write(CodeUtilConfig.NEW_LINE+"import java.util.stream.Collectors;");
				fw.write(CodeUtilConfig.NEW_LINE+"import org.springframework.beans.BeanUtils;");
				fw.write(CodeUtilConfig.NEW_LINE+"import org.springframework.beans.factory.annotation.Autowired;");
				fw.write(CodeUtilConfig.NEW_LINE+"import "+queryBean+";");
				fw.write(CodeUtilConfig.NEW_LINE+"import "+dtoBean+";");
				fw.write(CodeUtilConfig.NEW_LINE+"import "+doBean+";");
				fw.write(CodeUtilConfig.NEW_LINE+"import "+daoBean+";");
				fw.write(CodeUtilConfig.NEW_LINE+"import "+mangerBean+";");
				fw.write(CodeUtilConfig.NEW_LINE);
				fw.write(CodeUtilConfig.NEW_LINE+"@Component");
                fw.write(CodeUtilConfig.NEW_LINE+"public class "+mangerImplName+" implements "+mangerName+" {");
                fw.write(CodeUtilConfig.NEW_LINE);
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"private Logger logger = LoggerFactory.getLogger(this.getClass());");
                fw.write(CodeUtilConfig.NEW_LINE);
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"@Autowired");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+daoName+" "+daoBeanEx+";");
                fw.write(CodeUtilConfig.NEW_LINE);
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"@Override");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"public Pagination<"+dtoName+"> queryPage("+queryName+" query){");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK12+"Pagination<"+dtoName+"> pagination=new Pagination<>();");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK12+"List<"+dtoName+"> list="+daoBeanEx+".queryPage(query).stream().map(p->{");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK12+dtoName+" dto = new "+dtoName+"();");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK12+"BeanUtils.copyProperties(p, dto);");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK12+"return dto;");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK12+"}).collect(Collectors.toList());");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK12+"Long totalCount=queryCount(query);");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK12+"pagination.setDatas(list);");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK12+"pagination.setLimit(query.getQueryLimit());");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK12+"pagination.setStart(query.getQueryFrom());");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK12+"pagination.setTotalCount(totalCount);");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK12+"return pagination;");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"}");
                fw.write(CodeUtilConfig.NEW_LINE);

                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"@Override");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"public Long queryCount("+queryName+" obj){");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK12+"Long totalCount="+daoBeanEx+".queryCount(obj);");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK12+"return totalCount;");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"}");
                fw.write(CodeUtilConfig.NEW_LINE);
                

/*                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"@Override");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"public DataResult<"+dtoName+"> queryFirst("+queryName+" query){");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK12+"return queryFirst(query,true);");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"}");
                fw.write(CodeUtilConfig.NEW_LINE);*/
                /*                @Override
                public DataResult<BnLeadsDTO> queryFirst(BnLeadsQuery query, Boolean ignoreMultiple) {
                    List<BnLeadsDO> dos=bnLeadsDAO.queryPage(query);
                    //忽视当前条件数据多条的问题，直接放回最新一条
                    if(!CollectionUtils.isEmpty(dos)){
                        if(dos.size()>1){
                            if(ignoreMultiple==true){
                                List<BnLeadsDTO> list=dos.stream().map(p->{
                                BnLeadsDTO dto = new BnLeadsDTO();
                                BeanUtils.copyProperties(p, dto);
                                return dto;
                                    }).collect(Collectors.toList());
                                return DataResult.Utils.createSuccessResult(list.get(0));
                            }else{
                                return DataResult.Utils.createFailureResult("存在多条记录");
                            }
                        }else{
                            BnLeadsDTO dto = new BnLeadsDTO();
                            BeanUtils.copyProperties(dos.get(0), dto);
                            return DataResult.Utils.createSuccessResult(dto);
                        }
                    }
                    return DataResult.Utils.createFailureResult("未查询到记录");
                }*/
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"@Override");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"public "+dtoName+" queryFirst("+queryName+" query, Boolean ... ignoreMultiple){");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK12+"List<"+doName+"> dos="+daoBeanEx+".queryPage(query);");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK12+"if(!CollectionUtils.isEmpty(dos)){");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK16+"if(dos.size()>1){");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK16+"//总条数大于1条，默认是返回错误");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK20+"if(ignoreMultiple.length==1&&ignoreMultiple[0]==true){");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK24+"List<"+dtoName+"> list=dos.stream().map(p->{");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK28+""+dtoName+" dto = new "+dtoName+"();");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK28+"BeanUtils.copyProperties(p, dto);");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK28+"return dto;");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK24+"}).collect(Collectors.toList());");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK24+"return list.get(0);");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK20+"}else{");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK24+"logger.error(\"Multiple record find in BnLeadsManagerImpl.queryFirst,"+queryName+":{},ignoreMultiple:{}\",JSON.toJSONString(query),ignoreMultiple);");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK24+"throw new RuntimeException(\"存在多条记录\");");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK20+"}");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK16+"}else{");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK20+""+dtoName+" dto = new "+dtoName+"();");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK20+"BeanUtils.copyProperties(dos.get(0), dto);");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK20+"return dto;");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK16+"}");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK12+"}");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK12+"logger.error(\"No record find in BnLeadsManagerImpl.queryFirst,"+queryName+":{},ignoreMultiple:{}\",JSON.toJSONString(query),ignoreMultiple);");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK12+"throw new RuntimeException(\"未查询到记录\");");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"}");
                fw.write(CodeUtilConfig.NEW_LINE);
                
                
                
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"@Override");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"public Long save("+dtoName+" obj){");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK12+"check(obj);");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK12+doName+" entry =new "+doName+"();");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK12+"BeanUtils.copyProperties(obj, entry);");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK12+"try {");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK16+"entry.setDeleted((byte) 0);");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK16+daoBeanEx+".insert(entry);");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK16+"//插入成功则返回插入的id");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK16+"return entry.getId();");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK12+"} catch (Exception e) {"); 
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK16+"logger.error(\"Error caused in "+mangerImplName+".save,param:{}\",JSON.toJSONString(entry));");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK16+"logger.error(e.getMessage());");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK16+"throw new RuntimeException(\"插入失败\");");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK12+"}");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"}");
                fw.write(CodeUtilConfig.NEW_LINE);
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"public void check("+dtoName+" obj){");
                for(TableBean tableBean:fieldList){
                    if(!tableBean.getIsNullable()){
                        if(!(tableBean.getBeanColumnName().equals("id")||tableBean.getBeanColumnName().equals("gmtCreate")||tableBean.getBeanColumnName().equals("gmtModified")||tableBean.getBeanColumnName().equals("deleted"))){
                        String upName=tableBean.getBeanColumnName().substring(0, 1).toUpperCase()+tableBean.getBeanColumnName().substring(1);
                        if(tableBean.getColumnType().equals("varchar")){
                            fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK12+"if(StringUtils.isEmpty(obj.get"+upName+"())){"); 
                        }else{
                            fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK12+"if(null==obj.get"+upName+"()){"); 
                        }
                        fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK16+"logger.error(\""+upName+" can't be Null or Empty\");");
                        fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK16+"throw new RuntimeException(\""+tableBean.getColumnDesc()+"不能为空\");");
                        fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK12+"}"); 
                    }
                    }
                }
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"}");
                
                fw.write(CodeUtilConfig.NEW_LINE);
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"@Override");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"public Long update("+queryName+" entryQuery,"+dtoName+" obj, Boolean ... ignoreMultiple){");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK12+"List<"+doName+"> list="+daoBeanEx+".queryPage(entryQuery);");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK12+doName+" entryDO =new "+doName+"();");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK12+"BeanUtils.copyProperties(obj, entryDO);");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK12+"try {");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK16+"if(!CollectionUtils.isEmpty(list)){");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK20+"if(list.size()!=1){");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK24+"if(ignoreMultiple.length==1&&ignoreMultiple[0]==true){");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK28+"//更新条件自行xml拓展，默认根据id查询更新，也就是说默认只更新一条！！！安全第一。");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK28+daoBeanEx+".update(entryDO);");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK28+"return list.get(0).getId();");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK20+"}else{");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK20+CodeUtilConfig.BLOCK4+"throw new RuntimeException(\"存在多条数据\");");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK20+"}");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK16+"}else{");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK20+CodeUtilConfig.BLOCK4+"entryDO.setId(list.get(0).getId());");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK20+CodeUtilConfig.BLOCK4+daoBeanEx+".update(entryDO);");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK20+CodeUtilConfig.BLOCK4+"return list.get(0).getId();");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK16+"}");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK16+"}else{");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK20+"throw new RuntimeException(\"未查询到数据\");");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK16+"}");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK12+"} catch (Exception e) {");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK16+"logger.error(\"Error caused in "+mangerImplName+".update,param:{}\",JSON.toJSONString(entryDO));");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK16+"logger.error(e.getMessage());");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK16+"throw new RuntimeException(\"操作失败\");");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK12+"}");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"}");
                
                
                fw.write(CodeUtilConfig.NEW_LINE);
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"@Override");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"public Long saveOrUpdate("+queryName+" entryQuery,"+dtoName+" obj){");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK12+"List<"+doName+"> list="+daoBeanEx+".queryPage(entryQuery);");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK12+doName+" entryDO =new "+doName+"();");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK12+"BeanUtils.copyProperties(obj, entryDO);");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK12+"try {");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK16+"if(!CollectionUtils.isEmpty(list)){");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK20+"if(list.size()!=1){");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK20+CodeUtilConfig.BLOCK4+"throw new RuntimeException(\"存在多条数据\");");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK20+"}else{");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK20+CodeUtilConfig.BLOCK4+"entryDO.setId(list.get(0).getId());");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK20+CodeUtilConfig.BLOCK4+daoBeanEx+".update(entryDO);");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK20+CodeUtilConfig.BLOCK4+"return list.get(0).getId();");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK20+"}");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK16+"}else{");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK20+"return save(obj);");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK16+"}");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK12+"} catch (Exception e) {");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK16+"logger.error(\"Error caused in "+mangerImplName+".update,param:{}\",JSON.toJSONString(entryDO));");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK16+"logger.error(e.getMessage());");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK16+"throw new RuntimeException(\"操作失败\");");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK12+"}");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"}");
                
                
                fw.write(CodeUtilConfig.NEW_LINE);
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"@Override");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"public Long delete("+dtoName+" obj){");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK12+doName+" entry =new "+doName+"();");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK12+"BeanUtils.copyProperties(obj, entry);");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK12+"entry.setDeleted((byte) 1);");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK12+"try {");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK16+daoBeanEx+".update(entry);");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK16+"return entry.getId();");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK12+"} catch (Exception e) {");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK16+"logger.error(\"Error caused in "+mangerImplName+".delete,param:{}\",JSON.toJSONString(entry));");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK16+"throw new RuntimeException(\"操作失败\");");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK12+"}");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"}");
                fw.write(CodeUtilConfig.NEW_LINE);
				fw.write(CodeUtilConfig.NEW_LINE+"}");
				fw.close();
				System.out.println("MangerImpl创建文件成功");
			}catch (IOException e) {
				System.out.println("MangerImpl创建文件失败");
			}
		}else {
			System.out.println("MangerImpl文件已存在");
		}
	}
	

    
    public static void CreateBeanQuery(List<TableBean> list,String tableName){
        String beanName=CodeTools.tableFieldToJavaField(tableName);
        FileWriter fw = null;
        String doName=beanName+"Query";
        String doFileName=beanName+"Query.java";
        File file = new File(CodeUtilConfig.QUERY_PATH + doFileName);
        if (!file.exists()) {// 判断xml文件是否存在
            try {
                // 创建文件夹
                file.getParentFile().mkdirs();
                // 创建文件
                file.createNewFile();
                fw = new FileWriter(file);
                //引包
                fw.write("package "+CodeUtilConfig.QUERY_PACKAGE+";");
                fw.write(CodeUtilConfig.NEW_LINE);
                fw.write(CodeUtilConfig.NEW_LINE+"import java.util.Date;");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.NEW_LINE);
                fw.write(CodeUtilConfig.NEW_LINE+"public class "+doName+"  extends BaseQuery{");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK4);
                fw.write("private static final long serialVersionUID = 1L;");
                for(TableBean tableBean:list){
                    if(!tableBean.getBeanColumnName().equals("deleted")){
                        fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK4+"/**");
                        fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK4+" *  "+tableBean.getColumnDesc().trim());
                        fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK4+" */");
                        fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK4+"private "+EnumJdbcType.getJavaType(tableBean.getColumnType())+" "+tableBean.getBeanColumnName()+";");
                    
                    }
                }
                for(TableBean tableBean:list){
                    if(!tableBean.getBeanColumnName().equals("deleted")){
                        fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK4+"public void set"+getFirstUpcase(tableBean.getBeanColumnName())+"("+EnumJdbcType.getJavaType(tableBean.getColumnType())+" "+tableBean.getBeanColumnName()+") {");
                        fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"this."+tableBean.getBeanColumnName()+" = "+tableBean.getBeanColumnName()+";");
                        fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK4+"}");
                        fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK4+"public "+EnumJdbcType.getJavaType(tableBean.getColumnType())+" get"+getFirstUpcase(tableBean.getBeanColumnName())+"() {");
                        fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"return this."+tableBean.getBeanColumnName()+";");
                        fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK4+"}");
                    }
                }
                fw.write(CodeUtilConfig.NEW_LINE+"}");
                fw.close();
                System.out.println(doName+" 创建文件成功！");
            }catch (IOException e) {
                System.out.println(doName+" 创建文件失败！");
            }
        }else {
            System.out.println(doName+" 文件已存在！");
        }
    }
    

	public static <T> void createMybatisXml(List<TableBean> fieldList,String tableName) {
	    String beanName=CodeTools.tableFieldToJavaField(tableName);
        String doName=beanName+"DO";
        String doBean=CodeUtilConfig.DO_PACKAGE+"."+doName;
        String dtoName=beanName+"DTO";
        String dtoBean=CodeUtilConfig.DTO_PACKAGE+"."+dtoName;
        String queryName=beanName+"Query";
        String queryBean=CodeUtilConfig.QUERY_PACKAGE+"."+queryName;
        FileWriter fw = null;
        String daoName=beanName+"DAO";
        String daoBean=CodeUtilConfig.DAO_PACKAGE+"."+daoName;
        String xmlFileName=beanName+"DAO.xml";
        
        
        File file = new File(CodeUtilConfig.MYBATIS_PATH + xmlFileName);
		List<TableBean> listremovedKeyColumn=new ArrayList<>();
		List<TableBean> listKeyColumn=new ArrayList<>();
		//去除主键的列表
		listremovedKeyColumn=fieldList.stream().filter(obj->!obj.getColumnKey().equals("PRI")).collect(Collectors.toList());
		//主键的列表
		listKeyColumn=fieldList.stream().filter(obj->obj.getColumnKey().equals("PRI")).collect(Collectors.toList());
		if (!file.exists()) {// 判断xml文件是否存在
			try {
                // 创建文件夹
                file.getParentFile().mkdirs();
				// 创建文件
				file.createNewFile();
				fw = new FileWriter(file);
				fw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + CodeUtilConfig.NEW_LINE
						+ "<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\"" + CodeUtilConfig.NEW_LINE
						+ "\"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">" + CodeUtilConfig.NEW_LINE
						+ "<mapper namespace=\"" + daoBean + "\">");
				// 创建resultMap
				 
				fw.write(""+CodeUtilConfig.NEW_LINE);
				fw.write(CodeUtilConfig.NEW_LINE + CodeUtilConfig.BLOCK4 + "<resultMap id=\"BaseResultMap\" type=\""
						+ doBean+ "\">");
				fw.write(CodeUtilConfig.NEW_LINE + CodeUtilConfig.BLOCK8 + "<id column=\"id\" jdbcType=\"BIGINT\" property=\"id\" />");
				for(TableBean tableBean:listremovedKeyColumn){
				    fw.write(CodeUtilConfig.NEW_LINE + CodeUtilConfig.BLOCK8 + "<result column=\""
                            + tableBean.getColumnName() + "\" jdbcType=\"" + EnumJdbcType.getJdbcType(tableBean.getColumnType())
                            + "\" property=\"" + tableBean.getBeanColumnName()+ "\" />");
				}
				fw.write(CodeUtilConfig.NEW_LINE + CodeUtilConfig.BLOCK4 + "</resultMap>");
				
				
				// 创建Base_Column_List
				fw.write(CodeUtilConfig.NEW_LINE + CodeUtilConfig.NEW_LINE + CodeUtilConfig.BLOCK4 + "<sql id=\"Base_Column_List\">");
				Iterator<TableBean> iterator=fieldList.iterator();
				while(iterator.hasNext()){
				    TableBean tableBean=(TableBean)iterator.next();
				    fw.write(CodeUtilConfig.NEW_LINE + CodeUtilConfig.BLOCK8 +tableBean.getColumnName());
				    if (iterator.hasNext()) {
				        fw.write(",");
                    }
				}
				fw.write(CodeUtilConfig.NEW_LINE + CodeUtilConfig.BLOCK4 + "</sql>");
				fw.write(CodeUtilConfig.NEW_LINE);
				// 创建whereCondition
				 
				fw.write(CodeUtilConfig.NEW_LINE + CodeUtilConfig.BLOCK4 + "<sql id=\"where_Condition\">");
				fw.write(CodeUtilConfig.NEW_LINE + CodeUtilConfig.BLOCK4 + "<where>");
				String sqlwhere = "";
                for(TableBean tableBean:fieldList){
                    if(tableBean.getColumnType().equals("varchar")){
                        sqlwhere = CodeUtilConfig.NEW_LINE + CodeUtilConfig.BLOCK8 + "<if test=\"" + tableBean.getBeanColumnName()
                        + " != null and " + tableBean.getBeanColumnName() + " !=''\">";
                    }else{
                        sqlwhere = CodeUtilConfig.NEW_LINE + CodeUtilConfig.BLOCK8 + "<if test=\"" + tableBean.getBeanColumnName()
                        + " != null\">";
                    }
                    if(tableBean.getBeanColumnName().toLowerCase().contentEquals("name")){
                        sqlwhere += CodeUtilConfig.NEW_LINE + CodeUtilConfig.BLOCK12 + "and " + tableBean.getColumnName()
                        + " like CONCAT('%',#{" + tableBean.getBeanColumnName() + ",jdbcType=" + EnumJdbcType.getJdbcType(tableBean.getColumnType()) + "},'%')";
                    }else{
                        sqlwhere += CodeUtilConfig.NEW_LINE + CodeUtilConfig.BLOCK12 + "and " + tableBean.getColumnName()
                        + " = #{" + tableBean.getBeanColumnName() + ",jdbcType=" + EnumJdbcType.getJdbcType(tableBean.getColumnType()) + "}";
                    }
                    sqlwhere += CodeUtilConfig.NEW_LINE + CodeUtilConfig.BLOCK8 + "</if>";
                    fw.write(sqlwhere);
                }
				fw.write(CodeUtilConfig.NEW_LINE + CodeUtilConfig.BLOCK4 + "</where>");
				fw.write(CodeUtilConfig.NEW_LINE + CodeUtilConfig.BLOCK4 + "</sql>");
				fw.write(CodeUtilConfig.NEW_LINE);
				// 创建 limit_Condition

				fw.write(CodeUtilConfig.NEW_LINE + CodeUtilConfig.BLOCK4 + "<sql id=\"limit_Condition\">");
				fw.write(CodeUtilConfig.NEW_LINE + CodeUtilConfig.BLOCK8 + "<if test=\"offset != null and isNeedPagination !=false\">");
				fw.write(CodeUtilConfig.NEW_LINE + CodeUtilConfig.BLOCK12 + "limit #{offset},#{pageSize}");
				fw.write(CodeUtilConfig.NEW_LINE + CodeUtilConfig.BLOCK8 + "</if>");
				fw.write(CodeUtilConfig.NEW_LINE + CodeUtilConfig.BLOCK4 + "</sql>");
				fw.write(CodeUtilConfig.NEW_LINE);
				// 创建orderBy_Condition
				 
				fw.write(CodeUtilConfig.NEW_LINE + CodeUtilConfig.BLOCK4 + "<sql id=\"orderBy_Condition\">");
				fw.write(CodeUtilConfig.NEW_LINE + CodeUtilConfig.BLOCK8 + "<if test=\"orderBy != null\">");
				fw.write(CodeUtilConfig.NEW_LINE + CodeUtilConfig.BLOCK12 + "order by gmt_create");
				fw.write(CodeUtilConfig.NEW_LINE + CodeUtilConfig.BLOCK8 + "</if>");
				fw.write(CodeUtilConfig.NEW_LINE + CodeUtilConfig.BLOCK4 + "</sql>");
				fw.write(CodeUtilConfig.NEW_LINE);

				
				

				// 创建insert UserAreaColume
				 

				

				
				//<!-- 查询page -->
				fw.write(CodeUtilConfig.NEW_LINE + CodeUtilConfig.BLOCK4 + "<select id=\"queryPage\" resultMap=\"BaseResultMap\" parameterType=\""+queryBean+"\">");
				fw.write(CodeUtilConfig.NEW_LINE + CodeUtilConfig.BLOCK8 + "select");
				fw.write(CodeUtilConfig.NEW_LINE + CodeUtilConfig.BLOCK12 + "<include refid=\"Base_Column_List\" />");
				fw.write(CodeUtilConfig.NEW_LINE + CodeUtilConfig.BLOCK12 + "from "+tableName+"");
				fw.write(CodeUtilConfig.NEW_LINE + CodeUtilConfig.BLOCK12 + "<include refid=\"where_Condition\"/>");
				fw.write(CodeUtilConfig.NEW_LINE + CodeUtilConfig.BLOCK12 + "<include refid=\"orderBy_Condition\"/>");
				fw.write(CodeUtilConfig.NEW_LINE + CodeUtilConfig.BLOCK12 + "<include refid=\"limit_Condition\"/>");
				fw.write(CodeUtilConfig.NEW_LINE + CodeUtilConfig.BLOCK4 + "</select>");
				fw.write(CodeUtilConfig.NEW_LINE);
				//<!-- 查询page count -->
				fw.write(CodeUtilConfig.NEW_LINE + CodeUtilConfig.BLOCK4 + "<select id=\"queryCount\" resultType=\"java.lang.Long\" parameterType=\""+queryBean+"\">");
				fw.write(CodeUtilConfig.NEW_LINE + CodeUtilConfig.BLOCK8 + "select count(*)");
				fw.write(CodeUtilConfig.NEW_LINE + CodeUtilConfig.BLOCK12 + "from "+tableName+"");
				fw.write(CodeUtilConfig.NEW_LINE + CodeUtilConfig.BLOCK12 + "<include refid=\"where_Condition\"/>");
				fw.write(CodeUtilConfig.NEW_LINE + CodeUtilConfig.BLOCK4 + "</select>");
				fw.write(CodeUtilConfig.NEW_LINE);
				//<!-- 单条记录插入 -->
		/*		fw.write(CodeUtilConfig.NEW_LINE + CodeUtilConfig.BLOCK4 + "<insert id=\"insert\" parameterType=\""+doBean+"\" useGeneratedKeys=\"true\" keyProperty=\"id\">");
				fw.write(CodeUtilConfig.NEW_LINE + CodeUtilConfig.BLOCK8 + "insert into "+tableName+"(");
				Iterator<TableBean> insertIterator=listremovedKeyColumn.iterator();
                while(insertIterator.hasNext()){
                    fw.write(CodeUtilConfig.NEW_LINE + CodeUtilConfig.BLOCK8 +((TableBean)insertIterator.next()).getColumnName());
                    if (insertIterator.hasNext()) {
                        fw.write(",");
                    }
                }
				fw.write(CodeUtilConfig.NEW_LINE + CodeUtilConfig.BLOCK4 + ")values(");
	             Iterator<TableBean> insertValueIterator=listremovedKeyColumn.iterator();
	                while(insertValueIterator.hasNext()){
	                    TableBean tableBean=(TableBean)insertValueIterator.next();
	                    if(tableBean.getBeanColumnName().equals("gmtModified")||tableBean.getBeanColumnName().equals("gmtCreate")){
	                        fw.write(CodeUtilConfig.NEW_LINE + CodeUtilConfig.BLOCK8 +"now()");
	                    }else{
	                        fw.write(CodeUtilConfig.NEW_LINE + CodeUtilConfig.BLOCK8 +"#{"+tableBean.getBeanColumnName()+",jdbcType="+EnumJdbcType.getJdbcType(tableBean.getColumnType())+"}");
	                    }
	                    if (insertValueIterator.hasNext()) {
	                        fw.write(",");
	                    }
	                }
				fw.write(CodeUtilConfig.NEW_LINE + CodeUtilConfig.BLOCK4 + ")");
				fw.write(CodeUtilConfig.NEW_LINE + CodeUtilConfig.BLOCK4 + "</insert>");
				fw.write(CodeUtilConfig.NEW_LINE);*/
				fw.write(CodeUtilConfig.NEW_LINE + CodeUtilConfig.BLOCK4 + "<insert id=\"insert\" parameterType=\""+doBean+"\" useGeneratedKeys=\"true\" keyProperty=\"id\">");
                fw.write(CodeUtilConfig.NEW_LINE + CodeUtilConfig.BLOCK8 + "insert into "+tableName+"");
                fw.write(CodeUtilConfig.NEW_LINE + CodeUtilConfig.BLOCK8 + "<trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">");
                Iterator<TableBean> insertItemIterator=listremovedKeyColumn.iterator();
                String insertClumn="";
                while(insertItemIterator.hasNext()){
                        TableBean tableBean=(TableBean)insertItemIterator.next();
                        if(tableBean.getBeanColumnName().equals("gmtModified")){
                            fw.write(CodeUtilConfig.NEW_LINE + CodeUtilConfig.BLOCK12 +"gmt_modified,");
                        }else if(tableBean.getBeanColumnName().equals("gmtCreate")){
                            fw.write(CodeUtilConfig.NEW_LINE + CodeUtilConfig.BLOCK12 +"gmt_create,");
                        }else if(tableBean.getBeanColumnName().equals("deleted")){
                            fw.write(CodeUtilConfig.NEW_LINE + CodeUtilConfig.BLOCK12 +"deleted,");
                        }else{
                            if(tableBean.getColumnType().equals("varchar")){
                                insertClumn = CodeUtilConfig.NEW_LINE + CodeUtilConfig.BLOCK8 + "<if test=\"" + tableBean.getBeanColumnName()
                                + " != null and " + tableBean.getBeanColumnName() + " !=''\">";
                            }else{
                                insertClumn = CodeUtilConfig.NEW_LINE + CodeUtilConfig.BLOCK8 + "<if test=\"" + tableBean.getBeanColumnName()
                                + " != null\">";
                            }
                            insertClumn += CodeUtilConfig.NEW_LINE + CodeUtilConfig.BLOCK12 +""+tableBean.getColumnName()+",";
                            insertClumn += CodeUtilConfig.NEW_LINE + CodeUtilConfig.BLOCK8 + "</if>";
                            fw.write(insertClumn);
                        }
                }
                fw.write(CodeUtilConfig.NEW_LINE + CodeUtilConfig.BLOCK8 + "</trim>");
                fw.write(CodeUtilConfig.NEW_LINE + CodeUtilConfig.BLOCK8 + "<trim prefix=\"values (\" suffix=\")\" suffixOverrides=\",\">");
                Iterator<TableBean> insertValueIterator=listremovedKeyColumn.iterator();
                    while(insertValueIterator.hasNext()){
                        TableBean tableBean=(TableBean)insertValueIterator.next();
                        if(tableBean.getBeanColumnName().equals("gmtModified")||tableBean.getBeanColumnName().equals("gmtCreate")){
                            fw.write(CodeUtilConfig.NEW_LINE + CodeUtilConfig.BLOCK12 +"now(),");
                        }else if(tableBean.getBeanColumnName().equals("deleted")){
                            fw.write(CodeUtilConfig.NEW_LINE + CodeUtilConfig.BLOCK12 +"0,");
                        }else{
                            if(tableBean.getColumnType().equals("varchar")){
                                insertClumn = CodeUtilConfig.NEW_LINE + CodeUtilConfig.BLOCK8 + "<if test=\"" + tableBean.getBeanColumnName()
                                + " != null and " + tableBean.getBeanColumnName() + " !=''\">";
                            }else{
                                insertClumn = CodeUtilConfig.NEW_LINE + CodeUtilConfig.BLOCK8 + "<if test=\"" + tableBean.getBeanColumnName()
                                + " != null\">";
                            }
                            insertClumn +=CodeUtilConfig.NEW_LINE + CodeUtilConfig.BLOCK12 +"#{"+tableBean.getBeanColumnName()+",jdbcType="+EnumJdbcType.getJdbcType(tableBean.getColumnType())+"},";
                            insertClumn += CodeUtilConfig.NEW_LINE + CodeUtilConfig.BLOCK8 + "</if>";
                            fw.write(insertClumn);
                        }
                    }
                fw.write(CodeUtilConfig.NEW_LINE + CodeUtilConfig.BLOCK8 + "</trim>");
                fw.write(CodeUtilConfig.NEW_LINE + CodeUtilConfig.BLOCK4 + "</insert>");
                fw.write(CodeUtilConfig.NEW_LINE);
				
				//<!-- update -->
				fw.write(CodeUtilConfig.NEW_LINE + CodeUtilConfig.BLOCK4 + "<update id=\"update\" parameterType=\""+doBean+"\">");
				fw.write(CodeUtilConfig.NEW_LINE + CodeUtilConfig.BLOCK8 + "update "+tableName+"");
                //创建update_Colume
               fw.write(CodeUtilConfig.NEW_LINE + CodeUtilConfig.BLOCK8 + "<set>");
               for(TableBean tableBean:listremovedKeyColumn){
                   if("gmt_modified".equals(tableBean.getColumnName())){
                       fw.write(CodeUtilConfig.NEW_LINE + CodeUtilConfig.BLOCK8 + "gmt_modified=now(),");
                      
                   }else{
                       if(!"gmt_create".equals(tableBean.getColumnName())){
                           fw.write(CodeUtilConfig.NEW_LINE + CodeUtilConfig.BLOCK8 + "<if test=\"" + tableBean.getBeanColumnName()+ " != null\">");
                           fw.write(CodeUtilConfig.NEW_LINE + CodeUtilConfig.BLOCK12 +tableBean.getColumnName()+ " = #{" + tableBean.getBeanColumnName() + ", jdbcType=" + EnumJdbcType.getJdbcType(tableBean.getColumnType()) + "},");
                           fw.write(CodeUtilConfig.NEW_LINE + CodeUtilConfig.BLOCK8 + "</if>");
                       }
                   }
               }
               fw.write(CodeUtilConfig.NEW_LINE + CodeUtilConfig.BLOCK8 + "</set>");
               // 创建update_Condition
               fw.write(CodeUtilConfig.NEW_LINE + CodeUtilConfig.BLOCK8 + "<where>");
               for(TableBean tableBean:listKeyColumn){
                   fw.write(CodeUtilConfig.NEW_LINE + CodeUtilConfig.BLOCK12 + "and " + tableBean.getColumnName()+ " = #{" + tableBean.getBeanColumnName() + ",jdbcType=" + EnumJdbcType.getJdbcType(tableBean.getColumnType()) + "}");
               }
               fw.write(CodeUtilConfig.NEW_LINE + CodeUtilConfig.BLOCK8 + "</where>");
               fw.write(CodeUtilConfig.NEW_LINE);
				fw.write(CodeUtilConfig.NEW_LINE + CodeUtilConfig.BLOCK4 + "</update>");
				fw.write(CodeUtilConfig.NEW_LINE);
				fw.write(CodeUtilConfig.NEW_LINE + "</mapper>");
				fw.close();
				System.out.println("XML创建文件成功");
			} catch (IOException e) {
				System.out.println("XML创建文件失败");
			}
		} else {
			System.out.println("XML文件已存在");
		}

	}

	public static <T> void createHSFService(String tableName){
        String beanName=CodeTools.tableFieldToJavaField(tableName);
        String hsfService=beanName+"HsfService";
        String mangerBean=CodeUtilConfig.MANAGER_PACKAGE +"."+hsfService;
        String mangerFileName=hsfService+".java";
        String doName=beanName+"DO";
        String doBean=CodeUtilConfig.DO_PACKAGE+"."+doName;
        String dtoName=beanName+"DTO";
        String dtoBean=CodeUtilConfig.DTO_PACKAGE+"."+dtoName;
        String queryName=beanName+"Query";
        String queryBean=CodeUtilConfig.QUERY_PACKAGE+"."+queryName;
        FileWriter fw = null;
        String daoName=beanName+"DAO";
        String daoFileName=beanName+"DAO.java";
        File file = new File(CodeUtilConfig.MANGER_PATH + mangerFileName);
        if (!file.exists()) {// 判断xml文件是否存在
            try {
                // 创建文件夹
                file.getParentFile().mkdirs();
                // 创建文件
                file.createNewFile();
                fw = new FileWriter(file);
                fw.write("package "+CodeUtilConfig.MANAGER_PACKAGE +";");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.NEW_LINE+"import com.cainiao.bn.common.util.result.DataResult;");
                fw.write(CodeUtilConfig.NEW_LINE+"import com.cainiao.bn.common.util.result.PageResult;");
                fw.write(CodeUtilConfig.NEW_LINE+"import "+queryBean+";");
                fw.write(CodeUtilConfig.NEW_LINE+"import "+dtoBean+";");
                fw.write(CodeUtilConfig.NEW_LINE);
                fw.write(CodeUtilConfig.NEW_LINE+"public interface "+hsfService+" {");
                fw.write(CodeUtilConfig.NEW_LINE);
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"/**");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"* @author "+CodeUtilConfig.AUTHOR);
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"* 查询列表(可设置是否分页，默认分页)");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"* isNeedPagination=false(不分页)");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"* @param "+queryName+" query");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"* @return List");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"**/");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"PageResult<"+dtoName+"> queryPage("+queryName+" query);");
                fw.write(CodeUtilConfig.NEW_LINE);
                
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"/**");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"* @author "+CodeUtilConfig.AUTHOR);
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"* 查询记录数量");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"* @param "+queryName+" obj");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"* @return Long");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"**/");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"DataResult<Long> queryCount("+queryName+" obj);");
                fw.write(CodeUtilConfig.NEW_LINE);

                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"/**");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"* @author "+CodeUtilConfig.AUTHOR);
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"* 重载方法，根据条件获取最新一条记录(默认更新时间倒叙)，可选数据重复是否报错");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"* @param "+queryName+" query,Boolean ignoreMultiple)");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"* @return "+dtoName+"");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"**/");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"DataResult<"+dtoName+"> queryFirst("+queryName+" query,Boolean ... ignoreMultiple);");
                fw.write(CodeUtilConfig.NEW_LINE);
                
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"/**");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"* @author "+CodeUtilConfig.AUTHOR);
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"* 插入(无视数据是否存在都进行插入)");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"* @param "+dtoName+" obj");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"* @return 返回插入或者更新的id");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"**/");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"DataResult<Long> save("+dtoName+" obj);");
                fw.write(CodeUtilConfig.NEW_LINE);
                
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"/**");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"* @author "+CodeUtilConfig.AUTHOR);

                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"* 根据查询条件query查询数据，ignoreMultiple可选变量，为true的时候允许批量变更，需要自行xml拓展，否则只允许变更单条");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"* @param "+queryName+" query,"+dtoName+" obj,Boolean ... ignoreMultiple");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"* @return 返回更新的id");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"**/");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"DataResult<Long> update("+queryName+" query,"+dtoName+" obj,Boolean ... ignoreMultiple);");
                fw.write(CodeUtilConfig.NEW_LINE);
                
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"/**");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"* @author "+CodeUtilConfig.AUTHOR);
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"* 根据查询条件查询，判断是否插入或者更新单条记录(有：更新，无：新增)");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"* @param "+queryName+" query,"+dtoName+" obj");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"* @return 返回插入或者更新的id");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"**/");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"DataResult<Long> saveOrUpdate("+queryName+" query,"+dtoName+" obj);");
                fw.write(CodeUtilConfig.NEW_LINE);
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"/**");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"* @author "+CodeUtilConfig.AUTHOR);
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"* 删除单条记录");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"* @param "+dtoName+" obj");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"* @return 返回删除的id");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"**/");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"DataResult<Long> delete("+dtoName+" obj);");
                fw.write(CodeUtilConfig.NEW_LINE);
                fw.write(CodeUtilConfig.NEW_LINE+"}");
                fw.close();
                System.out.println("HsfService创建文件成功");
            }catch (IOException e) {
                System.out.println("HsfService创建文件失败");
            }
        }else {
            System.out.println("HsfService文件已存在");
        }
    }
    

    public static <T> void createHSFServiceImpl(List<TableBean> fieldList,String tableName){
        String beanName=CodeTools.tableFieldToJavaField(tableName);
        String hsfServiceName=beanName+"HsfService";
        String hsfServiceBean=CodeUtilConfig.MANAGER_PACKAGE +"."+hsfServiceName;
        String hsfServiceInstance=hsfServiceName.substring(0, 1).toLowerCase()+hsfServiceName.substring(1);
        String hsfServiceImplName=beanName+"HsfServiceImpl";
        String hsfServiceImplFileName=hsfServiceImplName+".java";
        String doName=beanName+"DO";
        String doBean=CodeUtilConfig.DO_PACKAGE+"."+doName;
        String dtoName=beanName+"DTO";
        String dtoBean=CodeUtilConfig.DTO_PACKAGE+"."+dtoName;
        String queryName=beanName+"Query";
        String queryBean=CodeUtilConfig.QUERY_PACKAGE+"."+queryName;
        FileWriter fw = null;
        String daoName=beanName+"Manager";
        String daoBean=CodeUtilConfig.DAO_PACKAGE+"."+daoName;
        String daoBeanEx=daoName.substring(0, 1).toLowerCase()+daoName.substring(1);
        File file = new File(CodeUtilConfig.MANGERIMPL_PATH + hsfServiceImplFileName);
        if (!file.exists()) {// 判断xml文件是否存在
            try {
                // 创建文件夹
                file.getParentFile().mkdirs();
                // 创建文件
                file.createNewFile();
                fw = new FileWriter(file);
                fw.write("package "+CodeUtilConfig.MANAGERIMPL_PACKAGE +";");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.NEW_LINE+"import com.cainiao.bn.common.util.result.DataResult;");
                fw.write(CodeUtilConfig.NEW_LINE+"import com.cainiao.bn.common.util.result.PageResult;");
                fw.write(CodeUtilConfig.NEW_LINE+"import com.cainiao.bn.common.util.result.Pagination;");
                fw.write(CodeUtilConfig.NEW_LINE+"import org.springframework.stereotype.Component;");
                fw.write(CodeUtilConfig.NEW_LINE+"import org.slf4j.Logger;");
                fw.write(CodeUtilConfig.NEW_LINE+"import org.slf4j.LoggerFactory;");
                fw.write(CodeUtilConfig.NEW_LINE+"import org.springframework.beans.factory.annotation.Autowired;");
                fw.write(CodeUtilConfig.NEW_LINE+"import "+queryBean+";");
                fw.write(CodeUtilConfig.NEW_LINE+"import "+dtoBean+";");
                fw.write(CodeUtilConfig.NEW_LINE+"import "+doBean+";");
                fw.write(CodeUtilConfig.NEW_LINE+"import "+daoBean+";");
                fw.write(CodeUtilConfig.NEW_LINE+"import "+hsfServiceBean+";");
                fw.write(CodeUtilConfig.NEW_LINE);
                fw.write(CodeUtilConfig.NEW_LINE+"@Component(\""+hsfServiceInstance+"\")");
                fw.write(CodeUtilConfig.NEW_LINE+"public class "+hsfServiceImplName+" implements "+hsfServiceName+" {");
                fw.write(CodeUtilConfig.NEW_LINE);
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"private Logger logger = LoggerFactory.getLogger(this.getClass());");
                fw.write(CodeUtilConfig.NEW_LINE);
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"@Autowired");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+daoName+" "+daoBeanEx+";");
                fw.write(CodeUtilConfig.NEW_LINE);
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"@Override");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"public PageResult<"+dtoName+"> queryPage("+queryName+" query){");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK12+"try {");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK16+"Pagination<"+dtoName+"> pagination = "+daoBeanEx+".queryPage(query);");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK12+"return PageResult.Utils.createSuccessResult(pagination);");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK12+"} catch (Exception e) {");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK16+"logger.info(e.getMessage());");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK16+"return PageResult.Utils.createFailureResult(e.getMessage());");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK12+"}");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"}");
                fw.write(CodeUtilConfig.NEW_LINE);

                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"@Override");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"public DataResult<Long> queryCount("+queryName+" query){");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK12+"try {");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK16+"Long totalCount = "+daoBeanEx+".queryCount(query);");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK12+"return DataResult.Utils.createSuccessResult(totalCount);");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK12+"} catch (Exception e) {");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK16+"logger.info(e.getMessage());");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK16+"return DataResult.Utils.createFailureResult(e.getMessage());");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK12+"}");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"}");
                fw.write(CodeUtilConfig.NEW_LINE);
                
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"@Override");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"public DataResult<"+dtoName+"> queryFirst("+queryName+" query, Boolean... ignoreMultiple){");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK12+"try {");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK16+""+dtoName+" first = "+daoBeanEx+".queryFirst(query, ignoreMultiple);");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK12+"return DataResult.Utils.createSuccessResult(first);");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK12+"} catch (Exception e) {");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK16+"logger.info(e.getMessage());");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK16+"return DataResult.Utils.createFailureResult(e.getMessage());");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK12+"}");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"}");
                fw.write(CodeUtilConfig.NEW_LINE);
                
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"@Override");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"public DataResult<Long> save("+dtoName+" obj){");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK12+"try {");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK16+"Long callback = "+daoBeanEx+".save(obj);");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK12+"return DataResult.Utils.createSuccessResult(callback);");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK12+"} catch (Exception e) {");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK16+"logger.info(e.getMessage());");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK16+"return DataResult.Utils.createFailureResult(e.getMessage());");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK12+"}");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"}");
                fw.write(CodeUtilConfig.NEW_LINE);
                
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"@Override");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"public DataResult<Long> update("+queryName+" query,"+dtoName+" obj, Boolean... ignoreMultiple){");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK12+"try {");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK16+"Long callback = "+daoBeanEx+".update(query, obj, ignoreMultiple);");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK12+"return DataResult.Utils.createSuccessResult(callback);");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK12+"} catch (Exception e) {");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK16+"logger.info(e.getMessage());");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK16+"return DataResult.Utils.createFailureResult(e.getMessage());");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK12+"}");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"}");
                fw.write(CodeUtilConfig.NEW_LINE);
                
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"@Override");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"public DataResult<Long> saveOrUpdate("+queryName+" query,"+dtoName+" obj){");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK12+"try {");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK16+"Long callback = "+daoBeanEx+".saveOrUpdate(query,obj);");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK12+"return DataResult.Utils.createSuccessResult(callback);");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK12+"} catch (Exception e) {");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK16+"logger.info(e.getMessage());");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK16+"return DataResult.Utils.createFailureResult(e.getMessage());");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK12+"}");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"}");
                fw.write(CodeUtilConfig.NEW_LINE);
                
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"@Override");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"public DataResult<Long> delete("+dtoName+" obj){");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK12+"try {");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK16+"Long callback = "+daoBeanEx+".delete(obj);");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK12+"return DataResult.Utils.createSuccessResult(callback);");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK12+"} catch (Exception e) {");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK16+"logger.info(e.getMessage());");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK16+"return DataResult.Utils.createFailureResult(e.getMessage());");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK12+"}");
                fw.write(CodeUtilConfig.NEW_LINE+CodeUtilConfig.BLOCK8+"}");
                fw.write(CodeUtilConfig.NEW_LINE);
                
                
                fw.write(CodeUtilConfig.NEW_LINE+"}");
                fw.close();
                System.out.println("HsfServiceImpl创建文件成功");
            }catch (IOException e) {
                System.out.println("HsfServiceImpl创建文件失败");
            }
        }else {
            System.out.println("HsfServiceImpl文件已存在");
        }
    }
	
    public static void createEnum(List<TableBean> fieldList, String tablename) {
        // TODO Auto-generated method stub
        // System.out.println("123");
    }





}
