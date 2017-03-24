package com.visionet.core.redis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.visionet.core.util.ResourceUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * redis工具类
 */
public class RedisUtil {
	private static JedisPool jedisPool = null;
	private static final Logger log = LoggerFactory.getLogger(RedisUtil.class);


	/**
	 * 初始化Redis连接池
	 */
	static {
		try {
			String redisIp = ResourceUtil.getValueBykey("base", "redis.ip");
			// String auth = ResourceUtil.getValueBykey("base", "redis.auth");
			String port = ResourceUtil.getValueBykey("base", "redis.port");
			// 创建jedis池配置实例
			JedisPoolConfig config = new JedisPoolConfig();
			// 设置池配置项值
			//config.setMaxActive(2000);
			config.setMaxIdle(200);
			config.setMaxWaitMillis(1000 * 100);
			config.setTestOnBorrow(true);
			jedisPool = new JedisPool(config, redisIp, Integer.parseInt(port), 10000);
			// jedisPool = new JedisPool(config, redisIp,
			// Integer.parseInt(port), 10000);
			// jedisPool = new JedisPool(config, redisIp, 6379, 10000, auth);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("[redis]初始化Redis连接池异常 : " + e);
		}
	}

	/**
	 * 获取Jedis实例
	 * 
	 * @return
	 */
	public synchronized static Jedis getJedis() {
		try {
			if (jedisPool != null) {

				Jedis resource = jedisPool.getResource();
				// resource.auth(auth);
				return resource;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("[redis]获取Jedis实例 : " + e);
			return null;
		}
	}

	/**
	 * 释放jedis资源
	 * 
	 * @param jedis
	 */
	public static void returnResource(final Jedis jedis) {
		if (jedis != null) {
			//jedisPool.returnResource(jedis);
			jedis.close();
		}
	}

	/**
	 * Redis保存字符串
	 * 
	 * @param key
	 * @param value
	 */
	public static void setData(String key, String value, Integer seconds) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();

			jedis.set(key, value);
			if (seconds != null) {
				jedis.expire(key, seconds);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("[redis]Redis保存字符串 : " + e);
		} finally {
			//jedisPool.returnResource(jedis);
			jedis.close();
		}
	}

