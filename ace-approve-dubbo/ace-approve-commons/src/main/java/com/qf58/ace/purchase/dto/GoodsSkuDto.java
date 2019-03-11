package com.qf58.ace.purchase.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: zhubo
 * Date: 2018-04-04
 * Time: 11:41
 */
public class GoodsSkuDto implements Serializable {

    private Long id;

    private Long goodsId;
    // {"1":"颜色","304a6d3d6b6b402a8f3dcb74beb75302":"鞋号"}
    private String specName;
    // {"5eb52acf18fc4b589594d279ca70bc4e":"黑色","767657a5a07f4dd1af701b9992ee57fe":"36"}
    private String specValue;
    // 商品价钱
    private Long marketPrice;
    // 平台采购价
    private Long platformPrice;
    // 平台最低售价
    private Long platformLowercasePrice;

    private Integer goodsStorage;

    private Integer saleNum;

    private Byte isDel;

    private Date createTime;

    private Date updateTime;

    private Double contractPrice;

    private List<SpecNameAndValue> specNameAndValueList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    public String getSpecValue() {
        return specValue;
    }

    public void setSpecValue(String specValue) {
        this.specValue = specValue;
    }

    public Long getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(Long marketPrice) {
        this.marketPrice = marketPrice;
    }

    public Long getPlatformPrice() {
        return platformPrice;
    }

    public void setPlatformPrice(Long platformPrice) {
        this.platformPrice = platformPrice;
    }

    public Long getPlatformLowercasePrice() {
        return platformLowercasePrice;
    }

    public void setPlatformLowercasePrice(Long platformLowercasePrice) {
        this.platformLowercasePrice = platformLowercasePrice;
    }

    public Integer getGoodsStorage() {
        return goodsStorage;
    }

    public void setGoodsStorage(Integer goodsStorage) {
        this.goodsStorage = goodsStorage;
    }

    public Integer getSaleNum() {
        return saleNum;
    }

    public void setSaleNum(Integer saleNum) {
        this.saleNum = saleNum;
    }

    public Byte getIsDel() {
        return isDel;
    }

    public void setIsDel(Byte isDel) {
        this.isDel = isDel;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Double getContractPrice() {
        return contractPrice;
    }

    public void setContractPrice(Double contractPrice) {
        this.contractPrice = contractPrice;
    }

    public List<SpecNameAndValue> getSpecNameAndValueList() {
        return specNameAndValueList;
    }

    public void setSpecNameAndValueList(List<SpecNameAndValue> specNameAndValueList) {
        this.specNameAndValueList = specNameAndValueList;
    }
}
