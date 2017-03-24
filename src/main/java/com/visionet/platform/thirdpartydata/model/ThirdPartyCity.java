package com.visionet.platform.thirdpartydata.model;

import javax.persistence.*;
import java.util.Date;

@Table(name = "t_third_party_city")
public class ThirdPartyCity {
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
     * 大众出行城市ID
     */
    @Column(name = "DZCX_CITY_ID")
    private Integer dzcxCityId;

    /**
     * 大众出行城市名称
     */
    @Column(name = "DZCX_CITY")
    private String dzcxCity;

    /**
     * 第三方商户城市ID
     */
    @Column(name = "THIRD_PARTY_CITY_ID")
    private Integer thirdPartyCityId;

    /**
     * 第三方商户城市名称
     */
    @Column(name = "THIRD_PARTY_CITY")
    private String thirdPartyCity;

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
     * 发送单到第三方开关 0 不发单 1发单
     */
    @Column(name = "PUSH_ORDER")
    private Integer pushOrder;

    /**
     * 接收第三方订单 0 不接收 1 接收
     */
    @Column(name = "RECIVE_ORDER")
    private Integer reciveOrder;

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
     * 获取大众出行城市ID
     *
     * @return DZCX_CITY_ID - 大众出行城市ID
     */
    public Integer getDzcxCityId() {
        return dzcxCityId;
    }

    /**
     * 设置大众出行城市ID
     *
     * @param dzcxCityId 大众出行城市ID
     */
    public void setDzcxCityId(Integer dzcxCityId) {
        this.dzcxCityId = dzcxCityId;
    }

    /**
     * 获取大众出行城市名称
     *
     * @return DZCX_CITY - 大众出行城市名称
     */
    public String getDzcxCity() {
        return dzcxCity;
    }

    /**
     * 设置大众出行城市名称
     *
     * @param dzcxCity 大众出行城市名称
     */
    public void setDzcxCity(String dzcxCity) {
        this.dzcxCity = dzcxCity;
    }

    /**
     * 获取第三方商户城市ID
     *
     * @return THIRD_PARTY_CITY_ID - 第三方商户城市ID
     */
    public Integer getThirdPartyCityId() {
        return thirdPartyCityId;
    }

    /**
     * 设置第三方商户城市ID
     *
     * @param thirdPartyCityId 第三方商户城市ID
     */
    public void setThirdPartyCityId(Integer thirdPartyCityId) {
        this.thirdPartyCityId = thirdPartyCityId;
    }

    /**
     * 获取第三方商户城市名称
     *
     * @return THIRD_PARTY_CITY - 第三方商户城市名称
     */
    public String getThirdPartyCity() {
        return thirdPartyCity;
    }

    /**
     * 设置第三方商户城市名称
     *
     * @param thirdPartyCity 第三方商户城市名称
     */
    public void setThirdPartyCity(String thirdPartyCity) {
        this.thirdPartyCity = thirdPartyCity;
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

    public Integer getPushOrder() {
        return pushOrder;
    }

    public void setPushOrder(Integer pushOrder) {
        this.pushOrder = pushOrder;
    }

    public Integer getReciveOrder() {
        return reciveOrder;
    }

    public void setReciveOrder(Integer reciveOrder) {
        this.reciveOrder = reciveOrder;
    }
}