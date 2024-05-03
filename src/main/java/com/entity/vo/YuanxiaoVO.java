package com.entity.vo;

import com.entity.YuanxiaoEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 院校展示
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("yuanxiao")
public class YuanxiaoVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 院校名称
     */

    @TableField(value = "yuanxiao_name")
    private String yuanxiaoName;


    /**
     * 展示照片
     */

    @TableField(value = "yuanxiao_photo")
    private String yuanxiaoPhoto;


    /**
     * 展示内容
     */

    @TableField(value = "yuanxiao_content")
    private String yuanxiaoContent;


    /**
     * 逻辑删除
     */

    @TableField(value = "yuanxiao_delete")
    private Integer yuanxiaoDelete;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 创建时间  show1 show2 photoShow
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
	 * 设置：院校名称
	 */
    public String getYuanxiaoName() {
        return yuanxiaoName;
    }


    /**
	 * 获取：院校名称
	 */

    public void setYuanxiaoName(String yuanxiaoName) {
        this.yuanxiaoName = yuanxiaoName;
    }
    /**
	 * 设置：展示照片
	 */
    public String getYuanxiaoPhoto() {
        return yuanxiaoPhoto;
    }


    /**
	 * 获取：展示照片
	 */

    public void setYuanxiaoPhoto(String yuanxiaoPhoto) {
        this.yuanxiaoPhoto = yuanxiaoPhoto;
    }
    /**
	 * 设置：展示内容
	 */
    public String getYuanxiaoContent() {
        return yuanxiaoContent;
    }


    /**
	 * 获取：展示内容
	 */

    public void setYuanxiaoContent(String yuanxiaoContent) {
        this.yuanxiaoContent = yuanxiaoContent;
    }
    /**
	 * 设置：逻辑删除
	 */
    public Integer getYuanxiaoDelete() {
        return yuanxiaoDelete;
    }


    /**
	 * 获取：逻辑删除
	 */

    public void setYuanxiaoDelete(Integer yuanxiaoDelete) {
        this.yuanxiaoDelete = yuanxiaoDelete;
    }
    /**
	 * 设置：录入时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：录入时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：创建时间  show1 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间  show1 show2 photoShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
