package com.dayu.lambda;

import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.function.*;

/**
 * 函数式接口
 * Java 8之前也有，但是没有@FunctionalInterface注解
 * 如Runable、Callable、Comparator、InvocationHandler...
 *
 * Java 8后
 *
 * 四大函数式接口 + 其衍生的函数式接口（特定的参数类型或返回值）
 *  Function<T, R> R apply(T t)     T：参数 R：返回值
 *  Consumer<T> void accept(T t)    T：参数，无返回值
 *  Supplier<T> T get()             T：返回值，无需参数
 *  Predicate<T> boolean test(T t)  T：参数，返回值：boolean
 *
 *  具体如下代码，
 *  四大函数式接口核心关注于 参数和返回值，
 *  即输入与输出，方法体即我们的逻辑。
 *
 * @author hjh
 * @date 2021/1/2 23:19
 */
public class AboutFunctionalInterface {

    /**
     * 关于Function接口 及其衍生
     *
     * @author hjh
     * @date 2021/1/2
     */

    @Test
    public void aboutFunction() {
        Function<Integer, Integer> function = (x) -> ++x;
        System.out.println(function.apply(1));

        // xxxFunction<R> 固定参数类型
        IntFunction<Integer> intFunction = (x) -> ++x;
        DoubleFunction<Double> doubleFunction = (x) -> ++x;
        LongFunction<Long> longLongFunction = (x) -> ++x;

        //ToXxxFunction<T> 固定返回值类型
        ToIntFunction<Integer> toIntFunction = (x) -> ++x;
        ToDoubleFunction<Double> toDoubleFunction = (x) -> ++x;
        ToLongFunction<Long> toLongFunction = (x) -> ++x;

        //XxxToXxxFunction 固定的参数类型和返回值类型  Integer、Double、Long三种的阶乘 一共六种类型的Function
        //..

        //UnaryOperation<T> 参数和返回值为同一类型
        UnaryOperator<Integer> unaryOperator = (x) -> ++x;

        //当这个类型为 Integer、Double、Long时，又有三种XxxOperation的函数式接口
        //...


        // --------------------- 开始接收两个参数--------------
        BiFunction<Integer, Integer, Integer> biFunction = (x, y) -> x + y + 1;

        //...

        //BinaryOperator<T>...

    }

    /**
     * 关于Consumer 基本和Function类型，都是泛型的特殊性，衍生出其他的函数式接口
     *
     * @author hjh
     * @date 2021/1/2
     */
    @Test
    public void aboutConsumer() {
        Consumer<Integer> consumer = System.out::println;

        //两个参数
        BiConsumer<Integer, Integer> biConsumer = (x, y) -> System.out.println(x + y);

    }

    /**
     * 关于Supplier 基本和Function类型，都是泛型的特殊性，衍生出其他的函数式接口
     *
     * @author hjh
     * @date 2021/1/2
     */
    @Test
    public void aboutSupplier() {
        Random random = new Random();

        Supplier<Integer> supplier = random::nextInt;

        //支持boolean，选择困难症的福音 boolean getAsBoolean();
        BooleanSupplier booleanSupplier = () -> false;

    }

    /**
     * 关于Predicate 基本和Function类型，都是泛型的特殊性，衍生出其他的函数式接口
     *
     * 断言
     *
     * @author hjh
     * @date 2021/1/2
     */
    @Test
    public void aboutPredicate() {
        Predicate<Integer> predicate = (x) -> x > 10;

        //也支持两个参数
        BiPredicate<Integer, Integer> biPredicate = (x, y) -> x + y > 10;

    }

}
