package com.wa.last.interview;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.nio.charset.Charset;
import java.text.NumberFormat;
import java.util.*;

public class BloomFilterTest {

    private static int insertions = 10000000;

    public static void main(String[] args) {

//        int[] ints = {1, 2, 3, 4, 5};
//        Funnel<Integer> integerFunnel = Funnels.integerFunnel();
//        BloomFilter.create(Funnels.integerFunnel(), insertions,0.03);

        BloomFilter<String> bloomFilter = BloomFilter.create(Funnels.stringFunnel(Charset.defaultCharset()), insertions, 0.01);

        Set<String> sets = new HashSet<String>(insertions);

        List<String> lists = new ArrayList<String>(insertions);

        for (int i = 0; i < insertions; i++) {
            String uid = UUID.randomUUID().toString();
            bloomFilter.put(uid);
            sets.add(uid);
            lists.add(uid);
        }

        //正确
        int right = 0;
        //误判
        int wrong = 0;

        for (int i = 0; i < 10000; i++) {
            String data = i % 100 == 0 ? lists.get(i / 100) : UUID.randomUUID().toString();
            if (bloomFilter.mightContain(data)) {
                if (sets.contains(data)) {
                    right++;
                    continue;
                }
                wrong++;
            }
        }

        NumberFormat percentFormat = NumberFormat.getPercentInstance();
        percentFormat.setMaximumFractionDigits(2);
        //wrong 误判
        //9900 应该误判的次数

        //误判率
        float percent = (float) wrong / 9900;
        //命中率
        //9900 - wrong  是没存在  且  没有匹配到的数量
        float bingo = (float) (9900 - wrong) / 9900;
        System.out.println(right);
        System.out.println(wrong);

        System.out.println("在 " + insertions + " 条数据中，判断 100 实际存在的元素，布隆过滤器认为存在的数量为：" + right);
        System.out.println("在 " + insertions + " 条数据中，判断 9900 实际不存在的元素，布隆过滤器误认为存在的数量为：" + wrong);
        System.out.println("命中率为：" + percentFormat.format(bingo) + "，误判率为：" + percentFormat.format(percent));

    }
}
