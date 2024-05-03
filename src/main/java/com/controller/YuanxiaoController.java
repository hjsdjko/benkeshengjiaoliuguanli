
package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.*;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.*;
import com.entity.view.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;
import com.alibaba.fastjson.*;

/**
 * 院校展示
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/yuanxiao")
public class YuanxiaoController {
    private static final Logger logger = LoggerFactory.getLogger(YuanxiaoController.class);

    private static final String TABLE_NAME = "yuanxiao";

    @Autowired
    private YuanxiaoService yuanxiaoService;


    @Autowired
    private TokenService tokenService;

    @Autowired
    private ChengjiService chengjiService;//学生成绩
    @Autowired
    private DictionaryService dictionaryService;//字典
    @Autowired
    private ForumService forumService;//论坛
    @Autowired
    private HuoodngbaomingService huoodngbaomingService;//活动报名
    @Autowired
    private JiaoshiService jiaoshiService;//教师
    @Autowired
    private KechengService kechengService;//课程信息
    @Autowired
    private NewsService newsService;//公告信息
    @Autowired
    private QingjiaService qingjiaService;//学生请假
    @Autowired
    private XuankeService xuankeService;//选课信息
    @Autowired
    private YonghuService yonghuService;//学生
    @Autowired
    private ZiliaoService ziliaoService;//公共资料
    @Autowired
    private UsersService usersService;//管理员


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永不会进入");
        else if("学生".equals(role))
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        else if("教师".equals(role))
            params.put("jiaoshiId",request.getSession().getAttribute("userId"));
        params.put("yuanxiaoDeleteStart",1);params.put("yuanxiaoDeleteEnd",1);
        CommonUtil.checkMap(params);
        PageUtils page = yuanxiaoService.queryPage(params);

        //字典表数据转换
        List<YuanxiaoView> list =(List<YuanxiaoView>)page.getList();
        for(YuanxiaoView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        YuanxiaoEntity yuanxiao = yuanxiaoService.selectById(id);
        if(yuanxiao !=null){
            //entity转view
            YuanxiaoView view = new YuanxiaoView();
            BeanUtils.copyProperties( yuanxiao , view );//把实体数据重构到view中
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody YuanxiaoEntity yuanxiao, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,yuanxiao:{}",this.getClass().getName(),yuanxiao.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<YuanxiaoEntity> queryWrapper = new EntityWrapper<YuanxiaoEntity>()
            .eq("yuanxiao_name", yuanxiao.getYuanxiaoName())
            .eq("yuanxiao_delete", 1)
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        YuanxiaoEntity yuanxiaoEntity = yuanxiaoService.selectOne(queryWrapper);
        if(yuanxiaoEntity==null){
            yuanxiao.setYuanxiaoDelete(1);
            yuanxiao.setInsertTime(new Date());
            yuanxiao.setCreateTime(new Date());
            yuanxiaoService.insert(yuanxiao);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody YuanxiaoEntity yuanxiao, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,yuanxiao:{}",this.getClass().getName(),yuanxiao.toString());
        YuanxiaoEntity oldYuanxiaoEntity = yuanxiaoService.selectById(yuanxiao.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
        if("".equals(yuanxiao.getYuanxiaoPhoto()) || "null".equals(yuanxiao.getYuanxiaoPhoto())){
                yuanxiao.setYuanxiaoPhoto(null);
        }

            yuanxiaoService.updateById(yuanxiao);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<YuanxiaoEntity> oldYuanxiaoList =yuanxiaoService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        ArrayList<YuanxiaoEntity> list = new ArrayList<>();
        for(Integer id:ids){
            YuanxiaoEntity yuanxiaoEntity = new YuanxiaoEntity();
            yuanxiaoEntity.setId(id);
            yuanxiaoEntity.setYuanxiaoDelete(2);
            list.add(yuanxiaoEntity);
        }
        if(list != null && list.size() >0){
            yuanxiaoService.updateBatchById(list);
        }

        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName, HttpServletRequest request){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        Integer yonghuId = Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId")));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //.eq("time", new SimpleDateFormat("yyyy-MM-dd").format(new Date()))
        try {
            List<YuanxiaoEntity> yuanxiaoList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields= new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("static/upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            YuanxiaoEntity yuanxiaoEntity = new YuanxiaoEntity();
//                            yuanxiaoEntity.setYuanxiaoName(data.get(0));                    //院校名称 要改的
//                            yuanxiaoEntity.setYuanxiaoPhoto("");//详情和图片
//                            yuanxiaoEntity.setYuanxiaoContent("");//详情和图片
//                            yuanxiaoEntity.setYuanxiaoDelete(1);//逻辑删除字段
//                            yuanxiaoEntity.setInsertTime(date);//时间
//                            yuanxiaoEntity.setCreateTime(date);//时间
                            yuanxiaoList.add(yuanxiaoEntity);


                            //把要查询是否重复的字段放入map中
                        }

                        //查询是否重复
                        yuanxiaoService.insertBatch(yuanxiaoList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }




    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        CommonUtil.checkMap(params);
        PageUtils page = yuanxiaoService.queryPage(params);

        //字典表数据转换
        List<YuanxiaoView> list =(List<YuanxiaoView>)page.getList();
        for(YuanxiaoView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        YuanxiaoEntity yuanxiao = yuanxiaoService.selectById(id);
            if(yuanxiao !=null){


                //entity转view
                YuanxiaoView view = new YuanxiaoView();
                BeanUtils.copyProperties( yuanxiao , view );//把实体数据重构到view中

                //修改对应字典表字段
                dictionaryService.dictionaryConvert(view, request);
                return R.ok().put("data", view);
            }else {
                return R.error(511,"查不到数据");
            }
    }


    /**
    * 前端保存
    */
    @RequestMapping("/add")
    public R add(@RequestBody YuanxiaoEntity yuanxiao, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,yuanxiao:{}",this.getClass().getName(),yuanxiao.toString());
        Wrapper<YuanxiaoEntity> queryWrapper = new EntityWrapper<YuanxiaoEntity>()
            .eq("yuanxiao_name", yuanxiao.getYuanxiaoName())
            .eq("yuanxiao_delete", yuanxiao.getYuanxiaoDelete())
//            .notIn("yuanxiao_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        YuanxiaoEntity yuanxiaoEntity = yuanxiaoService.selectOne(queryWrapper);
        if(yuanxiaoEntity==null){
            yuanxiao.setYuanxiaoDelete(1);
            yuanxiao.setInsertTime(new Date());
            yuanxiao.setCreateTime(new Date());
        yuanxiaoService.insert(yuanxiao);

            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

}

