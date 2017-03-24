package com.visionet.platform.cooperation.model;

import javax.persistence.*;
import java.util.Date;

@Table(name = "t_car_user")
public class CarUser {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 手机号码
     */
    @Column(name = "PHONE")
    private String phone;

    /**
     * 密码
     */
    @Column(name = "PASSWORD")
    private String password;

    /**
     * 司机类型：0出租车，1约租车
     */
    @Column(name = "BUSINESS_TYPE")
    private Integer businessType;

    /**
     * 类型（0自营，1非自营)
     */
    @Column(name = "TYPE")
    private Integer type;

    /**
     * 头像信息
     */
    @Column(name = "HEAD_PIC")
    private String headPic;

    /**
     * 名称
     */
    @Column(name = "NAME")
    private String name;

    /**
     * 称呼
     */
    @Column(name = "NICK_NAME")
    private String nickName;

    /**
     * 身份证号
     */
    @Column(name = "ID_NUMBER")
    private String idNumber;

    /**
     * 城市ID
     */
    @Column(name = "CITY_ID")
    private Integer cityId;

    /**
     * 城市  
     */
    @Column(name = "CITY")
    private String city;

    /**
     * 公司名称
     */
    @Column(name = "COMPANY")
    private String company;

    /**
     * 车队名称
     */
    @Column(name = "GROUP_NAME")
    private String groupName;

    /**
     * 账户等级 5普通,6银牌,7金牌,8砖石 
     */
    @Column(name = "LEVEL")
    private Integer level;

    /**
     * 综合评价等级
     */
    @Column(name = "GRADE")
    private Double grade;

    /**
     * 账户累计总额
     */
    @Column(name = "TOTAL")
    private Double total;

    /**
     * 账户可用余额
     */
    @Column(name = "BALANCE")
    private Double balance;

    /**
     * 账户累积积分
     */
    @Column(name = "POINTS_SUM")
    private Double pointsSum;

    /**
     * 账户可用积分
     */
    @Column(name = "POINTS_AVAILABLE")
    private Double pointsAvailable;

    /**
     * 账户可用众币
     */
    @Column(name = "VIRTUAL_CURRENCY")
    private Double virtualCurrency;

    /**
     * 状态0无效，1正常，2锁定，3拉黑, 4待激活,5已退款,6停运 7:修改信息已审核
     */
    @Column(name = "STATUS")
    private Integer status;

    /**
     * 车型,0:舒适型,1:商务型 2豪华型
     */
    @Column(name = "CAR_TYPE")
    private Integer carType;

    /**
     * 车牌号码
     */
    @Column(name = "CAR_NUMBER")
    private String carNumber;

    /**
     * 关联主账号
     */
    @Column(name = "MAIN_USER")
    private String mainUser;

    /**
     * 订单总数
     */
    @Column(name = "ORDER_COUNT")
    private Integer orderCount;

    /**
     * 当日订单总数
     */
    @Column(name = "ORDER_COUNT_TODAY")
    private Integer orderCountToday;

    /**
     * 是否认证（0未认证/认证失败，1已认证，2审核中）
     */
    @Column(name = "IS_VALID")
    private Integer isValid;

    /**
     * 注册时间
     */
    @Column(name = "REGISTER_DATE")
    private Date registerDate;

    /**
     * 是否登录，1已登录，0未登录
     */
    @Column(name = "IS_LOGIN")
    private Integer isLogin;

    /**
     * 上次活动时间
     */
    @Column(name = "LAST_LOGIN_DATE")
    private Date lastLoginDate;

    /**
     * 更新时间
     */
    @Column(name = "UPDATE_DATE")
    private Date updateDate;

    /**
     * 更新人
     */
    @Column(name = "UPDATE_BY")
    private Integer updateBy;

    /**
     * 行驶证号
     */
    @Column(name = "XH_LICENSE_NUMBER")
    private String xhLicenseNumber;

    /**
     * 驾驶证号
     */
    @Column(name = "JS_LICENSE_NUMBER")
    private String jsLicenseNumber;

    /**
     * 备用字段1----拉黑次数
     */
    @Column(name = "BAKSTR1")
    private String bakstr1;

    /**
     * 评价统计
     */
    @Column(name = "BAKSTR2")
    private String bakstr2;

    /**
     * 备用字段3-支付宝二维码
     */
    @Column(name = "BAKSTR3")
    private String bakstr3;

