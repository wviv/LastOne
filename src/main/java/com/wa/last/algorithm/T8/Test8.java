package com.wa.last.algorithm.T8;


//import org.apache.http.annotation.Contract;

import org.apache.http.annotation.Contract;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 电话号码的字母组合
 */
public class Test8 {

    static String[] letter_map = {" ","*","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
    static List<String> res = new ArrayList<>();;

    public static void main(String[] args) {

        soulation2("23",new StringBuilder(),0);
        
        System.out.println();
//        iterStr("digits",new StringBuilder(),0);

    }

//    @Contract
    private static void soulation2(String digits,StringBuilder letter ,int index) {

        if (index == digits.length()){
            res.add(letter.toString());
            return ;
        }

        //当前处理字符
        char c = digits.charAt(index);

        String s = letter_map[c - '0'];
        char[] chars = s.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            letter.append(chars[i]);
            soulation2(digits,letter,index + 1);
            letter.deleteCharAt(letter.length() - 1);
        }

    }

    /**
     *
     * @param str   原始字符
     * @param letter
     * @param index 拼接在第几位
     */
    private static void iterStr(String str, StringBuilder letter, int index) {

        //递归的终止条件，注意这里的终止条件看上去跟动态演示图有些不同，主要是做了点优化
        //动态图中是每次截取字符串的一部分，"234"，变成"23"，再变成"3"，最后变成""，这样性能不佳
        //而用index记录每次遍历到字符串的位置，这样性能更好
        //从头部遍历到尾部
        if(index == str.length()) {
            res.add(letter.toString());
            return;
        }
        //获取index位置的字符，假设输入的字符是"234"
        //第一次递归时index为0所以c=2，第二次index为1所以c=3，第三次c=4
        //subString每次都会生成新的字符串，而index则是取当前的一个字符，所以效率更高一点
        char c = str.charAt(index);
        //map_string的下表是从0开始一直到9， c-'0'就可以取到相对的数组下标位置
        //比如c=2时候，2-'0'，获取下标为2,letter_map[2]就是"abc"
        int pos = c - '0';
        String map_string = letter_map[pos];
        //遍历字符串，比如第一次得到的是2，页就是遍历"abc"
        for(int i=0;i<map_string.length();i++) {
            //调用下一层递归，用文字很难描述，请配合动态图理解
            letter.append(map_string.charAt(i));
            //如果是String类型做拼接效率会比较低
            //iterStr(str, letter+map_string.charAt(i), index+1);
            iterStr(str, letter, index+1);
            //删除拼接的当前位,比如  abd,需要把d  删除 拼接  ab 和 e
            letter.deleteCharAt(letter.length()-1);
        }
    }

    private static List<String> soulation(String digits) {
        List<String> re = new ArrayList<>();

        if ("".equals(digits)){
            return re;
        }

        List<char[]> chars = new ArrayList<>();

        Map<Character, char[]> hashMap = new HashMap<>();

        hashMap.put('2', new char[]{'a','b','c'});
        hashMap.put('3', new char[]{'d','e','f'});
        hashMap.put('4', new char[]{'g','h','i'});
        hashMap.put('5', new char[]{'j','k','l'});
        hashMap.put('6', new char[]{'m','n','o'});
        hashMap.put('7', new char[]{'p','q','r','s'});
        hashMap.put('8', new char[]{'t','u','v'});
        hashMap.put('9', new char[]{'w','x','y','z'});

        // 分割
        char[] strChars = digits.toCharArray();

        for (int i = 0; i < strChars.length; i++) {
            chars.add(hashMap.get(strChars[i]));
        }

        char[] chars1 = chars.get(0);

        if (chars.size() == 1){
            for (char c : chars1) {
                re.add("" + c);
            }
            return re;
        }

        for (char c : chars1) {
            for (int i1 = 1; i1 < chars.size(); i1++) {
                char[] chars2 = chars.get(i1);
                //一个一个拼接
                for (char value : chars2) {
                    re.add("" + c + value);
                }
            }
        }
        return re;
    }
}
