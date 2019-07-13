package com.cwh.myspringbootapi.controller;

import com.cwh.myspringbootutils.help.DateUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/getDate")
    public String getCurrentDate(){
        Date date = DateUtil.getCurDate();
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }

}
