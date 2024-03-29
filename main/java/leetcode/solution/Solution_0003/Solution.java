package leetcode.solution.Solution_0003;

import code_generation.utils.IoUtil;

import java.util.Arrays;

/**
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/boats-to-save-people
 * @title: boats-to-save-people
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "numRescueBoats", "in.txt");
    }


    // 每艘船最多可同时载两人，但条件是这些人的重量之和最多为 limit。

    public int numRescueBoats(int[] people, int limit) {
        countSort(people);
        int cnt = 0;
        for (int i = 0, j = people.length - 1; i <= j; ) {
            cnt++;
            if (people[j] + people[i] <= limit) {
                i++;
            }
            j--;
        }
        return cnt;
    }


    public static void countSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        int mx = arr[0], mi = arr[0];
        for (int p : arr) {
            if (mx < p) {
                mx = p;
            }
            if (mi > p) {
                mi = p;
            }
        }
        if (mx == mi) {
            return;
        }
        int[] h = new int[mx - mi + 1];
        for (int p : arr) {
            h[p - mi]++;
        }
        for (int i = 0, k = 0; i < h.length && k < arr.length; i++) {
            while (h[i] > 0 && k < arr.length) {
                arr[k] = i + mi;
                k++;
                h[i]--;
            }
        }
    }


    public int numRescueBoats1(int[] people, int limit) {
        Arrays.sort(people);
        // System.out.println(Arrays.toString(people));
        int cnt = 0;
        for (int i = 0, j = people.length - 1; i <= j; ) {
            int tot = people[j] + people[i];
            if (tot <= limit) {
                i++;
            }
            j--;
            cnt++;
            if (i == j) {
                cnt++;
                break;
            }
        }
        return cnt;
    }

}