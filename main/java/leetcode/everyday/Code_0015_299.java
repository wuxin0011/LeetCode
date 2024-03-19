package leetcode.everyday;

import code_generation.utils.IoUtil;

import java.util.*;

/**
 * @author: wuxin0011
 * @Description:
 * @title
 * @url https://leetcode.cn/problems/bulls-and-cows/?envType=daily-question&envId=2024-03-10
 */
@SuppressWarnings("all")
public class Code_0015_299 {

    public static void main(String[] args) {
        IoUtil.testUtil(Code_0015_299.class, "getHint", "./txt_file/Code_0015_299.txt");
    }

    public String getHint(String secret, String guess) {
        Map<Character,Integer> map1 = new HashMap<>();
        Map<Character,Integer> map2 = new HashMap<>();
        int n = secret.length();
        int aNum = 0;
        int bNum = 0;
        boolean[] vis = new boolean[n];
        for(int i = 0;i<n;i++){

            char c1 = secret.charAt(i);
            char c2 = guess.charAt(i);
            if(c1 == c2 ){
                aNum++;
                vis[i] = true;
            }else{
                map1.put(c1,map1.getOrDefault(c1,0)+1);
                map2.put(c2,map2.getOrDefault(c2,0)+1);
            }
        }
        // System.out.println("map1 = " + map1 + ",map2 = " + map2);
        for(int i = 0;i<n;i++){
            if(vis[i]) continue;
            char c1 = secret.charAt(i);
            //char c2 = guess.charAt(i);
            int n1 = map1.getOrDefault(c1,0);
            int n2 = map2.getOrDefault(c1,0);
            int nx = Math.min(n1,n2);
            bNum += nx;
            map1.put(c1,0);
            map2.put(c1,0);
        }

        StringBuilder sb = new StringBuilder();
        sb.append(aNum);
        sb.append('A');
        sb.append(bNum);
        sb.append('B');
        return sb.toString();
    }

}
