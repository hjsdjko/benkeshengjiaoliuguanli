package com.entity.vo;

import com.entity.HuoodngbaomingEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 活动报名
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("huoodngbaoming")
public class HuoodngbaomingVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 报名编号
     */

    @TableField(value = "huoodngbaoming_uuid_number")
    private String huoodngbaomingUuidNumber;


    /**
     * 用户
     */

    @TableField(value = "yonghu_id")
    private Integer yonghuId;


    /**
     * 报名活动
     */

    @TableField(value = "huoodngbaoming_name")
    private String huoodngbaomingName;


    /**
     * 活动类型
     */

    @TableField(value = "huoodngbaoming_types")
    private Integer huoodngbaomingTypes;


    /**
     * 报名理由
     */

    @TableField(value = "huoodngbaoming_text")
    private String huoodngbaomingText;


    /**
     * 活动报名时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 报名状态
     */

    @TableField(value = "huoodngbaoming_yesno_types")
    private Integer huoodngbaomingYesnoTypes;


    /**
     * 审核回复
     */

    @TableField(value = "huoodngbaoming_yesno_text")
    private String huoodngbaomingYesnoText;


    /**
     * 创建时间 show3 listShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "create_time")
    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：报名编号
	 */
    public String getHuoodngbaomingUuidNumber() {
        return huoodngbaomingUuidNumber;
    }


    /**
	 * 获取：报名编号
	 */

    public void setHuoodngbaomingUuidNumber(String huoodngbaomingUuidNumber) {
        this.huoodngbaomingUuidNumber = huoodngbaomingUuidNumber;
    }
    /**
	 * 设置：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 获取：用户
	 */

    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 设置：报名活动
	 */
    public String getHuoodngbaomingName() {
        return huoodngbaomingName;
    }


    /**
	 * 获取：报名活动
	 */

    public void setHuoodngbaomingName(String huoodngbaomingName) {
        this.huoodngbaomingName = huoodngbaomingName;
    }
    /**
	 * 设置：活动类型
	 */
    public Integer getHuoodngbaomingTypes() {
        return huoodngbaomingTypes;
    }


    /**
	 * 获取：活动类型
	 */

    public void setHuoodngbaomingTypes(Integer huoodngbaomingTypes) {
        this.huoodngbaomingTypes = huoodngbaomingTypes;
    }
    /**
	 * 设置：报名理由
	 */
    public String getHuoodngbaomingText() {
        return huoodngbaomingText;
    }


    /**
	 * 获取：报名理由
	 */

    public void setHuoodngbaomingText(String huoodngbaomingText) {
        this.huoodngbaomingText = huoodngbaomingText;
    }
    /**
	 * 设置：活动报名时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：活动报名时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：报名状态
	 */
    public Integer getHuoodngbaomingYesnoTypes() {
        return huoodngbaomingYesnoTypes;
    }


    /**
	 * 获取：报名状态
	 */

    public void setHuoodngbaomingYesnoTypes(Integer huoodngbaomingYesnoTypes) {
        this.huoodngbaomingYesnoTypes = huoodngbaomingYesnoTypes;
    }
    /**
	 * 设置：审核回复
	 */
    public String getHuoodngbaomingYesnoText() {
        return huoodngbaomingYesnoText;
    }


    /**
	 * 获取：审核回复
	 */

    public void setHuoodngbaomingYesnoText(String huoodngbaomingYesnoText) {
        this.huoodngbaomingYesnoText = huoodngbaomingYesnoText;
    }
    /**
	 * 设置：创建时间 show3 listShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间 show3 listShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
