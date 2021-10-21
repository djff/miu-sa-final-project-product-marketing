package edu.miu.inventoryservice.miusamsproductatega.mongo.redis;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;

@Component
public class RedisConfigSerializer implements RedisSerializer<Object> {

    @Override
    public byte[] serialize(Object o) throws SerializationException {
        return SerializationUtils.serialize(0);
    }

    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        return SerializationUtils.deserialize(bytes);
    }

    @Override
    public boolean canSerialize(Class<?> type) {
        return RedisSerializer.super.canSerialize(type);
    }

    @Override
    public Class<?> getTargetType() {
        return RedisSerializer.super.getTargetType();
    }
}