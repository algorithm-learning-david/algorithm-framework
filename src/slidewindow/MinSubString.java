package slidewindow;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 76.最小覆盖子串
 */
public class MinSubString {
    public String minWindow(String s, String t) {
        if(s == null || s.length() == 0){
            return "";
        }
        if(t == null || t.length() == 0){
            return "";
        }
        if(t.length() > s.length()){
            return "";
        }
        Map<Character,Integer> window = new HashMap<>();
        Map<Character,Integer> needs = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            needs.put(c,needs.getOrDefault(c,0) + 1);
        }
        int valid = 0;
        int left = 0, right = 0;
        int minSubStartIdx = 0, minSubL = Integer.MAX_VALUE;
        while (right < s.length()){
            char in = s.charAt(right);
            right ++;
            if(needs.containsKey(in)){
                window.put(in,window.getOrDefault(in,0) + 1);
                if(window.get(in).equals(needs.get(in))){
                    valid ++;
                }
            }
            while (valid == needs.size()){
                if(right - left < minSubL){
                    minSubL = right - left;
                    minSubStartIdx = left;
                }
                char out = s.charAt(left);
                left ++;
                if(needs.containsKey(out)){
                    if(window.get(out).equals(needs.get(out))){
                        valid --;
                    }
                    window.put(out,window.get(out) - 1);
                }
            }
        }
        return minSubL == Integer.MAX_VALUE ? "" : s.substring(minSubStartIdx,minSubStartIdx + minSubL);
    }

    @Test
    public void testMinWindow(){
        String s = minWindow("aa", "aa");
        System.out.println(s);
    }
}
