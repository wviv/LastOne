package com.wa.last.mvc.pojo.entity;

import org.springframework.web.filter.AbstractRequestLoggingFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class HocRequestLoggingFilter extends AbstractRequestLoggingFilter {

    @Override
    protected void initFilterBean() throws ServletException {
        super.setIncludeQueryString(true);
        super.setIncludeClientInfo(true);
        //headers不输出了
        super.setIncludeHeaders(false);
        super.setIncludePayload(true);
        //最大支持到1000个字符
        super.setMaxPayloadLength(1000);
        super.setBeforeMessagePrefix("请求开始 [");
        super.setBeforeMessageSuffix("]");
        super.setAfterMessagePrefix("请求结束 [");
        super.setAfterMessageSuffix("]");
    }

    /**
     * 根据request控制是否打印log
     * @param request
     * @return
     */
    @Override
    protected boolean shouldLog(HttpServletRequest request) {
        //预热
        super.getMessagePayload(request);
        return true;
    }

    @Override
    protected void beforeRequest(HttpServletRequest request, String message) {
        //不做操作
        logger.info(super.createMessage(request,"开始 [","]"));
    }

    @Override
    protected void afterRequest(HttpServletRequest request, String message) {
        //不做操作
        logger.info(super.createMessage(request,"结束 [","]"));
    }
}
