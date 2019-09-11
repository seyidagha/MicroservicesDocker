package com.consumerApp.models;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.header.Headers;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.fasterxml.jackson.core.type.TypeReference;

public class CustomDeserializer extends JsonDeserializer<List<Record>>{
	@Override
    public List<Record> deserialize(String topic, Headers headers, byte[] data) {
        return deserialize(topic, data);
    }

    @Override
    public List<Record> deserialize(String topic, byte[] data) {
        if (data == null) {
            return null;
        }
        try {
            return objectMapper.readValue(data, new TypeReference<List<Record>>() {
            });
        } catch (IOException e) {
            throw new SerializationException("Can't deserialize data [" + Arrays.toString(data) +
                    "] from topic [" + topic + "]", e);
        }
    }
}
