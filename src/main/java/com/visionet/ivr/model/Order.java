package com.visionet.ivr.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_order")
public class Order {
    /**
     * 订单号
     */
    @Id
    @Column(name = "ORDER_ID")
    private String orderId;

    /**
     * 司机来源（0：大众出行、1：首汽约车）
     */
    @Column(name = "CAR_USER_CHANNEL")
    private Integer carUserChannel;

    /**
     * 车主ID
     */
    @Column(name = "CAR_ID")
    private Integer carId;

    /**
     * 业务类型(0：出租车，1约租车)
     */
    @Column(name = "BUSINESS_TYPE")
    private Integer businessType;

    /**
     * 订单类型(0.app用车，1.allcenter用车，2.app预约，3.callcenter预约，4.协议价，5.接送机，6.首汽
7:酒店实时，8：酒店预约，9企业实时，10企业预约)
     */
    @Column(name = "ORDER_TYPE")
    private Integer orderType;

    /**
     * 订单来源：0 android；1 IOS；2 wechat；3 callcenter；4 首汽 ，5：酒店，6：企业
     */
    @Column(name = "ORDER_SOURCE")
    private Integer orderSource;

    /**
     * 车型（0舒适型，1商务型，2豪华型,3经济型）
     */
    @Column(name = "CAR_TYPE")
    private String carType;

    /**
     * 数量（默认1）
     */
    @Column(name = "CAR_NUMBER")
    private Integer carNumber;

    /**
     * 起点
     */
    @Column(name = "START_PLACE")
    private String startPlace;

    /**
     * 起点GPS信息
     */
    @Column(name = "START_GPS")
    private String startGps;

    /**
     * 终点GPS信息（存储JSON）
     */
    @Column(name = "END_GPS")
    private String endGps;

    /**
     * 预计公里数 KM 小数后两位
     */
    @Column(name = "EXPECTED_KM")
    private Float expectedKm;

    /**
     * 实际公里数 KM  小数后两位
     */
    @Column(name = "ACTUAL_KM")
    private Float actualKm;

    /**
     * 0 非指派单 1指派单
     */
    @Column(name = "ALLOCATE_ORDER")
    private Integer allocateOrder;

    /**
     * 订单总价格
     */
    @Column(name = "TOTAL_PRICE")
    private Float totalPrice;

    /**
     * 预计车费价格  小数后两位
     */
    @Column(name = "EXPECTED_PRICE")
    private Float expectedPrice;

    /**
     * 实际车费价格  小数后两位,用户获得车费(不含平台费)
     */
    @Column(name = "ACTUAL_PRICE")
    private Float actualPrice;

    /**
     * 低俗行驶时间
     */
    @Column(name = "LOW_SPEED_TIMES")
    private Double lowSpeedTimes;

    /**
     * 其他费用1 ---优惠劵支付金额
     */
    @Column(name = "OTHER_PRICE")
    private Float otherPrice;

    /**
     * 费用名称1 ---搬运费
     */
    @Column(name = "OTHER_PRICE_DESCRIPTION")
    private String otherPriceDescription;

    /**
     * 打印发票金额
     */
    @Column(name = "OTHER_PRICE2")
    private Float otherPrice2;

    /**
     * 费用名称2
     */
    @Column(name = "OTHER_PRICE_DESCRIPTION2")
    private String otherPriceDescription2;

    /**
     * 大众平台获得金额
     */
    @Column(name = "OTHER_PRICE3")
    private Float otherPrice3;

    /**
     * 费用名称3
     */
    @Column(name = "OTHER_PRICE_DESCRIPTION3")
    private String otherPriceDescription3;

    /**
     * 计价器价格
     */
    @Column(name = "OTHER_PRICE4")
    private Float otherPrice4;

    /**
     * 费用名称4
     */
    @Column(name = "OTHER_PRICE_DESCRIPTION4")
    private String otherPriceDescription4;

    /**
     * 乘客手机号
     */
    @Column(name = "CUSTOMER_PHONE")
    private String customerPhone;

    /**
     * 乘客名称
     */
    @Column(name = "CUSTOMER_NAME")
    private String customerName;

    /**
     * 手机号码
     */
    @Column(name = "CAR_USER_PHONE")
    private String carUserPhone;

    /**
     * 车主名称
     */
    @Column(name = "CAR_USER_NAME")
    private String carUserName;

    /**
     * 叫车时间
     */
    @Column(name = "CALL_DATE")
    private Date callDate;

    /**
     * 预约时间
     */
    @Column(name = "BOOK_DATE")
    private Date bookDate;

    /**
     * 订单开始时间
     */
    @Column(name = "ORDER_START_DATE")
    private Date orderStartDate;

    /**
     * 运输开始时间
     */
    @Column(name = "TRANPORT_START_DATE")
    private Date tranportStartDate;

