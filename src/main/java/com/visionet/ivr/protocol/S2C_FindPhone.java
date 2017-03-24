package com.visionet.ivr.protocol;

/**
 * 得到客户以前的信息
 * @author zph
 *
 */
public class S2C_FindPhone extends MsgBase {

	{
		setMsgId(201);
		msgStructs.put("Sessionid",null);
		msgStructs.put("ChannelID",null);
		msgStructs.put("电话号码",null);
		msgStructs.put("订单号",null);
		msgStructs.put("订单状态",null);
		msgStructs.put("车牌号",null);
		msgStructs.put("车当前位置",null);
		msgStructs.put("是否有分机",null);
		msgStructs.put("乘客姓名",null);
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
		msgStructs.put("订车次数",null);
		msgStructs.put("放空次数",null);
		msgStructs.put("客户级别",null);
		msgStructs.put("乘客性别",null);
		msgStructs.put("上次订单时间",null);
		msgStructs.put("上次用车时间",null);
		msgStructs.put("乘客地址经度",null);
		msgStructs.put("乘客地址纬度",null);
		msgStructs.put("备注",null);
		msgStructs.put("订车形式",null);
		msgStructs.put("路程",null);
		msgStructs.put("重复订车",null);
	}
}
