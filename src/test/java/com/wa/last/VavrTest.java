package com.wa.last;

import io.vavr.Tuple;
import io.vavr.Tuple3;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Objects;

/**
 * @author echidna
 * @date 2019/9/9 16:13
 */
@Slf4j
public class VavrTest extends BaseServiceTest {

    @Test
    public void test() {
        Tuple3<String, Integer, Long> tuple3 = Tuple.of("wwc", 20, 1L);
//        Tuple3<String, Integer, Long> map = tuple3.map(
//                a -> "a" +a,
//                b ->  b + 1,
//                c -> c + 2
//        );
//        Function3<String, Integer, Long,Tuple3<String, Integer, Long>> function3 = (a,b,c) -> Tuple.of("b" +a, 20+2, 1L+3);
//        Tuple3<String, Integer, Long> map1 = tuple3.map(function3);
        Try<Integer> of = Try.of(() -> 1 / 1).filter(Objects::isNull);
        System.out.println(1);
    }
}
