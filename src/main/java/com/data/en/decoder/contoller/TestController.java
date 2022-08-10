package com.data.en.decoder.contoller;

import com.alibaba.fastjson.JSON;
import com.data.en.decoder.dao.user.UserPhoneDao;
import com.data.en.decoder.entity.user.UserPhone;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
