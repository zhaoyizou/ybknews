package com.visionet.platform.cooperation.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "t_customer")
public class Customer {
    /**
     * 手机号码
     */
    @Id
    @Column(name = "PHONE")
    private String phone;

    /**
     * 密码
     */
    @Column(name = "PASSWORD")
    private String password;

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
     * 身份证ID
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
     * 等级 1普通，2银牌，3金牌，4砖石
     */
    @Column(name = "LEVEL")
    private Integer level;

    /**
     * 评价等级
     */
    @Column(name = "GRADE")
    private Integer grade;

    /**
     * 总额
     */
    @Column(name = "TOTAL")
    private Double total;

    /**
     * 可用余额
     */
    @Column(name = "BALANCE")
    private Double balance;

    /**
     * 虚拟币总额
     */
    @Column(name = "DZ_YUEZHUBI_SUM")
    private Double dzYuezhubiSum;

    /**
     * 可用虚拟币
     */
    @Column(name = "DZ_YUEZHUBI_AVAILABLE")
    private Double dzYuezhubiAvailable;

    /**
     * 总积分
     */
    @Column(name = "POINTS_SUM")
    private Double pointsSum;

    /**
     * 可用积分
     */
    @Column(name = "POINTS_AVAILABLE")
    private Double pointsAvailable;

    /**
     * 订单总数
     */
    @Column(name = "ORDER_COUNT")
    private Integer orderCount;

    /**
     * 放空次数
     */
    @Column(name = "FAILED_ORDER_COUNT")
    private Integer failedOrderCount;

    /**
     * 注册时间
     */
    @Column(name = "CREATE_DATE")
    private Date createDate;

    /**
     * 上次活动时间
     */
    @Column(name = "LAST_LOGIN_DATE")
    private Date lastLoginDate;

    /**
     * 状态0无效，1正常，2锁定，3拉黑 , 4待激活
     */
    @Column(name = "STATUS")
    private Integer status;

    /**
     * 是否认证（0非认证，1个人认证完成，2认证失败，3企业认证完成，4正在认证
     */
    @Column(name = "IS_VALID")
    private Integer isValid;

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
     * 0逻辑删除，1有效
     */
    @Column(name = "DEL_FLAG")
    private Integer delFlag;

    /**
     * 获评统计
     */
    @Column(name = "BAKSTR1")
    private String bakstr1;

    /**
     * 生日
     */
    @Column(name = "BAKSTR2")
    private String bakstr2;

    /**
     * 行业
     */
    @Column(name = "BAKSTR3")
    private String bakstr3;

    /**
     * 备用字段4
     */
    @Column(name = "BAKSTR4")
    private String bakstr4;

    /**
     * 备用字段5
     */
    @Column(name = "BAKSTR5")
    private String bakstr5;

    /**
     * 1已在微信端绑定，0未在微信端绑定
     */
    @Column(name = "IS_WECHAT")
    private Integer isWechat;

    /**
     * 1已在APP端注册，0未在APP端注册
     */
    @Column(name = "IS_APP")
    private Integer isApp;

    /**
     * 1已在96811使用，0未在96811使用
     */
    @Column(name = "IS_CALLCENTER")
    private Integer isCallcenter;

    /**
     * 微信openId
     */
    @Column(name = "OPENID")
    private String openid;

    /**
     * 上次访问消息中心的时间
     */
    @Column(name = "LAST_MESSAGE_DATE")
    private Date lastMessageDate;

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
     * 是否登录，1已登录，0未登录
     */
    @Column(name = "IS_LOGIN")
    private Integer isLogin;

    /**
     * 手机IMEI唯一码
     */
    @Column(name = "IMEI")
    private String imei;

    /**
     * 上次访问优惠券的时间
     */
    @Column(name = "LAST_COUPONS_DATE")
    private Date lastCouponsDate;

    /**
     * 乘客注册来源：0普通注册，1乘客推荐，2司机推荐，3微信，4后台注册
     */
    @Column(name = "SOURCE")
    private String source;

    /**
     * 上次访问接送机页面的时间
     */
    @Column(name = "LAST_FLIGHT_DATE")
    private Date lastFlightDate;

    /**
     * 上次访问接送机优惠券列表的时间
     */
    @Column(name = "LAST_FLIGHT_LIST_DATE")
    private Date lastFlightListDate;

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
     * 获取身份证ID
     *
     * @return ID_NUMBER - 身份证ID
     */
    public String getIdNumber() {
        return idNumber;
    }

