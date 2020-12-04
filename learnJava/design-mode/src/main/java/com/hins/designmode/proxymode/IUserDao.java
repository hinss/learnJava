package com.hins.designmode.proxymode;

/**
 * UserDao 对外提供的user接口
 */
public interface IUserDao {

    @Select("select * from user where id = #{uId}")
    String queryUserByUserId(String userId);

}
