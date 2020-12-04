package com.hins.designmode.proxymode;


/**
 * @author: hins
 * @created: 2020-12-04 12:51
 * @desc:
 **/
public class UserDaoImpl implements IUserDao {

    @Override
    public String queryUserByUserId(String userId) {
        return "#UserDaoImpl.queryUserByUserId#: " + userId;
    }
}
