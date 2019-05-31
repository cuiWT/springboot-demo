package com.example.providerApi.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class PromotionPointDTO implements Serializable {
    private static final long serialVersionUID = -8673063937364167728L;

    private Long id;

    private Long defId;

    private String name;

    private String userScopeKey;

    private String executeKey;

    private Integer type;

    private Integer status;

    private Date createDate;

    private Date updateDate;
}
