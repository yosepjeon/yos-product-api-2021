package com.yosep.product.product.data.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@Table(name="yos_product_description_image")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of="id")
@ToString
public class ProductDescriptionImage {
    @Id
    @Column
    private String id;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "description_id")
    private ProductDescription productDescription;

    @Column(name="url",length=300,nullable=false)
    private String url;

    @Column(name="rdate")
    private LocalDateTime rdate;

    @Column(name="udate")
    private LocalDateTime udate;
}
