package com.visionet.platform.cooperation.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_order_pay_detail")
public class OrderPayDetail {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 订单号
     */
    @Column(name = "ORDER_ID")
    private String orderId;

    /**
     * 货主手机号
     */
    @Column(name = "CUSTOMER_PHONE")
    private String customerPhone;

    /**
     * 车主ID
     */
    @Column(name = "CAR_ID")
    private Integer carId;

    /**
     * 车主手机号
     */
    @Column(name = "CAR_USER_PHONE")
    private String carUserPhone;

    /**
     * 0卡券，1支付宝，2现金，3微信，4货主储值账户，5约租币,6:pos刷卡支付,7:一卡通
     */
    @Column(name = "TYPE")
    private Integer type;

    /**
     * 支付账户号，如卡券编号，支付宝账号
     */
    @Column(name = "ACCOUNT")
    private String account;

    /**
     * 金额
     */
    @Column(name = "MONEY")
    private Float money;

    /**
     * 车主获得
     */
    @Column(name = "CAR_USER_GET")
    private Float carUserGet;

    /**
     * 大众平台获得
     */
    @Column(name = "PLATE_GET")
    private Float plateGet;

    /**
     * 支付时间
     */
    @Column(name = "CREATE_DATE")
    private Date createDate;

    /**
     * 0：逻辑删除，1：有效
     */
    @Column(name = "DEL_FLAG")
    private Integer delFlag;

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
     * 获取货主手机号
     *
     * @return CUSTOMER_PHONE - 货主手机号
     */
    public String getCustomerPhone() {
        return customerPhone;
    }

    /**
     * 设置货主手机号
     *
     * @param customerPhone 货主手机号
     */
    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    /**
     * 获取车主ID
     *
     * @return CAR_ID - 车主ID
     */
    public Integer getCarId() {
        return carId;
    }

    /**
     * 设置车主ID
     *
     * @param carId 车主ID
     */
    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    /**
     * 获取车主手机号
     *
     * @return CAR_USER_PHONE - 车主手机号
     */
    public String getCarUserPhone() {
        return carUserPhone;
    }

    /**
     * 设置车主手机号
     *
     * @param carUserPhone 车主手机号
     */
    public void setCarUserPhone(String carUserPhone) {
        this.carUserPhone = carUserPhone;
    }

    /**
     * 获取0卡券，1支付宝，2现金，3微信，4货主储值账户，5约租币,6:pos刷卡支付,7:一卡通
     *
     * @return TYPE - 0卡券，1支付宝，2现金，3微信，4货主储值账户，5约租币,6:pos刷卡支付,7:一卡通
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置0卡券，1支付宝，2现金，3微信，4货主储值账户，5约租币,6:pos刷卡支付,7:一卡通
     *
     * @param type 0卡券，1支付宝，2现金，3微信，4货主储值账户，5约租币,6:pos刷卡支付,7:一卡通
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取支付账户号，如卡券编号，支付宝账号
     *
     * @return ACCOUNT - 支付账户号，如卡券编号，支付宝账号
     */
    public String getAccount() {
        return account;
    }

    /**
     * 设置支付账户号，如卡券编号，支付宝账号
     *
     * @param account 支付账户号，如卡券编号，支付宝账号
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * 获取金额
     *
     * @return MONEY - 金额
     */
    public Float getMoney() {
        return money;
    }

    /**
     * 设置金额
     *
     * @param money 金额
     */
    public void setMoney(Float money) {
        this.money = money;
    }

    /**
     * 获取车主获得
     *
     * @return CAR_USER_GET - 车主获得
     */
    public Float getCarUserGet() {
        return carUserGet;
    }

    /**
     * 设置车主获得
     *
     * @param carUserGet 车主获得
     */
    public void setCarUserGet(Float carUserGet) {
        this.carUserGet = carUserGet;
    }

    /**
     * 获取大众平台获得
     *
     * @return PLATE_GET - 大众平台获得
     */
    public Float getPlateGet() {
        return plateGet;
    }

    /**
     * 设置大众平台获得
     *
     * @param plateGet 大众平台获得
     */
    public void setPlateGet(Float plateGet) {
        this.plateGet = plateGet;
    }

    /**
     * 获取支付时间
     *
     * @return CREATE_DATE - 支付时间
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置支付时间
     *
     * @param createDate 支付时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取0：逻辑删除，1：有效
     *
     * @return DEL_FLAG - 0：逻辑删除，1：有效
     */
    public Integer getDelFlag() {
        return delFlag;
    }

    /**
     * 设置0：逻辑删除，1：有效
     *
     * @param delFlag 0：逻辑删除，1：有效
     */
    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }
}