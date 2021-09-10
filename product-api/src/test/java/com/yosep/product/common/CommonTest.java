package com.yosep.product.common;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Slf4j
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class CommonTest extends BaseUnitTest{

    @Test
    @DisplayName("Rand Value Test")
    public void createRandomValueTest() throws NoSuchAlgorithmException {
        SecureRandom rand = SecureRandom.getInstance("SHA1PRNG");
        long value = (long) (rand.nextDouble() * 100000);

        log.info("" + value);
    }

    @Test
    @DisplayName("UUID 생성 테스트")
    public void createUuidTest() {
        log.info("UUID 생성 테스트");
        String uuid = UUID.randomUUID().toString();
        log.info("UUID: " + uuid);
    }

    @Test
    public void localDateTest() {
        LocalDateTime currentTime = LocalDateTime.now();
        System.out.println(currentTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")));
    }
}
