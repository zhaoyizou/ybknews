package com.visionet.ivr.protocol;

/**
 * 发送订单到服务器 提交订单
 * @author zph
 *
 */
public class C2S_DoSubmit extends MsgBase {

	{
		setMsgId(103);
		msgStructs.put("Sessionid",null);
		msgStructs.put("ChannelID",null);
		msgStructs.put("电话号码",null);
		msgStructs.put("乘客姓名",null);
		msgStructs.put("定车时间",null);
		msgStructs.put("地标",null);
		msgStructs.put("路",null);
		msgStructs.put("弄",null);
		msgStructs.put("支弄",null);
		msgStructs.put("门牌号码",null);
		msgStructs.put("栋",null);
		msgStructs.put("房间号码",null);
		msgStructs.put("临近道路",null);
		msgStructs.put("接客点",null);
		msgStructs.put("目的地",null);
		msgStructs.put("要车数","-1");
		msgStructs.put("乘客数","1");
		msgStructs.put("单双号","0");
		msgStructs.put("订车形式","-1");
		msgStructs.put("车型","-1");
		msgStructs.put("公司","-1");
		msgStructs.put("备注",null);
		msgStructs.put("性别","0");
		msgStructs.put("经度","1.0");//经,纬度不能为0
		msgStructs.put("纬度","1.0");
		msgStructs.put("路程","0");
	}
}
