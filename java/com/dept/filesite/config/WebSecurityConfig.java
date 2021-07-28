package com.dept.filesite.config;

import com.dept.filesite.entity.ResultCodeEnum;
import com.dept.filesite.vo.ResultVO;
import com.dept.filesite.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @className: WebSecurityConfig
 * @description: security配置类
 * @author: 201998
 * @create: 2019-12-25 15:45:00
 */

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserService userServer;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userServer);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/reg").hasAnyRole("超级管理员", "部门领导", "科室主任")
                .antMatchers("/anounce/delete").hasAnyRole("超级管理员", "体系管理员")
                .antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources", "/configuration/security", "/swagger-ui.html", "/webjars/**", "/swagger-resources/configuration/ui", "/swagger-ui.html").permitAll()
                .antMatchers("/temperature/*").hasAnyRole("超级管理员", "公司领导", "部门领导", "科室主任", "体温管理员", "体温记录员")
                .anyRequest().authenticated() //其他路径登录后即可访问
                .and().formLogin().loginPage("/login").successHandler(new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                httpServletResponse.setContentType("application/json;charset=utf-8");
                PrintWriter out = httpServletResponse.getWriter();
                ResultVO<String> result = new ResultVO<String>("登录成功");
                out.write(new ObjectMapper().writeValueAsString(result));
                out.flush();
                out.close();

            }
        })
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
                        httpServletResponse.setContentType("application/json;charset=utf-8");
                        String msg = "登陆失败";
                        String eMsg = e.toString();
                        System.out.println("错误原因：" + eMsg);

                        if (eMsg.indexOf("UsernameNotFoundException") != -1) {
                            msg = "用户名不存在";
                        }
                        if (eMsg.indexOf("BadCredentialsException") != -1) {
                            msg = "账号或密码错误";
                        }
                        if (eMsg.indexOf("DisabledException") != -1) {
                            msg = "账号未启用";
                        }
                        PrintWriter out = httpServletResponse.getWriter();
                        ResultVO<String> result = new ResultVO<String>(msg);
                        out.write(new ObjectMapper().writeValueAsString(result));
                        out.flush();
                        out.close();
                    }
                }).loginProcessingUrl("/login")//登录过程，账号密码验证接口，默认也是/login
                .usernameParameter("mail").passwordParameter("password").permitAll()
                .and().logout().permitAll().and().csrf().disable()
                .exceptionHandling()
                //没有认证时，在这里处理结果，返回401
                .authenticationEntryPoint(new AuthenticationEntryPoint() {
                                              @Override
                                              public void commence(HttpServletRequest req, HttpServletResponse resp, AuthenticationException authException) throws IOException, ServletException {
                                                  resp.setContentType("application/json;charset=utf-8");
                                                  resp.setStatus(401);
                                                  PrintWriter out = resp.getWriter();
                                                  ResultVO<String> result = new ResultVO<String>(ResultCodeEnum.FAILED, "401,用户无认证");

                                                  if (authException instanceof InsufficientAuthenticationException) {
                                                      result.setData("请求失败，请联系管理员!");
                                                  }
                                                  out.write(new ObjectMapper().writeValueAsString(result));
                                                  out.flush();
                                                  out.close();
                                              }
                                          }
                );
    }


    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/index.html", "/static/**");
    }
}
