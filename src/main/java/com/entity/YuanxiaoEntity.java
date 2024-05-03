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
 * 院校展示
 *
 * @author 
 * @email
 */
@TableName("yuanxiao")
public class YuanxiaoEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public YuanxiaoEntity() {

	}

	public YuanxiaoEntity(T t) {
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
     * 院校名称
     */
    @ColumnInfo(comment="院校名称",type="varchar(200)")
    @TableField(value = "yuanxiao_name")

    private String yuanxiaoName;


    /**
     * 展示照片
     */
    @ColumnInfo(comment="展示照片",type="varchar(200)")
    @TableField(value = "yuanxiao_photo")

    private String yuanxiaoPhoto;


    /**
     * 展示内容
     */
    @ColumnInfo(comment="展示内容",type="longtext")
    @TableField(value = "yuanxiao_content")

    private String yuanxiaoContent;


    /**
     * 逻辑删除
     */
    @ColumnInfo(comment="逻辑删除",type="int(11)")
    @TableField(value = "yuanxiao_delete")

    private Integer yuanxiaoDelete;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="录入时间",type="timestamp")
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 创建时间
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
	 * 获取：院校名称
	 */
    public String getYuanxiaoName() {
        return yuanxiaoName;
    }
    /**
	 * 设置：院校名称
	 */

    public void setYuanxiaoName(String yuanxiaoName) {
        this.yuanxiaoName = yuanxiaoName;
    }
    /**
	 * 获取：展示照片
	 */
    public String getYuanxiaoPhoto() {
        return yuanxiaoPhoto;
    }
    /**
	 * 设置：展示照片
	 */

    public void setYuanxiaoPhoto(String yuanxiaoPhoto) {
        this.yuanxiaoPhoto = yuanxiaoPhoto;
    }
    /**
	 * 获取：展示内容
	 */
    public String getYuanxiaoContent() {
        return yuanxiaoContent;
    }
    /**
	 * 设置：展示内容
	 */

    public void setYuanxiaoContent(String yuanxiaoContent) {
        this.yuanxiaoContent = yuanxiaoContent;
    }
    /**
	 * 获取：逻辑删除
	 */
    public Integer getYuanxiaoDelete() {
        return yuanxiaoDelete;
    }
    /**
	 * 设置：逻辑删除
	 */

    public void setYuanxiaoDelete(Integer yuanxiaoDelete) {
        this.yuanxiaoDelete = yuanxiaoDelete;
    }
    /**
	 * 获取：录入时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }
    /**
	 * 设置：录入时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }
    /**
	 * 设置：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Yuanxiao{" +
            ", id=" + id +
            ", yuanxiaoName=" + yuanxiaoName +
            ", yuanxiaoPhoto=" + yuanxiaoPhoto +
            ", yuanxiaoContent=" + yuanxiaoContent +
            ", yuanxiaoDelete=" + yuanxiaoDelete +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
