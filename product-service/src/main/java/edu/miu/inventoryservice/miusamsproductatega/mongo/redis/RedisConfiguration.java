//package edu.miu.inventoryservice.miusamsproductatega.mongo.redis;
//
//import org.springframework.cache.CacheManager;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Profile;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cache.annotation.CachingConfigurerSupport;
//import org.springframework.context.annotation.Bean;
//import org.springframework.data.redis.cache.RedisCacheManager;
//import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//
//@Profile(value = { "production", "dev" })
//@Configuration
//
//public class RedisConfiguration extends CachingConfigurerSupport {
//
//	@Value("${spring.redis.host}")
//	private String hostname;
//
//	@Value("${spring.redis.port}")
//	private int port;
//
//	@Bean
//	public JedisConnectionFactory jedisConnectionFactory() {
//		RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
//		redisStandaloneConfiguration.setHostName("127.0.0.1");
//		redisStandaloneConfiguration.setPort(6379);
//		JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(redisStandaloneConfiguration);
//		return jedisConnectionFactory;
//	}
//
//	@Bean
//	public RedisTemplate<String, Object> redisTemplate() {
//		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
//		redisTemplate.setConnectionFactory(jedisConnectionFactory());
//		redisTemplate.setHashKeySerializer(new StringRedisSerializer());
//		redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
//		redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
//		redisTemplate.setEnableTransactionSupport(true);
//		redisTemplate.afterPropertiesSet();
//		return redisTemplate;
//
//
//	}
//
//}
