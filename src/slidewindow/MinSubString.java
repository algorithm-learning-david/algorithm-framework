package slidewindow;

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
            needs.put(t.charAt(i),needs.getOrDefault(t,0) + 1);
        }
        int valid = 0;
        int left = 0, right = 0;
        int minSubStartIdx = 0, minSubL = Integer.MAX_VALUE;
        while (right < s.length()){
            char in = s.charAt(right);

            window.put(in,window.getOrDefault(in,0));
            right ++;
            if(needs.containsKey(in)){
                valid ++;
            }
            while (left < right && valid == t.length()){
                char out = s.charAt(left);
                window.remove(out);
                left ++;
                if(needs.containsKey(out)){
                    valid --;
                }
                minSubL = Math.min(minSubL,right - left);
            }
        }
    }
}
