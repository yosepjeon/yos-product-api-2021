package com.yosep.product.jpa.product.data.entity;

import com.yosep.product.jpa.common.entity.BaseEntity;
import com.yosep.product.jpa.product.data.vo.EventId;
import com.yosep.product.jpa.product.data.vo.EventType;
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
//    @Id
//    @Column(length = 100)
//    private String eventId;
//
//    @Enumerated(EnumType.STRING)
//    private EventType eventType;

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

//@Entity
//@Table(name = "yos_coupon_event")
//class CouponEvent (
//        @EmbeddedId
//        private val eventId: EventId
//) :BaseEntity(), Persistable<EventId> {
//
//        override fun getId(): EventId? {
//        return this.eventId
//        }
//
//        override fun isNew(): Boolean {
//        return createdDate == null
//        }
//        }