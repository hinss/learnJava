package com.hins.designmode.controller;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author: hins
 * @created: 2020-11-27 18:21
 * @desc:
 **/
@RestController
@RequestMapping("/test")
public class TestValidateContraller {

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void add(@RequestBody @Validated UserVO userVO, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            System.out.println("has error");
        }else{
            System.out.println("no error");
        }

    }


}
