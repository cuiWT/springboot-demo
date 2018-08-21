package com.example.providerApi.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel("点餐")
public class DinnerDTO implements Serializable {

    private static final long serialVersionUID = -8671238782233084826L;

    @ApiModelProperty("点餐的ID")
    private Long id;

    @ApiModelProperty("点餐人姓名")
    private String userName;

    @ApiModelProperty("点餐数量")
    private Integer number;

    @ApiModelProperty("点餐状态。 1：吃鸡 0：b不吃")
    private Integer status;

    @ApiModelProperty("年")
    private Integer year;

    @ApiModelProperty("月")
    private Integer month;

    @ApiModelProperty("日")
    private Integer day;

    @ApiModelProperty("周几， 0,1,2,3,4,5,6")
    private Integer week;

    @ApiModelProperty("创建时间")
    private Date createDate;

    @ApiModelProperty("最后更新时间")
    private Date updateDate;
}
