package com.hins.designmode;

import com.hins.designmode.buildermode.CarBuilder;
import com.hins.designmode.buildermode.ICarMenu;
import org.junit.jupiter.api.Test;

public class BuilderModeTest {

    @Test
    public void builderModeTest(){

        CarBuilder carBuilder = new CarBuilder();
        ICarMenu iCarMenu = carBuilder.builderFirstTypeCar();

        System.out.println("我这部车是:" + iCarMenu.getDesc());

        ICarMenu iCarMenu2 = carBuilder.builderSecondTypeCar();

        System.out.println("我这部车是:" + iCarMenu2.getDesc());



    }


}
