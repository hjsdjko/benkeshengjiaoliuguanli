package com.entity.view;

import org.apache.tools.ant.util.DateUtils;
import com.annotation.ColumnInfo;
import com.entity.XuefenjiluEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import com.utils.DateUtil;

/**
* 学分增加记录
* 后端返回视图实体辅助类
* （通常后端关联的表或者自定义的字段需要返回使用）
*/
@TableName("xuefenjilu")
public class XuefenjiluView extends XuefenjiluEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//当前表

	//级联表 教师
		/**
		* 工号
		*/

		@ColumnInfo(comment="工号",type="varchar(200)")
		private String jiaoshiUuidNumber;
		/**
		* 教师姓名
		*/

		@ColumnInfo(comment="教师姓名",type="varchar(200)")
		private String jiaoshiName;
		/**
		* 头像
		*/

		@ColumnInfo(comment="头像",type="varchar(255)")
		private String jiaoshiPhoto;
		/**
		* 联系方式
		*/

		@ColumnInfo(comment="联系方式",type="varchar(200)")
		private String jiaoshiPhone;
		/**
		* 教师类型
		*/
		@ColumnInfo(comment="教师类型",type="int(11)")
		private Integer jiaoshiTypes;
			/**
			* 教师类型的值
			*/
			@ColumnInfo(comment="教师类型的字典表值",type="varchar(200)")
			private String jiaoshiValue;
		/**
		* 邮箱
		*/

		@ColumnInfo(comment="邮箱",type="varchar(200)")
		private String jiaoshiEmail;
		/**
		* 假删
		*/

		@ColumnInfo(comment="假删",type="int(11)")
		private Integer jiaoshiDelete;
	//级联表 学生
		/**
		* 学生名称
		*/

		@ColumnInfo(comment="学生名称",type="varchar(200)")
		private String yonghuName;
		/**
		* 学生手机号
		*/

		@ColumnInfo(comment="学生手机号",type="varchar(200)")
		private String yonghuPhone;
		/**
		* 学生身份证号
		*/

		@ColumnInfo(comment="学生身份证号",type="varchar(200)")
		private String yonghuIdNumber;
		/**
		* 学生头像
		*/

		@ColumnInfo(comment="学生头像",type="varchar(200)")
		private String yonghuPhoto;
		/**
		* 学生邮箱
		*/

		@ColumnInfo(comment="学生邮箱",type="varchar(200)")
		private String yonghuEmail;
		/**
		* 学分
		*/

		@ColumnInfo(comment="学分",type="int(200)")
		private Integer yonghuXuefen;
		/**
		* 逻辑删除
		*/

		@ColumnInfo(comment="逻辑删除",type="int(11)")
		private Integer yonghuDelete;

	//重复字段
			/**
			* 重复字段 的types
			*/
			@ColumnInfo(comment="重复字段 的types",type="Integer")
			private Integer sexTypes;
				@ColumnInfo(comment="重复字段 的值",type="varchar(200)")
				private String sexValue;


	public XuefenjiluView() {

	}

	public XuefenjiluView(XuefenjiluEntity xuefenjiluEntity) {
		try {
			BeanUtils.copyProperties(this, xuefenjiluEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}





	//级联表的get和set 教师

		/**
		* 获取： 工号
		*/
		public String getJiaoshiUuidNumber() {
			return jiaoshiUuidNumber;
		}
		/**
		* 设置： 工号
		*/
		public void setJiaoshiUuidNumber(String jiaoshiUuidNumber) {
			this.jiaoshiUuidNumber = jiaoshiUuidNumber;
		}

		/**
		* 获取： 教师姓名
		*/
		public String getJiaoshiName() {
			return jiaoshiName;
		}
		/**
		* 设置： 教师姓名
		*/
		public void setJiaoshiName(String jiaoshiName) {
			this.jiaoshiName = jiaoshiName;
		}

		/**
		* 获取： 头像
		*/
		public String getJiaoshiPhoto() {
			return jiaoshiPhoto;
		}
		/**
		* 设置： 头像
		*/
		public void setJiaoshiPhoto(String jiaoshiPhoto) {
			this.jiaoshiPhoto = jiaoshiPhoto;
		}

		/**
		* 获取： 联系方式
		*/
		public String getJiaoshiPhone() {
			return jiaoshiPhone;
		}
		/**
		* 设置： 联系方式
		*/
		public void setJiaoshiPhone(String jiaoshiPhone) {
			this.jiaoshiPhone = jiaoshiPhone;
		}
		/**
		* 获取： 教师类型
		*/
		public Integer getJiaoshiTypes() {
			return jiaoshiTypes;
		}
		/**
		* 设置： 教师类型
		*/
		public void setJiaoshiTypes(Integer jiaoshiTypes) {
			this.jiaoshiTypes = jiaoshiTypes;
		}


			/**
			* 获取： 教师类型的值
			*/
			public String getJiaoshiValue() {
				return jiaoshiValue;
			}
			/**
			* 设置： 教师类型的值
			*/
			public void setJiaoshiValue(String jiaoshiValue) {
				this.jiaoshiValue = jiaoshiValue;
			}

		/**
		* 获取： 邮箱
		*/
		public String getJiaoshiEmail() {
			return jiaoshiEmail;
		}
		/**
		* 设置： 邮箱
		*/
		public void setJiaoshiEmail(String jiaoshiEmail) {
			this.jiaoshiEmail = jiaoshiEmail;
		}

		/**
		* 获取： 假删
		*/
		public Integer getJiaoshiDelete() {
			return jiaoshiDelete;
		}
		/**
		* 设置： 假删
		*/
		public void setJiaoshiDelete(Integer jiaoshiDelete) {
			this.jiaoshiDelete = jiaoshiDelete;
		}
	//级联表的get和set 学生

		/**
		* 获取： 学生名称
		*/
		public String getYonghuName() {
			return yonghuName;
		}
		/**
		* 设置： 学生名称
		*/
		public void setYonghuName(String yonghuName) {
			this.yonghuName = yonghuName;
		}

		/**
		* 获取： 学生手机号
		*/
		public String getYonghuPhone() {
			return yonghuPhone;
		}
		/**
		* 设置： 学生手机号
		*/
		public void setYonghuPhone(String yonghuPhone) {
			this.yonghuPhone = yonghuPhone;
		}

		/**
		* 获取： 学生身份证号
		*/
		public String getYonghuIdNumber() {
			return yonghuIdNumber;
		}
		/**
		* 设置： 学生身份证号
		*/
		public void setYonghuIdNumber(String yonghuIdNumber) {
			this.yonghuIdNumber = yonghuIdNumber;
		}

		/**
		* 获取： 学生头像
		*/
		public String getYonghuPhoto() {
			return yonghuPhoto;
		}
		/**
		* 设置： 学生头像
		*/
		public void setYonghuPhoto(String yonghuPhoto) {
			this.yonghuPhoto = yonghuPhoto;
		}

		/**
		* 获取： 学生邮箱
		*/
		public String getYonghuEmail() {
			return yonghuEmail;
		}
		/**
		* 设置： 学生邮箱
		*/
		public void setYonghuEmail(String yonghuEmail) {
			this.yonghuEmail = yonghuEmail;
		}

		/**
		* 获取： 学分
		*/
		public Integer getYonghuXuefen() {
			return yonghuXuefen;
		}
		/**
		* 设置： 学分
		*/
		public void setYonghuXuefen(Integer yonghuXuefen) {
			this.yonghuXuefen = yonghuXuefen;
		}

		/**
		* 获取： 逻辑删除
		*/
		public Integer getYonghuDelete() {
			return yonghuDelete;
		}
		/**
		* 设置： 逻辑删除
		*/
		public void setYonghuDelete(Integer yonghuDelete) {
			this.yonghuDelete = yonghuDelete;
		}

	//重复字段
			/**
			* 获取： 重复字段 的types
			*/
			public Integer getSexTypes() {
			return sexTypes;
			}
			/**
			* 设置： 重复字段 的types
			*/
			public void setSexTypes(Integer sexTypes) {
			this.sexTypes = sexTypes;
			}
				public String getSexValue() {
				return sexValue;
				}
				public void setSexValue(String sexValue) {
				this.sexValue = sexValue;
				}

	@Override
	public String toString() {
		return "XuefenjiluView{" +
			", yonghuName=" + yonghuName +
			", yonghuPhone=" + yonghuPhone +
			", yonghuIdNumber=" + yonghuIdNumber +
			", yonghuPhoto=" + yonghuPhoto +
			", yonghuEmail=" + yonghuEmail +
			", yonghuXuefen=" + yonghuXuefen +
			", yonghuDelete=" + yonghuDelete +
			", jiaoshiUuidNumber=" + jiaoshiUuidNumber +
			", jiaoshiName=" + jiaoshiName +
			", jiaoshiPhoto=" + jiaoshiPhoto +
			", jiaoshiPhone=" + jiaoshiPhone +
			", jiaoshiEmail=" + jiaoshiEmail +
			", jiaoshiDelete=" + jiaoshiDelete +
			"} " + super.toString();
	}
}