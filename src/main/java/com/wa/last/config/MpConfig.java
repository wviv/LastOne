//package com.chang.viv.user.config;
//
//import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.Arrays;
//
//@Configuration
//public class MpConfig {
//
//    @Bean
//    public PaginationInterceptor paginationInterceptor(){
//        return new PaginationInterceptor();
//    }
//    public static int[] twoSum(int[] nums, int target) {
//        for(int i = 0;i < nums.length;i++){
//            for(int j = i + 1;j < nums.length - i -1;i++){
//                if(target == nums[i]+nums[j]){
//                    return new int[]{i,j};
//                }
//            }
//        }
//        return null;
//    }
//
//    public static void main(String[] args) {
//        System.out.println(Arrays.toString(twoSum(new int[]{11, 7, 2, 15}, 9)));;
//    }
//}
