
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
 * 交流申请
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/jiaoliushenqing")
public class JiaoliushenqingController {
    private static final Logger logger = LoggerFactory.getLogger(JiaoliushenqingController.class);

    private static final String TABLE_NAME = "jiaoliushenqing";

    @Autowired
    private JiaoliushenqingService jiaoliushenqingService;


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
    private XuefenjiluService xuefenjiluService;//学分增加记录
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
        PageUtils page = jiaoliushenqingService.queryPage(params);

        //字典表数据转换
        List<JiaoliushenqingView> list =(List<JiaoliushenqingView>)page.getList();
        for(JiaoliushenqingView c:list){
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
        JiaoliushenqingEntity jiaoliushenqing = jiaoliushenqingService.selectById(id);
        if(jiaoliushenqing !=null){
            //entity转view
            JiaoliushenqingView view = new JiaoliushenqingView();
            BeanUtils.copyProperties( jiaoliushenqing , view );//把实体数据重构到view中
            //级联表 学生
            //级联表
            YonghuEntity yonghu = yonghuService.selectById(jiaoliushenqing.getYonghuId());
            if(yonghu != null){
            BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "username", "password", "newMoney", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
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
    public R save(@RequestBody JiaoliushenqingEntity jiaoliushenqing, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,jiaoliushenqing:{}",this.getClass().getName(),jiaoliushenqing.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("学生".equals(role))
            jiaoliushenqing.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<JiaoliushenqingEntity> queryWrapper = new EntityWrapper<JiaoliushenqingEntity>()
            .eq("yonghu_id", jiaoliushenqing.getYonghuId())
            .eq("jiaoliushenqing_name", jiaoliushenqing.getJiaoliushenqingName())
            .eq("jiaoliushenqing_huojiang", jiaoliushenqing.getJiaoliushenqingHuojiang())
            .eq("jiaoliushenqing_jingsai", jiaoliushenqing.getJiaoliushenqingJingsai())
            .in("jiaoliushenqing_yesno_types", new Integer[]{1,2})
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        JiaoliushenqingEntity jiaoliushenqingEntity = jiaoliushenqingService.selectOne(queryWrapper);
        if(jiaoliushenqingEntity==null){
            jiaoliushenqing.setJiaoliushenqingYesnoTypes(1);
            jiaoliushenqing.setInsertTime(new Date());
            jiaoliushenqing.setCreateTime(new Date());
            jiaoliushenqingService.insert(jiaoliushenqing);
            return R.ok();
        }else {
            if(jiaoliushenqingEntity.getJiaoliushenqingYesnoTypes()==1)
                return R.error(511,"有相同的待审核的数据");
            else if(jiaoliushenqingEntity.getJiaoliushenqingYesnoTypes()==2)
                return R.error(511,"有相同的审核通过的数据");
            else
                return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody JiaoliushenqingEntity jiaoliushenqing, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,jiaoliushenqing:{}",this.getClass().getName(),jiaoliushenqing.toString());
        JiaoliushenqingEntity oldJiaoliushenqingEntity = jiaoliushenqingService.selectById(jiaoliushenqing.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("学生".equals(role))
//            jiaoliushenqing.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        if("".equals(jiaoliushenqing.getJiaoliushenqingFile()) || "null".equals(jiaoliushenqing.getJiaoliushenqingFile())){
                jiaoliushenqing.setJiaoliushenqingFile(null);
        }
        if("".equals(jiaoliushenqing.getJiaoliushenqingText()) || "null".equals(jiaoliushenqing.getJiaoliushenqingText())){
                jiaoliushenqing.setJiaoliushenqingText(null);
        }
        if("".equals(jiaoliushenqing.getJiaoliushenqingYesnoText()) || "null".equals(jiaoliushenqing.getJiaoliushenqingYesnoText())){
                jiaoliushenqing.setJiaoliushenqingYesnoText(null);
        }

            jiaoliushenqingService.updateById(jiaoliushenqing);//根据id更新
            return R.ok();
    }


    /**
    * 审核
    */
    @RequestMapping("/shenhe")
    public R shenhe(@RequestBody JiaoliushenqingEntity jiaoliushenqingEntity, HttpServletRequest request){
        logger.debug("shenhe方法:,,Controller:{},,jiaoliushenqingEntity:{}",this.getClass().getName(),jiaoliushenqingEntity.toString());

        JiaoliushenqingEntity oldJiaoliushenqing = jiaoliushenqingService.selectById(jiaoliushenqingEntity.getId());//查询原先数据

//        if(jiaoliushenqingEntity.getJiaoliushenqingYesnoTypes() == 2){//通过
//            jiaoliushenqingEntity.setJiaoliushenqingTypes();
//        }else if(jiaoliushenqingEntity.getJiaoliushenqingYesnoTypes() == 3){//拒绝
//            jiaoliushenqingEntity.setJiaoliushenqingTypes();
//        }
        jiaoliushenqingService.updateById(jiaoliushenqingEntity);//审核

        return R.ok();
    }

    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<JiaoliushenqingEntity> oldJiaoliushenqingList =jiaoliushenqingService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        jiaoliushenqingService.deleteBatchIds(Arrays.asList(ids));

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
            List<JiaoliushenqingEntity> jiaoliushenqingList = new ArrayList<>();//上传的东西
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
                            JiaoliushenqingEntity jiaoliushenqingEntity = new JiaoliushenqingEntity();
//                            jiaoliushenqingEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            jiaoliushenqingEntity.setJiaoliushenqingName(data.get(0));                    //标题 要改的
//                            jiaoliushenqingEntity.setJiaoliushenqingHuojiang(data.get(0));                    //获奖经历 要改的
//                            jiaoliushenqingEntity.setJiaoliushenqingJingsai(data.get(0));                    //活动竞赛经历 要改的
//                            jiaoliushenqingEntity.setJiaoliushenqingFile(data.get(0));                    //附件 要改的
//                            jiaoliushenqingEntity.setJiaoliushenqingText(data.get(0));                    //详情信息 要改的
//                            jiaoliushenqingEntity.setJiaoliushenqingYesnoTypes(Integer.valueOf(data.get(0)));   //申请状态 要改的
//                            jiaoliushenqingEntity.setJiaoliushenqingYesnoText(data.get(0));                    //审核回复 要改的
//                            jiaoliushenqingEntity.setInsertTime(date);//时间
//                            jiaoliushenqingEntity.setCreateTime(date);//时间
                            jiaoliushenqingList.add(jiaoliushenqingEntity);


                            //把要查询是否重复的字段放入map中
                        }

                        //查询是否重复
                        jiaoliushenqingService.insertBatch(jiaoliushenqingList);
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
        PageUtils page = jiaoliushenqingService.queryPage(params);

        //字典表数据转换
        List<JiaoliushenqingView> list =(List<JiaoliushenqingView>)page.getList();
        for(JiaoliushenqingView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Integer id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        JiaoliushenqingEntity jiaoliushenqing = jiaoliushenqingService.selectById(id);
            if(jiaoliushenqing !=null){


                //entity转view
                JiaoliushenqingView view = new JiaoliushenqingView();
                BeanUtils.copyProperties( jiaoliushenqing , view );//把实体数据重构到view中

                //级联表
                    YonghuEntity yonghu = yonghuService.selectById(jiaoliushenqing.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "username", "password", "newMoney", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段
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
    public R add(@RequestBody JiaoliushenqingEntity jiaoliushenqing, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,jiaoliushenqing:{}",this.getClass().getName(),jiaoliushenqing.toString());
        Wrapper<JiaoliushenqingEntity> queryWrapper = new EntityWrapper<JiaoliushenqingEntity>()
            .eq("yonghu_id", jiaoliushenqing.getYonghuId())
            .eq("jiaoliushenqing_name", jiaoliushenqing.getJiaoliushenqingName())
            .eq("jiaoliushenqing_huojiang", jiaoliushenqing.getJiaoliushenqingHuojiang())
            .eq("jiaoliushenqing_jingsai", jiaoliushenqing.getJiaoliushenqingJingsai())
            .eq("jiaoliushenqing_text", jiaoliushenqing.getJiaoliushenqingText())
            .in("jiaoliushenqing_yesno_types", new Integer[]{1,2})
            .eq("jiaoliushenqing_yesno_text", jiaoliushenqing.getJiaoliushenqingYesnoText())
//            .notIn("jiaoliushenqing_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        JiaoliushenqingEntity jiaoliushenqingEntity = jiaoliushenqingService.selectOne(queryWrapper);
        if(jiaoliushenqingEntity==null){
            jiaoliushenqing.setJiaoliushenqingYesnoTypes(1);
            jiaoliushenqing.setInsertTime(new Date());
            jiaoliushenqing.setCreateTime(new Date());
        jiaoliushenqingService.insert(jiaoliushenqing);

            return R.ok();
        }else {
            if(jiaoliushenqingEntity.getJiaoliushenqingYesnoTypes()==1)
                return R.error(511,"有相同的待审核的数据");
            else if(jiaoliushenqingEntity.getJiaoliushenqingYesnoTypes()==2)
                return R.error(511,"有相同的审核通过的数据");
            else
                return R.error(511,"表中有相同数据");
        }
    }

}

