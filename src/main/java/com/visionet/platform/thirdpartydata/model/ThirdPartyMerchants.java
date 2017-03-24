package com.visionet.platform.thirdpartydata.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_third_party_merchants")
public class ThirdPartyMerchants {
    /**
     * 商户id
     */
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 商户名称
     */
    @Column(name = "MERCHANT_NAME")
    private String merchantName;

    /**
     * 商户号
     */
    @Column(name = "MERCHANT_NUMBER")
    private String merchantNumber;

    /**
     * 大众出行密钥
     */
    @Column(name = "DZCX_SEC")
    private String dzcxSec;

    /**
     * 商户密钥
     */
    @Column(name = "MERCHANT_SEC")
    private String merchantSec;

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
     * 删除标志(0:逻辑删除,1:正常)
     */
    @Column(name = "DEL_FLAG")
    private Integer delFlag;

    /**
     * 获取商户id
     *
     * @return ID - 商户id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置商户id
     *
     * @param id 商户id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取商户名称
     *
     * @return MERCHANT_NAME - 商户名称
     */
    public String getMerchantName() {
        return merchantName;
    }

    /**
     * 设置商户名称
     *
     * @param merchantName 商户名称
     */
    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    /**
     * 获取商户号
     *
     * @return MERCHANT_NUMBER - 商户号
     */
    public String getMerchantNumber() {
        return merchantNumber;
    }

    /**
     * 设置商户号
     *
     * @param merchantNumber 商户号
     */
    public void setMerchantNumber(String merchantNumber) {
        this.merchantNumber = merchantNumber;
    }

    /**
     * 获取大众出行密钥
     *
     * @return DZCX_SEC - 大众出行密钥
     */
    public String getDzcxSec() {
        return dzcxSec;
    }

    /**
     * 设置大众出行密钥
     *
     * @param dzcxSec 大众出行密钥
     */
    public void setDzcxSec(String dzcxSec) {
        this.dzcxSec = dzcxSec;
    }

    /**
     * 获取商户密钥
     *
     * @return MERCHANT_SEC - 商户密钥
     */
    public String getMerchantSec() {
        return merchantSec;
    }

    /**
     * 设置商户密钥
     *
     * @param merchantSec 商户密钥
     */
    public void setMerchantSec(String merchantSec) {
        this.merchantSec = merchantSec;
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

    /**
     * 获取删除标志(0:逻辑删除,1:正常)
     *
     * @return DEL_FLAG - 删除标志(0:逻辑删除,1:正常)
     */
    public Integer getDelFlag() {
        return delFlag;
    }

    /**
     * 设置删除标志(0:逻辑删除,1:正常)
     *
     * @param delFlag 删除标志(0:逻辑删除,1:正常)
     */
    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }
}