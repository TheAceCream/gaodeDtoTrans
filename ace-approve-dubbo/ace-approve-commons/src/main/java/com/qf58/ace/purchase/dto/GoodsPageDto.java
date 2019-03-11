package com.qf58.ace.purchase.dto;


import java.io.Serializable;
import java.util.List;

/**
 * @author chenboyu
 * @date 2018/11/20
 */
public class GoodsPageDto implements Serializable {

    private String goodsName;

    private Long goodsId;

    private Long brandId;

    private String brandName;

    private String specification;

    private String thumbnailUrl;

    private String picUrl;

    private List<GoodsSkuDto> goodsSkuDtoList;

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public List<GoodsSkuDto> getGoodsSkuDtoList() {
        return goodsSkuDtoList;
    }

    public void setGoodsSkuDtoList(List<GoodsSkuDto> goodsSkuDtoList) {
        this.goodsSkuDtoList = goodsSkuDtoList;
    }
}
