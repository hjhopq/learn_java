package com.dayu.lambda;

import org.junit.jupiter.api.Test;

import java.util.function.*;

/**
 * Lambda表达式，也叫闭包，箭头函数
 *
 * Java 8 新特性，允许把函数作为方法参数。
 * 可以使代码变得简洁紧凑
 *
 * (parameters) -> {expression}
 * ->：这个是核心，其他的能省即省
 *
 *
 * @author hjh
 * @date 2021/1/2 20:47
 */
public class LambdaTest {

    /**
     * 各种各样的lambda表达式
     *
     * Lambda依赖于各种的函数式接口，Lambda表达式只能出现在目标类型为函数式接口的上下文中。
     *
     * 什么是函数式接口
     * 简单来说，就是只有一个方法的接口，Java中用@FunctionalInterface注解标注
     *
     * lambda表达式可以简化很多代码，
     * 
     * @author hjh
     * @date 2021/1/2
     */
    @Test
    public void variousLambda() {

        IntSupplier intSupplier = () -> 5;
        IntToLongFunction intToLong = x -> 2 * x;
        IntBinaryOperator intBinary = (x, y) -> x - y;
        //...
    }


    /**
     * Lambda简化代码的一个经典例子，线程的创建
     *
     * 没有Lambda表达式的时候，
     * 我们要写一个类 implements Runable接口（或直接new Runable(...)）
     * 然后创建该类型的对象，然后用来创建Thread类。
     *
     * 或者是直接继承Thread类...
     *
     * 但是，整体基本上是重复的，我们执行线程，不同的只有run()方法的内容而已，其他代码冗余了...
     *
     * 然后就问：下面代码优雅不优雅？
     *
     * 怎么知道这个箭头函数式Runable接口？  --> 编译器对上下文的推断...
     *
     * 关于Lambda性能？
     * 编译器处理Lambda表达式时，会根据Lambda的方法体是否需要访问外部变量（需要：变量捕获，不需要：变量不捕获）
     *
     * 变量不捕获：
     *  方法体会被提取到一个静态方法中，该静态方法和Lambda表达式位于同一类中。
     *
     * 变量捕获：
     *  比较的复杂，但是也会把Lambda表达式提取到一个静态方法中
     *
     * 即Lambda表达式 --> 方法调用  性能>> 匿名内部类
     *
     * @author hjh
     * @date 2021/1/2
     */
    @Test
    public void createThread() {

        Thread thread = new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
        }, "dayu-thread");

        thread.start();
    }


}
