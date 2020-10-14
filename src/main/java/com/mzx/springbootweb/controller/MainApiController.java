package com.mzx.springbootweb.controller;


import com.alibaba.fastjson.JSONArray;
import com.mzx.springbootweb.dto.OverTimeDto;
import com.mzx.springbootweb.entity.Overtime;
import com.mzx.springbootweb.entity.User;
import com.mzx.springbootweb.entity.UserLog;
import com.mzx.springbootweb.mapper.AccountMapper;
import com.mzx.springbootweb.mapper.OverTimeMapper;
import com.mzx.springbootweb.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

//接口控制器
//RestController 标注类 所有return都为json数据，结合@ResponseBody与@Controller功能
@RestController
@CrossOrigin
public class MainApiController {


    private static final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    UserMapper userMapper;

    @Autowired
    OverTimeMapper overTimeMapper;

    @Autowired
    AccountMapper accountMapper;

    //登陆请求

    /**
     *
     * 接口地址：/login
     * @param request ServletRequest对象，获取session域中数据
     * @param userName 获取页面input中name="userName"数据
     * @param passWord 获取页面input中name="passWord"数据
     * @return
     */
    @RequestMapping(value = {"/login"})
    public Map<String,Object> login(HttpServletRequest request,String userName,String passWord){
        Map<String,Object> map = new HashMap<>();
        map.put("state",Integer.valueOf(500));

        if (("root".equals(userName)) && ("root".equals(passWord))){
            request.getSession().setAttribute("user","1");
            map.put("state",Integer.valueOf(200));
        }
        return map;
    }

    //查询接口
    @RequestMapping(value = {"/findAllUser"})
    public Map<String,Object> findAllUser(HttpServletRequest request, @RequestParam(defaultValue = "1")int page,@RequestParam(defaultValue = "10")int limit){
        Map<String,Object> map = new HashMap<>();

        //设置简单反爬，python请求头 或 访问速度过快 提示页面访问过于频繁
        Object visitDate = request.getSession().getAttribute("visitDate");
        // 访问速度过快(请求速度不能超过500毫秒)
        if (visitDate == null){
            long time = System.currentTimeMillis();
            request.getSession().setAttribute("visitDate",Long.valueOf(time));
        }else {
            long time = ((Long) request.getSession().getAttribute("visitDate")).longValue();
            long nowSecond = System.currentTimeMillis();
            long timelen = nowSecond - time;
            if (timelen < 500L){
                map.put("msg", "Web page visits are too frequent");
                return map;
            }
        }
        //请求头为Python
        String header = request.getHeader("User-Agent");
        if (header.contains("python")){
            map.put("msg", "Web page visits are too frequent");
            return map;
        }

        //请求单页数据超过30条
        if (limit > 30){
            map.put("msg", "Web page visits are too frequent");
            return map;
        }

        /**
         * sql 中 LIMIT 后的第一个参数是输出记录的初始位置，第二个参数偏移量
         */
        //limit： startLine 比如第二页 (2 - 1) * 10 = 第十行数据  limit
        int startLine = (page - 1) * limit;
        // count:统计所有数据数量
        long l = this.userMapper.phoneLogCount();
        List<User> allUser = this.userMapper.getAllPhoneLog(startLine,limit);
        List list = new ArrayList();
        map.put("code",Integer.valueOf(0));
        map.put("msg","");
        map.put("count",Long.valueOf(l));
        //若为偶数页添加dataA、dataB数据字段
//        if (page % 2 == 0){
//            UserLog userLog = new UserLog();
//            for (User user:allUser) {
//                userLog.setUserLog(user.getId(),user.getUserA(),user.getUserB(),user.getStartTime()
//                ,user.getEndTime(),user.getAddressCodeA(),user.getAddressCodeB(),
//                        UUID.randomUUID().toString(),UUID.randomUUID().toString());
//                list.add(userLog);
//            }
//            map.put("data",list);
//            return map;
//        }
        map.put("data",allUser);
        return map;
    }

