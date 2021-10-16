package com.yosep.product.jpa.product.data.entity;

import com.yosep.product.jpa.common.entity.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "yos_image")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name="dtype")
public abstract class Image extends BaseEntity {
    @Id
    @Column(length=300)
    private String id;

    @Column(name="url",length=300,nullable=false)
    private String url;

    public String getUrl() {
        return url;
    }
}
