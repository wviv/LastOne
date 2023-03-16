package com.wa.last.algorithm2.T1;

import java.util.HashSet;

/**
 * 存在重复元素
 */
public class Test1 {

    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5};
        System.out.println(solution(nums));;
    }

    private static boolean solution(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (!set.add(num)){
                return true;
            }
        }
        return false;
    }

}
