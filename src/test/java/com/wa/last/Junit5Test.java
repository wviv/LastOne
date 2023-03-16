package com.wa.last;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * 
 * @date 2019/9/3 16:34
 */
@Slf4j
@RunWith(SpringRunner.class)
//@SpringBootTest
public class Junit5Test {

    @Test
    public void test() {
        List<Object> objects = Lists.newArrayList();
        objects.add("a");
        objects.add(1);
        List<Object> collect = objects.stream().filter(a -> a.equals("a")).collect(Collectors.toList());
        Predicate<String> a = s -> s.contains("a");
        Predicate<String> b = a.and(s -> s.contains("b"));
        Predicate<String> c = a.or(s -> s.contains("b"));
        String str = "1a";
        System.out.println(a.test(str));
        System.out.println(b.test(str));
        str = "1b";
        System.out.println(c.test(str));
        Predicate<String> anegate = a.negate();
        System.out.println(a.test(str));
        System.out.println(anegate.test(str));


    }
}
