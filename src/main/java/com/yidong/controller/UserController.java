package com.yidong.controller;
/**
 * 用于登录的controller
 */

import com.yidong.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController {
    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private UserService userService;

    /**
     * 接收账号密码登录成功后返回一个token
     * @param account
     * @param password
     * @return token
     * @throws AuthenticationException
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ResponseEntity<String> createAuthenticationToken(
            @RequestParam String account, @RequestParam String password) throws AuthenticationException{
        final String token = userService.login(account, password);
        // Return the token
        return ResponseEntity.ok(token);
    }

    /**
     * 用于更新token
     * @param request
     * @return
     * @throws AuthenticationException
     */
    @RequestMapping(value = "/refresh", method = RequestMethod.GET)
    public ResponseEntity<String> refreshAndGetAuthenticationToken(
            HttpServletRequest request) throws AuthenticationException{
        String token = request.getHeader(tokenHeader);
        String refreshedToken = userService.refresh(token);
        if(refreshedToken == null) {
            return ResponseEntity.badRequest().body(null);
        } else {
            return ResponseEntity.ok(refreshedToken);
        }
    }

}
