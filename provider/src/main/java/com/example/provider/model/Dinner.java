package com.example.provider.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name ="dinner")
public class Dinner implements Serializable {
    private static final long serialVersionUID = -4903664227385227088L;

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private Integer number;

    @Column(nullable = false)
    private Integer status;

    @Column(nullable = false)
    private Integer year;

    @Column(nullable = false)
    private Integer month;

    @Column(nullable = false)
    private Integer day;

    @Column(nullable = false)
    private Integer week;

    private Date createDate;

    private Date updateDate;
}
