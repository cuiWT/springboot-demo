package com.example.provider.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name ="crm_promotion_point_def")
public class PromotionPointDef implements Serializable {

    private static final long serialVersionUID = -2358857874887758876L;
    @Id
    @GeneratedValue
    private Long id;

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
