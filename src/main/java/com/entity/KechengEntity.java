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
 * 课程信息
 *
 * @author 
 * @email
 */
@TableName("kecheng")
public class KechengEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public KechengEntity() {

	}

	public KechengEntity(T t) {
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
    @ColumnInfo(comment="主键",type="int(20)")
    @TableField(value = "id")

    private Integer id;


    /**
     * 课程编号
     */
    @ColumnInfo(comment="课程编号",type="varchar(200)")
    @TableField(value = "kecheng_uuid_number")

    private String kechengUuidNumber;


    /**
     * 课程名称
     */
    @ColumnInfo(comment="课程名称",type="varchar(200)")
    @TableField(value = "kecheng_name")

    private String kechengName;


    /**
     * 课程类型
     */
    @ColumnInfo(comment="课程类型",type="int(200)")
    @TableField(value = "kecheng_types")

    private Integer kechengTypes;


    /**
     * 课程封面
     */
    @ColumnInfo(comment="课程封面",type="varchar(200)")
    @TableField(value = "kecheng_photo")

    private String kechengPhoto;


    /**
     * 课程时间/分钟
     */
    @ColumnInfo(comment="课程时间/分钟",type="int(200)")
    @TableField(value = "kecheng_shijian")

    private Integer kechengShijian;


    /**
     * 负责教师
     */
    @ColumnInfo(comment="负责教师",type="int(200)")
    @TableField(value = "jiaoshi_id")

    private Integer jiaoshiId;


    /**
     * 学分
     */
    @ColumnInfo(comment="学分",type="int(200)")
    @TableField(value = "xuefen_numb")

    private Integer xuefenNumb;


    /**
     * 课程详情
     */
    @ColumnInfo(comment="课程详情",type="text")
    @TableField(value = "kecheng_content")

    private String kechengContent;


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
	 * 获取：课程编号
	 */
    public String getKechengUuidNumber() {
        return kechengUuidNumber;
    }
    /**
	 * 设置：课程编号
	 */

    public void setKechengUuidNumber(String kechengUuidNumber) {
        this.kechengUuidNumber = kechengUuidNumber;
    }
    /**
	 * 获取：课程名称
	 */
    public String getKechengName() {
        return kechengName;
    }
    /**
	 * 设置：课程名称
	 */

    public void setKechengName(String kechengName) {
        this.kechengName = kechengName;
    }
    /**
	 * 获取：课程类型
	 */
    public Integer getKechengTypes() {
        return kechengTypes;
    }
    /**
	 * 设置：课程类型
	 */

    public void setKechengTypes(Integer kechengTypes) {
        this.kechengTypes = kechengTypes;
    }
    /**
	 * 获取：课程封面
	 */
    public String getKechengPhoto() {
        return kechengPhoto;
    }
    /**
	 * 设置：课程封面
	 */

    public void setKechengPhoto(String kechengPhoto) {
        this.kechengPhoto = kechengPhoto;
    }
    /**
	 * 获取：课程时间/分钟
	 */
    public Integer getKechengShijian() {
        return kechengShijian;
    }
    /**
	 * 设置：课程时间/分钟
	 */

    public void setKechengShijian(Integer kechengShijian) {
        this.kechengShijian = kechengShijian;
    }
    /**
	 * 获取：负责教师
	 */
    public Integer getJiaoshiId() {
        return jiaoshiId;
    }
    /**
	 * 设置：负责教师
	 */

    public void setJiaoshiId(Integer jiaoshiId) {
        this.jiaoshiId = jiaoshiId;
    }
    /**
	 * 获取：学分
	 */
    public Integer getXuefenNumb() {
        return xuefenNumb;
    }
    /**
	 * 设置：学分
	 */

    public void setXuefenNumb(Integer xuefenNumb) {
        this.xuefenNumb = xuefenNumb;
    }
    /**
	 * 获取：课程详情
	 */
    public String getKechengContent() {
        return kechengContent;
    }
    /**
	 * 设置：课程详情
	 */

    public void setKechengContent(String kechengContent) {
        this.kechengContent = kechengContent;
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
        return "Kecheng{" +
            ", id=" + id +
            ", kechengUuidNumber=" + kechengUuidNumber +
            ", kechengName=" + kechengName +
            ", kechengTypes=" + kechengTypes +
            ", kechengPhoto=" + kechengPhoto +
            ", kechengShijian=" + kechengShijian +
            ", jiaoshiId=" + jiaoshiId +
            ", xuefenNumb=" + xuefenNumb +
            ", kechengContent=" + kechengContent +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
