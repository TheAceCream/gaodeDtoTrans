package com.qf58.ace.approve.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: weicaijia
 * Date: 2018/11/19 19:08
 * Time: 14:15
 */
@Getter
@Setter
@ToString
public class UserDto implements Serializable {

    private static final long serialVersionUID = -8308297058563146638L;

    private Long userId;

    private String email;

}
