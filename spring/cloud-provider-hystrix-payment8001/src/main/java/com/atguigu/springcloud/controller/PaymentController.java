package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.PaymentHystrixMain8001;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
public class PaymentController {

    @Resource
    PaymentService paymentService;

    @Value("${server.port}")
    private String ServerPort;

    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id){
        String result = paymentService.paymentInfo_OK(id);
        log.info("************result: " + result);
        return result;
    }
    @GetMapping("/payment/hystrix/timeout/{id}")
    public String paymentInfo_TimeOut(Integer id){
        String result = paymentService.paymentInfo_OK(id);
        log.info("******result：" + result);
        return result;
    }
}
