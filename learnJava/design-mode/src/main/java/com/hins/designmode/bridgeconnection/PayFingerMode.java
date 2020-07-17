package com.hins.designmode.bridgeconnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PayFingerMode implements IPayMode {

    protected Logger logger = LoggerFactory.getLogger(Pay.class);

    @Override
    public boolean security(String uId) {

        logger.info("指纹支付，风险校验指纹信息");

        return false;
    }
}
