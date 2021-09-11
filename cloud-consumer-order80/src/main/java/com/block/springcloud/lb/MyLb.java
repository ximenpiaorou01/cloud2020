package com.block.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MyLb implements LoadBalancer{

    private AtomicInteger atomicInteger=new AtomicInteger(0);

    @Override
    public ServiceInstance instances(List<ServiceInstance> ServiceInstances) {
        int count = getAndIncrement();
        int i = count % ServiceInstances.size();
        return ServiceInstances.get(i);
    }


    /**
     * 自循锁+CAS
     * @return
     */
    public final int getAndIncrement(){
        int current;
        int next;
        do{
            current=this.atomicInteger.get();
            next=current>=2147483647?0:current+1;
        }while (!this.atomicInteger.compareAndSet(current,next));
        System.out.println("调用次数***:"+next);
        return next;

        //第二种实现方式
//        for(;;){
//            int current=this.atomicInteger.get();
//            int next=current>=2147483647?0:current+1;
//            if(this.atomicInteger.compareAndSet(current,next))
//                return next;
//        }


    }

    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE);
    }
}
