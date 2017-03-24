package com.visionet.platform.cooperation.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_order_invoice")
public class OrderInvoice {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 发票申请人
     */
    @Column(name = "PHONE")
    private String phone;

    /**
     * 订单号
     */
    @Column(name = "ORDER_ID")
    private String orderId;

    /**
     * 开票总额
     */
    @Column(name = "TOTAL_AMOUNT")
    private Double totalAmount;

    /**
     * 接收人
     */
    @Column(name = "RECEIVER")
    private String receiver;

    /**
     * 接收人电话
     */
    @Column(name = "RECEIVER_PHONE")
    private String receiverPhone;

    /**
     * 邮寄地址
     */
    @Column(name = "RECEIVER_ADDRESS")
    private String receiverAddress;

    /**
     * 0:个人发票，1：公司发票
     */
    @Column(name = "INVOICE_TYPE")
    private Integer invoiceType;

    /**
     * 开票描述（订单号+价格） 
     */
    @Column(name = "INVOICE_DESCRIPTION")
    private String invoiceDescription;

    /**
     * 公司名称
     */
    @Column(name = "COMPANY_NAME")
    private String companyName;

    /**
     * 纳税识别ID
     */
    @Column(name = "TAXPAYER_ID")
    private String taxpayerId;

    /**
     * 公司地址
     */
    @Column(name = "COMPANY_ADDRESS")
    private String companyAddress;

    /**
     * 公司电话
     */
    @Column(name = "COMPANY_PHONE")
    private String companyPhone;

    /**
     * 对公账户
     */
    @Column(name = "COMPANY_ACCOUNT")
    private String companyAccount;

    /**
     * 开户行
     */
    @Column(name = "ACCOUNT_BANK")
    private String accountBank;

    /**
     * 申请时间
     */
    @Column(name = "APPLY_DATE")
    private Date applyDate;

    /**
     * 快递名称
     */
    @Column(name = "EXPRESS_NAME")
    private String expressName;

    /**
     * 快递单号
     */
    @Column(name = "EXPRESS_NUMBER")
    private String expressNumber;

    /**
     * 快递时间
     */
    @Column(name = "EXPRESS_DATE")
    private Date expressDate;

    /**
     * 0：拒绝  1：新申请 2：已开票 3：已快递 
     */
    @Column(name = "STATUS")
    private Integer status;

    /**
     * 处理人
     */
    @Column(name = "OPERATOR")
    private Integer operator;

    /**
     * 处理时间
     */
    @Column(name = "OPERATE_DATE")
    private Date operateDate;

    /**
     * 发送快递人
     */
    @Column(name = "SENDER")
    private Integer sender;

    /**
     * 取消的原因
     */
    @Column(name = "REMARKS")
    private String remarks;

    /**
     * 所在地区
     */
    private String area;

    /**
     * zip_code邮政编码
     */
    @Column(name = "zip_code")
    private String zipCode;

    /**
     * 寄送类型:  0挂号信  1顺丰  2自取 
     */
    @Column(name = "SEND_TYPE")
    private Integer sendType;

    /**
     * 开票张数
     */
    @Column(name = "NUMBER")
    private Integer number;

    /**
     * 开票面额
     */
    @Column(name = "AMOUNT")
    private Double amount;

    /**
     * @return ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取发票申请人
     *
     * @return PHONE - 发票申请人
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置发票申请人
     *
     * @param phone 发票申请人
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取订单号
     *
     * @return ORDER_ID - 订单号
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * 设置订单号
     *
     * @param orderId 订单号
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取开票总额
     *
     * @return TOTAL_AMOUNT - 开票总额
     */
    public Double getTotalAmount() {
        return totalAmount;
    }

    /**
     * 设置开票总额
     *
     * @param totalAmount 开票总额
     */
    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    /**
     * 获取接收人
     *
     * @return RECEIVER - 接收人
     */
    public String getReceiver() {
        return receiver;
    }

    /**
     * 设置接收人
     *
     * @param receiver 接收人
     */
    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    /**
     * 获取接收人电话
     *
     * @return RECEIVER_PHONE - 接收人电话
     */
    public String getReceiverPhone() {
        return receiverPhone;
    }

    /**
     * 设置接收人电话
     *
     * @param receiverPhone 接收人电话
     */
    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    /**
     * 获取邮寄地址
     *
     * @return RECEIVER_ADDRESS - 邮寄地址
     */
    public String getReceiverAddress() {
        return receiverAddress;
    }

    /**
     * 设置邮寄地址
     *
     * @param receiverAddress 邮寄地址
     */
    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    /**
     * 获取0:个人发票，1：公司发票
     *
     * @return INVOICE_TYPE - 0:个人发票，1：公司发票
     */
    public Integer getInvoiceType() {
        return invoiceType;
    }

    /**
     * 设置0:个人发票，1：公司发票
     *
     * @param invoiceType 0:个人发票，1：公司发票
     */
    public void setInvoiceType(Integer invoiceType) {
        this.invoiceType = invoiceType;
    }

    /**
     * 获取开票描述（订单号+价格） 
     *
     * @return INVOICE_DESCRIPTION - 开票描述（订单号+价格） 
     */
    public String getInvoiceDescription() {
        return invoiceDescription;
    }

    /**
     * 设置开票描述（订单号+价格） 
     *
     * @param invoiceDescription 开票描述（订单号+价格） 
     */
    public void setInvoiceDescription(String invoiceDescription) {
        this.invoiceDescription = invoiceDescription;
    }

    /**
     * 获取公司名称
     *
     * @return COMPANY_NAME - 公司名称
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * 设置公司名称
     *
     * @param companyName 公司名称
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * 获取纳税识别ID
     *
     * @return TAXPAYER_ID - 纳税识别ID
     */
    public String getTaxpayerId() {
        return taxpayerId;
    }

