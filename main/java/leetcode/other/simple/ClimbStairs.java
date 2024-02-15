package leetcode.other.simple;

import leetcode.annotation.Description;
import leetcode.utils.InvocationHandlerMethodTime;
import leetcode.utils.LogarithmicDevice;

/**
 * @author: wuxin0011
 * @Description: 爬楼梯
 * @see https://leetcode.cn/problems/climbing-stairs/
 */
@Description(value = "爬楼梯",url = "https://leetcode.cn/problems/climbing-stairs/" )
public class ClimbStairs implements LogarithmicDevice {


    int[] visited = new int[50];


    public int climbStairs(int n) {
        if (n <= 2) {
            return n;
        }
        if (visited[n] != 0) {
            return visited[n];
        }
        int temp = climbStairs(n - 1) + climbStairs(n - 2);
        visited[n] = temp;
        return temp;
    }


    public static void main(String[] args) {
        InvocationHandlerMethodTime.getRunTime(new ClimbStairs());
    }

    @Override
    public void logarithmicDevice() {
        System.out.println(new ClimbStairs().climbStairs(4));
    }
}
