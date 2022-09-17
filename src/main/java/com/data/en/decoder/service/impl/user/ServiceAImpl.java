package com.data.en.decoder.service.impl.user;

import com.data.en.decoder.service.user.ServiceA;
import com.data.en.decoder.service.user.ServiceB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceAImpl implements ServiceA {

    @Autowired
    private ServiceB serviceB;


    @Override
    public void methodA() {
        serviceB.methodB();
    }
}
