package com.yosep.product.jpa.product.data.entity;//package com.yosep.product.product.data.entity;
//
//import com.fasterxml.jackson.annotation.JsonBackReference;
//import com.fasterxml.jackson.annotation.JsonManagedReference;
//import com.yosep.product.common.entity.BaseEntity;
//import lombok.*;
//
//import javax.persistence.*;
//import java.util.List;
//
//@Entity
//@Table(name="yos_product_description")
//@Getter
//@Setter
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
//@EqualsAndHashCode(of="id")
//@ToString
//public class ProductDescription extends BaseEntity {
//    @Id
//    @Column(name="product_description_id")
//    private String id;
//
//    @JsonBackReference
//    @ManyToOne(fetch=FetchType.LAZY)
//    @JoinColumn(name="product_id")
//    private Product product;
//
//    @Column(name="description")
//    private String description;
//
//    @JsonManagedReference
//    @OneToMany(mappedBy = "productDescription", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<ProductDescriptionImage> descriptionImages;
//}
