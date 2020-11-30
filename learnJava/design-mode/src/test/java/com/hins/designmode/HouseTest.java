package com.hins.designmode;

import org.junit.Test;

import java.math.BigDecimal;

/**
 * @author: hins
 * @created: 2020-10-19 16:26
 * @desc:
 **/
public class HouseTest {

    @Test
    public void test(){

            BigDecimal total = new BigDecimal("1440000");

            BigDecimal spent = new BigDecimal("341340");

            BigDecimal rate = new BigDecimal("1.05");

            int year = 0;
            boolean flag = true;
            while(flag){

                total = total.multiply(rate);

                total = total.subtract(spent);

                if(total.compareTo(BigDecimal.ZERO) <= 0){
                    flag = false;
                }else{
                    year++;
                }
            }

            System.out.println("剩余:" + total);

            System.out.println(year);
    }


}