    /**
     * 备用字段4-微信支付二维码
     */
    @Column(name = "BAKSTR4")
    private String bakstr4;

    /**
     * 备用字段5
     */
    @Column(name = "BAKSTR5")
    private String bakstr5;

    /**
     * 备用字段6
     */
    @Column(name = "BAKSTR6")
    private String bakstr6;

    /**
     * 备用字段7
     */
    @Column(name = "BAKSTR7")
    private String bakstr7;

    /**
     * 备用字段8
     */
    @Column(name = "BAKSTR8")
    private String bakstr8;

    /**
     * 准营证
     */
    @Column(name = "permission_license_number")
    private String permissionLicenseNumber;

    /**
     * 上次访问消息中心的时间
     */
    @Column(name = "LAST_MESSAGE_DATE")
    private Date lastMessageDate;

    /**
     * 奖励金额
     */
    @Column(name = "Bonus_Money")
    private Double bonusMoney;

    /**
     * 上次访问公告的时间
     */
    @Column(name = "LAST_NOTIC_DATE")
    private Date lastNoticDate;

    /**
     * 可开票额度
     */
    @Column(name = "INVOICE_BALANCE")
    private Double invoiceBalance;

    /**
     * 手机IMEI唯一码
     */
    @Column(name = "IMEI")
    private String imei;

    /**
     * 司机接单状态。0：为接单。1：已接单。2：到达出发地，3：行驶中
     */
    @Column(name = "SERVICE_STATUS")
    private Integer serviceStatus;

    /**
     * 可提现金额
     */
    @Column(name = "cash_money")
    private Double cashMoney;

    /**
     * 待扣款金额
     */
    @Column(name = "wait_pay_money")
    private Double waitPayMoney;

    /**
     * 冻结余额
     */
    @Column(name = "freeze_balance")
    private Double freezeBalance;

    /**
     * 处理中的解冻余额
     */
    @Column(name = "check_freeze_balance")
    private Double checkFreezeBalance;

    /**
     * 冻结奖励金额
     */
    @Column(name = "freeze_bonus_money")
    private Double freezeBonusMoney;

    /**
     * 处理中的解冻奖励金额
     */
    @Column(name = "check_freeze_bonus_money")
    private Double checkFreezeBonusMoney;

    /**
     * 0逻辑删除，1有效
     */
    @Column(name = "DEL_FLAG")
    private Integer delFlag;

    /**
     * 第三方司机Id
     */
    @Column(name = "DRIVER_ID")
    private Integer driverId;

    /**
     * 司机来源(0:默认,1:首汽)
     */
    @Column(name = "DRIVER_SOURCE")
    private Integer driverSource;

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
     * 获取手机号码
     *
     * @return PHONE - 手机号码
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置手机号码
     *
     * @param phone 手机号码
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取密码
     *
     * @return PASSWORD - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取司机类型：0出租车，1约租车
     *
     * @return BUSINESS_TYPE - 司机类型：0出租车，1约租车
     */
    public Integer getBusinessType() {
        return businessType;
    }

    /**
     * 设置司机类型：0出租车，1约租车
     *
     * @param businessType 司机类型：0出租车，1约租车
     */
    public void setBusinessType(Integer businessType) {
        this.businessType = businessType;
    }

    /**
     * 获取类型（0自营，1非自营)
     *
     * @return TYPE - 类型（0自营，1非自营)
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置类型（0自营，1非自营)
     *
     * @param type 类型（0自营，1非自营)
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取头像信息
     *
     * @return HEAD_PIC - 头像信息
     */
    public String getHeadPic() {
        return headPic;
    }

