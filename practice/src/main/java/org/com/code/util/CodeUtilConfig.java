package org.com.code.util;

public class CodeUtilConfig {
	//表名
	public final static String TABLENAME="cw_role_org";
	//应用名称
	public final static String APPNAME="basic";
	//作者名称
    public final static String AUTHOR="system";
	//class路径
	public final static String CLASS_SRC_PATH="src/main/java/";
	//xml路径
    public final static String XML_SRC_PATH="src/main/resources/";

    // bn基础路径
    public static final String BASE_PATH = "com/cainiao/bn/";

    // impl路径
    public static final String IMPL_PATH = "/impl";

    // data 路径
    public static final String DATA_PATH = "/data";

    // dao路径
    public static final String DAO_PATH_POSTFIX = "/dao";

    // manager路径
    public static final String MANAGER_PATH_POSTFIX = "/manager";

    // common路径
    public static final String COMMON_PATH_POSTFIX = "/common";

    // query路径
    public static final String QUERY_PATH_POSTFIX = "/query";

    // dto路径
    public static final String DTO_PATH_POSTFIX = "/dto";

    // do路径
    public static final String DO_PATH_POSTFIX = "/dataobject";

    // mybatis sqlmap路径
    public static final String SQL_MAP_PATH = "/mybatis/sqlmap";

	//DO路径
	public final static String DO_PATH=CLASS_SRC_PATH + BASE_PATH + APPNAME + COMMON_PATH_POSTFIX + DO_PATH_POSTFIX + "/";
/*	//DTO路径
    public final static String DTO_PATH=System.getProperty("user.dir").substring(0, System.getProperty("user.dir").lastIndexOf("\\"))+"/bn-"+APPNAME+"-common/"+"/"+CLASS_SRC_PATH+"/com/cainiao/bn/"+APPNAME+"/common/dto/";
    //QUERY路径
    public final static String QUERY_PATH=System.getProperty("user.dir").substring(0, System.getProperty("user.dir").lastIndexOf("\\"))+"/bn-"+APPNAME+"-common/"+"/"+CLASS_SRC_PATH+"/com/cainiao/bn/"+APPNAME+"/common/query/";*/
	//DTO路径
    public final static String DTO_PATH=CLASS_SRC_PATH + BASE_PATH + APPNAME + COMMON_PATH_POSTFIX + DTO_PATH_POSTFIX + "/";
    //QUERY路径
    public final static String QUERY_PATH=CLASS_SRC_PATH + BASE_PATH + APPNAME + COMMON_PATH_POSTFIX + QUERY_PATH_POSTFIX + "/";
	//DAO路径
    public final static String DAO_PATH=CLASS_SRC_PATH + BASE_PATH + APPNAME + COMMON_PATH_POSTFIX + DAO_PATH_POSTFIX + "/";
    //Manger路径
    public final static String MANGER_PATH=CLASS_SRC_PATH + BASE_PATH + APPNAME + DATA_PATH + MANAGER_PATH_POSTFIX + "/";
    //MangerImpl路径
    public final static String MANGERIMPL_PATH = MANGER_PATH + IMPL_PATH + "/";
    //Mybatis路径
    // public final static String MYBATIS_PATH=CLASS_SRC_PATH + BASE_PATH + APPNAME + "file/";
    public final static String MYBATIS_PATH=XML_SRC_PATH + SQL_MAP_PATH + "/";

    
	/*//DO包名
    public final static String DO_PACKAGE="com.cainiao.bn.file";
    //DTO包名
    public final static String DTO_PACKAGE="com.cainiao.bn.file";
    //QUERY包名
    public final static String QUERY_PACKAGE="com.cainiao.bn.file";
    //DAO包名
    public final static String DAO_PACKAGE="com.cainiao.bn.file";
    //Manger包名
    public final static String MANAGER_PACKAGE="com.cainiao.bn.file";*/

	public static final String BASE_PACKAGE = "com.cainiao.bn.";

	public static final String IMPL_PACKAGE = ".impl";

	public static final String DATA_PACKAGE = ".data";

	public static final String DAO_PACKAGE_POSTFIX = ".dao";

	public static final String MANAGER_PACKAGE_POSTFIX = ".manager";

	public static final String COMMON_PACKAGE_POSTFIX = ".common";

	public static final String QUERY_PACKAGE_POSTFIX = ".query";

	public static final String DTO_PACKAGE_POSTFIX = ".dto";

	public static final String DO_PACKAGE_POSTFIX = ".dataobject";

    public final static String DO_PACKAGE = BASE_PACKAGE + APPNAME + COMMON_PACKAGE_POSTFIX + DO_PACKAGE_POSTFIX;
    //DTO包名
    public final static String DTO_PACKAGE = BASE_PACKAGE + APPNAME + COMMON_PACKAGE_POSTFIX + DTO_PACKAGE_POSTFIX;
    //QUERY包名 com.cainiao.bn.basic.common.query
    public final static String QUERY_PACKAGE = BASE_PACKAGE + APPNAME + COMMON_PACKAGE_POSTFIX + QUERY_PACKAGE_POSTFIX;
    //DAO包名
    public final static String DAO_PACKAGE = BASE_PACKAGE + APPNAME + DATA_PACKAGE + DAO_PACKAGE_POSTFIX;
    //Manger包名
    public final static String MANAGER_PACKAGE = BASE_PACKAGE + APPNAME + DATA_PACKAGE + MANAGER_PACKAGE_POSTFIX;
    //MangerImpl包名
    public final static String MANAGERIMPL_PACKAGE = MANAGER_PACKAGE + IMPL_PACKAGE;
    
    //换行
    public final static String NEW_LINE="\r\n";
    //空格
    public final static String BLOCK4="    ";
    //空格
    public final static String BLOCK8="        ";
    //空格
    public final static String BLOCK12="            ";
    //空格
    public final static String BLOCK16="                ";
    //空格
    public final static String BLOCK20="                    ";
    //空格
    public final static String BLOCK24="                        ";
    //空格
    public final static String BLOCK28="                            ";
}
