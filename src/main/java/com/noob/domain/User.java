package com.noob.domain;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    private Long createDate;
    private Long modifyDate;


}
