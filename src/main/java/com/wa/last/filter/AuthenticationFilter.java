package com.wa.last.filter;


import com.wa.last.exceptions.AuthenticationException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 接口验证过滤器,每次请求服务都会验证token是否有效
 *
 * @author Shmily
 */
@Slf4j
public class AuthenticationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        String token = request.getHeader("Authentication");
//        if (StringUtils.isEmpty(token)) {
//            throw new AuthenticationException(401, "Authentication is null");
//        }
//        log.info("验证通过");
        filterChain.doFilter(request, response);
    }
}
