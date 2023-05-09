package model;

import java.util.LinkedList;

/**
 * Created by mxg on 2022/2/25
 * 单调队列
 */
public class MonotonicQueue {
    private LinkedList<Integer> q = new LinkedList<>();

    // 双链表，支持头部和尾部增删元素
    public void push(int n){
        //删除前面所有比 n 小的值
        while (!q.isEmpty() && q.getLast() < n){
            q.pollLast();
        }
        q.addLast(n);
    }

    public int max() {
        // 队头的元素肯定是最大的
        return q.getFirst();
    }

    public void pop(int n) {
        if (n == q.getFirst()) {
            q.pollFirst();
        }
    }
}