    /**
     * 运输结束时间
     */
    @Column(name = "TRANPORT_END_DATE")
    private Date tranportEndDate;

    /**
     * 订单完成时间
     */
    @Column(name = "FINISH_DATE")
    private Date finishDate;

    /**
     * 货主获评
     */
    @Column(name = "CUSTOMER_GRADE")
    private Integer customerGrade;

    /**
     * 车主获评
     */
    @Column(name = "CAR_USER_GRADE")
    private Integer carUserGrade;

    /**
     * 众币奖励或消耗(+100/-100)
     */
    @Column(name = "VIRTUAL")
    private Float virtual;

    /**
     * 结算类型(0,大众内部，1加盟的，2其他)
     */
    @Column(name = "SETTLEMENT_TYPE")
    private Integer settlementType;

    /**
     * 回拨电话
     */
    @Column(name = "CALLBACK_PHONE")
    private String callbackPhone;

    /**
     * 后台接线员id
     */
    @Column(name = "CREATER")
    private Integer creater;

    /**
     * 状态0创建，1已接单，2已完成，3取消，4放空，5无供，6超时，7强制派单中,8待支付，9到达目的，未支付, 10取消(车主取消),11开始计价,12计价完成,13强派拒绝,14司机到达出发地,15出租车待支付
     */
    @Column(name = "STATUS")
    private Integer status;

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
     * 是否开票：0 未开票  ; 1 自动开票 ； 2手动开票
     */
    @Column(name = "IS_INVOICE")
    private Integer isInvoice;

    /**
     * 0逻辑删除，1有效
     */
    @Column(name = "DEL_FLAG")
    private Integer delFlag;

    /**
     * 备用字段1--取消订单描述 
     */
    @Column(name = "BAKSTR1")
    private String bakstr1;

    /**
     * 订单备注
     */
    @Column(name = "BAKSTR2")
    private String bakstr2;

    /**
     * 备用字段3--0:未见乘客,1:车辆故障,2:交通事故3:强制拒绝
     */
    @Column(name = "BAKSTR3")
    private String bakstr3;

    /**
     * 备用字段4--车主获评描述
     */
    @Column(name = "BAKSTR4")
    private String bakstr4;

    /**
     * 备用字段5--打赏的钱
     */
    @Column(name = "BAKSTR5")
    private String bakstr5;

    /**
     * 城市ID
     */
    @Column(name = "CITY_ID")
    private Integer cityId;

    /**
     * 下单的城市
     */
    @Column(name = "CITY")
    private String city;

    /**
     * 取消类型 :0货主取消;1车主取消
     */
    @Column(name = "DEL_TYPE")
    private Integer delType;

    /**
     * 加(减)价费用
     */
    @Column(name = "OTHER_PRICE5")
    private Float otherPrice5;

    /**
     * 加价倍率
     */
    @Column(name = "OTHER_PRICE_DESCRIPTION5")
    private String otherPriceDescription5;

    /**
     * 路桥费
     */
    @Column(name = "TOLL_FEE")
    private Double tollFee;

    /**
     * 下单加价
     */
    @Column(name = "INCREASE_PRICE")
    private Double increasePrice;

    /**
     * 查看打印信息接口时，是否修改乘客可开票余额,0：未修改，1：已修改
     */
    @Column(name = "UPDATE_INVOICE")
    private Integer updateInvoice;

    /**
     * 加价状态（0未加价，1扣加价，2还加价）
     */
    @Column(name = "INCREASE_TYPE")
    private Integer increaseType;

    /**
     * 订单渠道id
     */
    @Column(name = "CANAL_ID")
    private Integer canalId;

    /**
     * 渠道金额
     */
    @Column(name = "CANAL_MONEY")
    private Double canalMoney;

    /**
     * 监控席操作状态(0：未处理，1未确认，2处理中，3已处理，4已确认)
     */
    @Column(name = "CHECK_STATUS")
    private Integer checkStatus;

    /**
     * 最后操作时间(监控席)
     */
    @Column(name = "LAST_TIME")
    private Date lastTime;

    /**
     * 该订单当前操作人ID（监控席）
     */
    @Column(name = "NOW_CHECK_BY")
    private Integer nowCheckBy;

    /**
     * 航班号
     */
    @Column(name = "FLIGHT_NO")
    private String flightNo;

    /**
     * 航班日期 yyyy-MM-dd
     */
    @Column(name = "FLIGHT_DATE")
    private Date flightDate;

    /**
     * 接送机（0.接机，1.送机）
     */
    @Column(name = "FLIGHT_SHUTTLE")
    private Integer flightShuttle;

    /**
     * 终点（存储JSON）
     */
    @Column(name = "END_PLACE")
    private String endPlace;

