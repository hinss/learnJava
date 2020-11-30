package com.hins.designmode.controller;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author: hins
 * @created: 2020-11-27 18:23
 * @desc:
 **/
@Data
public class UserVO {

    @NotNull(message = "currency not null")
    private String currency;


}
