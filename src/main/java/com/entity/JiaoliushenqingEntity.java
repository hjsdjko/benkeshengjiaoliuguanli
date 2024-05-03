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
 * 交流申请
 *
 * @author 
 * @email
 */
@TableName("jiaoliushenqing")
public class JiaoliushenqingEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public JiaoliushenqingEntity() {

	}

	public JiaoliushenqingEntity(T t) {
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
     * 用户
     */
    @ColumnInfo(comment="用户",type="int(11)")
    @TableField(value = "yonghu_id")

    private Integer yonghuId;


    /**
     * 标题
     */
    @ColumnInfo(comment="标题",type="varchar(200)")
    @TableField(value = "jiaoliushenqing_name")

    private String jiaoliushenqingName;


    /**
     * 获奖经历
     */
    @ColumnInfo(comment="获奖经历",type="varchar(200)")
    @TableField(value = "jiaoliushenqing_huojiang")

    private String jiaoliushenqingHuojiang;


    /**
     * 活动竞赛经历
     */
    @ColumnInfo(comment="活动竞赛经历",type="varchar(200)")
    @TableField(value = "jiaoliushenqing_jingsai")

    private String jiaoliushenqingJingsai;


    /**
     * 附件
     */
    @ColumnInfo(comment="附件",type="varchar(200)")
    @TableField(value = "jiaoliushenqing_file")

    private String jiaoliushenqingFile;


    /**
     * 详情信息
     */
    @ColumnInfo(comment="详情信息",type="longtext")
    @TableField(value = "jiaoliushenqing_text")

    private String jiaoliushenqingText;


    /**
     * 申请状态
     */
    @ColumnInfo(comment="申请状态",type="int(11)")
    @TableField(value = "jiaoliushenqing_yesno_types")

    private Integer jiaoliushenqingYesnoTypes;


    /**
     * 审核回复
     */
    @ColumnInfo(comment="审核回复",type="longtext")
    @TableField(value = "jiaoliushenqing_yesno_text")

    private String jiaoliushenqingYesnoText;


    /**
     * 申请时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="申请时间",type="timestamp")
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


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
	 * 获取：标题
	 */
    public String getJiaoliushenqingName() {
        return jiaoliushenqingName;
    }
    /**
	 * 设置：标题
	 */

    public void setJiaoliushenqingName(String jiaoliushenqingName) {
        this.jiaoliushenqingName = jiaoliushenqingName;
    }
    /**
	 * 获取：获奖经历
	 */
    public String getJiaoliushenqingHuojiang() {
        return jiaoliushenqingHuojiang;
    }
    /**
	 * 设置：获奖经历
	 */

    public void setJiaoliushenqingHuojiang(String jiaoliushenqingHuojiang) {
        this.jiaoliushenqingHuojiang = jiaoliushenqingHuojiang;
    }
    /**
	 * 获取：活动竞赛经历
	 */
    public String getJiaoliushenqingJingsai() {
        return jiaoliushenqingJingsai;
    }
    /**
	 * 设置：活动竞赛经历
	 */

    public void setJiaoliushenqingJingsai(String jiaoliushenqingJingsai) {
        this.jiaoliushenqingJingsai = jiaoliushenqingJingsai;
    }
    /**
	 * 获取：附件
	 */
    public String getJiaoliushenqingFile() {
        return jiaoliushenqingFile;
    }
    /**
	 * 设置：附件
	 */

    public void setJiaoliushenqingFile(String jiaoliushenqingFile) {
        this.jiaoliushenqingFile = jiaoliushenqingFile;
    }
    /**
	 * 获取：详情信息
	 */
    public String getJiaoliushenqingText() {
        return jiaoliushenqingText;
    }
    /**
	 * 设置：详情信息
	 */

    public void setJiaoliushenqingText(String jiaoliushenqingText) {
        this.jiaoliushenqingText = jiaoliushenqingText;
    }
    /**
	 * 获取：申请状态
	 */
    public Integer getJiaoliushenqingYesnoTypes() {
        return jiaoliushenqingYesnoTypes;
    }
    /**
	 * 设置：申请状态
	 */

    public void setJiaoliushenqingYesnoTypes(Integer jiaoliushenqingYesnoTypes) {
        this.jiaoliushenqingYesnoTypes = jiaoliushenqingYesnoTypes;
    }
    /**
	 * 获取：审核回复
	 */
    public String getJiaoliushenqingYesnoText() {
        return jiaoliushenqingYesnoText;
    }
    /**
	 * 设置：审核回复
	 */

    public void setJiaoliushenqingYesnoText(String jiaoliushenqingYesnoText) {
        this.jiaoliushenqingYesnoText = jiaoliushenqingYesnoText;
    }
    /**
	 * 获取：申请时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }
    /**
	 * 设置：申请时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
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
        return "Jiaoliushenqing{" +
            ", id=" + id +
            ", yonghuId=" + yonghuId +
            ", jiaoliushenqingName=" + jiaoliushenqingName +
            ", jiaoliushenqingHuojiang=" + jiaoliushenqingHuojiang +
            ", jiaoliushenqingJingsai=" + jiaoliushenqingJingsai +
            ", jiaoliushenqingFile=" + jiaoliushenqingFile +
            ", jiaoliushenqingText=" + jiaoliushenqingText +
            ", jiaoliushenqingYesnoTypes=" + jiaoliushenqingYesnoTypes +
            ", jiaoliushenqingYesnoText=" + jiaoliushenqingYesnoText +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