    /**
     * 设置身份证ID
     *
     * @param idNumber 身份证ID
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
     * 获取等级 1普通，2银牌，3金牌，4砖石
     *
     * @return LEVEL - 等级 1普通，2银牌，3金牌，4砖石
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * 设置等级 1普通，2银牌，3金牌，4砖石
     *
     * @param level 等级 1普通，2银牌，3金牌，4砖石
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * 获取评价等级
     *
     * @return GRADE - 评价等级
     */
    public Integer getGrade() {
        return grade;
    }

    /**
     * 设置评价等级
     *
     * @param grade 评价等级
     */
    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    /**
     * 获取总额
     *
     * @return TOTAL - 总额
     */
    public Double getTotal() {
        return total;
    }

    /**
     * 设置总额
     *
     * @param total 总额
     */
    public void setTotal(Double total) {
        this.total = total;
    }

    /**
     * 获取可用余额
     *
     * @return BALANCE - 可用余额
     */
    public Double getBalance() {
        return balance;
    }

    /**
     * 设置可用余额
     *
     * @param balance 可用余额
     */
    public void setBalance(Double balance) {
        this.balance = balance;
    }

    /**
     * 获取虚拟币总额
     *
     * @return DZ_YUEZHUBI_SUM - 虚拟币总额
     */
    public Double getDzYuezhubiSum() {
        return dzYuezhubiSum;
    }

    /**
     * 设置虚拟币总额
     *
     * @param dzYuezhubiSum 虚拟币总额
     */
    public void setDzYuezhubiSum(Double dzYuezhubiSum) {
        this.dzYuezhubiSum = dzYuezhubiSum;
    }

    /**
     * 获取可用虚拟币
     *
     * @return DZ_YUEZHUBI_AVAILABLE - 可用虚拟币
     */
    public Double getDzYuezhubiAvailable() {
        return dzYuezhubiAvailable;
    }

    /**
     * 设置可用虚拟币
     *
     * @param dzYuezhubiAvailable 可用虚拟币
     */
    public void setDzYuezhubiAvailable(Double dzYuezhubiAvailable) {
        this.dzYuezhubiAvailable = dzYuezhubiAvailable;
    }

    /**
     * 获取总积分
     *
     * @return POINTS_SUM - 总积分
     */
    public Double getPointsSum() {
        return pointsSum;
    }

    /**
     * 设置总积分
     *
     * @param pointsSum 总积分
     */
    public void setPointsSum(Double pointsSum) {
        this.pointsSum = pointsSum;
    }

    /**
     * 获取可用积分
     *
     * @return POINTS_AVAILABLE - 可用积分
     */
    public Double getPointsAvailable() {
        return pointsAvailable;
    }

