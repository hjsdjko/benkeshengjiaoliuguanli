
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
 * 课程信息
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/kecheng")
public class KechengController {
    private static final Logger logger = LoggerFactory.getLogger(KechengController.class);

    private static final String TABLE_NAME = "kecheng";

    @Autowired
    private KechengService kechengService;


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
    private JiaoliushenqingService jiaoliushenqingService;//交流申请
    @Autowired
    private JiaoshiService jiaoshiService;//教师
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
        PageUtils page = kechengService.queryPage(params);

        //字典表数据转换
        List<KechengView> list =(List<KechengView>)page.getList();
        for(KechengView c:list){
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
        KechengEntity kecheng = kechengService.selectById(id);
        if(kecheng !=null){
            //entity转view
            KechengView view = new KechengView();
            BeanUtils.copyProperties( kecheng , view );//把实体数据重构到view中
            //级联表 教师
            //级联表
            JiaoshiEntity jiaoshi = jiaoshiService.selectById(kecheng.getJiaoshiId());
            if(jiaoshi != null){
            BeanUtils.copyProperties( jiaoshi , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "username", "password", "newMoney", "jiaoshiId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setJiaoshiId(jiaoshi.getId());
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
    public R save(@RequestBody KechengEntity kecheng, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,kecheng:{}",this.getClass().getName(),kecheng.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("教师".equals(role))
            kecheng.setJiaoshiId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<KechengEntity> queryWrapper = new EntityWrapper<KechengEntity>()
            .eq("kecheng_name", kecheng.getKechengName())
            .eq("kecheng_types", kecheng.getKechengTypes())
            .eq("kecheng_shijian", kecheng.getKechengShijian())
            .eq("jiaoshi_id", kecheng.getJiaoshiId())
            .eq("xuefen_numb", kecheng.getXuefenNumb())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        KechengEntity kechengEntity = kechengService.selectOne(queryWrapper);
        if(kechengEntity==null){
            kecheng.setCreateTime(new Date());
            kechengService.insert(kecheng);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody KechengEntity kecheng, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,kecheng:{}",this.getClass().getName(),kecheng.toString());
        KechengEntity oldKechengEntity = kechengService.selectById(kecheng.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("教师".equals(role))
//            kecheng.setJiaoshiId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        if("".equals(kecheng.getKechengPhoto()) || "null".equals(kecheng.getKechengPhoto())){
                kecheng.setKechengPhoto(null);
        }
        if("".equals(kecheng.getKechengContent()) || "null".equals(kecheng.getKechengContent())){
                kecheng.setKechengContent(null);
        }

            kechengService.updateById(kecheng);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<KechengEntity> oldKechengList =kechengService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        kechengService.deleteBatchIds(Arrays.asList(ids));

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
            List<KechengEntity> kechengList = new ArrayList<>();//上传的东西
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
                            KechengEntity kechengEntity = new KechengEntity();
//                            kechengEntity.setKechengUuidNumber(data.get(0));                    //课程编号 要改的
//                            kechengEntity.setKechengName(data.get(0));                    //课程名称 要改的
//                            kechengEntity.setKechengTypes(Integer.valueOf(data.get(0)));   //课程类型 要改的
//                            kechengEntity.setKechengPhoto("");//详情和图片
//                            kechengEntity.setKechengShijian(Integer.valueOf(data.get(0)));   //课程时间/分钟 要改的
//                            kechengEntity.setJiaoshiId(Integer.valueOf(data.get(0)));   //负责教师 要改的
//                            kechengEntity.setXuefenNumb(Integer.valueOf(data.get(0)));   //学分 要改的
//                            kechengEntity.setKechengContent("");//详情和图片
//                            kechengEntity.setCreateTime(date);//时间
                            kechengList.add(kechengEntity);


                            //把要查询是否重复的字段放入map中
                                //课程编号
                                if(seachFields.containsKey("kechengUuidNumber")){
                                    List<String> kechengUuidNumber = seachFields.get("kechengUuidNumber");
                                    kechengUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> kechengUuidNumber = new ArrayList<>();
                                    kechengUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("kechengUuidNumber",kechengUuidNumber);
                                }
                        }

                        //查询是否重复
                         //课程编号
                        List<KechengEntity> kechengEntities_kechengUuidNumber = kechengService.selectList(new EntityWrapper<KechengEntity>().in("kecheng_uuid_number", seachFields.get("kechengUuidNumber")));
                        if(kechengEntities_kechengUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(KechengEntity s:kechengEntities_kechengUuidNumber){
                                repeatFields.add(s.getKechengUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [课程编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        kechengService.insertBatch(kechengList);
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
        PageUtils page = kechengService.queryPage(params);

        //字典表数据转换
        List<KechengView> list =(List<KechengView>)page.getList();
        for(KechengView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Integer id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        KechengEntity kecheng = kechengService.selectById(id);
            if(kecheng !=null){


                //entity转view
                KechengView view = new KechengView();
                BeanUtils.copyProperties( kecheng , view );//把实体数据重构到view中

                //级联表
                    JiaoshiEntity jiaoshi = jiaoshiService.selectById(kecheng.getJiaoshiId());
                if(jiaoshi != null){
                    BeanUtils.copyProperties( jiaoshi , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "username", "password", "newMoney", "jiaoshiId"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setJiaoshiId(jiaoshi.getId());
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
    public R add(@RequestBody KechengEntity kecheng, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,kecheng:{}",this.getClass().getName(),kecheng.toString());
        Wrapper<KechengEntity> queryWrapper = new EntityWrapper<KechengEntity>()
            .eq("kecheng_uuid_number", kecheng.getKechengUuidNumber())
            .eq("kecheng_name", kecheng.getKechengName())
            .eq("kecheng_types", kecheng.getKechengTypes())
            .eq("kecheng_shijian", kecheng.getKechengShijian())
            .eq("jiaoshi_id", kecheng.getJiaoshiId())
            .eq("xuefen_numb", kecheng.getXuefenNumb())
//            .notIn("kecheng_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        KechengEntity kechengEntity = kechengService.selectOne(queryWrapper);
        if(kechengEntity==null){
            kecheng.setCreateTime(new Date());
        kechengService.insert(kecheng);

            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

}

