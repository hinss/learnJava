package com.hins.designmode.abstractfactory.factory;


import com.hins.designmode.abstractfactory.biz.orientcompany.FoCMEBreakdown;
import com.hins.designmode.abstractfactory.biz.orientcompany.FoIceBreakdown;
import com.hins.designmode.abstractfactory.biz.orientcompany.Orient;
import com.hins.designmode.abstractfactory.biz.straits.Straits;
import org.springframework.util.StringUtils;

public class OrientFactory {

    public Orient getOrient(String orientBreakdownType) {
        if(StringUtils.endsWithIgnoreCase("CME",orientBreakdownType)){
            return new FoCMEBreakdown();
        } else if(StringUtils.endsWithIgnoreCase("ICE",orientBreakdownType)){
            return new FoIceBreakdown();
        }

        return null;
    }

}
