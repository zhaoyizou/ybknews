package com.visionet.platform.cooperation.model;

import javax.persistence.*;

@Table(name = "t_city_base_point")
public class CityBasePoint {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 城市ID
     */
    @Column(name = "CITY_ID")
    private Integer cityId;

    /**
     * 城市名称
     */
    @Column(name = "CITY")
    private String city;

    /**
     * 基准纬度
     */
    @Column(name = "BASE_LAT")
    private String baseLat;

    /**
     * 基准经度
     */
    @Column(name = "BASE_LON")
    private String baseLon;

    @Column(name = "DEL_FLAG")
    private Boolean delFlag;

    /**
     * 最大宽度米
     */
    @Column(name = "MAX_WIDE")
    private Integer maxWide;

    /**
     * 最大长度米
     */
    @Column(name = "MAX_LONG")
    private Integer maxLong;

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
     * 获取城市名称
     *
     * @return CITY - 城市名称
     */
    public String getCity() {
        return city;
    }

    /**
     * 设置城市名称
     *
     * @param city 城市名称
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 获取基准纬度
     *
     * @return BASE_LAT - 基准纬度
     */
    public String getBaseLat() {
        return baseLat;
    }

    /**
     * 设置基准纬度
     *
     * @param baseLat 基准纬度
     */
    public void setBaseLat(String baseLat) {
        this.baseLat = baseLat;
    }

    /**
     * 获取基准经度
     *
     * @return BASE_LON - 基准经度
     */
    public String getBaseLon() {
        return baseLon;
    }

    /**
     * 设置基准经度
     *
     * @param baseLon 基准经度
     */
    public void setBaseLon(String baseLon) {
        this.baseLon = baseLon;
    }

    /**
     * @return DEL_FLAG
     */
    public Boolean getDelFlag() {
        return delFlag;
    }

    /**
     * @param delFlag
     */
    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }

    /**
     * 获取最大宽度米
     *
     * @return MAX_WIDE - 最大宽度米
     */
    public Integer getMaxWide() {
        return maxWide;
    }

    /**
     * 设置最大宽度米
     *
     * @param maxWide 最大宽度米
     */
    public void setMaxWide(Integer maxWide) {
        this.maxWide = maxWide;
    }

    /**
     * 获取最大长度米
     *
     * @return MAX_LONG - 最大长度米
     */
    public Integer getMaxLong() {
        return maxLong;
    }

    /**
     * 设置最大长度米
     *
     * @param maxLong 最大长度米
     */
    public void setMaxLong(Integer maxLong) {
        this.maxLong = maxLong;
    }
}