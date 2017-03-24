package com.visionet.platform.thirdpartydata.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_third_party_app_cancel")
public class ThirdPartyAppCancel {
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
     * 大众出行APP取消类型ID(3:订单取消)
     */
    @Column(name = "DZCX_CANCEL_TYPE_ID")
    private Integer dzcxCancelTypeId;

    /**
     * 大众出行APP取消类型
     */
    @Column(name = "DZCX_CANCEL_TYPE")
    private String dzcxCancelType;

    /**
     * 第三方商户APP取消类型ID
     */
    @Column(name = "THIRD_PARTY_CANCEL_TYPE_ID")
    private Integer thirdPartyCancelTypeId;

    /**
     * 第三方商户APP取消类型
     */
    @Column(name = "THIRD_PARTY_CANCEL_TYPE")
    private String thirdPartyCancelType;

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
     * 获取大众出行APP取消类型ID(3:订单取消)
     *
     * @return DZCX_CANCEL_TYPE_ID - 大众出行APP取消类型ID(3:订单取消)
     */
    public Integer getDzcxCancelTypeId() {
        return dzcxCancelTypeId;
    }

    /**
     * 设置大众出行APP取消类型ID(3:订单取消)
     *
     * @param dzcxCancelTypeId 大众出行APP取消类型ID(3:订单取消)
     */
    public void setDzcxCancelTypeId(Integer dzcxCancelTypeId) {
        this.dzcxCancelTypeId = dzcxCancelTypeId;
    }

    /**
     * 获取大众出行APP取消类型
     *
     * @return DZCX_CANCEL_TYPE - 大众出行APP取消类型
     */
    public String getDzcxCancelType() {
        return dzcxCancelType;
    }

    /**
     * 设置大众出行APP取消类型
     *
     * @param dzcxCancelType 大众出行APP取消类型
     */
    public void setDzcxCancelType(String dzcxCancelType) {
        this.dzcxCancelType = dzcxCancelType;
    }

    /**
     * 获取第三方商户APP取消类型ID
     *
     * @return THIRD_PARTY_CANCEL_TYPE_ID - 第三方商户APP取消类型ID
     */
    public Integer getThirdPartyCancelTypeId() {
        return thirdPartyCancelTypeId;
    }

    /**
     * 设置第三方商户APP取消类型ID
     *
     * @param thirdPartyCancelTypeId 第三方商户APP取消类型ID
     */
    public void setThirdPartyCancelTypeId(Integer thirdPartyCancelTypeId) {
        this.thirdPartyCancelTypeId = thirdPartyCancelTypeId;
    }

    /**
     * 获取第三方商户APP取消类型
     *
     * @return THIRD_PARTY_CANCEL_TYPE - 第三方商户APP取消类型
     */
    public String getThirdPartyCancelType() {
        return thirdPartyCancelType;
    }

    /**
     * 设置第三方商户APP取消类型
     *
     * @param thirdPartyCancelType 第三方商户APP取消类型
     */
    public void setThirdPartyCancelType(String thirdPartyCancelType) {
        this.thirdPartyCancelType = thirdPartyCancelType;
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