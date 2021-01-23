package com.liyun.jdk8.lambda;

import java.util.concurrent.RecursiveTask;

/**
 *
 */
public class ForkJoinClculate extends RecursiveTask<Long> {

    private long start;
    private long end;

    private static final long THRSHOLD=10000;

    public ForkJoinClculate(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long length=end - start;
        if(length <=THRSHOLD){
            long sum=0;
            for(long i= start ;i <=end ;i++){
                sum+=i;
            }
            return sum;
        }else{
            long middle=(start + end )/2;
            ForkJoinClculate left = new ForkJoinClculate(start,middle);
            left.fork();//拆分子任务，同时压入线程队列

            ForkJoinClculate right = new ForkJoinClculate(middle+1,end);
            right.fork();
            return left.join()+right.join();
        }
    }
}
