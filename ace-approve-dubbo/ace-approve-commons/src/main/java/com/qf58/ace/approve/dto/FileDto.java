package com.qf58.ace.approve.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created with IntelliJ IDEA. Description: User: weicaijia Date: 2019/1/10 18:25 Time: 14:15
 */
@Getter
@Setter
@ToString
public class FileDto implements Serializable {

    private static final long serialVersionUID = -2549293111578579871L;

    private String url;

    private String name;

    private Long size;

}
