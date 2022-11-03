package com.java.zero.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName school
 */
public class School extends BaseEntity implements Serializable {
    public School() {
    }

    public School(String name, String address) {
        this.name = name;
        this.address = address;
    }

    /**
     * 
     */
    private Long id;

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private String address;

    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public Long getId() {
        return id;
    }

    /**
     * 
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 
     */
    public String getName() {
        return name;
    }

    /**
     * 
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     */
    public String getAddress() {
        return address;
    }

    /**
     * 
     */
    public void setAddress(String address) {
        this.address = address;
    }
}