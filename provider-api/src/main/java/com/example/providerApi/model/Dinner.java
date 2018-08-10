package com.example.providerApi.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Dinner implements Serializable {
    private static final long serialVersionUID = -4903664227385227088L;

    private Long id;

    private String userName;

    private Integer member;

    private Integer status;

    private Integer year;

    private Integer month;

    private Integer day;

    private Integer week;

    private Date createDate;

    private Date updateDate;
}
