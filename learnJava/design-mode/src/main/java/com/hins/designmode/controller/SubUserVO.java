package com.hins.designmode.controller;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author: hins
 * @created: 2020-11-27 18:23
 * @desc:
 **/
@Data
public class SubUserVO {

    @NotBlank(message = "currency not null")
    private String currency;

}
