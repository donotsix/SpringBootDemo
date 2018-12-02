package com.cainiao.bn.lender.common.dto;

import java.io.Serializable;
import java.util.Date;


public class CwContractOrderDTO  implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     *  主键
     */
    private Long id;

    /**
     *  创建时间
     */
    private Date gmtCreate;

    /**
     *  修改时间
     */
    private Date gmtModified;

    /**
     *  订购单ID
     */
    private String subscriptionCode;

    /**
     *  物理仓code
     */
    private String warehouseCode;

    /**
     *  物理仓name
     */
    private String warehouseName;

    /**
     *  仓所在省
     */
    private String whProvince;

    /**
     *  仓所在市
     */
    private String whCity;

    /**
     *  仓详细地址
     */
    private String whAddress;

    /**
     *  仓联系人
     */
    private String whContactName;

    /**
     *  仓联系方式
     */
    private String whContactMobile;

    /**
     *  行业code
     */
    private String industryCode;

    /**
     *  商家类型,1-个人，2-企业
     */
    private Byte sellerType;

    /**
     *  商家菜鸟账号
     */
    private Long sellerCnId;

    /**
     *  商家名称
     */
    private String sellerName;

    /**
     *  商家身份证号
     */
    private String sellerIdCard;

    /**
     *  商家支付宝账号
     */
    private String sellerAlipay;

    /**
     *  商家联系方式
     */
    private String sellerMobile;

    /**
     *  CP菜鸟账号
     */
    private Long cpCnId;

    /**
     *  CP名称
     */
    private String cpName;

    /**
     *  CP联系方式
     */
    private String cpMobile;

    /**
     *  RSP菜鸟账号
     */
    private Long rspCnId;

    /**
     *  RSP名称
     */
    private String rspName;

    /**
     *  RSP联系方式
     */
    private String rspMobile;

    /**
     *  订单状态
     */
    private String orderStatus;

    /**
     *  确认协议URL
     */
    private String protocolUrl;

    /**
     *  补充协议URL,分号分隔
     */
    private String extraProtocol;

    /**
     *  CP签约时间
     */
    private Date cpSignTime;

    /**
     *  商家签约时间
     */
    private Date sellerSignTime;

    /**
     *  订购单创建人编码
     */
    private String createrCode;

    /**
     *  订购单创建人名称
     */
    private String createrName;

    /**
     *  订购单作废人菜鸟账号
     */
    private String cancelerCode;

    /**
     *  订购单作废人名称
     */
    private String cancelerName;

    /**
     *  订购单作废原因
     */
    private String cancelReason;

    /**
     *  订购单作废时间
     */
    private Date cancelTime;

    /**
     *  订购单生效时间
     */
    private Date validStartTime;

    /**
     *  订购单失效时间
     */
    private Date validEndTime;

    /**
     *  扩展字段
     */
    private String feature;

    /**
     *  0-单据有效，1-单据删除
     */
    private Byte deleted;

    /**
     *  商品code
     */
    private String productCode;

    /**
     *  商品名称
     */
    private String productName;

    /**
     *  计费开始时间
     */
    private Date chargeStartTime;

    /**
     *  计费结束时间
     */
    private Date chargeEndTime;

    /**
     *  云仓统一编码
     */
    private String cwCode;

    /**
     *  RSP合同ID
     */
    private String rspContractCode;

    /**
     *  CP合同ID
     */
    private String cpContractCode;

    /**
     *  商家淘宝ID
     */
    private Long sellerTbId;

    /**
     *  生效人编码
     */
    private String validerCode;

    /**
     *  生效人名称
     */
    private String validerName;

    public void setId(Long id) {
        this.id = id;
     }

    public Long getId() {
        return this.id;
     }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
     }

    public Date getGmtCreate() {
        return this.gmtCreate;
     }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
     }

    public Date getGmtModified() {
        return this.gmtModified;
     }

    public void setSubscriptionCode(String subscriptionCode) {
        this.subscriptionCode = subscriptionCode;
     }

    public String getSubscriptionCode() {
        return this.subscriptionCode;
     }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
     }

    public String getWarehouseCode() {
        return this.warehouseCode;
     }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
     }

    public String getWarehouseName() {
        return this.warehouseName;
     }

    public void setWhProvince(String whProvince) {
        this.whProvince = whProvince;
     }

    public String getWhProvince() {
        return this.whProvince;
     }

    public void setWhCity(String whCity) {
        this.whCity = whCity;
     }

    public String getWhCity() {
        return this.whCity;
     }

    public void setWhAddress(String whAddress) {
        this.whAddress = whAddress;
     }

    public String getWhAddress() {
        return this.whAddress;
     }

    public void setWhContactName(String whContactName) {
        this.whContactName = whContactName;
     }

    public String getWhContactName() {
        return this.whContactName;
     }

    public void setWhContactMobile(String whContactMobile) {
        this.whContactMobile = whContactMobile;
     }

    public String getWhContactMobile() {
        return this.whContactMobile;
     }

    public void setIndustryCode(String industryCode) {
        this.industryCode = industryCode;
     }

    public String getIndustryCode() {
        return this.industryCode;
     }

    public void setSellerType(Byte sellerType) {
        this.sellerType = sellerType;
     }

    public Byte getSellerType() {
        return this.sellerType;
     }

    public void setSellerCnId(Long sellerCnId) {
        this.sellerCnId = sellerCnId;
     }

    public Long getSellerCnId() {
        return this.sellerCnId;
     }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
     }

    public String getSellerName() {
        return this.sellerName;
     }

    public void setSellerIdCard(String sellerIdCard) {
        this.sellerIdCard = sellerIdCard;
     }

    public String getSellerIdCard() {
        return this.sellerIdCard;
     }

    public void setSellerAlipay(String sellerAlipay) {
        this.sellerAlipay = sellerAlipay;
     }

    public String getSellerAlipay() {
        return this.sellerAlipay;
     }

    public void setSellerMobile(String sellerMobile) {
        this.sellerMobile = sellerMobile;
     }

    public String getSellerMobile() {
        return this.sellerMobile;
     }

    public void setCpCnId(Long cpCnId) {
        this.cpCnId = cpCnId;
     }

    public Long getCpCnId() {
        return this.cpCnId;
     }

    public void setCpName(String cpName) {
        this.cpName = cpName;
     }

    public String getCpName() {
        return this.cpName;
     }

    public void setCpMobile(String cpMobile) {
        this.cpMobile = cpMobile;
     }

    public String getCpMobile() {
        return this.cpMobile;
     }

    public void setRspCnId(Long rspCnId) {
        this.rspCnId = rspCnId;
     }

    public Long getRspCnId() {
        return this.rspCnId;
     }

    public void setRspName(String rspName) {
        this.rspName = rspName;
     }

    public String getRspName() {
        return this.rspName;
     }

    public void setRspMobile(String rspMobile) {
        this.rspMobile = rspMobile;
     }

    public String getRspMobile() {
        return this.rspMobile;
     }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
     }

    public String getOrderStatus() {
        return this.orderStatus;
     }

    public void setProtocolUrl(String protocolUrl) {
        this.protocolUrl = protocolUrl;
     }

    public String getProtocolUrl() {
        return this.protocolUrl;
     }

    public void setExtraProtocol(String extraProtocol) {
        this.extraProtocol = extraProtocol;
     }

    public String getExtraProtocol() {
        return this.extraProtocol;
     }

    public void setCpSignTime(Date cpSignTime) {
        this.cpSignTime = cpSignTime;
     }

    public Date getCpSignTime() {
        return this.cpSignTime;
     }

    public void setSellerSignTime(Date sellerSignTime) {
        this.sellerSignTime = sellerSignTime;
     }

    public Date getSellerSignTime() {
        return this.sellerSignTime;
     }

    public void setCreaterCode(String createrCode) {
        this.createrCode = createrCode;
     }

    public String getCreaterCode() {
        return this.createrCode;
     }

    public void setCreaterName(String createrName) {
        this.createrName = createrName;
     }

    public String getCreaterName() {
        return this.createrName;
     }

    public void setCancelerCode(String cancelerCode) {
        this.cancelerCode = cancelerCode;
     }

    public String getCancelerCode() {
        return this.cancelerCode;
     }

    public void setCancelerName(String cancelerName) {
        this.cancelerName = cancelerName;
     }

    public String getCancelerName() {
        return this.cancelerName;
     }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
     }

    public String getCancelReason() {
        return this.cancelReason;
     }

    public void setCancelTime(Date cancelTime) {
        this.cancelTime = cancelTime;
     }

    public Date getCancelTime() {
        return this.cancelTime;
     }

    public void setValidStartTime(Date validStartTime) {
        this.validStartTime = validStartTime;
     }

    public Date getValidStartTime() {
        return this.validStartTime;
     }

    public void setValidEndTime(Date validEndTime) {
        this.validEndTime = validEndTime;
     }

    public Date getValidEndTime() {
        return this.validEndTime;
     }

    public void setFeature(String feature) {
        this.feature = feature;
     }

    public String getFeature() {
        return this.feature;
     }

    public void setDeleted(Byte deleted) {
        this.deleted = deleted;
     }

    public Byte getDeleted() {
        return this.deleted;
     }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
     }

    public String getProductCode() {
        return this.productCode;
     }

    public void setProductName(String productName) {
        this.productName = productName;
     }

    public String getProductName() {
        return this.productName;
     }

    public void setChargeStartTime(Date chargeStartTime) {
        this.chargeStartTime = chargeStartTime;
     }

    public Date getChargeStartTime() {
        return this.chargeStartTime;
     }

    public void setChargeEndTime(Date chargeEndTime) {
        this.chargeEndTime = chargeEndTime;
     }

    public Date getChargeEndTime() {
        return this.chargeEndTime;
     }

    public void setCwCode(String cwCode) {
        this.cwCode = cwCode;
     }

    public String getCwCode() {
        return this.cwCode;
     }

    public void setRspContractCode(String rspContractCode) {
        this.rspContractCode = rspContractCode;
     }

    public String getRspContractCode() {
        return this.rspContractCode;
     }

    public void setCpContractCode(String cpContractCode) {
        this.cpContractCode = cpContractCode;
     }

    public String getCpContractCode() {
        return this.cpContractCode;
     }

    public void setSellerTbId(Long sellerTbId) {
        this.sellerTbId = sellerTbId;
     }

    public Long getSellerTbId() {
        return this.sellerTbId;
     }

    public void setValiderCode(String validerCode) {
        this.validerCode = validerCode;
     }

    public String getValiderCode() {
        return this.validerCode;
     }

    public void setValiderName(String validerName) {
        this.validerName = validerName;
     }

    public String getValiderName() {
        return this.validerName;
     }
}