    /**
     * 获取订单号
     *
     * @return ORDER_ID - 订单号
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * 设置订单号
     *
     * @param orderId 订单号
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取司机来源（0：大众出行、1：首汽约车）
     *
     * @return CAR_USER_CHANNEL - 司机来源（0：大众出行、1：首汽约车）
     */
    public Integer getCarUserChannel() {
        return carUserChannel;
    }

    /**
     * 设置司机来源（0：大众出行、1：首汽约车）
     *
     * @param carUserChannel 司机来源（0：大众出行、1：首汽约车）
     */
    public void setCarUserChannel(Integer carUserChannel) {
        this.carUserChannel = carUserChannel;
    }

    /**
     * 获取车主ID
     *
     * @return CAR_ID - 车主ID
     */
    public Integer getCarId() {
        return carId;
    }

    /**
     * 设置车主ID
     *
     * @param carId 车主ID
     */
    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    /**
     * 获取业务类型(0：出租车，1约租车)
     *
     * @return BUSINESS_TYPE - 业务类型(0：出租车，1约租车)
     */
    public Integer getBusinessType() {
        return businessType;
    }

    /**
     * 设置业务类型(0：出租车，1约租车)
     *
     * @param businessType 业务类型(0：出租车，1约租车)
     */
    public void setBusinessType(Integer businessType) {
        this.businessType = businessType;
    }

    /**
     * 获取订单类型(0.app用车，1.allcenter用车，2.app预约，3.callcenter预约，4.协议价，5.接送机，6.首汽
7:酒店实时，8：酒店预约，9企业实时，10企业预约)
     *
     * @return ORDER_TYPE - 订单类型(0.app用车，1.allcenter用车，2.app预约，3.callcenter预约，4.协议价，5.接送机，6.首汽
7:酒店实时，8：酒店预约，9企业实时，10企业预约)
     */
    public Integer getOrderType() {
        return orderType;
    }

    /**
     * 设置订单类型(0.app用车，1.allcenter用车，2.app预约，3.callcenter预约，4.协议价，5.接送机，6.首汽
7:酒店实时，8：酒店预约，9企业实时，10企业预约)
     *
     * @param orderType 订单类型(0.app用车，1.allcenter用车，2.app预约，3.callcenter预约，4.协议价，5.接送机，6.首汽
7:酒店实时，8：酒店预约，9企业实时，10企业预约)
     */
    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    /**
     * 获取订单来源：0 android；1 IOS；2 wechat；3 callcenter；4 首汽 ，5：酒店，6：企业
     *
     * @return ORDER_SOURCE - 订单来源：0 android；1 IOS；2 wechat；3 callcenter；4 首汽 ，5：酒店，6：企业
     */
    public Integer getOrderSource() {
        return orderSource;
    }

    /**
     * 设置订单来源：0 android；1 IOS；2 wechat；3 callcenter；4 首汽 ，5：酒店，6：企业
     *
     * @param orderSource 订单来源：0 android；1 IOS；2 wechat；3 callcenter；4 首汽 ，5：酒店，6：企业
     */
    public void setOrderSource(Integer orderSource) {
        this.orderSource = orderSource;
    }

    /**
     * 获取车型（0舒适型，1商务型，2豪华型,3经济型）
     *
     * @return CAR_TYPE - 车型（0舒适型，1商务型，2豪华型,3经济型）
     */
    public String getCarType() {
        return carType;
    }

    /**
     * 设置车型（0舒适型，1商务型，2豪华型,3经济型）
     *
     * @param carType 车型（0舒适型，1商务型，2豪华型,3经济型）
     */
    public void setCarType(String carType) {
        this.carType = carType;
    }

    /**
     * 获取数量（默认1）
     *
     * @return CAR_NUMBER - 数量（默认1）
     */
    public Integer getCarNumber() {
        return carNumber;
    }

    /**
     * 设置数量（默认1）
     *
     * @param carNumber 数量（默认1）
     */
    public void setCarNumber(Integer carNumber) {
        this.carNumber = carNumber;
    }

    /**
     * 获取起点
     *
     * @return START_PLACE - 起点
     */
    public String getStartPlace() {
        return startPlace;
    }

    /**
     * 设置起点
     *
     * @param startPlace 起点
     */
    public void setStartPlace(String startPlace) {
        this.startPlace = startPlace;
    }

    /**
     * 获取起点GPS信息
     *
     * @return START_GPS - 起点GPS信息
     */
    public String getStartGps() {
        return startGps;
    }

    /**
     * 设置起点GPS信息
     *
     * @param startGps 起点GPS信息
     */
    public void setStartGps(String startGps) {
        this.startGps = startGps;
    }

    /**
     * 获取终点GPS信息（存储JSON）
     *
     * @return END_GPS - 终点GPS信息（存储JSON）
     */
    public String getEndGps() {
        return endGps;
    }

