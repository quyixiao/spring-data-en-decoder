package com.data.en.decoder.contoller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.data.en.decoder.dao.user.StageBorrowDao;
import com.data.en.decoder.dao.user.UserPhoneDao;
import com.data.en.decoder.entity.user.*;
import com.data.en.decoder.service.user.StageBorrowService;
import com.data.en.decoder.service.user.UserPhoneService;
import com.data.en.decoder.utils.ISelect;
import com.data.en.decoder.utils.PageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController

@Slf4j
public class TestController {

    @Autowired
    private UserPhoneDao userPhoneDao;


    @Autowired
    private  UserPhoneService userPhoneService;

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

        String [] arrays = new String[]{"18411111111","18422222222","18433333333","18444444444"};
        List<UserPhone> userPhone = userPhoneDao.selectUserPhoneByArrayUserNameEnsAndRealName(arrays,"张三") ;
        System.out.println(JSON.toJSONString(userPhone));

        return "sucess";
    }


    // http://localhost:8502/api/test8
    @RequestMapping("/test8")
    public String test8() throws Exception {
        UserPhone userPhone = new UserPhone();
        userPhone.setUserNameEn("18488888888");
        UserPhone data = userPhoneDao.selectUserPhoneByUsernNameObject(userPhone);
        System.out.println(JSON.toJSONString(userPhone));
        return "success";

    }


    // http://localhost:8502/api/test9
    @RequestMapping("/test9")
    public String test9() throws Exception {
        UserPhone userPhone = userPhoneDao.selectUserPhoneById(2l);
        userPhone.setUserNameEn("111111111111");
        userPhoneDao.updateUserPhoneById(userPhone);
        userPhone = userPhoneDao.selectUserPhoneById(2l);
        System.out.println(JSON.toJSONString(userPhone));

        return "success";

    }


    // http://localhost:8502/api/test10
    @RequestMapping("/test10")
    public String test10() throws Exception {
        UserPhone userPhone1 = userPhoneDao.selectUserPhoneById(1l);
        UserPhone userPhone2 = userPhoneDao.selectUserPhoneById(2l);
        UserPhone userPhone3 = userPhoneDao.selectUserPhoneById(3l);
        userPhone1.setUserNameEn("111111111111");
        userPhone2.setUserNameEn("222222222222");
        userPhone3.setUserNameEn("333333333333");

        List<UserPhone> userPhones = new ArrayList<>();
        userPhones.add(userPhone1);
        userPhones.add(userPhone2);
        userPhones.add(userPhone3);

        userPhoneService.updateBatchById(userPhones);

        UserPhone userPhone11 = userPhoneDao.selectUserPhoneById(1l);
        UserPhone userPhone22 = userPhoneDao.selectUserPhoneById(2l);
        UserPhone userPhone33= userPhoneDao.selectUserPhoneById(3l);
        System.out.println(JSON.toJSONString(userPhone11));
        System.out.println(JSON.toJSONString(userPhone22));
        return "success";

    }


    // http://localhost:8502/api/test11
    @RequestMapping("/test11")
    public String test11() throws Exception {
        List<String> usernames = new ArrayList<>();
        usernames.add("111111111111");
        usernames.add("222222222222");
        usernames.add("333333333333");
        PageUtils pageUtils = PageUtils.startPage(1, 10).doSelect(new ISelect() {
            @Override
            public List doSelect(IPage page) {
                return userPhoneDao.selectUserPhoneByPageUserNameEns(page,usernames);
            }
        });
        System.out.println(JSON.toJSONString(pageUtils));
        return "success";

    }



    @Autowired
    private StageBorrowDao stageBorrowDao;


    // http://localhost:8502/api/test12
    @RequestMapping("/test12")
    public String test12() throws Exception {
        List<StageBorrow> borrows = stageBorrowDao.selectStageBorrowByAll();
        // 此时此刻 需要拿到所有用户 的用户名
        List<String> uniqueCodes = borrows.stream().map(StageBorrow::getUniqueCode).collect(Collectors.toList());
        List<UserPhone> userPhones = userPhoneDao.selectUserPhoneByUniqueCodes(uniqueCodes);
        Map<String,UserPhone> mapUserPhone =  userPhones.stream().collect(Collectors.toMap(UserPhone::getUniqueCode , Function.identity()));
        for(StageBorrow stageBorrow :borrows){
            UserPhone userPhone = mapUserPhone.get(stageBorrow.getUniqueCode());
            System.out.println("=========" + userPhone.getRealNameEn());
        }

        System.out.println("==================还有一种写法=========================");
        for(StageBorrow stageBorrow :borrows){
            // 这种法写，性能太差了
            UserPhone userPhone = userPhoneDao.selectUserPhoneByUniqueCode(stageBorrow.getUniqueCode());
            System.out.println("=========" + userPhone.getRealNameEn());
        }
        return "success";
    }




    // http://localhost:8502/api/test13
    @RequestMapping("/test13")
    public String test13() throws Exception {
        List<StageBorrow> borrows = stageBorrowDao.selectStageBorrowByAll();
        // 此时此刻 需要拿到所有用户 的用户名
        Map<String,UserPhone> mapUserPhone =  userPhoneDao.selectUserPhoneMapByUniqueCode(borrows);
        for(StageBorrow stageBorrow :borrows){
            UserPhone userPhone = mapUserPhone.get(stageBorrow.getUniqueCode());
            System.out.println("=========" + userPhone.getRealNameEn());
        }
        return "success";
    }




    // http://localhost:8502/api/test14
    @RequestMapping("/test14")
    public String test14() throws Exception {
        List<StageBorrow> borrows = stageBorrowDao.selectStageBorrowByAll();
        // 此时此刻 需要拿到所有用户 的用户名
        Map<String,String> mapUserPhone =  userPhoneDao.selectUserPhoneMapByUniqueCodeRealName(borrows);
        for(StageBorrow stageBorrow :borrows){
            String  realName = mapUserPhone.get(stageBorrow.getUniqueCode());
            System.out.println("=========" +realName);
        }
        return "success";
    }




    // http://localhost:8502/api/test15
    @RequestMapping("/test15")
    public String test15() throws Exception {
        List<StageBorrowDto> borrows = stageBorrowDao.selectStageBorrowByAllDto();
        for(StageBorrowDto stageBorrowDto : borrows){
            List<UserPhone> userPhones = stageBorrowDto.getUserPhoneList();
            System.out.println(JSON.toJSONString(userPhones));
        }
        return "success";
    }


    // http://localhost:8502/api/test16
    @RequestMapping("/test16")
    public String test16() throws Exception {
        List<StageBorrowDto2> borrows = stageBorrowDao.selectStageBorrowByAllDto2();
        for(StageBorrowDto2 stageBorrowDto : borrows){
            UserPhone userPhone = stageBorrowDto.getUserPhone();
            System.out.println(JSON.toJSONString(userPhone));
        }
        return "success";
    }



    // http://localhost:8502/api/test17
    @RequestMapping("/test17")
    public String test17() throws Exception {
        List<StageBorrowDto3> borrows = stageBorrowDao.selectStageBorrowByAllDto3();
        for(StageBorrowDto3 stageBorrowDto : borrows){
            UserPhone userPhone = stageBorrowDto.getUserPhone();
            System.out.println(JSON.toJSONString(userPhone));
        }
        return "success";
    }



    // http://localhost:8502/api/test18
    @RequestMapping("/test18")
    public String test18() throws Exception {
        List<StageBorrowDto4> borrows = stageBorrowDao.selectStageBorrowByAllDto4();
        for(StageBorrowDto4 stageBorrowDto : borrows){
            UserPhone userPhone = stageBorrowDto.getUserPhone();
            System.out.println(JSON.toJSONString(userPhone));
        }
        return "success";
    }


}
