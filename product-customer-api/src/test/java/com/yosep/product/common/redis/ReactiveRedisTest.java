package com.yosep.product.common.redis;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.ReactiveValueOperations;
import org.springframework.test.context.ActiveProfiles;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@ActiveProfiles("test")
@Slf4j
public class ReactiveRedisTest extends BaseRedisTest {
    @Autowired
    ReactiveRedisTemplate<String, String> redisTemplate;

    @Autowired
    ReactiveRedisOperations<String, String> valueOps;

    @Test
    @DisplayName("[Reactive Redis] 정수 값 넣고빼기 성공 테스트")
    public void 정수_값_넣고빼기_성공_테스트() {
        log.info("[Reactive Redis] 정수 값 넣기 성공 테스트");
        ReactiveValueOperations<String, String> valueOps = redisTemplate.opsForValue();

        StepVerifier.create(
                valueOps.set("a", "1")
                        .flatMap(result -> {
                            return valueOps.get("a");
                        }))
                .assertNext(result -> {
                    log.info("result: " + result);
                    Assertions.assertEquals("1", result);
                })
                .verifyComplete();
    }

    @Test
    public void a() {
        String t1 = Timestamp.valueOf(LocalDateTime.now())
                .toString()
                .replaceAll("[- :]","");
        String t2 = Timestamp.valueOf(LocalDateTime.now())
                .toString()
                .replaceAll("[- :]","");

        String t3 = Timestamp.valueOf(LocalDateTime.now())
                .toString()
                .replaceAll("[- :]","");

        log.info("t1: " + t1);
        log.info("t2: " + t2);
        log.info("t3: " + t3);
    }

}
