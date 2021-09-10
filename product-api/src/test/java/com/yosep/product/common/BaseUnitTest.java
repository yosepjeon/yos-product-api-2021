package com.yosep.product.common;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;

@Slf4j
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public abstract class BaseUnitTest {
    @BeforeEach
    public void drawLineByTestBefore() {
        log.info("===================================================== START =====================================================");
    }

    @AfterEach
    public void drawLineByTestAfter(){
        log.info("===================================================== END =====================================================");
    }
}
