package com.university.services.supportServices.impl;

import com.university.services.supportServices.VerifyService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
@Service
public class VerifyServiceImpl implements VerifyService {

    @Value("${page.access.token}")
    private String ACCESS_TOKEN;

    @Override
    public boolean verify(String verifyToken) {
        return verifyToken.equals(ACCESS_TOKEN);

    }



}
