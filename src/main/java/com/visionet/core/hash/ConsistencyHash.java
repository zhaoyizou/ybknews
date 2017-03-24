package com.visionet.core.hash;

import com.visionet.core.util.ResourceUtil;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class ConsistencyHash {
	private TreeMap<Long, Object> nodes = null;
	// 真实服务器节点信息
	private List<Object> shards = new ArrayList<Object>();
	// 调用配置文件，为订单服务器的数量
	private int shardSize = 2;
	// 设置虚拟节点数目
	private int VIRTUAL_NUM = 4;

	public ConsistencyHash() {
		init();
	}

	/**
	 * 初始化一致环
	 */
	public void init() {
		String serverNum =  ResourceUtil.getValueBykey("base",
				"order.server.num");
		if(serverNum!=null && !"".equals(serverNum)){
			shardSize = Integer.parseInt(serverNum);
		}
		for (int i = 0; i < shardSize; i++) {
			shards.add(i+"");
		}

		// shards.add("192.168.0.2-服务器2");
		// shards.add("192.168.0.3-服务器3");
		// shards.add("192.168.0.4-服务器4");

		nodes = new TreeMap<Long, Object>();
		for (int i = 0; i < shards.size(); i++) {
			Object shardInfo = shards.get(i);
			for (int j = 0; j < VIRTUAL_NUM; j++) {
				nodes.put(hash(computeMd5("SHARD-" + i + "-NODE-" + j), j),
						shardInfo);
			}
		}
	}

	/**
	 * 根据key的hash值取得服务器节点信息
	 * 
	 * @param hash
	 * @return
	 */
	public Object getShardInfo(long hash) {
		Long key = hash;
		SortedMap<Long, Object> tailMap = nodes.tailMap(key);
		if (tailMap.isEmpty()) {
			key = nodes.firstKey();
		} else {
			key = tailMap.firstKey();
		}
		return nodes.get(key);
	}

	/**
	 * 打印圆环节点数据
	 */
	public void printMap() {
		System.out.println(nodes);
	}

	/**
	 * 根据2^32把节点分布到圆环上面。
	 * 
	 * @param digest
	 * @param nTime
	 * @return
	 */
	public long hash(byte[] digest, int nTime) {
		long rv = ((long) (digest[3 + nTime * 4] & 0xFF) << 24)
				| ((long) (digest[2 + nTime * 4] & 0xFF) << 16)
				| ((long) (digest[1 + nTime * 4] & 0xFF) << 8)
				| (digest[0 + nTime * 4] & 0xFF);

		return rv & 0xffffffffL; /* Truncate to 32-bits */
	}

	/**
	 * Get the md5 of the given key. 计算MD5值
	 */
	public byte[] computeMd5(String k) {
		MessageDigest md5;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("MD5 not supported", e);
		}
		md5.reset();
		byte[] keyBytes = null;
		try {
			keyBytes = k.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("Unknown string :" + k, e);
		}

		md5.update(keyBytes);
		return md5.digest();
	}

	public static String getListName(String orderId) {
		Random ran = new Random();
		ConsistencyHash hash = new ConsistencyHash();
		return (String) hash.getShardInfo(hash.hash(
				hash.computeMd5(String.valueOf(orderId)),
				ran.nextInt(hash.VIRTUAL_NUM)));
	}
	
	public static void main(String[] args) {
		System.out.println(getListName("1"));
	}
}
