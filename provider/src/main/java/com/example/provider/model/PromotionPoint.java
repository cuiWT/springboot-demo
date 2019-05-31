package com.example.provider.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name ="crm_promotion_point")
public class PromotionPoint implements Serializable {

    private static final long serialVersionUID = 2894737266493331146L;
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Long defId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String userScopeKey;

    @Column(nullable = false)
    private String executeKey;

    @Column(nullable = false)
    private Integer type;

    @Column(nullable = false)
    private Integer status;

    private Date createDate;

    private Date updateDate;
}
