package com.demo;

import cn.z.clock.Clock;
import cn.z.id.Id;

/**
 * <h1>高性能Id生成器测试</h1>
 *
 * <p>
 * createDate 2021/02/25 08:52:28
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
public class IdTest {

    public static void main(String[] args) {
//        test();
         init();
        // initMore();
        // initLate();
        // initException();
        // block();
        // back();
        // speed();
    }

    /**
     * 测试
     */
    static void test() {
        System.out.println("ID为：" + Id.next());
        // ID为：10566621028417536
    }

    /**
     * 初始化
     */
    static void init() {
        Id.init(0, 8, 22);
        System.out.println("ID为：" + Id.next());
        // [main] INFO cn.z.id.Id - 开始初始化，MACHINE_ID为0，MACHINE_BITS为8，SEQUENCE_BITS为12
        // ID为：10566962677547008
    }

    /**
     * 初始化多次
     */
    static void initMore() {
        Id.init(0, 8, 12);
        Id.init(0, 8, 13);
        System.out.println("ID为：" + Id.next());
        // [main] INFO cn.z.id.Id - 开始初始化，MACHINE_ID为0，MACHINE_BITS为8，SEQUENCE_BITS为12
        // [main] WARN cn.z.id.Id - 已经初始化过了，不可重复初始化！
        // ID为：10567138953658368
    }

    /**
     * 初始化晚了
     */
    static void initLate() {
        System.out.println("ID为：" + Id.next());
        Id.init(0, 8, 12);
        System.out.println("ID为：" + Id.next());
        // ID为：10567406546059264
        // [main] WARN cn.z.id.Id - 已经初始化过了，不可重复初始化！
        // ID为：10567406546059265
    }

    /**
     * 初始化异常
     */
    static void initException() {
        Id.init(1000, 8, 12);
        System.out.println("ID为：" + Id.next());
        // [main] INFO cn.z.id.Id -
        // 开始初始化，MACHINE_ID为1000，MACHINE_BITS为8，SEQUENCE_BITS为12
        // [main] ERROR cn.z.id.Id - 机器码MACHINE_ID需要>=0并且<=255。当前为1000
        // ID为：2687454335107072
    }

    /**
     * 阻塞
     */
    static void block() {
        // 初始化，复现阻塞
        Id.init(0, 0, 0);
        System.out.println(Id.next());
        System.out.println(Id.next());
        System.out.println(Id.next());
        // [main] INFO cn.z.id.Id - 开始初始化，MACHINE_ID为0，MACHINE_BITS为0，SEQUENCE_BITS为0
        // 5042679784
        // [main] WARN cn.z.id.Id - 检测到阻塞，时间戳为1614501879784，最大序列号为0。请考虑增加SEQUENCE_BITS。
        // 5042679803
        // [main] WARN cn.z.id.Id - 检测到阻塞，时间戳为1614501879803，最大序列号为0。请考虑增加SEQUENCE_BITS。
        // 5042679805
    }

    /**
     * 回拨(需要在1分钟内手动回拨时钟)
     */
    static void back() {
        for (int i = 0; i < 60; i++) {
            System.out.println(Id.next());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 10575587806543872
        // 10575589903695872
        // [main] WARN cn.z.id.Id -
        // 监测到系统时钟发生了回拨。时间戳为1614498432211，上一个生成的时间戳为1614502034236。
        // 10575589905793024
        // 10575592013430784
    }

    /**
     * 测速
     */
    static void speed() {
        // 初始化，避免阻塞
        Id.init(0, 0, 28);
        // 100万
        speed(1000000);
        // 1000万
        speed(10000000);
        // 1亿
        speed(100000000);
        // 21亿
        speed(Integer.MAX_VALUE);
        // [main] INFO cn.z.id.Id - 开始初始化，MACHINE_ID为0，MACHINE_BITS为0，SEQUENCE_BITS为28
        // 高性能Id生成器调用1000000次使用时间为：16毫秒
        // 高性能Id生成器调用10000000次使用时间为：162毫秒
        // 高性能Id生成器调用100000000次使用时间为：308毫秒
        // 高性能Id生成器调用2147483647次使用时间为：15802毫秒
    }

    /**
     * 测速
     * 
     * @param count 次数
     */
    static void speed(int count) {
        // 调用一次，并延时1秒，初始化
        Id.next();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long a = Clock.now();
        for (int i = 0; i < count; i++) {
            Id.next();
        }
        long b = Clock.now();
        long ba = b - a;
        System.out.println("高性能Id生成器调用" + count + "次使用时间为：" + ba + "毫秒");
    }

}
