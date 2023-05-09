import org.junit.Test;

import javax.swing.*;
import java.util.*;

/**
 * Created by mxg on 2022/2/14
 *
 * 注意： 窗口是[left,right] 不是window
 */
public class SlideWindow {

    public static void main(String[] args) {
        String s = "ADOBECODEBANC", t = "ABC";
        System.out.println("输入： " + s + "  " + t);
        System.out.println("输出：" + minWindowMxg(s,t));
    }

    static Map<Character,Integer> need = new HashMap<>();
    static Map<Character,Integer> window = new HashMap<>(); //记录窗口中包含need中的字符
    //76.最小覆盖子串
    public static String minWindowMxg(String s, String t){
        int left = 0, right = 0;
        int valid = 0;//window中符合need中字符个数 valid + 1
        int sLen = s.length();
        //满足条件的字符串起始索引
        int start = 0;
        //满足条件的字符串长度
        int len = Integer.MAX_VALUE;
        for (int i = 0; i < t.length(); i ++){
            need.put(t.charAt(i),need.getOrDefault(t.charAt(i),0) + 1);
        }
        while (right < sLen){
            //将移入window的字符
            char in = s.charAt(right);
            //何时更新valid
            if(need.containsKey(in)){
                window.put(in,window.getOrDefault(in,0) + 1);
                if(need.get(in).equals(window.get(in))){
                    valid ++;
                }
            }
            right ++;
            //窗口已包含t中所有字符
            while (valid == need.size()){
                //更新最小满足条件长度
                if(right - left < len){
                    len = right - left;
                    start = left;
                }
                //开始增加left 缩小窗口
                //将移出window的字符
                char out = s.charAt(left);
                if(need.containsKey(out)){
                    if(window.get(out).equals(need.get(out))){
                        valid --;
                    }
                    window.put(out,window.getOrDefault(out,0) - 1);
                }
                left ++;
            }
        }
        return len == Integer.MAX_VALUE ? "" : s.substring(start,start + len);
    }

    /**
     * 官方解法 76.最小覆盖子串
     * */
    Map<Character, Integer> ori = new HashMap<>();
    Map<Character, Integer> cnt = new HashMap<>();

    public String minWindow2(String s, String t) {
        int tLen = t.length();
        for (int i = 0; i < tLen; i++) {
            char c = t.charAt(i);
            ori.put(c, ori.getOrDefault(c, 0) + 1);
        }
        int l = 0, r = -1;
        int len = Integer.MAX_VALUE, ansL = -1, ansR = -1;
        int sLen = s.length();
        while (r < sLen) {
            ++r;
            if (r < sLen && ori.containsKey(s.charAt(r))) {
                cnt.put(s.charAt(r), cnt.getOrDefault(s.charAt(r), 0) + 1);
            }
            while (check() && l <= r) {
                if (r - l + 1 < len) {
                    len = r - l + 1;
                    ansL = l;
                    ansR = l + len;
                }
                if (ori.containsKey(s.charAt(l))) {
                    cnt.put(s.charAt(l), cnt.getOrDefault(s.charAt(l), 0) - 1);
                }
                ++l;
            }
        }
        return ansL == -1 ? "" : s.substring(ansL, ansR);
    }

    public boolean check() {
        Iterator iter = ori.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            Character key = (Character) entry.getKey();
            Integer val = (Integer) entry.getValue();
            if (cnt.getOrDefault(key, 0) < val) {
                return false;
            }
        }
        return true;
    }


    @Test
    public void testlengthOfLongestSubstring(){
        int res = lengthOfLongestSubstring("bbbbb");
        System.out.println(res);
    }
    //3. 无重复字符的最长子串 输出长度 s = "abcabcbb"  s = "pwwkew"
    public static int lengthOfLongestSubstring(String s) {
        int len = 0;
        int left = 0, right = 0;
        int sLen = s.length();
        while (right < sLen){
            char in = s.charAt(right);
            window.put(in,window.getOrDefault(in,0) + 1);
            right ++;
            while (window.get(in) > 1){
                char out = s.charAt(left);
                window.put(out,window.getOrDefault(out,0) - 1);
                left ++;
            }
            if(right - left > len){
                len = right - left;
            }
        }
        return len;
    }

    @Test
    public void testfindAnagrams(){
        List<Integer> anagrams = findAnagrams("baa", "aa");
        System.out.println(anagrams);
    }

    //438. 找到字符串中所有字母异位词 s = "cbaebabacd", p = "abc"
    public List<Integer> findAnagrams(String s, String p) {
        int left = 0, right = 0;
        int sLen = s.length();
        int valid = 0;
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < p.length(); i++){
            need.put(p.charAt(i),need.getOrDefault(p.charAt(i),0) + 1);
        }
        while (right < sLen){
            char in = s.charAt(right);
            if(need.containsKey(in)){
                window.put(in,window.getOrDefault(in,0) + 1);
                if(window.get(in).equals(need.get(in))){
                    valid ++;
                }
            }
            right ++;
            while (right - left >= p.length()){
                if(valid == need.size()){
                    res.add(left);
                }
                char out = s.charAt(left);
                if(need.containsKey(out)){
                   if(window.get(out).equals(need.get(out))){
                       valid --;
                   }
                   window.put(out,window.getOrDefault(out,0) - 1);
                }
                left ++;
            }
        }
        return res;
    }

    @Test
    public void testcheckInclusion(){
        boolean b = checkInclusion("ab", "eidbooo");
        System.out.println(b);
    }
    //567.字符串的排列 s1 = "ab" s2 = "eidbaooo"
    public boolean checkInclusion(String s1, String s2) {
        int left = 0, right = 0;
        int sLen = s2.length();
        int valid = 0;
        for (int i = 0; i < s1.length(); i++){
            need.put(s1.charAt(i),need.getOrDefault(s1.charAt(i),0) + 1);
        }
        while (right < sLen){
            char in = s2.charAt(right);
            if(need.containsKey(in)){
                window.put(in,window.getOrDefault(in,0) + 1);
                if(window.get(in).equals(need.get(in))){
                    valid ++;
                }
            }
            right ++;
            while (right - left >= s1.length()){
                if(valid == need.size()){
                    return true;
                }
                char out = s2.charAt(left);
                if(need.containsKey(out)){
                    if(window.get(out).equals(need.get(out))){
                        valid --;
                    }
                    window.put(out,window.getOrDefault(out,0) - 1);
                }
                left ++;
            }
        }
        return false;
    }
}
