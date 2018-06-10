package com.yidong.service.impl;


import com.yidong.mapper.UserMapper;
import com.yidong.model.JwtUser;
import com.yidong.service.UserService;
import com.yidong.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private AuthenticationManager authenticationManager;
    private UserDetailsService userDetailsService;
    private JwtTokenUtil jwtTokenUtil;
    private UserMapper userMapper;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    public UserServiceImpl(
         @Qualifier("JwtUserDetailsServiceImpl")   UserDetailsService userDetailsService,
            JwtTokenUtil jwtTokenUtil,
            UserMapper userMapper,
         AuthenticationManager authenticationManager
            ) {
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userMapper = userMapper;
        this.authenticationManager = authenticationManager;
    }

    /**
     * 登录的方法
     * @param account
     * @param password
     * @return 一个jwt的token
     */
    @Override
    public String login(String account, String password) {
        //这一部分生成根据账号密码authentication放在SecurityContextHolder上下文中，用来验证密码
        UsernamePasswordAuthenticationToken unAuthentication = new UsernamePasswordAuthenticationToken(account, password);//还未验证的认证
        final Authentication authentication = authenticationManager.authenticate(unAuthentication);//验证认证
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Reload password post-security so we can generate token
        final JwtUser userDetails =(JwtUser)userDetailsService.loadUserByUsername(account);

        final String token = jwtTokenUtil.generateToken(userDetails);
        return token;
    }

    /**
     * 刷新过期token
     * @param oldToken
     * @return newToken
     */
    @Override
    public String refresh(String oldToken) {
        final String token = oldToken.substring(tokenHead.length());//获得“bearer”后面的部分
        String account = jwtTokenUtil.getAccountFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(account);//验证数据库中有无此用户
        if (jwtTokenUtil.canTokenBeRefreshed(token)&&user!=null){
            return jwtTokenUtil.refreshToken(token);
        }
        return null;
    }
}
