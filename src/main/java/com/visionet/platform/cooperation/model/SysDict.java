package com.visionet.platform.cooperation.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "b_sys_dict")
public class SysDict {
    /**
     * PK
     */
    @Id
    @Column(name = "DICT_ID")
    private Integer dictId;

    /**
     * 0 全部，1：乘客 2：司机 3：后台
     */
    @Column(name = "USE_TYPE")
    private Integer useType;

    /**
     * 字典类型
     */
    @Column(name = "DICT_TYPE")
    private String dictType;

    /**
     * 字典名称
     */
    @Column(name = "DICT_NAME")
    private String dictName;

    /**
     * 字典值
     */
    @Column(name = "DICT_VALUE")
    private String dictValue;

    /**
     * 备注描述
     */
    @Column(name = "DESCRIPTION")
    private String description;

    /**
     * Y/N 0:N，1:Y
     */
    @Column(name = "DEL_FLAG")
    private Integer delFlag;

    @Column(name = "CREATE_BY")
    private Integer createBy;

    @Column(name = "CREATE_DATE")
    private Date createDate;

    @Column(name = "UPDATE_BY")
    private Integer updateBy;

    @Column(name = "UPDATE_DATE")
    private Date updateDate;

    @Column(name = "VERSION")
    private Integer version;

    /**
     * 城市ID
     */
    @Column(name = "CITY_ID")
    private Integer cityId;

    /**
     * 城市
     */
    private String city;

    /**
     * 获取PK
     *
     * @return DICT_ID - PK
     */
    public Integer getDictId() {
        return dictId;
    }

    /**
     * 设置PK
     *
     * @param dictId PK
     */
    public void setDictId(Integer dictId) {
        this.dictId = dictId;
    }

    /**
     * 获取0 全部，1：乘客 2：司机 3：后台
     *
     * @return USE_TYPE - 0 全部，1：乘客 2：司机 3：后台
     */
    public Integer getUseType() {
        return useType;
    }

    /**
     * 设置0 全部，1：乘客 2：司机 3：后台
     *
     * @param useType 0 全部，1：乘客 2：司机 3：后台
     */
    public void setUseType(Integer useType) {
        this.useType = useType;
    }

    /**
     * 获取字典类型
     *
     * @return DICT_TYPE - 字典类型
     */
    public String getDictType() {
        return dictType;
    }

    /**
     * 设置字典类型
     *
     * @param dictType 字典类型
     */
    public void setDictType(String dictType) {
        this.dictType = dictType;
    }

    /**
     * 获取字典名称
     *
     * @return DICT_NAME - 字典名称
     */
    public String getDictName() {
        return dictName;
    }

    /**
     * 设置字典名称
     *
     * @param dictName 字典名称
     */
    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    /**
     * 获取字典值
     *
     * @return DICT_VALUE - 字典值
     */
    public String getDictValue() {
        return dictValue;
    }

    /**
     * 设置字典值
     *
     * @param dictValue 字典值
     */
    public void setDictValue(String dictValue) {
        this.dictValue = dictValue;
    }

    /**
     * 获取备注描述
     *
     * @return DESCRIPTION - 备注描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置备注描述
     *
     * @param description 备注描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取Y/N 0:N，1:Y
     *
     * @return DEL_FLAG - Y/N 0:N，1:Y
     */
    public Integer getDelFlag() {
        return delFlag;
    }

    /**
     * 设置Y/N 0:N，1:Y
     *
     * @param delFlag Y/N 0:N，1:Y
     */
    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    /**
     * @return CREATE_BY
     */
    public Integer getCreateBy() {
        return createBy;
    }

    /**
     * @param createBy
     */
    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    /**
     * @return CREATE_DATE
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * @return UPDATE_BY
     */
    public Integer getUpdateBy() {
        return updateBy;
    }

    /**
     * @param updateBy
     */
    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    /**
     * @return UPDATE_DATE
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * @param updateDate
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * @return VERSION
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * @param version
     */
    public void setVersion(Integer version) {
        this.version = version;
    }

    /**
     * 获取城市ID
     *
     * @return CITY_ID - 城市ID
     */
    public Integer getCityId() {
        return cityId;
    }

    /**
     * 设置城市ID
     *
     * @param cityId 城市ID
     */
    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    /**
     * 获取城市
     *
     * @return city - 城市
     */
    public String getCity() {
        return city;
    }

    /**
     * 设置城市
     *
     * @param city 城市
     */
    public void setCity(String city) {
        this.city = city;
    }
}