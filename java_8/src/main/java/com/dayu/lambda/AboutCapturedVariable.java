package com.dayu.lambda;

import org.junit.jupiter.api.Test;

/**
 * 关于变量捕获
 *
 * Java中 本地类\局部类（Local Class）和 匿名类（Anonymous Class）都存在变量捕获
 *
 * Java 8 的Lambda表达式也存在变量捕获
 *
 * Java 8开始，局部类、匿名内部类、Lambda表达式都可以访问最终（final）和实际最终（effectively final）的封闭块的局部变量和参数。
 * 实际最终：初始化之后，其中永远不会改变的变量或参数。
 *
 * @author hjh
 * @date 2021/1/2 21:54
 */
public class AboutCapturedVariable {

    private Integer i = 10;

    @Test
    public void print() {
        Integer localInt = 20; //捕获的变量（Captured Variable）

        //localInt初始化后不可更改..否则编译器报错

        class LocalClass {
            //局部类可以访问封闭类的成员
            public void say() {
                System.out.println(i);
                System.out.println(localInt);
            }
        }

        LocalClass localClass = new LocalClass();
        localClass.say();
    }

    @Test
    public void print2() {
        Integer localInt = 20; //捕获的变量（Captured Variable）

        //localInt初始化后不可更改..否则编译器报错

        //匿名内部类可以访问封闭类的成员
        Say say = new Say() {
            @Override
            public void say() {
                System.out.println(localInt);
                System.out.println(i);
            }
        };

        say.say();
    }

    @Test
    public void print3() {

        Integer localInt = 20;

        //localInt初始化后不可更改..否则编译器报错

        Thread thread = new Thread(() -> {
            System.out.println(localInt);
        });

    }

}

interface Say {
    void say();
}