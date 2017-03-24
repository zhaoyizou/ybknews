package com.visionet.platform.thirdpartydata.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_third_party_car_type")
public class ThirdPartyCarType {
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
     * 大众出行车型ID(0:舒适型,1:商务型,2:豪华型,3:经济型)
     */
    @Column(name = "DZCX_CAR_TYPE_ID")
    private Integer dzcxCarTypeId;

    /**
     * 大众出行车型名称
     */
    @Column(name = "DZCX_CAR_TYPE")
    private String dzcxCarType;

    /**
     * 第三方商户车型ID(34:舒适型,35:商务7座,40:多功能车)
     */
    @Column(name = "THIRD_PARTY_CAR_TYPE_ID")
    private Integer thirdPartyCarTypeId;

    /**
     * 第三方商户车型名称
     */
    @Column(name = "THIRD_PARTY_CAR_TYPE")
    private String thirdPartyCarType;

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
     * 获取大众出行车型ID(0:舒适型,1:商务型,2:豪华型,3:经济型)
     *
     * @return DZCX_CAR_TYPE_ID - 大众出行车型ID(0:舒适型,1:商务型,2:豪华型,3:经济型)
     */
    public Integer getDzcxCarTypeId() {
        return dzcxCarTypeId;
    }

    /**
     * 设置大众出行车型ID(0:舒适型,1:商务型,2:豪华型,3:经济型)
     *
     * @param dzcxCarTypeId 大众出行车型ID(0:舒适型,1:商务型,2:豪华型,3:经济型)
     */
    public void setDzcxCarTypeId(Integer dzcxCarTypeId) {
        this.dzcxCarTypeId = dzcxCarTypeId;
    }

    /**
     * 获取大众出行车型名称
     *
     * @return DZCX_CAR_TYPE - 大众出行车型名称
     */
    public String getDzcxCarType() {
        return dzcxCarType;
    }

    /**
     * 设置大众出行车型名称
     *
     * @param dzcxCarType 大众出行车型名称
     */
    public void setDzcxCarType(String dzcxCarType) {
        this.dzcxCarType = dzcxCarType;
    }

    /**
     * 获取第三方商户车型ID(34:舒适型,35:商务7座,40:多功能车)
     *
     * @return THIRD_PARTY_CAR_TYPE_ID - 第三方商户车型ID(34:舒适型,35:商务7座,40:多功能车)
     */
    public Integer getThirdPartyCarTypeId() {
        return thirdPartyCarTypeId;
    }

    /**
     * 设置第三方商户车型ID(34:舒适型,35:商务7座,40:多功能车)
     *
     * @param thirdPartyCarTypeId 第三方商户车型ID(34:舒适型,35:商务7座,40:多功能车)
     */
    public void setThirdPartyCarTypeId(Integer thirdPartyCarTypeId) {
        this.thirdPartyCarTypeId = thirdPartyCarTypeId;
    }

    /**
     * 获取第三方商户车型名称
     *
     * @return THIRD_PARTY_CAR_TYPE - 第三方商户车型名称
     */
    public String getThirdPartyCarType() {
        return thirdPartyCarType;
    }

    /**
     * 设置第三方商户车型名称
     *
     * @param thirdPartyCarType 第三方商户车型名称
     */
    public void setThirdPartyCarType(String thirdPartyCarType) {
        this.thirdPartyCarType = thirdPartyCarType;
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