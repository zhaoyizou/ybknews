package com.visionet.platform.thirdpartydata.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_third_party_white_list")
public class ThirdPartyWhiteList {
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
     * 第三方商户白名单IP
     */
    @Column(name = "THIRD_PARTY_IP")
    private String thirdPartyIp;

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
     * 获取第三方商户白名单IP
     *
     * @return THIRD_PARTY_IP - 第三方商户白名单IP
     */
    public String getThirdPartyIp() {
        return thirdPartyIp;
    }

    /**
     * 设置第三方商户白名单IP
     *
     * @param thirdPartyIp 第三方商户白名单IP
     */
    public void setThirdPartyIp(String thirdPartyIp) {
        this.thirdPartyIp = thirdPartyIp;
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