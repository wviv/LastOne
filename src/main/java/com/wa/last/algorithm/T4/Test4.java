package com.wa.last.algorithm.T4;

/**
 * 最长公共前缀
 */
public class Test4 {

    public static void main(String[] args) {
        System.out.println(soulation(new String[]{"flower", "flow", "flight"}));
        System.out.println(soulation(new String[]{"flower", "flow", "floght"}));
        System.out.println(soulation(new String[]{"flower", "flow", "flowht"}));
        System.out.println(soulation(new String[]{"dog","racecar","car"}));
    }


    //纵向扫描
    public static String soulation(String[] strs) {
        StringBuilder s = new StringBuilder();
        String first = strs[0];
        //第一个字符串的长度
        one : for (int i = 0; i < first.toCharArray().length; i++) {
            //第一个字符
            char str = first.charAt(i);
            //从数组的第二个元素开始判断
            for (int j = 1; j < strs.length; j++) {
                //不是第一个
//                String notFirst = strs[j];
                //下标越界
                if (strs[j].length() <= i || str != strs[j].charAt(i)){
                    break one;
                }
//                char c1 = notFirst.charAt(i);
//                //不相等
//                if (str != c1){
//                    break one;
//                }
            }
            s.append(str);
        }
        return s.toString();
    }
}
