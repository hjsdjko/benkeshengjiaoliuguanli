package com.dao;

import com.entity.YuanxiaoEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.YuanxiaoView;

/**
 * 院校展示 Dao 接口
 *
 * @author 
 */
public interface YuanxiaoDao extends BaseMapper<YuanxiaoEntity> {

   List<YuanxiaoView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
