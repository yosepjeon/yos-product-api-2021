package com.yosep.product.common.logic;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class RandomIdGenerator {
    public static String createId() {
        String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
        String uuid = UUID.randomUUID().toString().substring(0, 18);

        return currentTime + "-" + uuid;
    }
}
