package com.visionet.core.util;

import com.visionet.core.redis.RedisUtil;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class OrderCreaterUtil {

    private static SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmm");
    private static SimpleDateFormat simpleF = new SimpleDateFormat("yyyyMMdd");

    public static String createrOrder(String businessType) {
        String orderId = "";
        Date date = Calendar.getInstance().getTime();
        String dateS = sf.format(date);
        String orderIdLeft = dateS + businessType;
        orderId = orderIdLeft + getRandomNumber();
        try {
            Object orderIdRedis = RedisUtil.getObjectData("orderId");
            if (StringUtils.isEmpty(orderIdRedis)) {
                orderId = orderId + "0000001";
            } else {
                if (orderIdRedis instanceof String) {
                    String orderId_ord = (String) orderIdRedis;
                    if (!StringUtils.isEmpty(orderId_ord)) {
                        String yyyymmdd = orderId_ord.substring(0, 8);
                        Date redisDate = simpleF.parse(yyyymmdd);
                        Date nowDate = simpleF.parse(simpleF.format(date));
                        if (nowDate.compareTo(redisDate) <= 0) {
                            String number = orderId_ord.substring(orderId_ord.length() - 5, orderId_ord.length());
                            number = Integer.parseInt(number) + "";
                            orderId = orderId + getLastNumber(number);
                        } else {
                            orderId = orderId + "0000001";
                        }
                    }
                }

            }
            RedisUtil.setObjectData("orderId", orderId, 99999999);

        } catch (Exception e) {
            orderId = orderId + "00001";
        }
        System.out.println("生成订单ID：" + orderId);
        return orderId;
    }

    private static String getRandomNumber() {
        String code = "";
        for (int i = 0; i < 3; i++) {
            int intValue = (int) (Math.random() * 10 + 48);
            code = code + (char) intValue;
        }
        return code;
    }

    private static String getLastNumber(String number) {
        number = Integer.parseInt(number) + 1 + "";
        String string0 = "";
        for (int i = 0; i < 7 - number.length(); i++) {
            string0 += "0";
        }
        string0 += number;
        return string0;
    }

}
