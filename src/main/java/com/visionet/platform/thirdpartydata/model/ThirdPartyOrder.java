package com.visionet.platform.thirdpartydata.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "t_third_party_order")
public class ThirdPartyOrder {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 订单来源（0：大众出行、1：首汽约车）
     */
    @Column(name = "SOURCE")
    private Integer source;

    /**
     * 第三方商户ID
     */
    @Column(name = "MERCHANT_ID")
    private Integer merchantId;

    /**
     * 大众出行订单号
     */
    @Column(name = "ORDER_ID")
    private String orderId;

    /**
     * 合作方订单ID
     */
    @Column(name = "PARTNER_ORDER_ID")
    private String partnerOrderId;

    /**
     * 合作方订单号
     */
    @Column(name = "PARTNER_ORDER_NO")
    private String partnerOrderNo;

    /**
     * 支付类型（1：乘客支付、2：司机代收）
     */
    @Column(name = "PAY_TYPE")
    private Integer payType;

    /**
     * 代收金额
     */
    @Column(name = "PAY_AMOUNT")
    private BigDecimal payAmount;

    /**
     * 代收状态（0：等待代收、1：代收成功）
     */
    @Column(name = "PAY_STATUS")
    private Integer payStatus;

    /**
     * 订单完成时间
     */
    @Column(name = "FINISH_DATE")
    private Date finishDate;

    /**
     * 创建人
     */
    @Column(name = "CREATE_BY")
    private Integer createBy;

    /**
     * 创建时间
     */
    @Column(name = "CREATE_DATE")
    private Date createDate;

    /**
     * 更新人
     */
    @Column(name = "UPDATE_BY")
    private Integer updateBy;

    /**
     * 更新时间
     */
    @Column(name = "UPDATE_DATE")
    private Date updateDate;

    /**
     * 乘客取消订单扣款金额
     */
    @Column(name = "CUSTOMER_CANCEL_CHARGE")
    private BigDecimal customerCancelCharge;

    /**
     * 司机取消订单扣款金额
     */
    @Column(name = "CARUSER_CANCEL_CHARGE")
    private BigDecimal caruserCancelCharge;

    /**
     * 通知状态（0：未通知、1：已通知）
     */
    @Column(name = "NOTICE")
    private Integer notice;

    /**
     * 0 实时单 1 预约单
     */
    @Column(name = "ORDER_TYPE")
    private Integer orderType;

    //通知首约有司机接单 0 未通知 1 通知
    @Column(name = "DRIVER_INFO_NOTICE")
    private Integer driverInfoNotice;

    //通知大众使用大众司机 0 未通知 1 已通知
    @Column(name = "DRIVER_INFO_NOTICE_CALL_BACK")
    private Integer driverInfoNoticeCallBack;
    //通知大众使用大众司机 0 未通知 1 已通知
    @Column(name = "DRIVER_ID")
    private Integer driverId;

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
     * 获取订单来源（0：大众出行、1：首汽约车）
     *
     * @return SOURCE - 订单来源（0：大众出行、1：首汽约车）
     */
    public Integer getSource() {
        return source;
    }

    /**
     * 设置订单来源（0：大众出行、1：首汽约车）
     *
     * @param source 订单来源（0：大众出行、1：首汽约车）
     */
    public void setSource(Integer source) {
        this.source = source;
    }

    /**
     * 获取第三方商户ID
     *
     * @return MERCHANT_ID - 第三方商户ID
     */
    public Integer getMerchantId() {
        return merchantId;
    }

    /**
     * 设置第三方商户ID
     *
     * @param merchantId 第三方商户ID
     */
    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    /**
     * 获取大众出行订单号
     *
     * @return ORDER_ID - 大众出行订单号
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * 设置大众出行订单号
     *
     * @param orderId 大众出行订单号
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取合作方订单ID
     *
     * @return PARTNER_ORDER_ID - 合作方订单ID
     */
    public String getPartnerOrderId() {
        return partnerOrderId;
    }

    /**
     * 设置合作方订单ID
     *
     * @param partnerOrderId 合作方订单ID
     */
    public void setPartnerOrderId(String partnerOrderId) {
        this.partnerOrderId = partnerOrderId;
    }

    /**
     * 获取合作方订单号
     *
     * @return PARTNER_ORDER_NO - 合作方订单号
     */
    public String getPartnerOrderNo() {
        return partnerOrderNo;
    }

    /**
     * 设置合作方订单号
     *
     * @param partnerOrderNo 合作方订单号
     */
    public void setPartnerOrderNo(String partnerOrderNo) {
        this.partnerOrderNo = partnerOrderNo;
    }

