package com.yosep.product.jpa.product.data.entity;

import com.yosep.product.jpa.common.entity.BaseEntity;
import com.yosep.product.jpa.product.data.vo.EventId;
import lombok.*;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "eventId")
@Table(name = "yos_product_event")
public class ProductEvent extends BaseEntity implements Persistable<EventId> {

    @EmbeddedId
    private final EventId eventId;

    @Override
    public EventId getId() {
        return eventId;
    }

    @Override
    public boolean isNew() {
        return this.getCreatedDate() == null;
    }
}