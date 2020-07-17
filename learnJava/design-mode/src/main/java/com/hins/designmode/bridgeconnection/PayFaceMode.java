package com.hins.designmode.bridgeconnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PayFaceMode implements IPayMode {

    protected Logger logger = LoggerFactory.getLogger(Pay.class);

    @Override
    public boolean security(String uId) {

        logger.info("人脸识别,风险校验面部信息");
        return true;
    }
}
