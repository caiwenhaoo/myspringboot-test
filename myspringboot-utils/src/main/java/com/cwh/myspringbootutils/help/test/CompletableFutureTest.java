package com.cwh.myspringbootutils.help.test;


import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CompletableFutureTest {

    //不推荐此方式创建线程
    //    ExecutorService executor = Executors.newFixedThreadPool(3);

    public static ExecutorService newFixedThreadPool(int nThreads) {
        return new ThreadPoolExecutor(nThreads, nThreads,0L, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>());
    }

    ExecutorService executor = CompletableFutureTest.newFixedThreadPool(2);

    @Test
    public void ex() {
        long start = System.currentTimeMillis();
        //参数
        List<String> webPageLinks = Arrays.asList("A", "B", "C","D","E","F","G","H");
        List<CompletableFuture<Void>> pageContentFutures = webPageLinks.stream()
            .map(webPageLink -> handle(webPageLink))
            .collect(Collectors.toList());

        CompletableFuture<Void> allFutures = CompletableFuture.allOf(
            pageContentFutures.toArray(new CompletableFuture[pageContentFutures.size()])
        );

        allFutures.join();
        System.out.println("所有线程已执行完[{}]" + allFutures.isDone());
        allFutures.whenComplete(new BiConsumer<Void, Throwable>() {
            @Override
            public void accept(Void aVoid, Throwable throwable) {
                System.out.println("执行最后一步操作");
                // doSth();
                long end = System.currentTimeMillis();
                System.out.println("耗时:"+ (end-start)/1000L );
            }
        });
    }

    //executor 可不传入,则默认最多3个线程
    CompletableFuture<Void> handle(String pageLink) {
        return CompletableFuture.runAsync(() -> {

            //int i = 1/0;
            System.out.println("执行任务"+pageLink);
            //TODO 业务逻辑



            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        },executor).exceptionally(new Function<Throwable, Void>() { //捕捉异常,不会导致整个流程中断
            @Override
            public Void apply(Throwable throwable) {
                System.out.println("线程[{}]发生了异常, 继续执行其他线程,错误详情[{}]" + Thread.currentThread().getName() + throwable.getMessage());
                return null;
            }
        });
    }




}
