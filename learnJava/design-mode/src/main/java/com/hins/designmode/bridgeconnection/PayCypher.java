package com.hins.designmode.bridgeconnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PayCypher implements IPayMode {

    protected Logger logger = LoggerFactory.getLogger(Pay.class);

    @Override
    public boolean security(String uId) {

        logger.info("密码支付，风险校验环境安全");
        return true;
    }
}