    /**
     * 获取支付类型（1：乘客支付、2：司机代收）
     *
     * @return PAY_TYPE - 支付类型（1：乘客支付、2：司机代收）
     */
    public Integer getPayType() {
        return payType;
    }

    /**
     * 设置支付类型（1：乘客支付、2：司机代收）
     *
     * @param payType 支付类型（1：乘客支付、2：司机代收）
     */
    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    /**
     * 获取代收金额
     *
     * @return PAY_AMOUNT - 代收金额
     */
    public BigDecimal getPayAmount() {
        return payAmount;
    }

    /**
     * 设置代收金额
     *
     * @param payAmount 代收金额
     */
    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    /**
     * 获取代收状态（0：等待代收、1：代收成功）
     *
     * @return PAY_STATUS - 代收状态（0：等待代收、1：代收成功）
     */
    public Integer getPayStatus() {
        return payStatus;
    }

    /**
     * 设置代收状态（0：等待代收、1：代收成功）
     *
     * @param payStatus 代收状态（0：等待代收、1：代收成功）
     */
    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    /**
     * 获取订单完成时间
     *
     * @return FINISH_DATE - 订单完成时间
     */
    public Date getFinishDate() {
        return finishDate;
    }

    /**
     * 设置订单完成时间
     *
     * @param finishDate 订单完成时间
     */
    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    /**
     * 获取创建人
     *
     * @return CREATE_BY - 创建人
     */
    public Integer getCreateBy() {
        return createBy;
    }

    /**
     * 设置创建人
     *
     * @param createBy 创建人
     */
    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    /**
     * 获取创建时间
     *
     * @return CREATE_DATE - 创建时间
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置创建时间
     *
     * @param createDate 创建时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取更新人
     *
     * @return UPDATE_BY - 更新人
     */
    public Integer getUpdateBy() {
        return updateBy;
    }

    /**
     * 设置更新人
     *
     * @param updateBy 更新人
     */
    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    /**
     * 获取更新时间
     *
     * @return UPDATE_DATE - 更新时间
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * 设置更新时间
     *
     * @param updateDate 更新时间
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * 获取乘客取消订单扣款金额
     *
     * @return CUSTOMER_CANCEL_CHARGE - 乘客取消订单扣款金额
     */
    public BigDecimal getCustomerCancelCharge() {
        return customerCancelCharge;
    }

    /**
     * 设置乘客取消订单扣款金额
     *
     * @param customerCancelCharge 乘客取消订单扣款金额
     */
    public void setCustomerCancelCharge(BigDecimal customerCancelCharge) {
        this.customerCancelCharge = customerCancelCharge;
    }

    /**
     * 获取司机取消订单扣款金额
     *
     * @return CARUSER_CANCEL_CHARGE - 司机取消订单扣款金额
     */
    public BigDecimal getCaruserCancelCharge() {
        return caruserCancelCharge;
    }

    /**
     * 设置司机取消订单扣款金额
     *
     * @param caruserCancelCharge 司机取消订单扣款金额
     */
    public void setCaruserCancelCharge(BigDecimal caruserCancelCharge) {
        this.caruserCancelCharge = caruserCancelCharge;
    }

    /**
     * 获取通知状态（0：未通知、1：已通知）
     *
     * @return NOTICE - 通知状态（0：未通知、1：已通知）
     */
    public Integer getNotice() {
        return notice;
    }

    /**
     * 设置通知状态（0：未通知、1：已通知）
     *
     * @param notice 通知状态（0：未通知、1：已通知）
     */
    public void setNotice(Integer notice) {
        this.notice = notice;
    }

    /**
     * 获取0 实时单 1 预约单
     *
     * @return ORDER_TYPE - 0 实时单 1 预约单
     */
    public Integer getOrderType() {
        return orderType;
    }

    /**
     * 设置0 实时单 1 预约单
     *
     * @param orderType 0 实时单 1 预约单
     */
    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Integer getDriverInfoNotice() {
        return driverInfoNotice;
    }

    public void setDriverInfoNotice(Integer driverInfoNotice) {
        this.driverInfoNotice = driverInfoNotice;
    }

    public Integer getDriverInfoNoticeCallBack() {
        return driverInfoNoticeCallBack;
    }

    public void setDriverInfoNoticeCallBack(Integer driverInfoNoticeCallBack) {
        this.driverInfoNoticeCallBack = driverInfoNoticeCallBack;
    }

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }
}