package com.visionet.platform.thirdpartydata.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_third_party_order_status")
public class ThirdPartyOrderStatus {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 第三方商户ID
     */
    @Column(name = "MERCHANT_ID")
    private Integer merchantId;

    /**
     * 大众出行订单状态(0:寻车中,1:已接单,2:完成,3:取消,6:超时,8:待支付,9:待结算,11:开始计价,12:计价完成,14:到达乘客出发地)
     */
    @Column(name = "DZCX_ORDER_STATUS")
    private Integer dzcxOrderStatus;

    /**
     * 大众出行订单状态(中文)
     */
    @Column(name = "DZCX_ORDER_STATUS_CN")
    private String dzcxOrderStatusCn;

    /**
     * 第三方商户订单状态(10:预定中,13:已受理,15:待服务,20:已出发,25:已到达,30:服务中,40:待结算,42:支付中,43:扣款中,45:已结算,50:已完成,55:订单异议,60:已取消)
     */
    @Column(name = "THIRD_PARTY_ORDER_STATUS")
    private Integer thirdPartyOrderStatus;

    /**
     * 第三方商户订单状态(中文)
     */
    @Column(name = "THIRD_PARTY_ORDER_STATUS_CN")
    private String thirdPartyOrderStatusCn;

    /**
     * 创建时间
     */
    @Column(name = "CREATE_DATE")
    private Date createDate;

    /**
     * 创建人
     */
    @Column(name = "CREATE_BY")
    private Integer createBy;

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
     * 获取大众出行订单状态(0:寻车中,1:已接单,2:完成,3:取消,6:超时,8:待支付,9:待结算,11:开始计价,12:计价完成,14:到达乘客出发地)
     *
     * @return DZCX_ORDER_STATUS - 大众出行订单状态(0:寻车中,1:已接单,2:完成,3:取消,6:超时,8:待支付,9:待结算,11:开始计价,12:计价完成,14:到达乘客出发地)
     */
    public Integer getDzcxOrderStatus() {
        return dzcxOrderStatus;
    }

    /**
     * 设置大众出行订单状态(0:寻车中,1:已接单,2:完成,3:取消,6:超时,8:待支付,9:待结算,11:开始计价,12:计价完成,14:到达乘客出发地)
     *
     * @param dzcxOrderStatus 大众出行订单状态(0:寻车中,1:已接单,2:完成,3:取消,6:超时,8:待支付,9:待结算,11:开始计价,12:计价完成,14:到达乘客出发地)
     */
    public void setDzcxOrderStatus(Integer dzcxOrderStatus) {
        this.dzcxOrderStatus = dzcxOrderStatus;
    }

    /**
     * 获取大众出行订单状态(中文)
     *
     * @return DZCX_ORDER_STATUS_CN - 大众出行订单状态(中文)
     */
    public String getDzcxOrderStatusCn() {
        return dzcxOrderStatusCn;
    }

    /**
     * 设置大众出行订单状态(中文)
     *
     * @param dzcxOrderStatusCn 大众出行订单状态(中文)
     */
    public void setDzcxOrderStatusCn(String dzcxOrderStatusCn) {
        this.dzcxOrderStatusCn = dzcxOrderStatusCn;
    }

    /**
     * 获取第三方商户订单状态(10:预定中,13:已受理,15:待服务,20:已出发,25:已到达,30:服务中,40:待结算,42:支付中,43:扣款中,45:已结算,50:已完成,55:订单异议,60:已取消)
     *
     * @return THIRD_PARTY_ORDER_STATUS - 第三方商户订单状态(10:预定中,13:已受理,15:待服务,20:已出发,25:已到达,30:服务中,40:待结算,42:支付中,43:扣款中,45:已结算,50:已完成,55:订单异议,60:已取消)
     */
    public Integer getThirdPartyOrderStatus() {
        return thirdPartyOrderStatus;
    }

    /**
     * 设置第三方商户订单状态(10:预定中,13:已受理,15:待服务,20:已出发,25:已到达,30:服务中,40:待结算,42:支付中,43:扣款中,45:已结算,50:已完成,55:订单异议,60:已取消)
     *
     * @param thirdPartyOrderStatus 第三方商户订单状态(10:预定中,13:已受理,15:待服务,20:已出发,25:已到达,30:服务中,40:待结算,42:支付中,43:扣款中,45:已结算,50:已完成,55:订单异议,60:已取消)
     */
    public void setThirdPartyOrderStatus(Integer thirdPartyOrderStatus) {
        this.thirdPartyOrderStatus = thirdPartyOrderStatus;
    }

    /**
     * 获取第三方商户订单状态(中文)
     *
     * @return THIRD_PARTY_ORDER_STATUS_CN - 第三方商户订单状态(中文)
     */
    public String getThirdPartyOrderStatusCn() {
        return thirdPartyOrderStatusCn;
    }

    /**
     * 设置第三方商户订单状态(中文)
     *
     * @param thirdPartyOrderStatusCn 第三方商户订单状态(中文)
     */
    public void setThirdPartyOrderStatusCn(String thirdPartyOrderStatusCn) {
        this.thirdPartyOrderStatusCn = thirdPartyOrderStatusCn;
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
}