    /**
     * 设置终点GPS信息（存储JSON）
     *
     * @param endGps 终点GPS信息（存储JSON）
     */
    public void setEndGps(String endGps) {
        this.endGps = endGps;
    }

    /**
     * 获取预计公里数 KM 小数后两位
     *
     * @return EXPECTED_KM - 预计公里数 KM 小数后两位
     */
    public Float getExpectedKm() {
        return expectedKm;
    }

    /**
     * 设置预计公里数 KM 小数后两位
     *
     * @param expectedKm 预计公里数 KM 小数后两位
     */
    public void setExpectedKm(Float expectedKm) {
        this.expectedKm = expectedKm;
    }

    /**
     * 获取实际公里数 KM  小数后两位
     *
     * @return ACTUAL_KM - 实际公里数 KM  小数后两位
     */
    public Float getActualKm() {
        return actualKm;
    }

    /**
     * 设置实际公里数 KM  小数后两位
     *
     * @param actualKm 实际公里数 KM  小数后两位
     */
    public void setActualKm(Float actualKm) {
        this.actualKm = actualKm;
    }

    /**
     * 获取0 非指派单 1指派单
     *
     * @return ALLOCATE_ORDER - 0 非指派单 1指派单
     */
    public Integer getAllocateOrder() {
        return allocateOrder;
    }

    /**
     * 设置0 非指派单 1指派单
     *
     * @param allocateOrder 0 非指派单 1指派单
     */
    public void setAllocateOrder(Integer allocateOrder) {
        this.allocateOrder = allocateOrder;
    }

    /**
     * 获取订单总价格
     *
     * @return TOTAL_PRICE - 订单总价格
     */
    public Float getTotalPrice() {
        return totalPrice;
    }

    /**
     * 设置订单总价格
     *
     * @param totalPrice 订单总价格
     */
    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * 获取预计车费价格  小数后两位
     *
     * @return EXPECTED_PRICE - 预计车费价格  小数后两位
     */
    public Float getExpectedPrice() {
        return expectedPrice;
    }

    /**
     * 设置预计车费价格  小数后两位
     *
     * @param expectedPrice 预计车费价格  小数后两位
     */
    public void setExpectedPrice(Float expectedPrice) {
        this.expectedPrice = expectedPrice;
    }

    /**
     * 获取实际车费价格  小数后两位,用户获得车费(不含平台费)
     *
     * @return ACTUAL_PRICE - 实际车费价格  小数后两位,用户获得车费(不含平台费)
     */
    public Float getActualPrice() {
        return actualPrice;
    }

    /**
     * 设置实际车费价格  小数后两位,用户获得车费(不含平台费)
     *
     * @param actualPrice 实际车费价格  小数后两位,用户获得车费(不含平台费)
     */
    public void setActualPrice(Float actualPrice) {
        this.actualPrice = actualPrice;
    }

    /**
     * 获取低俗行驶时间
     *
     * @return LOW_SPEED_TIMES - 低俗行驶时间
     */
    public Double getLowSpeedTimes() {
        return lowSpeedTimes;
    }

    /**
     * 设置低俗行驶时间
     *
     * @param lowSpeedTimes 低俗行驶时间
     */
    public void setLowSpeedTimes(Double lowSpeedTimes) {
        this.lowSpeedTimes = lowSpeedTimes;
    }

    /**
     * 获取其他费用1 ---优惠劵支付金额
     *
     * @return OTHER_PRICE - 其他费用1 ---优惠劵支付金额
     */
    public Float getOtherPrice() {
        return otherPrice;
    }

    /**
     * 设置其他费用1 ---优惠劵支付金额
     *
     * @param otherPrice 其他费用1 ---优惠劵支付金额
     */
    public void setOtherPrice(Float otherPrice) {
        this.otherPrice = otherPrice;
    }

    /**
     * 获取费用名称1 ---搬运费
     *
     * @return OTHER_PRICE_DESCRIPTION - 费用名称1 ---搬运费
     */
    public String getOtherPriceDescription() {
        return otherPriceDescription;
    }

    /**
     * 设置费用名称1 ---搬运费
     *
     * @param otherPriceDescription 费用名称1 ---搬运费
     */
    public void setOtherPriceDescription(String otherPriceDescription) {
        this.otherPriceDescription = otherPriceDescription;
    }

    /**
     * 获取打印发票金额
     *
     * @return OTHER_PRICE2 - 打印发票金额
     */
    public Float getOtherPrice2() {
        return otherPrice2;
    }

    /**
     * 设置打印发票金额
     *
     * @param otherPrice2 打印发票金额
     */
    public void setOtherPrice2(Float otherPrice2) {
        this.otherPrice2 = otherPrice2;
    }

