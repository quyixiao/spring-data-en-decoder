package com.data.en.decoder.service.impl.user;

import com.data.en.decoder.dao.user.UserLoginDao;
import com.data.en.decoder.dao.user.UserPhoneDao;
import com.data.en.decoder.service.user.ServiceC;
import com.lz.mybatis.plugin.annotations.Sum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ServiceCImpl implements ServiceC {

    @Autowired
    private UserPhoneDao userPhoneDao;

    @Autowired
    private UserLoginDao userLoginDao;

    @Autowired
    private ServiceC serviceC;


    @Override
    public void methodC() {
        serviceC.methodD();
    }




    @Override
    public void methodD() {

        userPhoneDao.selectUserPhoneById(1l);
        log.info("测试方法调用链+++++++++++++++++++++++++++++");

        userPhoneDao.selectUserPhoneById(2l);
        log.info("测试方法调用链================================");
        userLoginDao.selectUserLoginById(1l);


    }
}
