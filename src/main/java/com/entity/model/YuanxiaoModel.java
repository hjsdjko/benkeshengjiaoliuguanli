package com.entity.model;

import com.entity.YuanxiaoEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 院校展示
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class YuanxiaoModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 院校名称
     */
    private String yuanxiaoName;


    /**
     * 展示照片
     */
    private String yuanxiaoPhoto;


    /**
     * 展示内容
     */
    private String yuanxiaoContent;


    /**
     * 逻辑删除
     */
    private Integer yuanxiaoDelete;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 创建时间  show1 show2 photoShow
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
	 * 获取：创建时间  show1 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间  show1 show2 photoShow
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
