/**
 * mapper层相当于原来的Dao层，Usermapper与mabatis的UserMapper.xml对应
 */
package com.yidong.mapper;

import com.yidong.model.User;

import java.util.Map;

public interface UserMapper {

    /**
     * 根据账号密码找用户
     * @param map
     * @return User
     */
    User selectByAccountPassword(Map<String,String> map);
    User selectByAccount(String account);

}