    /**
     * 设置头像信息
     *
     * @param headPic 头像信息
     */
    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }

    /**
     * 获取名称
     *
     * @return NAME - 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名称
     *
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取称呼
     *
     * @return NICK_NAME - 称呼
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * 设置称呼
     *
     * @param nickName 称呼
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * 获取身份证号
     *
     * @return ID_NUMBER - 身份证号
     */
    public String getIdNumber() {
        return idNumber;
    }

    /**
     * 设置身份证号
     *
     * @param idNumber 身份证号
     */
    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
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
     * @return CITY - 城市  
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

    /**
     * 获取公司名称
     *
     * @return COMPANY - 公司名称
     */
    public String getCompany() {
        return company;
    }

    /**
     * 设置公司名称
     *
     * @param company 公司名称
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * 获取车队名称
     *
     * @return GROUP_NAME - 车队名称
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * 设置车队名称
     *
     * @param groupName 车队名称
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    /**
     * 获取账户等级 5普通,6银牌,7金牌,8砖石 
     *
     * @return LEVEL - 账户等级 5普通,6银牌,7金牌,8砖石 
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * 设置账户等级 5普通,6银牌,7金牌,8砖石 
     *
     * @param level 账户等级 5普通,6银牌,7金牌,8砖石 
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * 获取综合评价等级
     *
     * @return GRADE - 综合评价等级
     */
    public Double getGrade() {
        return grade;
    }

    /**
     * 设置综合评价等级
     *
     * @param grade 综合评价等级
     */
    public void setGrade(Double grade) {
        this.grade = grade;
    }

    /**
     * 获取账户累计总额
     *
     * @return TOTAL - 账户累计总额
     */
    public Double getTotal() {
        return total;
    }

    /**
     * 设置账户累计总额
     *
     * @param total 账户累计总额
     */
    public void setTotal(Double total) {
        this.total = total;
    }

    /**
     * 获取账户可用余额
     *
     * @return BALANCE - 账户可用余额
     */
    public Double getBalance() {
        return balance;
    }

    /**
     * 设置账户可用余额
     *
     * @param balance 账户可用余额
     */
    public void setBalance(Double balance) {
        this.balance = balance;
    }

    /**
     * 获取账户累积积分
     *
     * @return POINTS_SUM - 账户累积积分
     */
    public Double getPointsSum() {
        return pointsSum;
    }

    /**
     * 设置账户累积积分
     *
     * @param pointsSum 账户累积积分
     */
    public void setPointsSum(Double pointsSum) {
        this.pointsSum = pointsSum;
    }

    /**
     * 获取账户可用积分
     *
     * @return POINTS_AVAILABLE - 账户可用积分
     */
    public Double getPointsAvailable() {
        return pointsAvailable;
    }

    /**
     * 设置账户可用积分
     *
     * @param pointsAvailable 账户可用积分
     */
    public void setPointsAvailable(Double pointsAvailable) {
        this.pointsAvailable = pointsAvailable;
    }

    /**
     * 获取账户可用众币
     *
     * @return VIRTUAL_CURRENCY - 账户可用众币
     */
    public Double getVirtualCurrency() {
        return virtualCurrency;
    }

    /**
     * 设置账户可用众币
     *
     * @param virtualCurrency 账户可用众币
     */
    public void setVirtualCurrency(Double virtualCurrency) {
        this.virtualCurrency = virtualCurrency;
    }

    /**
     * 获取状态0无效，1正常，2锁定，3拉黑, 4待激活,5已退款,6停运 7:修改信息已审核
     *
     * @return STATUS - 状态0无效，1正常，2锁定，3拉黑, 4待激活,5已退款,6停运 7:修改信息已审核
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态0无效，1正常，2锁定，3拉黑, 4待激活,5已退款,6停运 7:修改信息已审核
     *
     * @param status 状态0无效，1正常，2锁定，3拉黑, 4待激活,5已退款,6停运 7:修改信息已审核
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取车型,0:舒适型,1:商务型 2豪华型
     *
     * @return CAR_TYPE - 车型,0:舒适型,1:商务型 2豪华型
     */
    public Integer getCarType() {
        return carType;
    }

    /**
     * 设置车型,0:舒适型,1:商务型 2豪华型
     *
     * @param carType 车型,0:舒适型,1:商务型 2豪华型
     */
    public void setCarType(Integer carType) {
        this.carType = carType;
    }

    /**
     * 获取车牌号码
     *
     * @return CAR_NUMBER - 车牌号码
     */
    public String getCarNumber() {
        return carNumber;
    }

    /**
     * 设置车牌号码
     *
     * @param carNumber 车牌号码
     */
    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    /**
     * 获取关联主账号
     *
     * @return MAIN_USER - 关联主账号
     */
    public String getMainUser() {
        return mainUser;
    }

    /**
     * 设置关联主账号
     *
     * @param mainUser 关联主账号
     */
    public void setMainUser(String mainUser) {
        this.mainUser = mainUser;
    }

    /**
     * 获取订单总数
     *
     * @return ORDER_COUNT - 订单总数
     */
    public Integer getOrderCount() {
        return orderCount;
    }

    /**
     * 设置订单总数
     *
     * @param orderCount 订单总数
     */
    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }

    /**
     * 获取当日订单总数
     *
     * @return ORDER_COUNT_TODAY - 当日订单总数
     */
    public Integer getOrderCountToday() {
        return orderCountToday;
    }

    /**
     * 设置当日订单总数
     *
     * @param orderCountToday 当日订单总数
     */
    public void setOrderCountToday(Integer orderCountToday) {
        this.orderCountToday = orderCountToday;
    }

    /**
     * 获取是否认证（0未认证/认证失败，1已认证，2审核中）
     *
     * @return IS_VALID - 是否认证（0未认证/认证失败，1已认证，2审核中）
     */
    public Integer getIsValid() {
        return isValid;
    }

    /**
     * 设置是否认证（0未认证/认证失败，1已认证，2审核中）
     *
     * @param isValid 是否认证（0未认证/认证失败，1已认证，2审核中）
     */
    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    /**
     * 获取注册时间
     *
     * @return REGISTER_DATE - 注册时间
     */
    public Date getRegisterDate() {
        return registerDate;
    }

    /**
     * 设置注册时间
     *
     * @param registerDate 注册时间
     */
    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    /**
     * 获取是否登录，1已登录，0未登录
     *
     * @return IS_LOGIN - 是否登录，1已登录，0未登录
     */
    public Integer getIsLogin() {
        return isLogin;
    }

    /**
     * 设置是否登录，1已登录，0未登录
     *
     * @param isLogin 是否登录，1已登录，0未登录
     */
    public void setIsLogin(Integer isLogin) {
        this.isLogin = isLogin;
    }

    /**
     * 获取上次活动时间
     *
     * @return LAST_LOGIN_DATE - 上次活动时间
     */
    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    /**
     * 设置上次活动时间
     *
     * @param lastLoginDate 上次活动时间
     */
    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    /**
     * 获取更新时间
     *
     * @return UPDATE_DATE - 更新时间
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * 设置更新时间
     *
     * @param updateDate 更新时间
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * 获取更新人
     *
     * @return UPDATE_BY - 更新人
     */
    public Integer getUpdateBy() {
        return updateBy;
    }

    /**
     * 设置更新人
     *
     * @param updateBy 更新人
     */
    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    /**
     * 获取行驶证号
     *
     * @return XH_LICENSE_NUMBER - 行驶证号
     */
    public String getXhLicenseNumber() {
        return xhLicenseNumber;
    }

    /**
     * 设置行驶证号
     *
     * @param xhLicenseNumber 行驶证号
     */
    public void setXhLicenseNumber(String xhLicenseNumber) {
        this.xhLicenseNumber = xhLicenseNumber;
    }

    /**
     * 获取驾驶证号
     *
     * @return JS_LICENSE_NUMBER - 驾驶证号
     */
    public String getJsLicenseNumber() {
        return jsLicenseNumber;
    }

    /**
     * 设置驾驶证号
     *
     * @param jsLicenseNumber 驾驶证号
     */
    public void setJsLicenseNumber(String jsLicenseNumber) {
        this.jsLicenseNumber = jsLicenseNumber;
    }

    /**
     * 获取备用字段1----拉黑次数
     *
     * @return BAKSTR1 - 备用字段1----拉黑次数
     */
    public String getBakstr1() {
        return bakstr1;
    }

    /**
     * 设置备用字段1----拉黑次数
     *
     * @param bakstr1 备用字段1----拉黑次数
     */
    public void setBakstr1(String bakstr1) {
        this.bakstr1 = bakstr1;
    }

    /**
     * 获取评价统计
     *
     * @return BAKSTR2 - 评价统计
     */
    public String getBakstr2() {
        return bakstr2;
    }

    /**
     * 设置评价统计
     *
     * @param bakstr2 评价统计
     */
    public void setBakstr2(String bakstr2) {
        this.bakstr2 = bakstr2;
    }

    /**
     * 获取备用字段3-支付宝二维码
     *
     * @return BAKSTR3 - 备用字段3-支付宝二维码
     */
    public String getBakstr3() {
        return bakstr3;
    }

    /**
     * 设置备用字段3-支付宝二维码
     *
     * @param bakstr3 备用字段3-支付宝二维码
     */
    public void setBakstr3(String bakstr3) {
        this.bakstr3 = bakstr3;
    }

    /**
     * 获取备用字段4-微信支付二维码
     *
     * @return BAKSTR4 - 备用字段4-微信支付二维码
     */
    public String getBakstr4() {
        return bakstr4;
    }

    /**
     * 设置备用字段4-微信支付二维码
     *
     * @param bakstr4 备用字段4-微信支付二维码
     */
    public void setBakstr4(String bakstr4) {
        this.bakstr4 = bakstr4;
    }

    /**
     * 获取备用字段5
     *
     * @return BAKSTR5 - 备用字段5
     */
    public String getBakstr5() {
        return bakstr5;
    }

    /**
     * 设置备用字段5
     *
     * @param bakstr5 备用字段5
     */
    public void setBakstr5(String bakstr5) {
        this.bakstr5 = bakstr5;
    }

    /**
     * 获取备用字段6
     *
     * @return BAKSTR6 - 备用字段6
     */
    public String getBakstr6() {
        return bakstr6;
    }

    /**
     * 设置备用字段6
     *
     * @param bakstr6 备用字段6
     */
    public void setBakstr6(String bakstr6) {
        this.bakstr6 = bakstr6;
    }

    /**
     * 获取备用字段7
     *
     * @return BAKSTR7 - 备用字段7
     */
    public String getBakstr7() {
        return bakstr7;
    }

    /**
     * 设置备用字段7
     *
     * @param bakstr7 备用字段7
     */
    public void setBakstr7(String bakstr7) {
        this.bakstr7 = bakstr7;
    }

    /**
     * 获取备用字段8
     *
     * @return BAKSTR8 - 备用字段8
     */
    public String getBakstr8() {
        return bakstr8;
    }

    /**
     * 设置备用字段8
     *
     * @param bakstr8 备用字段8
     */
    public void setBakstr8(String bakstr8) {
        this.bakstr8 = bakstr8;
    }

    /**
     * 获取准营证
     *
     * @return permission_license_number - 准营证
     */
    public String getPermissionLicenseNumber() {
        return permissionLicenseNumber;
    }

    /**
     * 设置准营证
     *
     * @param permissionLicenseNumber 准营证
     */
    public void setPermissionLicenseNumber(String permissionLicenseNumber) {
        this.permissionLicenseNumber = permissionLicenseNumber;
    }

    /**
     * 获取上次访问消息中心的时间
     *
     * @return LAST_MESSAGE_DATE - 上次访问消息中心的时间
     */
    public Date getLastMessageDate() {
        return lastMessageDate;
    }

    /**
     * 设置上次访问消息中心的时间
     *
     * @param lastMessageDate 上次访问消息中心的时间
     */
    public void setLastMessageDate(Date lastMessageDate) {
        this.lastMessageDate = lastMessageDate;
    }

    /**
     * 获取奖励金额
     *
     * @return Bonus_Money - 奖励金额
     */
    public Double getBonusMoney() {
        return bonusMoney;
    }

    /**
     * 设置奖励金额
     *
     * @param bonusMoney 奖励金额
     */
    public void setBonusMoney(Double bonusMoney) {
        this.bonusMoney = bonusMoney;
    }

    /**
     * 获取上次访问公告的时间
     *
     * @return LAST_NOTIC_DATE - 上次访问公告的时间
     */
    public Date getLastNoticDate() {
        return lastNoticDate;
    }

    /**
     * 设置上次访问公告的时间
     *
     * @param lastNoticDate 上次访问公告的时间
     */
    public void setLastNoticDate(Date lastNoticDate) {
        this.lastNoticDate = lastNoticDate;
    }

    /**
     * 获取可开票额度
     *
     * @return INVOICE_BALANCE - 可开票额度
     */
    public Double getInvoiceBalance() {
        return invoiceBalance;
    }

    /**
     * 设置可开票额度
     *
     * @param invoiceBalance 可开票额度
     */
    public void setInvoiceBalance(Double invoiceBalance) {
        this.invoiceBalance = invoiceBalance;
    }

    /**
     * 获取手机IMEI唯一码
     *
     * @return IMEI - 手机IMEI唯一码
     */
    public String getImei() {
        return imei;
    }

    /**
     * 设置手机IMEI唯一码
     *
     * @param imei 手机IMEI唯一码
     */
    public void setImei(String imei) {
        this.imei = imei;
    }

    /**
     * 获取司机接单状态。0：为接单。1：已接单。2：到达出发地，3：行驶中
     *
     * @return SERVICE_STATUS - 司机接单状态。0：为接单。1：已接单。2：到达出发地，3：行驶中
     */
    public Integer getServiceStatus() {
        return serviceStatus;
    }

    /**
     * 设置司机接单状态。0：为接单。1：已接单。2：到达出发地，3：行驶中
     *
     * @param serviceStatus 司机接单状态。0：为接单。1：已接单。2：到达出发地，3：行驶中
     */
    public void setServiceStatus(Integer serviceStatus) {
        this.serviceStatus = serviceStatus;
    }

    /**
     * 获取可提现金额
     *
     * @return cash_money - 可提现金额
     */
    public Double getCashMoney() {
        return cashMoney;
    }

    /**
     * 设置可提现金额
     *
     * @param cashMoney 可提现金额
     */
    public void setCashMoney(Double cashMoney) {
        this.cashMoney = cashMoney;
    }

    /**
     * 获取待扣款金额
     *
     * @return wait_pay_money - 待扣款金额
     */
    public Double getWaitPayMoney() {
        return waitPayMoney;
    }

    /**
     * 设置待扣款金额
     *
     * @param waitPayMoney 待扣款金额
     */
    public void setWaitPayMoney(Double waitPayMoney) {
        this.waitPayMoney = waitPayMoney;
    }

    /**
     * 获取冻结余额
     *
     * @return freeze_balance - 冻结余额
     */
    public Double getFreezeBalance() {
        return freezeBalance;
    }

    /**
     * 设置冻结余额
     *
     * @param freezeBalance 冻结余额
     */
    public void setFreezeBalance(Double freezeBalance) {
        this.freezeBalance = freezeBalance;
    }

    /**
     * 获取处理中的解冻余额
     *
     * @return check_freeze_balance - 处理中的解冻余额
     */
    public Double getCheckFreezeBalance() {
        return checkFreezeBalance;
    }

    /**
     * 设置处理中的解冻余额
     *
     * @param checkFreezeBalance 处理中的解冻余额
     */
    public void setCheckFreezeBalance(Double checkFreezeBalance) {
        this.checkFreezeBalance = checkFreezeBalance;
    }

    /**
     * 获取冻结奖励金额
     *
     * @return freeze_bonus_money - 冻结奖励金额
     */
    public Double getFreezeBonusMoney() {
        return freezeBonusMoney;
    }

    /**
     * 设置冻结奖励金额
     *
     * @param freezeBonusMoney 冻结奖励金额
     */
    public void setFreezeBonusMoney(Double freezeBonusMoney) {
        this.freezeBonusMoney = freezeBonusMoney;
    }

    /**
     * 获取处理中的解冻奖励金额
     *
     * @return check_freeze_bonus_money - 处理中的解冻奖励金额
     */
    public Double getCheckFreezeBonusMoney() {
        return checkFreezeBonusMoney;
    }

    /**
     * 设置处理中的解冻奖励金额
     *
     * @param checkFreezeBonusMoney 处理中的解冻奖励金额
     */
    public void setCheckFreezeBonusMoney(Double checkFreezeBonusMoney) {
        this.checkFreezeBonusMoney = checkFreezeBonusMoney;
    }

    /**
     * 获取0逻辑删除，1有效
     *
     * @return DEL_FLAG - 0逻辑删除，1有效
     */
    public Integer getDelFlag() {
        return delFlag;
    }

    /**
     * 设置0逻辑删除，1有效
     *
     * @param delFlag 0逻辑删除，1有效
     */
    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    /**
     * 获取第三方司机Id
     *
     * @return DRIVER_ID - 第三方司机Id
     */
    public Integer getDriverId() {
        return driverId;
    }

    /**
     * 设置第三方司机Id
     *
     * @param driverId 第三方司机Id
     */
    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    /**
     * 获取司机来源(0:默认,1:首汽)
     *
     * @return DRIVER_SOURCE - 司机来源(0:默认,1:首汽)
     */
    public Integer getDriverSource() {
        return driverSource;
    }

    /**
     * 设置司机来源(0:默认,1:首汽)
     *
     * @param driverSource 司机来源(0:默认,1:首汽)
     */
    public void setDriverSource(Integer driverSource) {
        this.driverSource = driverSource;
    }
}