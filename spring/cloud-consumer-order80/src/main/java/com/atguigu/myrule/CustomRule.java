package com.atguigu.myrule;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.*;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 我们通过IRule接口，实现一个自定义的负载均衡策略——一致性哈希。
 */
public class CustomRule extends AbstractLoadBalancerRule{

    //定义一个原子类，以保证原子性
    private AtomicInteger atomicInteger =new AtomicInteger(0);

    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {
    }

    public Server choose(ILoadBalancer lb, Object key) {
        if (lb == null){
            return null;
        }
        //用于统计获取次数，当达到一定数量就不再去尝试
        int count = 0;
        Server server = null;
        //当服务还没获取到，并且尝试没有超过8次
        while (server == null && count++ < 8){
            //获取服务
            List<Server> allServers = lb.getAllServers();
            List<Server> reachableServers = lb.getReachableServers();
            int allServersSize = allServers.size();
            int reachableServersSize = reachableServers.size();
            //如果获取的服务list都为0就返回null
            if(allServersSize == 0 || reachableServersSize == 0){
                return  null;
            }
            //获取服务下标
            int next = getServerIndex(allServersSize);

            //获取服务
            server = reachableServers.get(next);

            //如果服务为空直接跳过下面的
            if (server == null){
                continue;
            }

            //如果获取到的这个服务是活着的就返回
            if (server.isAlive()){
                return server;
            }

            //如果获取到的服务不是空，但是不是存活状态，需要重新获取
            server=null;
        }

        //最后这里可能会返回null
        return  server;
    }

    //获取服务下标，为了保证原子性，使用了CAS
    public int getServerIndex(int allServersSize){
        //自旋锁
        for (;;) {
            //获取当前值
            int current = this.atomicInteger.get();
            //设置期望值
            int next = (current + 1) % allServersSize;
            //调用Native方法compareAndSet，执行CAS操作
            if (this.atomicInteger.compareAndSet(current, next))
                //成功后才会返回期望值，否则无线循环
                return next;
        }
    }
    @Override
    public Server choose(Object key) {

        return choose(getLoadBalancer(),key);
    }
}
