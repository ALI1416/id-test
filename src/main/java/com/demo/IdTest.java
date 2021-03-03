package com.demo;

import cn.z.clock.Clock;
import cn.z.id.Id;

import java.util.Random;
import java.util.UUID;

/**
 * <h1>高性能雪花Id生成器测试</h1>
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
        // compare();
    }

    /**
     * 测试
     */
    static void test() {
        System.out.println("ID为：" + Id.next());
        // [main] INFO cn.z.id.Id - 预初始化...
        // [main] INFO cn.z.id.Id - 初始化，MACHINE_ID为0，MACHINE_BITS为8，SEQUENCE_BITS为12
        // [main] INFO cn.z.id.Id - 最大机器码MACHINE_ID为255，1ms内最多生成Id数量为4096，时钟最早回拨到2021-01-01 08:00:00
        // .0，可使用时间大约为278年，失效日期为2299-09-27 23:10:22.207
        // ID为：5483442415337472
    }

    /**
     * 初始化
     */
    static void init() {
        Id.init(0, 8, 14);
        System.out.println("ID为：" + Id.next());
        // [main] INFO cn.z.id.Id - 预初始化...
        // [main] INFO cn.z.id.Id - 初始化，MACHINE_ID为0，MACHINE_BITS为8，SEQUENCE_BITS为12
        // [main] INFO cn.z.id.Id - 最大机器码MACHINE_ID为255，1ms内最多生成Id数量为4096，时钟最早回拨到2021-01-01 08:00:00
        // .0，可使用时间大约为278年，失效日期为2299-09-27 23:10:22.207
        // [main] INFO cn.z.id.Id - 手动初始化...
        // [main] INFO cn.z.id.Id - 初始化，MACHINE_ID为0，MACHINE_BITS为8，SEQUENCE_BITS为14
        // [main] INFO cn.z.id.Id - 最大机器码MACHINE_ID为255，1ms内最多生成Id数量为16384，时钟最早回拨到2021-01-01 08:00:00
        // .0，可使用时间大约为69年，失效日期为2090-09-07 23:47:35.551
        // ID为：21934128022683648
    }

    /**
     * 初始化多次
     */
    static void initMore() {
        Id.init(0, 8, 13);
        Id.init(0, 8, 15);
        System.out.println("ID为：" + Id.next());
        // [main] INFO cn.z.id.Id - 预初始化...
        // [main] INFO cn.z.id.Id - 初始化，MACHINE_ID为0，MACHINE_BITS为8，SEQUENCE_BITS为12
        // [main] INFO cn.z.id.Id - 最大机器码MACHINE_ID为255，1ms内最多生成Id数量为4096，时钟最早回拨到2021-01-01 08:00:00
        // .0，可使用时间大约为278年，失效日期为2299-09-27 23:10:22.207
        // [main] INFO cn.z.id.Id - 手动初始化...
        // [main] INFO cn.z.id.Id - 初始化，MACHINE_ID为0，MACHINE_BITS为8，SEQUENCE_BITS为13
        // [main] INFO cn.z.id.Id - 最大机器码MACHINE_ID为255，1ms内最多生成Id数量为8192，时钟最早回拨到2021-01-01 08:00:00
        // .0，可使用时间大约为139年，失效日期为2160-05-15 15:35:11.103
        // [main] WARN cn.z.id.Id - 已经初始化过了，不可重复初始化！
        // ID为：10967292061941760
    }

    /**
     * 初始化晚了
     */
    static void initLate() {
        System.out.println("ID为：" + Id.next());
        Id.init(0, 8, 12);
        System.out.println("ID为：" + Id.next());
        // [main] INFO cn.z.id.Id - 预初始化...
        // [main] INFO cn.z.id.Id - 初始化，MACHINE_ID为0，MACHINE_BITS为8，SEQUENCE_BITS为12
        // [main] INFO cn.z.id.Id - 最大机器码MACHINE_ID为255，1ms内最多生成Id数量为4096，时钟最早回拨到2021-01-01 08:00:00
        // .0，可使用时间大约为278年，失效日期为2299-09-27 23:10:22.207
        // [main] WARN cn.z.id.Id - 已经初始化过了，不可重复初始化！
        // ID为：5483684734959616
        // ID为：5483684734959617
    }

    /**
     * 初始化异常
     */
    static void initException() {
        Id.init(1000, 8, 12);
        System.out.println("ID为：" + Id.next());
        // [main] INFO cn.z.id.Id - 预初始化...
        // [main] INFO cn.z.id.Id - 初始化，MACHINE_ID为0，MACHINE_BITS为8，SEQUENCE_BITS为12
        // [main] INFO cn.z.id.Id - 最大机器码MACHINE_ID为255，1ms内最多生成Id数量为4096，时钟最早回拨到2021-01-01 08:00:00
        // .0，可使用时间大约为278年，失效日期为2299-09-27 23:10:22.207
        // [main] INFO cn.z.id.Id - 手动初始化...
        // [main] INFO cn.z.id.Id - 初始化，MACHINE_ID为1000，MACHINE_BITS为8，SEQUENCE_BITS为12
        // [main] INFO cn.z.id.Id - 最大机器码MACHINE_ID为255，1ms内最多生成Id数量为4096，时钟最早回拨到2021-01-01 08:00:00
        // .0，可使用时间大约为278年，失效日期为2299-09-27 23:10:22.207
        // [main] ERROR cn.z.id.Id - 机器码MACHINE_ID需要>=0并且<=255。当前为1000
        // java.lang.Exception: 机器码无效
        // [main] ERROR cn.z.id.Id - 重置初始化...
        // [main] INFO cn.z.id.Id - 初始化，MACHINE_ID为0，MACHINE_BITS为8，SEQUENCE_BITS为12
        // [main] INFO cn.z.id.Id - 最大机器码MACHINE_ID为255，1ms内最多生成Id数量为4096，时钟最早回拨到2021-01-01 08:00:00
        // .0，可使用时间大约为278年，失效日期为2299-09-27 23:10:22.207
        // ID为：5483719912587264
    }

    /**
     * 阻塞
     */
    static void block() {
        // 初始化，复现阻塞
        Id.init(0, 0, 0);
        System.out.println("ID为：" + Id.next());
        System.out.println("ID为：" + Id.next());
        System.out.println("ID为：" + Id.next());
        System.out.println("ID为：" + Id.next());
        // [main] INFO cn.z.id.Id - 预初始化...
        // [main] INFO cn.z.id.Id - 初始化，MACHINE_ID为0，MACHINE_BITS为8，SEQUENCE_BITS为12
        // [main] INFO cn.z.id.Id - 最大机器码MACHINE_ID为255，1ms内最多生成Id数量为4096，时钟最早回拨到2021-01-01 08:00:00
        // .0，可使用时间大约为278年，失效日期为2299-09-27 23:10:22.207
        // [main] INFO cn.z.id.Id - 手动初始化...
        // [main] INFO cn.z.id.Id - 初始化，MACHINE_ID为0，MACHINE_BITS为0，SEQUENCE_BITS为0
        // [main] INFO cn.z.id.Id - 最大机器码MACHINE_ID为0，1ms内最多生成Id数量为1，时钟最早回拨到2021-01-01 08:00:00
        // .0，可使用时间大约为292471208年，失效日期为292269004-12-03 00:47:04.191
        // [main] WARN cn.z.id.Id - 检测到阻塞，时间为2021-03-02 20:44:07.469，最大序列号为0
        // [main] WARN cn.z.id.Id - 检测到阻塞，时间为2021-03-02 20:44:07.485，最大序列号为0
        // ID为：5229847469
        // ID为：5229847485
        // ID为：5229847500
        // [main] WARN cn.z.id.Id - 检测到阻塞，时间为2021-03-02 20:44:07.5，最大序列号为0
        // ID为：5229847516
    }

    /**
     * 时钟回拨(需要在1分钟内手动回拨时钟)
     */
    static void back() {
        for (int i = 0; i < 60; i++) {
            System.out.println("ID为：" + Id.next());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // [main] INFO cn.z.id.Id - 预初始化...
        // [main] INFO cn.z.id.Id - 初始化，MACHINE_ID为0，MACHINE_BITS为8，SEQUENCE_BITS为12
        // [main] INFO cn.z.id.Id - 最大机器码MACHINE_ID为255，1ms内最多生成Id数量为4096，时钟最早回拨到2021-01-01 08:00:00
        // .0，可使用时间大约为278年，失效日期为2299-09-27 23:10:22.207
        // ID为：5483989976481792
        // [main] WARN cn.z.id.Id - 监测到系统时钟发生了回拨。回拨时间为2021-03-02 19:45:33.249，上一个生成的时间为2021-03-02 20:45:40.392
        // ID为：5483989977530368
    }

    /**
     * 比较
     */
    static void compare() {
        // 初始化，避免阻塞
        Id.init(0, 0, 28);
        // [main] INFO cn.z.id.Id - 预初始化...
        // [main] INFO cn.z.id.Id - 初始化，MACHINE_ID为0，MACHINE_BITS为8，SEQUENCE_BITS为12
        // [main] INFO cn.z.id.Id - 最大机器码MACHINE_ID为255，1ms内最多生成Id数量为4096，时钟最早回拨到2021-01-01 08:00:00
        // .0，可使用时间大约为278年，失效日期为2299-09-27 23:10:22.207
        // [main] INFO cn.z.id.Id - 手动初始化...
        // [main] INFO cn.z.id.Id - 初始化，MACHINE_ID为0，MACHINE_BITS为0，SEQUENCE_BITS为28
        // [main] INFO cn.z.id.Id - 最大机器码MACHINE_ID为0，1ms内最多生成Id数量为268435456，时钟最早回拨到2021-01-01 08:00:00
        // .0，可使用时间大约为1年，失效日期为2022-02-03 00:22:18.367
        /*100万次*/
        compare(1000000);
        // 高性能雪花Id生成器调用1000000次使用时间为：47毫秒
        // Random调用1000000次使用时间为：15毫秒
        // UUID调用1000000次使用时间为：1175毫秒
        // 调用1000000次，高性能雪花Id生成器比UUID性能高25.0倍
        /*1000万次*/
        compare(10000000);
        // 高性能雪花Id生成器调用10000000次使用时间为：227毫秒
        // Random调用10000000次使用时间为：173毫秒
        // UUID调用10000000次使用时间为：8853毫秒
        // 调用10000000次，高性能雪花Id生成器比UUID性能高39.0倍
        /*1亿次*/
        compare(100000000);
        // 高性能雪花Id生成器调用100000000次使用时间为：909毫秒
        // Random调用100000000次使用时间为：793毫秒
        // UUID调用100000000次使用时间为：83628毫秒
        // 调用100000000次，高性能雪花Id生成器比UUID性能高92.0倍
        /*21亿次*/
        compare(Integer.MAX_VALUE);
        // 高性能雪花Id生成器调用2147483647次使用时间为：37871毫秒
        // Random调用2147483647次使用时间为：36886毫秒
        // UUID调用2147483647次使用时间为：7915039毫秒
        // 调用2147483647次，高性能雪花Id生成器比UUID性能高209.0倍
    }

    /**
     * 比较
     *
     * @param count 次数
     */
    static void compare(int count) {
        Random random = new Random();
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
        System.out.println("高性能雪花Id生成器调用" + count + "次使用时间为：" + ba + "毫秒");

        long c = Clock.now();
        for (int i = 0; i < count; i++) {
            random.nextLong();
        }
        long d = Clock.now();
        long dc = d - c;
        System.out.println("Random调用" + count + "次使用时间为：" + dc + "毫秒");

        long e = Clock.now();
        for (int i = 0; i < count; i++) {
            UUID.randomUUID();
        }
        long f = Clock.now();
        long fe = f - e;
        System.out.println("UUID调用" + count + "次使用时间为：" + fe + "毫秒");

        System.out.println("调用" + count + "次，高性能雪花Id生成器比UUID性能高" + fe / (double) ba + "倍");
    }

}
