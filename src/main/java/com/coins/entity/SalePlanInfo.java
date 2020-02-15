package com.coins.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "sale_plan_info")
public class SalePlanInfo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 序号
     */
    @Column(name = "serial_number")
    private Integer serialNumber;

    @Column(name = "corp_id")
    private Integer corpId;

    @Column(name = "corp_code")
    private String corpCode;

    @Column(name = "corp_name")
    private String corpName;

    @Column(name = "dish_id")
    private Integer dishId;

    @Column(name = "item_id")
    private String itemId;

    @Column(name = "dish_name")
    private String dishName;

    @Column(name = "dish_code")
    private String dishCode;

    /**
     * 品类
     */
    @Column(name = "dish_class_id")
    private String dishClassId;

    /**
     * 售卖价格
     */
    private Double price;

    /**
     * 会员价
     */
    @Column(name = "vip_price")
    private Double vipPrice;

    /**
     * 是否推荐
     */
    @Column(name = "is_recommend")
    private Integer isRecommend;

    /**
     * 售卖状态
     */
    @Column(name = "`status`")
    private Integer status;

    @Column(name = "creator_id")
    private Integer creatorId;

    @Column(name = "creator_name")
    private String creatorName;

    @Column(name = "created_time")
    private Date createdTime;

    @Column(name = "modifier_id")
    private Integer modifierId;

    @Column(name = "modifier_name")
    private String modifierName;

    @Column(name = "modified_time")
    private Date modifiedTime;

    private static final long serialVersionUID = 1L;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取序号
     *
     * @return serial_number - 序号
     */
    public Integer getSerialNumber() {
        return serialNumber;
    }

    /**
     * 设置序号
     *
     * @param serialNumber 序号
     */
    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
    }

    /**
     * @return corp_id
     */
    public Integer getCorpId() {
        return corpId;
    }

    /**
     * @param corpId
     */
    public void setCorpId(Integer corpId) {
        this.corpId = corpId;
    }

    /**
     * @return corp_code
     */
    public String getCorpCode() {
        return corpCode;
    }

    /**
     * @param corpCode
     */
    public void setCorpCode(String corpCode) {
        this.corpCode = corpCode;
    }

    /**
     * @return corp_name
     */
    public String getCorpName() {
        return corpName;
    }

    /**
     * @param corpName
     */
    public void setCorpName(String corpName) {
        this.corpName = corpName;
    }

    /**
     * @return dish_id
     */
    public Integer getDishId() {
        return dishId;
    }

    /**
     * @param dishId
     */
    public void setDishId(Integer dishId) {
        this.dishId = dishId;
    }

    /**
     * @return item_id
     */
    public String getItemId() {
        return itemId;
    }

    /**
     * @param itemId
     */
    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    /**
     * @return dish_name
     */
    public String getDishName() {
        return dishName;
    }

    /**
     * @param dishName
     */
    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    /**
     * @return dish_code
     */
    public String getDishCode() {
        return dishCode;
    }

    /**
     * @param dishCode
     */
    public void setDishCode(String dishCode) {
        this.dishCode = dishCode;
    }

    /**
     * 获取品类
     *
     * @return dish_class_id - 品类
     */
    public String getDishClassId() {
        return dishClassId;
    }

    /**
     * 设置品类
     *
     * @param dishClassId 品类
     */
    public void setDishClassId(String dishClassId) {
        this.dishClassId = dishClassId;
    }

    /**
     * 获取售卖价格
     *
     * @return price - 售卖价格
     */
    public Double getPrice() {
        return price;
    }

    /**
     * 设置售卖价格
     *
     * @param price 售卖价格
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * 获取会员价
     *
     * @return vip_price - 会员价
     */
    public Double getVipPrice() {
        return vipPrice;
    }

    /**
     * 设置会员价
     *
     * @param vipPrice 会员价
     */
    public void setVipPrice(Double vipPrice) {
        this.vipPrice = vipPrice;
    }

    /**
     * 获取是否推荐
     *
     * @return is_recommend - 是否推荐
     */
    public Integer getIsRecommend() {
        return isRecommend;
    }

    /**
     * 设置是否推荐
     *
     * @param isRecommend 是否推荐
     */
    public void setIsRecommend(Integer isRecommend) {
        this.isRecommend = isRecommend;
    }

    /**
     * 获取售卖状态
     *
     * @return status - 售卖状态
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置售卖状态
     *
     * @param status 售卖状态
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return creator_id
     */
    public Integer getCreatorId() {
        return creatorId;
    }

    /**
     * @param creatorId
     */
    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    /**
     * @return creator_name
     */
    public String getCreatorName() {
        return creatorName;
    }

    /**
     * @param creatorName
     */
    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    /**
     * @return created_time
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     * @param createdTime
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * @return modifier_id
     */
    public Integer getModifierId() {
        return modifierId;
    }

    /**
     * @param modifierId
     */
    public void setModifierId(Integer modifierId) {
        this.modifierId = modifierId;
    }

    /**
     * @return modifier_name
     */
    public String getModifierName() {
        return modifierName;
    }

    /**
     * @param modifierName
     */
    public void setModifierName(String modifierName) {
        this.modifierName = modifierName;
    }

    /**
     * @return modified_time
     */
    public Date getModifiedTime() {
        return modifiedTime;
    }

    /**
     * @param modifiedTime
     */
    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }
}