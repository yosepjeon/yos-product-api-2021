package com.yosep.product.jpa.product.data.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

@Getter
@AllArgsConstructor
@ToString
public class EventId implements Serializable {
    private final String eventId;
    @Enumerated(EnumType.STRING)
    private final EventType eventType;
}