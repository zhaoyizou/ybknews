package com.visionet.ivr.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.support.logging.LogFactory;
import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.visionet.core.util.HttpClient;
import com.visionet.core.util.TokenUtils;
import com.visionet.ivr.exception.MyException;
import com.visionet.ivr.mapper.OrderMapper;
import com.visionet.ivr.model.Order;
import com.visionet.ivr.protocol.C2S_DoSubmit;

import jodd.util.StringUtil;

@Service
public class OrderService {

	Logger log=LoggerFactory.getLogger(this.getClass());
	@Autowired
	OrderMapper orderMapper;
	public Order selectByCustomePhoneBetweenTime(String phone,Date start)
	{
		HashMap<String, Object> map=new HashMap<>();
		map.put("phone", phone);
		map.put("start", start);
		return orderMapper.selectByCustomePhoneBetweenTime(map);
	}
	/**
	 * 该方法目前仅对新系统进行下单,并返回下单结果
	 * @param orderSubmit
	 * @throws MyException 
	 */
	public void CreateOrder(C2S_DoSubmit orderSubmit) throws MyException
	{
		//orderSubmit.get(key)
		String startAddres="";
		if(!StringUtil.isEmpty(orderSubmit.get("路")))
		{
			startAddres+=orderSubmit.get("路");
		}
		if(!StringUtil.isEmpty(orderSubmit.get("弄")))
		{
			startAddres+=orderSubmit.get("弄");
		}
		if(!StringUtil.isEmpty(orderSubmit.get("支弄")))
		{
			startAddres+=orderSubmit.get("支弄");
		}
		if(!StringUtil.isEmpty(orderSubmit.get("门牌号码")))
		{
			startAddres+=orderSubmit.get("门牌号码");
		}
		//经纬度
		String location="";
		//通过高德API 把地址转换为坐标,从IVR和老调度系统发过来的坐标的坐标系可能不一致
		
		String adurl="http://restapi.amap.com/v3/geocode/geo?key=c858cd0054a9ce76c000c55cbb13539c&output=JSON&address="+startAddres;
		log.info("请求高德:"+adurl);
		String result=HttpClient.get(adurl);
		log.info("高德返回"+result);
		JSONObject resultJson=JSONObject.parseObject(result);
		if(!resultJson.get("infocode").equals("10000"))
		{
			log.error("高德地址在转换为坐标失败,错误代码:"+resultJson.get("infocode")+",请求信息:"+adurl);
			return;
		}
		
		JSONArray jsonArr=resultJson.getJSONArray("geocodes");
		if(jsonArr==null||jsonArr.size()==0)
		{
			log.error("高德Api地址转换坐标失败,错误原因:可能是未找到地址匹配的坐标。请求地址:"+adurl+"\r\n返回参数:"+resultJson);
			return;
		}
		JSONObject geocodes= jsonArr.getJSONObject(0);
		location=geocodes.getString("location");
		if(StringUtils.isEmpty(location))
		{
			log.error("高德Api地址转换坐标失败,错误原因:可能是未找到地址匹配的坐标。请求地址:"+adurl+"\r\n返回参数:"+resultJson);
			return;
		}
		JSONObject orderData=new JSONObject();
		orderData.put("valuation", "0");
		orderData.put("customerPhone",orderSubmit.get("电话号码"));
		orderData.put("customerPhone1", "");
		orderData.put("callbackPhone", orderSubmit.get("电话号码"));
		orderData.put("isCallback", "on");
		orderData.put("customerName", "");
		orderData.put("name", orderSubmit.get("性别").equals("0")?"先生":"女士");
		orderData.put("isTexi", "0");
		orderData.put("number", "1");
		orderData.put("isBook", "1");//?
		orderData.put("start1", "上海市");
		orderData.put("start", startAddres);
		orderData.put("startDe", "");
		orderData.put("mudi1", "107");//目的代码
		orderData.put("end", "未知目的地,请咨询乘客");
		orderData.put("beizhu", "");
		JSONArray startPlace=new JSONArray();
		JSONObject startPlaceDetial=new JSONObject();
		startPlaceDetial.put("address", startAddres);
		startPlaceDetial.put("gps", location);//起始地址经纬度
		startPlaceDetial.put("consignee",orderData.get("name"));
		startPlaceDetial.put("phone", orderData.get("callbackPhone"));
		startPlace.add(startPlaceDetial);
		orderData.put("startPlace", startPlace);
		
		JSONArray endPlace=new JSONArray();
		JSONObject endPlaceDetial=new JSONObject();
		endPlaceDetial.put("address", "未知目的地,请咨询乘客");
		endPlaceDetial.put("gps", "0.0,0.0");
		endPlace.add(endPlaceDetial);
		orderData.put("endPlace", endPlace);
		orderData.put("endDe", "");
		orderData.put("expectedKm", "0公里");
		orderData.put("expectPrice", "");
		orderData.put("carType", "0");
		orderData.put("start1Value", "107");
		orderData.put("orderCanal",null);
		orderData.put("canalMoney", null);
		orderData.put("orderMoney", null);
		orderData.put("orderSource", "7"); //订单来源 IVR
		String json=JSONObject.toJSONString(orderData, SerializerFeature.WriteMapNullValue);
		
		/*if(1==1){
			return;
		}*/
		//请求后台订单
		//String orderUrl="http://test-ht.letzgo.com.cn:12015/DZCX_Manage/order/add.action";
		String orderUrl="http://localhost:12015/DZCX_Manage/order/ivrOrder.action";
		HashMap<String, String> map=new HashMap<>();
		String token=TokenUtils.getToken(json);
		orderData.put("token", token);
		json=JSONObject.toJSONString(orderData, SerializerFeature.WriteMapNullValue);
		log.info("开始下单:"+json);
		result=HttpClient.post(orderUrl, json, map);
		log.info("下单结果:"+result);
	}
	
}