    /**
     * 设置纳税识别ID
     *
     * @param taxpayerId 纳税识别ID
     */
    public void setTaxpayerId(String taxpayerId) {
        this.taxpayerId = taxpayerId;
    }

    /**
     * 获取公司地址
     *
     * @return COMPANY_ADDRESS - 公司地址
     */
    public String getCompanyAddress() {
        return companyAddress;
    }

    /**
     * 设置公司地址
     *
     * @param companyAddress 公司地址
     */
    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    /**
     * 获取公司电话
     *
     * @return COMPANY_PHONE - 公司电话
     */
    public String getCompanyPhone() {
        return companyPhone;
    }

    /**
     * 设置公司电话
     *
     * @param companyPhone 公司电话
     */
    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone;
    }

    /**
     * 获取对公账户
     *
     * @return COMPANY_ACCOUNT - 对公账户
     */
    public String getCompanyAccount() {
        return companyAccount;
    }

    /**
     * 设置对公账户
     *
     * @param companyAccount 对公账户
     */
    public void setCompanyAccount(String companyAccount) {
        this.companyAccount = companyAccount;
    }

    /**
     * 获取开户行
     *
     * @return ACCOUNT_BANK - 开户行
     */
    public String getAccountBank() {
        return accountBank;
    }

    /**
     * 设置开户行
     *
     * @param accountBank 开户行
     */
    public void setAccountBank(String accountBank) {
        this.accountBank = accountBank;
    }

    /**
     * 获取申请时间
     *
     * @return APPLY_DATE - 申请时间
     */
    public Date getApplyDate() {
        return applyDate;
    }

    /**
     * 设置申请时间
     *
     * @param applyDate 申请时间
     */
    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    /**
     * 获取快递名称
     *
     * @return EXPRESS_NAME - 快递名称
     */
    public String getExpressName() {
        return expressName;
    }

    /**
     * 设置快递名称
     *
     * @param expressName 快递名称
     */
    public void setExpressName(String expressName) {
        this.expressName = expressName;
    }

    /**
     * 获取快递单号
     *
     * @return EXPRESS_NUMBER - 快递单号
     */
    public String getExpressNumber() {
        return expressNumber;
    }

    /**
     * 设置快递单号
     *
     * @param expressNumber 快递单号
     */
    public void setExpressNumber(String expressNumber) {
        this.expressNumber = expressNumber;
    }

    /**
     * 获取快递时间
     *
     * @return EXPRESS_DATE - 快递时间
     */
    public Date getExpressDate() {
        return expressDate;
    }

    /**
     * 设置快递时间
     *
     * @param expressDate 快递时间
     */
    public void setExpressDate(Date expressDate) {
        this.expressDate = expressDate;
    }

    /**
     * 获取0：拒绝  1：新申请 2：已开票 3：已快递 
     *
     * @return STATUS - 0：拒绝  1：新申请 2：已开票 3：已快递 
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置0：拒绝  1：新申请 2：已开票 3：已快递 
     *
     * @param status 0：拒绝  1：新申请 2：已开票 3：已快递 
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取处理人
     *
     * @return OPERATOR - 处理人
     */
    public Integer getOperator() {
        return operator;
    }

    /**
     * 设置处理人
     *
     * @param operator 处理人
     */
    public void setOperator(Integer operator) {
        this.operator = operator;
    }

    /**
     * 获取处理时间
     *
     * @return OPERATE_DATE - 处理时间
     */
    public Date getOperateDate() {
        return operateDate;
    }

    /**
     * 设置处理时间
     *
     * @param operateDate 处理时间
     */
    public void setOperateDate(Date operateDate) {
        this.operateDate = operateDate;
    }

    /**
     * 获取发送快递人
     *
     * @return SENDER - 发送快递人
     */
    public Integer getSender() {
        return sender;
    }

    /**
     * 设置发送快递人
     *
     * @param sender 发送快递人
     */
    public void setSender(Integer sender) {
        this.sender = sender;
    }

    /**
     * 获取取消的原因
     *
     * @return REMARKS - 取消的原因
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * 设置取消的原因
     *
     * @param remarks 取消的原因
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**
     * 获取所在地区
     *
     * @return area - 所在地区
     */
    public String getArea() {
        return area;
    }

    /**
     * 设置所在地区
     *
     * @param area 所在地区
     */
    public void setArea(String area) {
        this.area = area;
    }

    /**
     * 获取zip_code邮政编码
     *
     * @return zip_code - zip_code邮政编码
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * 设置zip_code邮政编码
     *
     * @param zipCode zip_code邮政编码
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * 获取寄送类型:  0挂号信  1顺丰  2自取 
     *
     * @return SEND_TYPE - 寄送类型:  0挂号信  1顺丰  2自取 
     */
    public Integer getSendType() {
        return sendType;
    }

    /**
     * 设置寄送类型:  0挂号信  1顺丰  2自取 
     *
     * @param sendType 寄送类型:  0挂号信  1顺丰  2自取 
     */
    public void setSendType(Integer sendType) {
        this.sendType = sendType;
    }

    /**
     * 获取开票张数
     *
     * @return NUMBER - 开票张数
     */
    public Integer getNumber() {
        return number;
    }

    /**
     * 设置开票张数
     *
     * @param number 开票张数
     */
    public void setNumber(Integer number) {
        this.number = number;
    }

    /**
     * 获取开票面额
     *
     * @return AMOUNT - 开票面额
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * 设置开票面额
     *
     * @param amount 开票面额
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }
}