    /**
     * 获取费用名称2
     *
     * @return OTHER_PRICE_DESCRIPTION2 - 费用名称2
     */
    public String getOtherPriceDescription2() {
        return otherPriceDescription2;
    }

    /**
     * 设置费用名称2
     *
     * @param otherPriceDescription2 费用名称2
     */
    public void setOtherPriceDescription2(String otherPriceDescription2) {
        this.otherPriceDescription2 = otherPriceDescription2;
    }

    /**
     * 获取大众平台获得金额
     *
     * @return OTHER_PRICE3 - 大众平台获得金额
     */
    public Float getOtherPrice3() {
        return otherPrice3;
    }

    /**
     * 设置大众平台获得金额
     *
     * @param otherPrice3 大众平台获得金额
     */
    public void setOtherPrice3(Float otherPrice3) {
        this.otherPrice3 = otherPrice3;
    }

    /**
     * 获取费用名称3
     *
     * @return OTHER_PRICE_DESCRIPTION3 - 费用名称3
     */
    public String getOtherPriceDescription3() {
        return otherPriceDescription3;
    }

    /**
     * 设置费用名称3
     *
     * @param otherPriceDescription3 费用名称3
     */
    public void setOtherPriceDescription3(String otherPriceDescription3) {
        this.otherPriceDescription3 = otherPriceDescription3;
    }

    /**
     * 获取计价器价格
     *
     * @return OTHER_PRICE4 - 计价器价格
     */
    public Float getOtherPrice4() {
        return otherPrice4;
    }

    /**
     * 设置计价器价格
     *
     * @param otherPrice4 计价器价格
     */
    public void setOtherPrice4(Float otherPrice4) {
        this.otherPrice4 = otherPrice4;
    }

    /**
     * 获取费用名称4
     *
     * @return OTHER_PRICE_DESCRIPTION4 - 费用名称4
     */
    public String getOtherPriceDescription4() {
        return otherPriceDescription4;
    }

    /**
     * 设置费用名称4
     *
     * @param otherPriceDescription4 费用名称4
     */
    public void setOtherPriceDescription4(String otherPriceDescription4) {
        this.otherPriceDescription4 = otherPriceDescription4;
    }

    /**
     * 获取乘客手机号
     *
     * @return CUSTOMER_PHONE - 乘客手机号
     */
    public String getCustomerPhone() {
        return customerPhone;
    }

    /**
     * 设置乘客手机号
     *
     * @param customerPhone 乘客手机号
     */
    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    /**
     * 获取乘客名称
     *
     * @return CUSTOMER_NAME - 乘客名称
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * 设置乘客名称
     *
     * @param customerName 乘客名称
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * 获取手机号码
     *
     * @return CAR_USER_PHONE - 手机号码
     */
    public String getCarUserPhone() {
        return carUserPhone;
    }

    /**
     * 设置手机号码
     *
     * @param carUserPhone 手机号码
     */
    public void setCarUserPhone(String carUserPhone) {
        this.carUserPhone = carUserPhone;
    }

    /**
     * 获取车主名称
     *
     * @return CAR_USER_NAME - 车主名称
     */
    public String getCarUserName() {
        return carUserName;
    }

    /**
     * 设置车主名称
     *
     * @param carUserName 车主名称
     */
    public void setCarUserName(String carUserName) {
        this.carUserName = carUserName;
    }

    /**
     * 获取叫车时间
     *
     * @return CALL_DATE - 叫车时间
     */
    public Date getCallDate() {
        return callDate;
    }

    /**
     * 设置叫车时间
     *
     * @param callDate 叫车时间
     */
    public void setCallDate(Date callDate) {
        this.callDate = callDate;
    }

    /**
     * 获取预约时间
     *
     * @return BOOK_DATE - 预约时间
     */
    public Date getBookDate() {
        return bookDate;
    }

    /**
     * 设置预约时间
     *
     * @param bookDate 预约时间
     */
    public void setBookDate(Date bookDate) {
        this.bookDate = bookDate;
    }

    /**
     * 获取订单开始时间
     *
     * @return ORDER_START_DATE - 订单开始时间
     */
    public Date getOrderStartDate() {
        return orderStartDate;
    }

    /**
     * 设置订单开始时间
     *
     * @param orderStartDate 订单开始时间
     */
    public void setOrderStartDate(Date orderStartDate) {
        this.orderStartDate = orderStartDate;
    }

    /**
     * 获取运输开始时间
     *
     * @return TRANPORT_START_DATE - 运输开始时间
     */
    public Date getTranportStartDate() {
        return tranportStartDate;
    }

    /**
     * 设置运输开始时间
     *
     * @param tranportStartDate 运输开始时间
     */
    public void setTranportStartDate(Date tranportStartDate) {
        this.tranportStartDate = tranportStartDate;
    }

