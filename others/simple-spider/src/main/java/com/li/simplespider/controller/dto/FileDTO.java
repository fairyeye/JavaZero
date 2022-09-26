package com.li.simplespider.controller.dto;

/**
 * @author huapeng.zhang@going-link.com
 * @time 2022/6/13 16:27
 */

public class FileDTO {

    public FileDTO(String name) {
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
