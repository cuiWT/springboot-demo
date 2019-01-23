package com.example.consumerTest;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResultDTO implements Serializable {

    /**
     * 工程
     */
    private String project;

    /**
     * 旧项目文件名
     */
    private String oldFileName;

    /**
     * 新项目文件名
     */
    private String newFileName;

    /**
     * 类别 1、新项目中无对应文件
     * 2、新项目中文件变更
     */
    private Integer classify;
}
