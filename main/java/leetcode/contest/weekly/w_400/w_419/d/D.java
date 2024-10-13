package leetcode.contest.weekly.w_400.w_419.d;

import code_generation.utils.IoUtil;

import java.util.*;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: https://leetcode.cn/contest/weekly-contest-419/problems/find-x-sum-of-all-k-long-subarrays-ii
 * @title: 计算子数组的 x-sum II
 */
public class D {

    public static void main(String[] args) {
        IoUtil.testUtil(D.class, "findXSum", "D.txt");
    }


    static class Node implements Comparable<Node> {
        int cnt;
        int val;

        Node(int cnt, int val) {
            this.cnt = cnt;
            this.val = val;
        }

        @Override
        public int compareTo(Node o) {
            if (this.cnt == o.cnt) {
                return o.val - this.val;
            }
            return o.cnt - this.cnt;
        }

        @Override
        public int hashCode() {
            return this.val;
        }

        @Override
        public boolean equals(Object obj) {
            Node o = (Node) obj;
            return o.val == this.val;
        }

        @Override
        public String toString() {
            return "{ " + this.val + "," +this.cnt+ " }";
        }
    }

    //    TreeMap<Node,Integer> treeMap = new TreeMap<>();
    TreeSet<Node> treeSet = new TreeSet<>();
    TreeSet<Integer> treeMax = new TreeSet<>((a,b)->b.compareTo(a));
    Map<Integer, Node> map = new HashMap<>();

    public long[] findXSum(int[] nums, int k, int x) {
        int n = nums.length;
        long[] ans = new long[n - k + 1];
        long sums = 0;
        System.out.println("x = " + x);
        for (int i = 0; i < n; i++) {
            if (i >= k) {
                Node leaveNode = map.get(nums[i - k]);
                if (leaveNode != null) {
                    leaveNode.cnt--;
                    if (leaveNode.cnt == 0) {
                        map.remove(nums[i - k]);
                        treeSet.remove(leaveNode);
                    }
                }

            }
            Node updateNode = map.getOrDefault(nums[i], new Node(0, nums[i]));
            updateNode.cnt++;
            if (updateNode.cnt == 1) {
                map.put(nums[i], updateNode);
                treeSet.add(updateNode);
            }

            if (i >= k - 1) {
                ans[i - k + 1] = calc(x);
            }
        }
        return ans;
    }

    long calc(int x) {
        treeSet.forEach((k)->{
            System.out.print(k + "  ");
        });
        System.out.println();
        return 0;
    }


}