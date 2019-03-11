package com.qf58.ace.purchase.dto;


import com.qf58.cate.dto.method.ACateSelectDto;

import java.io.Serializable;
import java.util.List;

/**
 * @author chenboyu
 * @date 2018/11/20
 */

public class CatePageDto implements Serializable {

    private String name;

    private Long id;

    private Boolean isLeafNode;

    private List<ACateSelectDto> children ;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getIsLeafNode() {
        return isLeafNode;
    }

    public void setIsLeafNode(Boolean isLeafNode) {
        this.isLeafNode = isLeafNode;
    }

    public List<ACateSelectDto> getChildren() {
        return children;
    }

    public void setChildren(List<ACateSelectDto> children) {
        this.children = children;
    }
}
