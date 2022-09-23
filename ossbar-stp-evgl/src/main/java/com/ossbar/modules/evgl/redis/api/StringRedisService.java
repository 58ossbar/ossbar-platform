package com.ossbar.modules.evgl.redis.api;

public interface StringRedisService {
	
	/**
	 * 加锁 
	 * @param key
	 * @param value
	 * @return
	 */
    boolean secKilllock(String key, String value);

    /**
     * 解锁
     * @param key
     * @param value
     */
    void unlock(String key, String value);

	
}