    /**
     * 设置可用积分
     *
     * @param pointsAvailable 可用积分
     */
    public void setPointsAvailable(Double pointsAvailable) {
        this.pointsAvailable = pointsAvailable;
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
     * 获取放空次数
     *
     * @return FAILED_ORDER_COUNT - 放空次数
     */
    public Integer getFailedOrderCount() {
        return failedOrderCount;
    }

    /**
     * 设置放空次数
     *
     * @param failedOrderCount 放空次数
     */
    public void setFailedOrderCount(Integer failedOrderCount) {
        this.failedOrderCount = failedOrderCount;
    }

    /**
     * 获取注册时间
     *
     * @return CREATE_DATE - 注册时间
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置注册时间
     *
     * @param createDate 注册时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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
     * 获取状态0无效，1正常，2锁定，3拉黑 , 4待激活
     *
     * @return STATUS - 状态0无效，1正常，2锁定，3拉黑 , 4待激活
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态0无效，1正常，2锁定，3拉黑 , 4待激活
     *
     * @param status 状态0无效，1正常，2锁定，3拉黑 , 4待激活
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取是否认证（0非认证，1个人认证完成，2认证失败，3企业认证完成，4正在认证
     *
     * @return IS_VALID - 是否认证（0非认证，1个人认证完成，2认证失败，3企业认证完成，4正在认证
     */
    public Integer getIsValid() {
        return isValid;
    }

    /**
     * 设置是否认证（0非认证，1个人认证完成，2认证失败，3企业认证完成，4正在认证
     *
     * @param isValid 是否认证（0非认证，1个人认证完成，2认证失败，3企业认证完成，4正在认证
     */
    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
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
     * 获取获评统计
     *
     * @return BAKSTR1 - 获评统计
     */
    public String getBakstr1() {
        return bakstr1;
    }

    /**
     * 设置获评统计
     *
     * @param bakstr1 获评统计
     */
    public void setBakstr1(String bakstr1) {
        this.bakstr1 = bakstr1;
    }

    /**
     * 获取生日
     *
     * @return BAKSTR2 - 生日
     */
    public String getBakstr2() {
        return bakstr2;
    }

    /**
     * 设置生日
     *
     * @param bakstr2 生日
     */
    public void setBakstr2(String bakstr2) {
        this.bakstr2 = bakstr2;
    }

    /**
     * 获取行业
     *
     * @return BAKSTR3 - 行业
     */
    public String getBakstr3() {
        return bakstr3;
    }

    /**
     * 设置行业
     *
     * @param bakstr3 行业
     */
    public void setBakstr3(String bakstr3) {
        this.bakstr3 = bakstr3;
    }

    /**
     * 获取备用字段4
     *
     * @return BAKSTR4 - 备用字段4
     */
    public String getBakstr4() {
        return bakstr4;
    }

    /**
     * 设置备用字段4
     *
     * @param bakstr4 备用字段4
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
     * 获取1已在微信端绑定，0未在微信端绑定
     *
     * @return IS_WECHAT - 1已在微信端绑定，0未在微信端绑定
     */
    public Integer getIsWechat() {
        return isWechat;
    }

    /**
     * 设置1已在微信端绑定，0未在微信端绑定
     *
     * @param isWechat 1已在微信端绑定，0未在微信端绑定
     */
    public void setIsWechat(Integer isWechat) {
        this.isWechat = isWechat;
    }

    /**
     * 获取1已在APP端注册，0未在APP端注册
     *
     * @return IS_APP - 1已在APP端注册，0未在APP端注册
     */
    public Integer getIsApp() {
        return isApp;
    }

    /**
     * 设置1已在APP端注册，0未在APP端注册
     *
     * @param isApp 1已在APP端注册，0未在APP端注册
     */
    public void setIsApp(Integer isApp) {
        this.isApp = isApp;
    }

    /**
     * 获取1已在96811使用，0未在96811使用
     *
     * @return IS_CALLCENTER - 1已在96811使用，0未在96811使用
     */
    public Integer getIsCallcenter() {
        return isCallcenter;
    }

    /**
     * 设置1已在96811使用，0未在96811使用
     *
     * @param isCallcenter 1已在96811使用，0未在96811使用
     */
    public void setIsCallcenter(Integer isCallcenter) {
        this.isCallcenter = isCallcenter;
    }

    /**
     * 获取微信openId
     *
     * @return OPENID - 微信openId
     */
    public String getOpenid() {
        return openid;
    }

    /**
     * 设置微信openId
     *
     * @param openid 微信openId
     */
    public void setOpenid(String openid) {
        this.openid = openid;
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
     * 获取上次访问优惠券的时间
     *
     * @return LAST_COUPONS_DATE - 上次访问优惠券的时间
     */
    public Date getLastCouponsDate() {
        return lastCouponsDate;
    }

    /**
     * 设置上次访问优惠券的时间
     *
     * @param lastCouponsDate 上次访问优惠券的时间
     */
    public void setLastCouponsDate(Date lastCouponsDate) {
        this.lastCouponsDate = lastCouponsDate;
    }

    /**
     * 获取乘客注册来源：0普通注册，1乘客推荐，2司机推荐，3微信，4后台注册
     *
     * @return SOURCE - 乘客注册来源：0普通注册，1乘客推荐，2司机推荐，3微信，4后台注册
     */
    public String getSource() {
        return source;
    }

    /**
     * 设置乘客注册来源：0普通注册，1乘客推荐，2司机推荐，3微信，4后台注册
     *
     * @param source 乘客注册来源：0普通注册，1乘客推荐，2司机推荐，3微信，4后台注册
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * 获取上次访问接送机页面的时间
     *
     * @return LAST_FLIGHT_DATE - 上次访问接送机页面的时间
     */
    public Date getLastFlightDate() {
        return lastFlightDate;
    }

    /**
     * 设置上次访问接送机页面的时间
     *
     * @param lastFlightDate 上次访问接送机页面的时间
     */
    public void setLastFlightDate(Date lastFlightDate) {
        this.lastFlightDate = lastFlightDate;
    }

    /**
     * 获取上次访问接送机优惠券列表的时间
     *
     * @return LAST_FLIGHT_LIST_DATE - 上次访问接送机优惠券列表的时间
     */
    public Date getLastFlightListDate() {
        return lastFlightListDate;
    }

    /**
     * 设置上次访问接送机优惠券列表的时间
     *
     * @param lastFlightListDate 上次访问接送机优惠券列表的时间
     */
    public void setLastFlightListDate(Date lastFlightListDate) {
        this.lastFlightListDate = lastFlightListDate;
    }
}