    /**
     * 获取运输结束时间
     *
     * @return TRANPORT_END_DATE - 运输结束时间
     */
    public Date getTranportEndDate() {
        return tranportEndDate;
    }

    /**
     * 设置运输结束时间
     *
     * @param tranportEndDate 运输结束时间
     */
    public void setTranportEndDate(Date tranportEndDate) {
        this.tranportEndDate = tranportEndDate;
    }

    /**
     * 获取订单完成时间
     *
     * @return FINISH_DATE - 订单完成时间
     */
    public Date getFinishDate() {
        return finishDate;
    }

    /**
     * 设置订单完成时间
     *
     * @param finishDate 订单完成时间
     */
    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    /**
     * 获取货主获评
     *
     * @return CUSTOMER_GRADE - 货主获评
     */
    public Integer getCustomerGrade() {
        return customerGrade;
    }

    /**
     * 设置货主获评
     *
     * @param customerGrade 货主获评
     */
    public void setCustomerGrade(Integer customerGrade) {
        this.customerGrade = customerGrade;
    }

    /**
     * 获取车主获评
     *
     * @return CAR_USER_GRADE - 车主获评
     */
    public Integer getCarUserGrade() {
        return carUserGrade;
    }

    /**
     * 设置车主获评
     *
     * @param carUserGrade 车主获评
     */
    public void setCarUserGrade(Integer carUserGrade) {
        this.carUserGrade = carUserGrade;
    }

    /**
     * 获取众币奖励或消耗(+100/-100)
     *
     * @return VIRTUAL - 众币奖励或消耗(+100/-100)
     */
    public Float getVirtual() {
        return virtual;
    }

    /**
     * 设置众币奖励或消耗(+100/-100)
     *
     * @param virtual 众币奖励或消耗(+100/-100)
     */
    public void setVirtual(Float virtual) {
        this.virtual = virtual;
    }

    /**
     * 获取结算类型(0,大众内部，1加盟的，2其他)
     *
     * @return SETTLEMENT_TYPE - 结算类型(0,大众内部，1加盟的，2其他)
     */
    public Integer getSettlementType() {
        return settlementType;
    }

    /**
     * 设置结算类型(0,大众内部，1加盟的，2其他)
     *
     * @param settlementType 结算类型(0,大众内部，1加盟的，2其他)
     */
    public void setSettlementType(Integer settlementType) {
        this.settlementType = settlementType;
    }

    /**
     * 获取回拨电话
     *
     * @return CALLBACK_PHONE - 回拨电话
     */
    public String getCallbackPhone() {
        return callbackPhone;
    }

    /**
     * 设置回拨电话
     *
     * @param callbackPhone 回拨电话
     */
    public void setCallbackPhone(String callbackPhone) {
        this.callbackPhone = callbackPhone;
    }

    /**
     * 获取后台接线员id
     *
     * @return CREATER - 后台接线员id
     */
    public Integer getCreater() {
        return creater;
    }

    /**
     * 设置后台接线员id
     *
     * @param creater 后台接线员id
     */
    public void setCreater(Integer creater) {
        this.creater = creater;
    }