     @RequestMapping(value = {"/overTimeList"})
    public Map<String,Object> findOverTimes(HttpServletRequest request, String name,String content,String overTimeStartDate,String overTimeEndDate,@RequestParam(defaultValue = "1")int page,@RequestParam(defaultValue = "10")int limit) throws ParseException {
        String accountId = StringUtils.isEmpty(name)?null:accountMapper.getAccountIdByName(name);
        accountId = (accountId==null&&!StringUtils.isEmpty(name))?name:accountId;
        content = StringUtils.isEmpty(content)?null:content;
        overTimeStartDate = StringUtils.isEmpty(overTimeStartDate)?null:overTimeStartDate;
        overTimeEndDate = StringUtils.isEmpty(overTimeEndDate)?null:overTimeEndDate;
        Map<String,Object> map = new HashMap<>();
        int startLine = (page - 1) * limit;
        List<Overtime> overTimePages = overTimeMapper.getOverTimePages(accountId,content,overTimeStartDate,overTimeEndDate,startLine, limit);
        for (Overtime overtime: overTimePages) {
            String accountId1 = overtime.getAccountId();
            String accountNameById = accountMapper.getAccountNameById(accountId1);
            overtime.setAccountId(accountNameById==null?accountId1:accountNameById);
        }
        long l = overTimeMapper.overTimeSearchCount(accountId,content,overTimeStartDate,overTimeEndDate);
        map.put("code",Integer.valueOf(0));
        map.put("msg","");
        map.put("count",Long.valueOf(l));
        map.put("data",overTimePages);
        return map;
    }

    @RequestMapping(value = {"/overTimeList/add"},method = RequestMethod.POST)
    public String addOverTime(HttpServletRequest request) throws ParseException {
        Map<String, String[]> parameterMap = request.getParameterMap();
        OverTimeDto overTimeDto = new OverTimeDto();
        overTimeDto.setUsername(request.getParameter("username"));
        String date1 = request.getParameter("date1");
        String date2 = request.getParameter("date2");
        overTimeDto.setDate1(StringUtils.isEmpty(date1)?null:df.parse(date1));
        overTimeDto.setDate2(StringUtils.isEmpty(date2)?null:df.parse(date2));
        try{
            Double times = Double.parseDouble(request.getParameter("times"));
            overTimeDto.setTimes(times);
        }catch (Exception e){
            overTimeDto.setTimes(null);
        }
        overTimeDto.setProductName(request.getParameter("productName"));
        overTimeDto.setProjectName(request.getParameter("projectName"));
        overTimeDto.setOverTimeContext(request.getParameter("overTimeContext"));
        overTimeDto.setOverTimeType(Integer.parseInt(request.getParameter("overTimeType")));
        System.out.println("come in");
        String overTimeId = request.getParameter("overTimeId");
        int i = 0;
        if(StringUtils.isEmpty(overTimeId)){
             i = overTimeMapper.addOverTime(UUID.randomUUID().toString(), overTimeDto.getUsername(), overTimeDto.getOverTimeContext(), new Date(), overTimeDto.getTimes(), overTimeDto.getDate2(), overTimeDto.getDate1(), overTimeDto.getProductName(), overTimeDto.getProjectName(), overTimeDto.getOverTimeType());
        }else{
            i = overTimeMapper.updateOverTime(overTimeId, overTimeDto.getUsername(), overTimeDto.getOverTimeContext(), overTimeDto.getTimes(), overTimeDto.getDate2(), overTimeDto.getDate1(), overTimeDto.getProductName(), overTimeDto.getProjectName(), overTimeDto.getOverTimeType());
        }
        System.out.println(i);
        return "{\"result\":\"success\"}";
    }

    @GetMapping("/overTimeList/delete/{id}")
    public Boolean deleteOverTime(@PathVariable("id") String id){
        int result = overTimeMapper.deleteById(id);
        return result==1;
    }

    @PostMapping("/overTimeList/delete/")
    public Boolean deleteOverTime(HttpServletRequest request){
        Set<String> strings = request.getParameterMap().keySet();
        String next = "{ list:"+strings.iterator().next()+"}";
        PackOverTime packOverTime = JSONArray.parseObject(next, PackOverTime.class);
        List<String> ids = packOverTime.getList().stream().map(o -> o.getId()).collect(Collectors.toList());
        int i = overTimeMapper.deleteByIds(ids);
        System.out.println(i);
        return true;
    }

    public static class PackOverTime{
        private List<Overtime> list;

        public List<Overtime> getList() {
            return list;
        }

        public void setList(List<Overtime> list) {
            this.list = list;
        }
    }
    
}
