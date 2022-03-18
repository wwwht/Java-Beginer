package com.atguigu.myrule;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 我们通过IRule接口，实现一个自定义的负载均衡策略——一致性哈希。
 */
public class CustomRule extends AbstractLoadBalancerRule implements IRule {

    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String uri = request.getServletPath() + "?" + request.getQueryString();
        return route(uri.hashCode(), getLoadBalancer().getAllServers());
    }


    @Override
    public Server choose(Object o) {
        return null;
    }

    public
}
