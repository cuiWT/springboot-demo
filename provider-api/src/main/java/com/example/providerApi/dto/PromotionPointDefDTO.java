package com.example.providerApi.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class PromotionPointDefDTO implements Serializable {
    private static final long serialVersionUID = 6573135342073474892L;

    private Long id;

    private String name;

    private String userScopeKey;

    private String executeKey;

    private Integer type;

    private Integer status;

    private Date createDate;

    private Date updateDate;
}
