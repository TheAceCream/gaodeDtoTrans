package com.qf58.ace.approve.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * 只有Id和Name字段的Dto
 *
 * @author: HYC
 * @description:
 * @time: 2018年11月16日
 * @modifytime:
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IdAndNameDto implements Serializable {

    private Long id;

    private String name;
}
