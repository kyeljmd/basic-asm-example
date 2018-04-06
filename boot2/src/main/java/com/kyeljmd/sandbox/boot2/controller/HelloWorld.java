package com.kyeljmd.sandbox.boot2.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by kyel on 3/29/2018.
 */
@RestController
public class HelloWorld {

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String message(){
        return "Hello World";
    }

    @RequestMapping(value = "/", method =  RequestMethod.POST)
    public Message postRequest(@RequestBody Message message) {
        return message;
    }
}