	/**
	 * Redis保存MAP
	 * 
	 * @param key
	 * @param value
	 */
	public static void setMapData(String key, Map<String, String> value, Integer seconds) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.hmset(key, value);
			if (seconds != null) {
				jedis.expire(key, seconds);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("[redis]Redis保存MAP : " + e);
		} finally {
			//jedisPool.returnResource(jedis);
			jedis.close();
		}
	}

	/**
	 * 获取字符串
	 * 
	 * @param key
	 * @return
	 */
	public static String getData(String key) {
		Jedis jedis = null;
		String ret = "";
		try {
			jedis = jedisPool.getResource();
			ret = jedis.get(key);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("[redis]获取字符串_getData() : " + e);
		} finally {
			//jedisPool.returnResource(jedis);
			jedis.close();
		}
		return ret;
	}

	/**
	 * 获取ＭＡＰ
	 * 
	 * @param key
	 * @param field
	 * @return
	 */
	public static String getData(String key, String field) {
		Jedis jedis = null;
		String ret = "";
		try {
			jedis = jedisPool.getResource();
			ret = jedis.hget(key, field);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("[redis]获取ＭＡＰ_getData() : " + e);
		} finally {
			//jedisPool.returnResource(jedis);
			jedis.close();
		}
		return ret;

	}

	/**
	 * 删除数据
	 * 
	 * @param key
	 */
	public static Long delData(String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.del(key);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("[redis]获取ＭＡＰ_delData() : " + e);
		} finally {
			//jedisPool.returnResource(jedis);
			jedis.close();
		}
		return 0L;
	}

	/**
	 * 保存对象
	 * 
	 * @param key
	 * @param value
	 */
	public static void setObjectData(String key, Object value, Integer seconds) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.set(key.getBytes(), SerializeUtil.serialize(value));
			if (seconds != null) {
				jedis.expire(key, seconds);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("[redis]保存对象_setObjectData() : " + e);
		} finally {
			//jedisPool.returnResource(jedis);
			jedis.close();
		}

	}

	/**
	 * 保存对象LIST 在list的队尾插入
	 * 
	 * @param key
	 * @param value
	 */
	public static void setObjectListData(String key, Object value) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.lpush(key.getBytes(), SerializeUtil.serialize(value));
		} catch (Exception e) {
			e.printStackTrace();
			log.error("[redis]保存对象LIST 在list的队尾插入_setObjectListData() : " + e);
		} finally {
			//jedisPool.returnResource(jedis);
			jedis.close();
		}
	}
	/**
	 * 保存对象LIST 在list的队尾插入
	 * 
	 * @param key
	 * @param value
	 */
	public static long rpush(String key, String value) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.rpush(key, value);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("[redis]保存对象LIST 在list的队尾插入rpush() : " + e);
		} finally {
			//jedisPool.returnResource(jedis);
			jedis.close();
			
		}
		return 0;
	}
	/**
	 * 保存对象LIST 在list的队尾插入
	 * 
	 * @param key
	 * @param value
	 */
	public static long lpush(String key, String value) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.lpush(key, value);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("[redis]保存对象LIST 在list的队首插入lpush() : " + e);
		} finally {
			//jedisPool.returnResource(jedis);
			jedis.close();
			
		}
		return 0;
	}
	
	/**
	 * 
	 * @param source
	 * @param destination
	 * @param timeout
	 * @return
	 */
	public static String brpoplpush(String source,String destination,int timeout) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.brpoplpush(source, destination, timeout);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("[brpoplpush] 在list的队尾弹出,并向另一个list destination 插入该元素: " +e.getMessage());
		} finally {
			//jedisPool.returnResource(jedis);
			jedis.close();
			
		}
		return null;
	}

	/**
	 * 保存对象LIST 在list的队尾插入,带有效时间
	 * 
	 * @param key
	 * @param value
	 */
	public static void setObjectListData(String key, Object value, Integer seconds) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.rpush(key.getBytes(), SerializeUtil.serialize(value));
			if (seconds != null) {
				jedis.expire(key, seconds);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("[redis]保存对象LIST 在list的队尾插入,带有效时间_setObjectListData() : " + e);
		} finally {
			//jedisPool.returnResource(jedis);
			jedis.close();
		}
	}

	/**
	 * 保存对象LIST 在list的队尾插入
	 * 
	 * @param key
	 * @param value
	 */
	public static void setOneListData(String key, String value, Integer seconds) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.rpush(key.getBytes(), SerializeUtil.serialize(value));
			if (seconds != null) {
				jedis.expire(key, seconds);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("[redis]保存对象LIST 在list的队尾插入_setOneListData() : " + e);
		} finally {
			//jedisPool.returnResource(jedis);
			jedis.close();
		}
	}

	/**
	 * 删除list中的元素()
	 * 
	 * @param key
	 *            list的key
	 * @param begin
	 *            需要保留的数据的开始下标
	 * @param end
	 *            需要保留的数据的结束下标 -1为队尾
	 */
	public static void ltrimListDatta(String key, int begin, int end) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.ltrim(key.getBytes(), begin, end);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("[redis]删除list中的元素()_ltrimListDatta() : " + e);
		} finally {
			//jedisPool.returnResource(jedis);
			jedis.close();
		}

	}

	/**
	 * 获取对象
	 * 
	 * @param key
	 * @return
	 */
	public static Object getObjectData(String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			byte[] bytes = jedis.get(key.getBytes());
			Object ob = null;
			if (bytes != null) {
				ob = SerializeUtil.unserialize(bytes);
			}
			return ob;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("[redis]获取对象_getObjectData() : " + e);
		} finally {
			//jedisPool.returnResource(jedis);
			jedis.close();
		}
		return null;
	}

	/**
	 * 获取对象列表
	 * 
	 * @param key
	 * @return
	 */
	public static List<Object> getObjectListData(String key) {
		List<Object> os = new ArrayList<Object>();
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			List<byte[]> bytes = jedis.lrange(key.getBytes(), 0, -1);
			for (byte[] bs : bytes) {
				os.add(SerializeUtil.unserialize(bs));
			}
			return os;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("[redis]获取对象列表_getObjectListData() : " + e);
		} finally {
			//jedisPool.returnResource(jedis);
			jedis.close();
		}
		return os;
	}

	/**
	 * 删除对象列表 需重写对象的equals方法
	 * 
	 * @param key
	 * @return
	 */
	public static void lrem(String key, Object value) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			Long lrem = jedis.lrem(key.getBytes(), -1, SerializeUtil.serialize(value));
		} catch (Exception e) {
			e.printStackTrace();
			log.error("[redis]删除对象列表 需重写对象的equals方法_lrem() : " + e);
		} finally {
			//jedisPool.returnResource(jedis);
			jedis.close();
		}
	}

	/**
	 * 设置有效期
	 * 
	 * @param string
	 *            :key
	 * @param i
	 *            :时间
	 */
	public static void setExpire(String key, int i) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.expire(key.getBytes(), i);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("[redis]删除对象列表 需重写对象的equals方法_lrem() : " + e);
		} finally {
			//jedisPool.returnResource(jedis);
			jedis.close();
		}

	}

	public static Set<String> getKeys(String byKey) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			Set<String> keys = jedis.keys(byKey);
			return keys;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("[redis]获取所有key_getKeys() : " + e);
		} finally {
			//jedisPool.returnResource(jedis);
			jedis.close();
		}
		return null;
	}


}
