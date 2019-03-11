package com.qf58.logbd.common.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * Created with IntelliJ IDEA. Description: User: weicaijia Date: 2019/2/19 14:10 Time: 14:15
 */
@Getter
@Setter
@ToString
public class LogSearchDto implements Serializable {

    private static final long serialVersionUID = 7032072627388986599L;

    private Long userId;

    private String userName;

    private Date time;

    private String behavior;

    private String methodName;


}
