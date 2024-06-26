package com.entity;

import com.annotation.ColumnInfo;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;
import java.io.Serializable;
import java.util.*;
import org.apache.tools.ant.util.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.utils.DateUtil;


/**
 * 活动报名
 *
 * @author 
 * @email
 */
@TableName("huoodngbaoming")
public class HuoodngbaomingEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public HuoodngbaomingEntity() {

	}

	public HuoodngbaomingEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @ColumnInfo(comment="主键",type="int(11)")
    @TableField(value = "id")

    private Integer id;


    /**
     * 报名编号
     */
    @ColumnInfo(comment="报名编号",type="varchar(200)")
    @TableField(value = "huoodngbaoming_uuid_number")

    private String huoodngbaomingUuidNumber;


    /**
     * 用户
     */
    @ColumnInfo(comment="用户",type="int(11)")
    @TableField(value = "yonghu_id")

    private Integer yonghuId;


    /**
     * 报名活动
     */
    @ColumnInfo(comment="报名活动",type="varchar(200)")
    @TableField(value = "huoodngbaoming_name")

    private String huoodngbaomingName;


    /**
     * 活动类型
     */
    @ColumnInfo(comment="活动类型",type="int(200)")
    @TableField(value = "huoodngbaoming_types")

    private Integer huoodngbaomingTypes;


    /**
     * 报名理由
     */
    @ColumnInfo(comment="报名理由",type="longtext")
    @TableField(value = "huoodngbaoming_text")

    private String huoodngbaomingText;


    /**
     * 活动报名时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="活动报名时间",type="timestamp")
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 报名状态
     */
    @ColumnInfo(comment="报名状态",type="int(11)")
    @TableField(value = "huoodngbaoming_yesno_types")

    private Integer huoodngbaomingYesnoTypes;


    /**
     * 审核回复
     */
    @ColumnInfo(comment="审核回复",type="longtext")
    @TableField(value = "huoodngbaoming_yesno_text")

    private String huoodngbaomingYesnoText;


    /**
     * 创建时间  listShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="创建时间",type="timestamp")
    @TableField(value = "create_time",fill = FieldFill.INSERT)

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
	 * 获取：创建时间  listShow
	 */
    public Date getCreateTime() {
        return createTime;
    }
    /**
	 * 设置：创建时间  listShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Huoodngbaoming{" +
            ", id=" + id +
            ", huoodngbaomingUuidNumber=" + huoodngbaomingUuidNumber +
            ", yonghuId=" + yonghuId +
            ", huoodngbaomingName=" + huoodngbaomingName +
            ", huoodngbaomingTypes=" + huoodngbaomingTypes +
            ", huoodngbaomingText=" + huoodngbaomingText +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", huoodngbaomingYesnoTypes=" + huoodngbaomingYesnoTypes +
            ", huoodngbaomingYesnoText=" + huoodngbaomingYesnoText +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
