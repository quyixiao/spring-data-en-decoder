package com.data.en.decoder.service.impl.user;

import com.data.en.decoder.service.user.ServiceA;
import com.data.en.decoder.service.user.ServiceB;
import com.data.en.decoder.service.user.ServiceC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceBImpl implements ServiceB {

    @Autowired
    private ServiceC serviceC;


    @Override
    public void methodB() {
        serviceC.methodC();
    }
}
