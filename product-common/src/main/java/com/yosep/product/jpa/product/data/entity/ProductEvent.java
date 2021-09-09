package com.yosep.product.jpa.product.data.entity;

import com.yosep.product.jpa.common.entity.BaseEntity;
import com.yosep.product.jpa.product.data.vo.EventType;
import lombok.*;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = "eventId")
@Table(name = "yos_product_event")
public class ProductEvent extends BaseEntity implements Persistable<String> {
    @Id
    @Column(length = 100)
    private String eventId;

    @Enumerated(EnumType.STRING)
    private EventType eventType;

    @Override
    public String getId() {
        return eventId;
    }

    @Override
    public boolean isNew() {
        return this.getCreatedDate() == null;
    }
}
