package com.wa.last.algorithm.T2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * 两数之和
 */
public class Test2 {


    public static void main(String[] args) {

        int a = 0;
        int b = 0;
        long stop;
        long stop1;

        List<Long> long1s = new ArrayList<>();
        List<Long> long2s = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {
            long s1 = System.currentTimeMillis();
            soulation(new int[]{1,1,5,7,8,9,3,2,2,4}, 6);
            long t1 = System.currentTimeMillis();

            stop = t1 - s1;

            long1s.add(stop);


            long s2 = System.currentTimeMillis();
            soulation2(new int[]{1,1,5,7,8,9,3,2,2,4}, 6);
            long t2 = System.currentTimeMillis();

            stop1 = t2 - s2;

            long2s.add(stop1);

            if (stop  < stop1){
                a++;
            }else {
                b++;
            }
        }
        System.out.println(a);
        System.out.println(b);
    }

    //循环一次查出来
    //实现原理 用hash  得到精确位置,省去一次循环
    private static int[] soulation2(int[] nums, int target) {

        int[] iis = new int[2];
        HashMap<Integer, Integer> hashMap = new HashMap<>();

        for(int i = 0;i < nums.length;i++){

            if (hashMap.containsKey(target - nums[i])){
                iis[0] = hashMap.get(target - nums[i]);
                iis[1] = i;
                break;
            }else if (!hashMap.containsKey(nums[i])){
                hashMap.put(nums[i], i);
            }

        }

        return iis;
    }

    //时间复杂度 O(n2)
    private static int[] soulation(int[] nums, int target) {

        for(int i = 0;i < nums.length;i++){
            for(int j = i +1;j < nums.length;j++){
                if(target == nums[i]+nums[j]){
                    return new int[]{i,j};
                }
            }
        }
        throw new RuntimeException();
    }

}
