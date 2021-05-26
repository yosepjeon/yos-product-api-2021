package com.yosep.product.product.data.entity;

import com.yosep.product.common.BaseUnitTest;
import com.yosep.product.common.logic.RandomIdGenerator;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Slf4j
public class ProductUnitTest extends BaseUnitTest {
    @Test
    @DisplayName("[Product Unit] 상품 아이디 생성 테스트")
    public void 상품_아이디_생성_테스트() throws Exception {
        log.info("[Product Unit] 상품 아이디 생성 테스트");
        log.info(RandomIdGenerator.createId());
    }
}
