package com.data.en.decoder.contoller;

import com.alibaba.fastjson.JSON;
import com.data.en.decoder.dao.user.UserPhoneDao;
import com.data.en.decoder.entity.user.UserPhone;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController

@Slf4j
public class TestController {

    @Autowired
    private UserPhoneDao userPhoneDao;
    // http://localhost:8502/api/test1
    @RequestMapping("/test1")
    public String test1() throws Exception {
        UserPhone userPhone = new UserPhone();
        userPhone.setUserNameEn("18488888888");
        userPhone.setRealNameEn("张三");
        userPhone.setIdNumberEn("483898329832983");
        userPhone.setUserNo("89329832");
        userPhone.setCnlCode("chenn");
        userPhone.setChannelCode("3232");
        userPhone.setType(1);
        userPhoneDao.insertUserPhone(userPhone);
        return "sucess";
    }


    // http://localhost:8502/api/test2
    @RequestMapping("/test2")
    public String test2() throws Exception {
        UserPhone userPhone = userPhoneDao.selectUserPhoneByUserNameEn("18488888888");
        System.out.println(JSON.toJSONString(userPhone));
        return "sucess";
    }


    // http://localhost:8502/api/test3
    @RequestMapping("/test3")
    public String test3() throws Exception {
        List<String> usernames = new ArrayList<>();
        usernames.add("18488888888");
        usernames.add("18499999999");
        List<UserPhone> userPhone = userPhoneDao.selectUserPhoneByUserNameEns(usernames);
        System.out.println(JSON.toJSONString(userPhone));
        return "sucess";
    }


    // http://localhost:8502/api/test4
    @RequestMapping("/test4")
    public String test4() throws Exception {
        List<UserPhone> userPhones = new ArrayList<>();
        UserPhone userPhone = new UserPhone();
        userPhone.setUserNameEn("18488888888");
        userPhones.add(userPhone);


        UserPhone userPhone1 = new UserPhone();
        userPhone1.setUserNameEn("18488888889");
        userPhones.add(userPhone1);


        List<UserPhone> userPhoneList = userPhoneDao.selectUserPhoneByUserNameEnEntitys(userPhones);
        System.out.println(JSON.toJSONString(userPhoneList));
        return "sucess";
    }




    // http://localhost:8502/api/test5
    @RequestMapping("/test5")
    public String test5() throws Exception {
        String [] arrays = new String[]{"18488888888","18488888889"};
        List<UserPhone> userPhone = userPhoneDao.selectUserPhoneByArrayUserNameEns(arrays);
        System.out.println(JSON.toJSONString(userPhone));
        return "sucess";
    }




    // http://localhost:8502/api/test6
    @RequestMapping("/test6")
    public String test6() throws Exception {

        UserPhone userPhone = new UserPhone();
        userPhone.setUserNameEn("18488888888");

        UserPhone userPhone1 = new UserPhone();
        userPhone1.setUserNameEn("18488888889");
        UserPhone [] arrays = new UserPhone[]{userPhone,userPhone1};

        List<UserPhone> userPhoneList = userPhoneDao.selectUserPhoneByUserNameArrayEnEntitys(arrays);
        System.out.println(JSON.toJSONString(userPhoneList));
        return "sucess";
    }


    // http://localhost:8502/api/test7
    @RequestMapping("/test7")
    public String test7() throws Exception {

        String [] arrays = new String[]{"18488888888","18488888889"};
        List<UserPhone> userPhone = userPhoneDao.selectUserPhoneByArrayUserNameEnsAndRealName(arrays,"张三") ;
        System.out.println(JSON.toJSONString(userPhone));

        return "sucess";
    }








}
