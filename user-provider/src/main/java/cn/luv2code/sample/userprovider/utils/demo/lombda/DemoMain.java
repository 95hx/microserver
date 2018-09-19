package cn.luv2code.sample.userprovider.utils.demo.lombda;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.asList;

public class DemoMain {
    public static void main(String[] args) {

        Runnable runnable = () -> System.out.println();//只有一个方法的函数接口
        BinaryOperator<Long> add = (a, b) -> a + b;//类型推断
        BinaryOperator<Long> addExplicit = (Long a, Long b) -> a + b;//引用而不是变量

        List<String> list = new ArrayList<>();
        list.add("a1");
        list.add("zz");
        list.add("zz");

        //先构造操作,后迭代
        Long count = list.stream()
                .filter(str -> str.equals("zz"))
                .filter(str -> {
                    System.out.println("啥都不做");
                    return true;
                })
                .count();
//                .collect(Collectors.toList());
        list = Stream.of(list.toArray())
                .map(s -> s + "1")
                .collect(Collectors.toList());
        //list + list=>list
        List<Integer> together = Stream.of(asList(1, 2), asList(3, 4))
                .flatMap(ns -> ns.stream())
                .collect(Collectors.toList());
        //初始化数据
        List<Position> positions1 = asList(new Position(1, 1), new Position(0, 1));
        List<Position> positions2 = asList(new Position(1, 1), new Position(0, 1));
        List<Position> positions3 = asList(new Position(1, 1), new Position(0, 1));
        List<User> users = asList(
                new User("hx", 23, positions1),
                new User("h1", 23, positions2),
                new User("h2", 25, positions3));
        //求对象里属性最小值
        User userMinAge = users.stream()
                .min(Comparator.comparing(user -> user.age))
                .get();
        //求和
        int result = Stream.of(1, 2, 3)
                .reduce(10, (acc, element) -> acc + element);
    }
}
