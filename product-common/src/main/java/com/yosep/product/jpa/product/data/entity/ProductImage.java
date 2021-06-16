package com.yosep.product.jpa.product.data.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.yosep.product.jpa.common.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Builder
@Table(name="yos_product_image")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of="id")
@Entity
@ToString
public class ProductImage extends BaseEntity {
    @Id
    @Column(length=300)
    private String id;

//	@Column(name="file_name",length=100,nullable=false)
//	private String fileName;

    @JsonBackReference
    @ManyToOne(fetch= LAZY)
    @JoinColumn(name="product_id")
    private Product product;

    @Column(name="url",length=300,nullable=false)
    private String url;
}
