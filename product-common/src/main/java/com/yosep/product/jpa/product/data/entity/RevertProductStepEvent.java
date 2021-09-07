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
@Table(name = "yos_revert_product_step_event")
public class RevertProductStepEvent extends BaseEntity {
    @Id
    @Column(length = 100)
    private String eventId;
}
