package com.visionet.platform.cooperation.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_order_status_tracking")
public class OrderStatusTracking {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 订单ID
     */
    @Column(name = "ORDER_ID")
    private String orderId;

    /**
     * 司机手机号
     */
    @Column(name = "CAR_USER_PHONE")
    private String carUserPhone;

    /**
     * 乘客手机号
     */
    @Column(name = "CUSTOMER_PHONE")
    private String customerPhone;

    /**
     * 业务类型(0出租车,1约租车,2乘客)
     */
    @Column(name = "BUSINESS_TYPE")
    private Integer businessType;

    /**
     * 订单前一个状态
     */
    @Column(name = "PRE_STATUS")
    private Integer preStatus;

    /**
     * 订单更新状态
     */
    @Column(name = "NEW_STATUS")
    private Integer newStatus;

    /**
     * 创建时间
     */
    @Column(name = "CREATE_DATE")
    private Date createDate;

    /**
     * 操作人0：乘客，1：司机，2：订单服务器 3：后台
     */
    @Column(name = "OPERATOR")
    private Integer operator;

    /**
     * 用户id
     */
    @Column(name = "USER_ID")
    private Integer userId;

    /**
     * 监控席操作状态(0：未处理，1未确认，4已确认，5重试)
     */
    @Column(name = "CHECK_STATUS")
    private Integer checkStatus;

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
     * 获取订单ID
     *
     * @return ORDER_ID - 订单ID
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * 设置订单ID
     *
     * @param orderId 订单ID
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取司机手机号
     *
     * @return CAR_USER_PHONE - 司机手机号
     */
    public String getCarUserPhone() {
        return carUserPhone;
    }

    /**
     * 设置司机手机号
     *
     * @param carUserPhone 司机手机号
     */
    public void setCarUserPhone(String carUserPhone) {
        this.carUserPhone = carUserPhone;
    }

    /**
     * 获取乘客手机号
     *
     * @return CUSTOMER_PHONE - 乘客手机号
     */
    public String getCustomerPhone() {
        return customerPhone;
    }

    /**
     * 设置乘客手机号
     *
     * @param customerPhone 乘客手机号
     */
    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    /**
     * 获取业务类型(0出租车,1约租车,2乘客)
     *
     * @return BUSINESS_TYPE - 业务类型(0出租车,1约租车,2乘客)
     */
    public Integer getBusinessType() {
        return businessType;
    }

    /**
     * 设置业务类型(0出租车,1约租车,2乘客)
     *
     * @param businessType 业务类型(0出租车,1约租车,2乘客)
     */
    public void setBusinessType(Integer businessType) {
        this.businessType = businessType;
    }

    /**
     * 获取订单前一个状态
     *
     * @return PRE_STATUS - 订单前一个状态
     */
    public Integer getPreStatus() {
        return preStatus;
    }

    /**
     * 设置订单前一个状态
     *
     * @param preStatus 订单前一个状态
     */
    public void setPreStatus(Integer preStatus) {
        this.preStatus = preStatus;
    }

    /**
     * 获取订单更新状态
     *
     * @return NEW_STATUS - 订单更新状态
     */
    public Integer getNewStatus() {
        return newStatus;
    }

    /**
     * 设置订单更新状态
     *
     * @param newStatus 订单更新状态
     */
    public void setNewStatus(Integer newStatus) {
        this.newStatus = newStatus;
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
     * 获取操作人0：乘客，1：司机，2：订单服务器 3：后台
     *
     * @return OPERATOR - 操作人0：乘客，1：司机，2：订单服务器 3：后台
     */
    public Integer getOperator() {
        return operator;
    }

    /**
     * 设置操作人0：乘客，1：司机，2：订单服务器 3：后台
     *
     * @param operator 操作人0：乘客，1：司机，2：订单服务器 3：后台
     */
    public void setOperator(Integer operator) {
        this.operator = operator;
    }

    /**
     * 获取用户id
     *
     * @return USER_ID - 用户id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取监控席操作状态(0：未处理，1未确认，4已确认，5重试)
     *
     * @return CHECK_STATUS - 监控席操作状态(0：未处理，1未确认，4已确认，5重试)
     */
    public Integer getCheckStatus() {
        return checkStatus;
    }

    /**
     * 设置监控席操作状态(0：未处理，1未确认，4已确认，5重试)
     *
     * @param checkStatus 监控席操作状态(0：未处理，1未确认，4已确认，5重试)
     */
    public void setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
    }
}