    /**
     * 获取状态0创建，1已接单，2已完成，3取消，4放空，5无供，6超时，7强制派单中,8待支付，9到达目的，未支付, 10取消(车主取消),11开始计价,12计价完成,13强派拒绝,14司机到达出发地,15出租车待支付
     *
     * @return STATUS - 状态0创建，1已接单，2已完成，3取消，4放空，5无供，6超时，7强制派单中,8待支付，9到达目的，未支付, 10取消(车主取消),11开始计价,12计价完成,13强派拒绝,14司机到达出发地,15出租车待支付
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态0创建，1已接单，2已完成，3取消，4放空，5无供，6超时，7强制派单中,8待支付，9到达目的，未支付, 10取消(车主取消),11开始计价,12计价完成,13强派拒绝,14司机到达出发地,15出租车待支付
     *
     * @param status 状态0创建，1已接单，2已完成，3取消，4放空，5无供，6超时，7强制派单中,8待支付，9到达目的，未支付, 10取消(车主取消),11开始计价,12计价完成,13强派拒绝,14司机到达出发地,15出租车待支付
     */
    public void setStatus(Integer status) {
        this.status = status;
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
     * 获取是否开票：0 未开票  ; 1 自动开票 ； 2手动开票
     *
     * @return IS_INVOICE - 是否开票：0 未开票  ; 1 自动开票 ； 2手动开票
     */
    public Integer getIsInvoice() {
        return isInvoice;
    }

    /**
     * 设置是否开票：0 未开票  ; 1 自动开票 ； 2手动开票
     *
     * @param isInvoice 是否开票：0 未开票  ; 1 自动开票 ； 2手动开票
     */
    public void setIsInvoice(Integer isInvoice) {
        this.isInvoice = isInvoice;
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
     * 获取备用字段1--取消订单描述 
     *
     * @return BAKSTR1 - 备用字段1--取消订单描述 
     */
    public String getBakstr1() {
        return bakstr1;
    }

    /**
     * 设置备用字段1--取消订单描述 
     *
     * @param bakstr1 备用字段1--取消订单描述 
     */
    public void setBakstr1(String bakstr1) {
        this.bakstr1 = bakstr1;
    }

    /**
     * 获取订单备注
     *
     * @return BAKSTR2 - 订单备注
     */
    public String getBakstr2() {
        return bakstr2;
    }

    /**
     * 设置订单备注
     *
     * @param bakstr2 订单备注
     */
    public void setBakstr2(String bakstr2) {
        this.bakstr2 = bakstr2;
    }

    /**
     * 获取备用字段3--0:未见乘客,1:车辆故障,2:交通事故3:强制拒绝
     *
     * @return BAKSTR3 - 备用字段3--0:未见乘客,1:车辆故障,2:交通事故3:强制拒绝
     */
    public String getBakstr3() {
        return bakstr3;
    }

    /**
     * 设置备用字段3--0:未见乘客,1:车辆故障,2:交通事故3:强制拒绝
     *
     * @param bakstr3 备用字段3--0:未见乘客,1:车辆故障,2:交通事故3:强制拒绝
     */
    public void setBakstr3(String bakstr3) {
        this.bakstr3 = bakstr3;
    }

    /**
     * 获取备用字段4--车主获评描述
     *
     * @return BAKSTR4 - 备用字段4--车主获评描述
     */
    public String getBakstr4() {
        return bakstr4;
    }

    /**
     * 设置备用字段4--车主获评描述
     *
     * @param bakstr4 备用字段4--车主获评描述
     */
    public void setBakstr4(String bakstr4) {
        this.bakstr4 = bakstr4;
    }

    /**
     * 获取备用字段5--打赏的钱
     *
     * @return BAKSTR5 - 备用字段5--打赏的钱
     */
    public String getBakstr5() {
        return bakstr5;
    }

    /**
     * 设置备用字段5--打赏的钱
     *
     * @param bakstr5 备用字段5--打赏的钱
     */
    public void setBakstr5(String bakstr5) {
        this.bakstr5 = bakstr5;
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
     * 获取下单的城市
     *
     * @return CITY - 下单的城市
     */
    public String getCity() {
        return city;
    }

    /**
     * 设置下单的城市
     *
     * @param city 下单的城市
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 获取取消类型 :0货主取消;1车主取消
     *
     * @return DEL_TYPE - 取消类型 :0货主取消;1车主取消
     */
    public Integer getDelType() {
        return delType;
    }

    /**
     * 设置取消类型 :0货主取消;1车主取消
     *
     * @param delType 取消类型 :0货主取消;1车主取消
     */
    public void setDelType(Integer delType) {
        this.delType = delType;
    }

    /**
     * 获取加(减)价费用
     *
     * @return OTHER_PRICE5 - 加(减)价费用
     */
    public Float getOtherPrice5() {
        return otherPrice5;
    }

    /**
     * 设置加(减)价费用
     *
     * @param otherPrice5 加(减)价费用
     */
    public void setOtherPrice5(Float otherPrice5) {
        this.otherPrice5 = otherPrice5;
    }

    /**
     * 获取加价倍率
     *
     * @return OTHER_PRICE_DESCRIPTION5 - 加价倍率
     */
    public String getOtherPriceDescription5() {
        return otherPriceDescription5;
    }

    /**
     * 设置加价倍率
     *
     * @param otherPriceDescription5 加价倍率
     */
    public void setOtherPriceDescription5(String otherPriceDescription5) {
        this.otherPriceDescription5 = otherPriceDescription5;
    }

    /**
     * 获取路桥费
     *
     * @return TOLL_FEE - 路桥费
     */
    public Double getTollFee() {
        return tollFee;
    }

    /**
     * 设置路桥费
     *
     * @param tollFee 路桥费
     */
    public void setTollFee(Double tollFee) {
        this.tollFee = tollFee;
    }

    /**
     * 获取下单加价
     *
     * @return INCREASE_PRICE - 下单加价
     */
    public Double getIncreasePrice() {
        return increasePrice;
    }

    /**
     * 设置下单加价
     *
     * @param increasePrice 下单加价
     */
    public void setIncreasePrice(Double increasePrice) {
        this.increasePrice = increasePrice;
    }

    /**
     * 获取查看打印信息接口时，是否修改乘客可开票余额,0：未修改，1：已修改
     *
     * @return UPDATE_INVOICE - 查看打印信息接口时，是否修改乘客可开票余额,0：未修改，1：已修改
     */
    public Integer getUpdateInvoice() {
        return updateInvoice;
    }

    /**
     * 设置查看打印信息接口时，是否修改乘客可开票余额,0：未修改，1：已修改
     *
     * @param updateInvoice 查看打印信息接口时，是否修改乘客可开票余额,0：未修改，1：已修改
     */
    public void setUpdateInvoice(Integer updateInvoice) {
        this.updateInvoice = updateInvoice;
    }

    /**
     * 获取加价状态（0未加价，1扣加价，2还加价）
     *
     * @return INCREASE_TYPE - 加价状态（0未加价，1扣加价，2还加价）
     */
    public Integer getIncreaseType() {
        return increaseType;
    }

    /**
     * 设置加价状态（0未加价，1扣加价，2还加价）
     *
     * @param increaseType 加价状态（0未加价，1扣加价，2还加价）
     */
    public void setIncreaseType(Integer increaseType) {
        this.increaseType = increaseType;
    }

    /**
     * 获取订单渠道id
     *
     * @return CANAL_ID - 订单渠道id
     */
    public Integer getCanalId() {
        return canalId;
    }

    /**
     * 设置订单渠道id
     *
     * @param canalId 订单渠道id
     */
    public void setCanalId(Integer canalId) {
        this.canalId = canalId;
    }

    /**
     * 获取渠道金额
     *
     * @return CANAL_MONEY - 渠道金额
     */
    public Double getCanalMoney() {
        return canalMoney;
    }

    /**
     * 设置渠道金额
     *
     * @param canalMoney 渠道金额
     */
    public void setCanalMoney(Double canalMoney) {
        this.canalMoney = canalMoney;
    }

    /**
     * 获取监控席操作状态(0：未处理，1未确认，2处理中，3已处理，4已确认)
     *
     * @return CHECK_STATUS - 监控席操作状态(0：未处理，1未确认，2处理中，3已处理，4已确认)
     */
    public Integer getCheckStatus() {
        return checkStatus;
    }

    /**
     * 设置监控席操作状态(0：未处理，1未确认，2处理中，3已处理，4已确认)
     *
     * @param checkStatus 监控席操作状态(0：未处理，1未确认，2处理中，3已处理，4已确认)
     */
    public void setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
    }

    /**
     * 获取最后操作时间(监控席)
     *
     * @return LAST_TIME - 最后操作时间(监控席)
     */
    public Date getLastTime() {
        return lastTime;
    }

    /**
     * 设置最后操作时间(监控席)
     *
     * @param lastTime 最后操作时间(监控席)
     */
    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    /**
     * 获取该订单当前操作人ID（监控席）
     *
     * @return NOW_CHECK_BY - 该订单当前操作人ID（监控席）
     */
    public Integer getNowCheckBy() {
        return nowCheckBy;
    }

    /**
     * 设置该订单当前操作人ID（监控席）
     *
     * @param nowCheckBy 该订单当前操作人ID（监控席）
     */
    public void setNowCheckBy(Integer nowCheckBy) {
        this.nowCheckBy = nowCheckBy;
    }

    /**
     * 获取航班号
     *
     * @return FLIGHT_NO - 航班号
     */
    public String getFlightNo() {
        return flightNo;
    }

    /**
     * 设置航班号
     *
     * @param flightNo 航班号
     */
    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
    }

    /**
     * 获取航班日期 yyyy-MM-dd
     *
     * @return FLIGHT_DATE - 航班日期 yyyy-MM-dd
     */
    public Date getFlightDate() {
        return flightDate;
    }

    /**
     * 设置航班日期 yyyy-MM-dd
     *
     * @param flightDate 航班日期 yyyy-MM-dd
     */
    public void setFlightDate(Date flightDate) {
        this.flightDate = flightDate;
    }

    /**
     * 获取接送机（0.接机，1.送机）
     *
     * @return FLIGHT_SHUTTLE - 接送机（0.接机，1.送机）
     */
    public Integer getFlightShuttle() {
        return flightShuttle;
    }

    /**
     * 设置接送机（0.接机，1.送机）
     *
     * @param flightShuttle 接送机（0.接机，1.送机）
     */
    public void setFlightShuttle(Integer flightShuttle) {
        this.flightShuttle = flightShuttle;
    }

    /**
     * 获取终点（存储JSON）
     *
     * @return END_PLACE - 终点（存储JSON）
     */
    public String getEndPlace() {
        return endPlace;
    }

    /**
     * 设置终点（存储JSON）
     *
     * @param endPlace 终点（存储JSON）
     */
    public void setEndPlace(String endPlace) {
        this.endPlace = endPlace;
    }
}