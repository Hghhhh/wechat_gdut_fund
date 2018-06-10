package com.yidong.model;
/**
 * 自己实现的用于springsecurity权限认证的UserDetail对象
 */

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JwtUser implements UserDetails {

    private String id;
    private String account;
    private String password;
    private String department;
    private String name;


    public JwtUser(String id,String account,String password,String department,String name){
        this.id = id;
        this.name = name;
        this.account = account;
        this.department = department;
        this.password = password;
    }


    public String getId() {
        return id;
    }

    public String getDepartment() {
        return department;
    }

    public String getName() {
        return name;
    }


    /**
     * 为了方便，权限固定为USER，如果需要权限可以从user中获取后到userdetail中
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authority = new ArrayList();
        authority.add(new SimpleGrantedAuthority("ROLE_USER"));
        return authority;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.account;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
