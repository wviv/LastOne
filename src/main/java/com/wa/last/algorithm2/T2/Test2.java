package com.wa.last.algorithm2.T2;

/**
 * 最大子数组和
 */
public class Test2 {

    public static void main(String[] args) {
        int[] nums = {5,4,-1,7,8};
        System.out.println(solution(nums,nums[0],0));;
    }

    public int maxSubArray(int[] nums) {
        int pre = 0, maxAns = nums[0];
        for (int x : nums) {
            pre = Math.max(pre + x, x);
            maxAns = Math.max(maxAns, pre);
        }
        return maxAns;
    }

    private static int solution(int[] nums,int ind,int total) {
        if (nums.length == 0){
            return total;
        }

        //
        for (int i = 1; i < nums.length; i++) {

            total += nums[1];
        }

        return total;
    }

}
