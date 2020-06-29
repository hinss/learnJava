package com.hins.designmode.abstractfactory.factory;

import com.hins.designmode.abstractfactory.biz.straits.FxSpreadMarginBreakdown;
import com.hins.designmode.abstractfactory.biz.straits.FxTenorBreakdown;
import com.hins.designmode.abstractfactory.biz.orientcompany.Orient;
import com.hins.designmode.abstractfactory.biz.straits.Straits;
import org.springframework.util.StringUtils;

public class StraitsFactory extends AbstractFactory{



    @Override
    public Orient getOrient(String orientBreakdownType) {
        return null;
    }

    @Override
    public Straits getStraits(String straitsBreakdownType) {

        if(StringUtils.endsWithIgnoreCase("TENOR",straitsBreakdownType)){
            return new FxTenorBreakdown();
        } else if(StringUtils.endsWithIgnoreCase("SPREAD",straitsBreakdownType)){
            return new FxSpreadMarginBreakdown();
        }

        return null;
    }
}
