package com.yosep.product.jpa.product.data.entity;

import com.yosep.product.jpa.common.entity.BaseEntity;
import com.yosep.product.jpa.product.data.vo.EventType;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = "eventId")
@Table(name = "yos_product_event")
public class ProductEvent extends BaseEntity {
    @Id
    @Column(length = 100)
    private String eventId;

    @Enumerated(EnumType.STRING)
    private EventType eventType;
}
