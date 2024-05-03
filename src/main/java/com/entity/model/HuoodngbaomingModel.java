package com.entity.model;

import com.entity.HuoodngbaomingEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 活动报名
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class HuoodngbaomingModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 报名编号
     */
    private String huoodngbaomingUuidNumber;


    /**
     * 用户
     */
    private Integer yonghuId;


    /**
     * 报名活动
     */
    private String huoodngbaomingName;


    /**
     * 活动类型
     */
    private Integer huoodngbaomingTypes;


    /**
     * 报名理由
     */
    private String huoodngbaomingText;


    /**
     * 活动报名时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 报名状态
     */
    private Integer huoodngbaomingYesnoTypes;


    /**
     * 审核回复
     */
    private String huoodngbaomingYesnoText;


    /**
     * 创建时间 show3 listShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date createTime;


    /**
	 * 获取：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 设置：主键
	 */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 获取：报名编号
	 */
    public String getHuoodngbaomingUuidNumber() {
        return huoodngbaomingUuidNumber;
    }


    /**
	 * 设置：报名编号
	 */
    public void setHuoodngbaomingUuidNumber(String huoodngbaomingUuidNumber) {
        this.huoodngbaomingUuidNumber = huoodngbaomingUuidNumber;
    }
    /**
	 * 获取：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 设置：用户
	 */
    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 获取：报名活动
	 */
    public String getHuoodngbaomingName() {
        return huoodngbaomingName;
    }


    /**
	 * 设置：报名活动
	 */
    public void setHuoodngbaomingName(String huoodngbaomingName) {
        this.huoodngbaomingName = huoodngbaomingName;
    }
    /**
	 * 获取：活动类型
	 */
    public Integer getHuoodngbaomingTypes() {
        return huoodngbaomingTypes;
    }


    /**
	 * 设置：活动类型
	 */
    public void setHuoodngbaomingTypes(Integer huoodngbaomingTypes) {
        this.huoodngbaomingTypes = huoodngbaomingTypes;
    }
    /**
	 * 获取：报名理由
	 */
    public String getHuoodngbaomingText() {
        return huoodngbaomingText;
    }


    /**
	 * 设置：报名理由
	 */
    public void setHuoodngbaomingText(String huoodngbaomingText) {
        this.huoodngbaomingText = huoodngbaomingText;
    }
    /**
	 * 获取：活动报名时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 设置：活动报名时间
	 */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：报名状态
	 */
    public Integer getHuoodngbaomingYesnoTypes() {
        return huoodngbaomingYesnoTypes;
    }


    /**
	 * 设置：报名状态
	 */
    public void setHuoodngbaomingYesnoTypes(Integer huoodngbaomingYesnoTypes) {
        this.huoodngbaomingYesnoTypes = huoodngbaomingYesnoTypes;
    }
    /**
	 * 获取：审核回复
	 */
    public String getHuoodngbaomingYesnoText() {
        return huoodngbaomingYesnoText;
    }


    /**
	 * 设置：审核回复
	 */
    public void setHuoodngbaomingYesnoText(String huoodngbaomingYesnoText) {
        this.huoodngbaomingYesnoText = huoodngbaomingYesnoText;
    }
    /**
	 * 获取：创建时间 show3 listShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间 show3 listShow
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
