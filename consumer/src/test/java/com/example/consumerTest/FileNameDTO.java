package com.example.consumerTest;

import lombok.Data;

import java.io.Serializable;

@Data
public class FileNameDTO implements Serializable {

    private String fullName;

    private String shotName;

    private String md5;
}
