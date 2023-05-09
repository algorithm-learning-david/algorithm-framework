package slidewindow;

import java.util.HashMap;
import java.util.Map;

public class SlideWindowFramework {

    public void slideWindow(String s, String needs){
        //记录窗口中满足条件的数据
        Map<Character,Integer> window = new HashMap<>();
        //窗口 [left,right)
        int left = 0, right = 0;
        //记录窗口中满足needs中字符的个数
        int valid = 0;
        while (right < s.length()){
            //将移入窗口的字符
            char in = s.charAt(right);
            window.put(in,window.getOrDefault(in,0));
            //增大窗口
            right ++;
            //进行窗口内数据的一系列更新
            // ...

            //判断左侧窗口是否要收缩
            while (left < right && valid == needs.length()){
                //将移出窗口的字符
                char out = s.charAt(left);
                window.remove(out);
                //缩小窗口
                left ++;
                //进行窗口内数据的一系列更新
                // ...
            }
        }
    }

}
