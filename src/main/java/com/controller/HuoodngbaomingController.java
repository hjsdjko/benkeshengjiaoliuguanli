
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
 * 活动报名
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/huoodngbaoming")
public class HuoodngbaomingController {
    private static final Logger logger = LoggerFactory.getLogger(HuoodngbaomingController.class);

    private static final String TABLE_NAME = "huoodngbaoming";

    @Autowired
    private HuoodngbaomingService huoodngbaomingService;


    @Autowired
    private TokenService tokenService;

    @Autowired
    private ChengjiService chengjiService;//学生成绩
    @Autowired
    private DictionaryService dictionaryService;//字典
    @Autowired
    private ForumService forumService;//论坛
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
    private YuanxiaoService yuanxiaoService;//院校展示
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
        CommonUtil.checkMap(params);
        PageUtils page = huoodngbaomingService.queryPage(params);

        //字典表数据转换
        List<HuoodngbaomingView> list =(List<HuoodngbaomingView>)page.getList();
        for(HuoodngbaomingView c:list){
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
        HuoodngbaomingEntity huoodngbaoming = huoodngbaomingService.selectById(id);
        if(huoodngbaoming !=null){
            //entity转view
            HuoodngbaomingView view = new HuoodngbaomingView();
            BeanUtils.copyProperties( huoodngbaoming , view );//把实体数据重构到view中
            //级联表 学生
            //级联表
            YonghuEntity yonghu = yonghuService.selectById(huoodngbaoming.getYonghuId());
            if(yonghu != null){
            BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setYonghuId(yonghu.getId());
            }
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
    public R save(@RequestBody HuoodngbaomingEntity huoodngbaoming, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,huoodngbaoming:{}",this.getClass().getName(),huoodngbaoming.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("学生".equals(role))
            huoodngbaoming.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<HuoodngbaomingEntity> queryWrapper = new EntityWrapper<HuoodngbaomingEntity>()
            .eq("yonghu_id", huoodngbaoming.getYonghuId())
            .eq("huoodngbaoming_name", huoodngbaoming.getHuoodngbaomingName())
            .eq("huoodngbaoming_types", huoodngbaoming.getHuoodngbaomingTypes())
            .in("huoodngbaoming_yesno_types", new Integer[]{1,2})
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        HuoodngbaomingEntity huoodngbaomingEntity = huoodngbaomingService.selectOne(queryWrapper);
        if(huoodngbaomingEntity==null){
            huoodngbaoming.setInsertTime(new Date());
            huoodngbaoming.setHuoodngbaomingYesnoTypes(1);
            huoodngbaoming.setCreateTime(new Date());
            huoodngbaomingService.insert(huoodngbaoming);
            return R.ok();
        }else {
            if(huoodngbaomingEntity.getHuoodngbaomingYesnoTypes()==1)
                return R.error(511,"有相同的待审核的数据");
            else if(huoodngbaomingEntity.getHuoodngbaomingYesnoTypes()==2)
                return R.error(511,"有相同的审核通过的数据");
            else
                return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody HuoodngbaomingEntity huoodngbaoming, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,huoodngbaoming:{}",this.getClass().getName(),huoodngbaoming.toString());
        HuoodngbaomingEntity oldHuoodngbaomingEntity = huoodngbaomingService.selectById(huoodngbaoming.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("学生".equals(role))
//            huoodngbaoming.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

            huoodngbaomingService.updateById(huoodngbaoming);//根据id更新
            return R.ok();
    }


    /**
    * 审核
    */
    @RequestMapping("/shenhe")
    public R shenhe(@RequestBody HuoodngbaomingEntity huoodngbaomingEntity, HttpServletRequest request){
        logger.debug("shenhe方法:,,Controller:{},,huoodngbaomingEntity:{}",this.getClass().getName(),huoodngbaomingEntity.toString());

        HuoodngbaomingEntity oldHuoodngbaoming = huoodngbaomingService.selectById(huoodngbaomingEntity.getId());//查询原先数据

//        if(huoodngbaomingEntity.getHuoodngbaomingYesnoTypes() == 2){//通过
//            huoodngbaomingEntity.setHuoodngbaomingTypes();
//        }else if(huoodngbaomingEntity.getHuoodngbaomingYesnoTypes() == 3){//拒绝
//            huoodngbaomingEntity.setHuoodngbaomingTypes();
//        }
        huoodngbaomingService.updateById(huoodngbaomingEntity);//审核

        return R.ok();
    }

    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<HuoodngbaomingEntity> oldHuoodngbaomingList =huoodngbaomingService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        huoodngbaomingService.deleteBatchIds(Arrays.asList(ids));

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
            List<HuoodngbaomingEntity> huoodngbaomingList = new ArrayList<>();//上传的东西
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
                            HuoodngbaomingEntity huoodngbaomingEntity = new HuoodngbaomingEntity();
//                            huoodngbaomingEntity.setHuoodngbaomingUuidNumber(data.get(0));                    //报名编号 要改的
//                            huoodngbaomingEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            huoodngbaomingEntity.setHuoodngbaomingName(data.get(0));                    //报名活动 要改的
//                            huoodngbaomingEntity.setHuoodngbaomingTypes(Integer.valueOf(data.get(0)));   //活动类型 要改的
//                            huoodngbaomingEntity.setHuoodngbaomingText(data.get(0));                    //报名理由 要改的
//                            huoodngbaomingEntity.setInsertTime(date);//时间
//                            huoodngbaomingEntity.setHuoodngbaomingYesnoTypes(Integer.valueOf(data.get(0)));   //报名状态 要改的
//                            huoodngbaomingEntity.setHuoodngbaomingYesnoText(data.get(0));                    //审核回复 要改的
//                            huoodngbaomingEntity.setCreateTime(date);//时间
                            huoodngbaomingList.add(huoodngbaomingEntity);


                            //把要查询是否重复的字段放入map中
                                //报名编号
                                if(seachFields.containsKey("huoodngbaomingUuidNumber")){
                                    List<String> huoodngbaomingUuidNumber = seachFields.get("huoodngbaomingUuidNumber");
                                    huoodngbaomingUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> huoodngbaomingUuidNumber = new ArrayList<>();
                                    huoodngbaomingUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("huoodngbaomingUuidNumber",huoodngbaomingUuidNumber);
                                }
                        }

                        //查询是否重复
                         //报名编号
                        List<HuoodngbaomingEntity> huoodngbaomingEntities_huoodngbaomingUuidNumber = huoodngbaomingService.selectList(new EntityWrapper<HuoodngbaomingEntity>().in("huoodngbaoming_uuid_number", seachFields.get("huoodngbaomingUuidNumber")));
                        if(huoodngbaomingEntities_huoodngbaomingUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(HuoodngbaomingEntity s:huoodngbaomingEntities_huoodngbaomingUuidNumber){
                                repeatFields.add(s.getHuoodngbaomingUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [报名编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        huoodngbaomingService.insertBatch(huoodngbaomingList);
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
        PageUtils page = huoodngbaomingService.queryPage(params);

        //字典表数据转换
        List<HuoodngbaomingView> list =(List<HuoodngbaomingView>)page.getList();
        for(HuoodngbaomingView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        HuoodngbaomingEntity huoodngbaoming = huoodngbaomingService.selectById(id);
            if(huoodngbaoming !=null){


                //entity转view
                HuoodngbaomingView view = new HuoodngbaomingView();
                BeanUtils.copyProperties( huoodngbaoming , view );//把实体数据重构到view中

                //级联表
                    YonghuEntity yonghu = yonghuService.selectById(huoodngbaoming.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
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
    public R add(@RequestBody HuoodngbaomingEntity huoodngbaoming, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,huoodngbaoming:{}",this.getClass().getName(),huoodngbaoming.toString());
        Wrapper<HuoodngbaomingEntity> queryWrapper = new EntityWrapper<HuoodngbaomingEntity>()
            .eq("huoodngbaoming_uuid_number", huoodngbaoming.getHuoodngbaomingUuidNumber())
            .eq("yonghu_id", huoodngbaoming.getYonghuId())
            .eq("huoodngbaoming_name", huoodngbaoming.getHuoodngbaomingName())
            .eq("huoodngbaoming_types", huoodngbaoming.getHuoodngbaomingTypes())
            .eq("huoodngbaoming_text", huoodngbaoming.getHuoodngbaomingText())
            .in("huoodngbaoming_yesno_types", new Integer[]{1,2})
            .eq("huoodngbaoming_yesno_text", huoodngbaoming.getHuoodngbaomingYesnoText())
//            .notIn("huoodngbaoming_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        HuoodngbaomingEntity huoodngbaomingEntity = huoodngbaomingService.selectOne(queryWrapper);
        if(huoodngbaomingEntity==null){
            huoodngbaoming.setInsertTime(new Date());
            huoodngbaoming.setHuoodngbaomingYesnoTypes(1);
            huoodngbaoming.setCreateTime(new Date());
        huoodngbaomingService.insert(huoodngbaoming);

            return R.ok();
        }else {
            if(huoodngbaomingEntity.getHuoodngbaomingYesnoTypes()==1)
                return R.error(511,"有相同的待审核的数据");
            else if(huoodngbaomingEntity.getHuoodngbaomingYesnoTypes()==2)
                return R.error(511,"有相同的审核通过的数据");
            else
                return R.error(511,"表中有相同数据");
        }
    }

}

