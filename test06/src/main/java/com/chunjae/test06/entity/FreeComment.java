package com.chunjae.test06.entity;

import lombok.Data;

@Data
public class FreeComment {

    private int cno;
    private int fno;
    private String name;
    private String content;
    private String resdate;
    private int recommend;

}
