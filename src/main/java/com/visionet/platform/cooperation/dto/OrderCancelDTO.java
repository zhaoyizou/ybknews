package com.visionet.platform.cooperation.dto;

import java.util.List;

public class OrderCancelDTO {

    private String phone;

    private String description;// 取消原因

    private List<String> orderIds;

    private Integer type;// 是否强制取消 0否，1是

    private Integer sourceType;// 操作人0：乘客，1：司机，2：订单服务器 3：后台 4：企业

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getOrderIds() {
        return orderIds;
    }

    public void setOrderIds(List<String> orderIds) {
        this.orderIds = orderIds;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getSourceType() {
        return sourceType;
    }

    public void setSourceType(Integer sourceType) {
        this.sourceType = sourceType;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
