package com.visionet.platform.cooperation.model;

import javax.persistence.*;

@Table(name = "t_push_des")
public class PushDes {
    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户手机号
     */
    private String phone;

    /**
     * 极光唯一标识
     */
    @Column(name = "channel_id")
    private String channelId;

    /**
     * 设备类型（3:android, 4:ios）
     */
    @Column(name = "device_type")
    private String deviceType;

    /**
     * 用户类型（1:出租车司机，2：乘客主，3:约租车司机）
     */
    @Column(name = "user_type")
    private String userType;

    /**
     * 获取ID
     *
     * @return id - ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置ID
     *
     * @param id ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取用户手机号
     *
     * @return phone - 用户手机号
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置用户手机号
     *
     * @param phone 用户手机号
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取极光唯一标识
     *
     * @return channel_id - 极光唯一标识
     */
    public String getChannelId() {
        return channelId;
    }

    /**
     * 设置极光唯一标识
     *
     * @param channelId 极光唯一标识
     */
    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    /**
     * 获取设备类型（3:android, 4:ios）
     *
     * @return device_type - 设备类型（3:android, 4:ios）
     */
    public String getDeviceType() {
        return deviceType;
    }

    /**
     * 设置设备类型（3:android, 4:ios）
     *
     * @param deviceType 设备类型（3:android, 4:ios）
     */
    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    /**
     * 获取用户类型（1:出租车司机，2：乘客主，3:约租车司机）
     *
     * @return user_type - 用户类型（1:出租车司机，2：乘客主，3:约租车司机）
     */
    public String getUserType() {
        return userType;
    }

    /**
     * 设置用户类型（1:出租车司机，2：乘客主，3:约租车司机）
     *
     * @param userType 用户类型（1:出租车司机，2：乘客主，3:约租车司机）
     */
    public void setUserType(String userType) {
        this.userType = userType;
    }
}