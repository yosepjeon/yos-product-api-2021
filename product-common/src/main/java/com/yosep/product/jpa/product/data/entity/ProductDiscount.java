package com.yosep.product.jpa.product.data.entity;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
public class ProductDiscount {
    private int discountAmount = 0;
    private int discountPercent = 0;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public long calculateProductPrice(final long productPrice) {
        long value = productPrice;

        value = calculateAmount(value);
        value = calculatePercent(value);

        return value;
    }

    private long calculateAmount(long value) {
        return value < discountAmount ? 0 : (value - discountAmount);
    }

    private long calculatePercent(long value) {
        return discountPercent <= 0 ? value : (value / discountPercent / 100);
    }
}
