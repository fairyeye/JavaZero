package com.li.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class SysMenu implements Serializable {
    private Integer id;
    private Integer pid;
    private Integer type;
    private String name;
    private String code;
}
