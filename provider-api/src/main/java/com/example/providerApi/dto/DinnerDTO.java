package com.example.providerApi.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class DinnerDTO implements Serializable {

    private static final long serialVersionUID = -8671238782233084826L;

    private Long id;

    private String userName;

    private Integer number;

    private Integer status;

    private Integer year;

    private Integer month;

    private Integer day;

    private Integer week;

    private Date createDate;

    private Date updateDate;
}
